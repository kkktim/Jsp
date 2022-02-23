<%@page import="kr.co.board1.bean.FileBean"%>
<%@page import="java.io.File"%>
<%@page import="kr.co.board1.dao.ArticleDao"%>
<%@page import="kr.co.board1.db.DBConfig"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%
request.setCharacterEncoding("utf-8");
String id = request.getParameter("id");
String parent = request.getParameter("parent");
String dfile = request.getParameter("file");

ArticleDao dao = ArticleDao.getInstance();

dao.deleteArticle(id);


FileBean fb = new FileBean();
fb = dao.deleteFile(parent, dfile);

// 파일이 첨부된 글이면
if(dfile != null){
	/*
	File file = new File(path+"/"+nName);
	file.delete();
	*/
}




response.sendRedirect("/Board1/list.jsp");
%>
