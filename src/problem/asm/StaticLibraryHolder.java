package problem.asm;

public class StaticLibraryHolder {
	
	private static MetaDataLibrary mdl;
	private static String classname;
	
	public static void initializeLibrary() {
		mdl = new MetaDataLibrary();
	}
	
	public static void addClass(ClassVolume cv) {
		mdl.addClass(cv);
	}
	
	public static void addMethod(MethodBook mb) {
		mdl.addMethod(mb, classname);
	}
	
	public static void addField(FieldPage fp) {
		mdl.addField(fp, classname);
	}
	
	public static MetaDataLibrary getLibrary() {
		return mdl;
	}
	
	public static void setClassname(String cn) {
		classname = cn;
	}

}
