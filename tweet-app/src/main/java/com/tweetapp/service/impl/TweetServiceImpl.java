package com.tweetapp.service.impl;

import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tweetapp.model.Tweet;
import com.tweetapp.model.User;
import com.tweetapp.repository.TweetRepository;
import com.tweetapp.repository.UserRepository;
import com.tweetapp.service.TweetService;

@Service
public class TweetServiceImpl implements TweetService{
	
	@Autowired
	private TweetRepository tweetRepository;
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public void post(User user) {
		Scanner in = new Scanner(System.in);
		System.out.println("------------");
		System.out.println("Post a Tweet");
		System.out.println("------------");
		System.out.println("Enter your message:");
		String message=in.nextLine();
		Tweet tweet = new Tweet();
		tweet.setUser(user);
		tweet.setMessage(message);
		tweetRepository.save(tweet);
	}

	@Override
	public void viewAllTweets(User user) {
		List<Tweet> tweets=tweetRepository.findByUserId(user.getId());
		System.out.println("-----------------------------------");
		System.out.println("Below are the tweets posted by you");
		System.out.println("-----------------------------------");
		for(Tweet tweet: tweets) {
			System.out.println("Tweet:  "+tweet.getMessage());
		}
	}

	@Override
	public void viewAll() {
		List<Tweet> tweets=tweetRepository.findAll();
		System.out.println("-----------------------------------");
		System.out.println("Below are the tweets posted by all");
		System.out.println("-----------------------------------");
		for(Tweet tweet: tweets) {
			System.out.println("Name:  "+tweet.getUser().getFirstName());
			System.out.println("Email:  "+tweet.getMessage());
			System.out.println("Tweet:  "+tweet.getUser().getEmail());
		}
		
	}

}
