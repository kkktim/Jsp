package kr.co.farmstory2.service.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.farmstory2.controller.CommonService;
import kr.co.farmstory2.dao.UserDao;
import kr.co.farmstory2.vo.UserVo;

public class RegisterService implements CommonService {

	@Override
	public String businessProc(HttpServletRequest req, HttpServletResponse resp) {
		if(req.getMethod().equals("GET")) {
			return "/user/register.jsp";
		}else {
			UserVo uv = new UserVo();
			uv.setUid(req.getParameter("uid"));
			uv.setPass(req.getParameter("pass2"));
			uv.setName(req.getParameter("name"));
			uv.setNick(req.getParameter("nick"));
			uv.setEmail(req.getParameter("email"));
			uv.setHp(req.getParameter("hp"));
			uv.setZip(req.getParameter("zip"));
			uv.setAddr1(req.getParameter("addr1"));
			uv.setAddr2(req.getParameter("addr2"));
			uv.setRegip(req.getRemoteAddr());
			
			UserDao.getInstance().insertUser(uv);
			
			return "redirect:/Farmstory2/user/login.do";
		}
			
	}


}
