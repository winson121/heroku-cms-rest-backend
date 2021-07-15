package com.springcms.backendrestapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springcms.backendrestapi.dto.UserDTO;
import com.springcms.backendrestapi.entity.Role;
import com.springcms.backendrestapi.entity.User;
import com.springcms.backendrestapi.service.RoleService;
import com.springcms.backendrestapi.service.UserService;

@RestController
@RequestMapping("/user")
public class UserRestController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleService roleService;
	
	@PostMapping("/signup")
	public ResponseEntity<?> signUp(@RequestBody UserDTO userDto) {
		// retrieve user by username from the db
		String username = userDto.getUsername();
		
		User existing = userService.findByUsername(username);
		
		// if user existed, return response entity, otherwise create a new user with the username
		if (existing != null) {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
		userService.save(userDto);
		return new ResponseEntity<>(existing, HttpStatus.CREATED);
	}
	
	@GetMapping("/getuser/{username}")
	public ResponseEntity<?> getUser(@PathVariable String username) {
		// return user if exist, else return null with NOT_FOUND status
		User persistentUser = userService.findByUsername(username);
		
		if (persistentUser != null) {
			User user = new User(persistentUser);
			
			return new ResponseEntity<>(user, HttpStatus.OK);
		}
		
		return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
	}
	
	@GetMapping("/role/{roleName}")
	public ResponseEntity<?> getRoleByRoleName(@PathVariable String roleName) {
		Role role = roleService.findRoleByName(roleName);
		
		if(role != null) {
			return new ResponseEntity<>(role, HttpStatus.OK);
		}
		
		return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
	}
}
