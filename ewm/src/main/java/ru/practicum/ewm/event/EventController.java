package ru.practicum.ewm.event;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.practicum.ewm.category.dto.CategoryDto;
import ru.practicum.ewm.category.dto.NewCategoryDto;
import ru.practicum.ewm.category.service.CategoryService;
import ru.practicum.ewm.event.dto.EventFullDto;
import ru.practicum.ewm.event.dto.NewEventDto;
import ru.practicum.ewm.event.service.EventService;

import javax.validation.Valid;
import javax.validation.ValidationException;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "")
public class EventController {

    private final EventService eventService;



    @PostMapping("/users/{userId}/events")
    public ResponseEntity<EventFullDto> createEvent(@PathVariable Long userId,
                                                       @RequestBody @Valid NewEventDto eventDto) throws ValidationException {
        return new ResponseEntity<>(eventService.addEvent(userId, eventDto), HttpStatus.CREATED);
    }
}
