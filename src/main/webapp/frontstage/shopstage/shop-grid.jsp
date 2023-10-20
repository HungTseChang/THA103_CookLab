<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.cooklab.product.model.*"%>
<%@ page import="java.util.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
List<ProductVO> productList = (List<ProductVO>) request.getAttribute("productList");
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
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/frontstage/shopstage/css/my.css"
	type="text/css" />
<!-- bootstrap icon -->
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.0/font/bootstrap-icons.css" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/frontstage/shopstage/css/ding.css"
	type="text/css" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/frontstage/shopstage/css/dingsearch.css" />
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

	<!-- Breadcrumb Section Begin -->
	<section class="breadcrumb-section set-bg"
		data-setbg="img/breadcrumb.jpg">
		<div class="container">
			<div class="row">
				<div class="col-lg-12 text-center">
					<div class="breadcrumb__text">
						<h2>商城</h2>
					</div>
				</div>
			</div>
		</div>
	</section>
	<!-- Breadcrumb Section End -->

	<!-- Product Section Begin -->
	<section class="product spad">
		<div class="container">
			<div class="filter__item">
				<div class="row">
					<div class="col-lg-12 col-md-12">
						<div class="filter__found">
							<h6>
								<span>16</span> Products found
							</h6>
						</div>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-lg-3 col-md-5">
					<div class="sidebar">
						<div class="sidebar__item">
							<h4>商品分類</h4>
							<ul>
								<li><a href="#">蔬菜</a></li>
								<li><a href="#">肉類</a></li>
								<li><a href="#">魚肉</a></li>
								<li><a href="#">辛香料</a></li>
								<li><a href="#">廚具</a></li>
							</ul>
						</div>
					</div>
				</div>
				<div class="col-lg-9 col-md-7">
					<div class="row">
						<c:forEach var="product" items="${productList}">
							<div class="col-lg-4 col-md-4 col-sm-6 mix oranges fresh-meat">
								<div class="featured__item">
									<div class="featured__item__pic set-bg"
										data-setbg="<%= request.getContextPath() %>/ProductImgServlet?productNo=${product.productNo}">
										<ul class="featured__item__pic__hover">
											<li><a href="#"><i class="fa fa-shopping-cart"></i></a></li>
										</ul>
									</div>
									<div class="featured__item__text">
										<h6>
											<a
												href="<%= request.getContextPath() %>/ProductServlet2?action=getOne_For_Display&productNo=${product.productNo}">
												${product.productName} </a>
										</h6>
										<h5>${product.productPrice}</h5>
									</div>
								</div>
							</div>
						</c:forEach>



					</div>
				</div>
			</div>
			<div class="container d-flex">
				<nav aria-label="Page navigation example " class="mx-auto">
					<ul class="pagination mx-auto">
						<li class="page-item"><a class="page-link" href="#">Previous</a></li>
						<li class="page-item"><a class="page-link" href="#">1</a></li>
						<li class="page-item"><a class="page-link" href="#">2</a></li>
						<li class="page-item"><a class="page-link" href="#">3</a></li>
						<li class="page-item"><a class="page-link" href="#">Next</a></li>
					</ul>
				</nav>
			</div>
		</div>
	</section>

	<!-- Product Section End -->

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
</body>
</html>
