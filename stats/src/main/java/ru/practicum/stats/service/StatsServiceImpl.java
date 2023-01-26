package ru.practicum.stats.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.practicum.stats.StatsRepository;
import ru.practicum.stats.dto.EndpointHitDto;
import ru.practicum.stats.mapper.EndpointHitMapper;

@Service
@RequiredArgsConstructor
public class StatsServiceImpl implements StatsService{

    private final StatsRepository statsRepository;
    @Override
    public void hit(EndpointHitDto hitDto) {
        statsRepository.save(EndpointHitMapper.toEndpointHit(hitDto));
    }
}
