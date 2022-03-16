<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>글보기</title>
    <link rel="stylesheet" href="/Board2/css/style.css"/>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    
</head>
<body>
    <div id="wrapper">
        <section id="board" class="view">
            <h3>글보기</h3>
            <table>
                <tr>
                    <td>제목</td>
                    <td><input type="text" name="title" value="${av.title }" readonly/></td>
                </tr>
                <c:if test="${av.file > 0 }">
                <tr>
                    <td>첨부파일</td>
                    <td>
                        <a href="#">${av.fv.oName }</a>
                        <span>${av.fv.download }회 다운로드</span>
                    </td>
                </tr>
                </c:if>
                <tr>
                    <td>내용</td>
                    <td>
                        <textarea name="content" readonly>${av.content }</textarea>
                    </td>
                </tr>
            </table>
            <div>
                <a href="/Board2/delete.do" class="btnDelete">삭제</a>
                <a href="/Board2/modify.do?no=${av.no }&uid=${av.uid}" class="btnModify">수정</a>
                <a href="/Board2/list.do" class="btnList">목록</a>
            </div>  
            
            <!-- 댓글리스트 -->
            <section class="commentList">
                <h3>댓글목록</h3>
                <c:forEach var="comment" items="${comments }">
                	<article class="comment">
                    	<span>
                        	<span>${comment.nick }</span>
                        	<span>${comment.rdate }</span>
                    	</span>
                    	<textarea name="comment" readonly>${comment.content }</textarea>
                    	<div>
                        	<a href="#">삭제</a>
                        	<a href="#">수정</a>
                    	</div>
                	</article>
                </c:forEach>
                <c:if test="${comments.size() == 0 }">
                	<p class="empty">
                    	등록된 댓글이 없습니다.
                	</p>
                </c:if>
            </section>
			<script>
				$(function () {
					$('.commentForm > form').submit(function () {
						let parent = $(this).children('input[name=parent]').val()
						let uid = $(this).children('input[name=uid]').val()
						let content = $(this).children('textarea[name=content]').val()
						
						let jsonData = {"parent":parent, "uid":uid, "content":content}
						
						$.ajax({
							url: '/Board2/comment.do',
							type: 'post',
							data: jsonData,
							dataType: 'json',
							success: function (data) {
								console.log(data)
								//화면 렌더링
								let tags = `<article class='comment'>
			                    				<span>
		                        					<span>닉네임</span>
		                        					<span>22-03-16</span>
		                    					</span>
		                    					<textarea name='comment' readonly>댓글내용</textarea>
		                    					<div>
		                        					<a href='#'>삭제</a>
		                        					<a href='#'>수정</a>
		                    					</div>
		                					</article>`;
		                			
		                		let dom = $(tags)
		                		dom.find('.comment > span > span:nth-child(1)').text(data.nick)
		                		dom.find('.comment > span > span:nth-child(2)').text(data.rdate)
		                		dom.find('.comment > textarea').text(data.content)
									
								$('.commentList').append(dom)
							}
						})						
						
						return false;
					})
				})
			</script>
            <!-- 댓글입력폼 -->
            <section class="commentForm">
                <h3>댓글쓰기</h3>
                <form action="#" method="post">
                	<input type="hidden" name="uid" value="${sessUser.uid }"/>
                	<input type="hidden" name="parent" value="${av.no }"/>
                    <textarea name="content"></textarea>
                    <div>
                        <a href="/Board/view.do" class="btnCancel">취소</a>
                        <input type="submit" class="btnWrite" value="작성완료"/>
                    </div>
                </form>
            </section>

        </section>
    </div>    
</body>
</html>