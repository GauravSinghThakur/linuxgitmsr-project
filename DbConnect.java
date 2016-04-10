package linuxgitmsr;

import java.sql.*;

public class DbConnect {
	//encapsulating required variables to connect to postgres DB
	private Connection con = null;
	private String jdbcDriver = "org.postgresql.Driver";  
	private String dbUrl = "jdbc:postgresql://127.0.0.1:5432/linuxgitmsr";
	private String userName = "dmg";
	private String password = "admin";
	
	// method to connect to postgres DB
	public Connection connectToPg(){
		System.out.println("-------- PostgreSQL "+"JDBC Connection Testing ------------");
 
		try { 
			Class.forName(jdbcDriver);
 		} catch (ClassNotFoundException e) {
 			System.out.println("No PostgreSQL JDBC Driver? "+"Include in your library path!");
			e.printStackTrace();
		}
 
		System.out.println("PostgreSQL JDBC Driver Registered!");
 
		try {
 			con = DriverManager.getConnection(dbUrl,userName,password);
		} catch (SQLException e) {
			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();
		}
 
		if (con != null) {
			System.out.println("Connection Successful! (:");
		} else {
			System.out.println("Failed to make connection! ):");
		}
		return con;
	}
	
		
	public void closeConnection(Connection conn){
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
