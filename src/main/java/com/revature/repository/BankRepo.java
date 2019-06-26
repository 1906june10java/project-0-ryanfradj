package com.revature.repository;

import java.util.List;

import com.revature.controller.Accounts;
import com.revature.exception.InvalidAccountException;

public interface BankRepo {
	
	public boolean create(Accounts acct);
	public Accounts findByName(String name) throws InvalidAccountException;
	public List<Accounts> findAll();

}
