<%@page import="bean.CustBean"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%

String host = "jdbc:mysql://13.124.252.103:3306/rkdxogh1987";
String user = "timk";
String pass = "1234";

List<CustBean> CusMembers = new ArrayList();

try{
	Class.forName("com.mysql.jdbc.Driver");
	Connection conn = DriverManager.getConnection(host, user, pass);
	Statement stmt = conn.createStatement();
	
	String sql = "SELECT * FROM `Customer`;";
	ResultSet rs = stmt.executeQuery(sql);
	
	while(rs.next()){
		CustBean cb = new CustBean();
		
		cb.setCustid(rs.getInt(1));
		cb.setName(rs.getString(2));
		cb.setAddress(rs.getString(3));
		cb.setPhone(rs.getString(4));
		
		CusMembers.add(cb);
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
	<h3>고객목록</h3>
	<a href="./register.jsp">고객등록</a>
	<table border="1">
		<tr>
			<td>아이디</td>
			<td>이름</td>
			<td>주소</td>
			<td>휴대폰</td>
			<td>관리</td>
		</tr>
		<%for(CustBean cb : CusMembers){ %>
		<tr>
			<td><%=cb.getCustid() %></td>
			<td><%=cb.getName() %></td>
			<td><%=cb.getAddress() %></td>
			<td><%=cb.getPhone() %></td>
			<td>
				<a href="./modify.jsp?custid=<%=cb.getCustid()%>">수정</a>
				<a href="./delete.jsp?custid=<%=cb.getCustid()%>">삭제</a>
			</td>
		</tr>
		<%} %>
	</table>
</body>
</html>