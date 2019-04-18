package com.techelevator;

import java.time.LocalDate;

public class User {

	private String name;
	private String role;
	private int age;
	private LocalDate signupDate;
	
	public User() {}
	
	public User(String name, String role, int age, LocalDate signupDate) {
		this.name = name;
		this.role = role;
		this.age = age;
		this.signupDate = signupDate;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public LocalDate getSignupDate() {
		return signupDate;
	}
	public void setSignupDate(LocalDate signupDate) {
		this.signupDate = signupDate;
	}

	@Override
	public String toString() {
		return "User [name=" + name + ", role=" + role + ", age=" + age + ", signupDate=" + signupDate + "]";
	}
	
}
