package com.modsen.books.services;

import java.util.List;

public interface AbstractService<K,T,V> {
    void add(T dto);
    V findById(K id);
    List<V> findAll();
    void update(T dto,K id);
    void delete(K id);
}
