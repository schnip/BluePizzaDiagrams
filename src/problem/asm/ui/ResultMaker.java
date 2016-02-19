package problem.asm.ui;

import java.util.HashSet;
import java.util.Set;

import problem.asm.api.patternfinder.IFindPatterns;
import problem.asm.api.patternfinder.IPatternInstance;
import problem.asm.outputer.DiagramOutputer;
import problem.asm.outputer.IOutputData;
import problem.asm.storage.MetaDataLibrary;
import problem.asm.storage.StU;

public class ResultMaker implements IMakeResults {

	private MetaDataLibrary mdl;
	private String dotPath;
	private String outputDir;
	private String path;
	private Set<String> phases;

	public ResultMaker(String dotPath, MetaDataLibrary library, String outputDir, Set<String> phases) {
		mdl = library;
		this.dotPath = dotPath;
		this.outputDir = outputDir;
		path = outputDir + "out.dot";
	}

	@Override
	public String makeResult(PatternCollection patC) {
		System.out.println("==I am going to make a result out of the following== " + patC.toString());
		if (patC.isChecked()) {
			IOutputData iod = new DiagramOutputer(path, phases);
			iod.outputData(mdl);
		} else {
			Set<String> newPhases = new HashSet<String>();
			Set<String> newClasses = new HashSet<String>();
			// Load the two sets
			for (PatternCollection pattern : patC.getSubCollection()) {
				boolean all = patC.isChecked();
				if (pattern.isChecked()) {
					newPhases.add(pattern.getTitle());
					all = true;
				}
				for (PatternCollection instance : pattern.getSubCollection()) {
					IFindPatterns ifp = loadAndGetPatternFinderByName(pattern.getTitle());
					for (IPatternInstance ipi : ifp.getInstances()) {
						if (ipi.getTitle().equals(instance.getTitle()) && (all || instance.isChecked())) {
							newPhases.add(pattern.getTitle());
							for (String inst : ipi.getParticipantClasses()) {
								newClasses.add(inst);
							}
						}
					}
				}
			}
			
			System.out.println("the number of patterns is " + newPhases.toString());
			System.out.println("the number of classes is " + newClasses.toString());
			System.out.flush();

			// Now that they have been loaded, make the new mdl and run the
			// outputter on it
			MetaDataLibrary newMdl = new MetaDataLibrary();
			for (String s : newClasses) {
				newMdl.addClass(mdl.getClassByString(s));
			}
			IOutputData iod = new DiagramOutputer(path, newPhases);
			iod.outputData(newMdl);
		}
		return runDot();
	}

	private IFindPatterns loadAndGetPatternFinderByName(String title) {
		Set<IFindPatterns> patternfinders = new HashSet<IFindPatterns>();
		StU.loadClasses("bin/problem/asm/impl/patternfinder", patternfinders );
		for (IFindPatterns ifp : patternfinders) {
			if (ifp.getName().equals(title)) {
				ifp.intake(mdl);
				return ifp;
			}
		}
		return null;
	}

	private String runDot() {
		// Assume that this is being run on linux
		Process p;
		try {
			p = Runtime.getRuntime().exec(dotPath + " -O -Tpng " + path);
			p.waitFor();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return outputDir + "out.dot.png";
	}

}
