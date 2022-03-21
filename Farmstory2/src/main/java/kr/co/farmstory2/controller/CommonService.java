package kr.co.farmstory2.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspWriter;

public interface CommonService {
	public String businessProc(HttpServletRequest req, HttpServletResponse resp);
}
