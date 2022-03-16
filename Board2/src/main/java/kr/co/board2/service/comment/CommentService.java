package kr.co.board2.service.comment;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import kr.co.board2.controller.CommonService;
import kr.co.board2.dao.ArticleDao;
import kr.co.board2.vo.ArticleVo;

public class CommentService implements CommonService {

	@Override
	public String businessProc(HttpServletRequest req, HttpServletResponse resp) {
		String uid = req.getParameter("uid");
		String parent = req.getParameter("parent");
		String content = req.getParameter("content");
		String regip = req.getRemoteAddr();
			
		ArticleVo av = new ArticleVo();
		av.setParent(parent);
		av.setContent(content);
		av.setUid(uid);
		av.setRegip(regip);
		
		ArticleDao dao = ArticleDao.getInstance();
		
		//´ñ±Û ÀÔ·Â
		int no = dao.insertComment(av);
		
		//´ñ±Û +1
		dao.updateArticleComment(uid, true);
		
		//´ñ±Û Á¶È¸
		ArticleVo comment = dao.selectComment(no);
		
		//json µ¥ÀÌÅÍ »ý¼º
		Gson gson = new Gson();
		String jsonData = gson.toJson(comment);
		
		//json Ãâ·Â
		return "json:"+jsonData;
		
	}
}
	
