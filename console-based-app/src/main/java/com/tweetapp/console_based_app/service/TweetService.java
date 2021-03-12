package com.tweetapp.console_based_app.service;

import java.util.List;


import com.tweetapp.console_based_app.dao.TweetDao;
import com.tweetapp.console_based_app.entity.Tweet;

public class TweetService {
	
	TweetDao tweetDao = new TweetDao();
	
	public boolean createTweet(Tweet tweet) {
		tweetDao.createTweet(tweet);
		return true;
	}
	
	public boolean getAllTweets(){
		tweetDao.getAllTweets().stream().forEach(t->System.out.println(t.tweet));
		return true;
	}
	
	public boolean getTweetsByUserId(int userId){
		tweetDao.getTweetsByUserId(userId).stream().forEach(t->System.out.println(t.tweet));
		return true;
	}
}
