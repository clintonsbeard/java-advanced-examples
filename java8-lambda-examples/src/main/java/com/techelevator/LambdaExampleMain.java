package com.techelevator;

import java.util.Arrays;

import com.techelevator.lambda.interfaces.CalculatorInterface;
import com.techelevator.lambda.interfaces.FunctionalInterface;
import com.techelevator.lambda.interfaces.PrintFormattedValues;
import com.techelevator.lambda.interfaces.StringFunctionInterface;


/*
 * Lambda's where introduced in Java 8
 * 
 * Lambda expressions basically express instances of functional interfaces 
 * An interface with single abstract method is called functional interface. 
 * 
 * lambda expressions implement the only abstract function and therefore implement functional interfaces
 * lambda expressions were added in Java 8 and provide below functionalities.
 * 			1) Enable to treat functionality as a method argument, or code as data.
 * 			2) A function that can be created without belonging to any class.
 * 			3) A lambda expression can be passed around as if it was an object and executed on demand.
 * 
 * Syntax of a lambda expression
 * 		FunctionalInterface (n) -> n*x;
 * 		FunctionalInterface (n) -> { code block processing n };
 * 
 * 		Where (n) is the value to be passed to the implementation. 
 * 		Any number of variables can be passed:  (n, x, y) -> { n + x + y; };
 *      n*x is a simple single line of code doing something to n
 *      { code block processing n } is a block of code that does something to n
 *      
 * 		Lambda's are passed using the "thin arrow"  ->
 * 
 * 		When executed the value in the variable(s) (n) being passed as arguments to the code after the ->
 * 
 */

/*
 * Lambda Examples
 */
public class LambdaExampleMain {


	public static void main(String[] args) {
		
		/*
		 * A Simple example
		 */
		System.out.println("Multiplied by 2");
		FunctionalInterface func = (int x) -> System.out.println(2 * x);
		func.abstractMethod(5);
		
		/*
		 * Allows the Assignment of a more complex operation to be used more easily without repetition
		 */
		System.out.println("Formatted Printing");
		PrintFormattedValues formattedPrinter = (String x1, String x2, String x3) -> System.out.println("The " + x1 + " red " + x2 + " jumped over the " + x3);
		formattedPrinter.print("quick", "fox", "fence");
		formattedPrinter.print("slow", "turtle", "snail");
		
		/*
		 * The implementation instantiated with Lambda expressions can be passed to a method as an argument
		 */
		System.out.println("Passed to a function");
		passedToFunction(10, func);
		
		/*
		 * Can be used with the Java 8 Streams
		 */
		System.out.println("Print all the values in the Array:");
		int[] intArray = {10, 6, 7, 9, 8, 9};
		Arrays.stream(intArray).forEach(i -> System.out.println(i));
		
		/*
		 * Or built as abstract methods with use with Collection Streams (also introduced in Java 8)
		 */
		System.out.println("Just the Even values:");
		Arrays.stream(intArray).forEach(i ->  { if (i % 2 == 0) System.out.println(i);; });
		
		/*
		 * The same interface can be used with different implementations
		 */
		System.out.println("Calculator Operations:");
		CalculatorInterface add = (int x, int y) -> x + y;
		CalculatorInterface multiply = (int x, int y) -> x * y;
		CalculatorInterface subtract = (int x, int y) -> x - y;
		CalculatorInterface divide = (int x, int y) -> x / y;
		
		System.out.println(add.operation(5, 2));
		System.out.println(multiply.operation(5, 2));
		System.out.println(subtract.operation(5, 2));
		System.out.println(divide.operation(5, 2));
		
		/*
		 * Lambda's can also be created using Block syntax
		 */
		StringFunctionInterface reverse = (str) -> {
			String reversed = "";
			for (int i = str.length() - 1 ; i>=0 ; i--) {
				reversed += str.charAt(i);
			}
			return reversed;
		};
		
		/*
		 * Multiple lambda implementations can be created for each
		 * interface.
		 */
		StringFunctionInterface upperFirst = (str) -> {
			String result = "";
			result = str.substring(0, 1).toUpperCase() + str.substring(1).toLowerCase();
			return result;
		};
		
		/*
		 * The different functions can be passed to another function to 
		 * change it's behavior.  In this case the manipulateString function
		 * will do the behavior passed for the StringFunctionInterface lambda 
		 * implementation
		 */
		System.out.println("Revese the String");
		manipulateString("Hello", reverse);
		
		System.out.println("Uppercase First Letter Only");
		manipulateString("heLlO", upperFirst);
		
	}
	
	private static void manipulateString(String str, StringFunctionInterface func) {
		System.out.println(func.process(str));
	}
	
	private static void passedToFunction(int x, FunctionalInterface func) {
		func.abstractMethod(x);
	}

}
