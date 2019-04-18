package com.techelevator.comparators;

import java.util.Comparator;

import com.techelevator.User;

public class SortByAge implements Comparator<User>{

	@Override
	public int compare(User one, User two) {
		return one.getAge() - two.getAge();
	}

}
