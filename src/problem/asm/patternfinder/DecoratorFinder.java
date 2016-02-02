package problem.asm.patternfinder;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import problem.asm.storage.ClassVolume;
import problem.asm.storage.FieldPage;
import problem.asm.storage.MetaDataLibrary;
import problem.asm.storage.MethodBook;
import problem.asm.storage.StU;

public class DecoratorFinder implements IFindPatterns {

	private Map<String, String> classToSpecial = new HashMap<String, String>();
	private Map<String, String> edgeToLabel = new HashMap<String, String>();
	private List<String> comp;
	private MetaDataLibrary mdl;

	@Override
	public void intake(MetaDataLibrary mdl) {
		this.mdl = mdl;
		for (ClassVolume cv : mdl.getClassVolume()) {
			this.comp = new ArrayList<String>();
//			System.out.println("cvog:   " + cv.getName());
				if (hasInterfaceAsField(cv) || hasSuperClassAsField(cv, cv.getSuperName())) {
//					System.out.println("cv name:   " + cv.getName());
					//System.out.println("grape");
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
		//System.out.println("classToSpecial:     " + this.classToSpecial.toString());
		//System.out.println("edgeToLabel:        " + this.edgeToLabel.toString());

	}
	
	public boolean constructorTakesType(ClassVolume cv) {
		//System.out.println("yah");
		for (MethodBook mb : cv.getMethods()) {
			if (mb.getName().equals("<init>")) {
				for (String arg : mb.getArgTypes()) {
					String comp = cv.getSuperName();
					String[] intcomp = cv.getInterfaces();
					if (superConstructorTakesTypeLooperHelper(comp, arg) || StU.ehhContains(Arrays.asList(intcomp), arg)) {
						this.comp.add(arg);
						return true;
					}
				}
			}
		}
		//System.out.println("nah");
		return false;
	}
	
	public boolean superConstructorTakesTypeLooperHelper(String superName, String arg) {
		//System.out.println("fried fish");
		if (StU.ehhEquals(arg, superName)) {
			//System.out.println("watermellon");
			return true;
		}
		if (mdl.getClassByString(superName) != null) {
			return superConstructorTakesTypeLooperHelper(mdl.getClassByString(superName).getSuperName(), arg);
		}
		return false;
	}
	
	public boolean hasSuperClassAsField(ClassVolume cv, String superName) {
		//System.out.println(" pasta " + cv.getName());
		//String comp = cv.getSuperName();
		for (FieldPage fp : cv.getFields()) {
			//System.out.println("jello");
			//System.out.println("peanut" + superName.replace('/', '.'));
			//System.out.println("salt  " + fp.getType());
			if (fp.getType().equals(superName.replace('/', '.'))) {
				//System.out.println("jamjam");
				return true;
			}
		}
		if (cv.getSuperName() != "java.lang.object" && mdl.getClassByString(superName) != null){
			//System.out.println("recurse!" + comp);
			return hasSuperClassAsField(cv, mdl.getClassByString(superName).getSuperName());
		}
		return false;
	}

	public boolean hasInterfaceAsField(ClassVolume cv) {
		//System.out.println("fries");
		String[] check = cv.getInterfaces();
		if (check.length == 0) return false;
		//System.out.println("pudding");
		for (String c : check) {
			for (FieldPage fp : cv.getFields()) {
				if (StU.ehhEquals(c, fp.getType())) {
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
