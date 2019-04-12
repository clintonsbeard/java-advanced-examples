package com.techelevator.visitor.interfaces;

/*
 * An interface that will identify our objects as visitable.   The visitable interface does not need to be named
 * visitable, but it must have a method that accepts the visitor as an argument that each of the visitable sub-classes
 * must implement. 
 */
public interface Visitable {
	void accept(ShippingCostVisitor visitor);
}
