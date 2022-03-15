package kr.co.board2.service.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonObject;

import kr.co.board2.controller.CommonService;
import kr.co.board2.dao.UserDao;

public class CheckNickService implements CommonService {

	@Override
	public String businessProc(HttpServletRequest req, HttpServletResponse resp) {
		String nick = req.getParameter("nick");
		
		int count = UserDao.getInstance().selectCountUser(nick, 2);
		//json 데이터 생성
		JsonObject json = new JsonObject();
		json.addProperty("result", count);
		//json 출력
		return "json:"+json.toString();
	}
	
}
