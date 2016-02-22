package com.jmssolutions.iot.domain;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name="user")
public class User {
	
	@Id
	@Column(name = "user_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	long ID;

	@Column(name="username")
	private String username;

	@Column(name="email")
	private String email;

	@JsonIgnore
	@Column(name="password")
	private String password;

	@Column(name = "enabled")
	private int enabled;

	@Column(name="first_name")
	private String firstName;

	@Column(name="last_name")
	private String lastName;

	@ManyToMany(cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
	@JoinTable(	name = "user_role",
				joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "user_id"),
				inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "role_id"))
	private Collection<Role> roles;

	@JsonIgnore
	@OneToMany(mappedBy = "owner")
	private Collection<Device> devices;


	public User(){	}

	public String getUsername() {	return username;}

	public void setUsername(String username) {	this.username = username;}

	public String getPassword() {return password;}

	public void setPassword(String password) {	this.password = password;}

	public int getEnabled() {	return enabled;	}

	public void setEnabled(int enabled) {this.enabled = enabled;}

	public String getFirstName() {	return firstName;}

	public void setFirstName(String firstName) {this.firstName = firstName;	}

	public String getLastName() {	return lastName;	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public long getID() { return ID;}

	public void setID(long ID) {this.ID = ID;}

	public Collection<Role> getRoles() {return roles;}

	public void setRoles(Collection<Role> roles) {this.roles = roles;}

	public Collection<Device> getDevices() {
		return devices;
	}

	public void setDevices(Collection<Device> devices) {
		this.devices = devices;
	}

	//	public VerificationToken getVerificationToken() {
//		return verificationToken;
//	}
//
//	public void setVerificationToken(VerificationToken verificationToken) {
//		this.verificationToken = verificationToken;
//	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		User user = (User) o;

		if (enabled != user.enabled) return false;
		if (!username.equals(user.username)) return false;
		if (!password.equals(user.password)) return false;
		if (firstName != null ? !firstName.equals(user.firstName) : user.firstName != null) return false;
		if (lastName != null ? !lastName.equals(user.lastName) : user.lastName != null) return false;
		return email.equals(user.email);

	}

	@Override
	public int hashCode() {
		int result = username.hashCode();
		result = 31 * result + password.hashCode();
		result = 31 * result + enabled;
		result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
		result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
		result = 31 * result + email.hashCode();
		return result;
	}

	@Override
	public String toString() {
		return "User{" +
				"username='" + username + '\'' +
				", password='" + password + '\'' +
				", enabled=" + enabled +
				", firstName='" + firstName + '\'' +
				", lastName='" + lastName + '\'' +
				", email='" + email + '\'' +
				'}';
	}
}
