<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%
//현재 사용자 세션 해제
session.invalidate();

//리다이렉트
response.sendRedirect("/Farmstory1?success=101");
%>
