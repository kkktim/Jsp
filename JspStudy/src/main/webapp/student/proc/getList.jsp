<%@page import="com.google.gson.JsonObject"%>
<%@page import="com.google.gson.Gson"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="bean.StudentBean"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@ page contentType="application/json;charset=UTF-8" pageEncoding="UTF-8"%>
<%
String host = "jdbc:mysql://13.124.252.103:3306/rkdxogh1987";
String user = "timk";
String pass = "1234";

List<StudentBean> students = new ArrayList();

try{
	Class.forName("com.mysql.jdbc.Driver");
	Connection conn = DriverManager.getConnection(host, user, pass);
	Statement stmt = conn.createStatement();
	
	String sql = "SELECT * FROM `Students`;";
	ResultSet rs = stmt.executeQuery(sql);
	
	while(rs.next()){
		StudentBean sb = new StudentBean();
		sb.setSid(rs.getString(1));
		sb.setName(rs.getString(2));
		sb.setGender(rs.getInt(3));
		sb.setHp(rs.getString(4));
		sb.setGrade(rs.getInt(5));
		sb.setRegdate(rs.getString(6));
		
		students.add(sb);
	}
	conn.close();
	
}catch(Exception e){
	e.printStackTrace();
}

Gson gson = new Gson();
String jsonData = gson.toJson(students);
out.println(jsonData);
%>