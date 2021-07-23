package com.springcms.backendrestapi.controller;

import java.security.Principal;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springcms.backendrestapi.entity.Course;
import com.springcms.backendrestapi.exception.CourseNotFoundException;
import com.springcms.backendrestapi.service.CourseService;

@RestController
@RequestMapping("/api")
public class CourseRestController {
	
	@Autowired
	private CourseService courseService;
	
	@GetMapping("/courses/users")
	public ResponseEntity<Collection<Course>> getCoursesByUser(Principal principal) {
		
		Collection<Course> userCourses = courseService.getCoursesByUser(principal.getName());
		
		
		if (userCourses != null) {
			return new ResponseEntity<>(userCourses, HttpStatus.OK);
		}
		
		return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
	}
	
	@GetMapping("/courses")
	public ResponseEntity<Collection<Course>> getCourses() {
		Collection<Course> courses = courseService.getCourses();
		
		return new ResponseEntity<>(courses, HttpStatus.OK);
	}
	
	@PostMapping("/courses/users")
	public ResponseEntity<Course> saveCourseByUser(@RequestBody Course course, Principal principal) {
		// just in case an id is pass in the JSON, we will set the id to 0
		// this will force a save of new item instead of updating the course
		if (course == null) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
		
		course.setId(0);
		
		// assigned course to current user
		Course savedCourse = courseService.saveCourseByUser(principal.getName(), course);
		
		return new ResponseEntity<>(savedCourse, HttpStatus.OK);
	}
	
	@GetMapping("/courses/{courseId}")
	public ResponseEntity<Course> getCourse(@PathVariable int courseId) {
		
		Course course = courseService.getCourse(courseId);
		
		if (course == null) {
			throw new CourseNotFoundException("Course id not found - " + courseId);
		}
		
		return new ResponseEntity<>(course, HttpStatus.OK);
	}
	
	@PutMapping("/courses/users")
	public ResponseEntity<Course> updateCourseByUser(@RequestBody Course course, Principal principal) {
		if(course == null) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
		
		courseService.updateCourseByUser(principal.getName(), course);
		
		return new ResponseEntity<>(course, HttpStatus.OK);
	}
	
	@DeleteMapping("/courses/{courseId}")
	public ResponseEntity<String> deleteCourse(@PathVariable int courseId, Principal principal) {
		
		courseService.deleteCourse(courseId);
		
		return new ResponseEntity<>("Deleted course id - " + courseId, HttpStatus.OK);
	}
	
	@GetMapping("/courses/{pageId}/{total}")
	public ResponseEntity<Collection<Course>> getCoursesByPage(@PathVariable int pageId, @PathVariable int total) {
		Collection<Course> courses = courseService.getCoursesByPage(pageId, total);
		
		return new ResponseEntity<>(courses, HttpStatus.OK);
	}
	
	@GetMapping("/courses/users/enroll/{courseId}")
	public ResponseEntity<Course> saveCourseToUser(@PathVariable int courseId, Principal principal) {
		// add course to current user
		Course course = courseService.saveCourseToUser(principal.getName(), courseId);
		
		return new ResponseEntity<>(course, HttpStatus.OK);
	}
	
	@GetMapping("/courses/users/unenroll/{courseId}")
	public ResponseEntity<Course> removeCourseFromUser(@PathVariable int courseId, Principal principal) {
		// remove course from current user
		Course course = courseService.removeCourseFromUser(principal.getName(), courseId);
		
		return new ResponseEntity<>(course, HttpStatus.OK);
	}
}
