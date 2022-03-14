<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>GKMarket AdminPage</title>
</head>
<body>
	 <jsp:forward page="/MainView/indexControl">
 
        <jsp:param name="CONTROL" value="intro" />
        <jsp:param name="PAGE_NUM" value="01" />
 
    </jsp:forward>
</body>
</html>