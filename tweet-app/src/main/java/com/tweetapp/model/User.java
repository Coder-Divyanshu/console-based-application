package com.tweetapp.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="users")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String firstName;
	private String lastName;
	private String gender;
	private String dateOfbirth;
	private String email;
	private String password;
	private boolean status;
	@OneToMany(mappedBy = "user")
	private List<Tweet> tweets;
	
	public User(String firstName, String lastName, String gender, String dateOfbirth, String email, String password,
			boolean status) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.dateOfbirth = dateOfbirth;
		this.email = email;
		this.password = password;
		this.status = status;
	}
	
	
}
