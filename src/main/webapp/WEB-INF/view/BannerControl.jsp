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
        var token = localStorage.getItem("token");
		
		$.ajax({
			type: "GET",
			url: "/banner/getList",
			beforeSend: function (xhr) {
                xhr.setRequestHeader("Authorization","Bearer " + token);
            },
            success: function(res) {
        		for(data in res){
        			if (JSON.stringify(res[data].banner_show) == 1){       	
        				$("#bannerList").append($("<img id='img" + data + "' src='/imagePath/"+ JSON.stringify(res[data].banner_saveName).replaceAll("\"", "") +"'><br>"
        					+ "<div> id : " + JSON.stringify(res[data].banner_idx) + ", filename : " + JSON.stringify(res[data].banner_oriName).replaceAll("\"", "") + "</div><hr>"
        				));
        			}       	
        		}
        	},
        	error : function(){
        		alert('에러!!!');
        	}
			
		})
    });
 
	//이미지 바로 출력하는 코드 안쓸듯
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
        		getBannerList();
        	},
        	error : function(){
        		alert('에러!!!');
        	}
      	});
    }
	
	function getBannerList(){
		var token = localStorage.getItem("token");
		
		$.ajax({
			type: "GET",
			url: "/banner/getList",
			beforeSend: function (xhr) {
                xhr.setRequestHeader("Authorization","Bearer " + token);
            },
            success: function(res) {
            	if (JSON.stringify(res[data].banner_show) == 1){       	
    				$("#bannerList").append($("<img id='img" + data + "' src='/imagePath/"+ JSON.stringify(res[res.length-1].banner_saveName).replaceAll("\"", "") +"'><br>"
    					+ "<div> id : " + JSON.stringify(res[res.length-1].banner_idx) + ", filename : " + JSON.stringify(res[res.length-1].banner_oriName).replaceAll("\"", "") + "</div><hr>"
    				));
    			}
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