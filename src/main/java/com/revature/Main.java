package com.revature;

import com.revature.controller.ControllerTwo;
import com.revature.exception.InvalidAccountException;
import com.revature.exception.InvalidWithdrawAmount;

/** 
 * Create an instance of your controller and launch your application.
 * 
 * Try not to have any logic at all on this class.
 */
public class Main {

	public static void main(String[] args) throws InvalidAccountException, InvalidWithdrawAmount {
		ControllerTwo.main(args);

	}
}
