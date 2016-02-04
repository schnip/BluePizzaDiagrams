package problem.food.treefood;

import java.util.LinkedList;
import java.util.List;

public class Tray {
	
	private List<Tray> contents;
	
	public Tray() {
		contents = new LinkedList<Tray>();
	}
	
	public void addFood(Tray t) {
		contents.add(t);
	}
	
	public void removeFood(Tray t) {
		contents.remove(t);
	}
	
	public void flipTray() {
		
	}

}
