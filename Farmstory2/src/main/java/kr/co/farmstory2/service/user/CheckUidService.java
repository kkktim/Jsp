package kr.co.farmstory2.service.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonObject;

import kr.co.farmstory2.controller.CommonService;
import kr.co.farmstory2.dao.UserDao;

public class CheckUidService implements CommonService {

	@Override
	public String businessProc(HttpServletRequest req, HttpServletResponse resp) {
		String uid = req.getParameter("uid");
		//System.out.println("uid : "+uid);   //디버깅
		
		int result = UserDao.getInstance().selectCountUser(uid, 1);
		
		JsonObject json = new JsonObject();   //Gson 라이브러리 클래스
		json.addProperty("result", result);
		
		return "json:"+json.toString();
	}

}
