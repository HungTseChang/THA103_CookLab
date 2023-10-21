<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.cooklab.product.model.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
ProductVO productVO = (ProductVO) request.getAttribute("productVO"); //EmpServlet.java(Concroller), 存入req的empVO物件
%>
<!DOCTYPE html>
<html lang="zxx">

<head>
<meta charset="UTF-8">
<meta name="description" content="Ogani Template">
<meta name="keywords" content="Ogani, unica, creative, html">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>Ogani | Template</title>

<!-- Google Font -->
<link
	href="https://fonts.googleapis.com/css2?family=Cairo:wght@200;300;400;600;900&display=swap"
	rel="stylesheet">

<!-- Css Styles -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/frontstage/shopstage/css/bootstrap.min.css"
	type="text/css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/frontstage/shopstage/css/font-awesome.min.css"
	type="text/css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/frontstage/shopstage/css/elegant-icons.css"
	type="text/css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/frontstage/shopstage/css/nice-select.css"
	type="text/css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/frontstage/shopstage/css/jquery-ui.min.css"
	type="text/css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/frontstage/shopstage/css/owl.carousel.min.css"
	type="text/css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/frontstage/shopstage/css/slicknav.min.css"
	type="text/css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/frontstage/shopstage/css/style.css"
	type="text/css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/frontstage/shopstage/css/my.css"
	type="text/css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/frontstage/shopstage/css/footer.css">
<!-- bootstrap icon -->
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.0/font/bootstrap-icons.css" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/frontstage/shopstage/css/ding.css"
	type="text/css" />
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
							src="<%=request.getContextPath() %>/frontstage/shopstage/img/indexlogo.png" alt="" /></a>
					</div>
				</div>
				<div class="col-lg-9 d-flex align-items-center">
					<nav class="header__menu">
						<ul>
							<li class="active"><a href="./index.html">食譜總覽</a></li>
							<li><a href="#">新增食譜</a></li>
							<li><a href="#">關注食譜</a></li>
							<li><a href="./shop-grid.html">商城</a></li>
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
	<section class="hero hero-normal" id="rs_hero_felx">
		<div class="container">
			<div class="row">
				<div class="col-lg-2"></div>
				<div class="col-lg-2">
					<div class="hero__categories">
						<div class="hero__categories__all" id="rs_categories_all">
							<i class="fa fa-bars"></i> <span>總類</span>
						</div>
						<ul>
							<li><a href="#">蔬菜</a></li>
							<li><a href="#">肉</a></li>
							<li><a href="#">魚</a></li>
							<li><a href="#">辛香料</a></li>
							<li><a href="#">廚具</a></li>
						</ul>
					</div>
				</div>
				<div class="col-lg-6">
					<div class="hero__search" id="rs_hero_search">
						<div class="hero__search__form " id="rs_search_form">
							<form action="#">
								<input type="text" placeholder="What do yo u need?">
								<button type="submit" class="site-btn">SEARCH</button>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>
	<!-- Hero Section End -->

	<!-- Breadcrumb Section Begin -->
	<section class="breadcrumb-section set-bg"
		data-setbg="<%=request.getContextPath()%>/frontstage/shopstage/img/breadcrumb.jpg">
		<div class="container">
			<div class="row">
				<div class="col-lg-12 text-center">
					<div class="breadcrumb__text">
						<h2>商品頁面</h2>
					</div>
				</div>
			</div>
		</div>
	</section>
	<!-- Breadcrumb Section End -->

	<!-- Product Details Section Begin -->
	<section class="product-details spad">
		<div class="container">
			<div class="row">
				<div class="col-lg-6 col-md-6">
					<div class="product__details__pic">
						<div class="product__details__pic__item"
							id="rs_product__details__pic__item">
							<img class="product__details__pic__item--large"
								src="<%= request.getContextPath() %>/ProductImgServlet?productNo=${productVO.productNo}"
								alt="">
						</div>
					</div>
				</div>
				<div class="col-lg-6 col-md-6">
					<div class="product__details__text">
						<h3>${productVO.productName}</h3>
						<div class="product__details__rating"></div>
						<div class="product__details__price">${productVO.productPrice}</div>
						<p>${productVO.productDec}</p>
						<div class="product__details__quantity">
							<div class="quantity">
								<div class="pro-qty">
									<input type="text" value="1" id="quantityInput">
								</div>
							</div>
						</div>
						<a href="#" class="primary-btn" id="addtocart"
							data-product-no="${productVO.productNo}"
							data-product-name="${productVO.productName}"
							data-product-price="${productVO.productPrice}"
							data-product-image="<%= request.getContextPath() %>/ProductImgServlet?productNo=${productVO.productNo}">加入購物車</a> <a
							href="#" class="primary-btn">直接購買</a>
					</div>
				</div>
				<div class="col-lg-12">
					<div class="product__details__tab">
						<ul class="nav nav-tabs" role="tablist">
							<li class="nav-item"><a class="nav-link active"
								data-toggle="tab" href="#tabs-1" role="tab" aria-selected="true">商品介紹</a>
							</li>
						</ul>
						<div class="tab-content">
							<div class="tab-pane active" id="tabs-1" role="tabpanel">
								<div class="product__details__tab__desc">
									<h6>商品詳情</h6>
									<p>${productVO.productIntroduction}</p>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>
	<!-- Product Details Section End -->


	<!-- Footer Section Begin -->
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
	<!-- Footer Section End -->

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
	<script
		src="<%=request.getContextPath()%>/frontstage/shopstage/js/shop_sh.js"></script>


	<script>
		$("#addtocart").on("click", function() {
			// 获取产品信息
			let productNo = $(this).data("product-no");
			let productName = $(this).data("product-name");
			let productPrice = $(this).data("product-price");
			let productImage = $(this).data("product-image");
			let selectedQuantity = $("#quantityInput").val();
			// 构建产品信息对象
			let productInfo = {
				productNo : productNo,
				productName : productName,
				productPrice : productPrice,
				productImage : productImage,
				quantity : selectedQuantity,
				action : "buttonadd2"
			};

			console.log(productInfo); // 输出 productInfo 对象，确保数据正确
			$.ajax({
				url : "/CookLab/CartServlet", // 资料请求的网址
				type : "POST", // GET | POST | PUT | DELETE | PATCH
				data : productInfo, // 将对象数据（不需要双引号）发送到指定的 URL
				dataType : "json", // 预期会接收到返回数据的格式：json | xml | html
				beforeSend : function() {
					// 在请求发送之前执行
				},
				headers : {
				// 如果请求有请求头数据要设置的话
				// "X-CSRF-Token": "abcde" // 参考写法
				},
				statusCode : {
					// 状态码
					200 : function(res) {
					},
					404 : function(res) {
					},
					500 : function(res) {
					},
				},
				success : function(data) {
					// 请求成功取得响应后执行
					console.log(data);
					console.log("Ajax 成功");
					alert("添加商品成功");
				},
				error : function(xhr) {
					// 请求发生错误的话执行
					console.log("请求失败，状态码：" + xhr.status);
					console.log(xhr.responseText);
					console.log(xhr);
					alert("添加到购物车时发生错误，请重试。");
				},
				complete : function(xhr) {
					// 请求完成之后执行（在 success / error 事件之后执行）
					console.log(xhr);
				},
			});
		});
	</script>

</body>

</html>