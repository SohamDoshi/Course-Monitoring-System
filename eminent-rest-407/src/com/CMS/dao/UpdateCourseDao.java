package com.CMS.dao;

import com.CMS.exceptions.CourseException;

public interface UpdateCourseDao {

	public String insertNewCourse() throws CourseException;
	
	public void updateCourse();
	
	public String updateCourseId() throws CourseException;
	
	public String updateCourseName() throws CourseException;
	
	public String updateCourseFee() throws CourseException;
	
	public String updateCourseDesc() throws CourseException;
}
