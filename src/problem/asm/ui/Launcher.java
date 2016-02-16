package problem.asm.ui;

import java.io.FileReader;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import problem.asm.storage.StaticLibraryHolder;

public class Launcher {
	
	private static String ouputDir;
	private static String dotPath;
	private static String diagramType;
	

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
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
