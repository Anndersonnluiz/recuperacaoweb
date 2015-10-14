package br.senai.sc.tii2014n1.pw4.anderson.dao;

import java.sql.DriverManager;

import com.mysql.jdbc.Connection;

public class Dao {
	
	private Connection conn;
	
	public Connection getConnection(){
		if (conn == null) {
			try {
				Class.forName("com.mysql.jdbc.Driver").newInstance();
				conn = (Connection) DriverManager.getConnection(
						"jdbc:mysql://localhost/avaliacao1", "root", "");
				System.out.println("Conectou");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return conn;
	}
	
}
