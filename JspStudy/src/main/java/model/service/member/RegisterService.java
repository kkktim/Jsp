package model.service.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.MemberDao;
import model.service.CommonService;
import vo.MemberVo;

public class RegisterService implements CommonService {

	@Override
	public String businessProc(HttpServletRequest req, HttpServletResponse resp) {
		if(req.getMethod().equals("GET")) {
			return "/Sub8/member/register.jsp";
		}else {
			String uid = req.getParameter("uid");
			String name = req.getParameter("name");
			String hp = req.getParameter("hp");
			String pos = req.getParameter("pos");
			String dep = req.getParameter("dep");
			
			MemberVo mv = new MemberVo();
			mv.setUid(uid);
			mv.setName(name);
			mv.setHp(hp);
			mv.setPos(pos);
			mv.setDep(dep);
			
			MemberDao.getInstance().insertMember(mv);
			
			return "redirect:/JspStudy/Sub8/member/list.do";
		}
		
	}

}
