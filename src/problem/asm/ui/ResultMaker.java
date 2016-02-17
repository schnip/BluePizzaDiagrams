package problem.asm.ui;

import java.util.Set;

import problem.asm.outputer.DiagramOutputer;
import problem.asm.outputer.IOutputData;
import problem.asm.storage.MetaDataLibrary;

public class ResultMaker implements IMakeResults {
	
	private MetaDataLibrary mdl;
	private String dotPath;
	private String outputDir;
	private String path;
	private Set<String> phases;

	public ResultMaker(String dotPath, MetaDataLibrary library, String outputDir, Set<String> phases) {
		mdl = library;
		this.dotPath = dotPath;
		path = outputDir + "out.dot";
	}

	@Override
	public String makeResult(PatternCollection patC) {
		if (patC.isChecked()) {
			IOutputData iod = new DiagramOutputer(path, phases);
			iod.outputData(mdl);
		} else {
			
		}
		return runDot();
	}
	
	private String runDot() {
		// Assume that this is being run on linux
		Process p;
		try {
			p = Runtime.getRuntime().exec(dotPath + " -O -Tpng " + path);
			p.waitFor();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return outputDir + "out.dot.png";
	}

}
