package com.skilldistillery.jets;

public class Abraxan extends MagicalCreature {
	private static String favFood = "single malt whiskey";
	
	public Abraxan() {
	}

	public Abraxan(String model, double speed, int range, long price, String handler) {
		super(model, speed, range, price, handler);
	}
	
	public void howFly() {
		System.out.println(this.getHandler() + " gives the " + this.getModel() + " a sip of whiskey to get it to fly.");
	}
	
	public void feed() {
		System.out.println("You give the " + this.getModel() + " some " + favFood + ". Yum!");
	}
}
