package problem.asm.outputer;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
	
	private Set<String> classes;
	private List<String> calls;
	
	private SDOutputer() {
		classes = new HashSet<String>();
		calls = new ArrayList<String>();
	}
	
	public SDOutputer(String filePath, String methodSig, String className, List<String> args) {
		this();
		this.filePath = filePath;
		this.startMethod = methodSig;
		this.depth = 5;
		this.startClass = className;
		this.startArgs = args;
	}
	
	public SDOutputer(String filePath, String methodSig, String className, List<String> args, int depth) {
		this();
		this.filePath = filePath;
		this.startMethod = methodSig;
		this.depth = depth;
		this.startClass = className;
		this.startArgs = args;
	}

	@Override
	public void outputData(MetaDataLibrary m) {
		this.classes.add(startClass + ":" + startClass);
		m.parseClass(startClass);
//		System.out.println(m.getClassVolume().toString());
		recursiveSpitter(startClass, startMethod, startArgs, m, depth-1);
		write();
	}
	
	private void recursiveSpitter(String hostClass, String hostMethod, List<String> hostArgs, MetaDataLibrary mdl, int toGo) {
//		System.out.println("time to spit");
		for (MethodCallParagraph mcp: mdl.getClassByString(hostClass).getMethodCall()) {
//			if ((mcp.getMethodName().equals(hostMethod)) && (mcp.getMethodArgs().equals(hostArgs))) {
			if (mcp.getMethodName().equals(hostMethod)) {
				// We here know that we are at a method call that we are actually to consider
				if (!mdl.contains(mcp.getOwner())) {
					mdl.parseClass(mcp.getOwner());
				}
				// Add the class if it is not already present
				this.classes.add(mcp.getOwner() + ":" + mcp.getOwner());
				
				// Add the call
				String build = hostClass + ":" + getReturnType(mdl, mcp.getOwner(), mcp.getName()) + "=" + mcp.getOwner() + "." + mcp.getName() + "(";
				if (mcp.getMethodArgs().size() > 0) {
					build += mcp.getMethodArgs().get(0);
				}
				if (mcp.getMethodArgs().size() > 1) {
					for (int i = 1; i < mcp.getMethodArgs().size(); i++) {
						build += ", ";
						build += mcp.getMethodArgs().get(i);
					}
				}
				build += ")";
				this.calls.add(build);
				
				// Determine if we want to recurse or not
				if (toGo > 0) {
					recursiveSpitter(mcp.getOwner(), mcp.getMethodName(), mcp.getMethodArgs(), mdl, toGo - 1);
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
	
	private void write() {
		try {
			// Setup the header
			PrintWriter writer = new PrintWriter(this.filePath, "UTF-8");
			for (String s : this.classes) {
				writer.println(s);
			}
			writer.println();
			for (String s : this.calls) {
				writer.println(s);
			}
//			for (ClassVolume cv : m.getClassVolume()) {
//				writer.println(cv.getName() + ":" + cv.getName());
//			}
//			writer.println("");
//			outerLoop:
//			for (ClassVolume cv : m.getClassVolume()) {
//				for (MethodCallParagraph mcp: cv.getMethodCall()) {
//					//System.out.println(mcp.getMethodName() + " " + mcp.getName() + " " + cv.getName());
//					if (mcp.getMethodName().equals("main")) {
//						recursiveSpitter(cv.getName(), "main", writer, m);
//						break outerLoop;
//					}
//				}
//			}
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
