package com.techelevator.observer;

/*
 * Create an abstract Observer class which will be the base class for all of our observers.
 */
public abstract class ColorObserver {
	/*
	 * Create a protected variable to hold the Object we will be observing.  It must be protected so the
	 * child classes have access to it.
	 */
	protected ColorChanger colorChanger;
	
	/*
	 * Create a method to be called when the class we are observing makes a change
	 */
	public abstract void update();	
}
