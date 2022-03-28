<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<fmt:requestEncoding value="UTF-8"/>
<!DOCTYPE html>
<html lang="ko">

<head>

    <title>BackCategory</title>
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
		
		$.ajax({
			type: "GET",
			url: "/category/categoryList", 
			beforeSend: function (xhr) {
                xhr.setRequestHeader("Authorization","Bearer " + token);
            },
            success: function(res) {
            	for(data in res){
        			    
            		$("#categoryList").append($("<tr><td>" + JSON.stringify(res[data].back_Category_Idx) + "</td>"
	       					+ "<td>" + JSON.stringify(res[data].back_Category_Name).replaceAll("\"", "") + "</td>"
	       					+ "<td>" + JSON.stringify(res[data].back_Category_Use).replaceAll("\"", "") + "</td>"
       						+ "<td><button type='button' class='btn btn-danger' onclick='activateCategory("+ JSON.stringify(res[data].back_Category_Idx) +")'>활성화 변경</button> &nbsp;"
       						+ "</tr>"
       				));	       	
        			       	
        		}
        	},
        	error : function(){
        		alert('에러!!!');
        	}
			
		});
	}
	
	
	function insertCategory(){
		var form_data = new FormData();
		var token = localStorage.getItem("token");
		
		var back_Category_Name = $("#inputCategoryName").val();
		
		if(back_Category_Name == "" || back_Category_Name == null){
			alert('카테고리 이름을 입력해주세요.');
			$("#inputCategoryName").focus();
			return
		}
		
		$.ajax({
			type: "POST",
			url: "/category/insertCategory",
			data: {
				"back_Category_Name" : back_Category_Name,
			},
			beforeSend: function (xhr) {
                xhr.setRequestHeader("Authorization","Bearer " + token);
            },
            success: function(res) {
            	alert('추가 성공');
            	location.reload();
        	},
        	error : function(){
        		alert('추가 에러!');
        	}
			
		});
	}
	function activateCategory(back_Category_Idx){
		var token = localStorage.getItem("token");
		
		$.ajax({
			type: "POST",
			data: {
				"back_Category_Idx":back_Category_Idx
			},
			url: "/category/activateCategory",
			beforeSend: function (xhr) {
                xhr.setRequestHeader("Authorization","Bearer " + token);
            },
            success: function(res) {
            	alert('변경완료');
				location.reload();
        	},
        	error : function(){
        		alert('변경에러!!!');
        	}
			
		})
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
</style>    
</head>

<body id="page-top">

   <!-- Begin Page Content -->
   <div class="container-fluid">
	   <!-- Page Heading -->
	   <h1 class="h3 mb-4 text-gray-800">카테고리관리</h1>
	   
	   <div class="modal"> 
			<div class="modal_body">
				<h1>카테고리관리</h1>
				
				<div class="input-group mb-3">
				  <span class="input-group-text"> 카테고리 이름: </span>
				  <input type="text" id="inputCategoryName" class="form-control" placeholder="카테고리 입력" aria-label="back_Category_Name" aria-describedby="basic-addon1">
				</div>
				
				<br><br>
				
				<button class="btn btn-warning btn-confirm" onclick='insertCategory();'>확인</button>
				<button class="btn btn-warning btn-close-popup">취소</button>
			</div>
		</div>
		<button class="btn btn-warning btn-open-popup">카테고리 추가</button>
		
		
		
		<table id="categoryList">
			<tr>
				<th width="10%">No</th>
				<th width="30%">이름</th>
				<th width="20%">사용여부</th>
				<th width="20%">Control</th>
			</tr>
		
		</table>
	 
		</div>
   <!-- /.container-fluid -->
                       
</body>

</html>