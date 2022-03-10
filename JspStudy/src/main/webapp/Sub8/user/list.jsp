<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<title>list</title>
</head>
<body>
	<h3>User목록</h3>
	<a href="/JspStudy/Sub8/user/register.do">User등록</a>
	<table border="1">
		<tr>
			<td>아이디</td>
			<td>이름</td>
			<td>휴대폰</td>
			<td>나이</td>
			<td>관리</td>
		</tr>
		<c:forEach var="user" items="${users }">
			<tr>
				<td>${user.getUid() }</td>
				<td>${user.name }</td>
				<td>${user.hp }</td>
				<td>${user.age }</td>
				<td>
				<a href="/JspStudy/Sub8/user/delete.do?uid=${user.uid }">삭제</a>
				<a href="/JspStudy/Sub8/user/modify.do?uid=${user.uid }">수정</a>
				</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>