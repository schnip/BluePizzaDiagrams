package problem.car.impl;

import problem.car.api.IWheel;
import problem.car.visitor.api.IVisitor;

public class Wheel implements IWheel {
	private String vendor;
	private float radius;
	private float width;
	
	public Wheel(String vendor, float radius, float width) {
		this.vendor = vendor;
		this.radius = radius;
		this.width = width;
	}

	@Override
	public String getVendor() {
		return vendor;
	}

	@Override
	public float getRadius() {
		return radius;
	}

	@Override
	public float getWidth() {
		return width;
	}

	@Override
	public String toString() {
		return "Wheel [vendor=" + vendor + ", radius=" + radius + ", width=" + width + "]";
	}

	@Override
	public void accept(IVisitor v) {
		// TODO Auto-generated method stub
		v.visit(this);
	}
}
