package com.jmssolutions.iot.services;

import java.util.List;

import com.jmssolutions.iot.domain.User;

public interface UserService {
	void insertUser(User user);
	void deleteUser(User user);
	List<User> getAllUsers();
}
