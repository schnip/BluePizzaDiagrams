package problem.asm.visitor;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.FieldVisitor;

import problem.asm.storage.FieldPage;
import problem.asm.storage.StaticLibraryHolder;

public class ClassFieldVisitor extends ClassVisitor {
	
	public ClassFieldVisitor(int arg0) {
		super(arg0);
	}

	public ClassFieldVisitor(int arg0, ClassVisitor arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}
	
	public FieldVisitor visitField(int access, String name, String desc, String signature, Object value) {
		FieldVisitor toDecorate = super.visitField(access,  name, desc, signature, value);
		
		//String type = Type.getType(desc).getClassName();
		
//		System.out.println("   "+type+" "+name);
		StaticLibraryHolder.addField(new FieldPage(access, name, desc, signature, value));
		
		return toDecorate;
	}

}
