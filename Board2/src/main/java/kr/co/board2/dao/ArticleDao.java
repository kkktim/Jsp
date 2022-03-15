package kr.co.board2.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import kr.co.board2.db.DBConfig;
import kr.co.board2.vo.ArticleVo;

public class ArticleDao {
	private static ArticleDao instance = new ArticleDao();
	public static ArticleDao getInstance() {
		return instance;
	}
	public ArticleDao() {}
	
	//CRUD
	public int insertArticle(ArticleVo av) {
		try {
			Connection conn = DBConfig.getInstance().getConnection();
			PreparedStatement psmt = conn.prepareStatement(Sql.INSERT_ARTICLE);
			psmt.setString(1, av.getTitle());
			psmt.setString(2, av.getContent());
			psmt.setInt(3, av.getFile());
			psmt.setString(4, av.getUid());
			psmt.setString(5, av.getRegip());
			psmt.executeUpdate();
			
			conn.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return selectMaxNo();
	}
	public void insertFile(int no, String fname, String nName) {
		try {
			Connection conn = DBConfig.getInstance().getConnection();
			PreparedStatement psmt = conn.prepareStatement(Sql.INSERT_FILE);
			psmt.setInt(1, no);
			psmt.setString(2, fname);
			psmt.setString(3, nName);
			psmt.executeUpdate();
			
			conn.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public int selectCountTotal() {
		int total = 0;
		try {
			Connection conn = DBConfig.getInstance().getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(Sql.SELECT_COUNT_TOTAL);
			if(rs.next()) {
				total = rs.getInt(1);
			}
			rs.close();
			stmt.close();
			conn.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return total;
	}
	public int selectMaxNo() {
		int no = 0;
		try {
			Connection conn = DBConfig.getInstance().getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(Sql.SELECT_MAX_NO);
			if(rs.next()) {
				no = rs.getInt(1);
			}
			conn.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return no;
	}
	public void selectArticle() {}
	public List<ArticleVo> selectArticles(int start) {
		List<ArticleVo> articles = new ArrayList<>();
		try {
			Connection conn = DBConfig.getInstance().getConnection();
			PreparedStatement psmt = conn.prepareStatement(Sql.SELECT_ARTICLES);
			psmt.setInt(1, start);
			
			ResultSet rs = psmt.executeQuery();
			
			while(rs.next()) {
				ArticleVo av = new ArticleVo();
				av.setNo(rs.getInt(1));
				av.setParent(rs.getInt(2));
				av.setComment(rs.getInt(3));
				av.setType(rs.getString(4));
				av.setTitle(rs.getString(5));
				av.setContent(rs.getString(6));
				av.setFile(rs.getInt(7));
				av.setHit(rs.getInt(8));
				av.setUid(rs.getString(9));
				av.setRegip(rs.getString(10));
				av.setRdate(rs.getString(11).substring(2, 10));
				av.setNick(rs.getString(12));
				articles.add(av);
			}
			conn.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return articles;
	}
	
	public void updateArticle() {}
	public void deleteArticle() {}
}
