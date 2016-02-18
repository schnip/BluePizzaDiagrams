package problem.asm.api.patternfinder;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class PatternInstance implements IPatternInstance {
	
	Map<String, String> classToProperty;
	String title;
	
	public PatternInstance(String title) {
		classToProperty = new HashMap<String, String>();
		this.title = title;
	}

	@Override
	public String getTitle() {
		return title;
	}
	
	@Override
	public Set<String> getParticipantClasses() {
		return classToProperty.keySet();
	}
	
	@Override
	public void addParticipant(String a, String b) {
		classToProperty.put(a, b);
	}
	
	@Override
	public boolean isClassPresent(String c) {
		return classToProperty.containsKey(c);
	}
	
	@Override
	public String getClassAnnotation(String c) {
		return classToProperty.get(c);
	}

}
