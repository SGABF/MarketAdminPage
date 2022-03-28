<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<fmt:requestEncoding value="UTF-8"/>
<!DOCTYPE html>
<html lang="ko">

<head>

    <title>Notice</title>
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
        
        $("#fileUp").on('change',function(){
			  var fileName = $("#fileUp").val();
			  fileName = fileName.replace('C:\\fakepath\\', '');
			  $("#addFileName").val(fileName);
		});
	    
        
	});
	
	function fullReload(){
	 	var token = localStorage.getItem("token");
		
		$.ajax({
			type: "GET",
			url: "/notice/getList", 
			beforeSend: function (xhr) {
                xhr.setRequestHeader("Authorization","Bearer " + token);
            },
            success: function(res) {
            	for(data in res){
        			    
            		$("#noticeList").append($("<tr><td>" + JSON.stringify(res[data].back_Notice_Idx) + "</td>"
            				+ "<td>" +"<img id='img" + data + "' src='/imagePath/"+ JSON.stringify(res[data].back_Noticefile_SaveName).replaceAll("\"", "") +"'>"+ "</td>"
       						+ "<td>" + JSON.stringify(res[data].back_Notice_Subject).replaceAll("\"", "") + "</td>"
	       					+ "<td>" + JSON.stringify(res[data].back_Notice_Content).replaceAll("\"", "") + "</td>"
       						+ "<td><button type='button' class='btn btn-danger' onclick='deleteNotice("+ JSON.stringify(res[data].back_Notice_Idx) +")'>공지사항삭제</button> &nbsp;"
       						+ "</tr>"
       				));	       	
        			       	
        		}
        	},
        	error : function(){
        		alert('에러!!!');
        	}
			
		});
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
	
	function insertFile() {
		var token = localStorage.getItem("token");
		var form_data = new FormData();
		
		form_data.append( "fileUp", $("#fileUp")[0].files[0] );
      	$.ajax({
        	data: form_data,
        	type: "POST",
        	url: '/notice/insertFile',
        	enctype: 'multipart/form-data',
        	cache: false,
        	contentType: false,
        	processData: false,
        	beforeSend: function (xhr) {
                xhr.setRequestHeader("Authorization","Bearer " + token);
            },
        	success: function(res) {        
        		console.log(res);
        	
        	},
        	error : function(){
        		alert('에러!!!');
        	}
      	});
    }
	

	function deleteNotice(id){
		var token = localStorage.getItem("token");
		
		$.ajax({
			type: "POST",
			data: {
				"backNotice" : id
			},
			url: "/notice/deleteNotice",
			beforeSend: function (xhr) {
                xhr.setRequestHeader("Authorization","Bearer " + token);
            },
            success: function(res) {
            	alert('삭제완료');
				location.reload();
        	},
        	error : function(){
        		alert('에러!!!');
        	}
			
		})
	}
	function insertNotice(){
		var form_data = new FormData();
		var token = localStorage.getItem("token");
		
		var back_Notice_Subject = $("#inputNoticeSubject").val();
		var back_Notice_Content = $("#inputNoticeContent").val();
		
		if(back_Notice_Subject == "" || back_Notice_Subject == null){
			alert('제목을 입력해주세요.');
			$("#inputNoticeSubject").focus();
			return
		}
		
		if(back_Notice_Content == "" || back_Notice_Content == null){
			alert('내용을 입력해주세요.');
			$("#inputNoticeContent").focus();
			return
		}
		
		$.ajax({
			type: "POST",
			url: "/notice/insertNotice",
			data: {
				"back_Notice_Subject" : back_Notice_Subject,
				"back_Notice_Content" :  back_Notice_Content
			},
			beforeSend: function (xhr) {
                xhr.setRequestHeader("Authorization","Bearer " + token);
            },
            success: function(res) {
            	alert('공지글 추가 성공');
            	location.reload();
        	},
        	error : function(){
        		alert('에러!');
        	}
			
		});
	}
	
	
	</script>
	<style type="text/css">
	
	#noticeList{
		border: 3px solid black;
		margin: 5px;
		padding: 5px;
		width: 100%
	}
	
	th {
		border: 3px solid black;
		text-align: center;
		padding: 15px;
	}
	
	td {
		border: 3px solid black;
		text-align: center;
		padding: 15px;
	}
	
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
     img {
     	width: 100px;
     	height: 100px;
     }
</style>    
</head>

<body id="page-top">

   <!-- Begin Page Content -->
   <div class="container-fluid">
	   <!-- Page Heading -->
	   <h1 class="h3 mb-4 text-gray-800">공지사항 설정</h1>
	   
	   <div class="modal"> 
			<div class="modal_body">
				<h1>공지사항</h1>
				
				<div class="input-group mb-3">
				  <span class="input-group-text">제목 : </span>
				  <input type="text" id="inputNoticeSubject" class="form-control" placeholder="제목 입력" aria-label="back_Notice_Subject" aria-describedby="basic-addon1">
				</div>
				
				<div class="input-group mb-3">
				  <span class="input-group-text">내용 : </span>
				  <input type="text" id="inputNoticeContent" class="form-control" placeholder="내용 입력" aria-label="back_Notice_Content" aria-describedby="basic-addon1">
				</div>
				<input type="text" id="addFileName" class="form-control" placeholder="첨부파일" aria-label="Recipient's username" aria-describedby="basic-addon1">
				<br>
		        	<label class="btn btn-success" for="fileUp">
		        		파일 선택
		        	</label>
				<br><br>
				
				<button class="btn btn-warning btn-confirm" onclick='insertNotice();insertFile();'>확인</button>
				<button class="btn btn-warning btn-close-popup">취소</button>
				<input type="file" name="fileUp" id="fileUp" style="display: none;"/>
			</div>
		</div>
		<button class="btn btn-warning btn-open-popup">공지사항 추가</button>
		
		
		
		<table id="noticeList">
			<tr>
				<th width="10%">No</th>
				<th width="10%">이미지</th>
				<th width="20%">제목</th>
				<th width="30%">내용</th>
				<th>Control</th>
			</tr>
		
		</table>
	 
		</div>
   <!-- /.container-fluid -->
                       
</body>

</html>