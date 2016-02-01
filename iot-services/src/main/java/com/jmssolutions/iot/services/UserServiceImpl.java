package com.jmssolutions.iot.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.jmssolutions.iot.dao.RoleDAO;
import com.jmssolutions.iot.dao.VerificationTokenDAO;
import com.jmssolutions.iot.domain.Role;
import com.jmssolutions.iot.domain.VerificationToken;
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

	@Autowired
	VerificationTokenDAO tokenDAO;

	private final static Logger logger = Logger.getLogger(UserServiceImpl.class);

	@Override
	public User createUser(User user) {
		User u = userDAO.getUserByUsername(user.getUsername());
		u = userDAO.getUserByEmail(user.getEmail());
		if(u != null)
		{
			logger.error("No user inserted. Constraint violated. ");
			throw new UserExistsException("User already exists");
		}

		user = userDAO.insertUser(user);

		Collection<Role> roles = new ArrayList<>(1);
		roles.add(roleDAO.getRoleByName("ROLE_USER"));
		this.addUserRoles(user.getID(), roles);
		return user;
	}

	@Override
	public void updateUser(User user) {
		userDAO.updateUser(user);
	}

	public void deleteUser(long id) {
		tokenDAO.deleteByUserId(id);
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
	public User getUserByEmail(String email) {
		return userDAO.getUserByEmail(email);
	}

	@Override
	public List<User> getAllUsers() {
		return userDAO.getAllUsers();
	}

	@Override
	public List<User> getUserByUserParams(User toFind) {
		return userDAO.findUser(toFind);
	}

	@Override
	public User getUserByVerificationToken(String token) {
		VerificationToken t = tokenDAO.findByToken(token);
		if(t != null){
			return t.getUser();
		}
		else return null;
	}

	@Override
	public void createVerificationToken(User user, String token) {
		VerificationToken t = new VerificationToken(token, user);
		tokenDAO.insertToken(t);
	}

	@Override
	public VerificationToken getVerificationToken(String verificationToken) {
		return tokenDAO.findByToken(verificationToken);
	}

	@Override
	public void deleteVerificationToken(VerificationToken token) {
		tokenDAO.deleteByUserId(token.getUser().getID());
	}

}
