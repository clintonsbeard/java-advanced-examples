package com.techelevator;

import com.techelevator.iterator.AwesomeSuperList;
import com.techelevator.iterator.SuperIterator;
import com.techelevator.iterator.SuperList;

/*
 * 
 * An Iterator is applied to a Collection style class and allows the items in the collection to be accessed sequentially 
 * without the caller needing to know anything about the collection.  The Iterator pattern is used in a lot of Java class, 
 * including the Collection types (ArrayList, Set, Map, etc) and the SqlRowSet objects.  Any time you have an object that 
 * contains a list that has the following two methods: hasNext() and next(), the class is implementing a Iterator pattern.
 * 
 * WHY USE AN ITERATOR?
 * 
 * An Iterator is used when we want a way for the user of our object to go through a list of items our object
 * is holding without having to know the number of items in our list or how it is built.   While a custom iterator is
 * not very common, the Iterator pattern is used throughout the Java language.  For example, all of the Java Collection types
 * (Map, List, Set, Queue, etc.) and the SqlRowSet all use the Iterator pattern.
 */
public class IteratorExample {

	public static void main(String[] args) {
		
		System.out.println("*** ITERATOR PATTERN EXAMPLE ***");
		System.out.println();
		
		// Create a Copy of our customer Collection class
		System.out.println("Create a new instance of our Custom Collection");
		SuperList mySuperList = new AwesomeSuperList();
		
		// Fill it with some items (in this case it takes Strings for simplicity)
		System.out.println("Fill it with some strings, in this case: One, Two, Three, Four, and Five");
		mySuperList.Add("One");
		mySuperList.Add("Two");
		mySuperList.Add("Three");
		mySuperList.Add("Four");
		mySuperList.Add("Five");
		
		System.out.println("Get the Iterator and use it to get each item in the list to print to the console");
		// Get the Iterator from our SuperList instance		
		SuperIterator iterator = mySuperList.getIterator();
		
		// Use the Iterator to get each item in our SuperList, and Cast it as a String, so we can write it to the Console
		while(iterator.hasNext()) {  // Use the hasNext() to know when we've reached the last item
			String s = (String) iterator.next();  // Use next() to get the next item and increment the Iterator to the next item
			System.out.println(s);
		}
		
		
	}
}
