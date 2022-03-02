/**
 *  Validation 수행 (유효성 검증)
 */
 //데이터 형식을 검사하기 위한 정규표현식
 
 
 // 최종 유효성 검사에 사용될 변수
 
 $(function(){
	//아이디 중복 검사
	$('input[name=uid]').focusout(function(){
		let uid = $(this).val()
		let jsonData = {"uid":uid}
		
	})//아이디 끝
})//function 끝