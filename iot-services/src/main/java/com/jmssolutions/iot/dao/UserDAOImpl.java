package com.jmssolutions.iot.dao;

import java.util.List;
import org.apache.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;
import javax.sql.DataSource;

import com.jmssolutions.iot.domain.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class UserDAOImpl implements UserDAO {
	
	@Autowired
	DataSource dataSource;
	
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
	public void deleteUser(long id) {
		Query query = entityManager.createQuery("delete User where ID = :id");
		query.setParameter("id", id);
		int result = query.executeUpdate();

	}

	@SuppressWarnings("unchecked")
	@Transactional
	public List<User> getAllUsers() {
		String hql = "from User";
		Query query = entityManager.createQuery(hql);
		return query.getResultList();
	}

	public User getUserById(long id) {
		return entityManager.find(User.class, id);
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
