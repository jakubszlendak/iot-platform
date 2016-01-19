package com.jmssolutions.iot.webapp.DTO;


import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.jmssolutions.iot.webapp.validation.PasswordMatches;
import com.jmssolutions.iot.webapp.validation.ValidEmail;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

@PasswordMatches
public class UserDTO {
	
	
	@NotBlank
	private String username;
	
	private String firstName;
	private String lastName;

	@ValidEmail
	@NotNull
	@NotBlank
	private String email;
	
	@NotBlank
	@Size(min=5)
	private String password;
	private String matchingPassword;
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMatchingPassword() {
		return matchingPassword;
	}

	public void setMatchingPassword(String matchingPassword) {
		this.matchingPassword = matchingPassword;
	}
	
}
