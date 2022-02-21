<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.Timestamp"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%

request.setCharacterEncoding("utf-8");
String idt = request.getParameter("idt");
String passwd = request.getParameter("passwd");
String name = request.getParameter("name");
String addr = request.getParameter("addr");
String tel = request.getParameter("tel");

String host = "jdbc:mysql://13.124.252.103:3306/rkdxogh1987";
String user = "timk";
String pass = "1234";

Connection conn = null;
PreparedStatement psmt = null;
try{
	Class.forName("com.mysql.jdbc.Driver");
	conn = DriverManager.getConnection(host, user, pass);
	
	String sql = "INSERT INTO `B_Member` VALUES (?, ?, ?, NOW(), ?, ?);";
	psmt = conn.prepareStatement(sql);
	psmt.setString(1, idt);
	psmt.setString(2, passwd);
	psmt.setString(3, name);
	psmt.setString(4, addr);
	psmt.setString(5, tel);
	psmt.executeUpdate();
	
	out.println("member 테이블에 새로운 레코드를 추가 했습니다.");
	
}catch(Exception e){
	e.printStackTrace();
	out.println("member 테이블에 새로운 레코드를 추가하는데 실패했습니다.");
}finally{
	if(psmt != null)
		try{psmt.close();}catch(SQLException sqle){}
	if(conn != null)
		try{conn.close();}catch(SQLException sqle){}
}

%>