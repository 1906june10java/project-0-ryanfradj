package com.revature.controller;

import java.util.Scanner;

import com.revature.repository.BankRepoJdbc;

public class Deposit {

	

	public static String tranx(String name,double deposit) {
		
		double temp=BankRepoJdbc.getBalance(name);
		temp= temp+deposit;
		BankRepoJdbc.UpdateBalance(name, temp);
		return "deposit complete!";
		
	}

	

}
