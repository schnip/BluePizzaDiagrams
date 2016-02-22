package problem.asm.impl.patternfinder;

import java.io.PrintWriter;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;

import problem.asm.api.patternfinder.IFindPatterns;
import problem.asm.api.patternfinder.IPatternInstance;
import problem.asm.api.patternfinder.PatternInstance;
import problem.asm.storage.ClassVolume;
import problem.asm.storage.FieldPage;
import problem.asm.storage.MetaDataLibrary;
import problem.asm.storage.MethodBook;
import problem.asm.storage.StU;

public class SingletonFinder implements IFindPatterns {
	
	private Set<String> singletons = new HashSet<String>();
	private List<IPatternInstance> patInst = new LinkedList<IPatternInstance>();
	private boolean easyHolder = false;

	@Override
	public void intake(MetaDataLibrary mdl) {
		for (ClassVolume cv : mdl.getClassVolume()) {
			boolean field = checkPrivateStaticSameClass(cv);
			boolean privateConstructor = checkPCandgetter(cv);
			if (field && privateConstructor) {
				this.singletons.add(cv.getName().replace("/", "."));
				IPatternInstance temp = new PatternInstance(StU.toDot(cv.getName()));
				temp.addParticipant(StU.toDot(cv.getName()), "singleton");
				this.patInst.add(temp);
			}
		} 

	}
	
	private boolean checkPrivateStaticSameClass(ClassVolume cv) {
		for (FieldPage fp : cv.getFields()) {
			if ((fp.getAccess() & Opcodes.ACC_PRIVATE) != 0) {
				if ((fp.getAccess() & Opcodes.ACC_STATIC) != 0) {
					if (Type.getType(fp.getDesc()).getClassName().equals(cv.getName().replace("/", "."))) {
//						System.out.println("sumthin");
						return true;
					} 
				}
			}
		}
		return easyHolder;
	}
	
	private boolean checkPCandgetter(ClassVolume cv) {
		boolean hasPrivateConstructor = false;
		boolean hasGetInstance = false;
		for (MethodBook mb : cv.getMethods()) {
			if ((mb.getAccess() & Opcodes.ACC_PUBLIC) != 0) {
//				System.out.println(mb.getName());
				if (Type.getReturnType(mb.getDesc()).getClassName().equals(cv.getName().replace("/", "."))) {
					hasGetInstance = true;
					if (hasPrivateConstructor) {
						return true;
					}
				}
			}
			else if ((mb.getAccess() & Opcodes.ACC_PRIVATE) != 0) {
				System.out.println("mb names:   " + mb.getName());
				 if (mb.getName().equals("<init>")) {
					 System.out.println("Picks up private constructor");
					 hasPrivateConstructor = true;
					 if (hasGetInstance) {
						 return true;
					 }
				 }
			 }
		}
		return false;
	}

	@Override
	public void write(String className, PrintWriter writer) {
		if (singletons.contains(className)) {
			//System.out.println("inner being called");
			writer.println();
			writer.print("\\<\\<Singleton\\>\\>\\l");
		}
	}

	@Override
	public String getName() {
		return "singleton";
	}

	@Override
	public void writeAttributes(String className, PrintWriter writer) {
		if (singletons.contains(className)) {
			//System.out.println("inner being called");
			writer.print("color = blue,");
			writer.println();
		}
	}

	@Override
	public void labelEdge(String edgeDescription, PrintWriter writer) {
		// This does nothing		
	}

	@Override
	public Iterable<IPatternInstance> getInstances() {
		return patInst;
	}

	@Override
	public void intakeOptions(Map<String, String> options) {
		if (options.get("singleton").equals("noPrivateField"))
			this.easyHolder = true;
	}

}
