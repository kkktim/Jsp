package kr.co.board2.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.co.board2.controller.CommonService;
import kr.co.board2.dao.ArticleDao;
import kr.co.board2.vo.ArticleVo;
import kr.co.board2.vo.UserVo;

public class ViewService implements CommonService {

	@Override
	public String businessProc(HttpServletRequest req, HttpServletResponse resp) {
		//로그인 체크
		HttpSession sess = req.getSession();
		UserVo uv = (UserVo) sess.getAttribute("sessUser");
		if(uv == null) {
			return "redirect:/Board2/user/login.do";
		}else {
			ArticleDao dao = ArticleDao.getInstance();
			
			String no = req.getParameter("no");
			
			ArticleVo av = dao.selectArticle(no);
			req.setAttribute("av", av);
			
			//댓글 가져오기
			List<ArticleVo> comments = dao.selectComments(no);
			req.setAttribute("comments", comments);
			
			//글 조회수 +1
			ArticleDao.getInstance().updateArticleHit(no);
			
			return "/view.jsp";
		}
	}

}
