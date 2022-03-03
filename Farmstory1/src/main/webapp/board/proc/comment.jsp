<%@page import="kr.co.farmstory1.dao.ArticleDao"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%
request.setCharacterEncoding("utf-8");
String no = request.getParameter("no");
String cate = request.getParameter("cate");
String type = request.getParameter("type");
String uid = request.getParameter("uid");
String comment = request.getParameter("comment");
String regip = request.getRemoteAddr();

ArticleDao dao = ArticleDao.getInstance();

//댓글 INSERT
dao.insertComment(no, comment, uid, regip);

//리다이렉트
response.sendRedirect("/Farmstory1/board/view.jsp?cate="+cate+"&type="+type+"&no="+no);
%>