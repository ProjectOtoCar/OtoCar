package com.otocar.otocar.service;

import java.util.Map;
import java.util.Optional;

public interface CrudServce<ID, T> {
    Iterable<T> findAll();
    T findBy(ID id);
    Optional<Void> deleteById(ID id);
    T save(T obj);
    T change(ID id, T obj);
    Optional<Void> patch(ID id, Map<String, String> fields);
}
