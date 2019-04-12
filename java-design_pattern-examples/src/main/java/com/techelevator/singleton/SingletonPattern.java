package com.techelevator.singleton;


public class SingletonPattern {

	// Create and keep an instance of the class in a private variable
	private static final SingletonPattern instance = new SingletonPattern();
	
	
	// private constructor so this class cannot be instantiated using NEW
	private SingletonPattern(){}
	
	
	// getInstance method that returns the single copy of the class that is
	// stored in the private instance variable
	public static SingletonPattern getInstance() {
		return instance;
	}
	
	
	// BELOW IS NOT PART OF THE PATTERN, IT IS THE CLASS CODE
	private int number = 0;
	public int getNumber() {
		return this.number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	
}
