package problem.asm.outputer;

import java.io.PrintWriter;
import java.util.List;

import org.objectweb.asm.Type;

import problem.asm.storage.ClassVolume;
import problem.asm.storage.MetaDataLibrary;
import problem.asm.storage.MethodBook;
import problem.asm.storage.MethodCallParagraph;

public class SDOutputer implements IOutputData{
	
	private String filePath;
	private String startMethod;
	private String startClass; 
	private List<String> startArgs;
	private int depth;
	
	public SDOutputer(String filePath, String methodSig, String className, List<String> args) {
		this.filePath = filePath;
		this.startMethod = methodSig;
		this.depth = 5;
		this.startClass = className;
		this.startArgs = args;
	}
	
	public SDOutputer(String filePath, String methodSig, String className, List<String> args, int depth) {
		this.filePath = filePath;
		this.startMethod = methodSig;
		this.depth = depth;
		this.startClass = className;
		this.startArgs = args;
	}

	@Override
	public void outputData(MetaDataLibrary m) {
		try {
			// Setup the header
			PrintWriter writer = new PrintWriter(this.filePath, "UTF-8");
			for (ClassVolume cv : m.getClassVolume()) {
				writer.println(cv.getName() + ":" + cv.getName());
			}
			writer.println("");
			outerLoop:
			for (ClassVolume cv : m.getClassVolume()) {
				for (MethodCallParagraph mcp: cv.getMethodCall()) {
					//System.out.println(mcp.getMethodName() + " " + mcp.getName() + " " + cv.getName());
					if (mcp.getMethodName().equals("main")) {
						recursiveSpitter(cv.getName(), "main", writer, m);
						break outerLoop;
					}
				}
			}
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void recursiveSpitter(String className, String methodHost, PrintWriter writer, MetaDataLibrary mdl) {
		for (ClassVolume cv : mdl.getClassVolume()) {
			if (cv.getName().equals(className)) {
				for (MethodCallParagraph mcp : cv.getMethodCall()) {
					if (mcp.getMethodName().equals(methodHost)) {
						if (mdl.contains(mcp.getOwner())) {
							writer.println(cv.getName() + ":" + getReturnType(mdl, mcp.getOwner(), mcp.getName()) + "=" + mcp.getOwner() + "." + mcp.getName());
							recursiveSpitter(mcp.getOwner(), mcp.getName(), writer, mdl);
						}
					}
				}
			}
		}
	}

	private String getReturnType(MetaDataLibrary mdl, String owner, String name) {
		for (ClassVolume cv : mdl.getClassVolume()) {
			if (cv.getName().equals(owner)) {
				for (MethodBook mb : cv.getMethods()) {
					if (mb.getName().equals(name)) {
						return Type.getReturnType(mb.getDesc()).getClassName();
					}
				}
			}
		}
		return null;
	}

}
