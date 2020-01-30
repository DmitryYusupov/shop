package ru.yusdm.shop.category.service.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import ru.yusdm.shop.category.domain.Category;
import ru.yusdm.shop.category.service.CategoryService;
import ru.yusdm.shop.common.database.BaseDatabaseIntegrationTest;

import java.util.Optional;
import java.util.UUID;


@Transactional
class DefaultCategoryServiceTest extends BaseDatabaseIntegrationTest {

  @Autowired
  private CategoryService categoryService;

  @Test
  public void testSave() {
    Category category = new Category();
    UUID id = UUID.randomUUID();
    category.setId(id);
    category.setName("Name_1");
    category.setDescription("description");

    categoryService.save(category);

    Optional<Category> categoryOptional = categoryService.findById(id);
    Assertions.assertTrue(categoryOptional.isPresent());

    System.out.println("SIZE " + categoryService.getAll().size());
  }

  @Test
  public void testSave2() {
    Category category = new Category();
    UUID id = UUID.randomUUID();
    category.setId(id);
    category.setName("Name_1");
    category.setDescription("description");

    categoryService.save(category);

    Optional<Category> categoryOptional = categoryService.findById(id);
    Assertions.assertTrue(categoryOptional.isPresent());

    System.out.println("SIZE " + categoryService.getAll().size());
  }
}