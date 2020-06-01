package com.anitha.demorest;

import javax.servlet.ServletException;  
import javax.servlet.http.*; 
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Servlet1 extends HttpServlet  {
	public void doPost(HttpServletRequest request, HttpServletResponse response)  
            throws ServletException, IOException {  
  
response.setContentType("text/html");  
PrintWriter out = response.getWriter();  

ClientAPI client =new ClientAPI();
String json=client.getRequest();
SendEmailService service=new SendEmailService();
if(json!="") {
    JSONParser parse = new JSONParser();
	//Type caste the parsed json data in json object
	
		JSONObject jobj = null;
		try {
			jobj = (JSONObject)parse.parse(json);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		service.sent((String)jobj.get("to"),(String)jobj.get("subject"),(String)jobj.get("body"));
		

	
	} 
    
 
	out.print("You are successfully sent the email...");  


out.close();  
}  


}
