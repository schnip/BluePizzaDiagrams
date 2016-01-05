package problem.asm;

import org.objectweb.asm.ClassVisitor;

public class ClassDeclarationVisitor extends ClassVisitor {

	public ClassDeclarationVisitor(int arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}
	
	public ClassDeclarationVisitor(int arg0, ClassVisitor arg1){
		super(arg0, arg1);
	}

	@Override
	public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
//		System.out.println("Class: " + name + " extends " + superName + " implements " + Arrays.toString(interfaces));
		StaticLibraryHolder.addClass(new ClassVolume(version, access, name, signature, superName, interfaces));
		
		super.visit(version, access, name, signature, superName, interfaces);

	}

}
