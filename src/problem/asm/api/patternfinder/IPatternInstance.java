package problem.asm.api.patternfinder;

import java.util.Set;

public interface IPatternInstance {

	public String getTitle();
	public Set<String> getParticipantClasses();
	public void addParticipant(String a, String b);
	public boolean isClassPresent(String c);
	public String getClassAnnotation(String c);

}
