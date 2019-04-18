package com.techelevator.comparators;

import java.time.LocalDate;
import java.util.Comparator;

import com.techelevator.User;

public class SortBySingupDateAscAndNameDesc  implements Comparator<User> {

	@Override
	public int compare(User one, User two) {
		int dateCompare = compareDates(one.getSignupDate(), two.getSignupDate());
		int nameCompare = two.getName().compareTo(one.getName());
		
		// If the dates are EQUAL we need to determine what to return, else return the result of the dateCompare
		if (dateCompare == 0) {
			// If the names are also equal, then return the result of dateCompare, else return the result of the nameCompare
			return nameCompare == 0 ? dateCompare : nameCompare;
		} else {
			return dateCompare;
		}
		
	}
	
	private int compareDates(LocalDate one, LocalDate two) {
		if (one.isAfter(two)) {
			return 1;
		}
		if (one.isBefore(two)) {
			return -1;
		}
		return 0;
	}
}
