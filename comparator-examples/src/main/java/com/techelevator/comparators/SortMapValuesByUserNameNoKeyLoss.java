package com.techelevator.comparators;

import java.util.Comparator;
import java.util.Map;

import com.techelevator.User;

/*
 * The Comparator for a Map is a little more complex.  The Comparators value will not be the map, but instead
 * the maps key.  Since the map being sorted is defined as Map<Integer, User> the comparator is defined to 
 * sort by Integer (the key's data type)
 */
public class SortMapValuesByUserNameNoKeyLoss implements Comparator<Integer> {

	// The Map will need to be held to be sorted, so define a variable to hold it
	private Map<Integer, User> map;
	
	// The original map will be passed to the comparator's constructor and its value held in the private variable above
	public SortMapValuesByUserNameNoKeyLoss(Map<Integer, User> map) {
		this.map = map;
	}
	
	// The compare function will take two keys from the original map (Integer)
	public int compare(Integer one, Integer two) {
		// The returned comparison value will be determined by the value at these keys
		int nameCompare =  map.get(one).getName().compareTo(map.get(two).getName());
		
		// If the names are equal, then return 1 (or -1, it doesn't matter since the sort will be the same).  
		// If 0 is returned then the keys will be combined, this will prevent that from happening, by informing
		// the TreeMap how to sort keys with equal value comparisons.
		return nameCompare == 0 ? 1 : nameCompare;
	}
	
}
