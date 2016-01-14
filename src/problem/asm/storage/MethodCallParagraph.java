package problem.asm.storage;

public class MethodCallParagraph {
	
	private int opcode;
	private String owner;
	private String name;
	private String desc;
	private boolean itf;
	private String methodName;
	
	public MethodCallParagraph(int opcode, String owner, String name, String desc, boolean itf, String methodName) {
		this.opcode = opcode;
		this.owner = owner;
		this.name = name;
		this.desc = desc;
		this.itf = itf;
		this.methodName = methodName;
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
	
	

}
