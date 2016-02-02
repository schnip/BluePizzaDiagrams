package problem.asm.storage;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
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
		// TODO Auto-generated method stub
//		patternfinders.add(new SingletonFinder());
//		patternfinders.add(new AdapterFinder());
//		patternfinders.add(new DecoratorFinder());
//		packagepath = "./bin/problem/asm/patternfinder";
		System.out.println("i dont even know");
//		URI resource;
		try {
			File f = new File(packagepath);
			File cp = new File("bin");
			System.out.println();
			System.out.println(f.getAbsolutePath());
			System.out.println(f.getName());
			System.out.println(f.listFiles()[0].getName());
			System.out.println(f.toURI().toURL());
			ClassLoader cl = new URLClassLoader(new URL[]{cp.toURI().toURL()});
			for (File classfile : f.listFiles()) {
				System.out.println(f.getAbsolutePath().replace(cp.getAbsolutePath(), "").replace('\\', '.').substring(1) + "." + classfile.getName().replace(".class", ""));
				Class c = cl.loadClass(f.getAbsolutePath().replace(cp.getAbsolutePath(), "").replace('\\', '.').substring(1) + "." + classfile.getName().replace(".class", ""));
				dump.add(c.newInstance());
			}
			System.out.flush();
//			resource = ClassLoader.getSystemClassLoader().getResource(packagepath).toURI();
			
//			System.out.println(resource);
			System.out.flush();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.flush();
		}
		System.out.flush();
		System.out.println("bananana");
	}

}
