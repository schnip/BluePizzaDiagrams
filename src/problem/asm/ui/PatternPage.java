package problem.asm.ui;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

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
		DefaultMutableTreeNode root = createMenu(this.patC);

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
			}
		});

		// listen for changes in the model (including check box toggles)
		treeModel.addTreeModelListener(new TreeModelListener() {

			@Override
			public void treeNodesChanged(final TreeModelEvent e) {
				System.out.println(System.currentTimeMillis() + ": nodes changed");
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

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);

	}

	public DefaultMutableTreeNode createMenu(PatternCollection pc) {
		DefaultMutableTreeNode node = new DefaultMutableTreeNode(pc.getTitle());
		for (PatternCollection n : pc.getSubCollection()) {
			node.add(createMenu(n));
		}
		return node;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub

	}

}
