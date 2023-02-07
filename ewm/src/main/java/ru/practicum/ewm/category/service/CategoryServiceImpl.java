package ru.practicum.ewm.category.service;

import lombok.RequiredArgsConstructor;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.practicum.ewm.PaginationHelper;
import ru.practicum.ewm.category.Category;
import ru.practicum.ewm.category.CategoryMapper;
import ru.practicum.ewm.category.CategoryRepository;
import ru.practicum.ewm.category.dto.CategoryDto;
import ru.practicum.ewm.category.dto.NewCategoryDto;
import ru.practicum.ewm.exception.ConflictException;

import java.util.Collection;


@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {


    private final CategoryRepository categoryRepository;

    @Override
    public CategoryDto addCategory(final NewCategoryDto categoryDto) {
        Category category = CategoryMapper.toCategory(categoryDto);
        Category createdCategory;
        try {
            createdCategory = categoryRepository.save(category);
        } catch (DataIntegrityViolationException e) {
            throw new ConflictException("Name is not unique");
        }
        return CategoryMapper.toCategoryDto(createdCategory);
    }

    @Override
    public Collection<CategoryDto> findCategories(Integer from, Integer size) {

        Pageable pageable = PaginationHelper.makePageable(from, size);

        return CategoryMapper.toCategoryDto(categoryRepository.findAll(pageable).getContent());
    }

    @Override
    public CategoryDto patchCategory(Long id, NewCategoryDto categoryDto) {
        Category categoryToUpdate = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));

        categoryToUpdate.setName(categoryDto.getName());
        Category updatedCategory;
        try {
            updatedCategory = categoryRepository.save(categoryToUpdate);
        } catch (DataIntegrityViolationException e) {
            throw new ConflictException("Name is not unique");
        }
        return CategoryMapper.toCategoryDto(updatedCategory);

    }
}
