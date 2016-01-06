package com.jmssolutions.iot.dao;

import java.util.List;
import com.jmssolutions.iot.domain.User;


public interface UserDAO {
	
	public User getUserById(long id);
	public void insertUser(User user);
	public void deleteUser(long id);
	public List<User> getAllUsers();
	List<User> findUser(User user);

}
