package com.book.client;

import java.util.Scanner;

import com.book.bl.CustomerCartMap;
import com.book.ui.HomeUI;

public class MyMain {
	
	public static void main(String[] args) {
		HomeUI homeUI=new HomeUI();
		Scanner sc=new Scanner(System.in);
		boolean truthVal;
		do{
			homeUI.displayMenu();
			System.out.println("enter choice : ");
			int ch=sc.nextInt();
			truthVal = homeUI.choice(ch);
		} while(truthVal);
	}
	
	

}
