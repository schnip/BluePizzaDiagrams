package problem.asm.patternfinder;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import problem.asm.storage.ClassVolume;
import problem.asm.storage.FieldPage;
import problem.asm.storage.MetaDataLibrary;
import problem.asm.storage.MethodBook;

public class DecoratorFinder implements IFindPatterns {

	private Map<String, String> classToSpecial = new HashMap<String, String>();
	private Map<String, String> edgeToLabel = new HashMap<String, String>();

	@Override
	public void intake(MetaDataLibrary mdl) {
		for (ClassVolume cv : mdl.getClassVolume()) {
			if (superInClassVolume(cv)) {
				if (hasInterfaceAsField(cv) || hasSuperClassAsField(cv)) {
					if (constructorTakesType(cv)) {
						
					}
				}
			}
		}

	}

	public boolean superInClassVolume(ClassVolume c) {
		if (!c.getSuperName().equals("java.lang.object")) {
			return true;
		}
		return false;
	}
	
	public boolean constructorTakesType(ClassVolume cv) {
		for (MethodBook mb : cv.getMethods()) {
			if (mb.getName().equals("<init>")) {
				List<String> li = mb.getArgTypes();
				for (String arg : li) {
					String comp = cv.getSuperName();
					String[] intcomp = cv.getInterfaces();
					if (arg.equals(comp) || arg.equals(intcomp)) {
						return true;
					}
				}
			}
		}
		return false;
	}
	
	public boolean hasSuperClassAsField(ClassVolume cv) {
		String comp = "";
		if (cv.getSuperName() != "java.lang.object"){
			comp = cv.getSuperName();
		}
		for (FieldPage fp : cv.getFields()) {
			if (fp.getValue().toString().equals(comp)) {
				return true;
			}
		}
		return false;
	}

	public boolean hasInterfaceAsField(ClassVolume cv) {
		String[] check = cv.getInterfaces();
		if (check.length == 0) return false;
		for (String c : check) {
			for (FieldPage fp : cv.getFields()) {
				if (fp.getValue().toString().equals(c)) {
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public String getName() {
		return "decorator";
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
			writer.print("fillcolor=green, style=filled,");
			writer.println();
		}
	}

	@Override
	public void labelEdge(String edgeDescription, PrintWriter writer) {
		if (edgeToLabel.keySet().contains(edgeDescription)) {
			writer.print(", label = \"" + edgeToLabel.get(edgeDescription) + "\"");
		}

	}

}
