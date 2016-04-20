package utils;

import java.lang.reflect.Method;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.testng.ITestContext;
import org.testng.annotations.DataProvider;


public class SqlHandler {
	
	
	@DataProvider(name = "sqlHandler")
	public static Object[][] sqlTransform(Method m) 
	{		
			String testCase = m.getName();
			Connection connection = null;
		    PreparedStatement statement = null;
		    ResultSet resultSet = null;
		    
		    String url1 = "jdbc:mysql://localhost:3306/seleniumautomation";
	        String user = "pquinn";
	        String password = "u_pick_it";
	        

		    try {
		    	
		    	Class.forName("com.mysql.jdbc.Driver").newInstance();
		        connection = DriverManager.getConnection(url1, user, password);
		        statement = connection.prepareStatement("SELECT * FROM " + testCase);
		        resultSet = statement.executeQuery();
		        ResultSetMetaData rsmd = resultSet.getMetaData();
		        
		        int numcols = rsmd.getColumnCount();
		        Object [][] result = new TestCaseDetails[getRowCount(resultSet)][1];
		        
		        int counter = 0;
		        while (resultSet.next()) 
		        {
		            TestCaseDetails row = new TestCaseDetails(); // new list per row
		            for (int i=1; i<= numcols; i++) 
		            {  // don't skip the last column, use <=
		                row.detail.put(rsmd.getColumnName(i), resultSet.getString(i));
		                System.out.print(resultSet.getString(i) + "\t");
		            }
		            result[counter][0] = row; // add it to the result
		            System.out.print("\n");
		            counter ++;
		        }
		        
		        return result;
		    } 
		    catch (Exception e) 
			{
				e.printStackTrace();
			}
		    finally {
		        if (resultSet != null) try { resultSet.close(); } catch (SQLException logOrIgnore) {}
		        if (statement != null) try { statement.close(); } catch (SQLException logOrIgnore) {}
		        if (connection != null) try { connection.close(); } catch (SQLException logOrIgnore) {}
		    }

		    return null;
			
		 
	}
	public static void closeResultSet(ResultSet rs) 
	{
	    if (rs != null) {
	        try 
	        {
	            rs.close();
	        } 
	        catch (SQLException ex) 
	        {
	            System.out.println("SQL Exception");
	        }
	    }
	}
	public List<HashMap<String,Object>> convertResultSetToList(ResultSet rs) throws SQLException {
	    ResultSetMetaData md = rs.getMetaData();
	    int columns = md.getColumnCount();
	    List<HashMap<String,Object>> list = new ArrayList<HashMap<String,Object>>();

	    while (rs.next()) {
	        HashMap<String,Object> row = new HashMap<String, Object>(columns);
	        for(int i=1; i<=columns; ++i) {
	            row.put(md.getColumnName(i),rs.getObject(i));
	        }
	        list.add(row);
	    }

	    return list;
	}
	public static int getRowCount(ResultSet result)
	{
		int size = 0;
		try {
		    result.last();
		    size = result.getRow();
		    result.beforeFirst();
		}
		catch(Exception ex) {
		    return 0;
		}
		return size;
	}
	
	
	
}

