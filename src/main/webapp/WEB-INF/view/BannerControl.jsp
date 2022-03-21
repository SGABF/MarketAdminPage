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
		fullReload();
		
		$("#addbanner").css('display', 'none');
		$("#addbannerButton").click(function(){
			if($("#addbanner").css('display') == 'none'){
				$("#addbanner").slideDown(500);
			}
		});
		
		$("#uploadcancel").click(function(){
			if($("#addbanner").css('display') != 'none'){
				$("#addbanner").slideUp(500);
			}
		});
		
		$("#fileUp").on('change',function(){
			  var fileName = $("#fileUp").val();
			  fileName = fileName.replace('C:\\fakepath\\', '');
			  $("#addfilename").val(fileName);
		});
		
    });
	
	function fullReload(){
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
        				$("#bannerList").append($("<div class='container'><div class='row align-items-center row-cols-4'><div class='col'><img id='img" + data + "' src='/imagePath/"+ JSON.stringify(res[data].banner_saveName).replaceAll("\"", "") +"'></div>"
        					+ "<div class='col'> id : " + JSON.stringify(res[data].banner_idx) + "</div>"
        					+ "<div class='col'> filename : " + JSON.stringify(res[data].banner_oriName).replaceAll("\"", "") + "</div>"
        					+ "<div class='col'><input type='button' id='deletebannerButton' class='btn btn-danger' value='배너삭제' onclick='deleteBanner(" + JSON.stringify(res[data].banner_idx) + ")'/></div>"
        					+ "</div></div><hr>"
        				));
        			}       	
        		}
        	},
        	error : function(){
        		alert('에러!!!');
        	}
			
		});
	}
 
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
            	
            	if (JSON.stringify(res[res.length-1].banner_show) == 1){
            		
    				$("#bannerList").append($("<div class='container'><div class='row align-items-center row-cols-4'><div class='col'><img id='img" + data + "' src='/imagePath/"+ JSON.stringify(res[res.length-1].banner_saveName).replaceAll("\"", "") +"'></div>"
    					+ "<div class='col'> id : " + JSON.stringify(res[res.length-1].banner_idx) + "</div>"
    					+ "<div class='col'> filename : " + JSON.stringify(res[res.length-1].banner_oriName).replaceAll("\"", "") + "</div>" 
    					+ "<div class='col'><input type='button' id='deletebannerButton' class='btn btn-danger' value='배너삭제' onclick='deleteBanner("+ JSON.stringify(res[res.length-1].banner_idx) +")'/></div>"
    					+ "</div></div><hr>"
    				));
            		
    			}
        	},
        	error : function(){
        		alert('에러!!!');
        	}
			
		})
	}
	
	function deleteBanner(id){
		var token = localStorage.getItem("token");
		
		$.ajax({
			type: "POST",
			data: {
				"banner_id" : id
			},
			url: "/banner/deleteBanner",
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
       <h1 class="h3 mb-4 text-gray-800">Banner 설정</h1>
       
       <input type="button" id="addbannerButton" class="btn btn-success" value="배너추가"/>
       <!-- 배너 추가 버튼 클릭 시 -->
       <div id="addbanner">
	        <form id="uploadForm" enctype="multipart/form-data" method="post">
	        	<div class="input-group mb-3">
		        	<input type="text" id="addfilename" class="form-control" placeholder="첨부파일" aria-label="Recipient's username" aria-describedby="basic-addon2">
		        	<label class="btn btn-success" for="fileUp">
		        		파일 선택
		        	</label>
		        </div>
		        <input type="button" class="btn btn-success" value="업로드하기" onclick="sendFile()"/>
		        <input type="button" id="uploadcancel" class="btn btn-success" value="업로드취소"/>
	       		<input type="file" name="fileUp" id="fileUp" style="display: none;"/>
	        </form>
			
		</div>
		
		<div id="bannerList">
			
		</div>
   </div>
   <!-- /.container-fluid -->
                       
     
</body>

</html>