<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
	trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<fmt:requestEncoding value="UTF-8" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>질문</title>
<!--  엑시콘사용 : 다운로드받은 폴더를 넣고 CSS파일을 읽는다. -->
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/static/axicon/axicon.min.css" />

<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
<link
	href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-bs4.min.css"
	rel="stylesheet">
<script
	src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-bs4.min.js"></script>
<!-- CDN 한글화 -->
<script
	src=" https://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.18/lang/summernote-ko-KR.min.js"></script>
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
	});
	function sendFile(file, el) {
		var form_data = new FormData();
      	form_data.append('file', file);
      	$.ajax({
        	data: form_data,
        	type: "POST",
        	url: '${pageContext.request.contextPath}/imageUpload',
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
	// 첨부 파일 개수 증감하는 스크립트
	var minLimit = 1;  // 최소 파일 갯수
	var maxLimit = 5; // 최대 파일 갯수
	var fileCount = 1; // 현재 파일 갯수
	function addFile(){ // 추가
		if(fileCount>=maxLimit){
			alert('첨부 파일은 최대 ' + maxLimit + "까지만 허용합니다.");
			return;
		}
		fileCount++;
		$("#fileBox").append("<div id='fileItem"+fileCount+"' class='fileItem'><input type='file' name='upfile'></div>");
	}
	function removeFile(){ // 삭제
		if(fileCount<=minLimit){
			alert('첨부 파일은 최소 ' + minLimit + "개는 있어야 합니다.");
			return;
		}
		$("#fileItem"+fileCount).remove();
		fileCount--;
	}
	//-----------------------------------------------------------------------------------------------------------
	// 돌아가기버튼 클릭시 사용할 함수
	function goBack(){
		SendPost("${pageContext.request.contextPath}/qna/qnaView", {"p":${cv.currentPage},"s":${cv.pageSize},"b":${cv.blockSize},"idx":${bv.back_Qna_Idx}});
	}
	// 폼의 값 유효성 검사하기 스크립트
	function formCheck(){
		var value = $("#content").summernote('code');
		// alert("값 : " + value);
		if(!value || value.trim()=="<p><br></p>"){
			alert('내용은 반드시 입력해야 합니다.');
			$("#content").val("");
			$("#content").focus();
			return false; 
		}
		return true;
	}
	function deleteFile(idx, count){
		var value = $("#delfile"+count).val();
		if(value==0){
			if(confirm(idx+"번 첨부파일을 정말로 삭제하시겠습니까?")){
				$("#delfile"+count).val(idx)
				$("#msg"+count).html('삭제')
			}
		}else{
			if(confirm(idx+"번 첨부파일을 삭제를 취소 하시겠습니까?")){
				$("#delfile"+count).val(0)
				$("#msg"+count).html('')
			}
		}
	}
</script>
<style type="text/css">
* {
	font-size: 10pt;
}

table#main_content {
	width: 80%;
	margin: auto;
}

th {
	border: 1px solid gray;
	background-color: silver;
	padding: 5px;
	text-align: center;
}

td {
	border: 1px solid gray;
	padding: 5px;
}

td.title {
	border: none;
	padding: 5px;
	text-align: center;
	font-size: 18pt;
}

td.info {
	border: none;
	padding: 5px;
	text-align: right;
}

td.info2 {
	border: 1px solid gray;
	padding: 5px;
	text-align: center;
}

td.answer {
	width: 80%;
	margin: auto;
	border: none;
}

.fileItem {
	margin-bottom: 3px;
}
</style>
</head>
<body>
	<form action="${pageContext.request.contextPath}/qna/qnaUpdateOK"
		method="post" enctype="multipart/form-data"
		onsubmit="return formCheck();">

		<table id="main_content">
			<tr>
				<td colspan="4" class="title">질문</td>
			</tr>
			<tr>
				<th>제목</th>
				<td colspan="3"><c:out value="${bv.back_Qna_Name }"></c:out></td>
			</tr>
			<tr>
				<th valign="middle">내용</th>
				<td colspan="3">
					<div id="content">${bv.back_Qna_Content }</div>
				</td>
			</tr>
			<tr>
				<th valign="top">자료</th>
				<td colspan="3">
					<%-- 첨부파일을 다운 받도록 링크를 달아준다. --%> <c:if
						test="${not empty bv.fileList }">
						<c:forEach var="bvo" items="${bv.fileList }">
							<c:url var="url" value="/qna/qnaDownload">
								<c:param name="of" value="${bvo.back_Qnafile_OriName }"></c:param>
								<c:param name="sf" value="${bvo.back_Qnafile_SaveName }"></c:param>
							</c:url>
							<a href="${url }" title="${bvo.back_Qnafile_OriName }"><i
								class="axi axi-download2"></i> ${bvo.back_Qnafile_OriName }</a>
							<br />
						</c:forEach>
					</c:if>
				</td>
			</tr>
			<tr>
				<td colspan="4" class="info">
					<input type="submit" value=" 수정완료 " class="btn btn-outline-success btn-sm" />
					<input type="button" value=" 수정취소 " class="btn btn-outline-success btn-sm" onclick="goBack()" /> 
				</td>
			</tr>
		</table>
	</form>
</body>
</html>