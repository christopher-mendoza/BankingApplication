package dev.mendoza.app;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import dev.mendoza.models.Account;
import dev.mendoza.models.Transaction;
import dev.mendoza.models.User;
import dev.mendoza.services.AccountServiceImpl;
import dev.mendoza.services.TransactionServiceImpl;
import dev.mendoza.services.UserServiceImpl;

public class Driver {
	
	public static void customerOptions() {
		System.out.println("**********USER MENU*********");
		System.out.println("(1) Apply For New Account\n" +
							"(2) View Balance\n" +
							"(3) Deposit\n" +
							"(4) Withdraw\n" + 
							"(5) Transfer Funds\n" + 
							"(6) Logout");
	}
	
	public static void adminOptions() {
		System.out.println("*********ADMIN MENU********");
		System.out.println("(1) Approve/Reject Accounts\n" +
							"(2) View Customer Accounts\n" +
							"(3) View All Transactions\n" +
							"(4) Logout");
	}
	
	public static void loginOptions() {
		System.out.println("************MENU***********");
		System.out.println("(1) Login\n(2) Register\n(3) Quit");
	}
	
	public static void userInputError(String userInput) {
		System.out.println("You entered '" + userInput + "', which was not understood. \n"
							+ "Please try again.");
	}
	
	public static void userInputError(int userInput) {
		System.out.println("You entered '" + userInput + "', which was not understood. \n"
							+ "Please try again.");
	}
	
	public static void main(String[] args) {
		//Initial setup
		boolean instance = true;
		String userInputStr = "";
		Scanner userScan = new Scanner(System.in);
		
		// Service inits
		UserServiceImpl uService = new UserServiceImpl();
		AccountServiceImpl aService = new AccountServiceImpl();
		TransactionServiceImpl tService = new TransactionServiceImpl();
		
		
		System.out.println("Hello! Welcome to the Mendoza Bank!");
		while(instance) {
			loginOptions();
			userInputStr = userScan.nextLine();
			if(userInputStr.matches("\\d")) {
				switch(userInputStr) {
					// Login
					case "1": {
						System.out.println("************LOGIN***********");
						User login = new User();
						System.out.println("Please enter your username:");
						userInputStr = userScan.nextLine();
						login.setUsername(userInputStr);
						System.out.println("Please enter your password:");
						userInputStr = userScan.nextLine();
						login.setPassword(userInputStr);
						System.out.println("Logging in with\nUsername: " + login.getUsername() +
											"\nPassword: " + login.getPassword());
						// Check if username exists in table
						if(uService.getUser(login.getUsername()) != null) {
							// Check password is tied to username
							if(uService.getUser(login.getUsername()).getPassword().equals(login.getPassword())) {
								boolean loggedIn = true;
								// Check admin status of user
								if(uService.getUser(login.getUsername()).getAdmin() == true) {
									// ADMIN
									while(loggedIn) {
										adminOptions();
										userInputStr = userScan.nextLine();
										if(userInputStr.matches("\\d")) {
											switch(userInputStr) {
												// Admin Approve/Reject Accounts
												case "1": {
													System.out.println("Approve/Reject Accounts");
													List<Account> unapproved = new ArrayList<Account>();
													unapproved = aService.getAllUnapprovedAccounts();
													for(Account a : unapproved) {
														System.out.println("Would you like to\n" +
																			"(1) Approve\n" +
																			"(2) Reject\n" +
																			"(Other) Skip\n" +
																			a);
														userInputStr = userScan.nextLine();
														if(userInputStr.matches("\\d")) {
															switch(userInputStr) {
																// Approve Account
																case "1": {
																	System.out.println("Account Approved!");
																	aService.approve(a);
																	break;
																}
																
																// Deny Account
																case "2": {
																	System.out.println("Account Denied.");
																	aService.deny(a);
																	break;
																}
																
																// Bad Admin Input
																default : {
																	System.out.println("Skipping account.");
																}
															}
														}
														// Input was not a number
														else {
															System.out.println("Skipping account.");
														}
													}
													break;
												}
												
												// Admin View Customer Accounts
												case "2": {
													System.out.println("View Customer Accounts");
													System.out.println("Please enter the Customer's username: ");
													userInputStr = userScan.nextLine();
													User view = new User();
													view.setUsername(userInputStr);
													List<Account> viewAcc = aService.getAllAccountsByUser(view);
													if(viewAcc == null) {
														break;
													}
													else {
														for(Account a : viewAcc) {
															System.out.println(a);
														}
													}
													break;
												}
												
												// Admin View All Transactions
												case "3": {
													System.out.println("View All Transactions");
													List<Transaction> allTrans = tService.getAllTransactions();
													for(Transaction t : allTrans) {
														System.out.println(t);
													}
													break;
												}
					
												// Admin Logout
												case "4": {
													System.out.println("Logout");
													loggedIn = false;
													break;
												}
												
												// Admin Input Error
												default: {
													userInputError(userInputStr);
													break;
												}
											}
										}
										
										// Input was not a number
										else {
											System.out.println("I did not understand the input, please try again.");
										}
									}
								}
								
								else {
									// CUSTOMER
									while(loggedIn) {
										customerOptions();
										userInputStr = userScan.nextLine();
										if(userInputStr.matches("\\d")) {
											switch(userInputStr) {
												// Customer Apply for Account
												case "1": {
													System.out.println("Account Application");
													System.out.println("Please enter an account number (> 0):");
													userInputStr = userScan.nextLine();
													try {
														int accNum = Integer.parseInt(userInputStr);
														if(accNum < 0 || aService.getAccount(accNum) != null) {
															System.out.println("Account number invalid, please try again.");
															break;
														}
														Account reg = new Account();
														reg.setUsername(login.getUsername());
														reg.setAccNum(accNum);
														System.out.println("Please enter a starting balance (>= 0):");
														userInputStr = userScan.nextLine();
														try {
															float startBal = Float.parseFloat(userInputStr);
															if(startBal < 0) {
																System.out.println("Starting balance invalid, please try again.");
																break;
															}
															reg.setBalance(startBal);
															System.out.println("Creating Account:\n" +
																				"Username: " + reg.getUsername() +
																				"\nAccount Number: " + reg.getAccNum() + 
																				"\nStarting Balance: " + reg.getBalance() +
																				"\nAccept? (Y)");
															userInputStr = userScan.nextLine();
															if(userInputStr.equalsIgnoreCase("Y")) {
																aService.addAccount(reg);
																System.out.println("Account pending approval!");
															}
															else {
																System.out.println("Account creation aborted.");
															}
														}
														catch (NumberFormatException e) {
															System.out.println("'" + userInputStr + "' is not a valid starting balance. Please try again.");
														}
													}
													catch (NumberFormatException e) {
														System.out.println("'" + userInputStr + "' is not a valid account number. Please try again.");
													}
													break;
												}
												
												// Customer View Balance
												case "2": {
													System.out.println("View Account Balance");
													System.out.println("Please enter your account number:");
													userInputStr = userScan.nextLine();
													try {
														int accNum = Integer.parseInt(userInputStr);
														if(aService.getAccount(accNum) == null) {
															System.out.println("Could not find account '" + accNum + "'. Please try again.");
															break;
														}
														// Check if account exists and if it belongs to logged in user
														else if(aService.getAccount(accNum) != null &&
																aService.getAccount(accNum).getUsername().equals(login.getUsername())) {
															System.out.println("Balance for Account '" + accNum +"': " + aService.getAccount(accNum).getBalance());
														}
														else {
															System.out.println("Could not find account '" + accNum + "'. Please try again.");
														}
													}
													catch (NumberFormatException e) {
														System.out.println("'" + userInputStr + "' is not a valid account number. Please try again.");
													}
													break;
												}
												
												// Customer Deposit
												case "3": {
													System.out.println("Account Deposit");
													System.out.println("Please enter your account number:");
													userInputStr = userScan.nextLine();
													try {
														int accNum = Integer.parseInt(userInputStr);
														if(aService.getAccount(accNum) == null) {
															System.out.println("Could not find account '" + accNum + "'. Please try again.");
															break;
														}
														// Check if account exists and if it belongs to logged in user
														else if(aService.getAccount(accNum) != null &&
																aService.getAccount(accNum).getUsername().equals(login.getUsername())) {
															System.out.println("Balance for Account '" + accNum +"': " + aService.getAccount(accNum).getBalance());
															System.out.println("How much would you like to deposit (> 0):");
															userInputStr = userScan.nextLine();
															try {
																float deposit = Float.parseFloat(userInputStr);
																if(deposit < 0) {
																	System.out.println("Invalid deposit amount, please try again.");
																	break;
																}
																Account change = aService.getAccount(accNum);
																System.out.println("Depositing $" + deposit +
																					"\nAccept? (Y)");
																userInputStr = userScan.nextLine();
																if(userInputStr.equalsIgnoreCase("Y")) {
																	aService.deposit(change, deposit);
																	System.out.println("Deposited $" + deposit);
																}
																else {
																	System.out.println("Deposit aborted.");
																}
															}
															catch (NumberFormatException e) {
																System.out.println("'" + userInputStr + "' is not a valid deposit amount. Please try again.");
															}
														}
														else {
															System.out.println("Could not find account '" + accNum + "'. Please try again.");
														}
													}
													catch (NumberFormatException e) {
														System.out.println("'" + userInputStr + "' is not a valid account number. Please try again.");
													}
													break;
												}
												
												// Customer Withdraw
												case "4": {
													System.out.println("Account Withdraw");
													
													break;
												}
												
												// Customer Transfer Funds
												case "5": {
													System.out.println("Account Fund Transfer");
													break;
												}
												
												// Customer Logout
												case "6": {
													System.out.println("Logout");
													loggedIn = false;
													break;
												}
												
												// Customer Input Error
												default: {
													userInputError(userInputStr);
													break;
												}
											}
										}
										
										// Input was not a number
										else {
											System.out.println("I did not understand the input, please try again.");
										}
									}
								}
							}
						}
						
						// Username does not exist
						else {
							System.out.println("Could not login.");
						}
						break;
					}
					
					// Register
					case "2": {
						User newUser = new User();
						System.out.println("**********REGISTER**********");
						System.out.println("Please enter a name:");
						userInputStr = userScan.nextLine();
						newUser.setName(userInputStr);
						System.out.println("Please enter a username:");
						userInputStr = userScan.nextLine();
						newUser.setUsername(userInputStr);
						System.out.println("Please enter a password:");
						userInputStr = userScan.nextLine();
						newUser.setPassword(userInputStr);
						System.out.println("Creating User:\n" +
											"Name: " + newUser.getName() +
											"\nUsername: " + newUser.getUsername() +
											"\nPassword: " + newUser.getPassword() +
											"\nAccept? (Y)");
						userInputStr = userScan.nextLine();
						if(userInputStr.equalsIgnoreCase("Y")) {
							uService.addUser(newUser);
							System.out.println("User created!");
						}
						else {
							System.out.println("Did not understand input. Please try again.");
						}
						break;
					}
					
					// Quit
					case "3": {
						System.out.println("************QUIT***********");
						System.out.println("Thank you for using the Mendoza Banking App!");
						instance = false;
						break;
					}
					
					// User Input Error
					default: {
						userInputError(userInputStr);
						break;
					}
				}
			}
			
			// Input was not a number
			else {
				System.out.println("I did not understand the input, please try again.");
			}
		}
	}
}
