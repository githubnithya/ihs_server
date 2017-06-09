package com.psg.ihsserver.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbUtil {

	private final String DB_DRIVER = "oracle.jdbc.driver.OracleDriver";
	private final String DB_CONNECTION = "jdbc:oracle:thin:@127.0.0.1:1521:XE";
	private final String DB_USER = "system";
	private final String DB_PASSWORD = "psgtech";

	private Connection getDBConnection() {

		Connection dbConnection = null;

		try {

			Class.forName(DB_DRIVER);

		} catch (ClassNotFoundException e) {

			System.out.println(e.getMessage());

		}

		try {

			dbConnection = DriverManager.getConnection(DB_CONNECTION, DB_USER,
					DB_PASSWORD);
			return dbConnection;

		} catch (SQLException e) {

			System.out.println(e.getMessage());

		}

		return dbConnection;

	}
}
