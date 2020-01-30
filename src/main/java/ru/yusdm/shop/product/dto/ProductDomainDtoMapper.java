package ru.yusdm.shop.product.dto;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;
import ru.yusdm.shop.category.domain.Category;
import ru.yusdm.shop.category.dto.CategoryDto;
import ru.yusdm.shop.common.domain.Currency;
import ru.yusdm.shop.common.mapper.CommonMappings;
import ru.yusdm.shop.product.domain.Product;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface ProductDomainDtoMapper extends CommonMappings {
    @Mappings({
            @Mapping(target = "currency", ignore = true)
    })
    ProductDto fromDomainToDto(Product product);
    List<ProductDto> fromDomainsToDtos(Collection<Product> products);

    @Mapping(target = "currency", source = "currency", defaultValue = "EUR")
    Product fromDtoToDomain(ProductDto product);

    List<Product> fromDtosToDomains(Collection<ProductDto> products);

    default CategoryDto categoryDtoFromDomain(Category category){
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setId(category.getId().toString());
        categoryDto.setName(category.getName());
        return categoryDto;
    }

  default Category categoryDomainFromDto(CategoryDto categoryDto){
        Category category = new Category();
        category.setId(UUID.fromString(categoryDto.getId()));
        category.setName(categoryDto.getName());
        return category;
    }


}
