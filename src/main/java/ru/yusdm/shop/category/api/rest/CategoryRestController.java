package ru.yusdm.shop.category.api.rest;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.yusdm.shop.category.domain.Category;
import ru.yusdm.shop.category.dto.CategoryDomainDtoMapper;
import ru.yusdm.shop.category.dto.CategoryDto;
import ru.yusdm.shop.category.service.CategoryService;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping(value = CategoryRestController.CATEGORY_BASE_REST_CONTROLLER_URL)
@AllArgsConstructor
public class CategoryRestController {

    public static final String CATEGORY_BASE_REST_CONTROLLER_URL = "/api/rest/category";

    private final CategoryService categoryService;
    private final CategoryDomainDtoMapper categoryDomainDtoMapper;

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDto> getById(@PathVariable("id") String id) {
        return categoryService.findById(id)
                .map(domain -> ResponseEntity.ok(categoryDomainDtoMapper.fromDomainToDto(domain)))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<CategoryDto>> getAll() {
        Collection<Category> all = categoryService.getAll();
        List<CategoryDto> dtos = categoryDomainDtoMapper.fromDomainsToDtos(all);
        return ResponseEntity.ok(dtos);
    }

    @PostMapping
    public ResponseEntity<CategoryDto> save(@RequestBody CategoryDto categoryDto) {
        Category category = categoryDomainDtoMapper.fromDtoToDomain(categoryDto);
        categoryService.save(category);
        return ResponseEntity.status(HttpStatus.CREATED).body(categoryDomainDtoMapper.fromDomainToDto(category));
    }

    @PutMapping
    public ResponseEntity<CategoryDto> update(@RequestBody CategoryDto categoryDto) {
        Category category = categoryDomainDtoMapper.fromDtoToDomain(categoryDto);
        categoryService.merge(category);
        return ResponseEntity.ok(categoryDomainDtoMapper.fromDomainToDto(category));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") String id) {
        categoryService.delete(id);
        return ResponseEntity.ok().build();
    }

}
