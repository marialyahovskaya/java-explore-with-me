package ru.practicum.stats.service;

import ru.practicum.stats.dto.EndpointHitDto;
import ru.practicum.stats.dto.ViewStatsDto;

import java.time.LocalDateTime;
import java.util.List;

public interface StatsService {
    void hit(EndpointHitDto hitDto);

    List<ViewStatsDto> getViewStats(LocalDateTime start, LocalDateTime end, String[] uris, Boolean unique);
}
