<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<script src="https://kit.fontawesome.com/845ab7ea16.js"
	crossorigin="anonymous"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.4.0/Chart.min.js"></script>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body id="page-top">

	<!-- Begin Page Content -->
	<div class="container-fluid">

		<!-- Page Heading -->
		<div class="d-sm-flex align-items-center justify-content-between mb-4">
			<h1 class="h3 mb-0 text-gray-800">게시글 정보</h1>
		</div>

		<!-- Content Row -->
		<div class="row">

			<!-- Earnings (Monthly) Card Example -->
			<div class="col-xl-3 col-md-6 mb-4">
				<div class="card border-left-primary shadow h-100 py-2">
					<div class="card-body">
						<div class="row no-gutters align-items-center">
							<div class="col mr-2">
								<div
									class="text-xs font-weight-bold text-primary text-uppercase mb-1">
									전체게시글</div>

								<div class="h5 mb-0 font-weight-bold text-gray-800">
									<c:out value="${mv.boardCount }"></c:out>
								</div>
							</div>
							<div class="col-auto">
								<i class="fa-solid fa-0"></i>
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
									class="text-xs font-weight-bold text-success text-uppercase mb-1">
									판매중인 게시글</div>
								<div class="h5 mb-0 font-weight-bold text-gray-800">
									<c:out value="${mv.forSale }"></c:out>
								</div>
							</div>
							<div class="col-auto">
								<i class="fas fa-dollar-sign fa-2x text-gray-300"></i>
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
									class="text-xs font-weight-bold text-info text-uppercase mb-1">질문답변완료
								</div>
								<div class="h5 mb-0 mr-3 font-weight-bold text-gray-800">
									<c:out value="${mv.replyDone }"></c:out>
								</div>
							</div>
							<div class="col-auto">
								<i class="fas fa-clipboard-list fa-2x text-gray-300"></i>
							</div>
						</div>
					</div>
				</div>
			</div>

			<!-- Pending Requests Card Example -->
			<div class="col-xl-3 col-md-6 mb-4">
				<div class="card border-left-warning shadow h-100 py-2">
					<div class="card-body">
						<div class="row no-gutters align-items-center">
							<div class="col mr-2">
								<div
									class="text-xs font-weight-bold text-warning text-uppercase mb-1">
									판매완료된 게시글</div>
								<div class="h5 mb-0 font-weight-bold text-gray-800">
									<c:out value="${mv.soldOut }"></c:out>
								</div>
							</div>
							<div class="col-auto">
								<i class="fas fa-comments fa-2x text-gray-300"></i>
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
		const labels = [ 'January', 'February', 'March', 'April', 'May',
				'June', ];

		const data = {
			labels : labels,
			datasets : [ {
				label : 'My First dataset',
				backgroundColor : 'rgb(255, 99, 132)',
				borderColor : 'rgb(255, 99, 132)',
				data : [ 0, 10, 5, 2, 20, 30, 45 ],
			} ]
		};

		const config = {
			type : 'line',
			data : data,
			options : {}
		};

		const myChart = new Chart(document.getElementById('myChart'), config);
	</script>

	<script>
		const dataDoughtnut = {
			labels : [ 'Red', 'Blue', 'Yellow' ],
			datasets : [ {
				label : 'My First Dataset',
				data : [ 300, 50, 100 ],
				backgroundColor : [ 'rgb(255, 99, 132)', 'rgb(54, 162, 235)',
						'rgb(255, 205, 86)' ],
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