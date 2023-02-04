package ru.practicum.ewm.request;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.practicum.ewm.event.dto.EventFullDto;
import ru.practicum.ewm.event.dto.NewEventDto;
import ru.practicum.ewm.event.service.EventService;
import ru.practicum.ewm.request.dto.ParticipationRequestDto;
import ru.practicum.ewm.request.service.RequestService;

import javax.validation.Valid;
import javax.validation.ValidationException;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "")
public class RequestController {

    private final RequestService requestService;

    @PostMapping("/users/{userId}/requests")
    public ResponseEntity<ParticipationRequestDto> createRequest(@PathVariable Long userId,
                                                                 @RequestParam Long eventId) throws ValidationException {
        return new ResponseEntity<>(requestService.addRequest(userId, eventId), HttpStatus.CREATED);
    }

}
