package utils;

import java.sql.*;

import org.testng.annotations.DataProvider;


public class SqlHandler {
	
	
	//@DataProvider(name = "sqlHandler")
	public static void sqlTransform() 
	{
		Connection con;
		Statement stmt;
		ResultSet rs;
		
		String url1 = "jdbc:mysql://localhost:3306/seleniumautomation";
        String user = "pquinn";
        String password = "u_pick_it";
		
		try {
			
			try {
				Class.forName("com.mysql.jdbc.Driver").newInstance();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    con = DriverManager.getConnection(url1, user, password);
		    stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
			rs = stmt.executeQuery("SELECT * FROM testtable");
			
			while(rs.next())
			{
				System.out.println(rs.getInt("ID") + " " + rs.getString("FirstName") + " " + rs.getString("LastName"));
			}
		    
		} catch (SQLException ex) {
		    // handle any errors
		    System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
		}
		//return Object [][];
	}
	
	
	
}
