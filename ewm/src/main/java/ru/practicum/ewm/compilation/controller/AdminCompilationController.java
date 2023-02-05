package ru.practicum.ewm.compilation.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.practicum.ewm.compilation.dto.CompilationDto;
import ru.practicum.ewm.compilation.dto.NewCompilationDto;
import ru.practicum.ewm.compilation.service.CompilationService;
import ru.practicum.ewm.event.dto.EventFullDto;
import ru.practicum.ewm.event.dto.NewEventDto;
import ru.practicum.stats.client.StatsClient;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.ValidationException;
import java.util.Collection;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/admin/compilations")
public class AdminCompilationController {

    private final CompilationService compilationService;

    private final StatsClient statsClient;

    @GetMapping
    public ResponseEntity<Collection<CompilationDto>> findCompilations(
            HttpServletRequest request,
            @RequestParam (required = false) Boolean pinned,
            @RequestParam(required = false, defaultValue = "0") Integer from,
            @RequestParam(required = false, defaultValue = "10") Integer size) throws ValidationException {
        statsClient.hit(request);
        return new ResponseEntity<>(compilationService.findCategories(pinned, from, size), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CompilationDto> createCompilation(
                                                    @RequestBody @Valid NewCompilationDto compilationDto) throws ValidationException {
        return new ResponseEntity<>(compilationService.addCompilation(compilationDto), HttpStatus.CREATED);
    }
}
