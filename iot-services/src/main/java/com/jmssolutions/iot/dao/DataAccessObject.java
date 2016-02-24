package com.jmssolutions.iot.dao;

import org.springframework.dao.DataIntegrityViolationException;

import java.util.List;

/**
 * Created by jakub on 03.02.16.
 */
public interface DataAccessObject<K, E> {
    E create(E entity) throws DataIntegrityViolationException;
    E findById(K id);
    List<E> findAll();
    E update(E entity);
    void remove(K entityId);


}
