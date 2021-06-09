package dev.mendoza.app;

import java.util.Scanner;

import dev.mendoza.models.User;
import dev.mendoza.services.UserServiceImpl;

public class Driver {
	
	public static void customerOptions() {
		
	}
	
	public static void adminOptions() {
		
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
		int userInputInt = 0;
		float userInputFloat = 0.0f;
		Scanner userScan = new Scanner(System.in);
		UserServiceImpl uService = new UserServiceImpl();
		
		
		System.out.println("Hello! Welcome to the Mendoza Bank!");
		while(instance) {
			loginOptions();
			userInputStr = userScan.nextLine();
			if(userInputStr.matches("\\d")) {
				switch(userInputStr) {
					// Login
					case "1": {
						System.out.println("************LOGIN***********");
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
