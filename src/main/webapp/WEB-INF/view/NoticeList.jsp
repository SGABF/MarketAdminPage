<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<fmt:requestEncoding value="UTF-8"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항</title>
<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
<script src="https://kit.fontawesome.com/5835e4ac0d.js"></script>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>
<script	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/comm.js"></script>
<script>
	$(function(){
		
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
	<table id="content">
		<tr>
			<td colspan="5" class="title">공지사항 - 목록보기</td>
		</tr>
		<tr>
			<td colspan="5" class="info">
				${pv.pageInfo }
				<script type="text/javascript">
					$(function(){
						$("#listCount").change(function(){
							var pageSize = $(this).val();
							SendPost("${pageContext.request.contextPath }/board/list", {"p":${cv.currentPage},"s":pageSize,"b":${cv.blockSize}});
						});	
					});
				</script>
				<select id="listCount">
					<option value="5" ${cv.pageSize==5 ? " selected='selected' " : "" }> 5개</option>
					<option value="10" ${cv.pageSize==10 ? " selected='selected' " : "" }>10개</option>
					<option value="20" ${cv.pageSize==20 ? " selected='selected' " : "" }>20개</option>
					<option value="30" ${cv.pageSize==30 ? " selected='selected' " : "" }>30개</option>
					<option value="40" ${cv.pageSize==50 ? " selected='selected' " : "" }>50개</option>
				</select>씩 보기	
			</td>
		</tr>
		<tr>
			<th>No</th>
			<th width="60%">제목</th>
			<th>작성자</th>
			<th>작성일</th>
			<th>첨부파일</th>
		</tr>
		<c:if test="${pv.totalCount==0 }">
			<tr>
				<td colspan="5" class="info2">등록된 글이 없습니다.</td>
			</tr>
		</c:if>
		<c:if test="${not empty pv.list }">
			<c:forEach var="vo" items="${pv.list }" varStatus="vs">
				<tr align="center">
					<td>${vo.idx }</td>
					<td align="left" >
						<a href="#" onclick='SendPost("${pageContext.request.contextPath }/board/view",{"p":${pv.currentPage },"s":${pv.pageSize },"b":${pv.blockSize },"idx":${vo.idx },"m":"view","h":"true"},"post")'><c:out value="${vo.subject }"></c:out></a>
						<%-- 오늘 저장한 글이면 new 아이콘을 달아보자  --%>		
						<jsp:useBean id="today" scope="request" class="java.util.Date"></jsp:useBean>				
						<fmt:formatDate value="${today }" pattern="yyyyMMdd" var="day"/> 
						<fmt:formatDate value="${vo.regDate }" pattern="yyyyMMdd" var="reg"/> 
						<c:if test="${day==reg }">
							  <span style="color:red;">New</span>
						</c:if>
					</td>
					<td>
						<c:out value="${vo.name }"></c:out>
					</td>
					<td>
						<fmt:formatDate value="${vo.regDate }" type="date" dateStyle="short"/>
					</td>
					<td>
						<%-- 첨부파일을 다운 받도록 링크를 달아준다. --%>
						<c:if test="${not empty vo.fileList }">
							<c:forEach var="fvo" items="${vo.fileList }">
								<c:url var="url" value="/board/download">
									<c:param name="of" value="${fvo.oriName }"></c:param>
									<c:param name="sf" value="${fvo.saveName }"></c:param>
								</c:url>
								<a href="${url }" title="${fvo.oriName }"><span class="material-icons">file_download</span></a>
							</c:forEach>
						</c:if>
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
			        onclick='SendPost("${pageContext.request.contextPath }/board/insertForm",{"p":${pv.currentPage },"s":${pv.pageSize },"b":${pv.blockSize }},"post")'>글쓰기</button>
			</td>
		</tr>
	</table>	
</body>
</html>