package com.CMS.home;

import java.util.Scanner;

import com.CMS.colors.ConsoleColor;
import com.CMS.dao.AdminDao;
import com.CMS.dao.AdminDaoImpl;

public class Home {

	public void HomePage() {
		Scanner s = new Scanner(System.in);
		
		
		System.out.println("=============");
		System.out.println(ConsoleColor.ANSI_PURPLE_BACKGROUND+"*** Home ***"+ConsoleColor.ANSI_RESET);
		System.out.println("=============");
		System.out.println(ConsoleColor.ANSI_GREEN+"Welcome to Course Monitoring System v1.0"+ConsoleColor.ANSI_RESET);
		System.out.println("Select Number from following options");
		System.out.println("1. Administrator Login");
		System.out.println("2. Faculty Login");
		System.out.println("3. Exit");
		System.out.println("Enter No. :");
		
		
		
		int option = s.nextInt();
		
		//AdminDao dao = new AdminDaoImpl();
		
		
		if(option == 1)new AdminDaoImpl().login();
		else if(option == 2)new AdminDaoImpl().login();
		else if(option == 3) {
			System.out.println("Thank you!");
			System.exit(0);
		}
	}
}
