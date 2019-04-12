package com.techelevator;

import com.techelevator.singleton.SingletonPattern;


/*
 * 
 *  The Singleton Pattern is used to create a single instance of an object that will be 
 *  shared for all uses in the project.  It is not created using the NEW keyword outside
 *  of this class, instead you use it by calling the static getInstance() method.
 * 
 * WHY USE A SINGLETON?
 * 
 * We use a singleton when we have a class that we only want to shared values across the entire project.  They should
 * be used sparingly, but are often good choices for configuration elements, user context, and database connections.
 */

public class SingletonExample {
	public static void main(String[] args) {

		System.out.println("*** SINGLETON PATTERN EXAMPLE ***");
		System.out.println();
		
		/* 
		 * To use the Singleton in your code you cannot use the NEW operator, instead
		 * you must call it's static getInstance() method.   Here we will get two copies.
		 */
		SingletonPattern one = SingletonPattern.getInstance();
		SingletonPattern two = SingletonPattern.getInstance();
		
		// Print both values
		System.out.println("Printing both copies of the Singleton: ");
		System.out.println("Singleton One: " + one.getNumber());
		System.out.println("Singleton Two: " + two.getNumber());
		
		System.out.println("Changing the value of copy one to 5");
		// Change one of them
		one.setNumber(5);
		
		System.out.println("Printing both copies of the Singleton Again: ");
		// Print them both again
		System.out.println("Singleton One: " + one.getNumber());
		System.out.println("Singleton Two: " + two.getNumber());
		System.out.println("NOTICE: both copies are the new value! ");
		/*
		 * Notice that both copies are now 5.  This is because both copies are references
		 * to the same object.  A singleton can only exist one time per project, so any
		 * changes made to it's values anywhere, changes all references.
		 */
		
	     /*
	      *  A common way to call a Singleton is using this style.  Notice that once 
	      *  static getInstance() method is called, we can then call any of the methods
	      *  on the class without the need to store an extra copy in a new variable.
	      */
		 System.out.println("Now the singleton is set to 7 using the static getInstance method.");
		 SingletonPattern.getInstance().setNumber(7);
		 
		System.out.println("Printing both copies of the Singleton on last time: ");
		// Print them both again
		System.out.println("Singleton One: " + one.getNumber());
		System.out.println("Singleton Two: " + two.getNumber());
		System.out.println("Singleton getInstance: " + SingletonPattern.getInstance().getNumber());
		System.out.println("NOTICE: once again all copies have the same value!");
	}
}
