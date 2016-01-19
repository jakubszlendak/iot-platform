package com.jmssolutions.iot.services;

import java.util.Collection;
import java.util.List;
import com.jmssolutions.iot.domain.Role;
import com.jmssolutions.iot.domain.User;

import javax.persistence.EntityExistsException;

public interface UserService {
	User createUser(User user) throws RuntimeException;
	User updateUser(User user);
	void deleteUser(long id);
	void addUserRoles(long userId, Collection<Role> roles);
	User getUserById(long id);
	User getUserByUsername(String username);
	List<User> getAllUsers();
	List<User> getUserByUserParams(User toFind);
}
