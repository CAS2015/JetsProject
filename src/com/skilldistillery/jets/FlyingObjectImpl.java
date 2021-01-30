package com.skilldistillery.jets;

public class FlyingObjectImpl extends FlyingObject {

	public FlyingObjectImpl() {
		super();
	}

	public FlyingObjectImpl(String model, double speed, int range, long price) {
		super();
		this.model = model;
		this.speed = Math.max(0, speed);
		this.range = Math.max(0, range);
		this.price = Math.max(0, price);
	}
}