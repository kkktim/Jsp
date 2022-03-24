<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../_header.jsp" %>
<script>
	let success = ${success}
	if(success == 103){
		alert('로그인 후 이용 바랍니다.')
	}
</script>
<script>
var naverLogin = new naver.LoginWithNaverId(
		{
			clientId: "", //내 애플리케이션 정보에 cliendId를 입력해줍니다.
			callbackUrl: "http://localhost:8181/naverLogin", // 내 애플리케이션 API설정의 Callback URL 을 입력해줍니다.
			isPopup: false,
			callbackHandle: true
		}
	);	

naverLogin.init();

window.addEventListener('load', function () {
	naverLogin.getLoginStatus(function (status) {
		if (status) {
			var email = naverLogin.user.getEmail(); // 필수로 설정할것을 받아와 아래처럼 조건문을 줍니다.
    		
			console.log(naverLogin.user); 
    		
            if( email == undefined || email == null) {
				alert("이메일은 필수정보입니다. 정보제공을 동의해주세요.");
				naverLogin.reprompt();
				return;
			}
		} else {
			console.log("callback 처리에 실패하였습니다.");
		}
	});
});


var testPopUp;
function openPopUp() {
    testPopUp= window.open("https://nid.naver.com/nidlogin.logout", "_blank", "toolbar=yes,scrollbars=yes,resizable=yes,width=1,height=1");
}
function closePopUp(){
    testPopUp.close();
}

function naverLogout() {
	openPopUp();
	setTimeout(function() {
		closePopUp();
		}, 1000);
	
	
}

</script>

<section id="user" class="login">
	<form action="/Farmstory2/user/login.do" method="post">
    	<table border="0">
        	<tr>
            	<td><img src="../img/login_ico_id.png" alt="아이디"/></td>
                <td><input type="text" name="uid" placeholder="아이디를 입력" /></td>
			</tr>
            <tr>
            	<td><img src="../img/login_ico_pw.png" alt="비밀번호"/></td>
                <td><input type="password" name="pass" placeholder="비밀번호 입력" /></td>
			</tr>
		</table>
        <input type="submit" class="btnLogin" value="로그인"/>
	</form>

    <div class="info">
    	<h3>회원로그인 안내</h3>
        <p>
        	아직 회원이 아니시면 회원으로 가입하세요.
		</p>
        <a href="/Farmstory2/user/terms.do">회원가입</a>
        <ul>
		<li>
      		<!-- 아래와같이 아이디를 꼭 써준다. -->
      		<a id="naverIdLogin_loginButton" href="javascript:void(0)">
      			<img alt="네이버 로그인" src="/Farmstory2/img/naver-icon-style.png"></a>
      			<span>네이버 로그인</span>
		</li>
		<li onclick="naverLogout(); return false;">
      		<a href="javascript:void(0)">
          		<span>네이버 로그아웃</span>
      		</a>
		</li>
	</ul>
	</div>
</section>
<%@ include file="../_footer.jsp" %>