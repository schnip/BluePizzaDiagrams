package problem.asm.ui;

import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class ImagePane extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private ImageIconProxy imageProxy;
	
	public ImagePane(String path) {
		
		this.setLayout(new BorderLayout());
		
		this.imageProxy = new ImageIconProxy(this, path);
		this.add(new JLabel(imageProxy), BorderLayout.LINE_START);
	}
	
	public void loadImage() {
		this.imageProxy.loadImage();
	}
	
	public void disposeOfProxy() {
		this.imageProxy.dispose();
	}
	

}
