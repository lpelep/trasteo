package com.futbol.demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class testeo {
	public static void main(String[] argv) {

		System.out.println("-------- PostgreSQL "
				+ "JDBC Connection Testing ------------");

		try {

			Class.forName("org.postgresql.Driver");

		} catch (ClassNotFoundException e) {

			System.out.println("Where is your PostgreSQL JDBC Driver? "
					+ "Include in your library path!");
			e.printStackTrace();
			return;

		}

		System.out.println("PostgreSQL JDBC Driver Registered!");

		Connection connection = null;

		try {

			connection = DriverManager.getConnection(
					"jdbc:postgresql://localhost:8485/test", "luis",
					"luis");

		} catch (SQLException e) {

			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();
			return;

		}

		if (connection != null) {
			System.out.println("You made it, take control your database now!");
			
			 Statement stmt = null;
			 String cadena = "select idusuario, strlogin, strpassword from usuarios";
			 try {
			        stmt = connection.createStatement();
			        ResultSet rs = stmt.executeQuery(cadena);
			        while (rs.next()) {
			            int idusuario = rs.getInt("idusuario");
			            String strlogin = rs.getString("strlogin");
			            String strpassword = rs.getString("strpassword");
			        }
			    } catch (SQLException e ) {
			    	e.printStackTrace();
					return;
			    } finally {
			        if (stmt != null) { try {
						stmt.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} }
			    }
			
		} else {
			System.out.println("Failed to make connection!");
		}
		
	}
}
