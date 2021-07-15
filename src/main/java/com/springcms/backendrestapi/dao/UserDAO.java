package com.springcms.backendrestapi.dao;

import com.springcms.backendrestapi.entity.User;

public interface UserDAO {
	
	User findByUsername(String username);
	
	void save(User user);
}
