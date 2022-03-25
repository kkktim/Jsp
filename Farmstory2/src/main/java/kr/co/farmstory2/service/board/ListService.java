package kr.co.farmstory2.service.board;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.co.farmstory2.controller.CommonService;
import kr.co.farmstory2.dao.ArticleDao;
import kr.co.farmstory2.service.user.LoginService;
import kr.co.farmstory2.vo.ArticleVo;
import kr.co.farmstory2.vo.UserVo;

public class ListService extends LoginService implements CommonService {

	@Override
	public String businessProc(HttpServletRequest req, HttpServletResponse resp) {
		HttpSession sess = req.getSession();
		UserVo uv = (UserVo) sess.getAttribute("sessUser");
		
		if(uv == null) {
			return "/user/login.jsp?success=103";
		}
		
		String cate = req.getParameter("cate");
		String type = req.getParameter("type");
		String pg = req.getParameter("pg");
		
		req.setAttribute("cate", cate);
		req.setAttribute("type", type);
		req.setAttribute("pg", pg);
		
		//페이지 처리
		int currentPage = getCurrentPageNum(pg);
		int start = getLimitStart(currentPage);
		//페이지 total
		int total = ArticleDao.getInstance().selectCountTotal(type);
		int lastPageNum = getLastPageNum(total);
		//아래 페이지 인덱스
		int articleStartNum = getArticleStartNum(total, start);
		int[] indexGroups = getIndexGroup(currentPage, lastPageNum);
		
		req.setAttribute("currentPage", currentPage);
		req.setAttribute("lastPageNum", lastPageNum);
		req.setAttribute("articleStartNum", articleStartNum);
		req.setAttribute("indexGroups", indexGroups);
		
		List<ArticleVo> articles = ArticleDao.getInstance().selectArticles(type, start);
		
		req.setAttribute("articles", articles);
		
		return "/board/list.jsp";
		
	}//businessProc end...
	
	private int[] getIndexGroup(int currentPage, int lastPageNum) {
		int groupCurrent = (int) Math.ceil(currentPage / 10.0);
		int groupStart = (groupCurrent - 1) * 10 + 1;
		int groupEnd = groupCurrent * 10;
		
		if(groupEnd > lastPageNum) {
			groupEnd = lastPageNum;
		}
		
		int[] indexGroups = {groupStart, groupEnd};
		return indexGroups;
	}

	private int getArticleStartNum(int total, int start) {
		return (total - start)+1;
	}

	private int getLastPageNum(int total) {
		int lastPageNum = 0;
		if(total % 10 == 0) {
			lastPageNum = total/10;
		}else {
			lastPageNum = total/10 + 1;
		}
		return lastPageNum;
	}

	private int getLimitStart(int currentPage) {
		return (currentPage - 1) * 10;
	}
	
	//최초 pg값 null을 1로 return
	public int getCurrentPageNum(String pg) {
		if(pg == null) {
			pg = "1";
		}
		return Integer.parseInt(pg);
	}


}
