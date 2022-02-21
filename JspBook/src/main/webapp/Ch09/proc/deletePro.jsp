<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%
request.setCharacterEncoding("utf-8");
String id = request.getParameter("idt");
String passwd = request.getParameter("passwd");

Connection conn = null;
PreparedStatement psmt = null;
ResultSet rs = null;

try{
	String host = "jdbc:mysql://13.124.252.103:3306/rkdxogh1987";
	String user = "timk";
	String pass = "1234";
	
	Class.forName("com.mysql.jdbc.Driver");
	conn = DriverManager.getConnection(host, user, pass);
	
	String sql = "SELECT `id`, `passwd` FROM `B_Member` WHERE `id`=?";
	psmt = conn.prepareStatement(sql);
	psmt.setString(1, id);
	rs = psmt.executeQuery();
	
	if(rs.next()){
		String rld = rs.getString(1);
		String rPasswd = rs.getString(2);
		if(id.equals(rld) && passwd.equals(rPasswd)){
			sql = "DELETE FROM `B_Member` WHERE `id`=?;";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, id);
			psmt.executeUpdate();
			out.println("member 테이블의 레코드를 삭제 했습니다.");
		}else{
			out.println("패스워드가 틀렸습니다.");
		}
	}else{
		out.println("아이디가 틀렸습니다.");
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
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8"/>
	<title>p349</title>
</head>
<body>

</body>
</html>