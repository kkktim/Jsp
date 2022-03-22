<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../_header.jsp" %>
<script>
	let reUid = /^[a-z]+[a-z0-9]{3,11}$/g;   //영문 소대문자 + 숫자 4~12자
	let rePass = /^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$/;   //영문 대소문자 + 특수문자 + 숫자 8자리 이상
	let reNick = /^[가-힣a-zA-Z]+$/; //한글+영문
	
	//최종 유효성 검사 변수
	let isUidOk = false;
	let isPassOk = false;
	let isNickOk = false;
	
	$(function () {
		$('input[name=uid]').keyup(function () {
			//console.log('keydown!')
			let value = $(this).val()
			if(value.length > 3){
				//alert(value)
				let jsonData = {"uid":value}
				console.log(jsonData)
				$.ajax({
					url: '/Farmstory2/user/checkUid.do',
					type: 'get',
					data: jsonData,   //서버로 전송하는 데이터
					dataType: 'json',  //서버로 부터 되돌려 받는 값
					success: function (data) {
						if(data.result == 1){
							$('.resultId').css('color', 'red').text('이미 사용중인 아이디 입니다.')
							isUidOk = false;
						}else{
							if(reUid.test(value)){
								$('.resultId').css('color', 'green').text('사용 가능한 아이디 입니다.')	
								isUidOk = true;
							}else{
								$('.resultId').css('color', 'red').text('아이디는 영문 소,대문자 숫자 조합 4글자 이상 12글자 이하 입니다.')
								isUidOk = false;
							}
						}
					}//success end... 	
				})//ajax end...
			}else{
				$('.resultId').css('color', 'red').text('아이디는 최소 4자 이상 이어야 합니다.')
			}
			
			
		})//checkUid end...
		
		//비밀번호 유효성검사
		$('input[name=pass1]').focusout(function () {
			let pw1 = $('input[name=pass1]').val()
			let pw2 = $('input[name=pass2]').val()
			
			if(rePass.test(pw1)){
				$('.resultPass1').text('')
				$('input[name=pass2]').focusout(function () {
					let pw1 = $('input[name=pass1]').val()
					let pw2 = $('input[name=pass2]').val()
					if(pw1 == pw2){
						$('.resultPass2').css('color', 'green').text('비밀번호가 일치 합니다.')
						isPassOk = true;
					}else{
						$('.resultPass2').css('color', 'red').text('비밀번호가 일치 하지 않습니다.')
						isPassOk = false;
					}
				})
			}else{
				$('.resultPass1').css('color', 'red').text('비밀번호는 영문 대소문자 특수문자 숫자 포함 8자리 이상 이어야 합니다.')
				$(this).focus()
			}
		})//비밀번호 유효성 검사 끝
		
		//닉네임 유효성 검사
		$('input[name=nick]').keyup(function () {
			let nick = $(this).val()
			let jsonData = {"nick":nick}
			//console.log(jsonData)
			$.ajax({
				url:'/Farmstory2/user/checkNick.do',
				type: 'get',
				data: jsonData,
				dataType: 'json',
				success: function (data) {
					//alert(data.result)
					if(reNick.test(nick)){
						if(data.result == 1){
							$('.resultNick').css('color', 'red').text('사용중인 별명 입니다.')
							isNickOk = false;
						}else{
							$('.resultNick').css('color', 'green').text('사용가능한 별명 입니다.')
							isNickOk = true;
						}	
					}else{
						$('.resultNick').css('color', 'red').text('공백 없이 한글 또는 영문 별명을 입력해주세요.')
						isNickOk = false;
					}
					
				}//success end...
				
			})//ajax end...
		})//닉네임 유효성 검사 끝
		
		$('.register > form').submit(function () {
			//form 이 전송 될 때 실행되는 이벤트
			if(!isUidOk){
				alert('아이디를 다시 확인하세요.')
				return false;
			}
			if(!isPassOk){
				alert('비밀번호를 다시 확인하세요.')
				return false;
			}
			if(!isNickOk){
				alert('별명을 다시 확인하세요.')
				return false;
			}
			
			return true;
		})
	})//function end...
	
	//전송 시 유효성 검사
	
</script>
        <section id="user" class="register">
            <form action="/Farmstory2/user/register.do" method="POST">
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
                            <span class="resultPass1"></span>                            
                        </td>
                    </tr>
                    <tr>
                        <td>비밀번호 확인</td>
                        <td>
                            <input type="password" name="pass2" placeholder="비밀번호 확인 입력"/>
                            <span class="resultPass2"></span>
                        </td>
                    </tr>
                </table>
                <table border="1">
                    <caption>개인정보 입력</caption>
                    <tr>
                        <td>이름</td>
                        <td>
                            <input type="text" name="name" placeholder="이름 입력"/>                            
                        </td>
                    </tr>
                    <tr>
                        <td>별명</td>
                        <td>
                            <p>공백없이 한글, 영문만 입력가능</p>
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
                                <input type="text" name="zip" placeholder="우편번호" readonly/>
                                <button class="btnZip">주소검색</button>
                            </div>                            
                            <div>
                                <input type="text" name="addr1" placeholder="주소를 검색하세요." readonly/>
                            </div>
                            <div>
                                <input type="text" name="addr2" placeholder="상세주소를 입력하세요."/>
                            </div>
                        </td>
                    </tr>
                </table>

                <div>
                    <a href="/Farmstory2/user/login.do" class="btnCancel">취소</a>
                    <input type="submit"   class="btnJoin" value="회원가입"/>
                </div>

            </form>
        </section>
<%@ include file="../_footer.jsp" %>