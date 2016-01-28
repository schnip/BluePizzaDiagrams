package problem.asm.patternfinder;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import problem.asm.storage.ClassVolume;
import problem.asm.storage.FieldPage;
import problem.asm.storage.MetaDataLibrary;

public class AdapterFinder implements IFindPatterns {
	
	private Map<String, String> classToSpecial = new HashMap<String, String>();
	private Map<String, String> edgeToLabel = new HashMap<String, String>();
	private List<String> comp;

	@Override
	public void intake(MetaDataLibrary mdl) {
		for (ClassVolume cv : mdl.getClassVolume()) {
			String[] inters = cv.getInterfaces();
			String choseninter = "";
			if (inters.length > 0) choseninter = inters[0];
			this.comp = new ArrayList<String>();
			if (hasAdaptee(mdl, cv)) {
				
			}
		}
		
	}
	
	public boolean hasAdaptee(MetaDataLibrary mdl, ClassVolume cv) {
		ArrayList<String> fields = new ArrayList<String>();
		for (FieldPage fp : cv.getFields()) {
			fields.add(fp.getName());
		}
		for (ClassVolume c : mdl.getClassVolume()) {
			for (String field : fields) {
				if (field.equals(c.getName()));
			}
		}
		return false;
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
