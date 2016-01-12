package problem.asm.visitor;

import org.objectweb.asm.ClassVisitor;

public class ClassInternalsVisitor extends ClassVisitor {
	
	public ClassInternalsVisitor(int arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	public ClassInternalsVisitor(int arg0, ClassVisitor arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void visitSource(String source, String debug) {
		System.out.println(source);
	}

}
