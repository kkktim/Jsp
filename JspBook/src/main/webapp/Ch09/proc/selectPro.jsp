<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.Timestamp"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<title>p340</title>
</head>
<body>
	<table>
		<tr>
			<td>아이디</td>
			<td>비밀번호</td>
			<td>이름</td>
			<td>가입일자</td>
			<td>주소</td>
			<td>전화번호</td>
		</tr>
	</table>
</body>
</html>
<%
Connection conn = null;
PreparedStatement psmt = null;
ResultSet rs = null;
try{
	String host = "jdbc:mysql://13.124.252.103:3306/rkdxogh1987";
	String user = "timk";
	String pass = "1234";
	Class.forName("com.mysql.jdbc.Driver");
	conn = DriverManager.getConnection(host, user, pass);
	
	String sql = "SELECT * FROM `B_Member`;";
	psmt = conn.prepareStatement(sql);
	rs = psmt.executeQuery();
	while(rs.next()){
		String id = rs.getString("id");
		String passwd = rs.getString("passwd");
		String name = rs.getString("name");
		Timestamp register = rs.getTimestamp("reg_date");
		String address = rs.getString("address");
		String tel = rs.getString("tel");
%>
	<tr>
		<td><%=id %></td>
		<td><%=passwd %></td>
		<td><%=name %></td>
		<td><%=register.toString() %></td>
		<td><%=address %></td>
		<td><%=tel %></td>
	</tr>	
<%	
	}
}catch(Exception e){
	e.printStackTrace();
}finally{
	if(rs != null)
		try{rs.close();}catch(SQLException sqle){}
	if(psmt != null)
		try{psmt.close();}catch(SQLException sqle){}
	if(conn != null)
		try{conn.close();}catch(SQLException sqle){}
	
}
%>
