package problem.asm.ui;

import java.awt.BorderLayout;

import javax.swing.ImageIcon;
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
		
//		System.out.println(new File(".").getAbsolutePath());
	}
	
//	public void loadImage() {
//		this.imageProxy.loadImage();
//	}
	
//	public void disposeOfProxy() {
//		this.imageProxy.dispose();
//	}
	
	public void consumeNewImage(String newPath) {
		// TODO write code that goes here
		// This method takes the path and draws the image
		this.imageProxy.consumeNewImage(newPath);
	}
	

}
