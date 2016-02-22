package problem.asm.ui;

import java.awt.Component;
import java.awt.Graphics;
import java.io.File;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

@SuppressWarnings("unused")
public class ImageIconProxy extends ImageIcon implements Icon {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static final ImageIcon NOIMAGE = new ImageIcon("Resources/nothing_here_by_superangrybirdsfan64-d5ca8tm.png");
	// public static final ImageIcon LOADING = new
	// ImageIcon("Resources/spiffygif_114x114.gif");

	private ImageIcon currentImage = NOIMAGE;
	private String imgPath;
	private JPanel returnPanel;	
	private Thread thread;

	public ImageIconProxy(JPanel returnPanel, String imgPath) {
		super();
//		super(NOIMAGE.getImage());
		this.returnPanel = returnPanel;
		this.imgPath = "Resources/nothing_here_by_superangrybirdsfan64-d5ca8tm.png";
	}

	public void consumeNewImage(String newPath) {
		String inp = new File(newPath).getAbsolutePath();
		System.out.println("new abs path:     " + inp);
		
		ImageIcon imgIcon = new ImageIcon(inp);
		
//		Image toret = imgIcon.getImage();
		
		System.out.println("width:   " + imgIcon.getIconWidth());
		System.out.println("     " + imgIcon.getDescription());
		this.setCurrentImage(imgIcon);
		
		System.out.println("!@@!#!#$@$#@$#$@#$#@$#$#@@$#$#@!!#@#@!#@!#!");
		
	}

	public int getIconHeight() {
		return this.currentImage.getIconHeight();
	}

	public int getIconWidth() {
		return this.currentImage.getIconWidth();
	}

	public synchronized void paintIcon(Component c, Graphics g, int x, int y) {
		this.currentImage.paintIcon(c, g, x, y);
	}

	public ImageIcon getCurrentImage() {
		return this.currentImage;
	}

	public void setCurrentImage(ImageIcon image) {
		System.out.println("HASHCODE OF CURRENT IMAGE IS:   " + this.currentImage.hashCode());
		this.currentImage = image;
		System.out.println("HASHCODE OF CURRENT IMAGE IS (AFTER) :   " + this.currentImage.hashCode());
	}
	
}