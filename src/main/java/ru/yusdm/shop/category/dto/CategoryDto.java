package ru.yusdm.shop.category.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class CategoryDto implements Serializable {
    private String id;
    private String name;
    private String description;
    private CategoryDto parent;
}
