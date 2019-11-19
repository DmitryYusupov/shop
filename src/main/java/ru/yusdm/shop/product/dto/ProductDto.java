package ru.yusdm.shop.product.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.yusdm.shop.category.dto.CategoryDto;
import ru.yusdm.shop.common.domain.Currency;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class ProductDto implements Serializable {
    private String id;
    private String name;
    private CategoryDto category;
    private long price;
    private Currency currency = Currency.EUR;
}
