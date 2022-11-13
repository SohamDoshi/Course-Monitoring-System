package com.CMS.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
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
		System.out.println(ConsoleColor.ANSI_PURPLE_BACKGROUND+" ***** Administrator Login ***** "+ConsoleColor.ANSI_RESET);
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
			System.out.println(ConsoleColor.ANSI_GREEN+" *** Welcome Back "+u+" *** "+ConsoleColor.ANSI_RESET);
			AdminDashbord();
			
			
		}else
			System.out.println(ConsoleColor.ANSI_RED+"Invaild Username or Password"+ConsoleColor.ANSI_RESET);
			login();
		
		
		}catch (SQLException e) {
			e.getStackTrace();
		}
		
		
	}

	@Override
	public void AdminDashbord() {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("===============================");
		System.out.println(ConsoleColor.ANSI_PURPLE_BACKGROUND+" ***** Admin Dashborad ***** "+ConsoleColor.ANSI_RESET);
		System.out.println("===============================");
		System.out.println();
		System.out.println(ConsoleColor.ANSI_GREEN_BACKGROUND+" Course "+ConsoleColor.ANSI_RESET);
		System.out.println("1. Insert new Course");
		System.out.println("2. Update Course");
		System.out.println("3. View all Courses");
		System.out.println();
		System.out.println(ConsoleColor.ANSI_GREEN_BACKGROUND+" Batch "+ConsoleColor.ANSI_RESET);
		System.out.println("4. Insert new Batch");
		System.out.println("5. Update Batch");
		System.out.println("6. View all Batches");
		System.out.println();
		System.out.println(ConsoleColor.ANSI_GREEN_BACKGROUND+" Facalty "+ConsoleColor.ANSI_RESET);
		System.out.println("7. Insert new Faculty");
		System.out.println("8. Update Faculty");
		System.out.println("9. View all Faculties");
		System.out.println();
		System.out.println(ConsoleColor.ANSI_GREEN_BACKGROUND+" Course Plan "+ConsoleColor.ANSI_RESET);
		System.out.println("10. Insert new Course Plan");
		System.out.println("11. Update Course Plan");
		System.out.println("12. View all Course Plans");
		System.out.println();
		System.out.println(ConsoleColor.ANSI_GREEN_BACKGROUND+" Other "+ConsoleColor.ANSI_RESET);
		System.out.println("13. Allocate faculty to a batch");
		System.out.println("14. View the Day wise update of every batch");
		System.out.println("15. Generate Report for every batch");
		System.out.println("16. LogOut");
		System.out.println(ConsoleColor.ANSI_GREEN+"Enter Option no. :"+ConsoleColor.ANSI_RESET);
		
		
		int option = sc.nextInt();
		
		CourseDao dao = new CourseDaoImpl();
		
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
			dao.viewCourse();
		}
		else if (option == 16) {
			new Home().HomePage();
		}else {
			System.out.println(ConsoleColor.ANSI_RED+"Invaild Input, Try again"+ConsoleColor.ANSI_RESET);
			AdminDashbord();
		}
	}

	
	

}
