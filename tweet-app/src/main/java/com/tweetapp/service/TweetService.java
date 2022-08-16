package com.tweetapp.service;

import org.springframework.stereotype.Service;

import com.tweetapp.model.User;

@Service
public interface TweetService {
	public void post(User user);

	public void viewAllTweets(User user);

	public void viewAll();
}
