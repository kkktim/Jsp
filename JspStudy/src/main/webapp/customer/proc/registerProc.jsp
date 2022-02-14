<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%
//데이터수신
request.setCharacterEncoding("utf-8");
String custid = request.getParameter("custid");
String name = request.getParameter("name");
String address = request.getParameter("address");
String phone = request.getParameter("phone");

//데이터 베이스 접속
String host = "jdbc:mysql://13.124.252.103:3306/rkdxogh1987";
String user = "timk";
String pass = "1234";

try{
	
	Class.forName("com.mysql.jdbc.Driver");
	Connection conn = DriverManager.getConnection(host, user, pass);
	
	String sql = "INSERT INTO `Customer` VALUES (?,?,?,?);";
	PreparedStatement psmt = conn.prepareStatement(sql);
	psmt.setString(1, custid);
	psmt.setString(2, name);
	psmt.setString(3, address);
	psmt.setString(4, phone);
	
	psmt.executeUpdate();
	conn.close();
	
	
}catch(Exception e){
	e.printStackTrace();
}

response.sendRedirect("../list.jsp");

%>