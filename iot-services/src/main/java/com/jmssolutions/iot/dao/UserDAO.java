package com.jmssolutions.iot.dao;

import java.util.Collection;
import java.util.List;

import com.jmssolutions.iot.domain.Role;
import com.jmssolutions.iot.domain.User;


public interface UserDAO {

	User getUserById(long id);
	void setUserRoles(long userID, Collection<Role> roles);
	User insertUser(User user);
	void deleteUser(long id);
	List<User> getAllUsers();
	List<User> findUser(User user);
	User getUserByUsername(String username);

}
