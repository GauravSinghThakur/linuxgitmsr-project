package linuxgitmsr;

import java.sql.*;
import java.util.ArrayList;

public class CommitTree {
	private Connection con = null;
	private Statement stmt = null;
	private String sql;
	private ResultSet rs = null;
	private ArrayList<CommitInfo> al = null;
	private ArrayList<CommitInfo> mcidlinus = null;
	private CommitInfo obj = null;
	
	public void setConnection(Connection conn){
		con = conn;
	} 
	
	
	public ArrayList<CommitInfo> getMcidlinus() {
		   try{
		      //STEP 1: Execute a query
		      System.out.println("Creating statement...");
		      stmt = con.createStatement();
		      sql = "select distinct mcidlinus from pathtoblessed";
		      rs = stmt.executeQuery(sql);
		      mcidlinus = new ArrayList<CommitInfo>();     
		      //STEP 2: Extract data from result set
		      while(rs.next()){
		         //Retrieve by column name
		    	  //*********************************
		    	  obj = new CommitInfo();
	
		    	  obj.setMcidlinus(rs.getString("mcidlinus"));
		    	  //System.out.println("Mcidlinus: "+obj.getMcidlinus());
	
		    	  mcidlinus.add(obj);
		    	  //*********************************
		      }
		      		      		  
		    //Clean-up environment
		      rs.close();
		      stmt.close();
		   } catch(SQLException se){
		         se.printStackTrace();
		   }
		return mcidlinus; 
		   	   
	   }
	
	public void printMcidlinus(ArrayList<CommitInfo> al1){
		System.out.println("\tCommit Hierarchy");
		for (int i=0; i<al1.size(); i++){
			System.out.println("********" + i);
			System.out.println(al1.get(i).getMcidlinus());
			System.out.println("------------------------");
		}
	}
	
	public ArrayList<CommitInfo> getCommitTree(String commit) {
		   try{
		      //STEP 1: Execute a query
		      System.out.println("Creating statement...");
		      stmt = con.createStatement();
		      sql = "select p.mcidlinus,p.mnextmerge,p.mnext,c.cid,c.comdate,c.repo,p.mdist,p.mwhen from commits c INNER JOIN pathtoblessed p on c.cid=p.cid WHERE mcidlinus = '"+commit+"' order by comdate desc";
		      rs = stmt.executeQuery(sql);
		      al = new ArrayList<CommitInfo>();     
		      //STEP 2: Extract data from result set
		      while(rs.next()){
		         //Retrieve by column name
		    	  //*********************************
		    	  
		    	  obj = new CommitInfo();
	
		    	  obj.setMcidlinus(rs.getString("mcidlinus"));
		    	  //System.out.println("Mcidlinus: "+obj.getMcidlinus());
	
		    	  obj.setMnextmerge(rs.getString("mnextmerge"));
		    	  //System.out.println("Mnextmerge: "+obj.getMnextmerge());

		    	  obj.setMnext(rs.getString("mnext"));
		    	  //System.out.println("Mnext: "+obj.getMnext());
		    	  
		    	  obj.setCid(rs.getString("cid"));
		    	  //System.out.println("CID: "+obj.getCid());
		    	  
		    	  obj.setComdate(rs.getTimestamp("comdate"));
		          //System.out.println("Comdate: "+obj.getComdate());
		    	  
		    	  obj.setRepo(rs.getString("repo"));
		    	  //System.out.println("Repository: "+obj.getRepo());
		          
		    	  obj.setMdist(rs.getString("mdist"));
		    	  //System.out.println("Mdist: "+obj.getMdist());
		          
		    	  obj.setMwhen(rs.getTimestamp("mwhen"));
		    	  //System.out.println("Mwhen: "+obj.getMwhen());
		    	  //System.out.println("------------------------");
		    	  al.add(obj);
		    	  //*********************************
		      }
		      		      		  
		    //Clean-up environment
		      rs.close();
		      stmt.close();
		   } catch(SQLException se){
		         se.printStackTrace();
		   }
		return al; 
		   	   
	   }
	
	public void printCommitTree(ArrayList<CommitInfo> al1){
		System.out.println("\tCommit Hierarchy");
		for (int i=0; i<al1.size(); i++){
			System.out.println("********" + i);
			System.out.println(al1.get(i).getMcidlinus());
			System.out.println(al1.get(i).getMnextmerge());
			System.out.println(al1.get(i).getMnext());
			System.out.println(al1.get(i).getCid());
			System.out.println(al1.get(i).getComdate());
			System.out.println(al1.get(i).getRepo());
			System.out.println(al1.get(i).getMdist());
			System.out.println(al1.get(i).getMwhen());
			System.out.println("------------------------");
		}
		
	}
  
}
