package com.springcms.backendrestapi.dao;

import java.util.List;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.springcms.backendrestapi.entity.Course;
import com.springcms.backendrestapi.entity.User;

@Repository
public class CourseDAOImpl implements CourseDAO{
	
	@Autowired
	private EntityManager entityManager;
	
	private Logger logger = Logger.getLogger(getClass().getName());
	@Override
	public List<Course> getCoursesByUser(String username) {
		// get current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);
		
		// get user from username
		Query<User> query = 
				currentSession.createQuery("select u from User u "
						+ "JOIN FETCH u.courses "
						+ "where u.username=:username", User.class);
		
		query.setParameter("username", username);
		
		List<Course> userCourses = null;
		
		try {
			// get users' courses if courses not null
			User user = query.getSingleResult();
			userCourses = (List<Course>) user.getCourses();
			logger.info("getCoursesByUser userCourses :" + userCourses);
		} catch (NoResultException nre) {
			// ignore if courses is empty
		}
		
		return userCourses;
	}

	@Override
	public Course saveCourseByUser(String username, Course course) {
		// get current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);
				
		// query user from database
		Query<User> query = currentSession.createQuery("from User where username=:username", User.class);
		query.setParameter("username", username);
		
		User user = query.getSingleResult();
		
		// set course's instructor to the current logged in teacher
		course.setInstructor(user);
		
		// add course to user
		user.addCourse(course);
		// save course
		currentSession.saveOrUpdate(course);
		
		return course;
	}

	@Override
	public List<Course> getCourses() {
		// get current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);
		
		// create query to get courses
		Query<Course> query = currentSession.createQuery("from Course", Course.class);
		
		// execute query and get results
		List<Course> courses = query.getResultList();
		
		logger.info("result is: " + courses);

		// return results
		return courses;
	}

	@Override
	public Course getCourse(int courseId) {
		// get current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);
		
		// retrieve course from database
		Course course = currentSession.get(Course.class, courseId);
		
		return course;
	}

	@Override
	public void deleteCourse(int courseId) {
		// get the current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);
		
		// delete object with primary key
		Query<?> query = 
				currentSession.createQuery("delete from Course where id=:courseId");
		
		query.setParameter("courseId", courseId);
		
		query.executeUpdate();
		
	}


}
