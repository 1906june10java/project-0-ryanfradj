package com.revature.controller;

import java.util.ArrayList;
import java.util.List;

import com.revature.repository.BankRepoJdbc;

public class Register {

	public static void register(Accounts account) {
		BankRepoJdbc repo=new BankRepoJdbc();
		repo.create(account);
	}
	
}
