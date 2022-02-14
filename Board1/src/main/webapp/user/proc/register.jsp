<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%
//전송 데이터 수신
request.setCharacterEncoding("utf-8");
String uid = request.getParameter("uid");
String pass1 = request.getParameter("pass1");
String name = request.getParameter("name");
String nick = request.getParameter("nick");
String email = request.getParameter("email");
String hp = request.getParameter("hp");
String zip = request.getParameter("zip");
String addr1 = request.getParameter("addr1");
String addr2 = request.getParameter("addr2");
String regip = request.getRemoteAddr();   //클라이언트 시스템 IP

String host = "jdbc:mysql://13.124.252.103:3306/rkdxogh1987";
String user = "timk";
String pass = "1234";

try{
	Class.forName("com.mysql.jdbc.Driver");
	Connection conn = DriverManager.getConnection(host, user, pass);
	String sql = "INSERT INTO `Board_user` SET ";
	sql += "`uid`=?,";
	sql += "`pass`=PASSWORD(?),";
	sql += "`name`=?,";
	sql += "`nick`=?,";
	sql += "`email`=?,";
	sql += "`hp`=?,";
	sql += "`zip`=?,";
	sql += "`addr1`=?,";
	sql += "`addr2`=?,";
	sql += "`regip`=?,";
	sql += "`rdate`=NOW()";
	
	PreparedStatement psmt = conn.prepareStatement(sql);
	psmt.setString(1, uid);
	psmt.setString(2, pass1);
	psmt.setString(3, name);
	psmt.setString(4, nick);
	psmt.setString(5, email);
	psmt.setString(6, hp);
	psmt.setString(7, zip);
	psmt.setString(8, addr1);
	psmt.setString(9, addr2);
	psmt.setString(10, regip);
	psmt.executeUpdate();
	
	conn.close();
	
	
}catch(Exception e){
	e.printStackTrace();
}

//로그인 페이지로 이동
response.sendRedirect("/Board1/user/login.jsp?success=200");
%>