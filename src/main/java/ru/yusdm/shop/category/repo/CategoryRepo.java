package ru.yusdm.shop.category.repo;

import org.springframework.data.repository.CrudRepository;
import ru.yusdm.shop.category.domain.Category;

import java.util.Collection;

public interface CategoryRepo extends CrudRepository<Category, String> {

    @Override
    Collection<Category> findAll();

}
