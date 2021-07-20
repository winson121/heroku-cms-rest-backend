package com.springcms.backendrestapi.service;

import java.util.List;

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
	public List<Course> getCoursesByUser(String username) {
		return courseDAO.getCoursesByUser(username);
	}

	@Override
	@Transactional
	public List<Course> getCourses() {
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
		courseDAO.saveCourseByUser(username, course);
	}

	@Override
	@Transactional
	public void deleteCourse(int courseId) {
		courseDAO.deleteCourse(courseId);
	}

}
