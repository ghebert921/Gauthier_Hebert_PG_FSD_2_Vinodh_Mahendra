package com.simplilearn.workshop.utils;

import java.sql.*;

public class MySQLDatabaseUtils {

	public static Connection getConnection() throws SQLException, ClassNotFoundException {
		String driverClassName = "com.mysql.cj.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/flyawaydb";
		String user = "root";
		String password = "V0ng0la921!";
		Class.forName(driverClassName);
		
		Connection connection = DriverManager.getConnection(url,user,password);
		return connection;
		
	}
}
