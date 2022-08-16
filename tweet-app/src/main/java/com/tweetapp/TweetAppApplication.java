package com.tweetapp;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ApplicationContext;

import com.tweetapp.controller.MenuController;


@SpringBootApplication
public class TweetAppApplication implements CommandLineRunner{
	
	@Autowired
	private MenuController menuController;
	
	public static void main(String[] args) {
		SpringApplication.run(TweetAppApplication.class, args);
	}
	
	@Override
    public void run(String... arg0) throws Exception {
		menuController.start();
	}
	
	

}
