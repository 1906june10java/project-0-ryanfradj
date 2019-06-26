package com.revature.model;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.revature.controller.Deposit;
import com.revature.controller.Withdraw;
import com.revature.repository.BankRepoJdbc;

public class BankTest {

	@Before
	public void setUp() throws Exception {
		Deposit.tranx("john", 200);
	}

	@Test
	public void DepositTest() {

		double expected=BankRepoJdbc.getBalance("john")+200;
		System.out.println(expected);
		Deposit.tranx("john", 200);
		double result=BankRepoJdbc.getBalance("john");
		System.out.println(result);
		assertEquals(expected,result,0 );
	}
	@Test
	public void WithdrawTest() {
		double expected=BankRepoJdbc.getBalance("john")-200;
		System.out.println(expected);
		Withdraw.tranx("john", 200);
		double result=BankRepoJdbc.getBalance("john");
		System.out.println(result);
		assertEquals(expected,result,0 );
	}

}
