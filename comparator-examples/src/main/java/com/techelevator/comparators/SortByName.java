package com.techelevator.comparators;

import java.util.Comparator;

import com.techelevator.User;

public class SortByName  implements Comparator<User> {

	@Override
	public int compare(User one, User two) {	
		return one.getName().compareTo(two.getName());
	}

}
