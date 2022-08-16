package com.tweetapp.controller;

import java.time.LocalDate;
import java.util.Date;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tweetapp.exception.UserException;
import com.tweetapp.model.User;
import com.tweetapp.service.TweetService;
import com.tweetapp.service.UserService;

@Component
public class MenuController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private TweetService tweetService;
	
	public void registerUser() {
		Scanner in = new Scanner(System.in);
		System.out.println("---------Register User------------");
		System.out.println("Enter your first name (Required):");
		String firstName = in.nextLine();
		System.out.println("Enter your last Name (Optional):");
		String lastName=in.nextLine();
		System.out.println("Enter your gender (M/F/T) - (Required):");
		String gender = in.nextLine();
		System.out.println("Enter your date of birth (dd-mm-yyyy) - (Optional):");
		String dateOfBirth = in.nextLine();
		System.out.println("Enter your email (Required):");
		String email=in.nextLine();
		System.out.println("Enter your password (Required):");
		String password=in.nextLine();
		User user = new User(firstName, lastName, gender, dateOfBirth, email, password, false);
		validate(user);
		try {
		userService.register(user);
		}catch(Exception e) {
			System.out.println("Registration unsuccessful");
		}
	}

	private void validate(User user) {
		if(user.getFirstName().isEmpty())
			System.out.println("First name cannot be empty");
		else if(user.getGender().isEmpty())
			System.out.println("Gender cannot be empty");
		else if(user.getEmail().isEmpty())
			System.out.println("Email cannot be empty");
		else if(user.getPassword().isEmpty())
			System.out.println("Password cannot be empty");	
	}

	public void start() throws Exception {
		boolean temp=true;
		// TODO Auto-generated method stub
		while(temp) {
		Scanner in = new Scanner(System.in);
		System.out.println("---------Tweet App----------- \n 1.Register \n 2.Login \n 3.Forget password \n 4.Exit");
		System.out.println("Enter your choice (1-4):");
		int userInput=in.nextInt();
		switch (userInput) {
		case 1: {
			registerUser();
			break;
		}
		case 2: {
			loginUser();
			break;
		}
		case 3:
			userService.forget();
			break;
		case 4: {
			temp = false;
		}
		}
	}
	}

	private void loginUser() throws Exception {
		Scanner in=new Scanner(System.in);
		System.out.println("------------LOGIN USER--------------");
		System.out.println("Enter your email");
		String email=in.next();
		System.out.println("Enter your password");
		String password=in.next();
		try {
			User user=userService.login(email,password);
			afterLogin(user);
		}catch(Exception e) {
			System.out.println("Invalid Email or Password");
		}
		
	}
	
	private void afterLogin(User user) throws Exception {
		boolean temp=true;
		while(temp) {
		System.out.println("Hi - "+user.getFirstName()+"\n 1.Post a tweet \n 2.View my tweets \n 3.View all tweets \n 4.View All Users \n 5.Reset Password \n 6.Logout");
		Scanner in = new Scanner(System.in);
		System.out.println("Enter your choice:");
		int loggedUserInput = in.nextInt();
		switch(loggedUserInput) {
		case 1:{
			tweetService.post(user);
			break;
		}
		case 2:{
			tweetService.viewAllTweets(user);
			break;
		}
		case 3:
			tweetService.viewAll();
			break;
		case 4:
			userService.viewAllUsers();
			break;
		case 5:
			temp = userService.reset(user, temp);
			break;
		case 6:
			userService.logout(user);
			temp=false;
	}
		}
	}
}
