package com.CMS.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.CMS.colors.ConsoleColor;
import com.CMS.exceptions.CourseException;
import com.CMS.model.Course;
import com.CMS.utility.DBUtil;

public class CourseDaoImpl implements CourseDao{
	
	
    

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

	@Override
	public List<Course> viewAllCourse() throws CourseException {
		
		List<Course> cousres = new ArrayList<>();
		
		try(Connection conn = DBUtil.proviodConnection()){
			
			PreparedStatement ps = conn.prepareStatement("select * from course");
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				Course course = new Course();
				course.setCourseId(rs.getInt("courseId"));
				course.setCorseName(rs.getString("courseName"));
				course.setFee(rs.getInt("fee"));
				course.setCourseDescription(rs.getString("courseDescription"));
				
				cousres.add(course);
			}
			
			if(cousres.size() == 0) throw new CourseException("No Course Data available");
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
		return cousres;
	}


	
	@Override
	public void viewCourse() {
		Scanner s = new Scanner(System.in);
		System.out.println("===============================");
		System.out.println(ConsoleColor.ANSI_PURPLE_BACKGROUND+"***** View all Courses *****"+ConsoleColor.ANSI_RESET);
		System.out.println("===============================");
		System.out.println();
		System.out.println("1. View Course by Id");
		System.out.println("2. View Course by Name");
		System.out.println("3. View Course by Fee");
		System.out.println("4. View Course by Description");
		System.out.println("5. View All Courses");
		System.out.println("6. Go Back");
		System.out.println();
		System.out.println(ConsoleColor.ANSI_GREEN+"Select Optoion no. :"+ConsoleColor.ANSI_RESET);
		
		int option = s.nextInt();
		
		if(option == 1) {
			try {
				System.out.println(viewAllCourseById());
			} catch (CourseException e) {
				e.printStackTrace();
			}finally {
				Scanner s1 = new Scanner(System.in);
				System.out.println(ConsoleColor.ANSI_GREEN+"Press any Number to go Back"+ConsoleColor.ANSI_RESET);
				int x = s1.nextInt();
				if(x == 1) viewCourse();
				else viewCourse();
			}
		}else if(option == 2) {
			try {
				System.out.println(viewAllCourseByName());
			} catch (CourseException e) {
				e.printStackTrace();
			}finally {
				Scanner s1 = new Scanner(System.in);
				System.out.println(ConsoleColor.ANSI_GREEN+"Press any Number to go Back"+ConsoleColor.ANSI_RESET);
				int x = s1.nextInt();
				if(x == 1) viewCourse();
				else viewCourse();
			}
		}else if(option == 3) {
			try {
				List<Course> courses = viewAllCourseByFee();
				courses.forEach(c -> System.out.println(c));
			} catch (CourseException e) {
				e.printStackTrace();
			}finally {
				Scanner s1 = new Scanner(System.in);
				System.out.println(ConsoleColor.ANSI_GREEN+"Press any Number to go Back"+ConsoleColor.ANSI_RESET);
				int x = s1.nextInt();
				if(x == 1) viewCourse();
				else viewCourse();
			}
		}else if(option == 4) {
			try {
				List<Course> courses = viewAllCourseByDesc();
				courses.forEach(c -> System.out.println(c));
			} catch (CourseException e) {
				e.printStackTrace();
			}finally {
				Scanner s1 = new Scanner(System.in);
				System.out.println(ConsoleColor.ANSI_GREEN+"Press any Number to go Back"+ConsoleColor.ANSI_RESET);
				int x = s1.nextInt();
				if(x == 1) viewCourse();
				else viewCourse();
			}
		}else if(option == 5) {
			try {
				List<Course> courses = viewAllCourse();
				courses.forEach(c -> System.out.println(c));
			} catch (CourseException e) {
				e.printStackTrace();
			}finally {
				Scanner s1 = new Scanner(System.in);
				System.out.println(ConsoleColor.ANSI_GREEN+"Press any Number to go Back"+ConsoleColor.ANSI_RESET);
				int x = s1.nextInt();
				if(x == 1) viewCourse();
				else viewCourse();
			}
		}else if (option == 6) {
			new AdminDaoImpl().AdminDashbord();
		}else {
			System.out.println(ConsoleColor.ANSI_RED+"Invaild Input, Try again"+ConsoleColor.ANSI_RESET);
			viewCourse();
		}
		
	}
	
	
	
	
	@Override
	public Course viewAllCourseById() throws CourseException {
		
		Course course = new Course();
		
		Scanner s = new Scanner(System.in);
		System.out.println("Enter Course Id");
		int id = s.nextInt();
		
		try(Connection conn = DBUtil.proviodConnection()){
			
			PreparedStatement ps = conn.prepareStatement("select * from course WHERE courseId=?");
			
			ps.setInt(1, id);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				course.setCourseId(rs.getInt("courseId"));
				course.setCorseName(rs.getString("courseName"));
				course.setFee(rs.getInt("fee"));
				course.setCourseDescription(rs.getString("courseDescription"));
			}else {
				System.out.println(ConsoleColor.ANSI_RED+"Course does not exost with Course-Id : "+id+ConsoleColor.ANSI_RESET);
				viewAllCourse();
			}
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
		return course;
	}

	@Override
	public Course viewAllCourseByName() throws CourseException {
		Course course = new Course();
		
		Scanner s = new Scanner(System.in);
		System.out.println("Enter Course Name");
		String name = s.next();
		
		try(Connection conn = DBUtil.proviodConnection()){
			
			PreparedStatement ps = conn.prepareStatement("select * from course WHERE courseName=?");
			
			ps.setString(1, name);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				course.setCourseId(rs.getInt("courseId"));
				course.setCorseName(rs.getString("courseName"));
				course.setFee(rs.getInt("fee"));
				course.setCourseDescription(rs.getString("courseDescription"));
			}else {
				System.out.println(ConsoleColor.ANSI_RED+"Course does not exost with Course-Id : "+name+ConsoleColor.ANSI_RESET);
				viewAllCourse();
			}
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
		return course;
	}

	@Override
	public List<Course> viewAllCourseByFee() throws CourseException {
		
		List<Course> cousres = new ArrayList<>();
		
		Scanner s = new Scanner(System.in);
		System.out.println("Enter Course Fee amount");
		int fee = s.nextInt();
		
		try(Connection conn = DBUtil.proviodConnection()){
			
			PreparedStatement ps = conn.prepareStatement("select * from course WHERE fee=?");
			
			ps.setInt(1, fee);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				Course course = new Course();
				course.setCourseId(rs.getInt("courseId"));
				course.setCorseName(rs.getString("courseName"));
				course.setFee(rs.getInt("fee"));
				course.setCourseDescription(rs.getString("courseDescription"));
				
				cousres.add(course);
			}
			
			if(cousres.size() == 0) throw new CourseException(ConsoleColor.ANSI_RED+" No Course Data available with that fee amount "+fee+ConsoleColor.ANSI_RESET);
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
		return cousres;
	}

	@Override
	public List<Course> viewAllCourseByDesc() throws CourseException {
		List<Course> cousres = new ArrayList<>();
		
		Scanner s = new Scanner(System.in);
		System.out.println("Enter Course Descprition");
		String desc = "%"+s.next()+"%";
		
		try(Connection conn = DBUtil.proviodConnection()){
			
			PreparedStatement ps = conn.prepareStatement("select * from course WHERE courseDescription LIKE ?");
			
			ps.setString(1, desc);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				Course course = new Course();
				course.setCourseId(rs.getInt("courseId"));
				course.setCorseName(rs.getString("courseName"));
				course.setFee(rs.getInt("fee"));
				course.setCourseDescription(rs.getString("courseDescription"));
				
				cousres.add(course);
			}
			
			if(cousres.size() == 0) throw new CourseException(ConsoleColor.ANSI_RED+" No Course Data available with matching Course Description "+desc+ConsoleColor.ANSI_RESET);
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println();
		System.out.println(ConsoleColor.ANSI_PURPLE+"Result"+ConsoleColor.ANSI_RESET);
		return cousres;
	}

}
