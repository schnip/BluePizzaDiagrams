package problem.asm;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import problem.asm.outputer.ConsoleOutputer;
import problem.asm.outputer.DiagramOutputer;
import problem.asm.outputer.IOutputData;
import problem.asm.outputer.SDOutputer;
import problem.asm.storage.StaticLibraryHolder;
import problem.asm.ui.Launcher;

public class DesignParser {
	public static void main(String[] args) throws IOException {
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
			List<String> startArgs = new ArrayList<String>();
			for (String s : options) {
				if (s.substring(0, 2).equals("-a")) {
					startArgs.add(s.substring(2));
				}
			}
			if (findStart(options, "--depth=") >= 0) {
				iod = new SDOutputer("sd/out.sd", getArg(options, "--method="), getArg(options, "--class="), startArgs,
						Integer.parseInt(getArg(options, "--depth=")));
			} else {
				iod = new SDOutputer("sd/out.sd", getArg(options, "--method="), getArg(options, "--class="), startArgs);
			}
		}
		
		if (findStart(options, "--config=") >= 0) {
			Launcher.parseAndDo(getArg(options, "--config="));
			return;
		}

		iod.outputData(StaticLibraryHolder.getLibrary());
	}

	public static int findStart(List<String> ls, String start) {
		for (int i = 0; i < ls.size(); i++) {
			try {
				if (ls.get(i).substring(0, start.length()).equals(start)) {
					return i;
				}
			} catch (Exception e) {
				
			}
		}
		return -1;
	}

	public static String chopStart(String s, String start) {
		return s.substring(start.length());
	}

	public static String getArg(List<String> ls, String start) {
		return chopStart(ls.get(findStart(ls, start)), start);
	}
}
