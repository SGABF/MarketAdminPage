<%@page import="java.util.Enumeration"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Spring Boot Application</title>
</head>

<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.5/jquery.min.js"></script>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>

<script type="text/javascript">

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
	            alert("토큰 인증 성공");
	        }
	    });
	
}


</script>
<body>
	Hello, Spring Boot App
	
	<%
	Enumeration e = request.getHeaderNames();
	while ( e.hasMoreElements() ) {
		String names = (String)e.nextElement();
		String value = request.getHeader(names);
		out.println( names + " : " + value + "<br>" );
	}
	%>
	
	<button type="button" class="btn" onclick="test();">토큰 인증 방식 성공 테스트</button>
</body>
</html>