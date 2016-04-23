package linuxgitmsr;

import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTree;
import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.JScrollPane;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.JSplitPane;
import java.awt.Rectangle;

public class LinuxgitmsrGUI {

	private JFrame frame;
	
	private static Connection con = null;
	private static ArrayList<CommitInfo> al = null;
	private static ArrayList<CommitInfo> mcidlinus = null;
	private static CommitTree ct = null;
	private static JEditorPane editor = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LinuxgitmsrGUI window = new LinuxgitmsrGUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public LinuxgitmsrGUI() {
		initialize();
	}
	

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		frame = new JFrame();
		frame.setBounds(100, 100, 1019, 813);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// frame -> container -> outerbox -> splitpane -> left panel -> ScrollPane -> Tree
		//											   -> right panel
		 JSplitPane splitPane = new JSplitPane();
	     JPanel leftPanel = new JPanel();
	     JTree tree = new JTree();
	     JPanel rightPanel = new JPanel();
	     editor = new JEditorPane();
	     JScrollPane scroller = new JScrollPane(editor);
	     JScrollPane treeScroller = new JScrollPane(tree);

	     JPanel outerBox = new JPanel();

	     leftPanel.setLayout(new BorderLayout() );
	     leftPanel.add(treeScroller, BorderLayout.CENTER);
	    
	     rightPanel.setLayout(new BorderLayout() );
	     rightPanel.add(scroller, BorderLayout.CENTER);

	     splitPane.setLeftComponent(leftPanel);
	     splitPane.setRightComponent(rightPanel);

	     outerBox.setLayout (new BorderLayout ( ) );
	     outerBox.add ("Center", splitPane);

	     frame.getContentPane().add(outerBox);
		
		//Step 1: get mcidlinus commits from CommitTree
	     ct = new CommitTree();
	     mcidlinus = ct.getMcidlinus();
						
		// add mcidlinus to tree node
		
			tree.setModel(new DefaultTreeModel(
			new DefaultMutableTreeNode("Hierarchy") {
		{
			DefaultMutableTreeNode node_1;
			node_1 = new DefaultMutableTreeNode("mcidlinus");
			for (int i=0; i<mcidlinus.size(); i++){
				node_1.add(new DefaultMutableTreeNode(mcidlinus.get(i).getMcidlinus()));
				add(node_1);
			}
		}
			}
		));
								
		// mouse onclick event		
		MouseListener ml = new MouseAdapter() {
		    public void mousePressed(MouseEvent e) {
		        int selRow = tree.getRowForLocation(e.getX(), e.getY());
		        TreePath selPath = tree.getPathForLocation(e.getX(), e.getY());
		        if(selRow != -1) {
		            if(e.getClickCount() == 1) {
		            	DefaultMutableTreeNode node =(DefaultMutableTreeNode)tree.getSelectionPath().getLastPathComponent();
		            	String commit = (String) node.getUserObject();
		            	mySingleClick(selRow, selPath, commit);		               
		            }
		            //else if(e.getClickCount() == 2) {
		            //    myDoubleClick(selRow, selPath);
		            //}
		        }
		    }
		};
		tree.addMouseListener(ml);
			
	}

	public void mySingleClick(int selRow, TreePath selPath, String commit) {
			
			ct = new CommitTree();
			//Step 1: get mcidlinus commits from CommitTree
			mcidlinus = ct.getMcidlinus();
			//Step 2: Get Tree			
			al = ct.getCommitTree(commit);
			//Step 3: Print CommitTree in an ArrayList
			ct.printCommitTree(al);
			
		     System.out.println("you have clicked "+commit);
		     //JOptionPane.showMessageDialog(null, commit+" :My Goodness, this is so concise");
		     
		     String heading = "\n \t\tmcidlinus \t\t\t\t mnextmerge \t\t\t\t mnext \t\t\t\t cid \t\t repo \t\t mdist \n";
		     String concatenate = "";
		     String commitTree = "";
		     
		     for (int i=0; i<al.size(); i++){
		    	 
		    	 concatenate = "\n"+al.get(i).getMcidlinus()+"\t\t"
		    			 		+al.get(i).getMnextmerge()+"\t\t"+al.get(i).getMnext()+"\t\t"+al.get(i).getCid()+
		    			 		"\t\t"+al.get(i).getRepo()+"\t"+al.get(i).getMdist();
		    	
		    	commitTree = commitTree+concatenate; 
		    	 //editor.setText(al.get(i).getComdate());
		    	 //editor.setText(al.get(i).getMwhen());
			}
		     commitTree = heading+commitTree;
		     editor.setText(commitTree);	     
		
	}

}
