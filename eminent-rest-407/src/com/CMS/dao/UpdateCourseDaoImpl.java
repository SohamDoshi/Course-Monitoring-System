package com.CMS.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

import com.CMS.colors.ConsoleColor;
import com.CMS.exceptions.CourseException;
import com.CMS.utility.DBUtil;

public class UpdateCourseDaoImpl implements UpdateCourseDao{
	
	
    

	@Override
	public String insertNewCourse() throws CourseException{
		
		Scanner s = new Scanner(System.in);
		
		System.out.println("===============================");
		System.out.println(ConsoleColor.ANSI_GREEN_BACKGROUND+"***** Insert new Course *****"+ConsoleColor.ANSI_RESET);
		System.out.println("===============================");
		System.out.println();
		
		System.out.println("Enter Course ID :");
		int courseId = s.nextInt();
		
		System.out.println("Enter Course Name :");
		String courseName = s.next();
		
		System.out.println("Enter Amount of Fee :");
		int fee = s.nextInt();
		
		System.out.println("Enter Course Description :");
		String courseDesc = s.next();
		
		String message = ConsoleColor.ANSI_RED+"Not Inserted"+ConsoleColor.ANSI_RESET;
		
		try(Connection conn = DBUtil.proviodConnection()){
			
			PreparedStatement ps = conn.prepareStatement("insert into course values(?,?,?,?)");
			
			ps.setInt(1, courseId);
			ps.setString(2,courseName);
			ps.setInt(3, fee);
			ps.setString(4, courseDesc);
			
			int x = ps.executeUpdate();
			
			if(x > 0) message = ConsoleColor.ANSI_GREEN+"Record Inserted Successfully..."+ConsoleColor.ANSI_RESET;
			
		}catch (SQLException e) {
			message = e.getMessage();
			throw  new CourseException(e.getMessage());
		}
		
		return message;
		
	}

	@Override
	public void updateCourse() {
		
		Scanner s = new Scanner(System.in);
		
		System.out.println("===============================");
		System.out.println(ConsoleColor.ANSI_PURPLE_BACKGROUND + "***** Update Course *****" + ConsoleColor.ANSI_RESET);
		System.out.println("===============================");
		System.out.println();
		
		System.out.println("Select following options");
		System.out.println();
		System.out.println("1. Update Course ID");
		System.out.println("2. Update Course Name");
		System.out.println("3. Update Fee amount");
		System.out.println("4. Update Course description");
		System.out.println("5. Go Back");
		System.out.println("Enter No. :");
		int option = s.nextInt();
		
		if(option == 1) {
			try {
				System.out.println(updateCourseId());
			} catch (CourseException e) {
				e.printStackTrace();
			}finally {
				updateCourse();
			}
		}else if(option == 2) {
			try {
				System.out.println(updateCourseName());
			} catch (CourseException e) {
				e.printStackTrace();
			}finally {
				updateCourse();
			}
		}else if (option == 3) {
			try {
				System.out.println(updateCourseFee());
			} catch (CourseException e) {
				e.printStackTrace();
			}finally {
				updateCourse();
			}
		}else if (option == 4) {
			try {
				System.out.println(updateCourseDesc());
			} catch (CourseException e) {
				e.printStackTrace();
			}finally {
				updateCourse();
			}
		}else if(option == 5) {
			AdminDao dao = new AdminDaoImpl();
			dao.AdminDashbord();
		}else {
			System.out.println(ConsoleColor.ANSI_GREEN+"Invaild Input, Try again"+ConsoleColor.ANSI_RESET);
			updateCourse();
		}
		
	}


	@Override
	public String updateCourseId() throws CourseException {
		
		Scanner s = new Scanner(System.in);
		
		System.out.println("===============================");
		System.out.println(ConsoleColor.ANSI_PURPLE_BACKGROUND+"***** Update Course ID *****"+ConsoleColor.ANSI_RESET);
		System.out.println("===============================");
		System.out.println();
		
		System.out.println("Enter Old Course ID :");
		int oldCourseId = s.nextInt();
		
		System.out.println("Enter  New Course ID :");
		int newCourseId = s.nextInt();
		
		
		
		String message = ConsoleColor.ANSI_RED +"Course Id "+oldCourseId+" not found"+ConsoleColor.ANSI_RESET;
		
		
		try(Connection conn = DBUtil.proviodConnection()){
			
			PreparedStatement ps = conn.prepareStatement("UPDATE course SET courseId=? WHERE courseId=?");
			
			ps.setInt(1, newCourseId);
			ps.setInt(2, oldCourseId);
			
			int x = ps.executeUpdate();
		
			if(x > 0) message = ConsoleColor.ANSI_GREEN +"Course ID Sucessfully Updated..."+ConsoleColor.ANSI_RESET;
			
		}catch (SQLException e) {
			e.getMessage();
			throw new CourseException(e.getMessage());
		}
		return message;
	}


	@Override
	public String updateCourseName() throws CourseException {
		Scanner s = new Scanner(System.in);
		
		System.out.println("===============================");
		System.out.println(ConsoleColor.ANSI_PURPLE_BACKGROUND+"***** Update Course Name *****"+ConsoleColor.ANSI_RESET);
		System.out.println("===============================");
		System.out.println();
		
		System.out.println("Enter Old Course Name :");
		String oldCourseName = s.next();
		
		System.out.println("Enter  New Course Name :");
		String newCourseName = s.next();
		
		
		
		String message = ConsoleColor.ANSI_RED+"Course Name "+oldCourseName+" not found"+ConsoleColor.ANSI_RESET;
		
		
		try(Connection conn = DBUtil.proviodConnection()){
			
			PreparedStatement ps = conn.prepareStatement("UPDATE course SET courseName=? WHERE courseName=?");
			
			ps.setString(1, newCourseName);
			ps.setString(2, oldCourseName);
			
			int x = ps.executeUpdate();
		
			if(x > 0) message = ConsoleColor.ANSI_GREEN+"Course Name Sucessfully Updated..."+ConsoleColor.ANSI_RESET;
			
		}catch (SQLException e) {
			e.getMessage();
			throw new CourseException(e.getMessage());
		}
		return message;
	}



	@Override
	public String updateCourseFee() throws CourseException {
		Scanner s = new Scanner(System.in);
		
		System.out.println("===============================");
		System.out.println("Update Course Fee");
		System.out.println("===============================");
		System.out.println();
		
		System.out.println("Enter Old Course Fee :");
		int oldCoursefee = s.nextInt();
		
		System.out.println("Enter  New Course Fee :");
		int newCoursefee = s.nextInt();
		
		
		
		String message = "Course fee "+oldCoursefee+" not found";
		
		
		try(Connection conn = DBUtil.proviodConnection()){
			
			PreparedStatement ps = conn.prepareStatement("UPDATE course SET fee=? WHERE fee=?");
			
			ps.setInt(1, newCoursefee);
			ps.setInt(2, oldCoursefee);
			
			int x = ps.executeUpdate();
		
			if(x > 0) message = "Course Fee Sucessfully Updated...";
			
		}catch (SQLException e) {
			e.getMessage();
			throw new CourseException(e.getMessage());
		}
		return message;
	}



	@Override
	public String updateCourseDesc() throws CourseException {
		Scanner s = new Scanner(System.in);
		
		System.out.println("===============================");
		System.out.println("Update Course Description");
		System.out.println("===============================");
		System.out.println();
		
		System.out.println("Enter Old Course Description :");
		String oldCourseDesc = s.next();
		
		System.out.println("Enter  New Course Description :");
		String newCourseDesc = s.next();
		
		
		
		String message = "Course Name "+oldCourseDesc+" not found";
		
		
		try(Connection conn = DBUtil.proviodConnection()){
			
			PreparedStatement ps = conn.prepareStatement("UPDATE course SET courseDescription=? WHERE courseDescription=?");
			
			ps.setString(1, newCourseDesc);
			ps.setString(2, oldCourseDesc);
			
			int x = ps.executeUpdate();
		
			if(x > 0) message = "Course Description Sucessfully Updated...";
			
		}catch (SQLException e) {
			e.getMessage();
			throw new CourseException(e.getMessage());
		}
		return message;
	}
}
