package kr.co.farmstory2.service.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.farmstory2.controller.CommonService;
import kr.co.farmstory2.dao.ArticleDao;
import kr.co.farmstory2.vo.ArticleVo;

public class ModifyService implements CommonService {

	@Override
	public String businessProc(HttpServletRequest req, HttpServletResponse resp) {
		if(req.getMethod().equals("GET")) {
			String cate = req.getParameter("cate");
			String type = req.getParameter("type");
			String no = req.getParameter("no");
			
			req.setAttribute("cate", cate);
			req.setAttribute("type", type);
			
			ArticleVo av = ArticleDao.getInstance().selectArticle(no);
			
			req.setAttribute("av", av);
			
			return "/board/modify.jsp";
		}else {
			String cate = req.getParameter("cate");
			String type = req.getParameter("type");
			String no = req.getParameter("no");
			
			ArticleDao.getInstance().updateArticle(req.getParameter("title"),  req.getParameter("content"), no);
			
			return "redirect:/Farmstory2/board/view.do?cate="+cate+"&type="+type+"&no="+no;
		}
		
	}


}
