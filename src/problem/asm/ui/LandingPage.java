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

public class LandingPage extends JFrame implements Runnable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JButton loadConfig;
	JButton analyze;
	JPanel landingPanel;
	JLabel landingLabel;
	JProgressBar loadingBar;
	JFileChooser fileChooser;
	File selectedFile;
	
	boolean analyzeToRun;

	static final int MIN = 0;
	static final int MAX = 100;

	public LandingPage() {
		this.loadConfig = new JButton("Load Config");
		this.analyze = new JButton("Analyze");
		this.landingPanel = new JPanel();
		this.landingLabel = new JLabel("Loading");
		this.loadingBar = new JProgressBar();
		this.loadingBar.setMinimum(MIN);
		this.loadingBar.setMaximum(MAX);
		this.fileChooser = new JFileChooser();
		this.selectedFile = null;
		this.analyzeToRun = false;
		
	}

	public void createMenu() {
		this.add(this.landingPanel);
		this.landingPanel.add(this.loadConfig);
		this.landingPanel.add(this.analyze);
		this.landingPanel.add(this.landingLabel);
		this.landingPanel.add(this.loadingBar);
		
		this.loadConfig.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				fileChooser.setCurrentDirectory(new File("/home/nithin/workspace/CSSE374-Project/BluePizzaDiagrams"));
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
		});
		
		this.analyze.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				LandingPage.this.analyzeToRun = true;
			}
		});
		
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		
		this.pack();
	}

	public String getConfigPath() {
		createMenu();
		this.selectedFile = null;
		if (this.selectedFile == null) System.out.println("null it is");
//		boolean filler = false;
		while (this.selectedFile == null || !this.analyzeToRun) {
			System.out.print("");
		}
		System.out.println("about to return selected file");
		return this.selectedFile.toString();
	}	
	
	public void finishLoadingBar(PatternCollection patC, IMakeResults r) {
		this.loadingBar.setValue(MAX);
		
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		this.setVisible(false);
		this.dispose();
	}
	
	public void addToLoadingBar() {
		int currentVal = this.loadingBar.getValue();
		this.loadingBar.setValue(currentVal + 1);
	}

	@Override
	public void run() {
		System.out.println("run method being called :)");
	}

}
