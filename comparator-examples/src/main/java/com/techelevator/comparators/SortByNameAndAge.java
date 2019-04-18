package com.techelevator.comparators;

import java.util.Comparator;

import com.techelevator.User;

public class SortByNameAndAge implements Comparator<User> {

	@Override
	public int compare(User one, User two) {
		
		// Determine the Sort by name and Age
		int nameCompare = one.getName().compareTo(two.getName());
		int ageCompare = one.getAge() - two.getAge();
		
		// Then Compare the first level sort (name) to determine how to return the comparison
		// If the names are the same then determine how to return the sort
		if (nameCompare == 0) {   
			// If the age are also Equal then return the sort order by name
			if (ageCompare == 0) {
				return nameCompare;
			} else {
				// If the ages are not equal, then return the sort order of the age
				return ageCompare;
			}
		} else {
			// If the Names where not equal, then just return the name comparison 
			return nameCompare;
		}
	}

}
