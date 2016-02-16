package problem.asm.ui;

import org.json.simple.parser.JSONParser;

public class Launcher {

	public static void main(String[] args) {
		LandingPage lp = new LandingPage();
		parseAndDo(lp.getConfigPath());
	}

	public static void parseAndDo(String configPath) {
		JSONParser parser = new JSONParser();
		
	}

}
