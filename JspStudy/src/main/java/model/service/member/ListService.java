package model.service.member;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.MemberDao;
import model.service.CommonService;
import vo.MemberVo;

public class ListService implements CommonService {

	@Override
	public String businessProc(HttpServletRequest req, HttpServletResponse resp) {
		//dao
		List<MemberVo> members =  MemberDao.getInstance().selectMembers();
		
		//member dateset 을 리스트 페이지에 참조하기 위해 request 객체로 저장
		req.setAttribute("members", members);
		
		return "/Sub8/member/list.jsp";
	}

}
