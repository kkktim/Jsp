<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<title>list</title>
</head>
<body>
	<h3>직원목록</h3>
	<a href="./register.jsp">직원등록</a>
	<table border="1">
		<tr>
			<td>아이디</td>
			<td>이름</td>
			<td>휴대폰</td>
			<td>직급</td>
			<td>부서</td>
			<td>입사일</td>
			<td>관리</td>
		</tr>
		<c:forEach var="member" items="${members }">
		<tr>
			<td>${member.uid }</td>
			<td>${member.name }</td>
			<td>${member.hp }</td>
			<td>${member.pos }</td>
			<td>${member.dep }</td>
			<td>${member.rdate.substring(0, 10) }</td>
			<td>
				<a href="/JspStudy/Sub8/member/modify.do?uid=${member.uid }">수정</a>
				<a href="/JspStudy/Sub8/member/delete.do?uid=${member.uid }">삭제</a>
			</td>
		</tr>
		</c:forEach>
	</table>
</body>
</html>