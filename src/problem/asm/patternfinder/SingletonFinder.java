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
import problem.asm.storage.UseSentence;

public class SingletonFinder implements IFindPatterns {
	
	private Set<String> singletons = new HashSet<String>();

	@Override
	public void intake(MetaDataLibrary mdl) {
		
		for (ClassVolume v : mdl.getClassVolume()) {
			
			for (FieldPage fp : v.getFields()) {
				if (Opcodes.ACC_STATIC != 0) {
					if (fp.getName().contains(fp.getDesc()));
				}
				
			}
			

		}

	}

	@Override
	public void write(String className, PrintWriter writer) {
		if (singletons.contains(className)) {
			writer.print("\\<\\<Singleton\\>\\>\\l");
		}
	}

}
