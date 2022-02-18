<%@page import="com.google.gson.JsonObject"%>
<%@page import="com.google.gson.Gson"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@ page contentType="application/json;charset=UTF-8" pageEncoding="UTF-8"%>
<%
request.setCharacterEncoding("utf-8");
String sid = request.getParameter("sid");
String name = request.getParameter("name");
String gender = request.getParameter("gender");
String hp = request.getParameter("hp");
String grade = request.getParameter("grade");
String regdate = request.getParameter("regdate");

String host = "jdbc:mysql://13.124.252.103:3306/rkdxogh1987";
String user = "timk";
String pass = "1234";
int result = 0;
try{
	Class.forName("com.mysql.jdbc.Driver");
	Connection conn = DriverManager.getConnection(host, user, pass);
	
	String sql = "INSERT INTO `Students` VALUES (?, ?, ?, ?, ?, ?);";
	PreparedStatement psmt = conn.prepareStatement(sql);
	psmt.setString(1, sid);
	psmt.setString(2, name);
	psmt.setString(3, gender);
	psmt.setString(4, hp);
	psmt.setString(5, grade);
	psmt.setString(6, regdate);
	result = psmt.executeUpdate();
	
	conn.close();
	
}catch(Exception e){
	e.printStackTrace();
}

Gson gson = new Gson();
JsonObject json = new JsonObject();
json.addProperty("result", result);
String jsonData = json.toString();
out.print(jsonData);
%>