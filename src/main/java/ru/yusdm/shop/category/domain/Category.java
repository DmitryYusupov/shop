package ru.yusdm.shop.category.domain;

import lombok.Getter;
import lombok.Setter;
import ru.yusdm.shop.product.domain.Product;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "CATEGORY")
@Getter
@Setter
public class Category {

    @Id
    @Column(name = "ID")
    private UUID id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "DESCRIPTION")
    private String description;

    @JoinColumn(name = "PARENT_CATEGORY_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private Category parent;

    @OneToMany(mappedBy = "parent")
    private List<Category> subCategories;

    @OneToMany(mappedBy = "category", cascade = {
            CascadeType.PERSIST,
            CascadeType.DETACH,
            CascadeType.REMOVE
    })
    private List<Product> products;
}
