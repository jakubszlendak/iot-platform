package com.jmssolutions.iot.services;

import java.util.List;

import com.jmssolutions.iot.domain.User;

public interface UserService {
	void insertUser(User user);
	void deleteUser(long ID);
	User getUserById(long ID);
	List<User> getAllUsers();
//	User getUserByUsername(String username);
	List<User> getUserByUserParams(User toFind);
}
