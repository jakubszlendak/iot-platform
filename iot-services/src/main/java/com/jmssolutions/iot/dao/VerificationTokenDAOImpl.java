package com.jmssolutions.iot.dao;

import com.jmssolutions.iot.domain.VerificationToken;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;

/**
 * Created by jakub on 30.01.16.
 */
@Repository
public class VerificationTokenDAOImpl implements VerificationTokenDAO {
    @PersistenceContext
    EntityManager entityManager;

    private final static Logger logger = Logger.getLogger(VerificationTokenDAOImpl.class);

    @Override
    @Transactional
    public VerificationToken insertToken(VerificationToken token) {
        logger.info("Inserting token: " + token.toString());
        try {
            VerificationToken t = entityManager.merge(token);
            entityManager.flush();
            entityManager.detach(t);
            return t;
        } catch (PersistenceException e)
        {
            logger.error("Constraint violation:" + e.getMessage()+ "// Probably tried to insert token with same value as exist");
            throw new PersistenceException(e.getCause().toString());
        }
    }

    @Override
    public VerificationToken findByToken(String token) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<VerificationToken> q = cb.createQuery(VerificationToken.class);
        Root<VerificationToken> t = q.from(VerificationToken.class);
        q.select(t);
        q.where(
                cb.equal(t.get("token"), token)
        );

        TypedQuery<VerificationToken> typedQuery = entityManager.createQuery(q);
        try {
            return typedQuery.getSingleResult();
        } catch (NonUniqueResultException e)
        {
            logger.error("Result of token query non-unique. This mean serious violation of database structure. Had better call police.");
            throw new RuntimeException("DB error");
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    @Transactional
    public VerificationToken deleteByUserId(long id) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<VerificationToken> q = cb.createQuery(VerificationToken.class);
        Root<VerificationToken> t = q.from(VerificationToken.class);
        q.select(t);
        q.where(
                cb.equal(t.get("user"), id)
        );
        TypedQuery<VerificationToken> typedQuery = entityManager.createQuery(q);
        try{
            VerificationToken token = typedQuery.getSingleResult();
            entityManager.remove(token);
            entityManager.flush();
        } catch (NonUniqueResultException e){
            throw new RuntimeException("Db error");
        } catch (NoResultException e1){
            return null;
        } catch (Exception e){
            throw new RuntimeException("Something went wrong");
        }
        return null;

    }
}
