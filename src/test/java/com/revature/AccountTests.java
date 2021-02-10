package com.revature;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import com.revature.beans.Account;
import com.revature.beans.Transaction;
import com.revature.dao.AccountDao;
import com.revature.exceptions.OverdraftException;
import com.revature.services.AccountService;

@RunWith(MockitoJUnitRunner.class)
public class AccountTests {
	
	@Mock
	AccountDao dao;
	
	@InjectMocks
	AccountService actSrv;
	
	@Before
	public void setup() {
		MockitoAnnotations.openMocks(this);
	}
	
	private Account getNewApprovedAccount() {
		Account a = new Account();
		a.setApproved(true);
		a.setBalance(AccountService.STARTING_BALANCE);
		return a;
	}
	
	@Test(expected=OverdraftException.class)
	@Points(2)
	public void testOverdraftPrevention() {
		Account testAct = getNewApprovedAccount();
		testAct.setBalance(10d);
		actSrv.withdraw(testAct, 15.01);
	}
	
	@Test
	@Points(1)
	public void testValidWithdraw() {
		Account testAct = getNewApprovedAccount();
		testAct.setBalance(18.34);
		actSrv.withdraw(testAct, 1.11);
		assertEquals(testAct.getBalance(), 17.23, 0.01);
	}
	
	@Test(expected=UnsupportedOperationException.class)
	public void testInvalidDeposit() {
		Account testAct = getNewApprovedAccount();
		testAct.setBalance(18.34);
		actSrv.deposit(testAct, -5d);
	}
	
	@Test
	public void testValidDeposit() {
		Account testAct = getNewApprovedAccount();
		testAct.setBalance(12.94);
		actSrv.deposit(testAct, 2.06);
		assertEquals(testAct.getBalance(), 15.00, 0.01);
	}
	
	@Test
	public void testValidTransfer() {
		Account testActOne = getNewApprovedAccount();
		testActOne.setBalance(100d);
		Account testActTwo = getNewApprovedAccount();
		testActTwo.setBalance(10d);
		actSrv.transfer(testActOne, testActTwo, 30d);
		assertEquals(testActOne.getBalance(), 70d, 0.01);
		assertEquals(testActTwo.getBalance(), 40d, 0.01);
	}
	
	@Test(expected=UnsupportedOperationException.class)
	public void testInvalidTransfer() {
		Account testActOne = getNewApprovedAccount();
		testActOne.setBalance(20d);
		Account testActTwo = getNewApprovedAccount();
		testActTwo.setBalance(50d);
		actSrv.transfer(testActOne, testActTwo, 30d);
	}
	
	@Test
	public void testCreateNewAccount() {
		Account act = actSrv.createNewAccount();
		assertEquals(act.getBalance(), AccountService.STARTING_BALANCE, 0.01);
		verify(dao).addAccount(act);
	}
	
	@Test(expected=UnsupportedOperationException.class)
	public void testPreventTransactionsBeforeApproval() {
		Account act = actSrv.createNewAccount();
		assertFalse(act.isApproved());
		actSrv.deposit(act, 100d);
	}
	
	@Test
	public void testApproveThenAllowTransactions() {
		Account act = actSrv.createNewAccount();
		act.setApproved(true);
		actSrv.deposit(act, 100d);
		actSrv.withdraw(act, 50d);
		Account act2 = actSrv.createNewAccount();
		act2.setApproved(true);
		actSrv.transfer(act, act2, 25d);
		assertEquals(act.getBalance(), AccountService.STARTING_BALANCE + 25d, 0.01);
		assertEquals(act2.getBalance(), AccountService.STARTING_BALANCE + 25d, 0.01);
		// first account should have been updated twice
		verify(dao, times(2)).updateAccount(act);
		// second account should have been updated once
		verify(dao, times(1)).updateAccount(act2);
	}
	
	@Test
	public void testTransactionsAdded() {
		Account act = actSrv.createNewAccount();
		Account act2 = actSrv.createNewAccount();
		act.setApproved(true);
		act2.setApproved(true);
		actSrv.deposit(act, 35d);
		assertEquals(act.getTransactions().size(), 1);
		assertEquals(act.getTransactions().get(0).getAmount(), 35d, 0.01);
		assertEquals(act.getTransactions().get(0).getType(), Transaction.TransactionType.DEPOSIT);
		actSrv.withdraw(act, 10d);
		assertEquals(act.getTransactions().size(), 2);
		assertEquals(act.getTransactions().get(1).getAmount(), 10d, 0.01);
		assertEquals(act.getTransactions().get(1).getType(), Transaction.TransactionType.WITHDRAWAL);
		actSrv.transfer(act, act2, 5);
		// first account should record a transfer of 5 and have balance of 20 + starting balance
		assertEquals(act.getTransactions().size(), 3);
		assertEquals(act.getTransactions().get(2).getAmount(), 5d, 0.01);
		assertEquals(act.getTransactions().get(2).getType(), Transaction.TransactionType.TRANSFER);
		assertEquals(act.getBalance(), AccountService.STARTING_BALANCE + 20d, 0.01);
		// second account should record a transfer of 5 as well and have a balance of 5 + starting balance
		assertEquals(act2.getTransactions().size(), 1);
		assertEquals(act2.getTransactions().get(0).getAmount(), 5d, 0.01);
		assertEquals(act2.getTransactions().get(0).getType(), Transaction.TransactionType.TRANSFER);
		assertEquals(act2.getBalance(), AccountService.STARTING_BALANCE + 5d, 0.01);
		// first account should have been updated twice
		verify(dao, times(2)).updateAccount(act);
		// second account should have been updated once
		verify(dao).updateAccount(act2);
	}
}
