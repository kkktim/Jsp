<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>

<%
//세션 해제
session.invalidate();
%>

<script>
//	alert('정상적으로 로그아웃을 했습니다');
//	document.location.href="/Board1/user/login.jsp"
</script>


<%
//로그인 페이지 이동
response.sendRedirect("/Board1/user/login.jsp?success=102");
%>