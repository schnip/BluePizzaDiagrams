package problem.asm.patternfinder;

import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Set;

import problem.asm.storage.MetaDataLibrary;

public class SingletonFinder implements IFindPatterns {
	
	private Set<String> singletons = new HashSet<String>();

	@Override
	public void intake(MetaDataLibrary mdl) {
		// TODO Auto-generated method stub

	}

	@Override
	public void write(String className, PrintWriter writer) {
		if (singletons.contains(className)) {
			writer.print("\\<\\<Singleton\\>\\>\\l");
		}
	}

}
