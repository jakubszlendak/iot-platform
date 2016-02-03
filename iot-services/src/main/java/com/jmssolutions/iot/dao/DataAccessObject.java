package com.jmssolutions.iot.dao;

/**
 * Created by jakub on 03.02.16.
 */
public interface DataAccessObject<K, E> {
    E create(E entity);
    E findById(K id);
    E update(E entity);
    void remove(E entity);

}
