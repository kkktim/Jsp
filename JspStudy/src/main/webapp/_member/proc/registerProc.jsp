<%@page import="com.google.gson.JsonObject"%>
<%@page import="com.google.gson.Gson"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@ page contentType="application/json;charset=UTF-8" pageEncoding="UTF-8"%>
<%
//전송 데이터 수신
request.setCharacterEncoding("utf-8");
String uid = request.getParameter("uid");
String name = request.getParameter("name");
String hp = request.getParameter("hp");
String pos = request.getParameter("pos");
String dep = request.getParameter("dep");

//데이터 베이스 작업(preparestatment 사용)
String host = "jdbc:mysql://13.124.252.103:3306/rkdxogh1987";
String user = "timk";
String pass = "1234";

int result = 0;

try{
	Class.forName("com.mysql.jdbc.Driver");
	Connection conn = DriverManager.getConnection(host, user, pass);
	
	String sql = "INSERT INTO `Member` VALUES (?, ?, ?, ?, ?, NOW());";
	PreparedStatement psmt = conn.prepareStatement(sql);
	psmt.setString(1, uid);
	psmt.setString(2, name);
	psmt.setString(3, hp);
	psmt.setString(4, pos);
	psmt.setString(5, dep);

	result = psmt.executeUpdate();
	conn.close();
	
}catch(Exception e){
	e.printStackTrace();
}


//결과 json 출력
//String jsonData = "{\"result\":"+result+"}";
//out.print(jsonData);

Gson gson = new Gson();
JsonObject json = new JsonObject();
json.addProperty("result", result);
String jsonData = json.toString();
out.print(jsonData);

%>