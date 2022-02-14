<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%
request.setCharacterEncoding("utf-8");
String custid = request.getParameter("custid");
String name = request.getParameter("name");
String address = request.getParameter("address");
String phone = request.getParameter("phone");


String host = "jdbc:mysql://13.124.252.103:3306/rkdxogh1987";
String user = "timk";
String pass = "1234";

try{
	
	Class.forName("com.mysql.jdbc.Driver");
	Connection conn = DriverManager.getConnection(host, user, pass);
	
	String sql = "UPDATE `Customer` SET ";
	sql += "`name`=?, `address`=?, `phone`=? WHERE `custid`=?;";
	PreparedStatement psmt = conn.prepareStatement(sql);
	psmt.setString(4, custid);
	psmt.setString(1, name);
	psmt.setString(2, address);
	psmt.setString(3, phone);
	
	psmt.executeUpdate();
	conn.close();
	
}catch(Exception e){
	e.printStackTrace();
}

response.sendRedirect("../list.jsp");
%>