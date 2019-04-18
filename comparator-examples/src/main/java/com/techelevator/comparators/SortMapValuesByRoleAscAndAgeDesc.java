package com.techelevator.comparators;

import java.util.Comparator;
import java.util.Map;

import com.techelevator.User;

public class SortMapValuesByRoleAscAndAgeDesc implements Comparator<Integer> {

	private Map<Integer, User> map;
	
	public SortMapValuesByRoleAscAndAgeDesc(Map<Integer, User> map) {
		this.map = map;
	}
	
	public int compare(Integer one, Integer two) {
		
		int roleCompare =  map.get(one).getRole().compareTo(map.get(two).getRole());
		int ageCompare = map.get(two).getAge() - map.get(one).getAge();

		if (roleCompare == 0 && ageCompare != 0) {
			return ageCompare == 0 ? 1: ageCompare;
		} else {
			return roleCompare == 0 ? 1 : roleCompare;
		}
	}

}
