package com.nainesh.test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.annotation.Resource;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class TestDataSource {

	@Resource(name = "jdbc/school")
	private DataSource ds;
	
	public TestDataSource(){
		  try {
			Context ctx = new InitialContext();
			ds = (DataSource)ctx.lookup("java:comp/env/jdbc/school");
		  } catch (NamingException e) {
			e.printStackTrace();
		  }
		}

	public String execute() throws Exception {
		
		try(Connection conn=ds.getConnection()) {
			
			String sql="select * from student";
			Statement stmt=conn.createStatement();
			ResultSet rtst=stmt.executeQuery(sql);
			while(rtst.next()) {
				String email=rtst.getString("email");
				System.out.println(email);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return "success";
	}
}
