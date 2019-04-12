package com.techelevator;

import com.techelevator.factory.AnimalFactory;
import com.techelevator.shared.Animal;


/*
 * The factory pattern is used to create objects of the same parent type without 
 * exposing the logic of the creation to our calling class.  We always refer to the 
 * created object by the interface, which allows us to use it generically.  During the 
 * creation any configuration or setup that is needed to make the generated class work
 * are done by the factory.  In this simple example, there is no configuration just simple
 * classes with the the same makeSound() method.  
 * 
 * This is the most common use of the Factory pattern, but it can be made more complex.  
 * Imagine if we made an interface for the factory and then injected different factories, 
 * depending on our need using Dependency injection!  Now when we get our objects they could
 * have different behaviors, depending on the factory that created them.
 * 
 * WHY USE A FACTORY?
 * 
 * Object factories are used when we want to create objects without the calling code knowing anything about
 * how they are created.  A common use for a Factory is to create a database connection when multiple databases
 * are being used.  The Factory can get a standard connection connection is preconfigured for the database and
 * environment, so the calling code does not need to be aware or worry about the "how" of how the object is
 * configured.  For example, if a single product must make a connection to maybe a PostgreSQL databaes for logging,
 * an Oracle database to retrieve users account information, and a MS Sql Server database to retrieve client data, 
 * you can have a factory for the JDBCTemplate, where you can just call DatabaseFactory.getConnection("Oracle") when
 * you want to get user account information, without the code needing to worry about how the Oracle connection needs
 * to be configured.
 * 
 */
public class FactoryExample {
	

	public static void main(String[] args) {
		
		System.out.println("*** FACTORY PATTERN EXAMPLE ***");
		System.out.println();
		
		/*
		 *  Create a copy of the factory.  It is common for the Factory to be a singleton, but
		 *  to keep this to a single pattern, it is not done here.
		 */
		System.out.println("Get an Instance of the AnimalFactory");
		AnimalFactory animalFactory = new AnimalFactory();
		
		/*
		 * Now that we have a factory, let's make some animals.  We will do so by passing the
		 * factory an object name, in this case the name of the animals we want.  It will then
		 * create it for us ready for use.
		 * Note, that these object names could be made more consistent and easier to use using
		 * a static enum on the animalFactory.
		 */
		
		// Let's get a COW
		System.out.println("Now Create a few Animals, in this case a cow, pig, and chicken, and then call the makeSound() method on each");
		Animal animal = animalFactory.getAnimal("cow");
		
		// Call it's make sound method
		System.out.println("Animal one says: " + animal.makeSound());
	
		// Now let's get a pig from the factory
		Animal animal2 = animalFactory.getAnimal("pig");
		
		// Call it's make sound method
		System.out.println("Animal two says: " + animal2.makeSound());
		
		// Now let's get the chicken and call make sound all at once
		System.out.println("Animal three says: " + animalFactory.getAnimal("chicken").makeSound());
		
		
	}
}
