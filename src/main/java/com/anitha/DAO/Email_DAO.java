package com.anitha.DAO;

import java.sql.Connection;
import org.redisson.api.RMap;
import org.redisson.api.RMapCache;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import com.anitha.model.Email;
import com.anitha.util.ConnectionHelper;
import com.anitha.util.RedisCache;



public class Email_DAO {
	ConnectionHelper conHelper=new ConnectionHelper();
	RMapCache<Integer, Email> map=RedisCache.redisMap();
	
	public List<Email> findAll() {
		
        List<Email> list = new ArrayList<Email>();
        Connection connection = null;
    	String sql = "select * from email";
    	ConnectionHelper con =new ConnectionHelper();
        
        try {
        	
        	connection = conHelper.connectToPostgres();
            Statement s = connection.createStatement();
            s.setFetchSize(20);
            ResultSet rs = s.executeQuery(sql);
           // rs.setFetchSize(10);
            while (rs.next()) {
            	Email email=processRow(rs);
            	map.put(email.getEmail_id(), email);
                list.add(email);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
		} finally {
			con.close();
		}
        return list;
    }
	public Email findById(int id) {
		if(map.get(id)!=null) {
			return map.get(id);
		}
    	String sql = "SELECT * from email where email_id= ? ";
    	Email email = null;
        Connection connection = null;
        try {
        	connection = conHelper.connectToPostgres();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                email = processRow(rs);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
		} finally {
			conHelper.close();
		}
        return email;
    }


    public int create(Email e) {
    	 String query = "INSERT INTO email(to_address,subject,body) VALUES(?, ?,?) ";
    	 Connection connection=null;
    	 int id =0;
         try {
        	 
        	 connection=conHelper.connectToPostgres();
         	 PreparedStatement pst = connection.prepareStatement(query,new String[] {"email_id"});
             pst.setString(1,e.getTo_address());
             pst.setString(2, e.getSubject());
             pst.setString(3, e.getBody());
             pst.executeUpdate();
             
             ResultSet rs = pst.getGeneratedKeys();
             rs.next();
             // Update the id in the returned object. This is important as this value must be returned to the client.
             id= rs.getInt(1);

         } catch (SQLException ex) {

            System.out.println(ex);
         }
         catch(Exception e1) {
         	System.out.println(e1);
         }
         return id;

    }
    public boolean remove(int id) {
        Connection c = null;
        try {
            c = conHelper.connectToPostgres();
            PreparedStatement ps = c.prepareStatement("DELETE FROM email WHERE email_id=?");
            ps.setInt(1, id);
            int count = ps.executeUpdate();
            return count == 1;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
		} finally {
			conHelper.close();
		}
    }
    protected Email processRow(ResultSet rs) throws SQLException {
    	Email email = new Email();
        email.setEmail_id(rs.getInt("email_id"));
        email.setBody(rs.getString("body"));
        email.setSubject(rs.getString("subject"));
        email.setTo_address(rs.getString("to_address"));
   
        return email;
    }
	public void update(int id, boolean flag) {
		String query = "UPDATE email SET sent=? WHERE email_id=? ";
   	 Connection connection=null;
        try {
       	 
       	connection=conHelper.connectToPostgres();
        	PreparedStatement pst = connection.prepareStatement(query);
            pst.setBoolean(1,flag);
            pst.setInt(2, id);
       
            pst.executeUpdate();
            
        

        } catch (SQLException ex) {

           System.out.println(ex);
        }
        catch(Exception e1) {
        	System.out.println(e1);
        }
      
	}
	public Boolean findByUserId(String id, String pwd) {
    	String sql = "SELECT * from sender where userid= ? and password=? ";
    	Email email = null;
        Connection connection = null;
        try {
        	connection = conHelper.connectToPostgres();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, id);
            ps.setString(2, pwd);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
            	return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
		} finally {
			conHelper.close();
		}
        return false;
        
    }

	
    
    
}
