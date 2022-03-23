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
	
	$(document).ready(function() {
		fullReload();
		
	
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
            	console.log(res);
            	for(data in res){
        			    
        				$("#noticeList").append($("<div class='container'><div class='row align-items-center row-cols-4'><div class='col'></div>"
        					+ "<div class='col'> 글번호 : " + JSON.stringify(res[data].back_Notice_Idx) + "</div>"
        					+ "<div class='col'> 제목 : " + JSON.stringify(res[data].back_Notice_Subject).replaceAll("\"", "") + "</div>"
        					+ "<div class='col'><input type='button' id='deleteBannerButton' class='btn btn-danger' value='삭제' onclick='deleteNotice(" + JSON.stringify(res[data].back_Notice_Idx) + ")'/></div>"
        					+ "</div></div><hr>"
        				));
        			       	
        		}
        	},
        	error : function(){
        		alert('에러!!!');
        	}
			
		});
	}
	
	function insertNotice() {
		var token = localStorage.getItem("token");
		var form_data = new FormData();
		
		form_data.append( "fileUp", $("#fileUp")[0].files[0] );
      	$.ajax({
        	data: form_data,
        	type: "POST",
        	url: '/notice/insertNotice',
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
	
	</script>
	<style type="text/css">
	#addbanner {
		text-align: right;
		border: 2px solid gray;
		margin: 5px;
		padding: 5px;
	}
	
	#bannerList {
		margin: 5px;
		padding: 5px;
	}
	
	.input-file-button{
	    padding: 6px 25px;
	    background-color:#FF6600;
	    border-radius: 4px;
	    color: white;
	    cursor: pointer;
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
	   <input type="button" id="insertNotice" class="btn btn-success" value="공지사항 추가"/>
	 
	   <div id="noticeList">
   </div>
   <!-- /.container-fluid -->
                       
</body>

</html>