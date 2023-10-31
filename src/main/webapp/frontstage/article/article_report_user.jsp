<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.cooklab.article.model.ArticleVO"%>
<%@ page import="com.cooklab.article_sub.model.*"%>
<%@ page import="com.cooklab.article_sub_reaction.model.*"%>
<%@ page import="com.cooklab.article_report.model.*"%>
<%@ page import="java.util.*"%>
<%
session.getAttribute("membersVO");
Object mem = session.getAttribute("userId" ); 
Integer userId= Integer.valueOf(mem.toString());
	//帶入文章查詢後的資料
    ArticleVO artVO = (ArticleVO) request.getAttribute("artVO");
	pageContext.setAttribute("artVO",artVO);//不透過這行set值型別就會是ArticleVO，送到後端也不能做處理
	
	//帶入回文 文章查詢後的資料
	ArticleSubVO artVO2 = (ArticleSubVO) request.getAttribute("artVO2");
	pageContext.setAttribute("artVO2",artVO2);
	
	//用來接收錯誤訊息
	ArticleReportVO repVO = (ArticleReportVO) request.getAttribute("repVO");
	pageContext.setAttribute("repVO",repVO);
	
	//主文用
    ArticleVO artErr = (ArticleVO) request.getAttribute("artErr");
	pageContext.setAttribute("artErr",artErr);
	//回文用
    ArticleSubVO artErr2 = (ArticleSubVO) request.getAttribute("artErr2");
	pageContext.setAttribute("artErr2",artErr2);
	
%>
<!DOCTYPE html>
<html lang="zxx">

<head>
<meta charset="UTF-8">
<meta name="description" content="Ogani Template">
<meta name="keywords" content="Ogani, unica, creative, html">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>HO_article_content</title>
<script>

</script>
<!-- Google Font -->
<link
	href="https://fonts.googleapis.com/css2?family=Cairo:wght@200;300;400;600;900&display=swap"
	rel="stylesheet">

<!-- Css Styles -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/frontstage/css/bootstrap.min.css"
	type="text/css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/frontstage/css/font-awesome.min.css"
	type="text/css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/frontstage/css/elegant-icons.css"
	type="text/css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/frontstage/css/nice-select.css"
	type="text/css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/frontstage/css/jquery-ui.min.css"
	type="text/css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/frontstage/css/owl.carousel.min.css"
	type="text/css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/frontstage/css/slicknav.min.css"
	type="text/css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/frontstage/css/style.css"
	type="text/css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/frontstage/article/css/HO.css" type="text/css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/frontstage/article/css/ding.css"
	type="text/css">
<style>
		a.wccA {
			border: 1px solid rgb(151, 135, 249);
			background-color: rgb(195, 241, 253);
			padding: 4px;
			border-radius: 20px;
		}

		td a.wcc {
			border: 1px solid rgb(151, 135, 249);
			background-color: rgb(195, 241, 253);
			padding: 4px;
			border-radius: 20px;
		}

		td input.wcc {
			border-radius: 20px;
		}

		td {
			white-space: nowrap;

		}

		td.wcc {
			text-align: center;
		}

		th {
			white-space: nowrap;
		}

		span.wcc {
			border: 1px solid rgb(151, 135, 249);
			background-color: rgb(195, 241, 253);
			border-radius: 20px;
		}
	</style>	

</head>

<body>
	<!-- Page Preloder -->
	<div id="preloder">
		<div class="loader"></div>
	</div>
</div>
	<!-- Humberger Begin -->
	<div class="humberger__menu__overlay"></div>
	<div class="humberger__menu__wrapper">
		<div class="humberger__menu__logo">
			<a href="#"><img src="img/indexlogo.png" alt="" /></a>
		</div>
		<section class="container">
			<div
				class="humberger__menu__widget row d-flex justify-content-between align-items-center">
				<div class="">
					<a href="#"> <i class="bi bi-cart3 fa-3x"></i>
					</a>
				</div>

				<div class="">
					<a href="#"> <i class="fa fa-user fa-3x"></i>
					</a>
				</div>

				<div class="">
					<a href="#"> <i class="bi bi-bell fa-3x"></i>
					</a>
				</div>
			</div>
		</section>
		<nav class="humberger__menu__nav mobile-menu">
			<ul>
				<li><a href="./index.html">首頁</a></li>
				<li><a href="<%=request.getContextPath()%>/frontstage/shopstage/shop-grid.html">商城</a></li>
				<li><a href="<%=request.getContextPath()%>/frontstage/recipe/recipe_overview.jsp">食譜總覽</a></li>
				<li><a href="<%=request.getContextPath()%>/frontstage/faq/faq.html">常見問題</a></li>
				<li class="active"><a href="<%=request.getContextPath()%>/frontstage/article/article_main.jsp">討論區</a></li>
				<li><a href="<%=request.getContextPath()%>/frontstage/news/news.html">最新消息</a></li>
			</ul>
		</nav>
		<div id="mobile-menu-wrap"></div>
		<div class="header__top__right__social">
			<a href="#"><i class="fa fa-2x fa-facebook"></i></a> <a href="#"><i
				class="fa fa-2x fa-instagram"></i></a> <a href="#"><i
				class="fa fa-2x fa-twitter"></i></a>
		</div>
		<div class="humberger__menu__contact">
			<ul>
				<li><i class="fa fa-envelope"></i> tomato@cooklab.com</li>
			</ul>
		</div>
	</div>
	<!-- Humberger End -->

	<!-- Header Section Begin -->
	<header class="header">
		<div class="header__top">
			<div class="container">
				<div class="row">
					<div class="col-lg-6"></div>
					<div class="col-lg-6">
						<div class="header__top__right">
							<div class="header__top__right__auth">
								<a href="<%=request.getContextPath()%>/frontstage/shopstage/shoping-cart.html"> <i class="bi bi-cart3 m-0 ml-2 fa-lg"></i> <span
									class="ding-nav-text">購物車</span>
								</a>
							</div>
							<div class="header__top__right__auth">
								<a href="<%=request.getContextPath()%>/frontstage/members/member-panel.jsp"> <i class="fa fa-user m-0 ml-2 fa-lg"></i> <span
									class="ding-nav-text">會員中心</span>
								</a>
							</div>
							<div class="header__top__right__auth">
								<a href="<%=request.getContextPath()%>/frontstage/members/login.html" class="m-0 ml-2 ding-nav-text">登入/註冊</a>
							</div>
							<div class="header__top__right__auth">
								<a href="<%=request.getContextPath()%>/frontstage/members/member-panel-news.html"> <i class="bi bi-bell m-0 ml-2 fa-lg"></i> <span
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
							<li><a href="<%=request.getContextPath()%>/frontstage/recipe/recipe_overview.jsp">食譜總覽</a></li>
							<li><a href="<%=request.getContextPath()%>/frontstage/recipe/recipe_create.jsp">新增食譜</a></li>
							<li><a href="<%=request.getContextPath()%>/frontstage/members/member-panel-follow.html">關注食譜</a></li>
							<li><a href="<%=request.getContextPath()%>/frontstage/shopstage/shop.html">商城</a></li>
							<li class="active"><a href="<%=request.getContextPath()%>/frontstage/article/article_main.jsp">討論區</a></li>
							<li><a href="<%=request.getContextPath()%>/frontstage/news/news.html">最新消息</a></li>
							<li><a href="<%=request.getContextPath()%>/frontstage/news/news.html">關於我們</a></li>
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
		<!--上方表頭結束-->
<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/ArticleSubServlet" name="form1">	
	<div class="container">
		<div class="row" style="background-color: white;">
			<div class="col-lg-12">
				<section id="basic-horizontal-layouts">
					<div class="row match-height" style=" position: relative;">
						<div class="col-md-1"></div>
						<div class="col-md-5">
							<div class="card">
								<div class="card-header">
									<h4 class="card-title">文章檢舉資訊</h4>
								</div>
								<div class="card-content">
									<div class="card-body">
										<form class="form form-horizontal">
											<div class="form-body">
												<div class="row">
													<div class="col-md-6">
														<label>文章編號</label>
													</div>
													<div class="col-md-6 form-group">
														<input type="text" name="articleNo"
															value="${(artVO != null) ? artVO.articleNo : artErr.articleNo}"readonly >
													</div>
							
													<div class="col-md-6">
														<label>文章名稱</label>
													</div>								
													<div class="col-md-6 form-group">
														<input type="text" 
														value="${(artVO != null) ? artVO.articleTitle : artErr.articleTitle}"readonly >
													</div>	
													<div class="col-md-6">
														<label>會員編號(文章作者)</label>
													</div>
							
													<div class="col-md-6 form-group">
														<input type="text" 
																value="${(artVO != null) ? artVO.members.memberId : artErr.members.memberId}"readonly >															
													</div>
													<div class="col-md-6">
														<label>會員帳號(文章作者)</label>
													</div>
														<div class="col-md-6 form-group">
															<input type="text" value="${(artVO != null) ? artVO.members.memberAccount : artErr.members.memberAccount }"readonly >
														</div>
	
													<div class="col-md-6">
														<label>會員暱稱(文章作者)</label>
													</div>
													<div class="col-md-6 form-group">
														<input type="text" 
																value="${(artVO != null) ? artVO.members.memberNickname : artErr.members.memberNickname}"
																readonly >
													</div>
												</div>
											</div>
										</form>
									</div>
								</div>
							</div>
						</div>
						<div class="col-md-5">
							<div class="card">
								<div class="card-header">
									<h4 class="card-title">文章檢舉理由</h4>
								</div>
								<div class="card-content">
									<div class="card-body">
										<textarea  id="" cols="30" rows="10"  name="reportingReason"
											style="height: 260px; width: 100%; resize: none;">
										</textarea>
									</div>
								</div>
									<%-- 錯誤表列 --%>
									<div style="margin-top :50px">
				                     	<c:if test="${not empty errorMsgs}">
				                        <font style="color: red">請修正以下錯誤:</font>
				                        <ul>
				                            <c:forEach var="message" items="${errorMsgs}">
				                                <li style="color: red">${message}</li>
				                            </c:forEach>
				                        </ul>
				                        </c:if>
				                    </div>
							</div>
						</div>
					</div>
					<div style="text-align: right; margin-top: 10px; ">
					
						<input type ="hidden" name ="reporterId"  value="${membersVO.memberId}">
			
						<input type="hidden" name="action" value="insertReport">
						<a class="btn btn-primary rounded-pill" id="confirm" style=" margin-bottom: 20px;">
								確認送出</a>
						<a class="btn btn-primary rounded-pill" id="cancel"
							style="margin-right: 90px; margin-bottom: 20px;">取消</a>
					</div>
				 </div>
			</section>
		</div>	
	</Form>
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
	<script src="<%=request.getContextPath()%>/frontstage/js/jquery-3.3.1.min.js"></script>
	<script src="<%=request.getContextPath()%>/frontstage/js/bootstrap.min.js"></script>
	<script src="<%=request.getContextPath()%>/frontstage/js/jquery.nice-select.min.js"></script>
	<script src="<%=request.getContextPath()%>/frontstage/js/jquery-ui.min.js"></script>
	<script src="<%=request.getContextPath()%>/frontstage/js/jquery.slicknav.js"></script>
	<script src="<%=request.getContextPath()%>/frontstage/js/mixitup.min.js"></script>
	<script src="<%=request.getContextPath()%>/frontstage/js/owl.carousel.min.js"></script>
	<script src="<%=request.getContextPath()%>/frontstage/js/main.js"></script>
	<script src="<%=request.getContextPath()%>/frontstage/article/js/HO.js"></script>
	<script src="<%=request.getContextPath()%>/frontstage/js/TomJS.js"></script>
	
	<script>
	$(document).ready(function() {
	    // 當確認送出按鈕被點擊時
	    $("#confirm").click(function() {
	        // 提交表單
	        $("form[name='form1']").submit();
	    });

	    // 當取消按鈕被點擊時
	    $("#cancel").click(function() {
	        // 返回上一頁
	        window.history.back();
	    });
	});
	</script>

</body>

</html>