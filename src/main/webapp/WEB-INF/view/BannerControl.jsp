<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
    <title> - Banner 설정</title>
<script type="text/javascript"> 
	
	var sel_file;
	
	$(document).ready(function() {
        $("#fileUp").on("change", handleImgFileSelect);
    });
 
    function handleImgFileSelect(e) {
        var files = e.target.files;
        var filesArr = Array.prototype.slice.call(files);
 
        var reg = /(.*?)\/(jpg|jpeg|png|bmp)$/;
 
        filesArr.forEach(function(f) {
            if (!f.type.match(reg)) {
                alert("확장자는 이미지 확장자만 가능합니다.");
                return;
            }
 
            sel_file = f;
 
            var reader = new FileReader();
            reader.onload = function(e) {
                $("#img").attr("src", e.target.result);
            }
            reader.readAsDataURL(f);
        });
    }
   
	    
	function sendFile() {
		var token = localStorage.getItem("token");
		var form_data = new FormData();
		
		if($("#fileUp")[0].files.length === 0){
		    alert("파일을 선택해주세요");
		    return;
		}
		
		form_data.append( "fileUp", $("#fileUp")[0].files[0] );
      	$.ajax({
        	data: form_data,
        	type: "POST",
        	url: '/banner/addBanner',
        	enctype: 'multipart/form-data',
        	cache: false,
        	contentType: false,
        	processData: false,
        	beforeSend: function (xhr) {
                xhr.setRequestHeader("Authorization","Bearer " + token);
            },
        	success: function(img_name) {
        		alert('이미지 객체 반환 : ' + img_name);
        	},
        	error : function(){
        		alert('에러!!!');
        	}
      	});
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
		text-align: right;
		border: 2px solid gray;
		margin: 5px;
		padding: 5px;
	}
</style>    
</head>
<body id="page-top">

   <!-- Begin Page Content -->
   <div class="container-fluid">
       <!-- Page Heading -->
       <h1 class="h3 mb-4 text-gray-800">Banner 설정</h1>
       
       <!-- 배너 추가 버튼 클릭 시 -->
       <div id="addbanner">
	        <form id="uploadForm" enctype="multipart/form-data" method="post">
	       		<input type="file" name="fileUp" id="fileUp"/> 
	       		<br />
	        </form>
			<br />
			<input type="button" value="업로드하기" onclick="sendFile()"/>
		</div>
		
		<div id="bannerList">
			
		</div>
   </div>
   <!-- /.container-fluid -->
                       
   <div>
      <div class="img_wrap">
      	<img id="img">
      </div>
   </div>                   
</body>

</html>