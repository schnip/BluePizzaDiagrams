package problem.asm.storage;

import java.util.List;

public class MethodCallParagraph {
	
	private int opcode;
	private String owner;
	private String name;
	private String desc;
	private boolean itf;
	private String methodName;
	private List<String> methodArgs;
	
	public MethodCallParagraph(int opcode, String owner, String name, String desc, boolean itf, String methodName, List<String> methodArgs) {
		this.opcode = opcode;
		this.owner = owner;
		this.name = name;
		this.desc = desc;
		this.itf = itf;
		this.methodName = methodName;
		this.methodArgs = methodArgs;
	}

	public int getOpcode() {
		return opcode;
	}

	public String getOwner() {
		return owner;
	}

	public String getName() {
		return name;
	}

	public String getDesc() {
		return desc;
	}

	public boolean isItf() {
		return itf;
	}
	
	public String getMethodName() {
		return methodName;
	}
	
	public List<String> getMethodArgs() {
		return methodArgs;
	}

}
