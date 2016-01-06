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

	public void deleteUser(long ID) {
		userDAO.deleteUser(ID);

	}

	public User getUserById(long ID) {
		return userDAO.getUserById(ID);
	}

	public List<User> getAllUsers() {
		return userDAO.getAllUsers();
	}


	public List<User> getUserByUserParams(User toFind) {
		return userDAO.findUser(toFind);
	}

}
