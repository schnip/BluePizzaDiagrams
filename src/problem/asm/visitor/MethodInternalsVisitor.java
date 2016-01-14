package problem.asm.visitor;

import org.objectweb.asm.MethodVisitor;

import com.sun.xml.internal.ws.org.objectweb.asm.Opcodes;

import problem.asm.storage.MethodCallParagraph;
import problem.asm.storage.StaticLibraryHolder;
import problem.asm.storage.UseSentence;

public class MethodInternalsVisitor extends MethodVisitor {
	
	private String methodName;

	public MethodInternalsVisitor(int arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	public MethodInternalsVisitor(int arg0, MethodVisitor arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

	public MethodInternalsVisitor(int asm5, MethodVisitor toDecorate, String name) {
		super(asm5, toDecorate);
		methodName = name;
	}

	@Override
	public void visitMethodInsn(int opcode, String owner, String name, String desc, boolean itf) {
		//System.out.println(owner + " banana " + name + " " + methodName);
		StaticLibraryHolder.addUse(new UseSentence(owner));
		StaticLibraryHolder.addMethodCall(new MethodCallParagraph(opcode, owner, name, desc, itf, methodName));
	}
	
	@Override
	public void visitTypeInsn(int opcode, String type) {
		if (opcode == Opcodes.NEW) {
			//System.out.println(type + "%%%%%");
			StaticLibraryHolder.addUse(new UseSentence(type));
		}
	}

}
