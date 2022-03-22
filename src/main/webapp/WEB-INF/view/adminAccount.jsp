<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<fmt:requestEncoding value="UTF-8"/>
<!DOCTYPE html>
<html lang="ko">

<head>

    <title>- AdminAccount</title>

<script type="text/javascript">
	$(document).ready(function(){
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
	
	
	

</script>

<style type="text/css">
	.col{
		text-align: right;
	}
	
	#adminTable{
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
       height: 600px;

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
		<h1 class="h3 mb-4 text-gray-800">관리자 계정 설정 페이지</h1>
		
		<div class="modal"> 
			<div class="modal_body">
				<h1>관리자 계정 생성</h1>
				
				<div class="input-group mb-3">
				  <span class="input-group-text" id="basic-addon1">Admin ID : </span>
				  <input type="text" class="form-control" placeholder="ID 입력" aria-label="admin_id" aria-describedby="basic-addon1">
				</div>
				
				<div class="input-group mb-3">
				  <span class="input-group-text" id="basic-addon1">Admin PW : </span>
				  <input type="text" class="form-control" placeholder="Password 입력" aria-label="admin_password" aria-describedby="basic-addon1">
				</div>
				
				<div class="input-group mb-3">
				  <span class="input-group-text" id="basic-addon1">사용자 이름 : </span>
				  <input type="text" class="form-control" placeholder="사용자 이름 입력" aria-label="admin_name" aria-describedby="basic-addon1">
				</div>
				
				<select class="form-select" aria-label="privilege_select">
				  <option selected>권한 선택</option>
				  <option value="1">ADMIN</option>
				  <option value="2">USER</option>
				  <option value="3">OTHER</option>
				</select>
				
				<br>
			
				<button class="btn btn-warning btn-confirm">확인</button>
				<button class="btn btn-warning btn-close-popup">취소</button>
			</div>
		</div>
		<button class="btn btn-warning btn-open-popup">계정생성</button>
		
		<table id="adminTable">
			<tr>
				<th width="10%">No</hd>
				<th width="20%">계정명</th>
				<th width="20%">권한</th>
				<th>Control</th>
			</tr>
			<tr id="adminAccount">
				
			</tr>
		</table>
	</div>
	<!-- /.container-fluid -->
	                       
</body>

</html>