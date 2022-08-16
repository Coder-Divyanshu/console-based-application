package com.tweetapp.service.impl;

import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tweetapp.exception.UserException;
import com.tweetapp.model.User;
import com.tweetapp.repository.UserRepository;
import com.tweetapp.service.TweetService;
import com.tweetapp.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	
	private static final String regex = "^(.+)@(.+)$";
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public void register(User user) throws UserException {
		//System.out.println(user.getEmail());
		boolean isValid = Pattern.compile(regex).matcher(user.getEmail()).matches();
		//System.out.println(isValid);
		if(isValid) {
			userRepository.save(user);
			System.out.println("Registration successful");
		}else {
			throw new UserException("Invalid Email format");
		}
		
	}

	@Override
	public User login(String email, String password) throws Exception {
		User user=userRepository.findByEmail(email);
		if(user==null) {
			throw new UserException("Invalid Email.");
		}
		else if(!user.getPassword().equals(password)) {
			throw new UserException("Invalid password");
		}
		else {
			user.setStatus(true);
			userRepository.save(user);
			System.out.println("Login Successful");
			return user;
		}
		
		
	}

	@Override
	public void viewAllUsers() {
		List<User> users= userRepository.findAll();
		System.out.println("------------------------");
		System.out.println("Below are all the users ");
		System.out.println("------------------------");
		for(User user: users) {
			System.out.println("Name:"+user.getFirstName());
			System.out.println("Gender:"+user.getGender());
			System.out.println("Email:"+user.getEmail());
		}
		
	}

	@Override
	public boolean reset(User user,boolean temp){
		Scanner in = new Scanner(System.in);
		System.out.println("---------------");
		System.out.println("Reset Password ");
		System.out.println("---------------");
		System.out.println("Please enter your current password:");
		String password=in.next();
		if(!user.getPassword().equals(password)) {
			System.out.println("Invalid password. Please try again");
			return temp;
		}
		else {
				System.out.println("Enter your new password:");
				String newPassword=in.next();
				user.setPassword(newPassword);
				userRepository.save(user);
				System.out.println("---------------------------");
				System.out.println("Password Reset successfully");
				System.out.println("---------------------------");
				temp=false;
				System.out.println("Please login again to continue");
				return temp;
			}
		}

	@Override
	public void forget() {
		Scanner in = new Scanner(System.in);
		System.out.println("---------------");
		System.out.println("Forget Password ");
		System.out.println("---------------");
		System.out.println("Please enter your email:");
		String email=in.next();
		User user=userRepository.findByEmail(email);
		if(user!=null) {
			System.out.println("Enter your new password:");
			String newPassword=in.next();
			user.setPassword(newPassword);
			userRepository.save(user);
			System.out.println("Password changed successfully");
		}	
		else {
			System.out.println("Invalid Email. Please try again.");
		}
		
	}

	@Override
	public void logout(User user) {
		user.setStatus(false);
		userRepository.save(user);
		
	}
}
