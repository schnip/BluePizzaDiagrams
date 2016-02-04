package problem.asm.impl.patternfinder;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import problem.asm.api.patternfinder.IFindPatterns;
import problem.asm.storage.ClassVolume;
import problem.asm.storage.FieldPage;
import problem.asm.storage.MetaDataLibrary;
import problem.asm.storage.MethodBook;
import problem.asm.storage.StU;

public class CompositeFinder implements IFindPatterns {
	
	private Map<String, String> classToSpecial = new HashMap<String, String>();
	private MetaDataLibrary mdl;

	@Override
	public void intake(MetaDataLibrary mdl) {
		this.mdl = mdl;
		// Find the components
		for (ClassVolume cv : mdl.getClassVolume()) {
			if (checkForTwoSelfMethods(cv) && checkForContainsSelfCollection(cv)) {
				classToSpecial.putIfAbsent(cv.getName(), "component");
			}
		}
		
		// Find the composites
		for (ClassVolume cv : mdl.getClassVolume()) {
			for (String potentialComponent : classToSpecial.keySet()) {
				if (isExtension(cv, potentialComponent))
					if (hasTwoMethodsTaking(cv, potentialComponent))
						if (hasCollectionOf(cv, potentialComponent))
							classToSpecial.putIfAbsent(cv.getName(), "composite");
			}
		}
		
		// Find the leaves
		for (ClassVolume cv : mdl.getClassVolume()) {
			for (String potentialComponent : classToSpecial.keySet()) {
				if (isExtension(cv, potentialComponent))
					classToSpecial.putIfAbsent(cv.getName(), "leaf");
			}
		}
	}

	private boolean hasCollectionOf(ClassVolume cv, String potentialComponent) {
		int count = 0;
		for (FieldPage fp : cv.getFields()) {
			if (fp.getSignature() != null)
				if (StU.ehhEquals(StU.parseStringForT(fp.getSignature()), potentialComponent))
					count++;
		}
		return count >= 1;
	}

	private boolean hasTwoMethodsTaking(ClassVolume cv, String potentialComponent) {
		int count = 0;
		for (MethodBook mb : cv.getMethods()) {
			if (StU.ehhEquals(potentialComponent, mb.getArgTypes().get(0)) && mb.getArgTypes().size() == 1)
				count++;
		}
		return count >= 2;
	}

	private boolean isExtension(ClassVolume cv, String potentialComponent) {
		if (StU.ehhEquals(cv.getSuperName(), potentialComponent))
			return true;
		if (mdl.contains(cv.getSuperName()))
			return isExtension(mdl.getClassByString(cv.getSuperName()), potentialComponent);
		return false;
	}

	private boolean checkForContainsSelfCollection(ClassVolume cv) {
		return hasCollectionOf(cv, cv.getName());
	}

	private boolean checkForTwoSelfMethods(ClassVolume cv) {
		return hasTwoMethodsTaking(cv, cv.getName());
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
