<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<fmt:requestEncoding value="UTF-8"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>자료실</title>
<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
<script src="https://kit.fontawesome.com/5835e4ac0d.js"></script>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>
<script	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath }/static/js/comm.js"></script>
<script>
	$(function(){
		$("#listCount").change(function(){
			var pageSize = $(this).val();
			//alert(pageSize);
			SendPost("/qna/qnaList", {"p":${cv.currentPage},"s" : pageSize ,"b":${cv.blockSize}});
		});	
	});		
</script>
<style type="text/css">
	* { font-size: 10pt; }
	table#content{width: 90%; margin: auto;}
	th {border: 1px solid gray; background-color: silver;padding: 5px; text-align: center;}
	td {border: 1px solid gray; padding: 5px;}
	td.title {border:none; padding: 5px; text-align: center; font-size: 18pt;}
	td.info {border:none; padding: 5px; text-align: right; }
	td.info2 {border: 1px solid gray; padding: 5px; text-align: center; }
</style>
</head>
<body>
	<div>
		 p : ${cv.p}, s: ${cv.s } ,b: ${cv.b },<br />currentPage: ${cv.currentPage },
		pageSize: ${cv.pageSize },
		blockSize: ${cv.blockSize }
	</div>
	<table id="content">
		<tr>
			<td colspan="5" class="title">Q&A 게시판 </td>
		</tr>
		<tr>
			<td colspan="5" class="info">
				${pv.pageInfo }

				<select id="listCount">
					<option value="5" ${cv.pageSize==5 ? " selected='selected' " : "" }> 5개</option>
					<option value="10" ${cv.pageSize==10 ? " selected='selected' " : "" }>10개</option>
					<option value="20" ${cv.pageSize==20 ? " selected='selected' " : "" }>20개</option>
					<option value="30" ${cv.pageSize==30 ? " selected='selected' " : "" }>30개</option>
					<option value="40" ${cv.pageSize==40 ? " selected='selected' " : "" }>40개</option>
				</select>씩 보기	
			</td>
		</tr>
		<tr>
			<th width="5%">No</th>
			<th width="5%">이름</th>
			<th width="40%">제목</th>
			<th width="10%">작성일</th>
			<th width="5%">답변완료</th>
		</tr>
		<c:if test="${pv.totalCount==0 }">
			<tr>
				<td colspan="5" class="info2">등록된 글이 없습니다.</td>
			</tr>
		</c:if>
		<c:if test="${not empty pv.list }">
			<c:forEach var="vo" items="${pv.list }" varStatus="vs">
				<tr align="center">
					<td>${vo.back_Qna_Idx }</td>
					<td>
						<c:forEach var="nme" items="${pv.namelist }" varStatus="vs">
							<c:if test="${nme.back_qna_idx == vo.back_Qna_Idx }">
								${nme.user_name }
							</c:if>
						</c:forEach>
					</td>
					<td align="left" >
						<a href="#" onclick='SendPost("${pageContext.request.contextPath }/qna/qnaView" ,{"p":${pv.currentPage },"s":${pv.pageSize },"b":${pv.blockSize },"idx":${vo.back_Qna_Idx },"m":"view","h":"true"},"post")'><c:out value="${vo.back_Qna_Name }"></c:out></a>
					</td>
					<td>
						<fmt:formatDate value="${vo.back_Qna_RegDate }" type="date" dateStyle="short"/>
					</td>
					<td>
					</td>
				</tr>		
			</c:forEach>
			<tr>
				<td style="border: none;text-align: center;" colspan="5">
					${pv.pageList }
				</td>
			</tr>
		</c:if>
		<tr>
			<td class="info" colspan="5">
				<button type="button" class="btn btn-outline-success btn-sm" 
			        onclick='SendPost("${pageContext.request.contextPath }/qna/qnaInsertForm",{"p":${pv.currentPage },"s":${pv.pageSize },"b":${pv.blockSize }},"post")'>글쓰기</button>
			</td>
		</tr>
	</table>	
</body>
</html>