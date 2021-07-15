package com.springcms.backendrestapi.service;

import com.springcms.backendrestapi.entity.Role;

public interface RoleService {
	
	public Role findRoleByName(String roleName);
}
