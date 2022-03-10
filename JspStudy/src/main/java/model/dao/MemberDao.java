package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import controller.DBConfig;
import vo.MemberVo;

public class MemberDao {
	//singletone
	private static MemberDao instance = new MemberDao();
	
	public static MemberDao getInstance() {
		return instance;
	}
	private MemberDao() {}
	
	//CRUD
	public void insertMember(MemberVo mv) {
		try {
			Connection conn = DBConfig.getInstance().getConnection();
			String sql = "INSERT INTO `Member` VALUES (?,?,?,?,?,NOW());";
			PreparedStatement psmt = conn.prepareStatement(sql);
			psmt.setString(1, mv.getUid());
			psmt.setString(2, mv.getName());
			psmt.setString(3, mv.getHp());
			psmt.setString(4, mv.getPos());
			psmt.setInt(5, mv.getDep());
			psmt.executeUpdate();
			
			conn.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	public MemberVo selectMember(String uid) {
		MemberVo mv = null;
		try {
			Connection conn = DBConfig.getInstance().getConnection();
			PreparedStatement psmt = conn.prepareStatement("SELECT * FROM `Member` WHERE `uid`=?;");
			psmt.setString(1, uid);
			ResultSet rs = psmt.executeQuery();
			if(rs.next()) {
				mv = new MemberVo();
				mv.setUid(rs.getString(1));
				mv.setName(rs.getString(2));
				mv.setHp(rs.getString(3));
				mv.setPos(rs.getString(4));
				mv.setDep(rs.getInt(5));
				mv.setRdate(rs.getString(6));
			}
			conn.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return mv;
	}
	public List<MemberVo> selectMembers() {
		List<MemberVo> members = new ArrayList<MemberVo>();
		try {
			Connection conn = DBConfig.getInstance().getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM `Member`;");
			while(rs.next()) {
				MemberVo mv = new MemberVo();
				mv.setUid(rs.getString(1));
				mv.setName(rs.getString(2));
				mv.setHp(rs.getString(3));
				mv.setPos(rs.getString(4));
				mv.setDep(rs.getInt(5));
				mv.setRdate(rs.getString(6));
				members.add(mv);
			}
			conn.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return members;
	}
	public void updateMember(MemberVo mv) {
		try {
			Connection conn = DBConfig.getInstance().getConnection();
			String sql = "UPDATE `Member` SET `name`=?, `hp`=?, `pos`=?, `dep`=? WHERE `uid`=?;";
			PreparedStatement psmt = conn.prepareStatement(sql);
			psmt.setString(1, mv.getName());
			psmt.setString(2, mv.getHp());
			psmt.setString(3, mv.getPos());
			psmt.setInt(4, mv.getDep());
			psmt.setString(5, mv.getUid());
			psmt.executeUpdate();
			conn.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	public void deleteMember(String uid) {
		try {
			Connection conn = DBConfig.getInstance().getConnection();
			PreparedStatement psmt = conn.prepareStatement("DELETE FROM `Member` WHERE `uid`=?;");
			psmt.setString(1, uid);
			psmt.executeUpdate();
			conn.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
