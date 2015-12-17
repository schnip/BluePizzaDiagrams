package problem.car.visitor.api;

import problem.car.api.IBody;
import problem.car.api.ICar;
import problem.car.api.IEngine;
import problem.car.api.IWheel;

public interface IVisitor {

	void visit(ICar c);
	
	void postVisit(ICar c);

	void visit(IBody b);

	void visit(IEngine e);

	void visit(IWheel w);

}
