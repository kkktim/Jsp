package kr.co.board2.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import kr.co.board2.db.DBConfig;
import kr.co.board2.vo.TermsVo;
import kr.co.board2.vo.UserVo;

public class UserDao {
	//singletone
	private static UserDao instance = new UserDao();
	public static UserDao getInstance() {
		return instance;
	}
	public UserDao() {}
	
	//CRUD
	public TermsVo selectTerms() {

		TermsVo tv = new TermsVo();
		try {
			Connection conn = DBConfig.getInstance().getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(Sql.SELECT_TERMS);
			
			if(rs.next()) {
				tv.setTerms(rs.getString(1));
				tv.setPrivacy(rs.getString(2));
			}
			conn.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return tv;
	}
	public int selectCountUser(String info, int what) {
		int count = 0;
		try {
			Connection conn = DBConfig.getInstance().getConnection();
			PreparedStatement psmt = null;
			if(what == 1) {
				psmt = conn.prepareStatement(Sql.SELECT_COUNT_UID);
			}else if(what == 2) {
				psmt = conn.prepareStatement(Sql.SELECT_COUNT_NICK);
			}
			psmt.setString(1, info);
			
			ResultSet rs = psmt.executeQuery();
			if(rs.next()) {
				count = rs.getInt(1);
			}
			
			conn.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return count;
	}
	
	public void insertUser(UserVo uv) {
		try {
			Connection conn = DBConfig.getInstance().getConnection();
			PreparedStatement psmt = conn.prepareStatement(Sql.INSERT_USER);
			psmt.setString(1, uv.getUid());
			psmt.setString(2, uv.getPass());
			psmt.setString(3, uv.getName());
			psmt.setString(4, uv.getNick());
			psmt.setString(5, uv.getEmail());
			psmt.setString(6, uv.getHp());
			psmt.setString(7, uv.getZip());
			psmt.setString(8, uv.getAddr1());
			psmt.setString(9, uv.getAddr2());
			psmt.setString(10, uv.getRegip());
			psmt.executeUpdate();
			
			conn.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	public UserVo selectUser(String uid, String pass) {
		UserVo user = null;
		try {
			Connection conn = DBConfig.getInstance().getConnection();
			PreparedStatement psmt = conn.prepareStatement(Sql.SELECT_USER);
			psmt.setString(1, uid);
			psmt.setString(2, pass);
			ResultSet rs = psmt.executeQuery();
			if(rs.next()) {
				user = new UserVo();
				user.setUid(rs.getString(1));
				user.setPass(rs.getString(2));
				user.setName(rs.getString(3));
				user.setNick(rs.getString(4));
				user.setEmail(rs.getString(5));
				user.setHp(rs.getString(6));
				user.setGrade(rs.getInt(7));
				user.setZip(rs.getString(8));
				user.setAddr1(rs.getString(9));
				user.setAddr2(rs.getString(10));
				user.setRegip(rs.getString(11));
				user.setRdate(rs.getString(12));
			}
			conn.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return user;
	}
	public void selectUsers() {}
	public void updateUser() {}
	public void deleteUser() {}
}
