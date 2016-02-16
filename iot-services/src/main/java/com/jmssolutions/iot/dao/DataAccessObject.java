package com.jmssolutions.iot.dao;

import java.util.List;

/**
 * Created by jakub on 03.02.16.
 */
public interface DataAccessObject<K, E> {
    E create(E entity);
    E findById(K id);
    List<E> findAll();
    E update(E entity);
    void remove(K entityId);


}
