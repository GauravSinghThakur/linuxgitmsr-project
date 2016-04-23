package linuxgitmsr;

import java.sql.*;
import java.util.ArrayList;

public class CommitTree {
	private DbConnect postgres = null;
	private Connection con = null;
	private Connection conn = null;
	private Statement stmt = null;
	private String sql;
	private ResultSet rs = null;
	private ArrayList<CommitInfo> al = null;
	private ArrayList<CommitInfo> mcidlinus = null;
	private CommitInfo obj = null;
	private String[] repo_comdate_count = new String[2];
	private String[] comdates = null;
	private String[] repos = null;
	
	public Connection setConnection(){
		//Step 1: checking the postgres connection with DB linuxgitmsr
		postgres = new DbConnect();
		//Step 2: Connection to the DB
		conn = postgres.connectToPg();
		//Step 3: Pass the connection
		return conn;
	} 
	
	
	public String[] getRepos(String commit) {
		   try{
		      //STEP 1: Execute a query
		      String[] str = getRepoComdateCount(commit);
		      int i = Integer.parseInt(str[1]);
		      repos = new String[i];
		      int j = 0;
		      
		      con = setConnection();
		      System.out.println("Creating statement...");
		      stmt = con.createStatement();
		      sql = "select distinct repo as repos from(select p.mcidlinus,p.mnextmerge,p.mnext,c.cid,c.comdate,c.repo,p.mdist,p.mwhen from commits c INNER JOIN pathtoblessed p on c.cid=p.cid WHERE mcidlinus = '"+commit+"' order by comdate desc) q";
		      rs = stmt.executeQuery(sql);
		     
		      //STEP 2: Extract data from result set
		      while(rs.next()){
		         //Retrieve by column name
		    	  //*********************************
		    	  repos[j] = rs.getString("repos");
		    	  System.out.println("repo: "+j+" : "+rs.getString("repos"));
		    	  j++;
		      } 		      		  
		    //Clean-up environment
		      rs.close();
		      stmt.close();
		      con.close();
		   } catch(SQLException se){
		         se.printStackTrace();
		   }
		return repos; 
		   	   
	   }
	

	public String[] getComdates(String commit) {
		   try{
			  String[] str = getRepoComdateCount(commit);
		      int i = Integer.parseInt(str[0]);
		      comdates = new String[i];
		      int j = 0;     
		      
		    //STEP 1: Execute a query
		      con = setConnection();
			  System.out.println("Creating statement...");
		      stmt = con.createStatement();
		      sql = "select distinct comdate as comdates from(select p.mcidlinus,p.mnextmerge,p.mnext,c.cid,c.comdate,c.repo,p.mdist,p.mwhen from commits c INNER JOIN pathtoblessed p on c.cid=p.cid WHERE mcidlinus = '"+commit+"' order by comdate desc) q";
		      rs = stmt.executeQuery(sql);
		      //STEP 2: Extract data from result set
		      while(rs.next()){
		         //Retrieve by column name
		    	  //*********************************
		    	  comdates[j] = rs.getString("comdates");
		    	  System.out.println("commitdate: "+j+" : "+rs.getString("comdates"));
		    	  j++;
		      } 		      		  
		    //Clean-up environment
		      rs.close();
		      stmt.close();
		      con.close();
		   } catch(SQLException se){
		         se.printStackTrace();
		   }
		return comdates; 
		   	   
	   }
	
	public String[] getRepoComdateCount(String commit) {
		   try{
			   con = setConnection();
			   //STEP 1: Execute a query
		      System.out.println("Creating statement...");
		      stmt = con.createStatement();
		      sql = "select count(distinct comdate) as commitdatecount, count(distinct repo) as repocount from(select p.mcidlinus,p.mnextmerge,p.mnext,c.cid,c.comdate,c.repo,p.mdist,p.mwhen from commits c INNER JOIN pathtoblessed p on c.cid=p.cid WHERE mcidlinus = '"+commit+"' order by comdate desc) q";
		      rs = stmt.executeQuery(sql);
		           
		      //STEP 2: Extract data from result set
		      while(rs.next()){
		         //Retrieve by column name
		    	  //*********************************
		    	  repo_comdate_count[0] = rs.getString("commitdatecount");
		    	  System.out.println("commitdatecount: "+rs.getString("commitdatecount"));
		    	  repo_comdate_count[1] = rs.getString("repocount");
		    	  System.out.println("repocount: "+rs.getString("repocount"));
		      }
		      		      		  
		    //Clean-up environment
		      rs.close();
		      stmt.close();
		      con.close();
		   } catch(SQLException se){
		         se.printStackTrace();
		   }
		return repo_comdate_count; 
		   	   
	   }
	
	
	
	public ArrayList<CommitInfo> getMcidlinus() {
		   try{
			   con = setConnection();
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
		      con.close();
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
			   con = setConnection();
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
		      con.close();
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
