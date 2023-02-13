package ru.practicum.stats.server;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.stats.dto.EndpointHitDto;
import ru.practicum.stats.server.dto.ViewStatsDto;
import ru.practicum.stats.server.service.StatsService;

import javax.validation.Valid;
import javax.validation.ValidationException;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping(path = "")
@RequiredArgsConstructor
@Validated
@Slf4j
public class StatsController {

    private final StatsService statsService;

    @GetMapping("/stats")
    public ResponseEntity<List<ViewStatsDto>> getViewStats(
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime start,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime end,
            @RequestParam(required = false) String[] uris,
            @RequestParam(required = false, defaultValue = "false") Boolean unique) {
        return new ResponseEntity<>(statsService.getViewStats(start, end, uris, unique), HttpStatus.OK);
    }

    @PostMapping("/hit")
    public ResponseEntity<Void> hit(@RequestBody @Valid EndpointHitDto hitDto)
            throws ValidationException {
        statsService.hit(hitDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
