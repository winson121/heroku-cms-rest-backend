package com.springcms.backendrestapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springcms.backendrestapi.dao.RoleDAO;
import com.springcms.backendrestapi.entity.Role;

@Service
public class RoleServiceImpl implements RoleService {
	
	@Autowired
	private RoleDAO roleDAO;
	
	@Override
	@Transactional
	public Role findRoleByName(String roleName) {
		return roleDAO.findRoleByName(roleName);
	}

}
