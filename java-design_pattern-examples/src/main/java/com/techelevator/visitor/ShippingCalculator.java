package com.techelevator.visitor;

import java.util.ArrayList;
import java.util.List;

import com.techelevator.visitor.interfaces.Visitable;
import com.techelevator.visitor.interfaces.ShippingCostVisitor;

/*
 * The Shipping calculator is the concrete class that will use the visitors. It must keep 
 * track of the items that the visitors need to be applied to, and have a method that applies
 * the visitor to each item in the list.
 */
public class ShippingCalculator {

	/*
	 * Create a list to track the types to which the visitor will be applied.  In this case it will be Packages.
	 */
	private List<Visitable> visitorTypes = new ArrayList<Visitable>();

	/*
	 * Create a method to add the items to the list.
	 */
	public void addVisitorType(Visitable visitor) {
		visitorTypes.add(visitor);
	}
	
	/*
	 * This method will apply the visitor to each item that has been added to the list.  In this case
	 * it takes the Visitor as an argument, however, the visitor could also be created here in the class, 
	 *  injected into a property using either a getter or the constructor using dependency injection.
	 */
	public double calculateShippingCost(ShippingCostVisitor shippingCartVisitor) {
		
		/*
		 * Got through each item in the list, and pass it the visitor so it will be applied to that item
		 */
		for (Visitable item : visitorTypes) {
			item.accept(shippingCartVisitor);
		}
		
		/*
		 * In this case, the visitor tracks the total cost as it is applied to each item in the list, so once we 
		 * have applied it to all items in the list call it's total method to get the total shipping cost.
		 */
		return shippingCartVisitor.getTotalShippingCost();
	}
	
}
