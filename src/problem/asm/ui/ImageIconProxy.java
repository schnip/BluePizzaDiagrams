package problem.asm.ui;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class ImageIconProxy extends ImageIcon {

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
	private boolean stopThread;

	public ImageIconProxy(JPanel returnPanel, String imgPath) {
		super(NOIMAGE.getImage());
		this.returnPanel = returnPanel;
		this.imgPath = imgPath;
		this.stopThread = false;
	}

	// public void loadImage() {
	// if(this.currentImage != LOADING) {
	// this.currentImage = LOADING;
	// updateCallback();
	// this.thread = new Thread(this);
	// this.thread.start();
	// }
	// }

	// public void dispose() {
	// this.stopThread = true;
	// }

	public void consumeNewImage(String newPath) {
		this.setCurrentImage(new ImageIcon(newPath));
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
		this.currentImage = image;
	}
	
}