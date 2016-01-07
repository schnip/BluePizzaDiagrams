package problem.asm.storage;

import java.util.ArrayList;
import java.util.List;

public class MetaDataLibrary {
	
	private List<ClassVolume> classvolume;
	
	public MetaDataLibrary() {
		this.classvolume = new ArrayList<ClassVolume>();
	}
	
	public void addClass(ClassVolume cv) {
		this.classvolume.add(cv);
	}

	public List<ClassVolume> getClassVolume() {
		return classvolume;
	}
	
	public void addMethod(MethodBook mb, String classname) {
		for (ClassVolume cv : classvolume) {
			if (cv.getName().equals(classname)) {
				cv.addMethod(mb);
			}
		}
	}
	
	public void addField(FieldPage fp, String classname) {
		for (ClassVolume cv : classvolume) {
			if (cv.getName().equals(classname)) {
				cv.addField(fp);
			}
		}
	}
	
	public boolean contains(String classname) {
		for (ClassVolume cv : this.classvolume) {
			if (classname.equals(cv.getName())) {
				return true;
			}
		}
		return false;
	}

}
