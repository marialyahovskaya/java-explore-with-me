package ru.practicum.ewm.request.service;

import ru.practicum.ewm.event.dto.EventFullDto;
import ru.practicum.ewm.event.dto.NewEventDto;
import ru.practicum.ewm.request.dto.ParticipationRequestDto;

public interface RequestService {

    ParticipationRequestDto addRequest(Long userId, Long eventId);

}
