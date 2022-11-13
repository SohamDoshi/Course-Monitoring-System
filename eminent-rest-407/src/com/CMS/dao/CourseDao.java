package com.CMS.dao;

import java.util.List;

import com.CMS.exceptions.CourseException;
import com.CMS.model.Course;

public interface CourseDao {

	public String insertNewCourse() throws CourseException;
	
	public void updateCourse();
	
	public String updateCourseId() throws CourseException;
	
	public String updateCourseName() throws CourseException;
	
	public String updateCourseFee() throws CourseException;
	
	public String updateCourseDesc() throws CourseException;
	
	public void viewCourse();
	
	public List<Course> viewAllCourse() throws CourseException;
	
	public Course viewAllCourseById() throws CourseException;
	
	public Course viewAllCourseByName() throws CourseException;
	
	public List<Course> viewAllCourseByFee() throws CourseException;
	
	public List<Course> viewAllCourseByDesc() throws CourseException;
	
	
}
