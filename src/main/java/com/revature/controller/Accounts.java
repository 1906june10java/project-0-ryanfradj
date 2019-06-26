package com.revature.controller;

import java.util.HashSet;
import java.util.Set;

import com.revature.model.AccountsInterface;

public class Accounts implements AccountsInterface {

	
	private String name;
	private String pass;
	private double balance;
	private long id;
	Set<Object> listAccounts= new HashSet<>();
	
	public Accounts() {};
	
	public Accounts(String name, String pass) {
		super();
		this.name = name;
		this.pass = pass;
	}
	
	public Accounts(String name, String pass,long idnum,double bal) {
		super();
		this.name = name;
		this.pass = pass;
		this.id=idnum;
		this.balance=bal;
	}
	public Accounts(String name, String pass,long idnum) {
		super();
		this.name = name;
		this.pass = pass;
		this.id=idnum;
	}
	@Override
	public void insertName(String name) {
		this.name=name;
		
	}
	@Override
	public void insertPassword(String pass) {
		this.pass=pass;
		
	}
	@Override
	public double getbalance() {
		return this.balance;
	}
	
	
	@Override
	public void withdraw(double with) {
		this.balance=balance-with;
		
	}
	@Override
	public void deposit(double dep) {
		this.balance=balance-dep;
		
	}
	public String getName() {
		return this.name;
	}
	
	
	public String getPass() {
		return pass;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	@Override
	final public String viewBalance() {
		
		return "The balance is: "+ balance;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((pass == null) ? 0 : pass.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Accounts other = (Accounts) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (pass == null) {
			if (other.pass != null)
				return false;
		} else if (!pass.equals(other.pass))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Accounts [name=" + name + ", pass=" + pass + ", balance=" + balance + "]";
	}

	
//	@Override
//	public String viewAccount() {
//		
//		return name+" "+;
//	}
	
	


}
