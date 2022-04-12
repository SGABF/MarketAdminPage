<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<fmt:requestEncoding value="UTF-8"/>
<!DOCTYPE html>
<html lang="ko">

<head>

    <title>Qna</title>
<%-- 부트스트랩을 사용하기 위한 준비 시작 --%>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js" ></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<%-- 부트스트랩을 사용하기 위한 준비 끝 --%>
	<script type="text/javascript">
	$(document).ready(function(){
		fullReload();
		const body = document.querySelector('body');
	    const modal = document.querySelector('.modal');
	    const btnOpenPopup = document.querySelector('.btn-open-popup');
	    const btnClosePopup = document.querySelector('.btn-close-popup');
	    const btnConfirm = document.querySelector('.btn-confirm');
	
        btnOpenPopup.addEventListener('click', () => {
          modal.classList.toggle('show');

          if (modal.classList.contains('show')) {
            body.style.overflow = 'hidden';
          }
        });
        
        btnClosePopup.addEventListener('click', () => {
        	 modal.classList.toggle('show');

             if (modal.classList.contains('show')) {
               body.style.overflow = 'hidden';
             }
        });

        modal.addEventListener('click', (event) => {
          if (event.target === modal) {
            modal.classList.toggle('show');
 
            if (!modal.classList.contains('show')) {
              body.style.overflow = 'auto';
            }
          }
     	});
        
        btnConfirm.addEventListener('click', () => {
   	    	modal.classList.toggle('show');

	        if (modal.classList.contains('show')) {
	          body.style.overflow = 'hidden';
	        }
        });
        
        
	});
	function fullReload(){
	 	var token = localStorage.getItem("token");
		
	}
	function fileList(){
	 	var token = localStorage.getItem("token");
		
		$.ajax({
			type: "GET",
			url: "/notice/fileList", 
			beforeSend: function (xhr) {
                xhr.setRequestHeader("Authorization","Bearer " + token);
            },
            success: function(res) {
            	console.log(res);
            	for(data in res){
            		$("#fileList").append($("<div class='container'><div class='row align-items-center row-cols-4'><div class='col'><img id='img" + data + "' src='/imagePath/"+ JSON.stringify(res[data].back_Noticefile_SaveName).replaceAll("\"", "") +"'></div>"
            				+ "<div class='col'> id : " + JSON.stringify(res[data].back_Noticefile_Idx) + "</div>"
            				+ "</div></div><hr>"
       				));	       	
        			       	
        		}
        	},
        	error : function(){
        		alert('에러!!!');
        	}
			
		});
	}
	
	
	function deleteQnaReply(idx){
		var token = localStorage.getItem("token");
		var ans = confirm("삭제하시겠습니까?");
		
		if(ans){
			var query = {"idx" : idx};
			$.ajax({
				type: "post",
				url: "/qna/replyDelete",
				data: query, 
				
				beforeSend: function (xhr) {
	                xhr.setRequestHeader("Authorization","Bearer " + token);
	            },
	            success: function(res) {
	            	alert('답변 삭제 완료');
	            	location.reload();
	        	},
	        	error : function(){
	        		alert('에러!');
	        	}
			});
		}
	}
	
	function deleteQnaAdmin(idx){
		var token = localStorage.getItem("token");
		var ans = confirm("관리자의 권한으로 질문을 삭제하시겠습니까?");
		
		if(ans){
			var query = {"idx" : idx};
			$.ajax({
				type: "post",
				url: "/MainView/qnaDeleteAdmin",
				data: query, 
				
				beforeSend: function (xhr) {
	                xhr.setRequestHeader("Authorization","Bearer " + token);
	            },
	            success: function(res) {
	            	alert('질문 삭제 완료');
	            	location.reload();
	        	},
	        	error : function(){
	        		alert('에러!');
	        	}
			});
		}
	}
	

	function insertQnaReply(idx){
		var form_data = new FormData();
		var token = localStorage.getItem("token");
		var back_Qna_Idx = $("#inputQnaIdx").val();
		var back_Qna_Reply_Content = $("#inputQnaReply").val();
		
		/*if(back_Qna_Idx == "" || back_Qna_Idx == 0){
			alert('번호를 입력해주세요.');
			$("#back_Qna_Idx").focus();
			return
		}*/
		if(back_Qna_Reply_Content == "" || back_Qna_Reply_Content == null){
			alert('내용을 입력해주세요.');
			$("#back_Qna_Reply_Content").focus();
			return
		}
		
		$.ajax({
			type: "POST",
			url: "/qna/qndInsertComment",
			data: {
				"back_Qna_Reply_Content" :  back_Qna_Reply_Content,
				"back_Qna_Idx" : idx
			},
			beforeSend: function (xhr) {
                xhr.setRequestHeader("Authorization","Bearer " + token);
            },
            success: function(res) {
            	alert('답변 등록 성공');
            	location.reload();
        	},
        	error : function(){
        		alert('에러!');
        	}
			
		});
	}
	
	
	</script>
	
<style type="text/css">

	.modal {
        position: absolute;
        top: 0;
        left: 0;

        width: 100%;
        height: 100%;

        display: none;

        background-color: rgba(0, 0, 0, 0.4);
     }

     .modal.show {
       display: block;
     }

     .modal_body {
       position: absolute;
       top: 50%;
       left: 50%;

       width: 400px;
       height: 400px;

       padding: 40px;

       text-align: center;

       background-color: rgb(255, 255, 255);
       border-radius: 10px;
       box-shadow: 0 2px 3px 0 rgba(34, 36, 38, 0.15);

       transform: translateX(-50%) translateY(-50%);
     }
	* { font-size: 10pt; }
	table#content{width: 90%; margin: auto;}
	th {border: 1px solid gray; padding: 10px; text-align: center; font-size: 14pt;}
	td {border: 1px solid gray; padding: 10px; font-size: 12pt;}
	td.title {border:none; padding: 10px; text-align: center; font-size: 25pt;}
	td.info {border:none; padding: 10px; text-align: right; }
	td.info2 {border: 1px solid gray; padding: 10px; text-align: center; }
	
	     img {
     	width: 80px;
     	height: 80px;
     }
</style>  
</head>

<body>
<c:set var="imageURL" value="/imagePath/"/>
<table id="content">
		<tr>
			<td colspan="6" class="title">사용자 질문목록</td>
		</tr>
		<tr>
			<th width="5%">No</th>
			<th width="10%">작성자</th>
			<th width="8%">이미지</th>
			<th width="20%">제목</th>
			<th width="30%">질문내용</th>
			<th width="20%" style="background-color: #E6E6E6">관리자 답변</th>
			<th width="10%" style="background-color: #E6E6E6">답변완료</th>
		</tr>
			<c:forEach var="vo" items="${qna }" >
				<tr align="center">
					<td>${vo.back_Qna_Idx }</td>
					<td>
						<c:out value="${vo.user_Name }"></c:out>
					</td>
					<td>
						<img src="${imageURL} ${vo.back_Qnafile_SaveName }"/>
					</td>
					<td>
						<c:out value="${vo.back_Qna_Name}"></c:out>
					</td>
					<td>
						<c:out value="${vo.back_Qna_Content}"></c:out>
					</td>
					<td  style="background-color: #F2F2F2">
						<c:if test="${vo.back_Qna_Reply_Content != null }" >
							<c:out value="${vo.back_Qna_Reply_Content}"></c:out>
						</c:if>
						<c:if test="${vo.back_Qna_Reply_Content == null }" >
							<c:out value="아직 답변이 없습니다." ></c:out>
						</c:if>
					</td>
					<td style="color: green; background-color: #F2F2F2">
						<c:if test="${vo.back_Qna_Question == 0 }" >
							<c:out value="미답변"></c:out>
							 <div class="modal"> 
								<div class="modal_body">
									<h1>답변 등록</h1>
									<div class="input-group mb-3">
									</div>
									<div class="input-group mb-3">
									  <span class="input-group-text">답변내용 : </span>
									  <input type="text" id="inputQnaReply" class="form-control" placeholder="내용 입력" aria-label="back_Qna_Reply_Content" aria-describedby="basic-addon1">
									</div>
									<br><br>
									
									<button class="btn btn-warning btn-confirm" onclick='insertQnaReply(${vo.back_Qna_Idx});'>등록</button>
									<button class="btn btn-warning btn-close-popup">취소</button>
								</div>
							</div>
							<button style="float: right;" class="btn btn-outline-primary btn-open-popup">답변등록</button>
						</c:if>
						<c:if test="${vo.back_Qna_Question == 1 }">
							<c:out value="답변완료"></c:out>
							<button type="button" onclick="deleteQnaReply(${vo.back_Qna_Idx})"  class="btn btn-outline-danger " >답변삭제</button>
						</c:if>
							<button type="button" onclick="deleteQnaAdmin(${vo.back_Qna_Idx})"  class="btn btn-outline-danger " >질문삭제</button>
					</td>
				</tr>	
			</c:forEach>
		<tr>
		</tr>
	</table>	            
</body>

</html>