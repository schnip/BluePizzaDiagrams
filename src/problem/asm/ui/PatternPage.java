package problem.asm.ui;


import java.awt.BorderLayout;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import problem.asm.swingcheckbox.main.java.org.scijava.swing.checkboxtree.CheckBoxNodeData;
import problem.asm.swingcheckbox.main.java.org.scijava.swing.checkboxtree.CheckBoxNodeEditor;
import problem.asm.swingcheckbox.main.java.org.scijava.swing.checkboxtree.CheckBoxNodeRenderer;

public class PatternPage extends JFrame implements Runnable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	PatternCollection patC;
	IMakeResults results;
	JPanel menuPanel;
	ImagePane imagePanel;
	JPanel otherPanel;
	
	DefaultMutableTreeNode root;
	
	HashMap<String, Boolean> isChecked;

	public PatternPage(PatternCollection patC, IMakeResults r) {
		this.patC = patC;
		this.results = r;
		this.menuPanel = new JPanel();
		this.imagePanel = new ImagePane("/BluePizzaDiagrams/dot/out.png");
		this.isChecked = new HashMap<String, Boolean>();
	}

	public void createPatternPage() {
		this.setLayout(new BorderLayout());
		this.add(menuPanel, BorderLayout.WEST);
		this.add(imagePanel, BorderLayout.EAST);

		this.root = createMenu(this.patC);

		DefaultTreeModel treeModel = new DefaultTreeModel(root);
		JTree tree = new JTree(treeModel);

		CheckBoxNodeRenderer renderer = new CheckBoxNodeRenderer();
		tree.setCellRenderer(renderer);

		CheckBoxNodeEditor editor = new CheckBoxNodeEditor(tree);
		tree.setCellEditor(editor);
		tree.setEditable(true);

		// listen for changes in the selection
		tree.addTreeSelectionListener(new TreeSelectionListener() {
			@Override
			public void valueChanged(final TreeSelectionEvent e) {
				System.out.println(System.currentTimeMillis() + ": selection changed");
				System.out.println("source:   " + e.getSource());
				System.out.println("string:   " + e.toString());
				System.out.println("path  :   " + e.getPath());
				System.out.println(root.toString());
				System.out.println(root.getFirstChild().toString());
				
			}
		});

		// listen for changes in the model (including check box toggles)
		treeModel.addTreeModelListener(new TreeModelListener() {
			@Override
			public void treeNodesChanged(final TreeModelEvent e) {
//				System.out.println(System.currentTimeMillis() + ": nodes changed");
				
			}
			@Override
			public void treeNodesInserted(final TreeModelEvent e) {
				System.out.println(System.currentTimeMillis() + ": nodes inserted");
			}
			@Override
			public void treeNodesRemoved(final TreeModelEvent e) {
				System.out.println(System.currentTimeMillis() + ": nodes removed");
			}
			@Override
			public void treeStructureChanged(final TreeModelEvent e) {
				System.out.println(System.currentTimeMillis() + ": structure changed");
			}
		});
		JScrollPane scrollPane = new JScrollPane(tree);
		this.getContentPane().add(scrollPane, BorderLayout.CENTER);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(800, 800);
		this.setVisible(true);
	}
	
	public DefaultMutableTreeNode createMenu(PatternCollection pc) {
		CheckBoxNodeData data = new CheckBoxNodeData(pc.getTitle(), false);
		this.isChecked.put(pc.getTitle(), false);
		DefaultMutableTreeNode node = new DefaultMutableTreeNode(data);
		System.out.println(node.toString());
		
		System.out.println("collection size:    " + pc.getSubCollection().size());
		for (PatternCollection n : pc.getSubCollection()) {
			node.add(createMenu(n));
		}
		return node;
	}
	
	public HashMap<String, Boolean> getisChecked() {
		return this.isChecked;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub

	}

}
