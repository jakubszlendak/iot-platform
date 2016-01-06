package com.jmssolutions.iot.dao;

import java.util.List;
import org.apache.log4j.Logger;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;
import javax.sql.DataSource;

import com.jmssolutions.iot.domain.User;

import org.hibernate.Criteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class UserDAOImpl implements UserDAO {

//	@Autowired
//	DataSource dataSource;
//
	private final static Logger logger = Logger.getLogger(UserDAOImpl.class);

	@PersistenceContext
	EntityManager entityManager;

	@Transactional
	public void insertUser(User user) {
//		entityManager.getTransaction().begin();
		logger.info("Inserting user: " + user.toString());
		user = entityManager.merge(user);
		entityManager.flush();
//		entityManager.getTransaction().commit();
//		entityManager.detach(user);
		// TODO Auto-generated method stub

	}

	@Transactional
	public void deleteUser(String username) {
		Query query = entityManager.createQuery("delete User where username = :un");
		query.setParameter("un", username);
		int result = query.executeUpdate();

	}

	@SuppressWarnings("unchecked")
	@Transactional
	public List<User> getAllUsers() {
		String hql = "from User";
		Query query = entityManager.createQuery(hql);
		return query.getResultList();
	}


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

	public User getUserByUsername(String username) {
		return entityManager.find(User.class, username);
//
//		String hql = "from User where ID = :user_id";
//		Query query = entityManager.createQuery(hql);
//		query.setParameter("user_id", id);
//		User retval = null;
//		try{
//			retval = (User) query.getSingleResult();
//		} catch (NoResultException e) {
//			return null;
//		}
//		return retval;
	}

}
