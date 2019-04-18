package com.techelevator.comparators;

import java.util.Comparator;

import com.techelevator.User;

public class SortByAgeDesc implements Comparator<User>{

	@Override
	public int compare(User one, User two) {
		return two.getAge() - one.getAge();
	}

}

