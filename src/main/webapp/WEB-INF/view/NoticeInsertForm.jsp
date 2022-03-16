<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<fmt:requestEncoding value="UTF-8"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>자료실 새글쓰기</title>
<script src="https://code.jquery.com/jquery-3.5.1.min.js" ></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
<script	src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
<link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-bs4.min.css" rel="stylesheet">
<script	src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-bs4.min.js"></script>
<!-- CDN 한글화 -->
<script src=" https://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.18/lang/summernote-ko-KR.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/comm.js"></script>
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
		SendPost("index.jsp", {"p":${cv.currentPage},"s":${cv.pageSize},"b":${cv.blockSize}});
	}
	// 폼의 값 유효성 검사하기 스크립트
	function formCheck(){
		var value = $("#subject").val();
		if(!value || value.trim().length==0){
			alert('제목은 반드시 입력해야 합니다.');
			$("#subject").val("");
			$("#subject").focus();
			return false; 
		}
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
	function goList(){
		SendPost("${pageContext.request.contextPath }/board/list",{"p":${cv.currentPage },"s":${cv.pageSize },"b":${cv.blockSize }},"post");
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
	.fileItem { margin-bottom: 3px;}
</style>
</head>
<body>
	<%-- ${cv } --%>
	<form action="${pageContext.request.contextPath}/board/insertOk" method="post" enctype="multipart/form-data" onsubmit="return formCheck();" >
		<table id="main_content">
			<tr>
				<td colspan="4" class="title" >
				자료실 새글쓰기
					<%-- 페이지번호, 페이지 크기, 블록크기를 숨겨서 넘긴다.  --%>
					<input type="hidden" name="p"  value="${cv.currentPage }"/>
					<input type="hidden" name="s"  value="${cv.pageSize }"/>
					<input type="hidden" name="b"  value="${cv.blockSize }"/>
				</td>
			</tr>
			<tr>
				<th>제목</th>
				<td colspan="3"> 
					<input type="text" id="subject" name="subject" size="140" />
				</td>
			</tr>
			<tr>
				<th valign="top">내용</th>
				<td colspan="3"> 
					<textarea name="content" id="content" cols="135" rows="7"></textarea>
				</td>
			</tr>
			<tr>
				<th valign="top">자료</th>
				<td colspan="3"> 
					<input type="button" value=" + " class="btn btn-outline-success btn-sm" style="margin-bottom: 5px;" onclick="addFile();"/>
					<input type="button" value=" - " class="btn btn-outline-success btn-sm" style="margin-bottom: 5px;" onclick="removeFile();"/>
					<span style="color:red;font-size: 9pt;">※ 이미지는 내용에 직접 첨부하세요!!!</span>
					<br />
					<div id="fileBox">
						<div id="fileItem1" class="fileItem"> <input type="file" name="upfile"/></div>
					</div>
				</td>
			</tr>
			<tr>
				<td colspan="4" class="info">
					<input type="submit" value=" 저장하기 " class="btn btn-outline-success btn-sm" />
					<input type="button" value=" 돌아가기 " class="btn btn-outline-success btn-sm" onclick="goList()"/>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>