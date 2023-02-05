package ru.practicum.ewm.compilation.service;

import ru.practicum.ewm.compilation.dto.CompilationDto;

import java.util.Collection;

public interface CompilationService {
    Collection<CompilationDto> findCategories(Boolean pinned, Integer from, Integer size);

}
