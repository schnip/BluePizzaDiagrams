package problem.asm.storage;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Map;
import java.util.Set;

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

	@SuppressWarnings({ "unchecked", "rawtypes", "resource" })
	public static void loadClasses(String packagepath, Set dump) {
		try {
			File f = new File(packagepath);
			File cp = new File("bin");

			ClassLoader cl = new URLClassLoader(new URL[]{cp.toURI().toURL()});
			for (File classfile : f.listFiles()) {
				Class c = cl.loadClass(f.getAbsolutePath().replace(cp.getAbsolutePath(), "").replace('/', '.').replace('\\', '.').substring(1) + "." + classfile.getName().replace(".class", ""));
				dump.add(c.newInstance());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static String parseStringForT(String s) {
		//System.out.println("pineapple time!!! " + s);
		if (s.equals("()TE;")) {
			return "TE";
		}
		return s.substring(s.indexOf('<')).replace("<L", "").replace("<", "").replace(";>;", "").replace('/', '.');
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void putIfAbsent(Object key, Object value, Map m) {
		if (m.get(key) == null) {
			m.put(key, value);
		}
	}

}
