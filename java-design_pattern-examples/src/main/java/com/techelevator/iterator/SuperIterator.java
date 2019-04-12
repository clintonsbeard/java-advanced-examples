package com.techelevator.iterator;

/* 
 * We need an interface for our Iterator.  Traditionally it will have at least two methods: hasNext() and next(), however,
 * it may have any other methods you need for it to function.
 */
public interface SuperIterator {
	
	public boolean hasNext();
	public Object next();
	
}
