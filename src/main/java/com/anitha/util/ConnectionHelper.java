package com.anitha.util;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import com.anitha.demorest.SendEmailService;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;
 
public class ConnectionHelper 
{
	Connection c=null;
	public Connection connectToPostgres(){
		

		try {
			String decryptedPwd = Authentication.decrypt(DbConnectionDetails.pwd);
			Class.forName(DbConnectionDetails.driver);
			String url = DbConnectionDetails.url;
			Properties props = new Properties();
			props.setProperty("user",DbConnectionDetails.user);
			props.setProperty("password",decryptedPwd);
			c = DriverManager.getConnection(url, props);
			
		} 
		catch (Exception e) {
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