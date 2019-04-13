package com.techelevator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class StreamsExamples {

	/*
	 * These examples make use of Java 8 Lambda expressions.  If you are unfamiliar with Java 8
	 * Lambda expressions, then you should review the lambda-example project in this repository
	 * first.  
	 */
	
	/*
	 * The Java Streams API was added in Java 8.
	 * Streams are a way of processing Collections of data, by passing them through a
	 * sequence of Objects/Methods that are chained together to produce desired results.
	 * 
	 * Streams are not data structures, instead they take Collections, Arrays, or Input I/O
	 * as input to process those structures.
	 * 
	 * Streams do not change the original structure they take as input, instead they produce
	 * a new data structure that contain the results.
	 * 
	 * A chain of Stream operations chained together is called a Pipeline
	 * 
	 * Streams do not hold values, instead they pass them through one by one to the next operation in
	 * the pipeline as each is processed.   The only exception are Operations that require all of their values
	 * for what they are processing, such as sorted() and distinct(), which require the entire stream for
	 * their operation to be succesful.  In these cases, the stream is collected in the operation until the entire
	 * stream is processed and then it is streamed out 1 value at a time.   For example, sorted() collects all
	 * the values in the stream, sorts the values, and then continues the stream one value at a time through the
	 * pipeline.  
	 * 
	 * Streams are broken into 2 types of operations:
	 * 	1) Intermediate operations - take a stream as input, process the stream, and return
	 *     a stream as output.   
	 *  2) Terminal operations - take a stream as input, mark the end of the stream, and return
	 *     the results in a data structure.
	 *     
	 * Hence, Intermediate operations cannot be used without a Terminal operation, but must
	 * be in a pipeline with an additional operation until a Terminal operation is reached to end the
	 * stream.  Terminal operations can be used alone, but cannot be pipelined into other operations
	 * that require a stream as input, like the intermediate operations, but can be pipelined with
	 * other operations that take their output data structure as input, which can result in a 
	 * second stream.  
	 * 
	 * This is not as confusing as it sounds, in fact, once you know JavaScript streams will 
	 * feel natural. 
	 */
	
	public static void main(String[] args) {
		
		// Here is an Array and List of the same values to be used in the following examples
		Integer[] numbers = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
		List<Integer> numbersAsList = Arrays.asList(numbers);
		
		/*
		 * ForEach - Terminal Operation
		 * Takes a Stream as an input and iterates each item in it to produce a result
		 * forEach returns void, so it can only be used at the end of a chain
		 */
		// forEach with a List to print odd numbers in the list
		// .streams() get the stream from the List and passes it to .forEach
		// For each iteration of forEach the the value is put into num and then
		// passed by lambda expression to the anonymous function to process it.
		System.out.println("Print the odd numbers from a List with a forEach");
		numbersAsList.stream().forEach(num -> {
			if (num % 2 == 1) {
				System.out.println(num);
			}
		});
		
		// To get the Stream for an Array, the Arrays wrapper class must be used
		System.out.println("Print the even numbers from an Array with a forEach");
		Arrays.stream(numbers).forEach(num -> {
			if (num % 2 == 0) {
				System.out.println(num);
			}
		});
		
				// Core Java Code would do the same using a for statement
				System.out.println("Print the odd numbers from a List with a traditional for loop");
				for (Integer num : numbersAsList) {
					if (num % 2 == 1) {
						System.out.println(num);
					}
				}
		
		// Using ForEach to collect the results in a new List
		// Since forEach returns void, it cannot be chained to collect results, however, it can add
		// results to another value in scope.
		List<Integer> oddNumbers = new ArrayList<Integer>();
		numbersAsList.stream().forEach(num -> {
			if (num % 2 == 1) {
				oddNumbers.add(num);
			}
		});
		
		/* However, this only works with effective final declarations, so while we can add to a list
		   we can't access an int outside the scope of the anonymous method.   So this code won't 
		   work.   We will see later how to sum an array with streams.
		int sum = 0;
		Arrays.stream(numbers).forEach(num -> {
			sum += num
		});
		*/
		
		/*
		 * collect() - terminal operation
		 * The collect() operation aggregates the results of a steam (usually from an intermediate operation)
		 * into a final result.  collect() cannot effectively alone, but we will see it in the following examples
		 * of intermediate operations.
		 * We must pass the collect() method a Collector which tells it how to collect the final results
		 */	
		/*
		 * map() - Intermediate operation
		 * The map() method iterates the stream and maps that position in the stream to a new value
		 * map() returns a stream, so we must use a terminal operation like collect() to get the results.
		 */
		/* Explanation:
		 * 	.stream() returns the stream from the List
		 *  .map() iterates through the values and for each one multiplies it by 2, and returns a Stream
		 *  .collect() uses the Collectors.toList() to collect stream returned by map() into a new list
		 */
		List<Integer> numbersDoubled = numbersAsList.stream()
											.map(num -> num * 2)
											.collect(Collectors.toList());
		System.out.println(numbersDoubled);
		
		/*
		 * There are many Collectors.  The one above returns a list, but they can do things like group objects,
		 * build maps, average data, and many other things.  toList() is the most commonly used.
		 */
		
		
		/*
		 * count() - Terminal Operation
		 * Returns the count of items in the Stream as long
		 * Like collect it cannot be used alone, but will be shown in the next example.
		 */
		
		/* filter() - Intermediate Operation
		 * Filter() eliminates items from the stream based on criteria.  The criteria should be a boolean expression
		 * If the boolean expression is TRUE then it will be included, if it is FALSE then it will be eliminated. 
		 * It returns a Stream so a Terminal Operation must be used to produce a result
		 */
		
		 /*
		  * Using filter() and count() to count the number of even numbers in the Stream
		 */
		/* Explanation:
		 * Arrays.stream() gets the stream from the Array of numbers
		 * .filter() uses an anonymous function with a lambda to keep even numbers (TRUE) and
		 * 			 eliminated (FALSE) odd numbers from the stream
		 * .count() returns the count of the remaining items in the stream as a long
		 */
		long count = Arrays.stream(numbers).filter(num -> num % 2 == 0).count();
		System.out.println("There are " + count + " even numbers in the Array");
		
		// An Array of names of varying length for use in the following examples
		String[] names = { "Doug", "David", "Josh", "Andrew", "Dave", "Steve", "John", "Rachelle" };
		
		/* Use Filter and count to find the number of 4 letter names in the Array */
		long countOf4LetterNames = Arrays.stream(names)
										.filter(name -> name.length() == 4)
										.count();
		System.out.println("There are " + countOf4LetterNames + " names in the Array that are four letters");
		
		/* Use Filer and Collect to get a List of the 4 Letter names in the Array */
		List<String> fourLetterNames = Arrays.stream(names)
										.filter(name -> name.length() == 4)
										.collect(Collectors.toList());
		System.out.println("Four Letter names in the Array: " + fourLetterNames);
		
		/*
		 * distinct() - Intermediate Operation
		 * Returns on the unique elements of a Stream
		 * Returns a Stream, so a terminal operation is needed to get the results
		 */
		// A new Array with duplicate values
		String[] containsDuplicates = {"Hello", "Bye", "Hello", "Bye", "Hello", "Hello"};
		
		long countOfUniqueValue = Arrays.stream(containsDuplicates).distinct().count();
		System.out.println("There are " + countOfUniqueValue + " unique values in the Array");
		
		List<String> listOfUniqueValues = Arrays.stream(containsDuplicates).distinct().collect(Collectors.toList());
		System.out.println("Unique Values in Array " + listOfUniqueValues);
		

		/*
		 * Reduce() - Terminal Operation
		 * Reduces the elements of an array to a single result.  
		 * It takes 2 arguments, the first holds the output of the last iteration of reduce (the aggregate)
		 *                       the second is the current stream element, often including an anonymous function that
		 *                               gives direction on how to reduce the elements.
		 */
		
		/* Finding the sum of all elements in an Array/List */
		int sum = numbersAsList.stream().reduce(0, (x, y) -> x + y);
		System.out.println("Sum of values in the Array: " + sum);
		
		/* Find the longest name in the names Array with Reduce */
		String name = Arrays.stream(names).reduce("", (name1, name2) -> name1.length() > name2.length() ? name1 : name2);
		System.out.println("The Longest name is " + name);
		
		/*
		 * In the above case we give reduce a starting option, "", so it can always return a valid string, but what if we don't
		 * want a starting option?  Or want to know if no longest was found, like in the case of any empty array or one where
		 * all the names are the same length.   A better solution would be to use the Optional<T> container object.  Optional
		 * allows the stream to return no value if no longest is found rather than our default.  This can let us know that
		 * the longest name wasn't our default, but instead was not found.
		 * 
		 * Example using Optional<T>
		 */
		Optional<String> optionalName = Arrays.stream(names).reduce((name1, name2) -> name1.length() > name2.length() ? name1 : name2);
		/*
		 * To get a value from Optional we must use a method to retrieve it, there are a few common methods
		 * optionalName.isPresent() returns TRUE/FALSE is a value is present in the Optional container
		 * optionalName.get() returns the value of the Optional container (in this case the longest name)
		 * optionalName.orElse() returns the value of the Optional container, or if none exists a given default
		 * 
		 * In the below example, if a longest name is found then it is printed, if no longest name was found (e.g. the Optional
		 * container is empty), then print "Longest Not Found"
		 */
		System.out.println("The Longest optional name is " + optionalName.orElse("Longest Not Found"));
			
		/*
		 * Optional<T>
		 * Since we only give 1 argument to reduce (the anonymous function) it may not be able to return a value
		 * if all the values in the array are the same length, or 
		 */
		
		/*
		 * limit() Intermediate Operation
		 * Limits the number of values to be processed in the Stream
		 */
		
		/* Use reduce() and limit() to get the sum of the first 3 numbers in an Array. 
		 * If the array is empty, the starting value, 0, will be returned
		 * If the array contains < 3 values then the sum of what exists will be returned
		 * If the array contains >= 3 values, then the sum of the first 3 will be returned
		 */
		int sumOfFirst3 = numbersAsList.stream().limit(3).reduce(0, (x, y) -> x + y);
		System.out.println("Sum of first 3 values in the array = " + sumOfFirst3);
		
		/* sorted() Intermediate Operation 
		 * Sorts the values in the stream
		 */
		Integer[] unsortedInts = {5, 2, 7, 1, 3 };
		System.out.println("Unsorted: " + unsortedInts);
		List<Integer> sortedInts = Arrays.stream(unsortedInts).sorted().collect(Collectors.toList());
		System.out.println("Sorted: " + sortedInts);
		
		List<String> namesAsList = Arrays.asList(names);
		System.out.println("Unsorted: " + namesAsList);
		List<String> sortedNames = namesAsList.stream().sorted().collect(Collectors.toList());
		System.out.println("Sorted: " + sortedNames);
		

		/*
		 * mapToInt() - Intermediate Operator
		 * Maps the values in a stream to an int.   
		 * Effectively casts each value of the stream to an int before processing
		 */
		String[] numbersAsStrings = { "1", "2", "3", "4", "5" };
		int sumOfStringNumbers = Arrays.stream(numbersAsStrings)
										.mapToInt(num -> Integer.parseInt(num))
										.reduce(0, (x, y) -> x + y);
		System.out.println("Sum of string numbers = " + sumOfStringNumbers);
		
		/* Get the sum of the length of the names in the names List */
		int sumOfNameLengths = namesAsList.stream()
									.mapToInt((nextName) -> nextName.length())
									.reduce(0, (x, y) -> x + y);
		System.out.println("Sum of the length of names in the list = " + sumOfNameLengths);
		
		
		/* Use filter, mapToInt, and Reduce to sum a Array of numeric Strings that has empty strings */
		String[] numAsStrWithEmptyStrings = { "1", "", "2", "", "3", "", "4", "5" };
		int sumOfArrayWithEmptyStrings = Arrays.stream(numAsStrWithEmptyStrings)
													.filter((str) -> str.length() > 0)
													.mapToInt(num -> Integer.parseInt(num))
													.reduce(0, (x, y) -> x + y);
		System.out.println("Sum of numeric strings in a list with empty strings = " + sumOfArrayWithEmptyStrings);
		
		/*
		 * Some important things to keep in mind when building a Stream Pipeline. 
		 * 		1. Order matters.   
		 * 		2. Remember values are streamed one value at a time and no state is saved, except for 
		 *         the few Operations, like sorted(), that must collect the entire stream before continuing
		 *      3. There are many other Stream operations and many more advanced usage for the ones that are shown here.
		 *         Many operations can take an lambda expression to change how they will process the stream.  This is
		 *         meant to be an introduction to Streams and Pipelines and not a comprehensive guide. 
		 *      4. Remember that Intermediate Operators always require a Terminal Operator to end the Stream Pipeline.
		 *      5. Terminal Operators may or may not return a value, and the values they return are different for each
		 *         Terminal Operator.
		 *      6. Stream Operations NEVER affect the original Collection/Array, instead they produce a new output value or stream
		 *      7. The examples here use List and Array, however, all Collection Types (Map, Set, Stack, Queue, etc.),
		 *         and many other object types can be used with a Stream.  
		 */
		
	}
}
