package problem.asm.storage;

import java.util.ArrayList;
import java.util.List;

public class ClassVolume {
	
	private List<MethodBook> methods;
	private List<FieldPage> fields;
	private List<UseSentence> uses;
	private List<MethodCallParagraph> methodcalls;
	private int version;
	private int access;
	private String name;
	private String signature;
	private String superName;
	private String[] interfaces;
	
	public ClassVolume(int version, int access, String name, String signature, String superName, String[] interfaces) {
		this.version = version;
		this.access = access;
		this.name = name;
		this.signature = signature;
		this.superName = superName;
		this.interfaces = interfaces;
		
		this.methods = new ArrayList<MethodBook>();
		this.fields = new ArrayList<FieldPage>();
		this.uses = new ArrayList<UseSentence>();
		this.methodcalls = new ArrayList<MethodCallParagraph>();
	}
	
	public void addMethod(MethodBook m) {
		this.methods.add(m);
	}
	
	public void addField(FieldPage f) {
		this.fields.add(f);
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public int getAccess() {
		return access;
	}

	public void setAccess(int access) {
		this.access = access;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public String getSuperName() {
		return superName;
	}

	public void setSuperName(String superName) {
		this.superName = superName;
	}

	public String[] getInterfaces() {
		return interfaces;
	}

	public void setInterfaces(String[] interfaces) {
		this.interfaces = interfaces;
	}

	public List<MethodBook> getMethods() {
		return methods;
	}

	public List<FieldPage> getFields() {
		return fields;
	}

	public void addUse(UseSentence us) {
		this.uses.add(us);
	}

	public List<UseSentence> getUses() {
		return uses;
	}

	public void addMethodCall(MethodCallParagraph mcp) {
		this.methodcalls.add(mcp);
	}
	
	public List<MethodCallParagraph> getMethodCall() {
		return methodcalls;
	}

}
