package com.tweetapp.console_based_app.dao;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.tweetapp.console_based_app.entity.User;

public class UserDao {
	List<User> userList = new ArrayList<User>();
	private Connection connect = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;
	
    public UserDao(){
    	try {
    Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
    connect = DriverManager.getConnection("jdbc:mysql://localhost/tweetapp?"
                    + "user=root&password=");
    String sqlCreate = "CREATE TABLE IF NOT EXISTS USER"
            + "   (id            INTEGER AUTO_INCREMENT PRIMARY KEY,"
            + "   firstname          VARCHAR(20),"
            + "   lastname           VARCHAR(20),"
            + "   gender           VARCHAR(20),"
            + "   dob     VARCHAR(15),"
            + "   email VARCHAR(30),"
            + "	  password VARCHAR(15),"
            + "	  status BOOLEAN)";

		    Statement stmt = connect.createStatement();
		    stmt.execute(sqlCreate);
    	}catch(Exception e) {
    		System.out.println("User database not created");
    	}
    }
    
	//register user
	public Boolean registerUser(User user) {
		//String query = "insert into user(firstname,lastname,gender,dob,email,password,status) values (?,?,?,?,?,?,?)";
		try {
			PreparedStatement pstmt = connect.prepareStatement("insert into user(firstname,lastname,gender,dob,email,password,status) values (?,?,?,?,?,?,?)");
			pstmt.setString(1, user.getFirstName());
			pstmt.setString(2, user.getLastName());
			pstmt.setString(3, user.getGender());
			pstmt.setString(4, user.getDob());
			pstmt.setString(5, user.getEmail());
			pstmt.setString(6, user.getPassword());
			pstmt.setBoolean(7, false);
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Something went wrong! User not created..");
		}
		return true;
	}
	
	//login 
	public Map<String,Integer> login(String email,  String password) {
		Map<String,Integer> res = new HashMap<>();
		try {
		 statement = connect.createStatement();
	     String sql = "SELECT id,email,password from user";
	     resultSet = statement.executeQuery(sql);
	     while(resultSet.next()) {
	    	 if(email.equals(resultSet.getString("email"))&&password.equals(resultSet.getString("password"))) {
	    		 res.put("userId", resultSet.getInt("id"));
	    		 res.put("status", 1);
	    		 preparedStatement = connect.prepareStatement("UPDATE user SET status = ? WHERE id = ?");
	    		 preparedStatement.setBoolean(1, true);
	    		 preparedStatement.setInt(2, resultSet.getInt("id"));
	    		 preparedStatement.executeUpdate();
	    		 return res;
	    	 }
	     }
		}catch(Exception e) {
			res.put("status", 1);
		}
		return res;
	}
	
	//update password
	public Boolean updatePassword(int userId, String newPassword) {
		userList.stream().filter(a->a.getUserId()==userId).collect(Collectors.toList())
		.get(0).password = newPassword;
		return true;
	}
	
	//retrieve all users
	public List<User> getAllUsers(){
		return userList;
	}
	
	//retrieve user by id
	public User getUserById(int userId) {
		return userList.stream().filter(a->a.getUserId()==userId).collect(Collectors.toList())
				.get(0);
	}
	
}
