package problem.asm.visitor;

import org.objectweb.asm.MethodVisitor;

import com.sun.xml.internal.ws.org.objectweb.asm.Opcodes;

import problem.asm.storage.StaticLibraryHolder;
import problem.asm.storage.UseSentence;

public class MethodInternalsVisitor extends MethodVisitor {

	public MethodInternalsVisitor(int arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	public MethodInternalsVisitor(int arg0, MethodVisitor arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void visitMethodInsn(int opcode, String owner, String name, String desc, boolean itf) {
		//System.out.println(owner + " " + name);
		StaticLibraryHolder.addUse(new UseSentence(owner));
	}
	
	@Override
	public void visitTypeInsn(int opcode, String type) {
		if (opcode == Opcodes.NEW) {
			//System.out.println(type + "%%%%%");
			StaticLibraryHolder.addUse(new UseSentence(type));
		}
	}

}
