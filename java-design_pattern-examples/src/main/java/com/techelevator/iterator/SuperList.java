package com.techelevator.iterator;

/*
 * We need to create a Collection class so we can apply an iterator to it.  We could use the java.util.List interface,
 * but that would mean implementing a lot of extra code, so instead this is a simple one that has only the getIterator()
 * method, which is the only one we care about for this example.
 */
public interface SuperList {
	public SuperIterator getIterator();
	public void Add(String s);
}
