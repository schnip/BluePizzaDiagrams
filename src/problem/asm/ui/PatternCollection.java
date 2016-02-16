package problem.asm.ui;

import java.util.LinkedList;
import java.util.List;

public class PatternCollection {
	
	private boolean checked;
	private String title;
	private List<PatternCollection> subpatterns;
	
	public PatternCollection(String t) {
		checked = false;
		title = t;
		subpatterns = new LinkedList<PatternCollection>();
	}
	
	public boolean isChecked() {
		return checked;
	}
	
	public void setChecked(boolean b) {
		checked = b;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void addSubCollection(PatternCollection pc) {
		subpatterns.add(pc);
	}
	
	public List<PatternCollection> getSubCollection() {
		return subpatterns;
	}

}
