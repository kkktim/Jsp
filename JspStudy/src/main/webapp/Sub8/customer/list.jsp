<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
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
		<tr>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td>
				<a href="./modify.jsp?custid=">수정</a>
				<a href="./delete.jsp?custid=">삭제</a>
			</td>
		</tr>
	</table>
</body>
</html>