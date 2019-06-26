package com.revature.model;

public interface AccountsInterface{
	
	void insertName(String name);
	void insertPassword(String pass);
	double getbalance();
	void withdraw(double with);
	void deposit(double dep);
	String viewBalance();
	//String viewAccount();
}
