package problem.asm;

import java.io.IOException;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.Opcodes;

import problem.asm.outputer.DiagramOutputer;
import problem.asm.outputer.IOutputData;
import problem.asm.storage.StaticLibraryHolder;
import problem.asm.visitor.ClassDeclarationVisitor;
import problem.asm.visitor.ClassFieldVisitor;
import problem.asm.visitor.ClassMethodVisitor;

public class DesignParser {
	public static void main(String[] args) throws IOException{
		StaticLibraryHolder.initializeLibrary();
		for (String className : args) {
			StaticLibraryHolder.setClassname(className.replace('.', '/'));
			
			// ASM's ClassReader does the heavy lifting of parsing the compiled Java class
			ClassReader reader = new ClassReader(className);
			
			// make class declaration visitor to get superclass and interfaces
			ClassVisitor decVisitor = new ClassDeclarationVisitor(Opcodes.ASM5);
				
			// DECORATE declaration visitor with field visitor
			ClassVisitor fieldVisitor = new ClassFieldVisitor(Opcodes.ASM5, decVisitor);
			
			// DECORATE field visitor with method visitor
			ClassVisitor methodVisitor = new ClassMethodVisitor(Opcodes.ASM5, fieldVisitor);
			// TODO: add more DECORATORS here in later milestones to accomplish specific tasks
			// Tell the Reader to use our (heavily decorated) ClassVisitor to visit the class
			reader.accept(methodVisitor, ClassReader.EXPAND_FRAMES);
		}
		IOutputData iod = new DiagramOutputer("dot/out.dot");
		iod.outputData(StaticLibraryHolder.getLibrary());
	}
}
