package ru.yusdm.shop.common.service;

import java.util.Collection;
import java.util.Optional;

public interface BaseService<Type, ID> {
    Optional<Type> findById(ID id);

    Collection<Type> getAll();

    ID save(Type entity);

    void delete(ID id);

    void merge(Type entity);
}
