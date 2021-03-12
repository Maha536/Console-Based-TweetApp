package com.tweetapp.console_based_app.service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.tweetapp.console_based_app.dao.UserDao;
import com.tweetapp.console_based_app.entity.User;

public class UserService {
	

	UserDao userDao = new UserDao();
	
	public Boolean registerUser(User user) {
		boolean regStatus = userDao.registerUser(user);
		if(regStatus == true) {
			return regStatus;
		}
		return false;
	}
	
	//login
	public Map<String,Integer> login(String email, String password){
		return userDao.login(email, password);
	}
	
	public Boolean updatePassword(int userId, String oldPassword, String newPassword) {
		String pass = userDao.getUserById(userId).password;
		if(oldPassword.equals(pass)) {
			userDao.updatePassword(userId, newPassword);
			return true;
		}
		return false;
	}
	
	public boolean getAllUsers(){
		userDao.getAllUsers().stream().forEach(user->System.out.println(user.firstName+" "+user.lastName));
		return true;
	}
}