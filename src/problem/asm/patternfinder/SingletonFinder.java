package problem.asm.patternfinder;

import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Set;

import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;

import problem.asm.storage.ClassVolume;
import problem.asm.storage.FieldPage;
import problem.asm.storage.MetaDataLibrary;
import problem.asm.storage.MethodBook;

public class SingletonFinder implements IFindPatterns {
	
	private Set<String> singletons = new HashSet<String>();

	@Override
	public void intake(MetaDataLibrary mdl) {
		for (ClassVolume cv : mdl.getClassVolume()) {
			boolean field = checkPrivateStaticSameClass(cv);
			boolean privateConstructor = checkPCandgetter(cv);
			if (field && privateConstructor) {
				this.singletons.add(cv.getName().replace("/", "."));
			}
		} 

	}
	
	private boolean checkPrivateStaticSameClass(ClassVolume cv) {
		for (FieldPage fp : cv.getFields()) {
			if ((fp.getAccess() & Opcodes.ACC_PRIVATE) != 0) {
				if ((fp.getAccess() & Opcodes.ACC_STATIC) != 0) {
					if (Type.getType(fp.getDesc()).getClassName().equals(cv.getName().replace("/", "."))) {
						return true;
					} 
				}
			}
		}
		return false;
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
			System.out.println("inner being called");
			writer.println();
			writer.print("\\<\\<Singleton\\>\\>\\l");
		}
	}

	@Override
	public String getName() {
		return "singleton";
	}

}
