package com.revature.controller;

import java.util.ArrayList;
import java.util.Scanner;

import com.revature.exception.InvalidAccountException;
import com.revature.repository.BankRepoJdbc;

public class ControllerTwo {

	static ArrayList<Accounts> list = new ArrayList<Accounts>();

	public static void main(String[] args) throws InvalidAccountException {
		Scanner read = new Scanner(System.in);
		System.out.println(" ");
		System.out.println("Would you like to login?");
		String LoginYesOrNo = read.nextLine().toLowerCase();
		if (LoginYesOrNo.startsWith("n")) {
			return;
		}
		String username = null;
		String password = null;
		username = nameCheck(read, args);
		password = passwordCheck(read, args);
		System.out.println("");
		System.out.println("ACCOUNT INFORMATION:");
		System.out.println(list);
		boolean loop = true;
		String choice = null;
		while (loop) {
			System.out.println("");
			System.out.println("What would you like to do?");
			System.out.println(" ");
			System.out.println("DEPOSIT, WITHDRAW, or EXIT");
			if (read.hasNext()) {
				choice = read.next().toLowerCase();
				if (choice.startsWith("d")) {
					deposit(read, username);

				} else if (choice.startsWith("w")) {
					withdraw(read, username);

				}
				if (choice.startsWith("e")) {
					loop = false;
					return;
				}
				choice = null;

			}

		}

	}

	public static String nameCheck(Scanner read, String[] args) throws InvalidAccountException {
		String username = null;
		while (username == null || username.isEmpty()) {
			System.out.println(" ");
			System.out.println("please enter username:");
			if (read.hasNextLine()) {
				username = read.nextLine();
			} else {
				System.out.println(" ");
				System.out.println("no username insterted");
			}
			try {
				BankRepoJdbc bank = new BankRepoJdbc();
				list.add(bank.findByName(username.toLowerCase()));
			} catch (InvalidAccountException e) {
				username = null;
				System.err.print(e);

			} finally {
				if (username == null) {
					ControllerTwo.main(args);
				}

			}

		}
		return username;
	}

	public static String passwordCheck(Scanner read, String[] args) throws InvalidAccountException {
		String password = null;
		while (password == null || password.isEmpty()) {
			System.out.println(" ");
			System.out.println("please enter password:");
			if (read.hasNextLine()) {
				password = read.nextLine();
			} else {
				System.out.println(" ");
				System.out.println("no password insterted");
			}
			String tempPass = list.get(0).getPass();
			if (!password.equals(tempPass)) {
				System.out.println(" ");
				System.out.println("Wrong Password!");
				ControllerTwo.main(args);
			} else {
				return password;
			}

		}
		return null;
	}

	public static void deposit(Scanner read, String username) {

		double amount = 0;
		int trys = 0;
		while (amount == 0 && trys < 3) {
			System.out.println(" ");
			System.out.println("how much would you like to depoist?");
			double temp = read.nextDouble();
			if (temp > 0) {
				amount = temp;
				Deposit.tranx(username, amount);
				System.out.println(" ");
				System.out.println("deposit complete!");
				System.out.println("ACCOUNT INFORMATION:");
				System.out.println(list);
			} else {
				System.out.println(" ");
				System.out.println("please input number higher than 0.");
				trys++;
			}
		}
	}

	public static void withdraw(Scanner read, String username) {
		double amount = 0;
		int trys = 0;
		while (amount == 0 && trys < 3) {
			System.out.println(" ");
			System.out.println("how much would you like to withdraw?");
			double temp = read.nextDouble();

			if (temp > 0) {
				amount = temp;
				if (amount > BankRepoJdbc.getBalance(username)) {
					System.out.println(" ");
					System.out.println("withdraw amount larger than balance. Insufficient funds! ");
					System.out.println(" ");
					amount = 0;
					trys++;
				} else {
					Withdraw.tranx(username, amount);
					System.out.println("Withdraw complete!");
					System.out.println("ACCOUNT INFORMATION:");
					System.out.println(list);
					
				}

			} else {
				System.out.println(" ");
				System.out.println("please input number higher than 0.");
				trys++;
			}
		}
	}

}
