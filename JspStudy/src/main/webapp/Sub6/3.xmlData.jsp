<%@page import="java.util.ArrayList"%>
<%@page import="com.google.gson.Gson"%>
<%@page import="bean.MemberBean"%>
<%@page import="java.util.List"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@ page contentType="text/xml;charset=UTF-8" pageEncoding="UTF-8"%>
<%--
	날짜 : 2022/02/17
	이름 : 강태호
	내용 : Xml Data 실습
--%>
<%
String host = "jdbc:mysql://13.124.252.103:3306/rkdxogh1987";
String user = "timk";
String pass = "1234";

String xml = "<members>";

try{
	Class.forName("com.mysql.jdbc.Driver");
	Connection conn = DriverManager.getConnection(host, user, pass);
	Statement stmt = conn.createStatement();
	
	String sql = "SELECT * FROM `Member`;";
	ResultSet rs = stmt.executeQuery(sql);
	while(rs.next()){
		xml += "<member>";
		xml += "<uid>"+rs.getString(1)+"</uid>";
		xml += "<name>"+rs.getString(2)+"</name>";
		xml += "<hp>"+rs.getString(3)+"</hp>";
		xml += "<pos>"+rs.getString(4)+"</pos>";
		xml += "<dep>"+rs.getInt(5)+"</dep>";
		xml += "<rdata>"+rs.getString(6)+"</rdata>";
		xml += "</member>";
	}
	conn.close();
}catch(Exception e){
	e.printStackTrace();
}

xml += "</members>";

//xml 데이터 출력
out.println(xml);
%>