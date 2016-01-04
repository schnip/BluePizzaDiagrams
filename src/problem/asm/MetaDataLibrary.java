package problem.asm;

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

	public List<ClassVolume> getClassvolume() {
		return classvolume;
	}

}
