package com.CMS.model;

public class Course {

	private int courseId;
	private String corseName;
	private int fee;
	private String CourseDescription;
	
	
	public Course() {
		
	}


	public Course(int courseId, String corseName, int fee, String courseDescription) {
		super();
		this.courseId = courseId;
		this.corseName = corseName;
		this.fee = fee;
		CourseDescription = courseDescription;
	}


	@Override
	public String toString() {
		return "Course [courseId=" + courseId + ", corseName=" + corseName + ", fee=" + fee + ", CourseDescription="
				+ CourseDescription + "]";
	}


	public int getCourseId() {
		return courseId;
	}


	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}


	public String getCorseName() {
		return corseName;
	}


	public void setCorseName(String corseName) {
		this.corseName = corseName;
	}


	public int getFee() {
		return fee;
	}


	public void setFee(int fee) {
		this.fee = fee;
	}


	public String getCourseDescription() {
		return CourseDescription;
	}


	public void setCourseDescription(String courseDescription) {
		CourseDescription = courseDescription;
	}
	
	
}
