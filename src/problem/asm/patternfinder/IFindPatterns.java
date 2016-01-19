package problem.asm.patternfinder;

import java.io.PrintWriter;

import problem.asm.storage.MetaDataLibrary;

public interface IFindPatterns {

	public void intake(MetaDataLibrary mdl);
	public void write(String className, PrintWriter writer);
	
}
