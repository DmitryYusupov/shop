package ru.yusdm.shop.category.service.impl;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.yusdm.shop.category.domain.Category;
import ru.yusdm.shop.category.repo.CategoryRepo;
import ru.yusdm.shop.category.service.CategoryService;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
@AllArgsConstructor
public class DefaultCategoryService implements CategoryService {

    private final CategoryRepo categoryRepo;

    @Override
    public Optional<Category> findById(String id) {
        return categoryRepo.findById(id);
    }

    @Override
    public Collection<Category> getAll() {
        return categoryRepo.findAll();
    }

    @Override
    public String save(Category category) {

        String id = category.getId();
        if (id == null) {
            id = UUID.randomUUID().toString();
            category.setId(id);
        }

        return categoryRepo.save(category).getId();
    }

    @Override
    public void delete(String id) {
        categoryRepo.deleteById(id);
    }

    @Override
    public void merge(Category entity) {
        categoryRepo.save(entity);
    }
}
