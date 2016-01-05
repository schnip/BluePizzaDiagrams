package problem.asm;

public class StaticLibraryHolder {
	
	private static MetaDataLibrary mdl;
	
	public static void initializeLibrary() {
		mdl = new MetaDataLibrary();
	}
	
	public static void addClass(ClassVolume cv) {
		mdl.addClass(cv);
	}
	
	public static void addMethod(MethodBook mb, String classname) {
		mdl.addMethod(mb, classname);
	}
	
	public static void addField(FieldPage fp, String classname) {
		mdl.addField(fp, classname);
	}
	
	public static MetaDataLibrary getLibrary() {
		return mdl;
	}

}
