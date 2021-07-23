package com.springcms.backendrestapi.service;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springcms.backendrestapi.dao.CourseDAO;
import com.springcms.backendrestapi.entity.Course;

@Service
public class CourseServiceImpl implements CourseService{
	
	// inject courseDAO
	@Autowired
	private CourseDAO courseDAO;
	
	@Override
	@Transactional
	public Collection<Course> getCoursesByUser(String username) {
		return courseDAO.getCoursesByUser(username);
	}

	@Override
	@Transactional
	public Collection<Course> getCourses() {
		return courseDAO.getCourses();
	}

	@Override
	@Transactional
	public Course saveCourseByUser(String username, Course course) {
		return courseDAO.saveCourseByUser(username, course);
	}

	@Override
	@Transactional
	public Course getCourse(int courseId) {
		return courseDAO.getCourse(courseId);
	}

	@Override
	@Transactional
	public void updateCourseByUser(String username, Course course) {
		courseDAO.updateCourseByUser(username, course);
	}

	@Override
	@Transactional
	public void deleteCourse(int courseId) {
		courseDAO.deleteCourse(courseId);
	}

	@Override
	@Transactional
	public Collection<Course> getCoursesByPage(int pageId, int total) {
		return courseDAO.getCoursesByPage(pageId, total);
	}

	@Override
	@Transactional
	public Course saveCourseToUser(String username, int courseId) {
		return courseDAO.saveCourseToUser(username, courseId);
	}

	@Override
	@Transactional
	public Course removeCourseFromUser(String username, int courseId) {
		return courseDAO.removeCourseFromUser(username, courseId);
	}

}
