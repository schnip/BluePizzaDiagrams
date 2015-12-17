package problem.car.api;

import java.util.Collection;

import problem.car.visitor.api.ITraverser;

public interface ICar extends ITraverser{
	public String getVIN();
	public String getMake();
	public String getModel();
	public Collection<ICarPart> getParts(); 
}
