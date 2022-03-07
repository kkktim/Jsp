<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<!-- 
		날짜 : 2022/03/07
		이름 : 강태호
		내용 : JSP 표현언어 EL
	 -->
	<meta charset="UTF-8"/>
	<title>1.EL</title>
</head>
<body>
	<h3>1.표현언어(Express Language)</h3>
	<%
		String name = "홍길동";
		int num1 = 1;
		double num2 = 2.13;
		
		//표현언어로 출력을 위해 내장객체로 저장
		pageContext.setAttribute("name", name);
		request.setAttribute("num1", num1);
		session.setAttribute("num2", num2);
	%>
	<h4>표현식</h4>
	<p>
		name : <%=name %><br/>
		num1 : <%=num1%><br/>
		num2 : <%=num2 %><br/>
		num1 + num2 : <%=num1+num2 %><br/>
	</p>
	
	<h4>표현언어</h4>
	<p>
		name : ${name}<br/>
		num1 : ${num1}<br/>
		num2 : ${num2}<br/>
		num1+num2 : ${num1+num2}<br/>
	</p>
	
</body>
</html>