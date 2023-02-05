package ru.practicum.ewm.compilation;

import ru.practicum.ewm.compilation.dto.CompilationDto;
import ru.practicum.ewm.compilation.dto.NewCompilationDto;
import ru.practicum.ewm.event.Event;
import ru.practicum.ewm.event.EventMapper;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

public class CompilationMapper {

    public static CompilationDto toCompilationDto (Compilation compilation) {
        CompilationDto compilationDto = new CompilationDto();
        compilationDto.setId(compilation.getId());
        compilationDto.setPinned(compilation.getPinned());
        compilationDto.setTitle(compilation.getTitle());
        compilationDto.setEvents(EventMapper.toEventShortDto(compilation.getEvents()));

        return compilationDto;
    }

    public static Collection<CompilationDto> toCompilationDto(Collection<Compilation> compilations) {
        return compilations.stream()
                .map(CompilationMapper::toCompilationDto)
                .collect(Collectors.toUnmodifiableList());

    }

    public static Compilation toCompilation(NewCompilationDto compilationDto) {
        Compilation compilation = new Compilation();
        compilation.setTitle(compilationDto.getTitle());
        compilation.setPinned(compilationDto.getPinned());
        compilation.setEvents(Arrays.stream(compilationDto.getEvents()).map((id)-> {
            Event event = new Event();
            event.setId(id);
            return event;
        }).collect(Collectors.toUnmodifiableList()));

        return compilation;
    }
}
