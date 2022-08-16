package com.tweetapp.service;

import org.springframework.stereotype.Service;

import com.tweetapp.exception.UserException;
import com.tweetapp.model.User;

@Service
public interface UserService {
	
	public void register(User user) throws UserException;
	public User login(String email,String password) throws Exception;
	public void viewAllUsers();
	public boolean reset(User user,boolean temp);
	public void forget();
	public void logout(User user);

}
