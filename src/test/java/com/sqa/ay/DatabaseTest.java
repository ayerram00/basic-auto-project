package com.sqa.ay;

import java.sql.*;

import org.testng.annotations.Test;

public class DatabaseTest {
	@Test
	public void databaseTest() throws ClassNotFoundException, SQLException {
		System.out.println("Database Test");
		// Get Specific driver for the database you are using
		Class.forName("com.mysql.jdbc.Driver");
		// Create connection to database using database string with userName and
		// password
		Connection dbconn = DriverManager.getConnection("jdbc:mysql://localhost:8889/sqadb", "root", "root");
		// Create Statement object to run SQL commands
		Statement statement = dbconn.createStatement();
		// create the resultSet of an executed SQL statement
		ResultSet resultSet = statement.executeQuery("select * from person ");
		// Iterate through the results to capture or display all items
		while (resultSet.next()) {
			// TODO display or capture elements

			int id = resultSet.getInt("id");
			String name = resultSet.getString("name");
			String city = resultSet.getString("city");
			String address = resultSet.getString("address");
			String zip = resultSet.getString("zip");

			System.out.println(
					"ID:" + id + ", User name:" + name + " City:" + city + " Address:" + address + " Zip:" + zip);
		}
		resultSet.close();
		statement.close();
		dbconn.close();
	}
}
