<%@page import="com.google.gson.Gson"%>
<%@page import="bean.MemberBean"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.Statement"%>
<%@ page contentType="application/json;charset=UTF-8" pageEncoding="UTF-8"%>
<%
//데이터베이스 작업(statement 사용)
String host = "jdbc:mysql://13.124.252.103:3306/rkdxogh1987";
String user = "timk";
String pass = "1234";

List<MemberBean> members = new ArrayList<>();

try{
	Class.forName("com.mysql.jdbc.Driver");
	Connection conn = DriverManager.getConnection(host, user, pass);
	Statement stmt = conn.createStatement();
	
	String sql = "SELECT * FROM `Member`";
	ResultSet rs = stmt.executeQuery(sql);
	
	while(rs.next()){
		
		MemberBean mb = new MemberBean();
		mb.setUid(rs.getString(1));
		mb.setName(rs.getString(2));
		mb.setHp(rs.getString(3));
		mb.setPos(rs.getString(4));
		mb.setDep(rs.getInt(5));
		mb.setRdate(rs.getString(6));
		
		members.add(mb);
		
	}
	
	conn.close();
	
}catch(Exception e){
	e.printStackTrace();
}

// json 데이터 출력
Gson gson = new Gson();
String jsonData = gson.toJson(members);
out.print(jsonData);
%>
