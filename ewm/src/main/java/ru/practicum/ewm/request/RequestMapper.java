package ru.practicum.ewm.request;

import ru.practicum.ewm.request.dto.ParticipationRequestDto;

public class RequestMapper {
    public static ParticipationRequestDto toRequestDto(ParticipationRequest request) {
        ParticipationRequestDto requestDto = new ParticipationRequestDto();
        requestDto.setId(request.getId());
        requestDto.setCreated(request.getCreated());
        requestDto.setEvent(request.getEventId());
        requestDto.setRequester(request.getRequesterId());
        requestDto.setStatus(request.getStatus());

        return requestDto;
    }
}
