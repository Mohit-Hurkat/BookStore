package com.book.ui;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.book.bean.Admin;
import com.book.bean.Customer;
import com.book.bean.User;
import com.book.bl.CustomerLogic;
import com.book.constant.UIConstants;
import com.book.helper.AdminAuthenticator;
import com.book.helper.CustomerAuthenticator;
import com.book.helper.CustomerData;

public class HomeUI {
	
	private static final String MENU_OPTIONS = "1. Sign Up" +
			"\n" + "2. Sign In" + "\n" + "3. Exit";
	private static final String CHOICE_MSG = "Enter your choice";
	private static final String ACC_CREATION_SUCCESSFUL_MSG = 
			"Account Created Successfully";
	private static final String ACC_CREATION_FAILED_MSG1 = 
			"Sign Up with a Different Username";
	private static final String ACC_CREATION_FAILED_MSG = 
			"Account Creation failed";
	private static final String AUTH_FAILED_MSG = 
			"Authentication failed";
	private static final String USERNAME_PROMPT = "Enter Username";
	private static final String PASSWORD_PROMPT = "Enter Password";
	private static final String EXIT_MSG = "Exit";
	private static final String ERROR_DESC_PREFIX_MSG = "Error";
	private static final String INVALID_CHOICE_MSG = "Invalid Choice";

	private CustomerLogic cbl=new CustomerLogic();
	
	public void displayMenu(){
		System.out.println(MENU_OPTIONS);
	}
	
	public boolean choice(int choice) {
		CustomerData cd=new CustomerData();
		Scanner scanner=new Scanner(System.in);
		
		Customer customer;
		
		switch(choice){
		case 1:
			try {
				cd.input();
				boolean status=cbl.add(cd.getCustomer());
				if(status){
					System.out.println(ACC_CREATION_SUCCESSFUL_MSG);
				}
				else{
					System.out.println(ACC_CREATION_FAILED_MSG1);
					
				}
			} catch (IOException e) {
				System.out.println(ACC_CREATION_FAILED_MSG +
						ERROR_DESC_PREFIX_MSG + ": " + e);
			} catch (ClassNotFoundException e) {
				System.out.println(ACC_CREATION_FAILED_MSG +
						ERROR_DESC_PREFIX_MSG + ": " + e);
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case 2:
			try {
				CustomerAuthenticator ca = 
						new CustomerAuthenticator();
				AdminAuthenticator aa =
						new AdminAuthenticator();
				System.out.println(USERNAME_PROMPT + 
						": ");
				String username = scanner.next();
				System.out.println(PASSWORD_PROMPT + 
						": ");
				String password = scanner.next();
				boolean truthVal = false;
				if(username.equals(Admin.USER_NAME)){
					Map.Entry<Admin, Boolean> result =
							aa.authenticate(username, password);
					if(result.getValue() != false){
						AdminUI adminUI = new AdminUI();
						do{
							adminUI.displayMenu();
							System.out.print(CHOICE_MSG + ": ");
							truthVal = adminUI.choice(scanner.nextInt());
						} while (truthVal);
					}
					else{
						System.out.println(AUTH_FAILED_MSG);
					}
					
				}
				else{
					Map.Entry<Customer, Boolean> result =
							ca.authenticate(username, password);
					if(result.getValue() != false){
						CustomerUI customerUI = new CustomerUI(result.getKey());
						do {
							customerUI.displayMenu();
							System.out.print(CHOICE_MSG + ": ");
							truthVal = customerUI.choice(scanner.nextInt());
						} while(truthVal);
					}
					else{
						System.out.println(AUTH_FAILED_MSG);
					}
				}
			}
			catch(ClassNotFoundException cnfe){
				System.out.println(ERROR_DESC_PREFIX_MSG);
				cnfe.printStackTrace();
			} catch (IOException e) {
				System.out.println(ERROR_DESC_PREFIX_MSG);
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case 3:
			System.out.println(EXIT_MSG);
			return false;
		default:
			System.out.println(INVALID_CHOICE_MSG);
		}
		return true;
	}
}
