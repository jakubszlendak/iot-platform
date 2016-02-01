package com.jmssolutions.iot.services;

import java.util.Collection;
import java.util.List;
import com.jmssolutions.iot.domain.Role;
import com.jmssolutions.iot.domain.User;
import com.jmssolutions.iot.domain.VerificationToken;

import javax.persistence.EntityExistsException;

public interface UserService {
	User createUser(User user) throws RuntimeException;
	void updateUser(User user);
	void deleteUser(long id);
	void addUserRoles(long userId, Collection<Role> roles);
	User getUserById(long id);
	User getUserByUsername(String username);
	User getUserByEmail(String email);
	List<User> getAllUsers();
	List<User> getUserByUserParams(User toFind);
	User getUserByVerificationToken(String token);
	void createVerificationToken(User user, String token);
	VerificationToken getVerificationToken(String verificationToken);
	void deleteVerificationToken(VerificationToken token);
}
