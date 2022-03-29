<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<fmt:requestEncoding value="UTF-8"/>
<!DOCTYPE html>
<html lang="ko">

<head>

    <title>- MenuControl</title>
    

	<script type="text/javascript">

	$(document).ready(function(){
		getMenuListData();
		
		$("#addMenu").css('display', 'none');
		$("#addMenuButton").click(function(){
			if($("#addMenu").css('display') == 'none'){
				$("#addMenu").slideDown(500);
			}
		});
		
		$("#addMenuCancel").click(function(){
			if($("#addMenu").css('display') != 'none'){
				$("#addMenu").slideUp(500);
			}
		});
	});
	
	function getMenuListData(){
		var token = localStorage.getItem("token");
		
		$.ajax({
			type: "GET",
			url: "/menuCtl/selectListAll",
			beforeSend: function (xhr) {
                xhr.setRequestHeader("Authorization","Bearer " + token);
            },
            success: function(res) {
            	
            	$('#menuTable').DataTable( {
            	    data: res,
            	    columns : [
            	    	{ data : 'menuDB_idx'},
            	    	{ data : 'menuDB_name'},
            	    	{ data : 'menuDB_col1'},
            	    	{ data : 'menuDB_use'}
            	    ]
            	} );
        	},
        	error : function(){
        		alert('에러!!!');
        	}
			
		})
	}
	
	function addAdminMenu(){
		var token = localStorage.getItem("token");
		
		var menuDB_name = $("#inputMenuName").val();
		var menuDB_col1 = $("#inputMenuURL").val();
		
		if(menuDB_name == "" || menuDB_name == null){
			alert('메뉴 이름을 입력해주세요.');
			$("#inputMenuName").focus();
			return
		}
		
		if(menuDB_col1 == "" || menuDB_col1 == null){
			alert('메뉴 URL을 입력해주세요.');
			$("#inputMenuURL").focus();
			return
		}
		
		$.ajax({
			type: "POST",
			url: "/menuCtl/insertMenu",
			data: {
				"menuDB_name" : menuDB_name,
				"menuDB_col1" : menuDB_col1
			},
			beforeSend: function (xhr) {
                xhr.setRequestHeader("Authorization","Bearer " + token);
            },
            success: function(res) {
            	alert('메뉴 추가 성공');
            	location.reload();
        	},
        	error : function(){
        		alert('메뉴를 추가하지 못했습니다.');
        	}
			
		});
	}
	
	function SwitchingMenu(){
		var token = localStorage.getItem("token");
		
		var menuDB_idx = $("#inputMenuIdx").val();
		
		if(menuDB_idx == "" || menuDB_idx == null){
			alert('메뉴 번호를 입력해주세요.');
			$("#inputMenuIdx").focus();
			return
		}
		
		$.ajax({
			type: "POST",
			url: "/menuCtl/menuActive",
			data: {
				"menuDB_idx" : menuDB_idx
			},
			beforeSend: function (xhr) {
                xhr.setRequestHeader("Authorization","Bearer " + token);
            },
            success: function(res) {
            	alert('활성/비활성 성공');
            	location.reload();
        	},
        	error : function(){
        		alert('에러!!!');
        	}
			
		});
	}

	
	
</script>

<!-- Custom styles for this page -->
<link href="../static/vendor/datatables/dataTables.bootstrap4.min.css" rel="stylesheet">
<style type="text/css">
	#addMenu{
		margin: 10px;
		padding: 10px;
		border: 3px solid black;
		border-radius: 10px;
	}
</style>

</head>

<body id="page-top">
   
   <!-- Begin Page Content -->
    <div class="container-fluid">

        <!-- Page Heading -->
        <h1 class="h3 mb-2 text-gray-800">관리자 페이지 메뉴 관리</h1>
        <p class="mb-4">
        	관리자 페이지 메뉴를 추가하고 활성 / 비활성 할 수 있습니다.<br>
        </p>
        
        <input type="button" id="addMenuButton" class="btn btn-success" value="메뉴컨트롤" style="margin: 10px"/>
        <div id="addMenu">
	    	
	    	<div class="input-group mb-3">
			  <span class="input-group-text" id="basic-addon1">메뉴 이름</span>
			  <input type="text" class="form-control" id="inputMenuName" placeholder="메뉴 이름 입력" aria-label="MenuName" aria-describedby="basic-addon1">
			</div>    
			
			<div class="input-group mb-3">
			  <span class="input-group-text" id="basic-addon1">메뉴 URL</span>
			  <input type="text" class="form-control" id="inputMenuURL" placeholder="메뉴 URL 입력" aria-label="MenuURL" aria-describedby="basic-addon1">
			</div>
		    
		    <input type="button" id="addMenuButton" class="btn btn-success" onclick="addAdminMenu();" value="메뉴 추가 하기"/>
		    <hr/>
		    
		    <div class="input-group mb-3">
			  <button class="btn btn-outline-secondary" type="button" onclick="SwitchingMenu();" id="button-addon1">활성/비활성 하기</button>
			  <input type="text" class="form-control" id="inputMenuIdx" placeholder="활성 메뉴 번호 입력" aria-label="MenuActive" aria-describedby="button-addon1">
			</div>
		    
	        <input type="button" id="addMenuCancel" class="btn btn-danger" value="메뉴 컨트롤 닫기"/>
		</div>

        <div class="card shadow mb-4">
            <div class="card-header py-3">
                <h6 class="m-0 font-weight-bold text-primary">관리자 페이지 메뉴 리스트</h6>
            </div>
            <div class="card-body">
                <div class="table-responsive">
                    <table class="table table-bordered" id="menuTable" width="100%" cellspacing="0">
                        <thead>
                            <tr>
                                <th>No.</th>
                                <th>메뉴 이름</th>
                                <th>메뉴 URL</th>
                                <th>메뉴 활성여부</th>
                            </tr>
                        </thead>
                    </table>
                </div>
            </div>
        </div>

    </div>
    <!-- /.container-fluid -->

    <!-- Page level plugins -->
    <script src="../static/vendor/datatables/jquery.dataTables.min.js"></script>
    <script src="../static/vendor/datatables/dataTables.bootstrap4.min.js"></script>

    <!-- Page level custom scripts -->
    <script src="../static/js/demo/datatables-demo.js"></script>
   <!-- /.container-fluid -->
                       
</body>

</html>