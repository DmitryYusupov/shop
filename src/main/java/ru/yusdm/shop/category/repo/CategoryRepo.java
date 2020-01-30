package ru.yusdm.shop.category.repo;

import org.springframework.data.repository.CrudRepository;
import ru.yusdm.shop.category.domain.Category;

import java.util.Collection;
import java.util.UUID;

public interface CategoryRepo extends CrudRepository<Category, UUID> {

    @Override
    Collection<Category> findAll();

}
