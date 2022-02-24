<%@page import="kr.co.board1.bean.FileBean"%>
<%@page import="java.io.File"%>
<%@page import="kr.co.board1.dao.ArticleDao"%>
<%@page import="kr.co.board1.db.DBConfig"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%
request.setCharacterEncoding("utf-8");
String id = request.getParameter("id");
String fid = request.getParameter("fid");
String dfile = request.getParameter("dfile");


ArticleDao dao = ArticleDao.getInstance();

//댓글 삭제
dao.deleteCommentsByParent(id);

//파일삭제
// 파일이 첨부된 글이면
if(Integer.parseInt(dfile) > 0){
	FileBean fb = new FileBean();
	fb = dao.deleteFile(fid, dfile);
	String nName = fb.getnName();
	String path = request.getServletContext().getRealPath("/file");
	File file = new File(path+"/"+nName);
	file.delete();
}

//글 삭제
dao.deleteArticle(id);


response.sendRedirect("/Board1/list.jsp");
%>
