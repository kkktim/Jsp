package kr.co.farmstory2.service.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.co.farmstory2.controller.CommonService;
import kr.co.farmstory2.dao.ArticleDao;
import kr.co.farmstory2.vo.ArticleVo;

public class ViewService implements CommonService {

	@Override
	public String businessProc(HttpServletRequest req, HttpServletResponse resp) {
		
		String cate = req.getParameter("cate");
		String type = req.getParameter("type");
		String no = req.getParameter("no");
		
		req.setAttribute("cate", cate);
		req.setAttribute("type", type);
		
		ArticleVo article = ArticleDao.getInstance().selectArticle(no);
		
		req.setAttribute("article", article);
		
		return "/board/view.jsp?cate="+cate;
	}


}
