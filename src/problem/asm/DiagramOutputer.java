package problem.asm;

import java.io.PrintWriter;

import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;

public class DiagramOutputer implements IOutputData {

	private String filePath;

	public DiagramOutputer(String s) {
		filePath = s;
	}

	@Override
	public void outputData(MetaDataLibrary m) {
		try {
			PrintWriter writer = new PrintWriter(this.filePath, "UTF-8");
			writer.println("digraph G {");
			writer.println("fontname = \"Bitstream Vera Sans\"");
			writer.println("fontsize = 8");
			writer.println("node [");
			writer.println("fontname = \"Bitstream Vera Sans\"");
			writer.println("fontsize = 8");
			writer.println("shape = \"record\"");
			writer.println("]");
			writer.println("edge [");
			writer.println("fontname = \"Bitstream Vera Sans\"");
			writer.println("fontsize = 8");
			writer.println("]");

			for (ClassVolume v : m.getClassVolume()) {
				writer.println(v.getName().replaceAll("/", "") + " [");
				writer.print("label = \"{" + v.getName().replace('/', '.') + "|");
				for (FieldPage fp : v.getFields()) {
					String type = Type.getType(fp.getDesc()).getClassName();
					writer.print(fp.getName() + " : " + type + "\\l");
				}
				writer.print("|");
				for (MethodBook mb : v.getMethods()) {
					String symbol = "";
					if ((mb.getAccess() & Opcodes.ACC_PUBLIC) != 0) {
						symbol = "+";
					}
					String returnType = Type.getReturnType(mb.getDesc()).getClassName();
					writer.print(symbol + specialSnowflake(mb.getName()) + " : " + returnType + "\\l");
				}
				writer.print("}\"");
				writer.println();
				writer.println("]");
			}
			writer.println("}");
			writer.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private String specialSnowflake(String s) {
		String ret = new String(s);
		ret = ret.replace("<", "\\<");
		ret = ret.replace(">", "\\>");
		return ret;
	}

}
