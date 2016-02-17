package problem.asm.ui;

import problem.asm.storage.MetaDataLibrary;

public class ResultMaker implements IMakeResults {
	
	private MetaDataLibrary mdl;
	private String dotPath;

	public ResultMaker(String dotPath, MetaDataLibrary library) {
		mdl = library;
		this.dotPath = dotPath;
	}

	@Override
	public String makeResult(PatternCollection patC) {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
