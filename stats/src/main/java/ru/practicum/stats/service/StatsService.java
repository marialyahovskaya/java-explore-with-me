package ru.practicum.stats.service;

import ru.practicum.stats.dto.EndpointHitDto;
import ru.practicum.stats.model.ViewStats;

import java.time.LocalDateTime;
import java.util.List;

public interface StatsService {
    void hit(EndpointHitDto hitDto);

    List<ViewStats> getViewStats(LocalDateTime start, LocalDateTime end, String[] uris, Boolean unique);
}
