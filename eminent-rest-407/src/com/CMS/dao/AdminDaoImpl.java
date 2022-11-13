package com.CMS.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.CMS.colors.ConsoleColor;
import com.CMS.exceptions.CourseException;
import com.CMS.home.Home;
import com.CMS.model.Administrator;
import com.CMS.model.Course;
import com.CMS.usecase.AdminLogin;
import com.CMS.utility.DBUtil;

public class AdminDaoImpl implements AdminDao{

	@Override
	public void login() {
		
		Scanner s = new Scanner(System.in);
		
		System.out.println("===============================");
		System.out.println(ConsoleColor.ANSI_RED_BACKGROUND+"***** Administrator Login *****"+ConsoleColor.ANSI_RESET);
		System.out.println("===============================");
		System.out.println();
		System.out.println("Enter Username :");
		String user = s.nextLine();
		
		System.out.println("Enter Password :");
		String pass = s.next();
		
		try(Connection conn = DBUtil.proviodConnection()){
			
		PreparedStatement ps = conn.prepareStatement("select * from admin where username =? AND password=?");
			
		ps.setString(1, user);
		ps.setString(2, pass);
		
		ResultSet rs = ps.executeQuery();
		
		if(rs.next()) {
			
			String u= rs.getString("username");
			//String p= rs.getString("password");
			System.out.println();
			System.out.println("Welcome Back "+u);
			AdminDashbord();
			
			
		}else
			System.out.println("Invaild Username or Password");
			login();
		
		
		}catch (SQLException e) {
			e.getStackTrace();
		}
		
		
	}

	@Override
	public void AdminDashbord() {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("===============================");
		System.out.println(ConsoleColor.ANSI_YELLOW_BACKGROUND+"***** Admin Dashborad *****"+ConsoleColor.ANSI_RESET);
		System.out.println("===============================");
		System.out.println("1. Insert new Course");
		System.out.println("2. Update Course");
		System.out.println("3. LogOut");
		System.out.println("Enter Option no. :");
		
		
		int option = sc.nextInt();
		
		UpdateCourseDao dao = new UpdateCourseDaoImpl();
		
		if(option == 1) {
			
			try {
				System.out.println(dao.insertNewCourse());
			} catch (CourseException e) {
				e.printStackTrace();
			}finally {
				AdminDashbord();
			}
		}else if(option == 2) {
			dao.updateCourse();
		}else if (option == 3) {
			new Home().HomePage();
		}else {
			System.out.println(ConsoleColor.ANSI_RED+"Invaild Input, Try again"+ConsoleColor.ANSI_RESET);
		}
	}

	
	

}
