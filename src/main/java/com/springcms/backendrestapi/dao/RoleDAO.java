package com.springcms.backendrestapi.dao;

import com.springcms.backendrestapi.entity.Role;

public interface RoleDAO {
	
	public Role findRoleByName(String roleName);
}
