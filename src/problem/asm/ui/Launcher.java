package problem.asm.ui;

import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import problem.asm.api.patternfinder.IFindPatterns;
import problem.asm.api.patternfinder.IPatternInstance;
import problem.asm.outputer.DiagramOutputer;
import problem.asm.outputer.IOutputData;
import problem.asm.storage.MetaDataLibrary;
import problem.asm.storage.StU;
import problem.asm.storage.StaticLibraryHolder;

public class Launcher {

	private static String outputDir;
	private static String dotPath;
	private static String diagramType;
	private static String outputType;
	private static String inputDir;
	private static Set<String> phases;
	private static Map<String, String> options;
	private static LandingPage lp;

	public static void main(String[] args) {
		lp = new LandingPage();
		parseAndDo(lp.getConfigPath());
		lp.finishLoadingBar(null, null);
		PatternPage pp = new PatternPage(generatPatC(StaticLibraryHolder.getLibrary()),
				new ResultMaker(dotPath, StaticLibraryHolder.getLibrary(), outputDir, phases));
		pp.createPatternPage();
	}

	private static PatternCollection generatPatC(MetaDataLibrary mdl) {
		PatternCollection ret = new PatternCollection("root");
		Set<IFindPatterns> patternfinders = new HashSet<IFindPatterns>();

		StU.loadClasses("bin/problem/asm/impl/patternfinder", patternfinders);
		for (IFindPatterns s : patternfinders) {
			if (phases.size() <= 0 || phases.contains(s.getName())) {
				s.intake(mdl);
				PatternCollection temp = new PatternCollection(s.getName());
				System.out.println(s.getName());
				System.out.println("time for problems");
				for (IPatternInstance pi : s.getInstances()) {
					temp.addSubCollection(new PatternCollection(pi.getTitle()));
				}
				ret.addSubCollection(temp);
			}
		}

		return ret;
	}

	@SuppressWarnings("rawtypes")
	public static void parseAndDo(String configPath) {
		StaticLibraryHolder.initializeLibrary();
		JSONParser parser = new JSONParser();
		try {
			JSONObject obj = (JSONObject) parser.parse(new FileReader(configPath));
			JSONArray jarry = (JSONArray) obj.get("classes");
			for (Object o : jarry) {
				StaticLibraryHolder.parseClass((String) o);
			}
			diagramType = (String) obj.get("type");
			dotPath = (String) obj.get("dot-path");
			outputDir = (String) obj.get("output-dir");
			outputType = (String) obj.get("output-type");
			inputDir = (String) obj.get("input-dir");
//			System.out.println("The output directory is supposed to be " + outputDir);
			phases = new HashSet<String>();
			if (null != obj.get("phases")) {
				for (Object o : (JSONArray) obj.get("phases")) {
					phases.add((String) o);
				}
			}
			options = new HashMap<String, String>();
			JSONObject rawopt = (JSONObject) obj.get("options");
			for (Object entry : rawopt.entrySet()) {
				options.put((String) ((Map.Entry) entry).getKey(), (String) rawopt.get(((Map.Entry) entry).getKey()));
			}
			if (null != inputDir) {
				inputClassRec(new File(inputDir));
			}
			
			if (!diagramType.equals("uml")) {
				throw new UnsupportedOperationException();
			}

			// Take action if file output
			if ("file".equals(outputType)) {
				IOutputData iod = new DiagramOutputer(outputDir + "out.dot", phases);
				iod.outputData(StaticLibraryHolder.getLibrary());
				moveLoadingBar();
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void inputClassRec(File file) {
		if (file.isFile()) {
			StaticLibraryHolder.parseClass(StU.toDot(file.getPath()).replace(".class", ""));
			return;
		}
		for (File f : file.listFiles()) {
			inputClassRec(f);
		}
	}

	private static void moveLoadingBar() {
		if (null != lp) {
			lp.addToLoadingBar();
		}
	}

}
