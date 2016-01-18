package com.jmssolutions.iot.dao;

import com.jmssolutions.iot.domain.Role;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by jakub on 18.01.16.
 */
@Repository
public class RoleDAOImpl implements RoleDAO {

    private final static Logger logger = Logger.getLogger(UserDAOImpl.class);

    @PersistenceContext
    EntityManager entityManager;

    @Override
    @Transactional
    public Role getRoleById(long id) {
        return entityManager.find(Role.class, id);
    }

    @Override
    @Transactional
    public Role getRoleByName(String name) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Role> q = cb.createQuery(Role.class);
        Root<Role> r = q.from(Role.class);
        q.select(r);
        ParameterExpression<String> uName = cb.parameter(String.class);
        ParameterExpression<String> uEmail = cb.parameter(String.class);
        q.where(
                cb.equal(r.get("role"), name)
        );
        TypedQuery<Role> typedQuery = entityManager.createQuery(q);
        List<Role> result = typedQuery.getResultList();
        if(result.isEmpty()) {
            throw new EntityNotFoundException();
        }
        if(result.size() > 1)
            throw new NonUniqueResultException();

        return typedQuery.getResultList().get(0);
    }

    @Override
    @Transactional
    public void insertRole(Role role) {
        role = entityManager.merge(role);
        entityManager.flush();

    }

    @Override
    @Transactional
    public void deleteRole(long id) {
        Role r = entityManager.find(Role.class, id);
        entityManager.remove(r);
        entityManager.flush();

    }

    @Override
    @Transactional
    public List<Role> getAllRoles() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Role> q = cb.createQuery(Role.class);
        Root<Role> r = q.from(Role.class);
        q.select(r);

        TypedQuery<Role> typedQuery = entityManager.createQuery(q);
        return typedQuery.getResultList();
    }
}
