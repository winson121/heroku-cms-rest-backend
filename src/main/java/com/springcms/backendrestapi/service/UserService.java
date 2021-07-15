package com.springcms.backendrestapi.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.springcms.backendrestapi.dto.UserDTO;
import com.springcms.backendrestapi.entity.User;

public interface UserService extends UserDetailsService{
	
	User findByUsername(String username);
	
	void save(UserDTO userDto);
}
