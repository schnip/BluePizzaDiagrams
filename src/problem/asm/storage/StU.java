package problem.asm.storage;

import java.util.Set;

import problem.asm.patternfinder.AdapterFinder;
import problem.asm.patternfinder.DecoratorFinder;
import problem.asm.patternfinder.SingletonFinder;

public class StU {
	
	public static String toDot(String s) {
		return s.replace('/', '.');
	}
	
	public static String toSlash(String s) {
		return s.replace('/',  '.');
	}
	
	public static String toClean(String s) {
		return s.replace("/", "").replace(".", "");
	}
	
	public static boolean ehhEquals(String a, String b) {
		return toClean(a).equals(toClean(b));
	}
	
	public static boolean ehhContains(Iterable<String> iter, String want) {
		for (String s : iter) {
			if (ehhEquals(s, want)) {
				return true;
			}
		}
		return false;
	}
	
	public static String toArrow(String from, String to) {
		return toClean(from) + " -> " + toClean(to);
	}

	@SuppressWarnings("rawtypes")
	public static void loadClasses(String packagepath, Set patternfinders, Class typeWanted) {
		// TODO Auto-generated method stub
		patternfinders.add(new SingletonFinder());
		patternfinders.add(new AdapterFinder());
		patternfinders.add(new DecoratorFinder());
//		URL resource = ClassLoader.getSystemClassLoader().getResource(packagepath);
	}

}
