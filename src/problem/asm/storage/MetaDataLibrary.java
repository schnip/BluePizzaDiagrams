package problem.asm.storage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.Opcodes;

import problem.asm.visitor.ClassDeclarationVisitor;
import problem.asm.visitor.ClassFieldVisitor;
import problem.asm.visitor.ClassMethodVisitor;

public class MetaDataLibrary {
	
	private List<ClassVolume> classvolume;
	
	public MetaDataLibrary() {
		this.classvolume = new ArrayList<ClassVolume>();
	}
	
	public void addClass(ClassVolume cv) {
		this.classvolume.add(cv);
	}

	public List<ClassVolume> getClassVolume() {
		return classvolume;
	}
	
	public void addMethod(MethodBook mb, String classname) {
		for (ClassVolume cv : classvolume) {
			if (cv.getName().equals(classname)) {
				cv.addMethod(mb);
			}
		}
	}
	
	public void addField(FieldPage fp, String classname) {
		for (ClassVolume cv : classvolume) {
			if (cv.getName().equals(classname)) {
				cv.addField(fp);
			}
		}
	}
	
	public boolean contains(String classname) {
		for (ClassVolume cv : this.classvolume) {
			if (classname.equals(cv.getName())) {
				return true;
			}
		}
		return false;
	}

	public void addUse(UseSentence us, String classname) {
		for (ClassVolume cv : classvolume) {
			if (cv.getName().equals(classname)) {
				cv.addUse(us);
			}
		}
	}

	public void addMethodCall(MethodCallParagraph mcp, String classname) {
		for (ClassVolume cv : classvolume) {
			if (cv.getName().equals(classname)) {
				cv.addMethodCall(mcp);
			}
		}
	}
	
	public void parseClass(String className) {
		StaticLibraryHolder.setLibrary(this);
		StaticLibraryHolder.setClassname(className.replace('.', '/'));
		
		// ASM's ClassReader does the heavy lifting of parsing the compiled Java class
		ClassReader reader = null;
		try {
			reader = new ClassReader(className);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
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

}
