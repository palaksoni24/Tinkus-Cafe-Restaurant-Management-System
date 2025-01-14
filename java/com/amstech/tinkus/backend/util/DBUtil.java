package com.amstech.tinkus.backend.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class DBUtil {

	private final String URL="jdbc:mysql://localhost:3306/tinkus_cafe";
	private final String USERNAME="root";
	private final String PASSWORD="root";
	private final String DRIVER="com.mysql.cj.jdbc.Driver";
	
	public Connection getConnection() throws ClassNotFoundException, SQLException {
		Class.forName(DRIVER);
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/tinkus_cafe", "root", "root");
		
		System.out.println("Connected Successfully");
		return connection;
	}
	public DBUtil() {
		System.out.println("Creating DBUtil Object");

	}
	
	public void close(Connection conn, PreparedStatement pstmt, ResultSet rs) throws SQLException {
		if (rs != null)
			rs.close();

		if (pstmt != null)
			pstmt.close();

		if (conn != null)
			conn.close();
	}

	public void close(Connection conn, PreparedStatement pstmt) throws SQLException {
		close(conn, pstmt, null);
	}

	public void close(PreparedStatement pstmt) throws SQLException { 
		close(null, pstmt, null);
	}

	public void close(Connection conn) throws SQLException {
		close(conn, null, null);
	}

	public void close(PreparedStatement pstmt, ResultSet rs) throws SQLException {
		close(null, pstmt, rs);
	}

}


