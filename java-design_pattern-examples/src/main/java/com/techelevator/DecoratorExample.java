package com.techelevator;

import com.techelevator.shared.Animal;
import com.techelevator.decorator.BrownAnimalDecorator;
import com.techelevator.decorator.RedAnimalDecorator;
import com.techelevator.shared.CatAnimal;
import com.techelevator.shared.DogAnimal;

public class DecoratorExample {

	/*
	 * The Decorator pattern allows the use to add new functionality to an existing object without
	 * altering the original object.  The decorator "wraps" the original class and overrides it's functionality
	 * with methods that provide additional functionality.  
	 * 
	 * WHEN TO USE DECORATOR
	 * 
	 * Decorator can be used when you need to add functionality to a class you are working with where it has different
	 * conditional functionality or when you don't have access to change the existing code.  For example, imagine you had
	 * an application that wanted to provided different scroll bar to one of it's windows based on user settings.  You could
	 * add a complex set of if-statements to the window class to build each style, or you could add decorators for each
	 * style so you don't have to modify the original class.   Another example, would be imagine you have an object from 
	 * a library that creates a Rectangle in black, but you want to create Blue rectangle.  You can create a Decorator
	 * for the libraries rectangle class so it will generate it in Blue rather than Black. 
	 */
	
	public static void main(String[] args) {
		
		System.out.println("*** DECORATOR PATTERN EXAMPLE ***");
		System.out.println();
		
		System.out.println("Create a regular Dog and Cat Animal object");
		// Create a Cat and a Dog
		Animal normalCat = new CatAnimal();
		Animal normalDog = new DogAnimal();
		
		System.out.println("Create a new cat object and decorate with a BrownAnimalDecorator");
		// Get a new Cat decorated with Brown
		Animal brownCat = new BrownAnimalDecorator(new CatAnimal());
		System.out.println("No decorate the exisitng regular Dog object with a RedAnimalDecorator");
		// Decorate our normal Dog with Red
		Animal redDog = new RedAnimalDecorator(normalDog);
		
		System.out.println();
		System.out.println("Here is the result of each of the objects:");
		// Call the makeSound methods on the normal objects
		System.out.println("The normal cat says: " + normalCat.makeSound());
		System.out.println("The normal dog says: " + normalDog.makeSound());
		
		// Call the makeSound methods on the decorated objects
		System.out.println("The decorated cat says: " + brownCat.makeSound());
		System.out.println("The decorated dog says: " + redDog.makeSound());
	}

	
	
}
