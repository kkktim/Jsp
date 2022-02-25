package kr.co.board1.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import kr.co.board1.bean.ArticleBean;
import kr.co.board1.bean.FileBean;
import kr.co.board1.db.DBConfig;
import kr.co.board1.db.Sql;
import kr.co.board1.log.MyLog;

public class ArticleDao {
	
	private static ArticleDao instance = new ArticleDao();
	
	public static ArticleDao getInstance() {
		return instance;
	}
	
	private ArticleDao() {}
	
	//CRUD �޼��� ����
	public void insertFile(int id, String fname, String newName) {
		MyLog.getInstance().error("insertFile start...");
		try{
			Connection conn = DBConfig.getInstance().getConnection();
			PreparedStatement psmt = conn.prepareStatement(Sql.INSERT_FILE);
			psmt.setInt(1, id);
			psmt.setString(2, fname);
			psmt.setString(3, newName);
			psmt.executeUpdate();
			
			conn.close();
			
		}catch(Exception e){
			e.printStackTrace();
			MyLog.getInstance().error(e.getMessage());
		}
	}
	
	public int insertArticle(ArticleBean article) {
		MyLog.getInstance().error("insertArticle start...");
		try{
			Connection conn = DBConfig.getInstance().getConnection();
			//INSERT ����
			PreparedStatement psmt = conn.prepareStatement(Sql.INSERT_ARTICLE);
			psmt.setString(1, article.getTitle());
			psmt.setString(2, article.getContent());
			psmt.setInt(3, article.getFname() == null ? 0 : 1);
			psmt.setString(4, article.getUid());
			psmt.setString(5, article.getRegip());
			psmt.executeUpdate();
			
			conn.close();
			
		}catch(Exception e){
			e.printStackTrace();
			MyLog.getInstance().error(e.getMessage());
		}
		// ��� INSERT�� �� ��ȣ ��ȸ
		return selectMaxId();
	}
	public void insertComment(ArticleBean article) {
		MyLog.getInstance().error("insertComment start...");
		try{
			Connection conn = DBConfig.getInstance().getConnection();
			//INSERT ����
			PreparedStatement psmt = conn.prepareStatement(Sql.INSERT_COMMENT);
			psmt.setInt(1, article.getParent());
			psmt.setString(2, article.getContent());
			psmt.setString(3, article.getUid());
			psmt.setString(4, article.getRegip());
			psmt.executeUpdate();
			
			conn.close();
			
		}catch(Exception e){
			e.printStackTrace();
			MyLog.getInstance().error(e.getMessage());
		}
		// ��� INSERT�� �� ��ȣ ��ȸ
	}
	
	public int selectMaxId() {
		MyLog.getInstance().error("selectMaxId start...");
		int id = 0;
		try {
			
			Connection conn = DBConfig.getInstance().getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(Sql.SELECT_MAX_ID);
					
			if(rs.next()){
				id = rs.getInt(1);
			}
		}catch(Exception e) {
			MyLog.getInstance().error(e.getMessage());
			e.printStackTrace();
		}
		return id;
	}
	public int selectCountId() {
		MyLog.getInstance().error("selectCountId start...");
		int total = 0;
		try{
			Connection conn = DBConfig.getInstance().getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(Sql.SELECT_COUNT_ID);
			if(rs.next()){
				total = rs.getInt(1);
			}
			conn.close();
		}catch(Exception e){
			e.printStackTrace();
			MyLog.getInstance().error(e.getMessage());
		}
		return total;
	}
	
	public FileBean selectFile(String fid) {
		MyLog.getInstance().error("selectFile start...");
		FileBean fb = null;
		try {
			Connection conn = DBConfig.getInstance().getConnection();
			PreparedStatement psmt = conn.prepareStatement(Sql.SELECT_FILE);
			psmt.setString(1, fid);
			ResultSet rs = psmt.executeQuery();
			if(rs.next()) {
				fb = new FileBean();
				fb.setFid(rs.getInt(1));
				fb.setParent(rs.getInt(2));
				fb.setoName(rs.getString(3));
				fb.setnName(rs.getString(4));
				fb.setDownload(rs.getInt(5));
				fb.setRdate(rs.getString(6));
			}
			conn.close();
		}catch(Exception e){
			e.printStackTrace();
			MyLog.getInstance().error(e.getMessage());
		}
		return fb;
	}
	public ArticleBean selectArticle(String id) {
		MyLog.getInstance().error("selectArticle start...");
		ArticleBean article = null;
		try {
			Connection conn = DBConfig.getInstance().getConnection();
			PreparedStatement psmt = conn.prepareStatement(Sql.SELECT_ARTICLE);
			psmt.setString(1, id);
			ResultSet rs = psmt.executeQuery();
			
			if(rs.next()) {
				article = new ArticleBean();
				article.setId(rs.getInt(1));
				article.setParent(rs.getInt(2));
				article.setComment(rs.getInt(3));
				article.setCate(rs.getString(4));
				article.setTitle(rs.getString(5));
				article.setContent(rs.getString(6));
				article.setFile(rs.getInt(7));
				article.setHit(rs.getInt(8));
				article.setUid(rs.getString(9));
				article.setRegip(rs.getString(10));
				article.setRdate(rs.getString(11));
				
				//��������
				FileBean fb = new FileBean();
				fb.setFid(rs.getInt(12));
				fb.setParent(rs.getInt(13));
				fb.setoName(rs.getString(14));
				fb.setnName(rs.getString(15));
				fb.setDownload(rs.getInt(16));
				fb.setRdate(rs.getString(17));
				
				article.setFb(fb);;
			}
			conn.close();
		}catch(Exception e ){
			e.printStackTrace();
			MyLog.getInstance().error(e.getMessage());
		}
		return article;
	}
	
	public ArticleBean selectModifyArticle(String id) {
		MyLog.getInstance().error("selectModifyArticle start...");
		ArticleBean article = null;
		try {
			Connection conn = DBConfig.getInstance().getConnection();
			PreparedStatement psmt = conn.prepareStatement(Sql.SELECT_MODIFY_ARTICLE_ADD_FILE);
			psmt.setString(1, id);
			ResultSet rs = psmt.executeQuery();
			if(rs.next()) {
				article = new ArticleBean();
				article.setTitle(rs.getString(1));
				article.setContent(rs.getString(2));
				article.setUid(rs.getString(4));;
				
				FileBean fb = new FileBean();
				fb.setoName(rs.getString(3));
				
				article.setFb(fb);
			}
			conn.close();
		}catch(Exception e) {
			e.printStackTrace();
			MyLog.getInstance().error(e.getMessage());
		}
		return article;
	}
	
	public List<ArticleBean> selectArticles(int start) {
		
		List<ArticleBean> articles = new ArrayList<>();
		MyLog.getInstance().error("selectArticles...");

		try{
			Connection conn = DBConfig.getInstance().getConnection();
			PreparedStatement psmt = conn.prepareStatement(Sql.SELECT_ARTICLES);
			psmt.setInt(1, start);
			ResultSet rs = psmt.executeQuery();
			while(rs.next()){
				ArticleBean article = new ArticleBean();
				article.setId(rs.getInt(1));
				article.setParent(rs.getInt(2));
				article.setComment(rs.getInt(3));
				article.setCate(rs.getString(4));
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
			
		}catch(Exception e){
			e.printStackTrace();
			MyLog.getInstance().error(e.getMessage());
		}
		return articles;
	}
	public List<ArticleBean> selectComments(String parent) {
		MyLog.getInstance().error("selectComments start...");
		List<ArticleBean> comments = new ArrayList<ArticleBean>();
		try {
			Connection conn = DBConfig.getInstance().getConnection();
			PreparedStatement psmt = conn.prepareStatement(Sql.SELECT_COMMENTS);
			psmt.setString(1, parent);
			ResultSet rs = psmt.executeQuery();
			while(rs.next()) {
				ArticleBean comment = new ArticleBean();
				comment.setId(rs.getInt(1));
				comment.setParent(rs.getInt(2));
				comment.setContent(rs.getString(6));
				comment.setUid(rs.getString(9));
				comment.setRegip(rs.getString(10));
				comment.setRdate(rs.getString(11));
				comment.setNick(rs.getString(12));
				comments.add(comment);
			}
			rs.close();
			psmt.close();
			conn.close();
		}catch(Exception e) {
			e.printStackTrace();
			MyLog.getInstance().error(e.getMessage());
		}
		return comments;
	}
	
	public void updateFileCount(int fid) {
		MyLog.getInstance().error("updateFileCount start...");
		try {
			Connection conn = DBConfig.getInstance().getConnection();
			PreparedStatement psmt = conn.prepareStatement(Sql.UPDATE_FILE_COUNT);
			psmt.setInt(1, fid);
			psmt.executeUpdate();
			conn.close();
			
		}catch(Exception e) {
			e.printStackTrace();
			MyLog.getInstance().error(e.getMessage());
		}
	}
	public void updateArticleHit(int id) {
		MyLog.getInstance().error("updateArticleHit start...");
		try {
			Connection conn = DBConfig.getInstance().getConnection();
			PreparedStatement psmt = conn.prepareStatement(Sql.UPDATE_ARTICLE_HIT);
			psmt.setInt(1, id);
			psmt.executeUpdate();
			conn.close();
			
		}catch(Exception e) {
			e.printStackTrace();
			MyLog.getInstance().error(e.getMessage());
		}
	}
	public void updateArticleComment(String id, boolean isPlus) {
		MyLog.getInstance().error("updateArticleComment start...");
		try {
			Connection conn = DBConfig.getInstance().getConnection();
			PreparedStatement psmt = null;
			if(isPlus) {
				psmt = conn.prepareStatement(Sql.UPDATE_ARTICLE_COMMENT_PLUS);
			}else {
				psmt = conn.prepareStatement(Sql.UPDATE_ARTICLE_COMMENT_MINUS);
			}
			
			psmt.setString(1, id);
			psmt.executeUpdate();
			conn.close();
			
		}catch(Exception e) {
			e.printStackTrace();
			MyLog.getInstance().error(e.getMessage());
		}
	}
	public void updateArticle(String title, String content, String id) {
		MyLog.getInstance().error("updateArticle start...");
		try {
			Connection conn = DBConfig.getInstance().getConnection();
			PreparedStatement psmt = conn.prepareStatement(Sql.UPDATE_ARTICLE);
			psmt.setString(1, title);
			psmt.setString(2, content);
			psmt.setString(3, id);
			psmt.executeUpdate();
			conn.close();
		}catch(Exception e) {
			e.printStackTrace();
			MyLog.getInstance().error(e.getMessage());
		}
	}
	public int updateComment(String content, String id) {
		MyLog.getInstance().error("updateComment start...");
		int result = 0;
		try {
			Connection conn = DBConfig.getInstance().getConnection();
			PreparedStatement psmt = conn.prepareStatement(Sql.UPDATE_COMMENT);
			psmt.setString(1, content);
			psmt.setString(2, id);
			result = psmt.executeUpdate();
			conn.close();
		}catch(Exception e) {
			e.printStackTrace();
			MyLog.getInstance().error(e.getMessage());
		}
		return result;
	}

	public void deleteArticle(String id) {
		MyLog.getInstance().error("deleteArticle start...");
		try {
			Connection conn = DBConfig.getInstance().getConnection();
			PreparedStatement psmt = conn.prepareStatement(Sql.DELETE_ARTICLE);
			psmt.setString(1, id);
			psmt.executeUpdate();
			conn.close();
		}catch(Exception e) {
			e.printStackTrace();
			MyLog.getInstance().error(e.getMessage());
		}
	}
	public FileBean deleteFile(String fid, String dfile) {
		MyLog.getInstance().error("deleteFile start...");
		FileBean fb = null;
		PreparedStatement psmt = null;
		try {
			Connection conn = DBConfig.getInstance().getConnection();
			//÷�������� �����ϸ� nName ��� ��������
			if(Integer.parseInt(dfile) > 0) {
				psmt = conn.prepareStatement(Sql.SELECT_FILE_BY_FID);
				psmt.setString(1, fid);
				ResultSet rs = psmt.executeQuery();
				if(rs.next()) {
					fb = new FileBean();
					fb.setnName(rs.getString(1));
				}
			}
			//Board_file ���̺� ������ �����ϱ�
			psmt = conn.prepareStatement(Sql.DELETE_FILE);
			psmt.setString(1, fid);
			psmt.executeUpdate();
			
			conn.close();
		}catch(Exception e) {
			e.printStackTrace();
			MyLog.getInstance().error(e.getMessage());
		}
		return fb;
	}
	public void deleteComment(String id) {
		MyLog.getInstance().error("deleteComment start...");
		//��� �����ϰ�, ���� ��� ī��Ʈ -1 ���� ����
		try {
			Connection conn = DBConfig.getInstance().getConnection();
			PreparedStatement psmt = conn.prepareStatement(Sql.DELETE_COMMENT);
			psmt.setString(1, id);
			psmt.executeUpdate();
			conn.close();
		}catch(Exception e) {
			e.printStackTrace();
			MyLog.getInstance().error(e.getMessage());
		}
	}
	public void deleteCommentsByParent(String id) {
		MyLog.getInstance().error("deleteCommentsByParent start...");
		try {
			//��� �����ϱ�
			Connection conn = DBConfig.getInstance().getConnection();
			PreparedStatement psmt = conn.prepareStatement(Sql.DELETE_COMMENT_BY_PARENT);
			psmt.setString(1, id);
			psmt.executeUpdate();
			conn.close();
		}catch(Exception e) {
			e.printStackTrace();
			MyLog.getInstance().error(e.getMessage());
		}
	}
}
