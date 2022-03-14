<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<script	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>

<script type="text/javascript">


	
function json_convert(){
	
	var username = document.getElementById('username');
	var password = document.getElementById('password');
	
	$.ajax({
		type: "post",
		url: "/authenticate/token", //데이터 전송받는 특정 주소
		data: JSON.stringify({
			'username' : username.value,
			'password' : password.value
		}),
		dataType: "json",
		contentType: "application/json",
		success: function(data) {
			alert("인증 성공");
			localStorage.setItem('token', data.token);
			location.replace("/MainPage");
		},
		error: function(request, error){
			alert("인증 실패");
		}
	});
	
}

function test(){
	var token = localStorage.getItem("token");
	    $.ajax({
	        type: "GET",
	        url: "/hello",
	        beforeSend: function (xhr) {
	            xhr.setRequestHeader("Content-type","application/json");
	            xhr.setRequestHeader("Authorization","Bearer " + token);
	        },
	        success: function (res) {
	            //alert("쿼리를 정상적으로 보냈습니다. : " + res);
	            
	            $('#controllerTest').html(res);

	        },
	        error: function(request, error){
				alert("아직 인증되지 않았습니다.");
			}
	    });
	
}

$(function(){
	
	$("#tokenVisible").click(function() {
		var token = localStorage.getItem("token");
		if ($("#showToken").css('display') == 'inline') {
			$("#showToken").css('display','none');
		}else{
			if(token == null) {
				$("#showToken").html("Authorization : 인증 필요");
			} else {
				$("#showToken").html("Authorization : Bearer " + token);	
			}
			$("#showToken").css('display','inline');
		}
	});
	
});

</script>

<body>
	<h1 style="text-align: center;">관리자용 토큰인증 페이지</h1>
	<div>
        <form name="f" id="f">               
            <fieldset>
                <legend>Please Login</legend>
                <label for="username">Username</label>
                <input type="text" id="username" name="username"/>        
                <label for="password">Password</label>
                <input type="password" id="password" name="password"/>    
                <div class="form-actions">
        			<button type="button" class="btn" onclick="json_convert();">Log in</button>            
                </div>
            </fieldset>
        </form>
    </div>
    
    <hr />
    <button type="button" class="btn" onclick="test();">토큰 인증 방식 성공 테스트</button><br />
    <div id="controllerTest"></div><br />
    <hr />
    <button type="button" id="tokenVisible">토큰 보이기</button><br />
    <div id="showToken"></div>



</body>
</html>