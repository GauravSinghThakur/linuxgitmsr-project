package linuxgitmsr;

import java.awt.EventQueue;
import java.sql.Connection;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JTree;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.DefaultMutableTreeNode;

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
		
		JScrollPane scrollPane = new JScrollPane();
		frame.getContentPane().add(scrollPane, BorderLayout.WEST);
		
		//Step 1: checking the postgres connection with DB linuxgitmsr
				postgres = new DbConnect();
				//Step 2: Connection to the DB
				con = postgres.connectToPg();
				//Step 3: Pass the connection object to CommitTree
				ct = new CommitTree();
				ct.setConnection(con);
				//Step 4: Pass the commit id to CommitTree
				al = ct.getCommitTree("5ede3ceb7b2c2843e153a1803edbdc8c56655950");
				//Step 6: get mcidlinus in an ArrayList
				mcidlinus = ct.getMcidlinus();
				//Step 8: Close Connection to DB
				postgres.closeConnection(con);
				
		JTree tree = new JTree();
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
		frame.getContentPane().add(tree, BorderLayout.NORTH);
	}

}
