package ru.practicum.ewm.compilation.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.practicum.ewm.PaginationHelper;
import ru.practicum.ewm.category.Category;
import ru.practicum.ewm.compilation.Compilation;
import ru.practicum.ewm.compilation.CompilationMapper;
import ru.practicum.ewm.compilation.CompilationRepository;
import ru.practicum.ewm.compilation.dto.CompilationDto;
import ru.practicum.ewm.compilation.dto.NewCompilationDto;
import ru.practicum.ewm.event.Event;
import ru.practicum.ewm.event.EventMapper;
import ru.practicum.ewm.event.service.EventService;
import ru.practicum.ewm.user.User;
import ru.practicum.stats.client.StatsClient;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CompilationServiceImpl implements CompilationService {

    public final CompilationRepository compilationRepository;

    public final EventService eventService;

    @Override
    public Collection<CompilationDto> findCategories(Boolean pinned, Integer from, Integer size) {
        Pageable pageable = PaginationHelper.makePageable(from, size);
        Collection<Compilation> compilations = new ArrayList<>();

        if (pinned == null) {
            compilations = compilationRepository.findAll(pageable).getContent();
        } else {
            compilations = compilationRepository.findByPinned(pinned, pageable);
        }

        Set<Event> events = compilations.stream()
                .map(compilation -> compilation.getEvents())
                .flatMap(Collection::stream)
                .collect(Collectors.toSet());

        Map<Long, Long> views = eventService.getViews(events);

        return CompilationMapper.toCompilationDto(compilations, views);
    }

    @Override
    public CompilationDto addCompilation(NewCompilationDto compilationDto) {
        Compilation compilation = CompilationMapper.toCompilation(compilationDto);
        Map<Long, Long> views = eventService.getViews(compilation.getEvents());
        return CompilationMapper.toCompilationDto(compilationRepository.save(compilation), views);

    }
}
