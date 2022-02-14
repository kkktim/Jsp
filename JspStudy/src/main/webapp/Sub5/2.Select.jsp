<%@page import="bean.User1Bean"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%

String host = "jdbc:mysql://13.124.252.103:3306/rkdxogh1987";
String user = "timk";
String pass = "1234";

List<User1Bean> users = new ArrayList();

try{
	//1단계
	Class.forName("com.mysql.jdbc.Driver");
	//2단계
	Connection conn = DriverManager.getConnection(host, user, pass);
	
	//3단계
	Statement stmt = conn.createStatement();
	
	//4단계
	String sql = "SELECT * FROM `User1`;";
	ResultSet rs = stmt.executeQuery(sql);
	
	//5단계
	
	
	while(rs.next()){
		/*
		String uid = rs.getString(1);
		String name = rs.getString(2);
		String hp = rs.getString(3);
		int age = rs.getInt(4);
		*/
		
		//자바빈 생성 및 데이터 저장
		User1Bean ub = new User1Bean();
		
		ub.setUid(rs.getString(1));
		ub.setName(rs.getString(2));
		ub.setHp(rs.getString(3));
		ub.setAge(rs.getInt(4));
		
		// 리스트 추가
		users.add(ub);
	}
	
	//6단계
	
	
}catch(Exception e){
	e.printStackTrace();	
}
%>



<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<title>2.Select</title>
</head>
<body>
	<h3>2.Select</h3>
	
	<h4>User1 목록</h4>
	
	<a href="./1.Insert.jsp">User1 등록하기</a>
	<table border="1">
		<tr>
			<td>아이디</td>
			<td>이름</td>
			<td>휴대폰</td>
			<td>나이</td>
			<td>관리</td>
		</tr>
		<% for(User1Bean ub : users){ %>
		<tr>
			<td><%= ub.getUid() %></td>
			<td><%= ub.getName() %></td>
			<td><%= ub.getHp() %></td>
			<td><%= ub.getAge() %></td>
			<td>
			<a href="./4.Delete.jsp?uid=<%= ub.getUid() %>">삭제</a>
			<a href="./3.Update.jsp?uid=<%= ub.getUid() %>">수정</a>
			</td>
		</tr>
		<%} %>
	</table>
</body>
</html>