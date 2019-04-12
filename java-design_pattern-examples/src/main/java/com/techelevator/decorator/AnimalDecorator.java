package com.techelevator.decorator;

import com.techelevator.shared.Animal;

/*
 * Create an abstract Decorator class that implements the interface of the objects
 * we wish to decorate
 */
public abstract class AnimalDecorator implements Animal {
	
	/*
	 * Create a private variable to hold the decorated version of the object
	 */
	protected Animal decoratedAnimal;
	
	/*
	 * The constructor will take an instance of the object we wish to decorate and 
	 * save it as our decorated object
	 */
	public AnimalDecorator(Animal animal) {
		this.decoratedAnimal = animal;
	}
	
	/*
	 * Add a method for each method from the original objects interface we wish to decorate, and
	 * have it call the decorated version of the object with the same method name.
	 */
	public String makeSound() {
		return this.decoratedAnimal.makeSound();	
	}

}
