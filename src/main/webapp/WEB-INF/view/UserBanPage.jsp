<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<fmt:requestEncoding value="UTF-8"/>
<!DOCTYPE html>
<html lang="ko">

<head>

    <title>UserBanned</title>
	<script type="text/javascript">
	
	$(document).ready(function(){
		fullReload();
	});
	function fullReload(){
	 	var token = localStorage.getItem("token");
		
		$.ajax({
			type: "GET",
			url: "/userBanned/userBannedList", 
			beforeSend: function (xhr) {
                xhr.setRequestHeader("Authorization","Bearer " + token);
            },
            success: function(res) {
            	console.log(res);
            	for(data in res){
        			    
            		$("#userList").append($("<tr><td>" + JSON.stringify(res[data].user_Idx) + "</td>"
       						+ "<td>" + JSON.stringify(res[data].user_Id).replaceAll("\"", "") + "</td>"
	       					+ "<td>" + JSON.stringify(res[data].user_Name).replaceAll("\"", "") + "</td>"
	       					+ "<td>" + JSON.stringify(res[data].user_Banned).replaceAll("\"", "") + "</td>"
       						+ "<td><button type='button' class='btn btn-danger' onclick='activateBanned("+ JSON.stringify(res[data].user_Idx) +")'> 제재 상태</button> &nbsp;"
       						+ "</tr>"
       				));	       	
        			       	
        		}
        	},
        	error : function(){
        		alert('에러!!!');
        	}
			
		});
	}
	
	function activateBanned(user_Idx){
		var token = localStorage.getItem("token");
		
		$.ajax({
			type: "POST",
			data: {
				"user_Idx" : user_Idx
			},
			url: "/userBanned/activateBanned",
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
			
		});
	}
	
	
</script>
	
<style type="text/css">
	
	#userList{
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
</style>    
</head>

<body id="page-top">

   <!-- Begin Page Content -->
   <div class="container-fluid">
	   <!-- Page Heading -->
	   <h1 class="h3 mb-4 text-gray-800">회원 관리</h1>
	
		<table id="userList">
			<tr>
				<th width="10%">No</th>
				<th width="30%">ID</th>
				<th width="20%">이름</th>
				<th width="20%">제재 상태</th>
				<th width="20%">Control</th>
			</tr>
		
		</table>
	 
		</div>
   <!-- /.container-fluid -->
                       
</body>

</html>