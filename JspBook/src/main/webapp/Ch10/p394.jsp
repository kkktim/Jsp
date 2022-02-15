<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%
Cookie cookie = new Cookie("id", "kingdora");
cookie.setMaxAge(60 * 2);
response.addCookie(cookie);

out.println("쿠키가 생성 되었습니다.");
%>

<form action="useCookie.jsp" method="post">
	<table>
		<tr>
			<td><input type="submit" value="생성된 쿠기 확인"></td>
		</tr>
	</table>
</form>
