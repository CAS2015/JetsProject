package com.skilldistillery.jets;

public abstract class FlyingObject {
	final static int SPEED_OF_SOUND_MPH = 767;
	protected String model;
	protected double speed;
	protected int range;
	protected long price;
	protected String handler;
	
	public void fly() {
		howFly();
		System.out.printf(this.toString() + ". \n%.2f hours flying time before it runs out of energy.\n", (range/speed));
		System.out.println();
	}
	
	public abstract void howFly();
	
	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getHandler() {
		return handler;
	}

	public void setHandler() {
		String[] handlerList = {"Ron Weasley", "Luna Lovegood", "Ginny Weasley", "Fred Weasley", "George Weasley", "Harry Potter", "Severus Snape",
							"Dobby", "Hermione Granger", "Madam Maxime", "Albus Dumbledore", "Hagrid", "Draco Malfoy", "Sirius Black", "Minerva McGonagall",
							"Remus Lupin", "Nymphadora Tonks", "Cho Chang" };
		
		this.handler = handlerList[(int)(Math.random()* handlerList.length)];
	}

	public double getSpeed() {
		return speed;
	}

	public void setSpeed(double speed) {
		this.speed = Math.max(0, speed);
	}
	
	public double getSpeedMach() {
		double tempVar = Math.round(speed/SPEED_OF_SOUND_MPH*1000);
		return tempVar/1000;
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
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(model).append(": speed=").append(speed).append(" mph (").append(this.getSpeedMach()).append(" mach), range=")
				.append(range).append(" miles, price=").append(price).append(" galleons, handler=").append(this.getHandler());
		return builder.toString();
	}

}
