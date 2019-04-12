package com.techelevator.visitor.interfaces;

/*
 * The Package abstract is not part of the visitor pattern, but it is needed for this use case.  We use an 
 * abstract class rather than an interface since we need each subclass to implement it's own getPrice() method, but
 * we want to have standardized get/set Weight methods.  This gives us the usage of a base class while still requiring 
 * that the sub-classes implement a particular method (getPrice()).  
 * 
 */
public abstract class Package {
	
	/*
	 * A private variable to hold the package weight
	 */
	private double weight = 0;
	
	/*
	 * The getPrice() abstract method works like an interface, it create a contract that the getPrice() method will
	 * be available on all of the Package types, but it forces the implementation of the method on each sub-class.
	 */
	public abstract double getPrice();
	
	
	/*
	 * A getter to allow other classes to get the package weight.
	 */
	public double getWeight() {
		return this.weight;
	}
	
	/*
	 * A setter class to allow our sub-classes to set the package weight.  This method is protected 
	 * since we want the sub-classes to be able to set the package, but we do not want other non-sub-class objects
	 * to be able to change the weight of the Package.
	 */
	protected void setWeight(double weight) {
		this.weight = weight;
	}
}
