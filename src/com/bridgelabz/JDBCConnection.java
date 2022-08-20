package com.bridgelabz;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Enumeration;

public class JDBCConnection {
	public static Connection connectToDatabase() {
		String URL = "jdbc:mysql://localhost:3306/payroll_service";
		String USER = "root";
		String PASS = "Rushi@1410";
		Connection connection;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Driver Loaded!");
		} catch (ClassNotFoundException e) {
			System.out.println("can't find the driver in the classpath!");
		}

		listDrivers();
		try {
			System.out.println("connecting to database: " + URL);
			connection = DriverManager.getConnection(URL, USER, PASS);
			System.out.println("connection is successful! " + connection);

			return connection;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	private static void listDrivers() {
		Enumeration<java.sql.Driver> driverList = DriverManager.getDrivers();
		while (driverList.hasMoreElements()) {
			java.sql.Driver driverClass = driverList.nextElement();
			System.out.println(driverClass.getClass().getName() + " "); 
		}
	}

}
