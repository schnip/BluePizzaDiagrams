package problem.asm.visitor;

import java.util.ArrayList;
import java.util.List;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;

import problem.asm.storage.MethodBook;
import problem.asm.storage.StaticLibraryHolder;

public class ClassMethodVisitor extends ClassVisitor {

	public ClassMethodVisitor(int arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	public ClassMethodVisitor(int arg0, ClassVisitor arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
		MethodVisitor toDecorate = super.visitMethod(access, name, desc, signature, exceptions);
		
		//String returnType = Type.getReturnType(desc).getClassName();
		
		Type[] argTypes = Type.getArgumentTypes(desc);
		
//		String[] stypes = new String[argTypes.length];
		
		List<String> stypes = new ArrayList<String>();
		for (Type t : argTypes) {
			stypes.add(t.getClassName());
			
		}
		
		/*
		String symbol = "";
		if ((access & Opcodes.ACC_PUBLIC) != 0) {
			symbol = "+";
		}*/
		
//		System.out.println("     method " + symbol + returnType + " " + name + " ");
		StaticLibraryHolder.addMethod(new MethodBook(access, name, desc, signature, exceptions, stypes));
		stypes.toString();
		
		return new MethodInternalsVisitor(Opcodes.ASM5, toDecorate, name, stypes);
	}

}
