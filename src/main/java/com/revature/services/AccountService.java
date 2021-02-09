package com.revature.services;

import com.revature.beans.Account;
import com.revature.dao.AccountDao;

/**
 * This class should contain the business logic for performing operations on Accounts
 */
public class AccountService {
	
	public AccountDao actDao;
	public static final double STARTING_BALANCE = 25d;
	
	public AccountService(AccountDao dao) {
		this.actDao = dao;
	}
	
	public void withdraw(Account a, Double amount) {
		
	}
	
	public void deposit(Account a, Double amount) {
		if (!a.isApproved()) {
			throw new UnsupportedOperationException();
		}
	}
	
	public void transfer(Account fromAct, Account toAct, double amount) {
		
	}
	
	public Account createNewAccount() {
		return null;
	}
}
