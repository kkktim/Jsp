package kr.co.farmstory1.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kr.co.farmstory1.bean.ArticleBean;
import kr.co.farmstory1.db.DBConfig;
import kr.co.farmstory1.db.Sql;

public class ArticleDao {
	//ΩÃ±€≈Ê
	private static ArticleDao instance = new ArticleDao();
	
	public static ArticleDao getInstance() {
		return instance;
	}
	
	private ArticleDao() {}
	
	//CRUD ∏ﬁº≠µÂ
	public void insertArticle(ArticleBean ab) {
		try {
			
			Connection conn = DBConfig.getInstance().getConnection();
			PreparedStatement psmt = conn.prepareStatement(Sql.INSERT_ARTICLE);
			psmt.setString(1, ab.getType());
			psmt.setString(2, ab.getTitle());
			psmt.setString(3, ab.getContent());
			psmt.setInt(4, ab.getFile());
			psmt.setString(5, ab.getUid());
			psmt.setString(6, ab.getRegip());
			psmt.executeUpdate();
			
			conn.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	public void insertComment(String no, String comment, String uid, String regip) {
		try {
			Connection conn = DBConfig.getInstance().getConnection();
			PreparedStatement psmt = conn.prepareStatement(Sql.INSERT_COMMENT);
			psmt.setString(1, no);
			psmt.setString(2, comment);
			psmt.setString(3, uid);
			psmt.setString(4, regip);
			psmt.executeUpdate();
			
			conn.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<ArticleBean> selectComments(String no) {
		List<ArticleBean> articles = new ArrayList<ArticleBean>();
		try {
			Connection conn = DBConfig.getInstance().getConnection();
			PreparedStatement psmt = conn.prepareStatement(Sql.SELECT_COMMENTS);
			psmt.setString(1, no);
			ResultSet rs = psmt.executeQuery();
			while(rs.next()) {
				ArticleBean article = new ArticleBean();
				article.setContent(rs.getString(6));
				article.setRdate(rs.getString(11));
				article.setNick(rs.getString(12));
				articles.add(article);
			}
			conn.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return articles;
	}
	public int selectCountTotal(String type) {
		int total = 0;
		try {
			Connection conn = DBConfig.getInstance().getConnection();
			PreparedStatement psmt = conn.prepareStatement(Sql.SELECT_COUNT_NO);
			psmt.setString(1, type);
			ResultSet rs = psmt.executeQuery();
			if(rs.next()) {
				total = rs.getInt(1);
			}
			conn.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return total;
	}
	public ArticleBean selectArticle(String no) {
		ArticleBean article = null;
		try {
			Connection conn = DBConfig.getInstance().getConnection();
			PreparedStatement psmt = conn.prepareStatement(Sql.SELECT_ARTICLE);
			psmt.setString(1, no);
			ResultSet rs = psmt.executeQuery();
			if(rs.next()) {
				article = new ArticleBean();
				article.setTitle(rs.getString(5));
				article.setContent(rs.getString(6));
				article.setUid(rs.getString(9));
				
			}
			conn.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return article;
	}
	public List<ArticleBean> selectArticles(String type, int start) {
		List<ArticleBean> articles = new ArrayList<>();
		try {
			Connection conn = DBConfig.getInstance().getConnection();
			PreparedStatement psmt = conn.prepareStatement(Sql.SELECT_ARTICLES);
			psmt.setString(1, type);
			psmt.setInt(2, start);
			ResultSet rs = psmt.executeQuery();
			while(rs.next()) {
				ArticleBean article = new ArticleBean();
				article.setNo(rs.getInt(1));
				article.setParent(rs.getInt(2));
				article.setComment(rs.getInt(3));
				article.setType(rs.getString(4));
				article.setTitle(rs.getString(5));
				article.setContent(rs.getString(6));
				article.setFile(rs.getInt(7));
				article.setHit(rs.getInt(8));
				article.setUid(rs.getString(9));
				article.setRegip(rs.getString(10));
				article.setRdate(rs.getString(11));
				article.setNick(rs.getString(12));
				articles.add(article);
			}
			conn.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return articles;
	}
	
	public List<ArticleBean> selectLatests (){
		List<ArticleBean> latests = new ArrayList<>();
		try {
			Connection conn = DBConfig.getInstance().getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(Sql.SELECT_LATESTS);
			
			while(rs.next()) {
				ArticleBean article = new ArticleBean();
				article.setNo(rs.getInt(1));
				article.setType(rs.getString(4));
				article.setTitle(rs.getString(5));
				article.setRdate(rs.getString(11).substring(2, 10));
				latests.add(article);
			}
			conn.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return latests;
	}
	
	public void updateArticle() {}
	public void deleteArticle() {}
}
