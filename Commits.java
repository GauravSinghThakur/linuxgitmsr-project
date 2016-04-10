package linuxgitmsr;

import java.sql.*;
import java.util.ArrayList;

public class Commits {
	private static Connection con = null;
	private static ArrayList<CommitInfo> al = null;
	private static DbConnect postgres = null;
	private static CommitTree ct = null;
	
	public static void main(String[] args) {
		//Step 1: checking the postgres connection with DB linuxgitmsr
		postgres = new DbConnect();
		
		//Step 2: Connection to the DB
		con = postgres.connectToPg();
		
		//Step 4: Pass the connection object to CommitTree
		ct = new CommitTree();
		ct.setConnection(con);
		
		//Step 4: Pass the commit id to CommitTree
		al = ct.setCommitTree("5ede3ceb7b2c2843e153a1803edbdc8c56655950");
		
		//Step 5: Print CommitTree in an ArrayList
		ct.getCommitTree(al);
		
		//Step 7: Close Connection to DB
        postgres.closeConnection(con);
	}

}
