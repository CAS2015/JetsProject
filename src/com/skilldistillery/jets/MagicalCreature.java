package com.skilldistillery.jets;

public class MagicalCreature extends FlyingObject implements Feedable {

	public MagicalCreature() {
		super();
	}

	public MagicalCreature(String model, double speed, int range, long price) {
		super();
		this.model = model;
		this.speed = Math.max(0, speed);
		this.range = Math.max(0, range);
		this.price = Math.max(0, price);
	}
	
	public void feed() {
		System.out.println("You feed the " + this.getModel() + " its favorite food. Yum!");
	}
}
