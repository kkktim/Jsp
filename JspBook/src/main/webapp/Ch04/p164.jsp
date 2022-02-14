<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page info = "page 내장객체 사용 예제" %>

<% String info = this.getServletInfo(); %>
<h2>page 내장객체 예제</h2>
page 디렉티브의 info 속성값 "<%=info %>" 을 얻어낸다.