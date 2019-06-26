package com.revature.controller;

import java.util.Scanner;

import com.revature.repository.BankRepoJdbc;

public class Withdraw {

	public static String tranx(String name, double withdraw) {
		
		double temp=BankRepoJdbc.getBalance(name);
		temp= temp-withdraw;
		BankRepoJdbc.UpdateBalance(name, temp);
		return "withdraw complete!";

	}

}
