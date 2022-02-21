<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8"/>
	<title>p335</title>
</head>
<body>
	<form action="./proc/insertPro.jsp" method="post">
		<table>
			<tr>
				<td class="label"><label for="idt">아이디</label>
				<td class="content"><input id="idt" name="idt"  type="text" size="20"
				maxlength="50" placeholder="example@kings.com" autofocus required>
			<tr>
				<td class="label"><label for="passwd">비밀번호</label>
				<td class="content"><input id="passwd" name="passwd"  type="password" 
				size="20" placeholder="6~16자 숫자/문자" maxlength="16" required>
			<tr>
				<td class="label"><label for="name">이름</label>
				<td class="content"><input id="name" name="name"  type="text" size="20" 
				placeholder="킹도라" maxlength="10" required>
			<tr>
				<td class="label"><label for="name">주소</label>
				<td class="content"><input id="addr" name="addr"  type="text" size="20" 
				placeholder="서울시" maxlength="100" required>
			<tr>
				<td class="label"><label for="name">전화번호</label>
				<td class="content"><input id="tel" name="tel"  type="text" size="20" 
				placeholder="010-1111-1111" maxlength="20" required>
			<tr>
				<td class="label2" colspan="2"><input type="submit" value="입력완료"> 
				<input type="reset" value="다시작성">
		</table>
	</form>
</body>
</html>