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
			for (ClassVolume cv : m.getClassVolume()) {
				for (MethodCallParagraph mcp: cv.getMethodCall()) {
					
				}
			}
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
