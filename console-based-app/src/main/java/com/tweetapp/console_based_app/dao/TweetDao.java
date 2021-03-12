package com.tweetapp.console_based_app.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.tweetapp.console_based_app.entity.Tweet;

public class TweetDao {
	private Connection connect = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;
	public TweetDao(){
		try {
		    Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
		    connect = DriverManager.getConnection("jdbc:mysql://localhost/tweetapp?"
		                    + "user=root&password=");
		    String sqlCreate = "CREATE TABLE IF NOT EXISTS TWEET"
		            + "   (id  INTEGER AUTO_INCREMENT PRIMARY KEY,"
		            + "   userid INTEGER,"
		            + "   tweet VARCHAR(100))";
		            
				    Statement stmt = connect.createStatement();
				    stmt.execute(sqlCreate);
		    	}catch(Exception e) {
		    		System.out.println("User database not created");
		    	}
	}
	
	List<Tweet> tweetList = new ArrayList<Tweet>();
	
	public boolean createTweet(Tweet tweet) {
		try {
			preparedStatement = connect.prepareStatement("insert into tweet(userid,tweet) values(?,?)");
			preparedStatement.setInt(1, tweet.getUserId());
			preparedStatement.setString(2, tweet.getTweet());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Something went wrong! Tweet not uploaded..");
		}
		return true;
	}
	
	public List<Tweet> getAllTweets(){
		return tweetList;
	}
	
	public List<Tweet> getTweetsByUserId(int userId){
		return tweetList.stream().filter(a->a.getUserId()==userId).collect(Collectors.toList());
	}
}