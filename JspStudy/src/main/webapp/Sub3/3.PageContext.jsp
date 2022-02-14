<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8"/>
	<title>3.PageContext</title>
	<%--
		날짜 : 2022/02/08
		이름 : 강태호
		내용 : JSP 내장객체 실습하기
	
	PageContext 내장객체
	 - JSP 개벼 페이지에 대한 객체
	 - 주요기능은 include, forward 기능을 제공
	 
	forward 기능
	 - 시스템에서 페이지를 요청하는 pageContext 기능
	 - 최초 요청에 대한 주소가 반영  cf) redirect는 최종 요청에 대한 주소가 반영 
	 
	 --%>
</head>
<body>
	<h3>3.PageContext</h3>
	
	<%
	pageContext.include("../inc/_Header.jsp");
	pageContext.include("../inc/_Footer.jsp");
	%>
	
	<a href="../proc/forwardProc.jsp">포워드 페이지 요청하기</a>
	
	
</body>
</html>