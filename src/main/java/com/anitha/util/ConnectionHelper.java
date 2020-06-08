package com.anitha.util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
 
public class ConnectionHelper 
{
	Connection c=null;
	public Connection connectToPostgres(){
		

		try {
			Class.forName("org.postgresql.Driver");
			String url = "jdbc:postgresql://localhost:5432/postgres";
			Properties props = new Properties();
			props.setProperty("user","postgres");
			props.setProperty("password","anitha");
			c = DriverManager.getConnection(url, props);
			
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.getClass().getName()+": "+e.getMessage());
			System.exit(0);
		}
		
		System.out.println("Opened database successfully");
		return c;
	}
	public void close() 
	{	
		try
		{
			if( c != null )
			{
				c.close();
			}
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
}