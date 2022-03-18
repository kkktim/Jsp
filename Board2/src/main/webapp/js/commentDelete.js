/**
 * 
 */
 //댓글 삭제 수정
$(function () {
	//동적 이벤트 구현
	$(document).on('click', '.comment > div > .del', function (e) {
		e.preventDefault();
						
		let parentArticle = $(this).parent().parent() 
						
		let result = confirm('정말 삭제 하시겠습니까?')
		if(!result){
			return;
		}
		
		let no = $(this).attr('data-no')
		let parent = $(this).attr('data-value')
		
		let jsonData = {"no":no, "parent":parent}
		let url = "/Board2/commentDelete.do"
		console.log(jsonData)
		
		$.get(url, jsonData, function (data) {
			if(data.result == 1){
				alert('삭제 되었습니다.')
				//화면 동적 삭제(랜더링)
				parentArticle.remove()
			}
		}, 'json')
	})//동적 이벤트 구현 끝
});