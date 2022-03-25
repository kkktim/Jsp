<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../_header.jsp" %>
<jsp:include page="./inc/_${cate}.jsp"/>
<script>
	$(function () {
		$('.btnDelete').click(function () {
			let result = confirm('정말 삭제 하시겠습니까?')
			if(result){
				return true;
			}else{
				return false;
			}
		})
	})
</script>
        <section id="board" class="view">
            <h3>글보기</h3>
            <table>
                <tr>
                    <td>제목</td>
                    <td><input type="text" name="title" value="${article.title}" readonly/></td>
                </tr>
                <c:if test="${article.file > 0}">
                <tr>
                    <td>첨부파일</td>
                    <td>
                        <a href="#">${article.fv.oName}</a>
                        <span>${article.fv.download}회 다운로드</span>
                    </td>
                </tr>
                </c:if>
                <tr>
                    <td>내용</td>
                    <td>
                        <textarea name="content" readonly>${article.content}</textarea>
                    </td>
                </tr>
            </table>
            <div>
            <c:if test="${sessUser.uid eq article.uid}">
                <a href="/Farmstory2/board/delete.do?no=${article.no}&cate=${cate}&type=${type}" class="btnDelete">삭제</a>
                <a href="/Farmstory2/board/modify.do?cate=${cate}&type=${type}&no=${article.no}" class="btnModify">수정</a>
			</c:if>
                <a href="/Farmstory2/board/list.do?cate=${cate}&type=${type}" class="btnList">목록</a>
            </div>  
            
            <!-- 댓글리스트 -->
            <section class="commentList">
                <h3>댓글목록</h3>
                <article class="comment">
                    <span>
                        <span>길동이</span>
                        <span>20-05-13</span>
                    </span>
                    <textarea name="comment" readonly>댓글 샘플입니다.</textarea>
                    <div>
                        <a href="#">삭제</a>
                        <a href="#">수정</a>
                    </div>
                </article>
                <p class="empty">
                    등록된 댓글이 없습니다.
                </p>
            </section>

            <!-- 댓글입력폼 -->
            <section class="commentForm">
                <h3>댓글쓰기</h3>
                <form action="#">
                    <textarea name="comment"></textarea>
                    <div>
                        <a href="#" class="btnCancel">취소</a>
                        <input type="submit" class="btnWrite" value="작성완료"/>
                    </div>
                </form>
            </section>

        </section>

<!-- 내용 끝 -->
</article>
</section>
</div>
<%@ include file="../_footer.jsp" %>