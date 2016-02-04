package problem.food.treefood;

import java.util.LinkedList;
import java.util.List;

public class Bowl extends Tray {
	
	private List<Tray> contents;
	
	public Bowl() {
		contents = new LinkedList<Tray>();
	}
	
	public void addFood(Tray t) {
		contents.add(t);
	}
	
	public void removeFood(Tray t) {
		contents.remove(t);
	}
	
	public void slupFrom() {
		
	}

}
