package com.techelevator.observer;

public class GreenColorObserver extends ColorObserver {

	/*
	 * The constructor needs to take the instance of the class we are observing as an argument.  It will then
	 * save it to the protected variable we created in the abstract, and then call the original classes register()
	 * method to add itself to the list of registered observers to be notified.
	 */
	public GreenColorObserver(ColorChanger colorChanger) {
		this.colorChanger = colorChanger;
		this.colorChanger.register(this);
	}
	
	/*
	 * Override the method from our abstract observer that will be called when there is a change to the original
	 * class.  This method then take whatever action we need to take when the state of the object we are watching
	 * changed.
	 */
	@Override
	public void update() {
		String msg = "The GreenColorObserver says: ";
		if (this.colorChanger.getColor().equalsIgnoreCase("GREEN")) {
			msg += "The Color in the ColorChanger was changed to Green";
		} else {
			msg += "The Color in the ColorChanger is NOT Green";
		}	
		System.out.println(msg);
	}

}
