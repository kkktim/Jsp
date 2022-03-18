package kr.co.board2.service.comment;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import kr.co.board2.controller.CommonService;
import kr.co.board2.dao.ArticleDao;
import kr.co.board2.vo.ArticleVo;

public class CommentRegisterService implements CommonService {

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
		
		//��� �Է�
		int no = dao.insertComment(av);
		
		//��� +1
		dao.updateArticleComment(parent, true);
		
		//��� �� ��ȸ
		ArticleVo comment = dao.selectComment(no);
		
		//json ������ ����
		Gson gson = new Gson();
		String jsonData = gson.toJson(comment);
		
		//json ���
		return "json:"+jsonData;
		
	}
}
	
