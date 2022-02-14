<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>

<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%
//전송 데이터 수신
request.setCharacterEncoding("utf-8");

String uid = request.getParameter("uid");
String name= request.getParameter("name");
String hp = request.getParameter("hp");
String age = request.getParameter("age");

String host = "jdbc:mysql://13.124.252.103:3306/rkdxogh1987";
String user = "timk";
String pass = "1234";

try{
	//JDBC 프로그래밍
	Class.forName("com.mysql.jdbc.Driver");
	
	Connection conn = DriverManager.getConnection(host, user, pass);
	
	//3단계
	String sql = "UPDATE `User1` SET `name`=?, `hp`=?, `age`=? ";
	sql += "WHERE `uid`=?;";
	
	PreparedStatement psmt = conn.prepareStatement(sql);
	psmt.setString(1, name);
	psmt.setString(2, hp);
	psmt.setString(3, age);
	psmt.setString(4, uid);
	
	//4단계
	psmt.executeUpdate();
	
	//5단계
	//6단계
	conn.close();
}catch(Exception e){
	e.printStackTrace();
}
//리다이렉트
response.sendRedirect("../Sub5/2.Select.jsp");
%>