package com.skilldistillery.jets;

public class Broom extends FlyingObject implements Quidditchable {
	
	public Broom() {
		super();
	}

	public Broom(String model, double speed, int range, long price) {
		super();
		this.model = model;
		this.speed = Math.max(0, speed);
		this.range = Math.max(0, range);
		this.price = Math.max(0, price);
	}
	
	public void playQuidditch() {
		System.out.println("You hop on your " + this.getModel() + " and kick the ground to take off towards the quidditch field.");
	}
}
