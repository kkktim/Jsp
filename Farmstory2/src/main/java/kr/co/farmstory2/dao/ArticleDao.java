package kr.co.farmstory2.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import kr.co.farmstory2.config.DBConfig;
import kr.co.farmstory2.config.Sql;
import kr.co.farmstory2.vo.ArticleVo;

public class ArticleDao {
	//教臂沛按眉 积己
	private static ArticleDao instance = new ArticleDao();

	public static ArticleDao getInstance() {
		return instance;
	}
	public ArticleDao() {}
	
	//扁夯 CRUD
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
			
			psmt = conn.prepareStatement(Sql.SELECT_ARTICLE);
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
	
	public void selectArticle() {}
	public void selectArticles() {}
	
	public void updateArticle() {}
	
	public void deleteArticle() {}
}
