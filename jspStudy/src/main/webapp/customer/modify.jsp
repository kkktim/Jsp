<%@page import="bean.CustBean"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%
request.setCharacterEncoding("utf-8");
String custid = request.getParameter("custid");

String host = "jdbc:mysql://13.124.252.103:3306/rkdxogh1987";
String user = "timk";
String pass = "1234";

CustBean cb = new CustBean();

try{
	
	Class.forName("com.mysql.jdbc.Driver");
	Connection conn = DriverManager.getConnection(host, user, pass);
	Statement stmt = conn.createStatement();
	
	String sql = "SELECT * FROM `Customer` WHERE `custid`='"+custid+"';";
	ResultSet rs = stmt.executeQuery(sql);
	
	if(rs.next()){
		
		cb.setCustid(rs.getString(1));
		cb.setName(rs.getString(2));
		cb.setAddress(rs.getString(3));
		cb.setPhone(rs.getString(4));
		
	}
	
	conn.close();
	
}catch(Exception e){
	e.printStackTrace();
}


%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8"/>
	<title>modify</title>
</head>
<body>
	
	<h3>고객수정</h3>
	<a></a>
	<form action="./proc/modifyProc.jsp" method="post">
		<table border="1">
			<tr>
				<td>아이디</td>
				<td><input type="text" name="custid" value="<%=cb.getCustid() %>" readonly/></td>
			</tr>
			<tr>
				<td>이름</td>
				<td><input type="text" name="name" value="<%=cb.getName() %>"/></td>
			</tr>
			<tr>
				<td>주소</td>
				<td><input type="text" name="address" value="<%=cb.getAddress()%>"/></td>
			</tr>
			<tr>
				<td>휴대폰</td>
				<td><input type="text" name="phone" value="<%=cb.getPhone()%>"/></td>
			</tr>
			<tr>
				<td colspan="2" align="right"><input type="submit" value="수정" />
				</td>
			</tr>
		</table>
	</form>
</body>
</html>