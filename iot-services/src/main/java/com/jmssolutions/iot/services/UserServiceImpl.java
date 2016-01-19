package com.jmssolutions.iot.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.jmssolutions.iot.dao.RoleDAO;
import com.jmssolutions.iot.domain.Role;
import com.jmssolutions.iot.exceptions.UserExistsException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jmssolutions.iot.dao.UserDAO;
import com.jmssolutions.iot.domain.User;

import javax.persistence.EntityExistsException;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserDAO userDAO;

	@Autowired
	RoleDAO roleDAO;

	private final static Logger logger = Logger.getLogger(UserServiceImpl.class);

	@Override
	public User createUser(User user) {
		try {
			user = userDAO.insertUser(user);
		} catch (PersistenceException e) {
			logger.error("No user inserted. Constraint violated: " + e.getMessage());
			throw new UserExistsException("User already exists");
		} catch (Exception e){
			throw new RuntimeException("Internal error: "+e.getMessage());
		}

		Collection<Role> roles = new ArrayList<>(1);
		roles.add(roleDAO.getRoleByName("ROLE_USER"));
		this.addUserRoles(user.getID(), roles);
		return user;
	}

	@Override
	public User updateUser(User user) {
//		userDAO.updateUser(user);
		return null;
	}

	public void deleteUser(long id) {
		userDAO.deleteUser(id);

	}

	@Override
	public void addUserRoles(long userId, Collection<Role> roles) {
		userDAO.setUserRoles(userId, roles);

	}

	@Override
	public User getUserById(long id) {
		return userDAO.getUserById(id);
	}

	@Override
	public User getUserByUsername(String username) { return userDAO.getUserByUsername(username);}

	@Override
	public List<User> getAllUsers() {
		return userDAO.getAllUsers();
	}

	@Override
	public List<User> getUserByUserParams(User toFind) {
		return userDAO.findUser(toFind);
	}

}
