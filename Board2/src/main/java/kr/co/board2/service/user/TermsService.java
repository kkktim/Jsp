package kr.co.board2.service.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.board2.controller.CommonService;
import kr.co.board2.dao.UserDao;
import kr.co.board2.vo.TermsVo;

public class TermsService implements CommonService {

	@Override
	public String businessProc(HttpServletRequest req, HttpServletResponse resp) {
		//약관 내용 가져오기
		TermsVo tv = UserDao.getInstance().selectTerms();
		
		req.setAttribute("TermsVo", tv);
		
		return "/user/terms.jsp";
	}

}
