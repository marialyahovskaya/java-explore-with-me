package ru.practicum.ewm.request.dto;

import lombok.Getter;
import lombok.Setter;
import ru.practicum.ewm.request.enums.ParticipationRequestStatus;

import java.time.LocalDateTime;

@Setter
@Getter
public class ParticipationRequestDto {

    private Long id;
    private LocalDateTime created;
    private Long event;
    private Long requester;
    private ParticipationRequestStatus status;
}
