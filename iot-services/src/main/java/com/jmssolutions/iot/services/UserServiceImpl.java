package com.jmssolutions.iot.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jmssolutions.iot.dao.UserDAO;
import com.jmssolutions.iot.domain.User;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserDAO userDAO;
	
	public void insertUser(User user) {
		userDAO.insertUser(user);

	}

	public void deleteUser(User user) {
		userDAO.deleteUser(user.getID());

	}

	public List<User> getAllUsers() {
		return userDAO.getAllUsers();
	}

}
