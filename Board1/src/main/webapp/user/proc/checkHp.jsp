<%@page import="com.google.gson.JsonObject"%>
<%@page import="com.google.gson.Gson"%>
<%@page import="kr.co.board1.dao.UserDao"%>
<%@page import="kr.co.board1.dao.ArticleDao"%>
<%@ page contentType="application/json;charset=UTF-8" pageEncoding="UTF-8"%>
<%
request.setCharacterEncoding("utf-8");
String hp = request.getParameter("hp");

//전화번호 여부확인
int count = UserDao.getInstance().selectUserCount(hp, 4);

//결과값 리턴
JsonObject json = new JsonObject();
json.addProperty("result", count);
out.print(json);
%>