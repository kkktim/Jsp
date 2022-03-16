/**
 * 
 */
 //정규표현식
let reName = /^[가-힣]{2,10}$/;
let reNick = /^[a-z가-힣0-9]{2,5}$/;

//최종 유효성 검사에 사용될 변수 
let isNameOk  = false;
let isNickOk  = false;

$(function(){
	//이름 유효성 검사
	$('input[name=name]').focusout(function(){
		let name = $(this).val()
		
		if(reName.test(name)){
			$('.resultName').text('')
			isNameOk = true;
		}else{
			$('.resultName').text('color', 'red').text('이름을 한글로 2자 이상 입력해주세요.')
			isNameOk = false;
		}
	})
	
	//닉네임 중복체크
	$('input[name=nick]').focusout(function(){
		let nick = $(this).val()
		let jsonData = {"nick":nick}
		console.log(jsonData)
		$.get('/Board2/user/checkNick.do', jsonData, function(data){
			
			if(data.result > 0){
				console.log(data.result)
				$('.resultNick').css('color', 'red').text('이미 사용중인 별명 입니다.')
				isNickOk = false;
			}else{
				//유효성 검사
				if(reNick.test(nick)){
					$('.resultNick').css('color', 'green').text('사용가능한 별명 입니다.')
					isNickOk = true;
				}else{
					$('.resultNick').css('color', 'red').text('별명은 영문 소문자, 숫자, 한글 조합 2 ~ 5까지 입니다.')
					isNickOk = false;
				}
			}
		}, 'json')
	})
})