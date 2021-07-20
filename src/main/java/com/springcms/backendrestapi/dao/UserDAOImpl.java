package com.springcms.backendrestapi.dao;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.springcms.backendrestapi.entity.User;

@Repository
public class UserDAOImpl implements UserDAO {
	
	@Autowired
	private EntityManager entityManager;
	
	@Override
	public User findByUsername(String username) {
		// get current hibernate session
		Session currentSession =  entityManager.unwrap(Session.class);
		
		// create query to get the user
		Query<User> query = currentSession.createQuery("from User u where u.username=:username", User.class);
		query.setParameter("username", username);
		
		// check if username exist in db
		User user = null;
		
		try {
			user = query.getSingleResult();
//			System.out.println(user);
		} catch (Exception e) {
			
		}
		return user;
	}

	@Override
	public void save(User user) {
		// get current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);
		
		// create user
		currentSession.saveOrUpdate(user);
	}

}
