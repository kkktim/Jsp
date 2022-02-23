<%@page import="kr.co.board1.dao.ArticleDao"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%
request.setCharacterEncoding("utf-8");
String uid = request.getParameter("uid");
String title = request.getParameter("title");
String content = request.getParameter("content");

ArticleDao.getInstance().updateArticle(title, content, uid);

response.sendRedirect("/Board1/list.jsp");

%>