package problem.asm.ui;

import java.io.FileReader;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import problem.asm.storage.StaticLibraryHolder;

public class Launcher {

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
				JSONObject jo = (JSONObject) o;
				StaticLibraryHolder.parseClass(jo.toString());
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
