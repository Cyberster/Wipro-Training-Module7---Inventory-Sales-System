package com.wipro.sales.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
	private static Connection conn = null;
	
	/**
	 * Establish a connection to the database and return the java.sql.Connection reference
	 * */
	public static Connection getDBConnection() {
		try {
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521", "scott", "tiger");
			return conn;
		} catch (SQLException e) {
			System.out.println("Connection could not be estanlished");
			e.printStackTrace();
			return null;
		}
	}
}
