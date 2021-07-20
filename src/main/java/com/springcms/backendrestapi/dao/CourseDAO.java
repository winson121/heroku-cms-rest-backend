package com.springcms.backendrestapi.dao;

import java.util.List;

import com.springcms.backendrestapi.entity.Course;

public interface CourseDAO {

	List<Course> getCoursesByUser(String username);

	Course saveCourseByUser(String username, Course course);

	List<Course> getCourses();

	Course getCourse(int courseId);

	void deleteCourse(int courseId);

	
}
