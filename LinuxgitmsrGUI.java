package linuxgitmsr;

import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.util.ArrayList;

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

public class LinuxgitmsrGUI {

	private JFrame frame;
	
	private static Connection con = null;
	private static ArrayList<CommitInfo> al = null;
	private static ArrayList<CommitInfo> mcidlinus = null;
	private static DbConnect postgres = null;
	private static CommitTree ct = null;


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
		
		// frame -> container -> ScrollPane -> Tree
		Container content = frame.getContentPane();
		JTree tree = new JTree();
		JScrollPane scrolltree = new JScrollPane(tree);
		content.add(scrolltree, BorderLayout.CENTER);
		//frame.setVisible(true);		
		
		//Step 1: checking the postgres connection with DB linuxgitmsr
		postgres = new DbConnect();
		//Step 2: Connection to the DB
		con = postgres.connectToPg();
		//Step 3: Pass the connection object to CommitTree
		ct = new CommitTree();
		ct.setConnection(con);
		//Step 4: get mcidlinus in an ArrayList
		mcidlinus = ct.getMcidlinus();
		//Step 5: Close Connection to DB
		postgres.closeConnection(con);
				
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
		System.out.println("you have clicked "+commit);
		JOptionPane.showMessageDialog(null, commit+" :My Goodness, this is so concise");
		
	}

}
