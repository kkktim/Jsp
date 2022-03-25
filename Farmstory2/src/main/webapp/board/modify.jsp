<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../_header.jsp" %>
<jsp:include page="./inc/_${cate}.jsp"/>
        <section id="board" class="modify">
            <h3>글수정</h3>
            <article>
                <form action="/Farmstory2/board/modify.do" method="post">
                    <table>
                    	<input type="hidden" name="no" value="${av.no}"/>
                    	<input type="hidden" name="cate" value="${cate}"/>
                    	<input type="hidden" name="type" value="${type}"/>
                        <tr>
                            <td>제목</td>
                            <td><input type="text" name="title" value="${av.title}"/></td>
                        </tr>
                        <tr>
                            <td>내용</td>
                            <td>
                                <textarea name="content">${av.content}</textarea>                                
                            </td>
                        </tr>
                        <tr>
                            <td>첨부</td>
                            <td><input type="file" name="file"/></td>
                        </tr>
                    </table>
                    <div>
                        <a href="/Farmstory2/board/view.do?cate=${cate}&type=${type}" class="btnCancel">취소</a>
                        <input type="submit"  class="btnWrite" value="수정완료">
                    </div>
                </form>
            </article>
        </section>

<!-- 내용 끝 -->
</article>
</section>
</div>
<%@ include file="../_footer.jsp" %>