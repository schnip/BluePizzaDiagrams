package problem.asm.ui;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class PatternPage extends JFrame implements Runnable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	PatternCollection patC;
	IMakeResults results;
	JPanel menuPanel;
	JPanel imagePanel;

	public PatternPage(PatternCollection patC, IMakeResults r) {
		this.patC = patC;
		this.results = r;
		this.menuPanel = new JPanel();
		this.imagePanel = new JPanel();
		
	}
	
	public void CreatePatternPage() {
		this.add(menuPanel);
		this.add(imagePanel);
		
	}
	
	

	@Override
	public void run() {
		// TODO Auto-generated method stub

	}

}
