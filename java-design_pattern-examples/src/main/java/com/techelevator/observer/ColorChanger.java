package com.techelevator.observer;

import java.util.ArrayList;
import java.util.List;

/* 
 * Create the class that the observers will be watching.  It must implement some code for the observers to use.
 */
public class ColorChanger {

	/*
	 * Create a private list to hold the observers that are "registered" to watch the class
	 */
	private List<ColorObserver> observers = new ArrayList<ColorObserver>();
	
	/*
	 * Create the property that the observers will be watching
	 */
	private String color;
	
	public String getColor() {
		return color;
	}
	
	/*
	 * In the setter, after the value is set call the method to notify the observers of the change.  This will
	 * call the notification method (in this case update()) on each of the Observers that have been registered 
	 * with our object.
	 */
	public void SetColor(String color) {
		this.color = color;
		notifyObservers();
	}
	
	/*
	 * Create a method to allow the observers to register with our class for notification.  
	 * This method just needs to add the List of observers
	 */
	public void register(ColorObserver colorObserver) {
		observers.add(colorObserver);
	}
	
	
	/*
	 * Create a method to notify the observers.  It will iterator through the List of observers and call each
	 * of their update methods.
	 */
	public void notifyObservers() {
		for (ColorObserver observer : observers) {
			observer.update();
		}
	}
	
}
