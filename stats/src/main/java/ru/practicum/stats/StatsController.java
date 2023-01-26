package ru.practicum.stats;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.stats.dto.EndpointHitDto;
import ru.practicum.stats.service.StatsService;

import javax.validation.Valid;
import javax.validation.ValidationException;

@RestController
@RequestMapping(path = "")
@RequiredArgsConstructor
@Validated
public class StatsController {

    private final StatsService statsService;

//    @GetMapping("/stats")
//    public ResponseEntity<Object> findBookingsByOwnerId(
//            @RequestHeader("X-Sharer-User-Id") Long userId,
//            @RequestParam(required = false, defaultValue = "ALL") String state,
//            @PositiveOrZero @RequestParam(required = false, defaultValue = "0") Integer from,
//            @Positive @RequestParam(required = false, defaultValue = "100") Integer size) {
//        BookingRequestState requestedState = BookingRequestState.from(state)
//                .orElseThrow(() -> new IllegalArgumentException("Unknown state: " + state));
//        return bookingClient.findBookingsByOwnerId(userId, state, from, size);
//    }

    @PostMapping("/hit")
    public ResponseEntity<Void> hit(@RequestBody @Valid EndpointHitDto hitDto)
            throws ValidationException {
        statsService.hit(hitDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
