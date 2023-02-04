package ru.practicum.ewm.event.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.practicum.ewm.category.Category;
import ru.practicum.ewm.category.CategoryRepository;
import ru.practicum.ewm.event.Event;
import ru.practicum.ewm.event.EventMapper;
import ru.practicum.ewm.event.EventRepository;
import ru.practicum.ewm.event.dto.EventFullDto;
import ru.practicum.ewm.event.dto.NewEventDto;
import ru.practicum.ewm.user.User;
import ru.practicum.ewm.user.UserRepository;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService{

    private final EventRepository eventRepository;
    private final UserRepository userRepository;

    private final CategoryRepository categoryRepository;

    @Override
    public EventFullDto addEvent(final Long userId, final NewEventDto eventDto) {

        User initiator = userRepository.findById(userId).orElseThrow(RuntimeException::new);
        Category category = categoryRepository.findById(eventDto.getCategory()).orElseThrow(RuntimeException::new);
        Event event = EventMapper.toEvent(initiator, category, eventDto);
        return EventMapper.toEventFullDto(eventRepository.save(event));
    }
}
