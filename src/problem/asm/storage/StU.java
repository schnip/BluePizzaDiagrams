package problem.asm.storage;

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

}
