package com.techelevator.factory;

import com.techelevator.shared.Animal;
import com.techelevator.shared.ChickenAnimal;
import com.techelevator.shared.CowAnimal;
import com.techelevator.shared.PigAnimal;

public class AnimalFactory {

	/*
	 * The Animal factory will return an object of the requested type preconfigured for use.
	 */
	public Animal getAnimal(String animalType) {
		if (animalType == null) {
			return null;
		}
		
		if (animalType.equalsIgnoreCase("COW")) {
			return new CowAnimal();
		}
		
		if (animalType.equalsIgnoreCase("PIG")) {
			return new PigAnimal();
		}
		
		if (animalType.equalsIgnoreCase("CHICKEN")) {
			return new ChickenAnimal();
		}
		
		return null;
	}
}
