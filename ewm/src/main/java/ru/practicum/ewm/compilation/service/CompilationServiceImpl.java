package ru.practicum.ewm.compilation.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.practicum.ewm.PaginationHelper;
import ru.practicum.ewm.compilation.CompilationMapper;
import ru.practicum.ewm.compilation.CompilationRepository;
import ru.practicum.ewm.compilation.dto.CompilationDto;

import java.util.Collection;

@Service
@RequiredArgsConstructor
public class CompilationServiceImpl implements CompilationService {

    public final CompilationRepository compilationRepository;


    @Override
    public Collection<CompilationDto> findCategories(Boolean pinned, Integer from, Integer size) {
        Pageable pageable = PaginationHelper.makePageable(from, size);
        if (pinned == null) {
            return CompilationMapper.toCompilationDto(compilationRepository.findAll(pageable).getContent());
        } else {
            return CompilationMapper.toCompilationDto(compilationRepository.findByPinned(pinned, pageable));
        }
    }
}
