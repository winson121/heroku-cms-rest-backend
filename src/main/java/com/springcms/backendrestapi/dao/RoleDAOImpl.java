package com.springcms.backendrestapi.dao;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.springcms.backendrestapi.entity.Role;

@Repository
public class RoleDAOImpl implements RoleDAO {
	
	@Autowired
	private EntityManager entityManager;
	
	@Override
	public Role findRoleByName(String roleName) {
		
		// get current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);
		
		// retrieve role from db
		Query<Role> query = currentSession.createQuery("from Role where name=:roleName", Role.class);
		query.setParameter("roleName", roleName);
		
		Role role = null;
		
		// if query return a role, set role to the query result, otherwise return null
		try {
			role = query.getSingleResult();
		} catch (Exception e) {
			
		}
		
		return role;
	}

}
