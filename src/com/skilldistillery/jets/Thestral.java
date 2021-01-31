package com.skilldistillery.jets;

public class Thestral extends MagicalCreature {
	private static String favFood = "raw steak";
	
	public Thestral() {
	}

	public Thestral(String model, double speed, int range, long price, String handler) {
		super(model, speed, range, price, handler);
	}
	
	public void howFly() {
		System.out.println("You can only see the " + this.getModel() + " if you've seen death. " + getHandler() + " jumps on and flies it.");
	}
	
	public void feed() {
		System.out.println("You feed the " + this.getModel() + " a " + favFood + ". Yum!");
	}
}
