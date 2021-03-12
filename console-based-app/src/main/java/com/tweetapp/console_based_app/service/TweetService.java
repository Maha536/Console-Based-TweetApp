package com.tweetapp.console_based_app.service;

import java.sql.ResultSet;
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
		ResultSet rs = tweetDao.getAllTweets();
		try {
			while(rs.next()) {
				System.out.println(rs.getInt("id")+" "+rs.getString("tweet"));
			}
		}catch(Exception e) {
			
		}
		return true;
	}
	
	public boolean getTweetsByUserId(int userId){
		ResultSet rs = tweetDao.getTweetsByUserId(userId);
		try {
			while(rs.next()) {
				System.out.println(rs.getInt("id")+" "+rs.getString("tweet"));
			}
		}catch(Exception e) {
			
		}
		return true;
	}
}
