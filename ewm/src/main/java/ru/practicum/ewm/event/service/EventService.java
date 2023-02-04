package ru.practicum.ewm.event.service;

import ru.practicum.ewm.event.dto.EventFullDto;
import ru.practicum.ewm.event.dto.NewEventDto;

import java.time.LocalDateTime;
import java.util.Collection;

public interface EventService {

    EventFullDto addEvent(Long userId, NewEventDto eventDto);

    Collection<EventFullDto> findEvents(Long[] users,
                                        String[] states,
                                        Long[] categories,
                                        LocalDateTime rangeStart,
                                        LocalDateTime rangeEnd,
                                        Integer from,
                                        Integer size);

}
