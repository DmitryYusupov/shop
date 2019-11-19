package ru.yusdm.shop.category.dto;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;
import ru.yusdm.shop.category.domain.Category;

import java.util.Collection;
import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface CategoryDomainDtoMapper {
    CategoryDto fromDomainToDto(Category category);
    List<CategoryDto> fromDomainsToDtos(Collection<Category> category);

    @Mappings({
         @Mapping(target = "subCategories", ignore = true),
         @Mapping(target = "products", ignore = true)
    })
    Category fromDtoToDomain(CategoryDto category);
    List<Category> fromDtosToDomains(Collection<CategoryDto> category);
}
