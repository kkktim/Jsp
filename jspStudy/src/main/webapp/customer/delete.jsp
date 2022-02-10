<%@page import="java.sql.Statement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%
request.setCharacterEncoding("utf-8");
String uid = request.getParameter("uid");


try{
	String host = "jdbc:mysql://13.124.252.103:3306/rkdxogh1987";
	String user = "timk";
	String pass = "1234";
	
	Class.forName("com.mysql.jdbc.Driver");
	Connection conn = DriverManager.getConnection(host, user, pass);
	Statement stmt = conn.createStatement();
	stmt.executeUpdate("DELETE FROM `Member` WHERE `uid`= '"+uid+"'");
	conn.close();
	
}catch(Exception e){
	e.printStackTrace();
}


response.sendRedirect("./list.jsp");
%>