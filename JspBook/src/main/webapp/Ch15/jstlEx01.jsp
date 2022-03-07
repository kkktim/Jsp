<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8"/>
	<title>Insert title here</title>
</head>
<body>
	<h3>JSTL core 태그 예제 - set, out, remove</h3>
	<p>browser 변수값 설정
	<c:set var="browser" value="${header[User-agent] }"/><br>
	<c:out value="${browser }"/></p>
	
	<p>browser 변수값 제거 후
	<c:remove var="browser"/>
	<c:out value="browser"/>
</body>
</html>