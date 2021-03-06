<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>회원가입</title>
    <link rel="stylesheet" href="/Board2/css/style.css"/>
    <script src="/Board2/js/zipcode.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
    <script src="/Board2/js/validation.js"></script>
    <script>
	// 정규식 표현
    let reUid  = /^[a-z]+[a-z0-9]{3,9}$/;
    let rePass = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[@$!%*#?&])[A-Za-z\d@$!%*#?&]{5,}$/;
    
    // 최종 유효성 검사에 사용될 상태변수
    let isUidOk   = false;
	let isPassOk = false;
	
    $(function () {
    	// 아이디 중복체크
    	$('input[name=uid]').focusout(function(){
    			
    	let uid = $(this).val();
    	let jsonData = {"uid": uid};				
    			
    		$.ajax({
    			url: '/Board2/user/checkUid.do',
    			type: 'get',
    			data: jsonData,
    			dataType: 'json',
    			success: function(data){
    				if(data.result > 0){
    					$('.resultId').css('color', 'red').text('이미 사용중인 아이디 입니다.');
    				}else{
    					// 아이디 유효성 검사
    					if(reUid.test(uid)){
    						$('.resultId').css('color', 'green').text('사용 가능한 아이디 입니다.');
    					}else{
    						$('.resultId').css('color', 'red').text('아이디는 영문 소문자, 숫자 조합 4 ~ 10자까지 입니다.');
    					}
    				}
    			}
    		})//ajax end...
    	})//focusout end...
    	
    	$('input[name=pass2]').focusout(function () {
			let pw1 = $('input[name=pass1]').val()
			let pw2 = $('input[name=pass2]').val()
			
			if(pw1 == pw2){
				if(rePass.test(pw2)){
					$('.resultPass').css('color', 'green').text('비밀번호가 일치 합니다.')
					isPassOk = true;
				}else{
					$('.resultPass').css('color', 'red').text('비밀번호는 영문, 숫자, 특수문자 포함하여 최소 4자 이상이어야 합니다.')
					isPassOk = false;
				}
			}else{
				$('.resultPass').css('color', 'red').text('비밀번호가 일치하지 않습니다.')
				isPassOk = false;
			}
		})
    	
	})//function end...
    </script>
</head>
<body>
    <div id="wrapper">
        <section id="user" class="register">
            <form action="/Board2/user/register.do" method="POST">
                <table border="1">
                    <caption>사이트 이용정보 입력</caption>
                    <tr>
                        <td>아이디</td>
                        <td>
                            <input type="text" name="uid" placeholder="아이디 입력"/>
                            <span class="resultId"></span>
                        </td>
                    </tr>
                    <tr>
                        <td>비밀번호</td>
                        <td>
                            <input type="password" name="pass1" placeholder="비밀번호 입력"/>                            
                        </td>
                    </tr>
                    <tr>
                        <td>비밀번호 확인</td>
                        <td>
                            <input type="password" name="pass2" placeholder="비밀번호 확인 입력"/>
                            <span class="resultPass"></span>
                        </td>
                    </tr>
                </table>
                <table border="1">
                    <caption>개인정보 입력</caption>
                    <tr>
                        <td>이름</td>
                        <td>
                            <input type="text" name="name" placeholder="이름 입력"/>   
                            <span class="resultName"></span>                         
                        </td>
                    </tr>
                    <tr>
                        <td>별명</td>
                        <td>
                            <p>공백없이 한글, 영문, 숫자만 입력가능</p>
                            <input type="text" name="nick" placeholder="별명 입력"/>
                            <span class="resultNick"></span>                            
                        </td>
                    </tr>
                    <tr>
                        <td>E-Mail</td>
                        <td>
                            <input type="email" name="email" placeholder="이메일 입력"/>
                        </td>
                    </tr>
                    <tr>
                        <td>휴대폰</td>
                        <td>
                            <input type="text" name="hp" placeholder="- 포함 13자리 입력" minlength="13" maxlength="13" />
                        </td>
                    </tr>
                    <tr>
                        <td>주소</td>
                        <td>
                            <div>
                                <input type="text" id="zip" name="zip" placeholder="우편번호" readonly/>
                                <button type="button" class="btnZip" onclick="zipcode()">주소검색</button>
                            </div>                            
                            <div>
                                <input type="text" id="addr1" name="addr1" placeholder="주소를 검색하세요." readonly/>
                            </div>
                            <div>
                                <input type="text" id="addr2" name="addr2" placeholder="상세주소를 입력하세요."/>
                            </div>
                        </td>
                    </tr>
                </table>

                <div>
                    <a href="/Board2/user/login.do" class="btnCancel">취소</a>
                    <input type="submit"   class="btnJoin" value="회원가입"/>
                </div>

            </form>
        </section>
    </div>    
</body>
</html>