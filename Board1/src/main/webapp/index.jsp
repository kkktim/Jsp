<%@page import="org.apache.log4j.LogManager"%>
<%@page import="org.apache.log4j.Logger"%>
<%@page import="kr.co.board1.bean.UserBean"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%
Logger _logger = LogManager.getLogger(this.getClass());
_logger.info("index.jsp start...");

UserBean sessUser = (UserBean) session.getAttribute("sessUser");

if(sessUser == null){
	pageContext.forward("./user/login.jsp");
	_logger.info("forward to login...");
}else{
	pageContext.forward("./list.jsp");
	_logger.info("forward to list...");
}

%>