<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8"/>
	<title>Insert title here</title>
</head>
<body>
	<%if(session.getAttribute("id") == null){   //세션이 설정되지 않은 경우)%>
	<form action="sessionTest.jsp" method="post">
		<div id="unauth">
		<dl>
			<dd>
				<label for="id">아이디</label>
				<input id="id" name="id" type="text" placeholder="kingdora" autofocus required>
			</dd>
			<dd>
				<label for="pass">비밀번호</label>
				<input id="pass" name="pass" type="password" placeholder="1234" required>
			</dd>
			<dd>
				<input type="submit" vlaue="로그인">
			</dd>
		</dl>
		</div>
	</form>
	<%} else { //세션이 설정된 경우 %>
	<form action="logout.jsp" method="post">
		<div id="auth">
			<dl>
				<dd>
					<%= session.getAttribute("id") %>님 오셨구려..
				</dd>
				<dd>
					<input type="submit" value="로그아웃">
				</dd>
			</dl>
		</div>
	</form>
	<%} %>
</body>
</html>