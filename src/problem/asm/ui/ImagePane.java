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
	
	JLabel saveLabel;
		
	public ImagePane(String path) {
		
		this.setLayout(new BorderLayout());
		
		this.imageProxy = new ImageIconProxy(this, path);
		
		this.saveLabel = new JLabel(imageProxy);
		this.add(this.saveLabel, BorderLayout.LINE_START);
		
	}
	
	public void consumeNewImage(String newPath) {
		// This method takes the path and draws the image
		this.setLayout(new BorderLayout());
		this.imageProxy.consumeNewImage(newPath);
//		this.remove(saveLabel);
		saveLabel = new JLabel(imageProxy);
		this.add(saveLabel, BorderLayout.LINE_START);
		System.out.println("****************************************** hihihihihih **********************************************");
//		this.imageProxy = new ImageIconProxy(this, newPath);
//		this.add(new JLabel(this.imageProxy), BorderLayout.LINE_START);
//		this.saveLabel.setIcon(this.imageProxy);
		this.repaint();
	
	}
	

}
