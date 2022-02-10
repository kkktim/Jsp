<%@page import="bean.MemberBean"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.Statement"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
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



%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<title>list</title>
</head>
<body>
	<h3>직원목록</h3>
	<table border="1">
		<tr>
			<td>아이디</td>
			<td>이름</td>
			<td>휴대폰</td>
			<td>직급</td>
			<td>부서</td>
			<td>입사일</td>
			<td>관리</td>
		</tr>
		
		<% for(MemberBean mb : members){ %>
		<tr>
			<td><%= mb.getUid() %></td>
			<td><%= mb.getName() %></td>
			<td><%= mb.getHp() %></td>
			<td><%= mb.getPos() %></td>
			<td>
				<%
					switch(mb.getDep()){
					case 101:
						out.println("영업1부");
						break;
					case 102:
						out.println("영업2부");
						break;
					case 103:
						out.println("영업3부");
						break;
					case 104:
						out.println("영업4부");
						break;
					case 105:
						out.println("인사부");
						break;
					}
				
				
				%>
			</td>
			<td><%= mb.getRdate().substring(0, 10) %></td>
			<td>
				<a href="./modify.jsp?uid=<%= mb.getUid() %>">수정</a>
				<a href="./delete.jsp?uid=<%= mb.getUid() %>">삭제</a>
			</td>
		</tr>
		<%} %>
	</table>
</body>
</html>