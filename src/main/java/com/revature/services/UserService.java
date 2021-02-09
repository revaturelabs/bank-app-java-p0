package com.revature.services;

import com.revature.beans.User;
import com.revature.dao.AccountDao;
import com.revature.dao.UserDao;

/**
 * This class should contain the business logic for performing operations on users
 */
public class UserService {
	
	UserDao userDao;
	AccountDao accountDao;
	
	public UserService(UserDao udao, AccountDao adao) {
		this.userDao = udao;
		this.accountDao = adao;
	}
	
	public User login(String username, String password) {
		return null;
	}
	
	public void register(User newUser) {
		
	}
}
