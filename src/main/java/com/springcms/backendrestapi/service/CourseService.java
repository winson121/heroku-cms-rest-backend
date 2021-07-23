package com.springcms.backendrestapi.service;

import java.util.Collection;

import com.springcms.backendrestapi.entity.Course;

public interface CourseService {

	Collection<Course> getCoursesByUser(String username);

	Collection<Course> getCourses();

	Course saveCourseByUser(String username, Course course);

	Course getCourse(int courseId);

	void updateCourseByUser(String username, Course course);

	void deleteCourse(int courseId);

	Collection<Course> getCoursesByPage(int pageId, int total);

	Course saveCourseToUser(String username, int courseId);

	Course removeCourseFromUser(String username, int courseId);


}
