package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import controller.DBConfig;
import vo.CustVo;

public class CustDao {
	//singletone
	private static CustDao instance = new CustDao();
	public static CustDao getInstance() {
		return instance;
	}
	public CustDao() {}
	
	//CRUD
	public void insertCustomer(CustVo cv) {
		try {
			Connection conn = DBConfig.getInstance().getConnection();
			String sql = "INSERT FROM `Customer` VALUES (?,?,?);";
			PreparedStatement psmt = conn.prepareStatement(sql);
			psmt.setString(1, cv.getName());
			psmt.setString(2, cv.getAddress());
			psmt.setString(3, cv.getPhone());
			psmt.executeUpdate();
			
			conn.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	public void selectCustomer() {}
	public void selectCustomers() {}
	public void updateCustomer() {}
	public void deleteCustomer() {}
}
