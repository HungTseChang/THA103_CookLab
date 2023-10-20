<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.cooklab.product.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
ProductService productSvc = new ProductService();
List<ProductVO> list = productSvc.getAll();
pageContext.setAttribute("list", list);
%>
<!DOCTYPE html>
<html lang="zxx">
<head>
<meta charset="UTF-8" />
<meta name="description" content="Ogani Template" />
<meta name="keywords" content="Ogani, unica, creative, html" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<meta http-equiv="X-UA-Compatible" content="ie=edge" />
<title>Ogani | Template</title>

<!-- Google Font -->
<link
	href="https://fonts.googleapis.com/css2?family=Cairo:wght@200;300;400;600;900&display=swap"
	rel="stylesheet" />

<!-- Css Styles -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/frontstage/shopstage/css/bootstrap.min.css"
	type="text/css" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/frontstage/shopstage/css/font-awesome.min.css"
	type="text/css" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/frontstage/shopstage/css/elegant-icons.css"
	type="text/css" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/frontstage/shopstage/css/nice-select.css"
	type="text/css" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/frontstage/shopstage/css/jquery-ui.min.css"
	type="text/css" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/frontstage/shopstage/css/owl.carousel.min.css"
	type="text/css" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/frontstage/shopstage/css/slicknav.min.css"
	type="text/css" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/frontstage/shopstage/css/style.css"
	type="text/css" />
<!-- bootstrap icon -->
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.0/font/bootstrap-icons.css" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/frontstage/shopstage/css/ding.css"
	type="text/css" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/frontstage/shopstage/css/dingsearch.css" />

<style>
.carousel-image {
	max-width: 30%; /* 设置图片最大宽度为100% */
	height: auto; /* 自动计算图片高度，保持纵横比 */
	display: block; /* 设置图片为块级元素 */
	margin: 0 auto; /* 在水平方向上居中对齐 */
}
/* 自定义轮播左右按钮样式 */
.carousel-control-prev, .carousel-control-next {
	background-color: #ff0000; /* 修改按钮的背景颜色 */
	color: #ffffff; /* 修改按钮的文字颜色 */
	border: none; /* 移除按钮的边框 */
	border-radius: 50%; /* 设置按钮的边框半径以创建圆形按钮 */
	width: 40px; /* 设置按钮的宽度 */
	height: 40px; /* 设置按钮的高度 */
	font-size: 24px; /* 修改按钮上的文字大小 */
	line-height: 40px; /* 设置文字行高以垂直居中 */
	opacity: 0; /* 设置按钮的透明度 */
}

/* 自定义轮播左右按钮的悬停样式 */
.carousel-control-prev:hover, .carousel-control-next:hover {
	background-color: #ff5555; /* 修改悬停时的背景颜色 */
	color: #ffffff; /* 修改悬停时的文字颜色 */
	opacity: 1; /* 修改悬停时的透明度 */
}
/* 垂直居中按钮 */
.carousel-control-prev, .carousel-control-next {
	position: absolute; /* 设置按钮为绝对定位 */
	top: 50%; /* 上边距为容器高度的50% */
	transform: translateY(-50%); /* 使用负的垂直偏移来垂直居中 */
}

/* 调整按钮与轮播容器的水平距离 */
.carousel-control-prev {
	left: 10px; /* 左边距为10px，可根据需要调整 */
}

.carousel-control-next {
	right: 10px; /* 右边距为10px，可根据需要调整 */
}
</style>
</head>

<body>
	<!-- Page Preloder -->
	<div id="preloder">
		<div class="loader"></div>
	</div>

	<!-- Header Section Begin -->
	<header class="header">
		<div class="header__top">
			<div class="container">
				<div class="row">
					<div class="col-lg-6"></div>
					<div class="col-lg-6">
						<div class="header__top__right">
							<div class="header__top__right__auth">
								<a href="#"> <i class="bi bi-cart3 m-0 ml-2 fa-lg"></i> <span
									class="ding-nav-text">購物車</span>
								</a>
							</div>
							<div class="header__top__right__auth">
								<a href="#"> <i class="fa fa-user m-0 ml-2 fa-lg"></i> <span
									class="ding-nav-text">會員中心</span>
								</a>
							</div>
							<div class="header__top__right__auth">
								<a href="#" class="m-0 ml-2 ding-nav-text">登入/註冊</a>
							</div>
							<div class="header__top__right__auth">
								<a href="#"> <i class="bi bi-bell m-0 ml-2 fa-lg"></i> <span
									class="ding-nav-text">通知中心</span>
								</a>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="container">
			<div class="row">
				<div class="col-lg-3">
					<div class="header__logo">
						<a href="./index.html"><img style="height: 150px"
							src="img/indexlogo.png" alt="" /></a>
					</div>
				</div>
				<div class="col-lg-9 d-flex align-items-center">
					<nav class="header__menu">
						<ul>
							<li><a href="./index.html">食譜總覽</a></li>
							<li><a href="#">新增食譜</a></li>
							<li><a href="#">關注食譜</a></li>
							<li class="active"><a href="./shop-grid.html">商城</a></li>
							<li><a href="#">討論區</a></li>
							<li><a href="#">客服中心</a></li>
							<li><a href="./contact.html">關於我們</a></li>
						</ul>
					</nav>
				</div>
			</div>
			<div class="humberger__open">
				<i class="fa fa-bars"></i>
			</div>
		</div>
	</header>
	<!-- Header Section End -->

	<!-- Hero Section Begin -->
	<section class="container mb-3">
		<main class="searchbar-block-shop border rounded d-flex">
			<!-- 搜尋框起始 -->
			<form class="d-flex searchbar-form-shop">
				<input type="text"
					class="form-control border-0 searchbar-input-shop"
					id="index-searchbar" aria-describedby="searchbar"
					placeholder="輸入想搜尋的商品" />
				<button type="button" class="btn ding-btn-org searchbar-btn-shop">
					<i class="bi bi-search"></i>
				</button>
			</form>
			<!-- 搜尋框結束 -->
		</main>
		<!-- 熱搜關鍵字起始 -->
		<div class="topsearchwords-main-shop">
			<div class="topsearchwords-title">熱門搜尋：</div>
			<ul class="topsearchwords-menu">
				<li class="topsearchwords-item"><a href="#">紅酒杯</a></li>
				<li class="topsearchwords-item"><a href="#">不鏽鋼鍋</a></li>
				<li class="topsearchwords-item"><a href="#">咖啡機</a></li>
				<li class="topsearchwords-item"><a href="#">燒烤爐</a></li>
				<li class="topsearchwords-item"><a href="#">壓力鍋</a></li>
				<li class="topsearchwords-item"><a href="#">研磨機</a></li>
				<li class="topsearchwords-item"><a href="#">番茄</a></li>
				<li class="topsearchwords-item"><a href="#">木瓜</a></li>
				<li class="topsearchwords-item"><a href="#">玉米</a></li>
				<li class="topsearchwords-item"><a href="#">鮭魚</a></li>
			</ul>
		</div>
		<!-- 熱搜關鍵字結束 -->
	</section>
	<!-- Hero Section End -->

	<!-- 商品輪播圖(廣告區) Section Begin -->
	<section>
		<div class="container" style="margin-top: 50px">
			<div class="row">
				<div class="col-12">
					<div id="carouselExampleCaptions" class="carousel slide"
						data-ride="carousel">
						<ol class="carousel-indicators">
							<li data-target="#carouselExampleCaptions" data-slide-to="0"
								class="active"></li>
							<li data-target="#carouselExampleCaptions" data-slide-to="1"></li>
							<li data-target="#carouselExampleCaptions" data-slide-to="2"></li>
						</ol>
						<div class="carousel-inner">
							<div class="carousel-item active">
								<img src="./img/cart/cart-1.jpg"
									class="d-block w-100 carousel-image" alt="..." />
								<div class="carousel-caption d-none d-md-block">
									<h5>First slide label</h5>
									<p>Some representative placeholder content for the first
										slide.</p>
								</div>
							</div>
							<div class="carousel-item">
								<img src="./img/cart/cart-2.jpg"
									class="d-block w-100 carousel-image" alt="..." />
								<div class="carousel-caption d-none d-md-block">
									<h5>Second slide label</h5>
									<p>Some representative placeholder content for the second
										slide.</p>
								</div>
							</div>
							<div class="carousel-item">
								<img src="./img/cart/cart-3.jpg"
									class="d-block w-100 carousel-image" alt="..." />
								<div class="carousel-caption d-none d-md-block">
									<h5>Third slide label</h5>
									<p>Some representative placeholder content for the third
										slide.</p>
								</div>
							</div>
						</div>
						<button class="carousel-control-prev" type="button"
							data-target="#carouselExampleCaptions" data-slide="prev">
							<span class="carousel-control-prev-icon" aria-hidden="true"></span>
							<span class="sr-only">Previous</span>
						</button>
						<button class="carousel-control-next" type="button"
							data-target="#carouselExampleCaptions" data-slide="next">
							<span class="carousel-control-next-icon" aria-hidden="true"></span>
							<span class="sr-only">Next</span>
						</button>
					</div>
				</div>
			</div>
		</div>
	</section>
	<!-- 商品輪播圖(廣告區) Section End -->

	<!-- Featured Section Begin -->
	<section class="featured spad">
		<div class="container">
			<div class="row">
				<div class="col-lg-12">
					<div class="section-title">
						<h2>商品</h2>
					</div>
				</div>
			</div>
			<div class="row featured__filter">
				<c:forEach var="productVO" items="${list}">
					<div class="col-lg-4 col-md-4 col-sm-6 mix oranges fresh-meat">
						<div class="featured__item">
							<div class="featured__item__pic set-bg"
								data-setbg="<%= request.getContextPath() %>/ProductImgServlet?productNo=${productVO.productNo}">
								<ul class="featured__item__pic__hover">
									<li><a href="#"><i class="fa fa-shopping-cart"></i></a></li>
								</ul>
							</div>
							<div class="featured__item__text">
								<h6>
									<a href="#">${productVO.productName}</a>
								</h6>
								<h5>${productVO.productPrice}</h5>
							</div>
						</div>
					</div>
				</c:forEach>
				<div class="col-lg-4 col-md-4 col-sm-6 mix fastfood vegetables">
					<div class="featured__item">
						<div class="featured__item__pic set-bg"
							data-setbg="img/featured/feature-8.jpg">
							<ul class="featured__item__pic__hover">
								<li><a href="#"><i class="fa fa-shopping-cart"></i></a></li>
							</ul>
						</div>
						<div class="featured__item__text">
							<h6>
								<a href="#">Crab Pool Security</a>
							</h6>
							<h5>$30.00</h5>
						</div>
					</div>
				</div>
				<div class="col-lg-4 col-md-4 col-sm-6 mix fastfood vegetables">
					<div class="featured__item">
						<div class="featured__item__pic set-bg"
							data-setbg="img/featured/feature-8.jpg">
							<ul class="featured__item__pic__hover">
								<li><a href="#"><i class="fa fa-shopping-cart"></i></a></li>
							</ul>
						</div>
						<div class="featured__item__text">
							<h6>
								<a href="#">Crab Pool Security</a>
							</h6>
							<h5>$30.00</h5>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>
	<!-- Featured Section End -->

	<footer class="footer spad">
		<div class="container">
			<div class="row">
				<div class="col-lg-4">
					<div class="footer__about">
						<ul>
							<li>公司地址：台北市中山區南京東路三段219號5樓</li>
							<li>電話：(02)27120589</li>
							<li>電子信箱：tomato@cooklab.com</li>
						</ul>
					</div>
				</div>
				<div class="col-lg-4">
					<div class="footer__widget">
						<ul>
							<li><a href="#">關於我們</a></li>
							<li><a href="#">關於商店</a></li>
							<li><a href="#">安心購物</a></li>
						</ul>
						<ul>
							<li><a href="#">運送資訊</a></li>
							<li><a href="#">隱私政策</a></li>
							<li><a href="#">常見問題</a></li>
						</ul>
					</div>
				</div>
				<div class="col-lg-4 col-md-12">
					<div class="footer__widget">
						<h6>立即訂閱電子報</h6>
						<p>隨時接收我們的最新消息以及優惠訊息</p>
						<form action="#">
							<input type="text" placeholder="輸入您的電子信箱" />
							<button type="submit" class="btn ding-btn-org">訂閱</button>
						</form>
						<div class="footer__widget__social">
							<a href="#"><i class="fa fa-facebook"></i></a> <a href="#"><i
								class="fa fa-instagram"></i></a> <a href="#"><i
								class="fa fa-twitter"></i></a>
						</div>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-lg-12">
					<div class="footer__copyright">
						<div class="footer__copyright__text">
							<p>
								<!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
								Copyright &copy;
								<script>
									document.write(new Date().getFullYear());
								</script>
								All rights reserved | This template is made with <i
									class="fa fa-heart" aria-hidden="true"></i> by <a
									href="https://colorlib.com" target="_blank">Colorlib</a>
								<!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
							</p>
						</div>
					</div>
				</div>
			</div>
		</div>
	</footer>

	<!-- Js Plugins -->
	<script
		src="<%=request.getContextPath()%>/frontstage/shopstage/js/jquery-3.3.1.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/frontstage/shopstage/js/bootstrap.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/frontstage/shopstage/js/jquery.nice-select.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/frontstage/shopstage/js/jquery-ui.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/frontstage/shopstage/js/jquery.slicknav.js"></script>
	<script
		src="<%=request.getContextPath()%>/frontstage/shopstage/js/mixitup.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/frontstage/shopstage/js/owl.carousel.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/frontstage/shopstage/js/main.js"></script>

	<script>
		// 获取轮播元素
		const carousel = document.getElementById("carouselExampleCaptions");

		// 初始化轮播
		const myCarousel = new bootstrap.Carousel(carousel, {
			interval : 3000, // 设置自动播放的间隔时间（以毫秒为单位），这里是3秒
			pause : "hover", // 鼠标悬停时暂停自动播放
		});

		// 启用自动播放
		myCarousel.cycle();
	</script>
</body>
</html>
