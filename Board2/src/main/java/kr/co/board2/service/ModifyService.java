package kr.co.board2.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.co.board2.controller.CommonService;
import kr.co.board2.dao.ArticleDao;
import kr.co.board2.dao.UserDao;
import kr.co.board2.vo.ArticleVo;
import kr.co.board2.vo.UserVo;

public class ModifyService implements CommonService {

	@Override
	public String businessProc(HttpServletRequest req, HttpServletResponse resp) {
		HttpSession sess = req.getSession();
		UserVo user = (UserVo) sess.getAttribute("sessUser");
		if(user == null) {
			return "redirect:/Board2/user/login.do";
		}else {
			if(req.getMethod().equals("GET")) {
				String no = req.getParameter("no");
				ArticleVo av = ArticleDao.getInstance().selectArticle(no);
				req.setAttribute("av", av);
				
				return "modify.jsp";
			}else {
				String no = req.getParameter("no");
				String title = req.getParameter("title");
				String content = req.getParameter("content");
				
				ArticleDao.getInstance().updateArticle(title, content, no);
				
				return "redirect:/Board2/view.do?no="+no;
			}
			

			
		}
		
		
	}

}
