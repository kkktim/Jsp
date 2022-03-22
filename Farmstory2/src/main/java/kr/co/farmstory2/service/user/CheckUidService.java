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
		//System.out.println("uid : "+uid);   //�����
		
		int result = UserDao.getInstance().selectCountUser(uid, 1);
		
		JsonObject json = new JsonObject();   //Gson ���̺귯�� Ŭ����
		json.addProperty("result", result);
		
		return "json:"+json.toString();
	}

}
