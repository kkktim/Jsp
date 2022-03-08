<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8"/>
	<title>JSTL core 태그예제 - forTokens</title>
</head>
<body>
	<c:forTokens var="tech" items="금강불괴,허공답보,열양기공,천마군림보" delims=",">
		<p>익혀야할 기술 : <c:out value="${tech }"/>
	</c:forTokens>
</body>
</html>