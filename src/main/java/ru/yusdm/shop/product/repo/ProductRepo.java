package ru.yusdm.shop.product.repo;

import org.springframework.data.repository.CrudRepository;
import ru.yusdm.shop.product.domain.Product;

import java.util.Collection;

public interface ProductRepo extends CrudRepository<Product, String> {
    @Override
    Collection<Product> findAll();
}
