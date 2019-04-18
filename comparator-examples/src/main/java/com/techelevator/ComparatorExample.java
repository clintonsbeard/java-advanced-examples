package com.techelevator;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.techelevator.comparators.*;

public class ComparatorExample {

	/*
	 * A Comparator is an object that implements the Comparator interface that defines how to sort two unlike or custom
	 * objects.   Once implemented, a Comparator can be passed to many built in Java Collection sorting methods to perform
	 * custom sorts.  For example, sorting a list of Users by their role, name, or other properties of a custom user object.   
	 * 
	 * The Comparator interface has a single method defined as:
	 * 
	 * 		public int compare(Object obj1, Object obj2)
	 * 
	 * Collections.sort() work by comparing two elements and asking "which is greater?", the method expects a return of 
	 * 		-1 	if it is NOT GREATER
	 *  	 0   if it is Equal
	 *  	 1   if it is GREATER
	 *  
	 *  So a comparator method should return these values.
	 *  
	 *  For each example, the Comparator is in a class in the com.techelevator.comparators package.  
	 *      check those classes for how to build the comparator implementations.  The examples in 
	 *      this class demonstrate how to use these Comparator Implementations Classes first with 
	 *      the LIST collection type and then with the MAP collection types.   Other Collections and
	 *      data structures also support comparators, however, the usage will be the same or very similar.
	 */
	
	// A DateTimeFormatter to create LocalDates of specific values - this is not part of Comparators, but 
	// is used to generate data for the examples.
	private final static DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	
	public static void main(String args[]) {
		
		/* ***********************
		 *  	LIST EXAMPLES
		 ************************* */
		
		// Great a List of User so we can use them with the comparator examples
		List<User> users = getUnsorttedUserList();
		// Print the User list to show the starting order - f you are unsure what this line is doing, see the Streams examples
		System.out.println("Unorted Users");
		users.forEach(System.out::println);
		
		// Use the SortByName comparator to sort the Users by Name
		users.sort(new SortByName());
		System.out.println("\nSorted By Name");
		users.forEach(System.out::println);
		
		// Use the SortByAge comparator to sort the Users by Age
		users.sort(new SortByAge());
		System.out.println("\nSorted By Age");
		users.forEach(System.out::println);
		
		/*
		 *  The Order of the Sort can be controlled by how the Comparator compares the value
		 *  For example, by reversing the value returns by the comparator we can return a reversed order
		 */
		// Use the SortByAgeDesc to sort the Users by Age Oldest to Youngest
		users.sort(new SortByAgeDesc());
		System.out.println("\nSorted By Age Desc");
		users.forEach(System.out::println);
		
		// Use the SortBySingupDate comparator to sort the Users by Singup Date
		users.sort(new SortBySignupDate());
		System.out.println("\nSorted By Signup Date");
		users.forEach(System.out::println);
		
		// Use the SortBySignupDateDesc comparator to sort the Users by Singup Date in with the latest first
		users.sort(new SortBySignupDateDesc());
		System.out.println("\nSorted By Signup Date Desc");
		users.forEach(System.out::println);
		
		/*
		 *  A comparator can be any custom sort for your object, and can include multiple fields.  
		 *  For example, it can be used to sort users by Name and then Age
		 */
		// Use the SortByNameAndAge comparator to sort the Users first by Name and then by Age
		users.sort(new SortByNameAndAge());
		System.out.println("\nSorted By Name and then Age");
		users.forEach(System.out::println);

		// Use the SortBySingupDateAscAndNameDesc comparator to sort the Users first by Signup Date ASC and then by Name DESC
		users.sort(new SortBySingupDateAscAndNameDesc());
		System.out.println("\nSorted By Signup Date and then Name Desc");
		users.forEach(System.out::println);
		
		/* ***********************
		 *  	MAP EXAMPLES
		 ************************* */
		/*
		 * Comparators can also be used to Sort more complex data structures like a Map
		 */
		Map<Integer, User> userMap = getUnsorttedUserMap();
		// Print the unsorted Map
		System.out.println("\nUnsorted User Map");
		Arrays.stream(userMap.entrySet().toArray()).forEach(System.out::println);
		
		/*
		 * The Original HashMap cannot be sorted, as HashMaps will not hold order, so we a map implementation
		 * is needed that does hold order and that takes a Comparator as a constructor argument.  TreeMap does both.
		 * 
		 * So a TreeMap will be created, passing the comparator to its constructor.  As each item is added to the
		 * TreeMap it will use the custom comparator to determine how it should be sorted in the map.
		 * 
		 * The original Map will then be passed to the constructor of the comparator, so it can access it to return
		 * the correct sort order from the original map.
		 */
		Map<Integer, User> sortedByName = new TreeMap<Integer, User>(new SortMapValuesByUserName(userMap));
		// Put the values of the original Map in the TreeMap, which will use the comparator to sort them
		sortedByName.putAll(userMap);
		
		System.out.println("\nSorted User Map by Name");
		Arrays.stream(sortedByName.entrySet().toArray()).forEach(System.out::println);
		
		/*
		 * If you check the sorted Map we lost a Key.  This is because when the two equal names (John) where found it
		 * combined the keys into one entry.  
		 * 
		 * A second comparator (SortMapByValuesByUserNameNoKeyLoss) tells the TreeMap how to sort keys will equal values, by
		 * always returning either 1 or -1.  
		 */
		Map<Integer, User> sortedByNameNoKeyLoss = new TreeMap<Integer, User>(new SortMapValuesByUserNameNoKeyLoss(userMap));
		sortedByNameNoKeyLoss.putAll(userMap);
		
		System.out.println("\nSorted User Map by Name with No Key Loss");
		Arrays.stream(sortedByNameNoKeyLoss.entrySet().toArray()).forEach(System.out::println);
		
		
		// Use the SortMapValuesByRoleAscAndAgeDesc comparator to sort the Map Values by User role ASC and then age DESC
		Map<Integer, User> sortedByRoleAndAge = new TreeMap<Integer, User>(new SortMapValuesByRoleAscAndAgeDesc(userMap));
		sortedByRoleAndAge.putAll(userMap);
		
		System.out.println("\nSorted User Map by Role ASC and Age DESC");
		Arrays.stream(sortedByRoleAndAge.entrySet().toArray()).forEach(System.out::println);
	}
	
	
	// The following private methods are used to generate example data for sorting, and are not part of the Comparators   
	private static List<User> getUnsorttedUserList() {
		List<User> users = new ArrayList<User>();
		users.add(new User("John", "user", 28, LocalDate.parse("2017-08-13", dateFormatter)));
		users.add(new User("Andrew", "user", 42, LocalDate.parse("2017-11-02", dateFormatter)));
		users.add(new User("Steve", "user", 37, LocalDate.parse("2017-04-11", dateFormatter)));
		users.add(new User("Rachelle", "admin", 25, LocalDate.parse("2019-01-25", dateFormatter)));
		users.add(new User("Katie", "admin", 28, LocalDate.parse("2017-04-11", dateFormatter)));
		users.add(new User("John", "admin", 47, LocalDate.parse("2017-04-11", dateFormatter)));
		return users;
	}
	
	private static Map<Integer, User> getUnsorttedUserMap() {
		Map<Integer, User>  userMap = new HashMap<Integer, User>();
		userMap.put(1, new User("John", "user", 28, LocalDate.parse("2017-08-13", dateFormatter)));
		userMap.put(2, new User("Andrew", "user", 42, LocalDate.parse("2017-11-02", dateFormatter)));
		userMap.put(3, new User("Steve", "user", 37, LocalDate.parse("2017-04-11", dateFormatter)));
		userMap.put(4, new User("Rachelle", "admin", 25, LocalDate.parse("2019-01-25", dateFormatter)));
		userMap.put(5, new User("Katie", "admin", 28, LocalDate.parse("2017-04-11", dateFormatter)));
		userMap.put(6, new User("John", "admin", 47, LocalDate.parse("2017-04-11", dateFormatter)));
		return userMap;
	}
	
}
