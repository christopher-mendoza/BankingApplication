package dev.mendoza.app;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import dev.mendoza.models.Account;
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
																	System.out.println("I did not understand the input, skipping account.");
																}
															}
														}
														else {
															System.out.println("I did not understand the input, skipping account.");
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
										else {
											System.out.println("I did not understand the input, please try again.");
										}
									}
								}
								else {
									
								}
							}
						}
						else {
							System.out.println("Could not login.");
						}
						break;
					}
					
					// Register
					case "2": {
						User newUser = new User();
						System.out.println("**********REGISTER**********");
						System.out.println("Please enter your name or \ntype 'Q' to go back to the menu.");
						userInputStr = userScan.nextLine();
						// User goes back to menu
						if(userInputStr.equalsIgnoreCase("Q")) {
							break;
						}
						else {
							newUser.setName(userInputStr);
							System.out.println("Please enter your username or \ntype 'Q' to go back to the menu.");
							userInputStr = userScan.nextLine();
							if(userInputStr.equalsIgnoreCase("Q")) {
								break;
							}
							else {
								newUser.setUsername(userInputStr);
								System.out.println("Please enter your password or \ntype 'Q' to go back to the menu.");
								userInputStr = userScan.nextLine();
								if(userInputStr.equalsIgnoreCase("Q")) {
									break;
								}
								else {
									newUser.setPassword(userInputStr);
									System.out.println("Creating account:\n" +
														"Name: " + newUser.getName() +
														"\nUsername: " + newUser.getUsername() +
														"\nPassword: " + newUser.getPassword());
									uService.addUser(newUser);
								}
							}
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
			else {
				System.out.println("I did not understand the input, please try again.");
			}
		}
	}
}
