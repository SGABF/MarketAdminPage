<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>


<!DOCTYPE html>
<html>
<%-- 부트스트랩을 사용하기 위한 준비 시작 --%>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.7.1/font/bootstrap-icons.css">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<%-- 부트스트랩을 사용하기 위한 준비 끝 --%>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.4.0/Chart.min.js"></script>
<head>
<%--폰트 어썸 아이콘 --%>
<script src="https://kit.fontawesome.com/845ab7ea16.js" crossorigin="anonymous"></script>
<%--폰트 어썸 아이콘 --%>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body id="page-top">

	<!-- Begin Page Content -->
	<div class="container-fluid">
		
		<!-- Page Heading -->
		<div class="d-sm-flex align-items-center justify-content-between mb-4">
			<h1 class="h3 mb-0 text-gray-800">게시글 정보 </h1>
			<i class="fa fa-cog fa-spin fa-2x fa-fw"></i> 
		</div>

		<!-- Content Row -->
		<div class="row">

			<!-- Earnings (Monthly) Card Example -->
			<div class="col-xl-3 col-md-6 mb-4">
				<div class="card border-left-danger shadow h-100 py-2">
					<div class="card-body">
						<div class="row no-gutters align-items-center">
							<div class="col mr-2">
								<div
									class="text-xs font-weight-bold text-danger  text-uppercase mb-1">
									전체게시글</div>

								<div class="h5 mb-0 font-weight-bold text-gray-800">
									<c:out value="${mv.boardCount }"></c:out>
								</div>
							</div>
							<div class="col-auto">
								<i class="fa-solid fa-list fa-2x text-gray-600"></i>
							</div>
						</div>
					</div>
				</div>
			</div>

			<!-- Earnings (Monthly) Card Example -->
			<div class="col-xl-3 col-md-6 mb-4">
				<div class="card border-left-warning shadow h-100 py-2">
					<div class="card-body">
						<div class="row no-gutters align-items-center">
							<div class="col mr-2">
								<div
									class="text-xs font-weight-bold text-warning text-uppercase mb-1">
									판매중인 게시글</div>
								<div class="h5 mb-0 font-weight-bold text-gray-800">
									<c:out value="${mv.forSale }"></c:out>
								</div>
							</div>
							<div class="col-auto">
								<i class="fas fa-dollar-sign fa-2x text-gray-600"></i>
							</div>
						</div>
					</div>
				</div>
			</div>

			<!-- Earnings (Monthly) Card Example -->
			<div class="col-xl-3 col-md-6 mb-4">
				<div class="card border-left-success shadow h-100 py-2">
					<div class="card-body">
						<div class="row no-gutters align-items-center">
							<div class="col mr-2">
								<div
									class="text-xs font-weight-bold text-success text-uppercase mb-1">질문답변완료
								</div>
								<div class="h5 mb-0 mr-3 font-weight-bold text-gray-800">
									<c:out value="${mv.replyDone }"></c:out>
								</div>
							</div>
							<div class="col-auto">
								<i class="fa-solid fa-list-check fa-2x text-gray-600" ></i>
							</div>
						</div>
					</div>
				</div>
			</div>

			<!-- Pending Requests Card Example -->
			<div class="col-xl-3 col-md-6 mb-4">
				<div class="card border-left-primary shadow h-100 py-2">
					<div class="card-body">
						<div class="row no-gutters align-items-center">
							<div class="col mr-2">
								<div
									class="text-xs font-weight-bold text-primary text-uppercase mb-1">
									판매완료된 게시글</div>
								<div class="h5 mb-0 font-weight-bold text-gray-800">
									<c:out value="${mv.soldOut }"></c:out>
								</div>
							</div>
							<div class="col-auto">
								<i class="fa-regular fa-circle-check fa-2x text-gray-600"></i>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>

		<!-- Content Row -->

		<div class="d-sm-flex align-items-center justify-content-between mb-4">
			<h1 class="h3 mb-0 text-gray-800">회원 통계</h1>
		</div>

		<!-- Content Row -->
		
		<div class="row">

			<!-- Earnings (Monthly) Card Example -->
			<div class="col-xl-3 col-md-6 mb-4">
				<div class="card border-left-dark shadow h-100 py-2">
					<div class="card-body">
						<div class="row no-gutters align-items-center">
							<div class="col mr-2">
								<div
									class="text-xs font-weight-bold text-dark  text-uppercase mb-1">
									전체 회원</div>

								<div class="h5 mb-0 font-weight-bold text-gray-800">
									<c:out value="${allUser}"></c:out>
								</div>
							</div>
							<div class="col-auto">
								<i class="fa-solid fa-user-group fa-2x text-gray-550"></i>
							</div>
						</div>
					</div>
				</div>
			</div>

			<!-- Earnings (Monthly) Card Example -->
			<div class="col-xl-3 col-md-6 mb-4">
				<div class="card border-left-info shadow h-100 py-2">
					<div class="card-body">
						<div class="row no-gutters align-items-center">
							<div class="col mr-2">
								<div
									class="text-xs font-weight-bold text-info text-uppercase mb-1">
									불량 회원</div>
								<div class="h5 mb-0 font-weight-bold text-gray-800">
									<c:out value="${userBanned }"></c:out>
								</div>
							</div>
							<div class="col-auto">
								<i class="fa-solid fa-user-xmark fa-2x text-gray-550"></i>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		

		<div class="row">
			<!-- Area Chart -->
			<div class="col-xl-8 col-lg-7">
				<div class="card shadow mb-4">
					<!-- Card Body -->
					<div class="card-body">
						<div>
							<canvas id="myChart"></canvas>
						</div>
					</div>
				</div>
			</div>

			<!-- Doghtnut Chart -->
			<div class="col-xl-4 col-lg-6">
				<div class="card shadow mb-4">
					<!-- Card Body -->
					<div class="card-body">
						<div class="doghtnut pt-4 pb-2">
							<canvas id="myDoghtnutChart"></canvas>
						</div>
					</div>
				</div>
			</div>
		</div>


		<div class="col-lg-6 mb-4">

			<!-- Illustrations -->
			<div class="card shadow mb-4">
				<div class="card-header py-3">
					<h6 class="m-0 font-weight-bold text-primary">Illustrations</h6>
				</div>
				<div class="card-body">
					<div class="text-center">
						<img class="img-fluid px-3 px-sm-4 mt-3 mb-4"
							style="width: 25rem;"
							src="../static/img/undraw_posting_photo.svg" alt="...">
					</div>
					<p>
						Add some quality, svg illustrations to your project courtesy of <a
							target="_blank" rel="nofollow" href="https://undraw.co/">unDraw</a>,
						a constantly updated collection of beautiful svg images that you
						can use completely free and without attribution!
					</p>
					<a target="_blank" rel="nofollow" href="https://undraw.co/">Browse
						Illustrations on unDraw &rarr;</a>
				</div>
			</div>

			<!-- Approach -->
			<div class="card shadow mb-4">
				<div class="card-header py-3">
					<h6 class="m-0 font-weight-bold text-primary">Development
						Approach</h6>
				</div>
				<div class="card-body">
					<p>SB Admin 2 makes extensive use of Bootstrap 4 utility
						classes in order to reduce CSS bloat and poor page performance.
						Custom CSS classes are used to create custom components and custom
						utility classes.</p>
					<p class="mb-0">Before working with this theme, you should
						become familiar with the Bootstrap framework, especially the
						utility classes.</p>
				</div>
			</div>

		</div>
	</div>

	<!-- /.container-fluid -->

	<!-- Page level plugins -->
	<script src="../static/vendor/chart.js/Chart.min.js"></script>
	<!-- 그래프 테스트-->
	<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
	<script>
	
	
		const labels = [ '1월', '2월', '3월', '4월', '5월', '6월', '7월' , '8월', '9월', '10월', '11월', '12월' ];

		const dataChart = {
			labels : labels,
			datasets : [ {
				label : '회원가입 현황',
				backgroundColor : 'rgb(255, 182, 193)',
				borderColor : 'rgb(255, 182, 193)',
				data : ${mList},
			} ]
		};

		const config = {
			type : 'line',
			data : dataChart,
			options : {}
		};

		const myChart = new Chart(document.getElementById('myChart'), config);
	</script>

	<script>
		const dataDoughtnut = {
			labels : [ '20대', '30대', '40대', '50대', '60대이상' ],
			datasets : [ {
				label : 'My First Dataset',
				data : [ ${age.age_20}, ${age.age_30}, ${age.age_40}, ${age.age_50} , ${age.age_60} ],
				backgroundColor : [ 'rgb(107, 203, 119)','rgb(77, 150, 255)', 'rgb(255, 107, 107)', 'rgb(255, 217, 61)','rgb(0, 234, 211)' ],
				hoverOffset : 4
			} ]
		};
		const configDoughnut = {
			type : 'doughnut',
			data : dataDoughtnut,
		};

		const myDoghtnutChart = new Chart(document
				.getElementById('myDoghtnutChart'), configDoughnut);
	</script>

	<!-- Page level custom scripts -->
	<script src="../static/js/demo/chart-area-demo.js"></script>
	<script src="../static/js/demo/chart-pie-demo.js"></script>
</body>

</html>