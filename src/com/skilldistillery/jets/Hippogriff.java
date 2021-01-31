package com.skilldistillery.jets;

public class Hippogriff extends MagicalCreature {
	private static String favFood = "ferret";
	
	public Hippogriff() {
		super();
	}
	
	public Hippogriff(String model, double speed, int range, long price, String handler) {
		super(model, speed, range, price, handler);

	}
	
	public void howFly() {
		System.out.println(this.getHandler() + " bows before the " + this.getModel() + " and then hops on its back to fly.");
	}
	
	public void feed() {
		System.out.println("You feed the " + this.getModel() + " a " + favFood + ". Yum!");
	}


}
