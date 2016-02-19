package problem.asm.ui;

import java.awt.BorderLayout;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ImagePane extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private ImageIcon imageProxy;
		
	public ImagePane(String path) {
		
		this.setLayout(new BorderLayout());
		
		this.imageProxy = new ImageIconProxy(this, path);
		this.add(new JLabel(imageProxy), BorderLayout.LINE_START);
		
		System.out.println(new File(".").getAbsolutePath());
		
		
//		ImageIcon img = new ImageIcon("/home/nithin/workspace/CSSE374-Project/BluePizzaDiagrams/Resources/nothing_here_by_superangrybirdsfan64-d5ca8tm.png");
		
//		this.add(new JLabel(img), BorderLayout.LINE_START);
	}
	
//	public void loadImage() {
//		this.imageProxy.loadImage(null);
//	}
//	
//	public void disposeOfProxy() {
//		this.imageProxy.dispose();
//	}
	

}
