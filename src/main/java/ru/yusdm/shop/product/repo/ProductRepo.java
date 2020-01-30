package ru.yusdm.shop.product.repo;

import org.springframework.data.repository.CrudRepository;
import ru.yusdm.shop.product.domain.Product;

import java.util.Collection;
import java.util.UUID;

public interface ProductRepo extends CrudRepository<Product, UUID> {
    @Override
    Collection<Product> findAll();
}
