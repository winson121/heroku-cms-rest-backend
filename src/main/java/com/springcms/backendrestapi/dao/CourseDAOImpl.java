package com.springcms.backendrestapi.dao;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;

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
		
		return course;
	}
	
	@Override
	public void updateCourseByUser(String username, Course course) {
		// get current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);
		
	    // get course from db
		Course courseToUpdate = currentSession.get(Course.class, course.getId());
		
		// set course's attribute to the value that we want to update
		courseToUpdate.setTitle(course.getTitle());
		courseToUpdate.setDescription(course.getDescription());
		
	}

	@Override
	public Collection<Course> getCourses() {
		// get current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);
		
		// create query to get courses
		Query<Course> query = currentSession.createQuery("from Course", Course.class);
		
		// execute query and get results
		Collection<Course> courses = query.getResultList();

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

	@Override
	public Collection<Course> getCoursesByPage(int pageId, int total) {
		// get current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);
		
		// create query to get courses with limit
		Query<Course> query = currentSession.createQuery("from Course", Course.class).setFirstResult(pageId).setMaxResults(total);
		
		// execute query and get result list
		Collection<Course> courses = new HashSet<>(query.getResultList());
		
		return courses;
	}

	@Override
	public Course saveCourseToUser(String username, int courseId) {
		// get current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);
		
		// create query to get user 
		Query<User> userQuery = currentSession.createQuery("from User where username=:username", User.class);
		userQuery.setParameter("username",  username);
		
		User user = userQuery.getSingleResult();
		
		// get course by its id
		Course course = currentSession.get(Course.class, courseId);
		
		// add course to user and add user to course
		user.addCourse(course);
		course.addUser(user);
		
		return course;
	}

	@Override
	public Course removeCourseFromUser(String username, int courseId) {
		// get current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);
		
		//create query to get user
		Query<User> query = currentSession.createQuery("from User where username=:username", User.class)
							.setParameter("username", username);
		
		User user = query.getSingleResult();
		
		// get course by its id
		Course course = currentSession.get(Course.class, courseId);
		
		// remove course from user and remove user from course
		user.removeCourse(course);
		course.removeUser(user);
		
		return course;
	}

	@Override
	public Collection<Course> getCoursesBySearchString(String searchString, String colName) {
		// get current hibernate session
		Session currentSession =  entityManager.unwrap(Session.class);
		
		Query<Course> query;
        // only search by title if the searchTitle is not empty
        if (searchString != null && searchString.trim().length() > 0) {
            // search for title case insensitive
            query = getHibernateQuery(currentSession, searchString, colName);
        }
        else {
            // searchTitle is empty, just get all the courses
            query = currentSession.createQuery("from Course", Course.class);            
        }
        
    
        // execute query and get result list
        Collection<Course> courses = query.getResultList();
		return courses;
	}
	
	private Query<Course> getHibernateQuery(Session currentSession, String queryString, String queriedCol) {
		Query<Course> query = null;
		if (queriedCol.equals("title")) {
			query = currentSession.createQuery("from Course where lower("+queriedCol+") like :" + queriedCol, Course.class);
            query.setParameter(queriedCol, "%" + queryString.toLowerCase() + "%");
		}
		else if (queriedCol.equals("teacher")) {
			query = currentSession.createQuery("select c from Course c inner join c.instructor where lower(c.instructor.firstName) like :queryString or lower(c.instructor.lastName) like :queryString ", Course.class);
			query.setParameter("queryString", "%" + queryString.toLowerCase() + "%");
		}
        return query;
	}

}
