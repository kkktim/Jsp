<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%
//전송 데이터 수신
request.setCharacterEncoding("utf-8");
String uid = request.getParameter("uid");
String name = request.getParameter("name");
String gender = request.getParameter("gender");
String hp = request.getParameter("hp");
String email = request.getParameter("email");
String pos = request.getParameter("pos");
String dep = request.getParameter("dep");


//데이터베이스 접속
String host = "jdbc:mysql://13.124.252.103:3306/rkdxogh1987";
String user = "timk";
String pass = "1234";
try{
	//1단계
	Class.forName("com.mysql.jdbc.Driver");
	//2단계
	Connection conn = DriverManager.getConnection(host, user, pass);
	//3단계 4단계
	String sql ="INSERT INTO `Employee` VALUES (?, ?, ?, ?, ?, ?, ?, NOW());";
	PreparedStatement psmt = conn.prepareStatement(sql);
	psmt.setString(1, uid);
	psmt.setString(2, name);
	psmt.setString(3, gender);
	psmt.setString(4, hp);
	psmt.setString(5, email);
	psmt.setString(6, pos);
	psmt.setString(7, dep);
	psmt.executeUpdate();
	//5단계
	//6단계
	conn.close();
}catch(Exception e){
	e.printStackTrace();
}
response.sendRedirect("../list.jsp");
%>