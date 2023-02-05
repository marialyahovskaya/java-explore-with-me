package ru.practicum.ewm.compilation.service;

import ru.practicum.ewm.compilation.dto.CompilationDto;
import ru.practicum.ewm.compilation.dto.NewCompilationDto;

import java.util.Collection;

public interface CompilationService {
    Collection<CompilationDto> findCategories(Boolean pinned, Integer from, Integer size);

    CompilationDto addCompilation(NewCompilationDto compilationDto);

}
