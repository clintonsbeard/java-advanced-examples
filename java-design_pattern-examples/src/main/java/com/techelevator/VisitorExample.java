package com.techelevator;

import com.techelevator.visitor.PreferredCustomerVisitor;
import com.techelevator.visitor.ShippingCalculator;
import com.techelevator.visitor.StandardShippingVisitor;
import com.techelevator.visitor.implementation.BulkPackage;
import com.techelevator.visitor.implementation.FirstClassPackage;
import com.techelevator.visitor.implementation.MediaPackage;
import com.techelevator.visitor.interfaces.ShippingCostVisitor;


/*
 * The visitor pattern allow full separation of data from an algorithm, by allowing the algorithm to be changed by
 * the calling class by using a visitor.  
 * 
 * WHEN TO USE VISITOR
 * Visitor should be used when we need a separation of the algorithm and the data, so we can make dynamic changes to 
 * an algorithm dependent on the current needs of the calling class.   For example, imagine that you have a shopping 
 * cart that must calculate the total differently depending on a classification of customer, for example a loyalty card holder, a regular shopper, 
 * a new shopper, and an elderly shopper may all have different adjustments to the price of the items in their cart.  You 
 * could achieve this by a series of if..thens, or you could separate the calculation of the total cost using the visitor 
 * pattern.  The calling class would then just supply the visitor that knows how to do the calculation for the current
 * customer type.  
 */
public class VisitorExample {

	/*
	 * Notice that this implementation of the visitor pattern separates the supporting classes into multiple packages
	 * to make it easier to follow.  All the package include visitor in their name, but unlike the other pattern examples
	 * you will need to look in multiple packages to see the code for the pattern.  
	 * 
	 * The packages being used are:
	 * 		com.techelevator.visitor
	 * 		com.techelevator.visitor.implementation
	 * 		com.techelevator.visitor.interfaces
	 */
	public static void main(String[] args) {

		System.out.println("*** VISITOR PATTERN EXAMPLE ***");
		System.out.println();
		
		System.out.println("Create an instance of the Shipping Calculator");
		ShippingCalculator shippingCalculator = new ShippingCalculator();
		
		System.out.println("Create an instance of visitor to calculate shipping for a standard customer");
		ShippingCostVisitor standardShippingVisitor = new StandardShippingVisitor();
		
		System.out.println();
		System.out.println("Add the packages to the Shipping Calculator, they track their weight,");
		System.out.println("but will use a visitor to calculate their shipping individual cost.");
		BulkPackage bulkPackage = new BulkPackage(12D);
		shippingCalculator.addVisitorType(bulkPackage);
		
		FirstClassPackage firstClassPackage = new FirstClassPackage(.75D);
		shippingCalculator.addVisitorType(firstClassPackage);
		
		MediaPackage mediaPackage = new MediaPackage(6D);
		shippingCalculator.addVisitorType(mediaPackage);
		
		System.out.println();
		System.out.println("Pass the calculator an instance of the standard shipping visitor, which knows how to calculate the current");
		System.out.println("shipping rate for each package type for a standard customer.  Finally, call the calculate shipping method to get the total cost.");
		double cost = shippingCalculator.calculateShippingCost(standardShippingVisitor);
		System.out.println("Total Shipping Cost: " + cost);
		
		System.out.println();
		System.out.println("Now Create an instance of visitor to calculate shipping for a PREFERED customer");
		ShippingCostVisitor preferredCustomerVisitor = new PreferredCustomerVisitor();
		
		System.out.println("The packages have alread been added to our ShippingCalculator, so we don't need to change it.");
		System.out.println();
		
		System.out.println("Call the ShippingCalculator calculateShippingCost() method again, this time passing the perferred customer visitor,");
		System.out.println("which knows how to calculate the currentshipping rate for each package type for a preferred customer.");
		System.out.println("Finally, call the calculate shipping method to get the total cost.");
		double preferredCost = shippingCalculator.calculateShippingCost(preferredCustomerVisitor);
		System.out.println("Total Preferred Shipping Cost: " + preferredCost);
	}
	
}
