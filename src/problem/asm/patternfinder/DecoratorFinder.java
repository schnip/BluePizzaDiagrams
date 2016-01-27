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
	private List<String> comp;

	@Override
	public void intake(MetaDataLibrary mdl) {
		for (ClassVolume cv : mdl.getClassVolume()) {
			this.comp = new ArrayList<String>();
				if (hasInterfaceAsField(cv) || hasSuperClassAsField(cv)) {
					if (constructorTakesType(cv)) {
						List<String> component = null;
						component = this.comp;
						for (String c : component) {
							classToSpecial.put(cv.getName().replace('/', '.'), "decorator");
							classToSpecial.put(c, "component");
							edgeToLabel.put(cv.getName().replace("/", "").replace(".", "") + " -> " + c.replace("/", "").replace(".", ""), "decorates");
						}
					}
				}
		}
		System.out.println("classToSpecial:     " + this.classToSpecial.toString());
		System.out.println("edgeToLabel:        " + this.edgeToLabel.toString());

	}
	
	public boolean constructorTakesType(ClassVolume cv) {
		for (MethodBook mb : cv.getMethods()) {
			if (mb.getName().equals("<init>")) {
				for (String arg : mb.getArgTypes()) {
					String comp = cv.getSuperName();
					String[] intcomp = cv.getInterfaces();
					if (arg.equals(comp) || arg.equals(intcomp)) {
						this.comp.add(arg);
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
//		System.out.println("cv name:   " + cv.getName());
		for (FieldPage fp : cv.getFields()) {
//			System.out.println(fp.getType());
			if (fp.getType().equals(comp)) {
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
