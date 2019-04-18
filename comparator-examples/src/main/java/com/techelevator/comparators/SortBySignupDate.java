package com.techelevator.comparators;

import java.util.Comparator;

import com.techelevator.User;

public class SortBySignupDate implements Comparator<User> {

	@Override
	public int compare(User one, User two) {
		if (one.getSignupDate().isAfter(two.getSignupDate())) {
			return 1;
		}
		if (one.getSignupDate().isBefore(two.getSignupDate())) {
			return -1;
		}
		return 0;
	}

}

