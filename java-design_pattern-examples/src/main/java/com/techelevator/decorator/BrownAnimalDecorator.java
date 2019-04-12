package com.techelevator.decorator;

import com.techelevator.shared.Animal;

/*
 * To create the concrete decorator create a new class and extend the decorator Abstract class
 */
public class BrownAnimalDecorator extends AnimalDecorator {

	/*
	 * Create a constructor that takes an instance of the original object and passes it to the
	 * abstract class's constructor.  We can do this using super()
	 */
	public BrownAnimalDecorator(Animal animal) {
		super(animal);
	}
	
	/*
	 * Create version of each method you wish to change that functionality with the new functionality
	 */
	public String makeSound() {
		return this.decoratedAnimal.makeSound() + " means pet my brown fur!";	
	}
}
