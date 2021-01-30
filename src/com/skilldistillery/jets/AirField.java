package com.skilldistillery.jets;

import java.util.ArrayList;
import java.util.List;

public class AirField {
	private List<FlyingObject> flyingObjects;

	public AirField() {
		super();
		flyingObjects = new ArrayList<>();
	}

	public AirField(List<FlyingObject> flyingObjects) {
		super();
		this.flyingObjects = flyingObjects;
	}

	public List<FlyingObject> getFlyingObjects() {
		List<FlyingObject> copyList = new ArrayList<>();
		for ( int i = 0; i < flyingObjects.size(); i++) {
			copyList.add(flyingObjects.get(i));
		}
		return copyList;
	}

	public void setFlyingObjects(List<FlyingObject> flyingObjects) {
		this.flyingObjects = flyingObjects;
	}
	
	public void addFlyingObject(FlyingObject fo) {
		flyingObjects.add(fo);
	}
	
	public String removeFlyingObjectByIndex(int i) {
		return flyingObjects.remove(i).getModel();

	}
	
	public boolean removeFlyingObjectByModel(FlyingObject fo) {
		return flyingObjects.remove(fo);
	}
	
	public FlyingObject getFastestObject() {
		double max = 0;
		FlyingObject fastest = null;
		for (FlyingObject flyingObject : flyingObjects) {
			if (flyingObject != null) {
				if (flyingObject.getSpeed() >= max) {
					fastest = flyingObject;
					max = flyingObject.getSpeed();
				}
			}
		}
		return fastest;
	}
	
	public FlyingObject getBiggestRangeObject() {
		double max = 0;
		FlyingObject longestRange = null;
		for (FlyingObject flyingObject : flyingObjects) {
			if (flyingObject != null) {
				if (flyingObject.getRange() >= max) {
					longestRange = flyingObject;
					max = flyingObject.getRange();
				}
			}
		}
		return longestRange;
		
	}

	
}
