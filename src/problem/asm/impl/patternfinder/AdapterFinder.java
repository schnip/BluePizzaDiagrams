package problem.asm.impl.patternfinder;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import problem.asm.api.patternfinder.IFindPatterns;
import problem.asm.storage.ClassVolume;
import problem.asm.storage.FieldPage;
import problem.asm.storage.MetaDataLibrary;
import problem.asm.storage.MethodBook;
import problem.asm.storage.MethodCallParagraph;
import problem.asm.storage.StU;

public class AdapterFinder implements IFindPatterns {

	private Map<String, String> classToSpecial = new HashMap<String, String>();
	private Map<String, String> edgeToLabel = new HashMap<String, String>();
	//private MetaDataLibrary mdl;

	@Override
	public void intake(MetaDataLibrary mdl) {
		//this.mdl = mdl;
		for (ClassVolume pAdapter : mdl.getClassVolume()) {
			for (ClassVolume pAdaptee : mdl.getClassVolume()) {
				for (ClassVolume pTarget : mdl.getClassVolume()) {
					testFirstCombo(pAdapter, pAdaptee, pTarget);
				}
			}
		}
	}
	
	private void testFirstCombo(ClassVolume pAdapter, ClassVolume pAdaptee, ClassVolume pTarget) {
		if (StU.ehhEquals(pAdapter.getName(), pAdaptee.getName()) || StU.ehhEquals(pAdapter.getName(), pTarget.getName()) || StU.ehhEquals(pAdaptee.getName(), pTarget.getName())) {
			return;
		}
		for (String s : pAdapter.getInterfaces()) {
			if (StU.ehhEquals(s, pTarget.getName())) {
				testSecondCombo(pAdapter, pAdaptee, pTarget);
			}
		}
	}

	private void testSecondCombo(ClassVolume pAdapter, ClassVolume pAdaptee, ClassVolume pTarget) {
//		System.out.println("the second circle");
		for (FieldPage fp : pAdapter.getFields()) {
			if (StU.ehhEquals(fp.getType(), pAdaptee.getName())) {
				testThirdCombo(pAdapter, pAdaptee, pTarget);
			}
		}
	}

	private void testThirdCombo(ClassVolume pAdapter, ClassVolume pAdaptee, ClassVolume pTarget) {
//		System.out.println("the third circle");
		for (MethodBook mb : pAdapter.getMethods()) {
//			System.out.println(mb.getName());
			int passable = 0;
			if (mb.getName().equals("<init>")) {
				passable++;
			}
			for (MethodCallParagraph mcp : pAdapter.getMethodCall()) {
				for (MethodBook mbee : pAdaptee.getMethods()) {
//					System.out.println("  " + mcp.getName() + " " + mcp.getMethodName());
					if (mbee.getName().equals(mcp.getName()) && mb.getName().equals(mcp.getMethodName()) && StU.ehhEquals(pAdaptee.getName(), mcp.getOwner())) {
						passable++;
					}
				}
			}
			if (passable <= 0) {
				return;
			}	
		}
		classToSpecial.put(StU.toDot(pTarget.getName()), "target");
		classToSpecial.put(StU.toDot(pAdapter.getName()), "adapter");
		classToSpecial.put(StU.toDot(pAdaptee.getName()), "adaptee");
		edgeToLabel.put(StU.toArrow(pAdapter.getName(), pAdaptee.getName()), "\\<\\<adapts\\>\\>");
	}

	@Override
	public String getName() {
		return "adapter";
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
			writer.print("fillcolor=red, style=filled,");
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
