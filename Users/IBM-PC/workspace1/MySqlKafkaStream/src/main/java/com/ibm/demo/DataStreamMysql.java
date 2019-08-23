package com.ibm.demo;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DataStreamMysql {
	
	String driver="com.mysql.jdbc.Driver";
	String username="root";
	String password ="password";
	String url="jdbc:mysql://localhost:3306/mydb";
	
	
	public ResultSet GetMysqlData()
	{
		ResultSet rs=null;
		try
		{
			Class.forName(driver);
			Connection con=DriverManager.getConnection(url, username, password);
			System.out.println("successfully Connected");
			
			String sql="select * from sales_record";
			PreparedStatement ps=con.prepareStatement(sql);
			rs=ps.executeQuery();
			
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	  return rs;
	}
	

	
}
