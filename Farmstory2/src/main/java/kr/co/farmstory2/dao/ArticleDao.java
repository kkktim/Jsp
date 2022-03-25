package kr.co.farmstory2.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import kr.co.farmstory2.config.DBConfig;
import kr.co.farmstory2.config.Sql;
import kr.co.farmstory2.vo.ArticleVo;
import kr.co.farmstory2.vo.FileVo;

public class ArticleDao {
	//教臂沛按眉 积己
	private static ArticleDao instance = new ArticleDao();

	public static ArticleDao getInstance() {
		return instance;
	}
	public ArticleDao() {}
	
	//扁夯 CRUD
	//insert
	public void insertFile(int parent, String oName, String nName) {
		try {
			Connection conn = DBConfig.getInstance().getConnection();
			PreparedStatement psmt = conn.prepareStatement(Sql.INSERT_FILE);
			psmt.setInt(1, parent);
			psmt.setString(2, oName);
			psmt.setString(3, nName);
			psmt.executeUpdate();
			
			conn.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	public int insertArticle(ArticleVo av) {
		int no = 0;
		try {
			Connection conn = DBConfig.getInstance().getConnection();
			PreparedStatement psmt = conn.prepareStatement(Sql.INSERT_ARTICLE);
			psmt.setString(1, av.getType());
			psmt.setString(2, av.getTitle());
			psmt.setString(3, av.getContent());
			psmt.setInt(4, av.getFile());
			psmt.setString(5, av.getUid());
			psmt.setString(6, av.getRegip());
			psmt.executeUpdate();
			
			psmt = conn.prepareStatement(Sql.SELECT_ARTICLE_NO);
			psmt.setString(1, av.getType());
			psmt.setString(2, av.getUid());
			ResultSet rs = psmt.executeQuery();
			if(rs.next()) {
				no = rs.getInt(1); 
			}
			conn.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return no;
	}
	//select
	public String selectFile(String no) {
		String nName = null;
		try {
			Connection conn = DBConfig.getInstance().getConnection();
			PreparedStatement psmt = conn.prepareStatement(Sql.SELECT_FILE);
			psmt.setString(1, no);
			ResultSet rs = psmt.executeQuery();
			if(rs.next()) {
				nName = rs.getString(4);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return nName;
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
	public List<ArticleVo> selectLastests() {
		List<ArticleVo> lastests = new ArrayList<>();
		try {
			Connection conn = DBConfig.getInstance().getConnection();
			PreparedStatement psmt = conn.prepareStatement(Sql.SELECT_LATESTS);
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
				av.setRdate(rs.getString(11));
				lastests.add(av);
			}
			conn.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return lastests;
	}
	public ArticleVo selectArticle(String no) {
		ArticleVo article = new ArticleVo();
		
		try {
			Connection conn = DBConfig.getInstance().getConnection();
			PreparedStatement psmt = conn.prepareStatement(Sql.SELECT_ARTICLE);
			psmt.setString(1, no);
			
			ResultSet rs = psmt.executeQuery();
			
			if(rs.next()){
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
				article.setRdate(rs.getString(11).substring(2, 10));
				
				FileVo fv = new FileVo();
				fv.setFid(rs.getInt(12));
				fv.setParent(rs.getInt(13));
				fv.setoName(rs.getString(14));
				fv.setnName(rs.getString(15));
				fv.setDownload(rs.getInt(16));
				fv.setRdate(rs.getString(17));
									
				article.setFv(fv);
			}
			conn.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return article;
	}
	public List<ArticleVo> selectArticles(String type, int start) {
		List<ArticleVo> articles = new ArrayList<>();
		try {
			Connection conn = DBConfig.getInstance().getConnection();
			PreparedStatement psmt = conn.prepareStatement(Sql.SELECT_ARTICLES);
			psmt.setString(1, type);
			psmt.setInt(2, start);
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
		}catch(Exception e) {
			e.printStackTrace();
		}
		return articles;
	}
	//update
	public void updateArticle(String title, String content, String no) {
		try {
			Connection conn = DBConfig.getInstance().getConnection();
			PreparedStatement psmt = conn.prepareStatement(Sql.UPDATE_ARTICLE);
			psmt.setString(1, title);
			psmt.setString(2, content);
			psmt.setString(3, no);
			psmt.executeUpdate();
			
			conn.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	//delete
	public int deleteFile(String no) {
		int result = 0;
		try {
			Connection conn = DBConfig.getInstance().getConnection();
			PreparedStatement psmt = conn.prepareStatement(Sql.DELETE_FILE);
			psmt.setString(1, no);
			result = psmt.executeUpdate();
			
			conn.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	public String deleteComment(String no) {
		try {
			Connection conn = DBConfig.getInstance().getConnection();
			PreparedStatement psmt = conn.prepareStatement(Sql.DELETE_COMMENT);
			psmt.setString(1, no);
			psmt.executeUpdate();
			
			conn.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return selectFile(no);
	}
	public String deleteArticle(String no) {
		try {
			Connection conn = DBConfig.getInstance().getConnection();
			PreparedStatement psmt = conn.prepareStatement(Sql.DELETE_ARTICLE);
			psmt.setString(1, no);
			psmt.executeUpdate();
			
			conn.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return deleteComment(no);
	}
}
