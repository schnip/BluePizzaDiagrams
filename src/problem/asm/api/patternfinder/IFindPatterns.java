package problem.asm.api.patternfinder;

import java.io.PrintWriter;
import java.util.Map;

import problem.asm.storage.MetaDataLibrary;

/**
 * Implement this interface to develop your own pattern finding algorithm!
 */
public interface IFindPatterns {
	
	/**
	 * Here is where you are given all the data about the classes for the purpose of analysis
	 * @param mdl
	 */
	public void intake(MetaDataLibrary mdl);
	
	/**
	 * Here you are given options that you can put into the program for pattern finding.  You can set up a number of options that let you modify the functionality
	 * of your program's algorithm if you wish.
	 * @param options
	 */
	public void intakeOptions(Map<String, String> options);
	
	/**
	 * Returns the name of the algorithm - this serves as an identifier for your algorithm for usage elsewhere.
	 * @return
	 */
	public String getName();
	
	/**
	 * Add any annotations you might wish to add to this class.  These will be written to the writer.
	 * @param className
	 * @param writer
	 */
	public void write(String className, PrintWriter writer);
	
	/**
	 * Write any attribute information about this class to the writer such as colors and style.
	 * @param className
	 * @param writer
	 */
	public void writeAttributes(String className, PrintWriter writer);
	
	/**
	 * Use this function if you wish to label any edges on your UML diagram.
	 * @param edgeDescription
	 * @param writer
	 */
	public void labelEdge(String edgeDescription, PrintWriter writer);
	
	/**
	 * Return the instances of the pattern for the purpose of finding individual instances and displaying them.
	 * @return
	 */
	public Iterable<IPatternInstance> getInstances();
	
}
