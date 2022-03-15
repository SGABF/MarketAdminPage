<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<!DOCTYPE html>
<html>
<head>
	<title>
		<sitemesh:write property='title' />
	</title>
	<meta http-equiv="X-UA-Compatible" content="IE=edge" />
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<sitemesh:write property='head' />
</head>
<body>
	<div>데코레이터 확인용</div>
	<sitemesh:write property='body' />
</body>
</html>