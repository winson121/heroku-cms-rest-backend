package com.springcms.backendrestapi.dao;

import java.util.Collection;

import com.springcms.backendrestapi.entity.Course;

public interface CourseDAO {

	Collection<Course> getCoursesByUser(String username);

	Course saveCourseByUser(String username, Course course);

	Collection<Course> getCourses();

	Course getCourse(int courseId);

	void deleteCourse(int courseId);

	Collection<Course> getCoursesByPage(int pageId, int total);

	void updateCourseByUser(String username, Course course);

	Course saveCourseToUser(String username, int courseId);

	Course removeCourseFromUser(String username, int courseId);

	Collection<Course> getCoursesBySearchString(String searchString, String colName);

	
}
