<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8"/>
	<title>modify</title>
</head>
<body>
	
	<h3>고객수정</h3>
	<a href="./list.jsp">고객목록</a>
	<form action="./proc/modifyProc.jsp" method="post">
		<table border="1">
			<tr>
				<td>아이디</td>
				<td><input type="text" name="custid" readonly value=""/></td>
			</tr>
			<tr>
				<td>이름</td>
				<td><input type="text" name="name" value=""/></td>
			</tr>
			<tr>
				<td>주소</td>
				<td><input type="text" name="address" value=""/></td>
			</tr>
			<tr>
				<td>휴대폰</td>
				<td><input type="text" name="phone" value=""/></td>
			</tr>
			<tr>
				<td colspan="2" align="right"><input type="submit" value="수정" />
				</td>
			</tr>
		</table>
	</form>
</body>
</html>