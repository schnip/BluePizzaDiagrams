package problem.asm.storage;

import org.objectweb.asm.Type;

public class FieldPage {
	
	private int access;
	private String name;
	private String desc;
	private String signature;
	private Object value;
	
	public FieldPage(int a, String n, String d, String s, Object v) {
		access = a;
		name = n;
		desc = d;
		signature = s;
		value = v;
	}

	public int getAccess() {
		return access;
	}

	public void setAccess(int access) {
		this.access = access;
	}
	
	@Override
	public String toString() {
		return name;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}
	
	public String getType() {
		return Type.getType(this.getDesc()).getClassName();
	}

}
