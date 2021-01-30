package com.skilldistillery.jets;

public abstract class FlyingObject {
	final static int SPEED_OF_SOUND_MPH = 767;
	protected String model;
	protected double speed;
	protected int range;
	protected long price;
	
	public void fly() {
		System.out.printf(this.toString() + ", %.2f hours flying time before it runs out of energy.\n", (range/speed));
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public double getSpeed() {
		return speed;
	}

	public void setSpeed(double speed) {
		this.speed = Math.max(0, speed);
	}

	public int getRange() {
		return range;
	}

	public void setRange(int range) {
		this.range = Math.max(0, range);
	}

	public long getPrice() {
		return price;
	}

	public void setPrice(long price) {
		this.price = Math.max(0, price);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((model == null) ? 0 : model.hashCode());
		result = prime * result + (int) (price ^ (price >>> 32));
		result = prime * result + range;
		long temp;
		temp = Double.doubleToLongBits(speed);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FlyingObject other = (FlyingObject) obj;
		if (model == null) {
			if (other.model != null)
				return false;
		} else if (!model.equals(other.model))
			return false;
		if (price != other.price)
			return false;
		if (range != other.range)
			return false;
		if (Double.doubleToLongBits(speed) != Double.doubleToLongBits(other.speed))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(model).append(": speed=").append(speed).append(" mph, range=")
				.append(range).append(" miles, price=").append(price).append(" galleons");
		return builder.toString();
	}

}
