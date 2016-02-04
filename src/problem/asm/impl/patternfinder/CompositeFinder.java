package problem.asm.impl.patternfinder;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import problem.asm.api.patternfinder.IFindPatterns;
import problem.asm.storage.ClassVolume;
import problem.asm.storage.MetaDataLibrary;

public class CompositeFinder implements IFindPatterns {
	
	private Map<String, String> classToSpecial = new HashMap<String, String>();

	@Override
	public void intake(MetaDataLibrary mdl) {
		// Find the components
		for (ClassVolume cv : mdl.getClassVolume()) {
			if (checkForTwoSelfMethods(cv) && checkForContainsSelfCollection(cv)) {
				classToSpecial.putIfAbsent(cv.getName(), "component");
			}
		}
	}

	private boolean checkForContainsSelfCollection(ClassVolume cv) {
		// TODO Auto-generated method stub
		return false;
	}

	private boolean checkForTwoSelfMethods(ClassVolume cv) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getName() {
		return "composite";
	}

	@Override
	public void write(String className, PrintWriter writer) {
		if (classToSpecial.keySet().contains(className)) {
			writer.println();
			writer.print("\\<\\<" + classToSpecial.get(className) + "\\>\\>\\l");
		}
	}

	@Override
	public void writeAttributes(String className, PrintWriter writer) {
		if (classToSpecial.keySet().contains(className)) {
			writer.print("fillcolor=yellow, style=filled,");
			writer.println();
		}
	}

	@Override
	public void labelEdge(String edgeDescription, PrintWriter writer) {
		// No edges need to be labeled

	}

}
