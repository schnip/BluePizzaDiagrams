package problem.asm;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import problem.asm.outputer.ConsoleOutputer;
import problem.asm.outputer.DiagramOutputer;
import problem.asm.outputer.IOutputData;
import problem.asm.outputer.SDOutputer;
import problem.asm.storage.StaticLibraryHolder;

public class DesignParser {
	public static void main(String[] args) throws IOException{
		StaticLibraryHolder.initializeLibrary();
		List<String> options = new ArrayList<String>();
		for (String className : args) {
			if (className.subSequence(0, 1).equals("-")) {
				options.add(className);
				continue;
			}
			StaticLibraryHolder.parseClass(className);
		}
		
		IOutputData iod = new ConsoleOutputer();
		if (options.contains("-d")) {
			iod = new DiagramOutputer("dot/out.dot");
		}
		
		if (options.contains("-s")) {
			iod = new SDOutputer("sd/out.sd");
		}
		iod.outputData(StaticLibraryHolder.getLibrary());
	}
}
