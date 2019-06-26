package com.revature.exception;

public class InvalidWithdrawAmount extends Exception{

	public InvalidWithdrawAmount() {
		System.out.println("withdraw amount larger than balance. Insufficient funds!");
	}
}
