<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="bean.EmpBean"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%
//데이터베이스 접속 후 데이터 가져오기
String host = "jdbc:mysql://13.124.252.103:3306/rkdxogh1987";
String user = "timk";
String pass = "1234";
List<EmpBean> workers = new ArrayList<>();
try{
	//1단계
	Class.forName("com.mysql.jdbc.Driver");
	//2단계
	Connection conn = DriverManager.getConnection(host, user, pass);
	//3단계
	Statement stmt = conn.createStatement();
	//4단계
	String sql = "SELECT * FROM `Employee`;";
	ResultSet rs = stmt.executeQuery(sql);
	//5단계
	while(rs.next()){
			EmpBean eb = new EmpBean();
			eb.setUid(rs.getString(1));
			eb.setName(rs.getString(2));
			eb.setGender(rs.getInt(3));
			eb.setHp(rs.getString(4));
			eb.setEmail(rs.getString(5));
			eb.setPos(rs.getString(6));
			eb.setDep(rs.getInt(7));
			eb.setRadte(rs.getString(8));
			workers.add(eb);
		}
	//6단계
	conn.close();
}catch(Exception e){
	e.printStackTrace();
}
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8"/>
	<title>Employee 목록</title>
</head>
<body>
	<a href="./register.jsp">직원등록</a>
	<table border="1">
		<tr>
			<td>아이디</td>
			<td>이름</td>
			<td>성별</td>
			<td>휴대폰</td>
			<td>이메일</td>
			<td>직급</td>
			<td>부서</td>
			<td>입사일</td>
		</tr>
		<%for(EmpBean eb : workers){ %>
		<tr>
			<td><%=eb.getUid() %></td>
			<td><%=eb.getName() %></td>
			<td>
				<%
				if(eb.getGender() == 1){
					out.println("남");
				}else{
					out.println("여");
				}
				%>
			</td>
			<td><%=eb.getHp() %></td>
			<td><%=eb.getEmail() %></td>
			<td><%=eb.getPos() %></td>
			<td>
				<%
				if(eb.getDep() == 101){
					out.println("영업1부");
				}else if(eb.getDep() == 102){
					out.println("영업2부");
				}else if(eb.getDep() == 103){
					out.println("영업3부");
				}else if(eb.getDep() == 104){
					out.println("영업4부");
				}else{
					out.println("인사부");
				}
				%>
			</td>
			<td><%=eb.getRadte().substring(0, 10) %></td>
		</tr>
		<%} %>
	</table>
</body>
</html>