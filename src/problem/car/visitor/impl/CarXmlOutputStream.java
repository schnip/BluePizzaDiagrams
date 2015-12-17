package problem.car.visitor.impl;

import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import problem.car.api.IBody;
import problem.car.api.ICar;
import problem.car.api.IEngine;
import problem.car.api.IWheel;
import problem.car.visitor.api.IVisitor;

public class CarXmlOutputStream extends FilterOutputStream implements IVisitor{

	public CarXmlOutputStream(OutputStream arg0) {
		super(arg0);
	}
	
	private void write(String s) {
		try {
			super.write(s.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void visit(ICar c) {
		String s = String.format("<car make='%s' model='%s' vin='%s'>\n", c.getMake(), c.getModel(), c.getVIN());
		write(s);
		
	}

	@Override
	public void visit(IBody b) {
		String s = String.format("<body type='%s' material='%s'>\n", b.getType(), b.getMaterial());
		write(s);
		
		
	}

	@Override
	public void visit(IEngine e) {
		String s = String.format("<engine cylinder=%d' capacity='%f'>\n", e.getCylinder(), e.getCapacity());
		write(s);
		
		
	}

	@Override
	public void visit(IWheel w) {		
		String s = String.format("<wheel vendor='%s' radius='%f' width='%f>\n", w.getVendor(), w.getRadius(), w.getWidth());
		write(s);
	}

	@Override
	public void postVisit(ICar c) {
		// TODO Auto-generated method stub
		String s = "</car>\n";
		write(s);
	}
	
	

}
