package kr.co.board2.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import kr.co.board2.db.DBConfig;
import kr.co.board2.db.Sql;
import kr.co.board2.vo.ArticleVo;
import kr.co.board2.vo.FileVo;

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
	public int insertComment(ArticleVo vo) {
		try {
			Connection conn = DBConfig.getInstance().getConnection();
			PreparedStatement psmt = conn.prepareStatement(Sql.INSERT_COMMENT);
			psmt.setInt(1, vo.getParent());
			psmt.setString(2, vo.getContent());
			psmt.setString(3, vo.getUid());
			psmt.setString(4, vo.getRegip());
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
	public ArticleVo selectArticle(String no) {
		ArticleVo av = new ArticleVo();
		try {
			Connection conn = DBConfig.getInstance().getConnection();
			PreparedStatement psmt = conn.prepareStatement(Sql.SELECT_ARTICLE);
			psmt.setString(1, no);
			ResultSet rs = psmt.executeQuery();
			if(rs.next()) {
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
				
				FileVo fv = new FileVo();
				fv.setFid(rs.getInt(12));
				fv.setParent(rs.getInt(13));
				fv.setoName(rs.getString(14));
				fv.setnName(rs.getString(15));
				fv.setDownload(rs.getInt(16));
				fv.setRdate(rs.getString(17));
				
				av.setFv(fv);
			}
			conn.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return av;
	}
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
	public List<ArticleVo> selectComments(String parent) {
		List<ArticleVo> comments = new ArrayList<>();
		try {
			Connection conn = DBConfig.getInstance().getConnection();
			PreparedStatement psmt = conn.prepareStatement(Sql.SELECT_COMMENTS);
			psmt.setString(1, parent);
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
				comments.add(av);
			}
			
			conn.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return comments;
	}
	public ArticleVo selectComment(int no) {
		ArticleVo comment = new ArticleVo();
		try {
			Connection conn = DBConfig.getInstance().getConnection();
			PreparedStatement psmt = conn.prepareStatement(Sql.SELECT_COMMENT);
			psmt.setInt(1, no);
			ResultSet rs = psmt.executeQuery();
			if(rs.next()) {
				comment.setNo(rs.getInt(1));
				comment.setParent(rs.getInt(2));
				comment.setComment(rs.getInt(3));
				comment.setType(rs.getString(4));
				comment.setTitle(rs.getString(5));
				comment.setContent(rs.getString(6));
				comment.setFile(rs.getInt(7));
				comment.setHit(rs.getInt(8));
				comment.setUid(rs.getString(9));
				comment.setRegip(rs.getString(10));
				comment.setRdate(rs.getString(11).substring(2, 10));
				comment.setNick(rs.getString(12));
				
			}
			conn.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return comment;
	}
	public FileVo selectFile(String fid) {
		FileVo fv = new FileVo();
		try {
			Connection conn = DBConfig.getInstance().getConnection();
			PreparedStatement psmt = conn.prepareStatement(Sql.SELECT_FILE);
			psmt.setString(1, fid);
			ResultSet rs = psmt.executeQuery();
			if(rs.next()) {
				fv.setFid(rs.getInt(1));
				fv.setParent(rs.getInt(2));
				fv.setoName(rs.getString(3));
				fv.setnName(rs.getString(4));
				fv.setDownload(rs.getInt(5));
				fv.setRdate(rs.getString(6));
			}
			conn.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return fv;
	}
	
	public void updateFileCount(String fid) {
		try {
			Connection conn = DBConfig.getInstance().getConnection();
			PreparedStatement psmt = conn.prepareStatement(Sql.UPDATE_FILE_COUNT);
			psmt.setString(1, fid);
			psmt.executeUpdate();
			
			conn.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	public int updateComment(String content, String no) {
		int result = 0;
		try {
			Connection conn = DBConfig.getInstance().getConnection();
			PreparedStatement psmt = conn.prepareStatement(Sql.UPDATE_COMMENT);
			psmt.setString(1, content);
			psmt.setString(2, no);
			result = psmt.executeUpdate();
			
			conn.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public void updateArticleHit(String no) {
		try {
			Connection conn = DBConfig.getInstance().getConnection();
			PreparedStatement psmt = conn.prepareStatement(Sql.UPDATE_ARTICLE_HIT);
			psmt.setString(1, no);
			psmt.executeUpdate();
			
			conn.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	public void updateArticleComment(String no, boolean isCom) {
		try {
			PreparedStatement psmt = null;
			Connection conn = DBConfig.getInstance().getConnection();
			if(isCom == true) {
				psmt = conn.prepareStatement(Sql.UPDATE_ARTICLE_COMMENT_PLUS);
			}else {
				psmt = conn.prepareStatement(Sql.UPDATE_ARTICLE_COMMENT_MINUS);
			}
			psmt.setString(1, no);
			psmt.executeUpdate();
			
			conn.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
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
	
	public void deleteFile(String fid) {
		try {
			Connection conn = DBConfig.getInstance().getConnection();
			PreparedStatement psmt = conn.prepareStatement(Sql.DELETE_FILE);
			psmt.setString(1, fid);
			psmt.executeUpdate();
			
			conn.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	public void deleteArticle(String no) {
		try {
			Connection conn = DBConfig.getInstance().getConnection();
			PreparedStatement psmt = conn.prepareStatement(Sql.DELETE_ARTICLE);
			psmt.setString(1, no);
			psmt.executeUpdate();
			
			conn.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		try {
			Connection conn = DBConfig.getInstance().getConnection();
			PreparedStatement psmt = conn.prepareStatement(Sql.DELETE_COMMENT);
			psmt.setString(1, no);
			psmt.executeUpdate();
			
			conn.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	public int deleteComment(String no) {
		int result = 0;
		try {
			Connection conn = DBConfig.getInstance().getConnection();
			PreparedStatement psmt = conn.prepareStatement(Sql.DELETE_COMMENT);
			psmt.setString(1, no);
			result = psmt.executeUpdate();
			conn.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
