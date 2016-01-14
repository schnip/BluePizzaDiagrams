package problem.asm.outputer;

import java.io.PrintWriter;

import problem.asm.storage.ClassVolume;
import problem.asm.storage.MetaDataLibrary;
import problem.asm.storage.MethodCallParagraph;

public class SDOutputer implements IOutputData{
	
	private String filePath;
	
	public SDOutputer(String s) {
		filePath = s;
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
							writer.println(cv.getName() + ":done=" + mcp.getOwner() + "." + mcp.getName());
							recursiveSpitter(mcp.getOwner(), mcp.getName(), writer, mdl);
						}
					}
				}
			}
		}
	}

}
