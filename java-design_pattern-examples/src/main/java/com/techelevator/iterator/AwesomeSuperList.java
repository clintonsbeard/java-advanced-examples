package com.techelevator.iterator;

import java.util.ArrayList;
import java.util.List;


/* 
 * Here we will create a simple Collection class to demonstrate the internal usage of an iterator. 
 */
public class AwesomeSuperList implements SuperList {

	// An Object to hold the Collection
	public List<String> strings = new ArrayList<String>();
	
	/*
	 *  The Iterator is internal to the this class, so we need a public method to get it.  Each time this method is
	 *  called a new instance of the Iterator class will be returned.  This will reset the index to 0 each time.
	 */
	@Override
	public SuperIterator getIterator() {
		return new SuperAwesomeIterator();
	}
	
	public void Add(String s) {
		strings.add(s);
	}

	/*
	 * The Iterator is created as a private class in our Collection class.  This is so only the Collection class
	 * maintains full control of it and can create it.  It also follows the principle of Encapsulation.  We do need
	 * to implement our Public SuperIteraor Interface, this is so outside classes can get a reference to it and use it.
	 */
	private class SuperAwesomeIterator implements SuperIterator {

		/*
		 * An index is created and set to 0.  This starts the iterator at the first item in the list
		 */
		int index = 0;
		
		/*
		 * The hasNext() method checks if an item exists in our list at our current index.  If it does
		 * it returns TRUE otherwise it returns FALSE.
		 */
		@Override
		public boolean hasNext() {
			return (strings.size() > 0 && index < strings.size()) ? true : false;
		}

		/*
		 * The next() method first verifies that the the internal list has an item at the current index.  If it does, 
		 * then it returns the item.  If it does not, then it returns null.  Notice, that we are returning the list item
		 * as a generic Object.  We could just return String, but that would limit our Collection type and Iterator.  
		 * Preferably we would use a Generic to define the type to return. For example, when you declare a List it 
		 * takes a generic List<String> so it knows what type it will hold and what type it's Iterator will return.
		 * Using a generic is not required for the Pattern, so we will just stick to Object.
		 */
		@Override
		public Object next() {
			if (hasNext()) {
				return strings.get(index++);  // Since we use index++, it uses index to get the current item and then increments the index to the next item
			}
			return null;
		}
		
	}
	
	
	
}
