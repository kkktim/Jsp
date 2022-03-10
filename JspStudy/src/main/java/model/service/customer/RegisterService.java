package model.service.customer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.CustDao;
import model.service.CommonService;
import vo.CustVo;

public class RegisterService implements CommonService {

	@Override
	public String businessProc(HttpServletRequest req, HttpServletResponse resp) {
		if(req.getMethod().equals("GET")) {
			return "/Sub8/customer/register.jsp";
		}else {
			String name = req.getParameter("name");
			String address = req.getParameter("address");
			String phone = req.getParameter("phone");
			CustVo cv = new CustVo();
			cv.setName(name);
			cv.setAddress(address);
			cv.setPhone(phone);
			CustDao.getInstance().insertCustomer(cv);
			
			return "/Sub8/customer/list.do";
		}
		
	}

}
