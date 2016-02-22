package problem.asm.api.patternfinder;

import java.io.PrintWriter;
import java.util.Map;

import problem.asm.storage.MetaDataLibrary;

public interface IFindPatterns {

	public void intake(MetaDataLibrary mdl);
	
	public void intakeOptions(Map<String, String> options);
	
	public String getName();
	
	// Will be given the className with '.' between the parts
	public void write(String className, PrintWriter writer);
	
	public void writeAttributes(String className, PrintWriter writer);
	
	public void labelEdge(String edgeDescription, PrintWriter writer);
	
	public Iterable<IPatternInstance> getInstances();
	
}
