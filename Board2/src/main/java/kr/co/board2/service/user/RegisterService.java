package kr.co.board2.service.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.board2.controller.CommonService;
import kr.co.board2.dao.UserDao;
import kr.co.board2.vo.UserVo;

public class RegisterService implements CommonService {

	@Override
	public String businessProc(HttpServletRequest req, HttpServletResponse resp) {
		if(req.getMethod().equals("GET")) {
			return "/user/register.jsp";
		}else {
			String uid = req.getParameter("uid");
			String pass = req.getParameter("pass1");
			String name = req.getParameter("name");
			String nick = req.getParameter("nick");
			String email = req.getParameter("email");
			String hp = req.getParameter("hp");
			String zip = req.getParameter("zip");
			String addr1 = req.getParameter("addr1");
			String addr2 = req.getParameter("addr2");
			String regip = req.getRemoteAddr();
			
			UserVo uv = new UserVo();
			uv.setUid(uid);
			uv.setPass(pass);
			uv.setName(name);
			uv.setNick(nick);
			uv.setEmail(email);
			uv.setHp(hp);
			uv.setZip(zip);
			uv.setAddr1(addr1);
			uv.setAddr2(addr2);
			uv.setRegip(regip);
			
			UserDao.getInstance().insertUser(uv);
			
			return "redirect:/Board2/user/login.do?success=102";
		}
		
	}

}
