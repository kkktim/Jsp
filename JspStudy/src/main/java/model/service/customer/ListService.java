package model.service.customer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.service.CommonService;

public class ListService implements CommonService {

	@Override
	public String businessProc(HttpServletRequest req, HttpServletResponse resp) {
		return "/Sub8/customer/list.jsp";
	}

}
