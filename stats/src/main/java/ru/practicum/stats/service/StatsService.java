package ru.practicum.stats.service;

import org.springframework.http.ResponseEntity;
import ru.practicum.stats.dto.EndpointHitDto;

public interface StatsService {
    void hit(EndpointHitDto hitDto);

}
