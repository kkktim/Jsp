<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.sql.Timestamp"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"
session="true"
buffer="8kb"
autoFlush="true"
isThreadSafe="true"
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8"/>
	<title>page 디렉티브 예제2</title>
</head>
<body>
	<h2>page 디렉티브 예제2</h2>
	<%
	Timestamp now = new Timestamp(System.currentTimeMillis());
	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	String strDate = format.format(now);
	%>
	
	<p>오늘은 <%= strDate %> 입니다.</p>
</body>
</html>