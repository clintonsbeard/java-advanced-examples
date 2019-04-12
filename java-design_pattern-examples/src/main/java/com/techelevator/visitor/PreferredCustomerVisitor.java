package com.techelevator.visitor;

import com.techelevator.visitor.implementation.BulkPackage;
import com.techelevator.visitor.implementation.FirstClassPackage;
import com.techelevator.visitor.implementation.MediaPackage;
import com.techelevator.visitor.interfaces.ShippingCostVisitor;

/*
 * The concrete Visitor class knows how to apply the changes to the algorithm for each of the item types.  
 * If you wanted different Visitors to apply to change the algorithm depending on the data or certain conditions, 
 * then you would make an instance of this class for each change to the algorithm.  You would then create and pass
 * the one you want to apply as the Visitor to the concrete calculator class.
 * 
 * This is one of two visitors for this demonstration.  This one changes the algorithm to calculate lower shipping rates
 * for a preferred customer.
 */
public class PreferredCustomerVisitor implements ShippingCostVisitor {

	/*
	 * Create a private variable to hold our total cost as the visitor is applied to each item
	 */
	private double totalShippingCost = 0D;
	
	/*
	 * Create a method for each item type that the visitor can be applied to.  Notice, that even though these
	 * classes implement Visitable, we use their actual class names here instead of the interface name.  This is 
	 * because we don't need the interface method, instead we need the proper method to be applied depending on 
	 * what the class type is of the each package type.  
	 * 
	 * This takes advantage of method overloading to make sure the proper algorithm is applied for each visitable class
	 * type.  As each item is passed to this visitor it will call the method that has that type as it's argument.
	 */
	@Override
	public void visit(BulkPackage bulkPackage) {
		this.totalShippingCost += bulkPackage.getPrice() * .75D;
		
	}

	@Override
	public void visit(FirstClassPackage firstClassPackage) {
		this.totalShippingCost += firstClassPackage.getPrice() * .75D;
		
	}

	@Override
	public void visit(MediaPackage mediaPackage) {
		this.totalShippingCost += mediaPackage.getPrice() / 2;
		
	}
	
	/*
	 * Override the get shipping cost method so our calculator can access it.  This is not part of the Visitor pattern, but is needed for the use case in the demonstration.
	 */
	@Override
	public double getTotalShippingCost() {
		return this.totalShippingCost;
	}

}

