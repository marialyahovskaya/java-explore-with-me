package ru.practicum.ewm.event.service;

import com.querydsl.core.types.dsl.BooleanExpression;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.practicum.ewm.PaginationHelper;
import ru.practicum.ewm.category.Category;
import ru.practicum.ewm.category.CategoryRepository;
import ru.practicum.ewm.event.Event;
import ru.practicum.ewm.event.EventMapper;
import ru.practicum.ewm.event.EventRepository;
import ru.practicum.ewm.event.QEvent;
import ru.practicum.ewm.event.dto.EventFullDto;
import ru.practicum.ewm.event.dto.EventShortDto;
import ru.practicum.ewm.event.dto.NewEventDto;
import ru.practicum.ewm.event.enums.EventSort;
import ru.practicum.ewm.event.enums.EventState;
import ru.practicum.ewm.user.User;
import ru.practicum.ewm.user.UserRepository;
import ru.practicum.stats.client.StatsClient;
import ru.practicum.stats.dto.ViewStatsDto;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final StatsClient statsClient;

    @Override
    public Map<Long, Long> getViews(Collection<Event> events) {


        List<String> uris = events.stream()
                .map(Event::getId)
                .map(id -> "/events/" + id.toString())
                .collect(Collectors.toUnmodifiableList());

        List<ViewStatsDto> eventStats = statsClient.getStats(uris);

        Map<Long, Long> views = eventStats.stream()
                .filter(statRecord -> statRecord.getApp().equals("ewm-service"))
                .collect(Collectors.toMap(
                        statRecord -> {
                            Pattern pattern = Pattern.compile("\\/events\\/([0-9]*)");
                            Matcher matcher = pattern.matcher(statRecord.getUri());
                            return Long.parseLong(matcher.group(1));
                        },
                        ViewStatsDto::getHits)
                );
        return views;
    }

    @Override
    public EventFullDto addEvent(final Long userId, final NewEventDto eventDto) {
        User initiator = userRepository.findById(userId).orElseThrow(RuntimeException::new);
        Category category = categoryRepository.findById(eventDto.getCategory()).orElseThrow(RuntimeException::new);
        Event event = EventMapper.toEvent(initiator, category, eventDto);
        return EventMapper.toEventFullDto(eventRepository.save(event), 0L);
    }

    @Override
    public Collection<EventFullDto> findEvents(Long[] users,
                                               String[] states,
                                               Long[] categories,
                                               LocalDateTime rangeStart,
                                               LocalDateTime rangeEnd,
                                               Integer from,
                                               Integer size) {
        Pageable pageable = PaginationHelper.makePageable(from, size);
        QEvent qEvent = QEvent.event;
        BooleanExpression expression = qEvent.id.isNotNull();
        if (users != null && users.length > 0) {
            expression = expression.and(qEvent.initiator.id.in(users));
        }
        if (states != null && states.length > 0) {
            expression = expression.and(qEvent.state.in(Arrays.stream(states)
                    .map(EventState::valueOf)
                    .collect(Collectors.toUnmodifiableList())));
        }
        if (categories != null && categories.length > 0) {
            expression = expression.and(qEvent.category.id.in(categories));
        }
        if (rangeStart != null) {
            expression = expression.and(qEvent.eventDate.goe(rangeStart));
        }
        if (rangeEnd != null) {
            expression = expression.and(qEvent.eventDate.loe(rangeEnd));
        }

        Collection<Event> events = eventRepository.findAll(expression, pageable).getContent();

        Map<Long, Long> views = this.getViews(events);

        return EventMapper.toEventFullDto(events, views);
    }

    @Override
    public Collection<EventShortDto> findEvents(String text,
                                                Boolean paid,
                                                Boolean onlyAvailable,
                                                EventSort sort,
                                                Long[] categories,
                                                LocalDateTime rangeStart,
                                                LocalDateTime rangeEnd,
                                                Integer from,
                                                Integer size) {
        Pageable pageable = PaginationHelper.makePageable(from, size);
        QEvent qEvent = QEvent.event;
        BooleanExpression expression = qEvent.state.eq(EventState.PUBLISHED);
        if (text != null) {
            expression = expression.and(qEvent.annotation.containsIgnoreCase(text).or(qEvent.description.containsIgnoreCase(text)));
        }
        if (paid != null) {
            expression = expression.and(qEvent.paid.eq(paid));
        }
        if (categories != null && categories.length > 0) {
            expression = expression.and(qEvent.category.id.in(categories));
        }
        if (rangeStart != null) {
            expression = expression.and(qEvent.eventDate.goe(rangeStart));
        }
        if (rangeEnd != null) {
            expression = expression.and(qEvent.eventDate.loe(rangeEnd));
        }

        Collection<Event> events = eventRepository.findAll(expression, pageable).getContent();
        return EventMapper.toEventShortDto(events, this.getViews(events));
    }

    @Override
    public Collection<EventFullDto> findEvents(Long userId, Integer from, Integer size) {
        Pageable pageable = PaginationHelper.makePageable(from, size);
        Collection<Event> events = eventRepository.findByInitiatorId(userId, pageable);
        return EventMapper.toEventFullDto(events, this.getViews(events));
    }
}
