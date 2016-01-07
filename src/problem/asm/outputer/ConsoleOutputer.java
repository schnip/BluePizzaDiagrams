package problem.asm.outputer;

import java.util.Arrays;

import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;

import problem.asm.storage.ClassVolume;
import problem.asm.storage.FieldPage;
import problem.asm.storage.MetaDataLibrary;
import problem.asm.storage.MethodBook;

public class ConsoleOutputer implements IOutputData {

	@Override
	public void outputData(MetaDataLibrary m) {
		for (ClassVolume v : m.getClassVolume()) {
			System.out.println("Class: " + v.getName() + " extends " + v.getSuperName() + " implements "
					+ Arrays.toString(v.getInterfaces()));
			for (FieldPage fp : v.getFields()) {
				String type = Type.getType(fp.getDesc()).getClassName();
				System.out.println("   " + type + " " + fp.getName());
			}
			for (MethodBook mb : v.getMethods()) {
				String symbol = "";
				if ((mb.getAccess() & Opcodes.ACC_PUBLIC) != 0) {
					symbol = "+";
				}
				String returnType = Type.getReturnType(mb.getDesc()).getClassName();
				System.out.println("     method " + symbol + returnType + " " + mb.getName() + " ");
			}
		}
	}

}
