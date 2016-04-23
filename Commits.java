package linuxgitmsr;

import java.sql.*;
import java.util.ArrayList;

public class Commits {
	
	private static ArrayList<CommitInfo> al = null;
	private static ArrayList<CommitInfo> mcidlinus = null;
	private static CommitTree ct = null;
	private static String[] count = new String[2];
	private static String[] comdates = new String[100];
	private static String[] repos = new String[25];
	
	
	public static void main(String[] args) {
		
		ct = new CommitTree();
				
		//Step 1: Pass the commit id to CommitTree
		al = ct.getCommitTree("5ede3ceb7b2c2843e153a1803edbdc8c56655950");
		
		//Step 1a: Pass the commit id to CommitTree
		count = ct.getRepoComdateCount("5ede3ceb7b2c2843e153a1803edbdc8c56655950");
		comdates = ct.getComdates("5ede3ceb7b2c2843e153a1803edbdc8c56655950");
		repos = ct.getRepos("5ede3ceb7b2c2843e153a1803edbdc8c56655950");
				
		
		//Step 2: Print CommitTree in an ArrayList
		//ct.printCommitTree(al);
		
		//Step 3: get mcidlinus in an ArrayList
		//mcidlinus = ct.getMcidlinus();
		
		//Step 4: Print CommitTree in an ArrayList
		//ct.printMcidlinus(mcidlinus);
			
				
		}

}
