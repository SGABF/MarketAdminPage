<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>


<!DOCTYPE html>
<html>
<%-- 부트스트랩을 사용하기 위한 준비 시작 --%>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.7.1/font/bootstrap-icons.css">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<%-- 부트스트랩을 사용하기 위한 준비 끝 --%>
<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.4.0/Chart.min.js"></script>
<head>
<script type="text/javascript">
// <![CDATA[
var colour="random"; // in addition to "random" can be set to any valid colour eg "#f0f" or "red"
var sparkles=50;

/****************************
*  Tinkerbell Magic Sparkle *
*(c)2005-13 mf2fm web-design*
*  http://www.mf2fm.com/rv  *
* DON'T EDIT BELOW THIS BOX *
****************************/
var x=ox=400;
var y=oy=300;
var swide=800;
var shigh=600;
var sleft=sdown=0;
var tiny=new Array();
var star=new Array();
var starv=new Array();
var starx=new Array();
var stary=new Array();
var tinyx=new Array();
var tinyy=new Array();
var tinyv=new Array();

window.onload=function() { if (document.getElementById) {
  var i, rats, rlef, rdow;
  for (var i=0; i<sparkles; i++) {
    var rats=createDiv(3, 3);
    rats.style.visibility="hidden";
    rats.style.zIndex="999";
    document.body.appendChild(tiny[i]=rats);
    starv[i]=0;
    tinyv[i]=0;
    var rats=createDiv(5, 5);
    rats.style.backgroundColor="transparent";
    rats.style.visibility="hidden";
    rats.style.zIndex="999";
    var rlef=createDiv(1, 5);
    var rdow=createDiv(5, 1);
    rats.appendChild(rlef);
    rats.appendChild(rdow);
    rlef.style.top="2px";
    rlef.style.left="0px";
    rdow.style.top="0px";
    rdow.style.left="2px";
    document.body.appendChild(star[i]=rats);
  }
  set_width();
  sparkle();
}}

function sparkle() {
  var c;
  if (Math.abs(x-ox)>1 || Math.abs(y-oy)>1) {
    ox=x;
    oy=y;
    for (c=0; c<sparkles; c++) if (!starv[c]) {
      star[c].style.left=(starx[c]=x)+"px";
      star[c].style.top=(stary[c]=y+1)+"px";
      star[c].style.clip="rect(0px, 5px, 5px, 0px)";
      star[c].childNodes[0].style.backgroundColor=star[c].childNodes[1].style.backgroundColor=(colour=="random")?newColour():colour;
      star[c].style.visibility="visible";
      starv[c]=50;
      break;
    }
  }
  for (c=0; c<sparkles; c++) {
    if (starv[c]) update_star(c);
    if (tinyv[c]) update_tiny(c);
  }
  setTimeout("sparkle()", 40);
}

function update_star(i) {
  if (--starv[i]==25) star[i].style.clip="rect(1px, 4px, 4px, 1px)";
  if (starv[i]) {
    stary[i]+=1+Math.random()*3;
    starx[i]+=(i%5-2)/5;
    if (stary[i]<shigh+sdown) {
      star[i].style.top=stary[i]+"px";
      star[i].style.left=starx[i]+"px";
    }
    else {
      star[i].style.visibility="hidden";
      starv[i]=0;
      return;
    }
  }
  else {
    tinyv[i]=50;
    tiny[i].style.top=(tinyy[i]=stary[i])+"px";
    tiny[i].style.left=(tinyx[i]=starx[i])+"px";
    tiny[i].style.width="2px";
    tiny[i].style.height="2px";
    tiny[i].style.backgroundColor=star[i].childNodes[0].style.backgroundColor;
    star[i].style.visibility="hidden";
    tiny[i].style.visibility="visible"
  }
}

function update_tiny(i) {
  if (--tinyv[i]==25) {
    tiny[i].style.width="1px";
    tiny[i].style.height="1px";
  }
  if (tinyv[i]) {
    tinyy[i]+=1+Math.random()*3;
    tinyx[i]+=(i%5-2)/5;
    if (tinyy[i]<shigh+sdown) {
      tiny[i].style.top=tinyy[i]+"px";
      tiny[i].style.left=tinyx[i]+"px";
    }
    else {
      tiny[i].style.visibility="hidden";
      tinyv[i]=0;
      return;
    }
  }
  else tiny[i].style.visibility="hidden";
}

document.onmousemove=mouse;
function mouse(e) {
  if (e) {
    y=e.pageY;
    x=e.pageX;
  }
  else {
    set_scroll();
    y=event.y+sdown;
    x=event.x+sleft;
  }
}

window.onscroll=set_scroll;
function set_scroll() {
  if (typeof(self.pageYOffset)=='number') {
    sdown=self.pageYOffset;
    sleft=self.pageXOffset;
  }
  else if (document.body && (document.body.scrollTop || document.body.scrollLeft)) {
    sdown=document.body.scrollTop;
    sleft=document.body.scrollLeft;
  }
  else if (document.documentElement && (document.documentElement.scrollTop || document.documentElement.scrollLeft)) {
    sleft=document.documentElement.scrollLeft;
    sdown=document.documentElement.scrollTop;
  }
  else {
    sdown=0;
    sleft=0;
  }
}

window.onresize=set_width;
function set_width() {
  var sw_min=999999;
  var sh_min=999999;
  if (document.documentElement && document.documentElement.clientWidth) {
    if (document.documentElement.clientWidth>0) sw_min=document.documentElement.clientWidth;
    if (document.documentElement.clientHeight>0) sh_min=document.documentElement.clientHeight;
  }
  if (typeof(self.innerWidth)=='number' && self.innerWidth) {
    if (self.innerWidth>0 && self.innerWidth<sw_min) sw_min=self.innerWidth;
    if (self.innerHeight>0 && self.innerHeight<sh_min) sh_min=self.innerHeight;
  }
  if (document.body.clientWidth) {
    if (document.body.clientWidth>0 && document.body.clientWidth<sw_min) sw_min=document.body.clientWidth;
    if (document.body.clientHeight>0 && document.body.clientHeight<sh_min) sh_min=document.body.clientHeight;
  }
  if (sw_min==999999 || sh_min==999999) {
    sw_min=800;
    sh_min=600;
  }
  swide=sw_min;
  shigh=sh_min;
}

function createDiv(height, width) {
  var div=document.createElement("div");
  div.style.position="absolute";
  div.style.height=height+"px";
  div.style.width=width+"px";
  div.style.overflow="hidden";
  return (div);
}

function newColour() {
  var c=new Array();
  c[0]=255;
  c[1]=Math.floor(Math.random()*256);
  c[2]=Math.floor(Math.random()*(256-c[1]/2));
  c.sort(function(){return (0.5 - Math.random());});
  return ("rgb("+c[0]+", "+c[1]+", "+c[2]+")");
}
// ]]>
</script>
<%--폰트 어썸 아이콘 --%>
<script src="https://kit.fontawesome.com/845ab7ea16.js" crossorigin="anonymous"></script>
<%--폰트 어썸 아이콘 --%>
<meta charset="UTF-8">
<title>관리자 메인화면</title>
</head>
<body id="page-top">

	<!-- Begin Page Content -->
	<div class="container-fluid">
		
		<!-- Page Heading -->
		<div class="d-sm-flex align-items-center justify-content-between mb-4">
			<h1 class="h3 mb-0 text-gray-800">게시글 통계 </h1>
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
									class="text-sm font-weight-bold text-danger  text-uppercase mb-1">
									전체</div>

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
									class="text-sm font-weight-bold text-warning text-uppercase mb-1">
									판매중</div>
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
									class="text-sm font-weight-bold text-success text-uppercase mb-1">답변완료
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
									class="text-sm font-weight-bold text-primary text-uppercase mb-1">
									판매완료</div>
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
									class="text-sm font-weight-bold text-dark  text-uppercase mb-1">
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
									class="text-sm font-weight-bold text-info text-uppercase mb-1">
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
				backgroundColor : 'rgb(228, 88, 38)',
				borderColor : 'rgb(228, 88, 38)',
				data : ${mList},
			},
			{
				label : '등록게시글 현황',
				backgroundColor : 'rgb(63, 167, 150)',
				borderColor : 'rgb(63, 167, 150)',
				data : ${bList},
			}
			]
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