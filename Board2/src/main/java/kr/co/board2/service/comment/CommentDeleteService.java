package kr.co.board2.service.comment;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonObject;

import kr.co.board2.controller.CommonService;
import kr.co.board2.dao.ArticleDao;

public class CommentDeleteService implements CommonService {

	@Override
	public String businessProc(HttpServletRequest req, HttpServletResponse resp) {
		String no = req.getParameter("no");
		String parent = req.getParameter("parent");
		
		int result = ArticleDao.getInstance().deleteComment(no);
		
		JsonObject json = new JsonObject();
		json.addProperty("result", result);
		
		//´ñ±Û ¼ö -1
		ArticleDao.getInstance().updateArticleComment(parent, false);
		
		return "json:"+json.toString();
	}

}
