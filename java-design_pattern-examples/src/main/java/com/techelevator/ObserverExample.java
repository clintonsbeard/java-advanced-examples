package com.techelevator;

import com.techelevator.observer.ColorChanger;
import com.techelevator.observer.BlueColorObserver;
import com.techelevator.observer.RedColorObserver;
import com.techelevator.observer.GreenColorObserver;

/*
 * The Observer pattern is used when we need for a change in the state of an object to be reported to
 * other objects, called observers, that are registered to be notified.  When the object being watched changes
 * the objects observing the change are automatically notified.  The Observer pattern is used in many places in 
 * the Java language and often called Events.  It is also used in JavaScript and other UI languages to notify when
 * the user takes an action, such as presses a button.
 * 
 * WHEN TO USE OBSERVER
 * When you have an object that you want to automatically update other objects when its state changes.  For example,
 * when a Color Changer changes colors or when a button is pressed.
 */
public class ObserverExample {
	
	public static void main(String[] args) {

		System.out.println("*** OBSERVER PATTERN EXAMPLE ***");
		System.out.println();
		
		/*
		 * Instantiate a copy of the original object, in this case we will use a Color Changer classes, that 
		 * will notify the observer objects when the color is changed.
		 */
		System.out.println("Create a new instance of the original ColorChanger class");
		ColorChanger colorChanger = new ColorChanger();
		System.out.println();
		
		/*
		 * Register the observers with the original class, so they will be notified when its state changes
		 */
		System.out.println("Register the observer classes, in this case we will use one to watch ");
		System.out.println("when the Color changes to Red, Blue, and Green.");
		new RedColorObserver(colorChanger);
		new BlueColorObserver(colorChanger);
		new GreenColorObserver(colorChanger);
		
		/*
		 * Change the state of the original ColorChanger class, in this case it will be by changing the Color
		 */
		System.out.println();
		System.out.println("Set the Color to Red.  Notice how the three observers respond automatically.");
		colorChanger.SetColor("Red");
		System.out.println();
		System.out.println("Now we will Set the Color to Green.");
		colorChanger.SetColor("Green");
		System.out.println();
		System.out.println("Now we will Set the Color to Blue.");
		colorChanger.SetColor("Blue");
		System.out.println();
		System.out.println("Finally, we will Set the Color to Orange.");
		colorChanger.SetColor("Orange");
	}
}
