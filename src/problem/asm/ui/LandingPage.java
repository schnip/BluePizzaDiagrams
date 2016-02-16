package problem.asm.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

public class LandingPage extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JButton loadConfig;
	JButton analyze;
	// JFrame landingFrame;
	JPanel landingPanel;
	JLabel landingLabel;
	JProgressBar loadingBar;
	JFileChooser fileChooser;
	File selectedFile;

	static final int MIN = 0;
	static final int MAX = 100;

	public LandingPage() {
		this.loadConfig = new JButton("Load Config");
		this.analyze = new JButton("Analyze");
		// this.landingFrame = new JFrame();
		this.landingPanel = new JPanel();
		this.landingLabel = new JLabel();
		this.loadingBar = new JProgressBar();
		this.loadingBar.setMinimum(MIN);
		this.loadingBar.setMaximum(MAX);
		this.fileChooser = new JFileChooser();
		this.selectedFile = null;
	}

	public void createMenu() {
		this.add(this.landingPanel);
		this.loadConfig.addActionListener(new fileChoose());
		this.landingPanel.add(this.loadConfig);
		this.landingPanel.add(this.analyze);
		this.landingPanel.add(this.landingLabel);
		this.landingPanel.add(this.loadingBar);

	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);

	}

	public String getConfigPath() {

		return "";
	}

	class fileChoose implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			fileChooser.setMultiSelectionEnabled(false);
			fileChooser.setDialogTitle("Choose a File!");
			int returnVal = fileChooser.showOpenDialog(LandingPage.this);
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				File file = fileChooser.getSelectedFile();
				try {
					// return the file path
					LandingPage.this.selectedFile = file;
				} catch (Exception ex) {
					System.out.println("problem accessing file" + file.getAbsolutePath());
				}
			} else {
				System.out.println("File access cancelled by user.");
			}
		}
	}

}
