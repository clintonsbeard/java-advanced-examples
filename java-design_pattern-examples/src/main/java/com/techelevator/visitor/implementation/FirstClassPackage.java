package com.techelevator.visitor.implementation;

import com.techelevator.visitor.interfaces.Package;
import com.techelevator.visitor.interfaces.ShippingCostVisitor;
import com.techelevator.visitor.interfaces.Visitable;

/*
 * The concrete implementation of each visitable type (in this case package types) must implement the 
 * Visitable interface and provide a method to accept and use the visitor.   The other methods (getPrice() and 
 * the constructor) and not required for the visitor pattern, these methods should be whatever methods the visitor 
 * will need to call when it is applied to this class.   
 * 
 * This class also extends the abstract Package class.  This is also not part of the visitor pattern, but it is 
 * part of the individual use case being used to demonstrate the pattern.
 */
public class FirstClassPackage extends Package implements Visitable  {

	/*
	 * The constructor takes the weight as the an argument and passes it to the abstract base class.  This is 
	 * not part of the visitor pattern, but needed for this use case.
	 */
	public FirstClassPackage(double weight) {
		setWeight(weight);
	}
	
	/*
	 * The accept method is required by the Visitor pattern.  It takes the visitor as an argument and then calls
	 * the visit method on the visitor and passes it a copy of itself for the algoritm to be applied.  Since the
	 * visit method on the visitor is overloaded to take different package types, it will automatically select the
	 * method that should be applied to this type of object.
	 */
	@Override
	public void accept(ShippingCostVisitor visitor) {
		visitor.visit(this);
	}

	/*
	 * This method is not part of the visitor pattern, but is needed for this use case for the visitor to calculate the 
	 * total shipping cost.  This logic, or part of it, could also be in the visitor.
	 */
	@Override
	public double getPrice() {
		double baseprice = 5.00D;
		
		if (getWeight() < 1) {
			return 2.00D;
		}
		
		if (getWeight() < 5) {
			return baseprice;
		}
		
		return baseprice * 2D;
	}


}
