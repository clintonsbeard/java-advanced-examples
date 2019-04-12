package com.techelevator.visitor.interfaces;

import com.techelevator.visitor.implementation.BulkPackage;
import com.techelevator.visitor.implementation.FirstClassPackage;
import com.techelevator.visitor.implementation.MediaPackage;

/*
 * The interface for the Visitor.  We use an interface so we can create multiple concrete visitor that can change
 * the algorithm, but treat them generically as a visitor.  This way the ShippingCalculator doesn't need to know 
 * what visitor is being applied.
 * 
 * A visit() method should be created for each visitable type, and take the sub-class version of each class as its
 * argument.  This will overload the method and allow it to call the correct version for each visitable object.
 */
public interface ShippingCostVisitor {
	public void visit(BulkPackage bulkPackage);
	public void visit(FirstClassPackage firstClassPackage);
	public void visit(MediaPackage mediaPackage);
	
	/*
	 * This method is not required for the visitor pattern, but is required for the use cases used for this
	 * demonstration.
	 */
	public double getTotalShippingCost();
}
