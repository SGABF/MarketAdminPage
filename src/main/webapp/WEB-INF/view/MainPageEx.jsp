<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<script src="https://kit.fontawesome.com/845ab7ea16.js"
	crossorigin="anonymous"></script>
	
<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.4.0/Chart.min.js"></script>
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

		<div class="row">

			<div style="width: 900px; height: 900px;">
				<!--차트가 그려질 부분-->
				<canvas id="myChart"></canvas>
			</div>
			<!-- Pie Chart -->
			<div class="col-xl-4 col-lg-5">
				<div class="card shadow mb-4">
					<!-- Card Header - Dropdown -->
					<div
						class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
						<h6 class="m-0 font-weight-bold text-primary">Revenue Sources</h6>
						<div class="dropdown no-arrow">
							<a class="dropdown-toggle" href="#" role="button"
								id="dropdownMenuLink" data-toggle="dropdown"
								aria-haspopup="true" aria-expanded="false"> <i
								class="fas fa-ellipsis-v fa-sm fa-fw text-gray-400"></i>
							</a>
							<div
								class="dropdown-menu dropdown-menu-right shadow animated--fade-in"
								aria-labelledby="dropdownMenuLink">
								<div class="dropdown-header">Dropdown Header:</div>
								<a class="dropdown-item" href="#">Action</a> <a
									class="dropdown-item" href="#">Another action</a>
								<div class="dropdown-divider"></div>
								<a class="dropdown-item" href="#">Something else here</a>
							</div>
						</div>
					</div>


					<!-- Card Body -->
					<div class="card-body">
						<div class="chart-pie pt-4 pb-2">
							<canvas id="myPieChart"></canvas>
						</div>
						<div class="mt-4 text-center small">
							<span class="mr-2"> <i class="fas fa-circle text-primary"></i>
								Direct
							</span> <span class="mr-2"> <i class="fas fa-circle text-success"></i>
								Social
							</span> <span class="mr-2"> <i class="fas fa-circle text-info"></i>
								Referral
							</span>
						</div>
					</div>
				</div>
			</div>
		</div>

		<!-- Content Row -->
		<div class="row">

			<!-- Content Column -->
			<div class="col-lg-6 mb-4">

				<!-- Project Card Example -->
				<div class="card shadow mb-4">
					<div class="card-header py-3">
						<h6 class="m-0 font-weight-bold text-primary">Projects</h6>
					</div>
					<div class="card-body">
						<h4 class="small font-weight-bold">
							Server Migration <span class="float-right">20%</span>
						</h4>
						<div class="progress mb-4">
							<div class="progress-bar bg-danger" role="progressbar"
								style="width: 20%" aria-valuenow="20" aria-valuemin="0"
								aria-valuemax="100"></div>
						</div>
						<h4 class="small font-weight-bold">
							Sales Tracking <span class="float-right">40%</span>
						</h4>
						<div class="progress mb-4">
							<div class="progress-bar bg-warning" role="progressbar"
								style="width: 40%" aria-valuenow="40" aria-valuemin="0"
								aria-valuemax="100"></div>
						</div>
						<h4 class="small font-weight-bold">
							Customer Database <span class="float-right">60%</span>
						</h4>
						<div class="progress mb-4">
							<div class="progress-bar" role="progressbar" style="width: 60%"
								aria-valuenow="60" aria-valuemin="0" aria-valuemax="100"></div>
						</div>
						<h4 class="small font-weight-bold">
							Payout Details <span class="float-right">80%</span>
						</h4>
						<div class="progress mb-4">
							<div class="progress-bar bg-info" role="progressbar"
								style="width: 80%" aria-valuenow="80" aria-valuemin="0"
								aria-valuemax="100"></div>
						</div>
						<h4 class="small font-weight-bold">
							Account Setup <span class="float-right">Complete!</span>
						</h4>
						<div class="progress">
							<div class="progress-bar bg-success" role="progressbar"
								style="width: 100%" aria-valuenow="100" aria-valuemin="0"
								aria-valuemax="100"></div>
						</div>
					</div>
				</div>

				var context = document
                .getElementById('myChart')
                .getContext('2d');
            var myChart = new Chart(context, { //chart내용 }
				<!-- Color System -->
				<div class="row">
					<div class="col-lg-6 mb-4">
						<div class="card bg-primary text-white shadow">
							<div class="card-body">
								Primary
								<div class="text-white-50 small">#4e73df</div>
							</div>
						</div>
					</div>
					<div class="col-lg-6 mb-4">
						<div class="card bg-success text-white shadow">
							<div class="card-body">
								Success
								<div class="text-white-50 small">#1cc88a</div>
							</div>
						</div>
					</div>
					<div class="col-lg-6 mb-4">
						<div class="card bg-info text-white shadow">
							<div class="card-body">
								Info
								<div class="text-white-50 small">#36b9cc</div>
							</div>
						</div>
					</div>
					<div class="col-lg-6 mb-4">
						<div class="card bg-warning text-white shadow">
							<div class="card-body">
								Warning
								<div class="text-white-50 small">#f6c23e</div>
							</div>
						</div>
					</div>
					<div class="col-lg-6 mb-4">
						<div class="card bg-danger text-white shadow">
							<div class="card-body">
								Danger
								<div class="text-white-50 small">#e74a3b</div>
							</div>
						</div>
					</div>
					<div class="col-lg-6 mb-4">
						<div class="card bg-secondary text-white shadow">
							<div class="card-body">
								Secondary
								<div class="text-white-50 small">#858796</div>
							</div>
						</div>
					</div>
					<div class="col-lg-6 mb-4">
						<div class="card bg-light text-black shadow">
							<div class="card-body">
								Light
								<div class="text-black-50 small">#f8f9fc</div>
							</div>
						</div>
					</div>
					<div class="col-lg-6 mb-4">
						<div class="card bg-dark text-white shadow">
							<div class="card-body">
								Dark
								<div class="text-white-50 small">#5a5c69</div>
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
							Custom CSS classes are used to create custom components and
							custom utility classes.</p>
						<p class="mb-0">Before working with this theme, you should
							become familiar with the Bootstrap framework, especially the
							utility classes.</p>
					</div>
				</div>

			</div>
		</div>

	</div>
	<!-- /.container-fluid -->

	<!-- Page level plugins -->
	<script type="text/javascript">
            var context = document
                .getElementById('myChart')
                .getContext('2d');
            var myChart = new Chart(context, {
                type: 'bar', // 차트의 형태
                data: { // 차트에 들어갈 데이터
                    labels: [
                        //x 축
                        '1','2','3','4','5','6','7'
                    ],
                    datasets: [
                        { //데이터
                            label: 'test1', //차트 제목
                            fill: false, // line 형태일 때, 선 안쪽을 채우는지 안채우는지
                            data: [
                                21,19,25,20,23,26,25 //x축 label에 대응되는 데이터 값
                            ],
                            backgroundColor: [
                                //색상
                                'rgba(255, 99, 132, 0.2)',
                                'rgba(54, 162, 235, 0.2)',
                                'rgba(255, 206, 86, 0.2)',
                                'rgba(75, 192, 192, 0.2)',
                                'rgba(153, 102, 255, 0.2)',
                                'rgba(255, 159, 64, 0.2)'
                            ],
                            borderColor: [
                                //경계선 색상
                                'rgba(255, 99, 132, 1)',
                                'rgba(54, 162, 235, 1)',
                                'rgba(255, 206, 86, 1)',
                                'rgba(75, 192, 192, 1)',
                                'rgba(153, 102, 255, 1)',
                                'rgba(255, 159, 64, 1)'
                            ],
                            borderWidth: 1 //경계선 굵기
                        }/* ,
                        {
                            label: 'test2',
                            fill: false,
                            data: [
                                8, 34, 12, 24
                            ],
                            backgroundColor: 'rgb(157, 109, 12)',
                            borderColor: 'rgb(157, 109, 12)'
                        } */
                    ]
                },
                options: {
                    scales: {
                        yAxes: [
                            {
                                ticks: {
                                    beginAtZero: true
                                }
                            }
                        ]
                    }
                }
            });
        </script>
	<script src="../static/vendor/chart.js/Chart.min.js"></script>

	<!-- Page level custom scripts -->
	<script src="../static/js/demo/chart-area-demo.js"></script>
	<script src="../static/js/demo/chart-pie-demo.js"></script>

</body>
</html>