<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8"/>
	<title>학생목록</title>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	<script>
		$(function () {
			$('button').click(function () {
				$.get('./proc/getList.jsp', function (data) {
					for(let stu of data){
						$('table').append('<tr/>')
						$('table tr:last-child').append('<td>'+stu.sid+'</td>')
						$('table tr:last-child').append('<td>'+stu.name+'</td>')
						$('table tr:last-child').append('<td>'+stu.gender+'</td>')
						$('table tr:last-child').append('<td>'+stu.hp+'</td>')
						$('table tr:last-child').append('<td>'+stu.grade+'</td>')
						$('table tr:last-child').append('<td>'+stu.regdate+'</td>')
					}
				})
			})
		})
	</script>
</head>
<body>
	<h3>학생목록</h3>
	<button>학생 목록 불러오기</button>
	<a href="./register.jsp">학생등록</a>
	<table border="1">
		<tr>
			<td>학번</td>
			<td>이름</td>
			<td>성별</td>
			<td>휴대폰</td>
			<td>학년</td>
			<td>등록일</td>
		</tr>
	</table>
</body>
</html>