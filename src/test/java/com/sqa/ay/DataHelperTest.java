package com.sqa.jf.data;

import java.sql.*;

import org.testng.annotations.*;

import com.sqa.am.util.helper.*;
import com.sqa.jf.util.helper.*;

public class DataHelperTest {
	@DataProvider
	public Object[][] dpMySQL() throws ClassNotFoundException, SQLException {
		return DataHelper.evalDatabaseTable("com.mysql.jdbc.Driver", "jdbc:mysql://localhost:8889/sqadb", "root",
				"root", "amazon");
	}

	@DataProvider
	public Object[][] dpMySQLOS() throws ClassNotFoundException, SQLException {
		return DataHelper.evalDatabaseTable("com.mysql.jdbc.Driver", "jdbc:mysql://localhost:8889/sqadb", "root",
				"root", "amazon", 1, 1);
	}

	@DataProvider
	public Object[][] dpPostgres() throws ClassNotFoundException, SQLException {
		Object[][] data = DataHelper.evalDatabaseTable("org.postgresql.Driver",
				"jdbc:postgresql://localhost:5432/autodb", "postgres", "postgres", "monster");

		return data;
	}

	@DataProvider
	public Object[][] dpPostgresOSE() throws ClassNotFoundException, SQLException {
		Object[][] data = DataHelper.evalDatabaseTable("org.postgresql.Driver",
				"jdbc:postgresql://localhost:5432/autodb", "postgres", "postgres", "monster", 1, 1);

		return data;
	}

	@Test(dataProvider = "dpMySQL", priority = 1)
	public void testAmazon(Object id, Object name, Object quantity) {
		System.out.println("Test DP with mySQL: " + name);
	}

	@Test(dataProvider = "dpMySQLOSE", priority = 3)
	public void testAmazonOffset(Object name, Object quantity) throws ClassNotFoundException, SQLException {
		Display.display2DArray(dpMySQLOS());
		System.out.println("Test DP with mySQL and Offset: " + name);
	}

	@Test(dataProvider = "dpPostgres", priority = 2)
	public void testMonster(Object id, Object keywords, Object results) {
		System.out.println("Test DP with Postgres: " + keywords);
	}

	@Test(dataProvider = "dpPostgres", priority = 4)
	public void testMonsterOSE(Object id, Object keywords, Object results) {
		System.out.println("Test DP with Postgres: " + keywords);
	}
}
