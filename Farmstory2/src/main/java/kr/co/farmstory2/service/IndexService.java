package kr.co.farmstory2.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.farmstory2.controller.CommonService;
import kr.co.farmstory2.dao.ArticleDao;
import kr.co.farmstory2.vo.ArticleVo;

public class IndexService implements CommonService {

	@Override
	public String businessProc(HttpServletRequest req, HttpServletResponse resp) {
		List<ArticleVo> latests = ArticleDao.getInstance().selectLastests();
		
		req.setAttribute("latests1", latests.subList(0, 5));
		req.setAttribute("latests2", latests.subList(5, 10));
		req.setAttribute("latests3", latests.subList(10, 15));
		
		return "/index.jsp";
	}


}
