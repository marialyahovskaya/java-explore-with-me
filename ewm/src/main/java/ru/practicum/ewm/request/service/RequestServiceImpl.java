package ru.practicum.ewm.request.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.practicum.ewm.event.EventRepository;
import ru.practicum.ewm.request.ParticipationRequest;
import ru.practicum.ewm.request.RequestMapper;
import ru.practicum.ewm.request.RequestRepository;
import ru.practicum.ewm.request.dto.ParticipationRequestDto;
import ru.practicum.ewm.user.UserRepository;

@Service
@RequiredArgsConstructor
public class RequestServiceImpl implements RequestService{

    private final RequestRepository requestRepository;

    private final UserRepository userRepository;

    private final EventRepository eventRepository;


    @Override
    public ParticipationRequestDto addRequest(Long userId, Long eventId) {
        userRepository.findById(userId).orElseThrow(RuntimeException::new);
        eventRepository.findById(eventId).orElseThrow(RuntimeException::new);
        return RequestMapper.toRequestDto(requestRepository.save(ParticipationRequest.of(userId, eventId)));
    }
}
