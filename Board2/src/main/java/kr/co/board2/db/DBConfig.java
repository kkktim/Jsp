package kr.co.board2.db;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConfig {
	
	// �̱��� ��ü
	private static DBConfig instance = new DBConfig();
	
	public static DBConfig getInstance() {
		return instance;
	}
	
	private DBConfig() {}
	
	// ���߿�
	private final String HOST = "jdbc:mysql://13.124.252.103:3306/rkdxogh1987";
	private final String USER = "timk";
	private final String PASS = "1234";
	
	// ������
	//private final String HOST = "jdbc:mysql://localhost:3306/rkdxogh1987";
	//private final String USER = "timk";
	//private final String PASS = "1234";
	
	public Connection getConnection() throws Exception {
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn = DriverManager.getConnection(HOST, USER, PASS);
		return conn;
	}
}
