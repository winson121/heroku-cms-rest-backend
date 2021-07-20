package com.springcms.backendrestapi.service;

import java.util.List;

import com.springcms.backendrestapi.entity.Course;

public interface CourseService {

	List<Course> getCoursesByUser(String username);

	List<Course> getCourses();

	Course saveCourseByUser(String username, Course course);

	Course getCourse(int courseId);

	void updateCourseByUser(String username, Course course);

	void deleteCourse(int courseId);

}
