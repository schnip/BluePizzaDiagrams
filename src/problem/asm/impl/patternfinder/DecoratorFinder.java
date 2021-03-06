package problem.asm.impl.patternfinder;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import problem.asm.api.patternfinder.IFindPatterns;
import problem.asm.api.patternfinder.IPatternInstance;
import problem.asm.api.patternfinder.PatternInstance;
import problem.asm.storage.ClassVolume;
import problem.asm.storage.FieldPage;
import problem.asm.storage.MetaDataLibrary;
import problem.asm.storage.MethodBook;
import problem.asm.storage.StU;

public class DecoratorFinder implements IFindPatterns {

	private Map<String, String> classToSpecial = new HashMap<String, String>();
	private Map<String, String> edgeToLabel = new HashMap<String, String>();
	private List<IPatternInstance> patInst = new LinkedList<IPatternInstance>();
	private List<String> comp;
	private MetaDataLibrary mdl;

	@Override
	public void intake(MetaDataLibrary mdl) {
		this.mdl = mdl;
		for (ClassVolume cv : mdl.getClassVolume()) {
			this.comp = new ArrayList<String>();
//			System.out.println("cvog:   " + cv.getName());
				if (hasInterfaceAsField(cv) || hasSuperClassAsField(cv, cv.getName())) {
//					System.out.println("cv name:   " + cv.getName());
					//System.out.println("grape");
					if (constructorTakesType(cv)) {
						List<String> component = null;
						component = this.comp;
						for (String c : component) {
							classToSpecial.put(StU.toDot(cv.getName()), "decorator");
//							classToSpecial.put(c, "component");
							// Being a decorator is more important than being a component
							StU.putIfAbsent(c, "component", classToSpecial);
							edgeToLabel.put(cv.getName().replace("/", "").replace(".", "") + " -> " + c.replace("/", "").replace(".", ""), "decorates");
						}
					}
				}
		}
		for (ClassVolume cv : mdl.getClassVolume()) {
			if (superChain(cv, mdl)) {
				classToSpecial.put(StU.toDot(cv.getName()), "decorator");
			}
		}
		for (String sup : classToSpecial.keySet()) {
			if (classToSpecial.get(sup).equals("component")) {
				IPatternInstance temp = new PatternInstance(sup);
				for (String sub : classToSpecial.keySet()) {
					if (StU.isUnder(mdl, sub, sup)) {
						temp.addParticipant(sub, "decorator");
					}
				}
				temp.addParticipant(sup, "component");
			}
		}
		//System.out.println("classToSpecial:     " + this.classToSpecial.toString());
		//System.out.println("edgeToLabel:        " + this.edgeToLabel.toString());

	}
	
	public boolean constructorTakesType(ClassVolume cv) {
//		System.out.println("yah");
		for (MethodBook mb : cv.getMethods()) {
			if (mb.getName().equals("<init>")) {
//				System.out.println("Found the constructors");
				for (String arg : mb.getArgTypes()) {
//					System.out.println("the constructors have arg");
					String comp = cv.getName();
					String[] intcomp = cv.getInterfaces();
					if (superConstructorTakesTypeLooperHelper(comp, arg) || StU.ehhContains(Arrays.asList(intcomp), arg)) {
						this.comp.add(arg);
//						System.out.println("fine");
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
		for (FieldPage fp : cv.getFields()) {
			if (StU.ehhEquals(fp.getType(), superName)) {
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
		if (StU.ehhContains(classToSpecial.keySet(), className)) {
			writer.println();
			writer.print("\\<\\<" + classToSpecial.get(className) + "\\>\\>\\l");
		}
	}

	@Override
	public void writeAttributes(String className, PrintWriter writer) {
		if (StU.ehhContains(classToSpecial.keySet(), className)) {
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
	
	public boolean superChain(ClassVolume cv, MetaDataLibrary mdl) {
//		System.out.println("chocolate");
		for (String s : classToSpecial.keySet()) {
//			System.out.println(" " + cv.getName());
//			System.out.println(" " + s);
			if (StU.ehhEquals(cv.getName(), s)) {
//				System.out.println("hrmmmf");
//				System.out.println(s + " " + classToSpecial.get(s));
				if (classToSpecial.get(s).equals("decorator"))
					return true;
			}
		}
		if (mdl.contains(cv.getSuperName())) {
			return superChain(mdl.getClassByString(cv.getSuperName()), mdl);
		}
		return false;
	}

	@Override
	public Iterable<IPatternInstance> getInstances() {
		return patInst;
	}

	@Override
	public void intakeOptions(Map<String, String> options) {
		// TODO Auto-generated method stub
		
	}

}
