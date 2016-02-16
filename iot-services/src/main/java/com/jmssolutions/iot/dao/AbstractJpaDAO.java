package com.jmssolutions.iot.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * Created by jakub on 03.02.16.
 */
public abstract class AbstractJpaDAO<K, E> implements DataAccessObject<K, E> {

    protected Class entityClass;

    @PersistenceContext
    protected EntityManager entityManager;

    public AbstractJpaDAO() {
        ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();
        this.entityClass = (Class<E>) genericSuperclass.getActualTypeArguments()[1];
    }

    @Override
    @Transactional
    public E create(E entity) {
        entityManager.persist(entity);
        entityManager.flush();
        entityManager.detach(entity);
        return entity;
    }

    @Override
    public E findById(K id) {
        return (E)entityManager.find(entityClass, id);
    }

    @Override
    public List<E> findAll(){
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<E> q = cb.createQuery(entityClass);
        Root<E> root = q.from(entityClass);
        q.select(root);
        TypedQuery<E> typedQuery = entityManager.createQuery(q);
        return typedQuery.getResultList();
    }

    @Override
    @Transactional
    public E update(E entity) {
        E e =entityManager.merge(entity);
        entityManager.detach(e);
        return e;
    }

    @Override
    @Transactional
    public void remove(K id) {
        E e = (E)entityManager.find(entityClass, id);
        entityManager.remove(e);
    }

}
