<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8"/>
	<title>modify</title>
</head>
<body>
	<h3>직원수정</h3>
	<a href="/JspStudy/Sub8/member/list.do">직원목록</a>
	<form action="/JspStudy/Sub8/member/modify.do" method="post">
		<table border="1">
			<tr>
				<td>아이디</td>
				<td><input type="text" name="uid" readonly value="${mv.uid }"/></td>
			</tr>
			<tr>
				<td>이름</td>
				<td><input type="text" name="name" value="${mv.name }"/></td>
			</tr>
			<tr>
				<td>휴대폰</td>
				<td><input type="text" name="hp" value="${mv.hp }"/></td>
			</tr>
			<tr>
				<td>직급</td>
				<td>
					<select name="pos">
						<option value="사원">사원</option>
						<option value="대리">대리</option>
						<option value="과장">과장</option>
						<option value="차장">차장</option>
						<option value="부장">부장</option>
					</select>
				</td>
			</tr>
			<tr>
				<td>부서</td>
				<td>
					<select name="dep">
						<option value="101">영업1부</option>
						<option value="102">영업2부</option>
						<option value="103">영업3부</option>
						<option value="104">영업4부</option>
						<option value="105">인사부</option>
					</select>
				</td>
			</tr>
			<tr>
				<td colspan="2" align="right">
					<input type="submit" value="수정"/>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>