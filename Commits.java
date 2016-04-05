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
		postgres.connectToPg();
		
		//Step 3: Get the connection Object
		con = postgres.getConnection();
		
		//Step 4: Pass the connection object to CommitTree
		ct = new CommitTree();
		ct.setConnection(con);
		
		//Step 5: Pass the commit id to CommitTree
		ct.setCommitTree("5ede3ceb7b2c2843e153a1803edbdc8c56655950");
		
		//Step 6: Get CommitTree in an ArrayList
		ct.getCommitTree();
		
		//Step 7: Close Connection to DB
        postgres.closeConnection();
	}

}
