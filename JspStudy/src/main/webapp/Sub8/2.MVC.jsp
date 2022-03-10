<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<!-- 
		날짜 : 2022/03/08
		이름 : 강태호
		내용 : MVC(Model2) 실습하기
		
	MVC(Model2)
	 - MVC는 클라이언트의 요청, 응답, 비지니스 로직(처리 로직(DB관련)) 처리를 모듈화한 구조
	 - Controller : 사용자의 요청을 받아 모델(서비스)을 실행, 서비스로 부터 뷰를 받아서 포워드
	 - Model : 컨트롤러의 요청을 받아 비지니스 로직 처리를 수행하는 모듈, Service와 Dao로 분리
	 - View : 사용자에게 보여줄 화면(HTML), 모델에서 처리한 데이터를 보여주는 모듈
	 -->
	<meta charset="UTF-8"/>
	<title>2.MVC</title>
</head>
<body>
	<h3>2.MVC(Model2) 실습하기</h3>
	<a href="/JspStudy/Sub8/MVC.do"></a>
	<a href="/JspStudy/mvc/hello.do">hello.do</a>
	<a href="/JspStudy/mvc/welcome.do"></a>
	<a href="/JspStudy/mvc/greeting.do"></a>
</body>
</html>