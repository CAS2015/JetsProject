package com.skilldistillery.jets;

public class PassengerVehicle extends FlyingObject implements Loadable {
	
	public PassengerVehicle() {
		super();
	}

	public PassengerVehicle(String model, double speed, int range, long price, String handler) {
		super();
		this.model = model;
		this.speed = Math.max(0, speed);
		this.range = Math.max(0, range);
		this.price = Math.max(0, price);
		this.handler = handler;
	}
	
	public void howFly() {
		System.out.println( this.getHandler() + " waits for everyone to get settled, then the " + this.getModel() + " takes off.");
	}
	
	public void loadVehicle() {
		System.out.println("You all clamber into the " + this.getModel() + " and wait to go.");
	}
}
