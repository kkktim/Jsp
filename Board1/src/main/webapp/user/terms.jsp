<%@page import="kr.co.board1.bean.TermsBean"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%
//약관내용 가져오기
String host = "jdbc:mysql://13.124.252.103:3306/rkdxogh1987";
String user = "timk";
String pass = "1234";

TermsBean tb = null;   //선언

try{
	Class.forName("com.mysql.jdbc.Driver");
	Connection conn = DriverManager.getConnection(host, user, pass);
	Statement stmt = conn.createStatement();
	ResultSet rs = stmt.executeQuery("SELECT * FROM `Board_terms`;");
	
	if(rs.next()){
		tb = new TermsBean();   //생성
		tb.setTerms(rs.getString(1));
		tb.setPrivacy(rs.getString(2));
	}
	conn.close();
	
}catch(Exception e){
	e.printStackTrace();
}
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>약관</title>
    <link rel="stylesheet" href="/Board1/css/style.css"/>    
</head>
<body>
    <div id="wrapper">
        <section id="user" class="terms">
            <table>
                <caption>사이트 이용약관</caption>
                <tr>
                    <td>
                        <textarea readonly><%=tb.getTerms() %></textarea>
                        <p>
                            <label><input type="checkbox" name="chk1"/>동의합니다.</label>
                        </p>
                    </td>
                </tr>
            </table>
            <table>
                <caption>개인정보 취급방침</caption>
                <tr>
                    <td>
                        <textarea readonly><%=tb.getPrivacy() %></textarea>
                        <p>
                            <label><input type="checkbox" name="chk2"/>동의합니다.</label>
                        </p>
                    </td>
                </tr>
            </table>
            <div>
                <a href="/Board1/user/login.jsp">취소</a>
                <a href="/Board1/user/register.jsp">다음</a>
            </div>
        </section>
    </div>
    
</body>
</html>