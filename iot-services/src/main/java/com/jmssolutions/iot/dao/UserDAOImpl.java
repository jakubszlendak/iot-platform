package com.jmssolutions.iot.dao;

import java.util.Collection;
import java.util.List;

import com.jmssolutions.iot.domain.Role;
import com.sun.javaws.exceptions.InvalidArgumentException;
import org.apache.log4j.Logger;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;
import javax.sql.DataSource;

import com.jmssolutions.iot.domain.User;

import org.hibernate.Criteria;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class UserDAOImpl implements UserDAO {

	@Autowired
//	DataSource dataSource;
//
	private final static Logger logger = Logger.getLogger(UserDAOImpl.class);

	@PersistenceContext
	EntityManager entityManager;

	@Override
	public User getUserById(long id) {
		return entityManager.find(User.class, id);
	}

	@Override
	@Transactional
	public void setUserRoles(long userID, Collection<Role> roles) {
		User u = entityManager.find(User.class, userID);
		if(u != null) {
			u.setRoles(roles);
			entityManager.flush();
		}
		else throw new EntityNotFoundException();

	}

	@Transactional
	public User insertUser(User user) {
		logger.info("Inserting user: " + user.toString());
		try {
		user = entityManager.merge(user);
		entityManager.flush();
		entityManager.detach(user);
		return user;
		} catch (PersistenceException e)
		{
			logger.error("Constraint violation:" + e.getMessage()+ "// Probably tried to insert user with same name as exist");
			throw new PersistenceException(e.getCause().toString());
		}
	}

	@Transactional
	public void deleteUser(long id) {
		User u = entityManager.find(User.class, id);
		entityManager.remove(u);
		entityManager.flush();

	}

	@SuppressWarnings("unchecked")
	@Transactional
	public List<User> getAllUsers() {
		String hql = "from User";
		Query query = entityManager.createQuery(hql);
		return query.getResultList();
	}


	@Transactional
	@Override
	public List<User> findUser(User user) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<User> q = cb.createQuery(User.class);
		Root<User> u = q.from(User.class);
		q.select(u);
		ParameterExpression<String> uName = cb.parameter(String.class);
		ParameterExpression<String> uEmail = cb.parameter(String.class);
		q.where(
				cb.or(
						cb.like(u.get("username"), user.getUsername()),
						cb.like(u.get("email"), user.getEmail()))
		);

		TypedQuery<User> typedQuery = entityManager.createQuery(q);
		return typedQuery.getResultList();


	}

	@Override
	@Transactional
	public User getUserByUsername(String username) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<User> q = cb.createQuery(User.class);
		Root<User> u = q.from(User.class);
		q.select(u);
		ParameterExpression<String> uName = cb.parameter(String.class);
		q.where(
				cb.equal(u.get("username"), username)
		);

		TypedQuery<User> typedQuery = entityManager.createQuery(q);
		try {
			return typedQuery.getSingleResult();
		} catch (NonUniqueResultException e)
		{
			logger.error("Result of username query non-unique. This mean serious violation of database structure. Had better call police.");
			throw new RuntimeException("DB error");
		} catch (NoResultException e) {
			return null;
		}
	}

}
