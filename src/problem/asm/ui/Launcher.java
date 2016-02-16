package problem.asm.ui;

import java.io.FileReader;
import java.util.HashSet;
import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import problem.asm.outputer.DiagramOutputer;
import problem.asm.outputer.IOutputData;
import problem.asm.storage.StaticLibraryHolder;

public class Launcher {
	
	private static String outputDir;
	private static String dotPath;
	private static String diagramType;
	private static Set<String> phases;
	

	public static void main(String[] args) {
		LandingPage lp = new LandingPage();
		parseAndDo(lp.getConfigPath());
	}

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
			phases = new HashSet<String>();
			if (null != obj.get("phases")) {
				for (Object o : (JSONArray) obj.get("phases")) {
					phases.add((String) o);
				}
			}
			if (!diagramType.equals("uml")) {
				throw new UnsupportedOperationException();
			}
			
			// Take action if file output
			if ("file".equals(obj.get("output-type"))) {
				IOutputData iod = new DiagramOutputer(outputDir + "out.dot", phases);
				iod.outputData(StaticLibraryHolder.getLibrary());
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
