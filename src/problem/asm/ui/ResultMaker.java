package problem.asm.ui;

import java.util.HashSet;
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
			Set<String> newPhases = new HashSet<String>();
			Set<String> newClasses = new HashSet<String>();
			// Load the two sets
			for (PatternCollection pattern : patC.getSubCollection()) {
				if (pattern.isChecked()) {
					newPhases.add(pattern.getTitle());
				}
				for (PatternCollection instance : pattern.getSubCollection()) {
					if (instance.isChecked()) {
						newClasses.add(instance.getTitle());
						// TODO FINISH THIS STUFF
					}
				}
			}
			
			// Now that they have been loaded, make the new mdl and run the outputter on it
			MetaDataLibrary newMdl = new MetaDataLibrary();
			for (String s : newClasses) {
				newMdl.addClass(mdl.getClassByString(s));
			}
			IOutputData iod = new DiagramOutputer(path, newPhases);
			iod.outputData(newMdl);
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
