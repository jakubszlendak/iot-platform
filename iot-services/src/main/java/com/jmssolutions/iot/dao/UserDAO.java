package com.jmssolutions.iot.dao;

import java.util.List;
import com.jmssolutions.iot.domain.User;


public interface UserDAO {
	
	public User getUserByUsername(String username);
	public void insertUser(User user);
	public void deleteUser(String username);
	public List<User> getAllUsers();
	List<User> findUser(User user);

}
