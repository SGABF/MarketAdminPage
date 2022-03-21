<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<fmt:requestEncoding value="UTF-8"/>
<!DOCTYPE html>
<html lang="ko">

<head>

    <title>AdminAccount</title>

<style type="text/css">
	.col{
		text-align: right;
	}
	
	#adminTable{
		border: 3px solid black;
		margin: 5px;
		padding: 5px;
		width: 100%
	}
</style>
</head>

<body id="page-top">

	<!-- Begin Page Content -->
	<div class="container-fluid">
		<!-- Page Heading -->
		<h1 class="h3 mb-4 text-gray-800">관리자 계정 설정 페이지</h1>
		<input type="button" id="addAdminAccount" class="btn btn-warning" value="계정생성"/>
		<table id="adminTable">
			<tr>
				<th>No</hd>
				<th>계정명</th>
				<th>권한</th>
				<th>Control</th>
			</tr>
			<tr id="adminAccount">
				
			</tr>
		</table>
	</div>
	<!-- /.container-fluid -->
	                       
</body>

</html>