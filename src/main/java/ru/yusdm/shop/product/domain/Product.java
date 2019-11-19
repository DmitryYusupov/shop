package ru.yusdm.shop.product.domain;

import lombok.Getter;
import lombok.Setter;
import ru.yusdm.shop.category.domain.Category;
import ru.yusdm.shop.common.domain.Currency;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "PRODUCT")
public class Product {
    @Id
    @Column(name = "ID")
    private String id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "PRICE")
    private double price;

    @Transient
    private Currency currency = Currency.EUR;

    @ManyToOne
    @JoinColumn(name = "CATEGORY_ID")
    private Category category;

}
