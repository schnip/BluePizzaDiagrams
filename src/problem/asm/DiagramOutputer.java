package problem.asm;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;

public class DiagramOutputer implements IOutputData {

	private String filePath;
	private List<String> subclasses = new ArrayList<String>();
	private List<String> interfaces = new ArrayList<String>();
	private List<String> uses = new ArrayList<String>();
	private List<String> associates = new ArrayList<String>();

	public DiagramOutputer(String s) {
		filePath = s;
	}

	@Override
	public void outputData(MetaDataLibrary m) {
		try {
			// Setup the header
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

			// Draw the class boxes
			for (ClassVolume v : m.getClassVolume()) {
				// Start the class box
				writer.println(v.getName().replaceAll("/", "") + " [");
				writer.print("label = \"{" + v.getName().replace('/', '.') + "|");
				
				// Save superclass data
				if (m.contains(v.getSuperName())) {
					this.subclasses.add(v.getName().replaceAll("/", "") + " -> " + v.getSuperName().replaceAll("/", ""));
				}
				
				// Save interface data
				for (String s : v.getInterfaces()) {
					if (m.contains(s)) {
						this.interfaces.add(v.getName().replaceAll("/", "") + " -> " + s.replaceAll("/", ""));
					}
				}
				
				// Draw the fields
				for (FieldPage fp : v.getFields()) {
					String type = Type.getType(fp.getDesc()).getClassName();
					if (fp.getSignature() != null) {
						type = parseStringForT(fp.getSignature());
					}
					writer.print(fp.getName() + " : " + type + "\\l");
					
					// Stash information for association arrows
					if (m.contains(type.replace('.', '/'))) {
						this.associates.add(v.getName().replaceAll("/", "") + " -> " + type.replace(".", ""));
					}
				}
				writer.print("|");
				
				// Draw the methods
				for (MethodBook mb : v.getMethods()) {
					String symbol = "";
					if ((mb.getAccess() & Opcodes.ACC_PUBLIC) != 0) {
						symbol = "+";
					}
					String returnType = Type.getReturnType(mb.getDesc()).getClassName();
					if (mb.getSignature() != null) {
						returnType = parseStringForT(mb.getSignature());
					}
					
					writer.print(symbol + specialSnowflake(mb.getName()) + " : " + returnType + "\\l");
					
					// Stash information for uses arrows
					for (String type : mb.getArgTypes()) {
						if (m.contains(type.replace('.', '/'))) {
							this.uses.add(v.getName().replaceAll("/", "") + " -> " + type.replace(".", ""));
						}
					}
				}
				
				// Close the class block
				writer.print("}\"");
				writer.println();
				writer.println("]");
			}
			
			// Draw subclass arrows
			writer.println("edge [ arrowhead = \"empty\", style = \"none\" ]");
			for (String s : this.subclasses) {
				writer.println(s);
			}
			
			// Draw interface arrows
			writer.println("edge [ arrowhead = \"empty\", style = \"dashed\" ]");
			for (String s : this.interfaces) {
				writer.println(s);
			}
			
			// Draw uses arrows
			writer.println("edge [ arrowhead = \"vee\", style = \"dashed\" ]");
			for (String s : this.uses) {
				writer.println(s);
			}
			
			// Draw associate arrows
			writer.println("edge [ arrowhead = \"vee\", style = \"none\" ]");
			for (String s : this.associates) {
				writer.println(s);
			}
			
			// Close up shop
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
	
	private String parseStringForT(String s) {
		return s.substring(s.indexOf('<')).replace("<L", "").replace(";>;", "");
	}

}