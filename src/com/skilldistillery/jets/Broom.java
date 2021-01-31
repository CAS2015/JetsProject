package com.skilldistillery.jets;

public class Broom extends FlyingObject implements Quidditchable {
	
	public Broom() {
		super();
	}

	public Broom(String model, double speed, int range, long price, String handler) {
		super();
		this.model = model;
		this.speed = Math.max(0, speed);
		this.range = Math.max(0, range);
		this.price = Math.max(0, price);
		this.handler = handler;
	}
	
	public void howFly() {
		System.out.println( this.getHandler() + " hops on the " + this.getModel() + " and kicks off the ground.");
	}
	
	public void playQuidditch() {
		System.out.println(this.getHandler() + " hops on their " + this.getModel() + " and kicks the ground to take off towards the quidditch field.");
	}
}
