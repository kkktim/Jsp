<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8"/>
	<title>Insert title here</title>
</head>
<body>
	<h2>response 객체 예제 - sendRedirect() 메서드의 사용</h2><%-- 출력버퍼에 추가 --%>
	현재 페이지는 <b>responseRedirect.jsp</b>페이지 입니다.<%-- 출력버퍼에추가 --%>
	<%response.sendRedirect("responseRedirected.jsp"); %><%-- 출력버퍼비움 --%>
	
</body>
</html>