package com.tweetapp.console_based_app.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import com.tweetapp.console_based_app.entity.Tweet;
import com.tweetapp.console_based_app.entity.User;
import com.tweetapp.console_based_app.service.TweetService;
import com.tweetapp.console_based_app.service.UserService;

public class RegisterUtil {
	
	public String firstName;
	public String lastName;
	public String gender;
	public String dob;
	public String email;
	public String password;
	public int userId;
	public int tweetId;
	public String tweet;
	//Map<String,Integer> status = new HashMap<String,Integer>();
	Scanner scanner = new Scanner(System.in);
	UserService userService = new UserService();
	TweetService tweetService = new TweetService();
	
	//create user
	public Boolean registerUser() {
		User user = new User();
		
		System.out.println("enter your first name : ");
		firstName = scanner.nextLine();
		user.setFirstName(firstName);
		
		System.out.println("enter your last name : ");
		lastName = scanner.nextLine();
		user.setLastName(lastName);
		
		System.out.println("enter your gender : ");
		gender = scanner.nextLine();
		user.setGender(gender);
		
		System.out.println("enter your dob : ");
		dob = scanner.nextLine();
		user.setDob(dob);
		
		System.out.println("enter your email : ");
		email = scanner.nextLine();
		user.setEmail(email);
		
		System.out.println("enter your password : ");
		password = scanner.nextLine();
		user.setPassword(password);
		
		userService.registerUser(user);
		
		return true;
	}
	
	//login
	public Map<String,Integer> login(){
		System.out.println("enter your email : ");
		email = scanner.nextLine();
		
		System.out.println("enter your password : ");
		password = scanner.nextLine();
		
		return userService.login(email, password);
		
	}
	
	//reset password
	public Boolean updateUser(int userId) {
		String newPassword,oldPassword;
		System.out.println("enter your current password : ");
		oldPassword = scanner.nextLine();
		System.out.println("enter your new password : ");
		newPassword = scanner.nextLine();
		userService.updatePassword(userId,oldPassword, newPassword);
		return true;
	}
	
	//post a tweet
	public Boolean createTweet(int userId) {
		Tweet tweetObj = new Tweet();
		System.out.println("Please type your tweet : ");
		tweet = scanner.nextLine();
		tweetObj.setUserId(userId);
		tweetObj.setTweet(tweet);
		tweetService.createTweet(tweetObj);
		return true;
	}
	
	//get my tweets
	public boolean getMyTweets(int userId){
		return tweetService.getTweetsByUserId(userId);
	}
	
	//get all tweets
	public boolean getAllTweets(){
		return tweetService.getAllTweets();
	}
	
	//logout
	public boolean logout(int userId) {
		return true;
	}
	
	//forgot password
	public boolean forgotPassword() {
		System.out.println("enter your dob : ");
		dob = scanner.nextLine();
		
		System.out.println("enter your email : ");
		email = scanner.nextLine();
		
		return true;
	}
	
	//get all users
	public boolean getAllUsers() {
		userService.getAllUsers();
		return true;
	}
}