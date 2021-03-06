package com.springcms.backendrestapi.entity;

import java.util.Collection;
import java.util.HashSet;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name="course")
@JsonIdentityInfo(scope=Course.class,
		  generator = ObjectIdGenerators.PropertyGenerator.class, 
		  property = "id")
public class Course {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="title")
	private String title;
	
	@Column(name="description")
	private String description;
	
	@ManyToOne(cascade= {CascadeType.PERSIST, CascadeType.MERGE,
			CascadeType.DETACH, CascadeType.REFRESH
	})
	@JoinColumn(name="instructor_id")
	private User instructor;
	
	@ManyToMany(fetch=FetchType.LAZY,
			cascade= {CascadeType.DETACH,
					CascadeType.MERGE,
					CascadeType.PERSIST,
					CascadeType.REFRESH
					})
	@JoinTable(
			name="courses_users",
			joinColumns=@JoinColumn(name="course_id"),
			inverseJoinColumns=@JoinColumn(name="user_id"))
	private Collection<User> users;
	
	public Course() {}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public User getInstructor() {
		return instructor;
	}

	public void setInstructor(User instructor) {
		this.instructor = instructor;
	}

	

	public Collection<User> getUsers() {
		return users;
	}

	public void setUsers(Collection<User> users) {
		this.users = users;
	}



	
	@Override
	public String toString() {
		return "Course [id=" + id + ", title=" + title + ", description=" + description + ", instructor=" + instructor
				+ ", users=" + users + "]";
	}

	public void addUser(User user) {
		if(users == null) {
			users = new HashSet<>();
		}
		users.add(user);
	}
	
	public void removeUser(User user) {
		users.remove(user);
	}
	
	@Override
	public boolean equals(Object obj) {
		// if both object reference the same object return true
		if(this == obj) return true;
		
		// check if arguments if of the type of the same class
		if(obj == null || obj.getClass() != this.getClass()) return false;
		
		// if object is the type geek, check the state of object
		Course course = (Course) obj;
		
		// comparing state of obj to 'this' obj
		return (course.title.equals(this.title));
	}
	
	@Override
	public int hashCode() {
		return this.title.hashCode();
	}
}
