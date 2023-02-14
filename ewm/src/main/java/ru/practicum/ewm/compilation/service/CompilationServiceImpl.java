package ru.practicum.ewm.compilation.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.practicum.ewm.PaginationHelper;
import ru.practicum.ewm.compilation.Compilation;
import ru.practicum.ewm.compilation.CompilationMapper;
import ru.practicum.ewm.compilation.CompilationRepository;
import ru.practicum.ewm.compilation.dto.CompilationDto;
import ru.practicum.ewm.compilation.dto.NewCompilationDto;
import ru.practicum.ewm.event.Event;
import ru.practicum.ewm.event.EventRepository;
import ru.practicum.ewm.event.service.EventService;
import ru.practicum.ewm.exception.NotFoundException;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CompilationServiceImpl implements CompilationService {

    public final CompilationRepository compilationRepository;

    public final EventRepository eventRepository;

    public final EventService eventService;

    @Override
    public Collection<CompilationDto> findCompilations(Boolean pinned, Integer from, Integer size) {
        Pageable pageable = PaginationHelper.makePageable(from, size);
        Collection<Compilation> compilations;

        if (pinned == null) {
            compilations = compilationRepository.findAll(pageable).getContent();
        } else {
            compilations = compilationRepository.findByPinned(pinned, pageable);
        }

        Set<Event> events = compilations.stream()
                .map(Compilation::getEvents)
                .flatMap(Collection::stream)
                .collect(Collectors.toSet());

        Map<Long, Long> views = eventService.getViews(events);

        return CompilationMapper.toCompilationDto(compilations, views);
    }

    @Override
    public CompilationDto addCompilation(NewCompilationDto compilationDto) {
        Collection<Event> events = eventRepository.findAllById(Arrays.asList(compilationDto.getEvents()));
        if (compilationDto.getEvents().length != events.size()) {
            throw new NotFoundException("At least one event not found");
        }
        Compilation compilation = CompilationMapper.toCompilation(compilationDto, events);
        Map<Long, Long> views = eventService.getViews(compilation.getEvents());
        return CompilationMapper.toCompilationDto(compilationRepository.save(compilation), views);
    }

    @Override
    public void deleteCompilation(Long id) {
        compilationRepository.findById(id).orElseThrow(() -> new NotFoundException("Compilation not found"));
        compilationRepository.deleteById(id);
    }

    @Override
    public CompilationDto findCompilation(Long id) {
        Compilation compilation = compilationRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Compilation not found"));
        Map<Long, Long> views = eventService.getViews(compilation.getEvents());
        return CompilationMapper.toCompilationDto(compilation, views);
    }

    @Override
    public CompilationDto patchCompilation(Long id, NewCompilationDto compilationDto) {
        Compilation compilationToUpdate = compilationRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Compilation not found"));


        if (compilationDto.getPinned() != null) {
            compilationToUpdate.setPinned(compilationDto.getPinned());
        }
        if (compilationDto.getTitle() != null) {
            compilationToUpdate.setTitle(compilationDto.getTitle());
        }
        if (compilationDto.getEvents() != null) {
            Collection<Event> newEvents = eventRepository.findAllById(Arrays.asList(compilationDto.getEvents()));
            if (compilationDto.getEvents().length != newEvents.size()) {
                throw new NotFoundException("At least one event not found");
            }
            compilationToUpdate.setEvents(newEvents);
        }

        Map<Long, Long> views = eventService.getViews(compilationToUpdate.getEvents());

        return CompilationMapper.toCompilationDto(compilationRepository.save(compilationToUpdate), views);
    }
}
