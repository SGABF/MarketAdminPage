<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<fmt:requestEncoding value="UTF-8"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title> 질문 </title>
<!--  엑시콘사용 : 다운로드받은 폴더를 넣고 CSS파일을 읽는다. -->
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/axicon/axicon.min.css" />

<script src="https://code.jquery.com/jquery-3.5.1.min.js" ></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
<script	src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
<link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-bs4.min.css" rel="stylesheet">
<script	src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-bs4.min.js"></script>
<!-- CDN 한글화 -->
<script src=" https://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.18/lang/summernote-ko-KR.min.js"></script>
<script src="${pageContext.request.contextPath}/static/js/comm.js"></script>
<script>
	$(function(){
		$('#content').summernote(
				{
					lang : 'ko-KR', // default: 'en-US'
					height : 200, // set editor height
					minHeight : null, // set minimum height of editor
					maxHeight : null, // set maximum height of editor
					fontNames : [ '맑은고딕', 'Arial', 'Arial Black',
							'Comic Sans MS', 'Courier New', ],
					fontNamesIgnoreCheck : [ '맑은고딕' ],
					focus : true,
					callbacks : {
						onImageUpload : function(files, editor, welEditable) {
							for (var i = files.length - 1; i >= 0; i--) {
								sendFile(files[i], this);
							}
						}
					}
				});
		$('#content').summernote('disable');
		/*
		// 서머노트에 text 쓰기
		$('#summernote').summernote('insertText', 써머노트에 쓸 텍스트);
		// 서머노트 쓰기 비활성화
		$('#summernote').summernote('disable');
		// 서머노트 쓰기 활성화
		$('#summernote').summernote('enable');
		// 서머노트 리셋
		$('#summernote').summernote('reset');
		// 마지막으로 한 행동 취소 ( 뒤로가기 )
		$('#summernote').summernote('undo');
		// 앞으로가기
		$('#summernote').summernote('redo');
		*/
	});
	function sendFile(file, el) {
		var form_data = new FormData();
      	form_data.append('file', file);
      	$.ajax({
        	data: form_data,
        	type: "POST",
        	url: 'imageUpload.jsp',
        	cache: false,
        	contentType: false,
        	enctype: 'multipart/form-data',
        	processData: false,
        	success: function(img_name) {
          		$(el).summernote('editor.insertImage', img_name);
        	},
        	error : function(){
        		alert('에러!!!');
        	}
      	});
    }
	//-----------------------------------------------------------------------------------------------------------
	// 돌아가기버튼 클릭시 사용할 함수
	function goBack(){
		SendPost("${pageContext.request.contextPath}/qna/qnaList", {"p":${cv.currentPage},"s":${cv.pageSize},"b":${cv.blockSize}});
	}
	function goUpdate(){
		SendPost("${pageContext.request.contextPath}/qna/qnaUpdate", {"p":${cv.currentPage},"s":${cv.pageSize},"b":${cv.blockSize},"idx":${cv.idx}});
	}
	function goDelete(){
		SendPost("${pageContext.request.contextPath}/qna/qnaDelete", {"p":${cv.currentPage},"s":${cv.pageSize},"b":${cv.blockSize},"idx":${cv.idx}});
	}
</script>
<style type="text/css">
	* { font-size: 10pt; }
	table#main_content{width: 80%; margin: auto;}
	th {border: 1px solid gray; background-color: silver;padding: 5px; text-align: center;}
	td {border: 1px solid gray; padding: 5px;}
	td.title {border:none; padding: 5px; text-align: center; font-size: 18pt;}
	td.info {border:none; padding: 5px; text-align: right; }
	td.info2 {border: 1px solid gray; padding: 5px; text-align: center; }
	td.answer{width: 80%; margin: auto; border: none;}
	.fileItem { margin-bottom: 3px;}
</style>
</head>
<body>
	<table id="main_content">
		<tr>
			<td colspan="4" class="title" >
			질문 
			</td>
		</tr>
		<tr>
			<th>제목</th>
			<td colspan="3"> 
				<c:out value="${fv.back_Qna_Name }"></c:out>
			</td>
		</tr>
		<tr>
			<th valign="middle">내용</th>
			<td colspan="3"> 
				<div id="content">${fv.back_Qna_Content }</div>
			</td>
		</tr>
		<tr>
			<th valign="top">자료</th>
			<td colspan="3"> 
				<%-- 첨부파일을 다운 받도록 링크를 달아준다. --%>
				<c:if test="${not empty fv.fileList }">
					<c:forEach var="fvo" items="${fv.fileList }">
						<c:url var="url" value="/qna/qnaDownload">
							<c:param name="of" value="${fvo.back_Qnafile_OriName }"></c:param>
							<c:param name="sf" value="${fvo.back_Qnafile_SaveName }"></c:param>
						</c:url>
						<a href="${url }" title="${fvo.back_Qnafile_OriName }"><i class="axi axi-download2"></i> ${fvo.back_Qnafile_OriName }</a><br />
					</c:forEach>
				</c:if>
			</td>
		</tr>
		<tr>
			<td colspan="4" class="info">
				<input type="button" value=" 수정하기 " class="btn btn-outline-success btn-sm" onclick="goUpdate()"/>
				<input type="button" value=" 삭제가기 " class="btn btn-outline-success btn-sm" onclick="goDelete()"/>
				<input type="button" value=" 돌아가기 " class="btn btn-outline-success btn-sm" onclick="goBack()"/>
			</td>
		</tr>
		<tr>
			<td colspan="4" class="title" >
			답변
			</td>
		</tr>
		<tr>
			<td colspan="4" class="answer">
				<textarea rows="10" cols="250" style="margin: auto;"></textarea>
			</td>
		</tr>
			<tr>
			<td colspan="4" class="info">
				<input type="button" value=" 삭제가기 " class="btn btn-outline-success btn-sm" onclick="goDelete()"/>
				<input type="button" value=" 돌아가기 " class="btn btn-outline-success btn-sm" onclick="goBack()"/>
			</td>
		</tr>
	</table>
</body>
</html>