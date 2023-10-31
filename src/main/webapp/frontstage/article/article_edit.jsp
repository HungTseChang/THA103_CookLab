<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.cooklab.article.model.*"%>
<%@ page import="com.cooklab.article_category.model.*"%>
<%
ArticleVO artVO2 = (ArticleVO) request.getAttribute("artVO");

ArticleCategoryService artSvc = new ArticleCategoryService();
List<ArticleCategoryVO> list = artSvc.getAll();
pageContext.setAttribute("list", list);
                      
Object mem = session.getAttribute("userId" ); 
Integer userId= Integer.valueOf(mem.toString());

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
<!--下面兩行是影片編輯器-->
<link href="https://cdn.quilljs.com/1.3.6/quill.snow.css"
	rel="stylesheet" />
<script src="https://cdn.quilljs.com/1.3.6/quill.js"></script>
</head>
<body>
	<!-- Page Preloder -->
	<div id="preloder">
		<div class="loader"></div>
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
				<li><a href="<%=request.getContextPath()%>/frontstage/shopstage/shop-grid.html">商城todo</a></li>
				<li><a href="<%=request.getContextPath()%>/frontstage/recipe/recipe_overview.jsp">食譜總覽todo</a></li>
				<li><a href="<%=request.getContextPath()%>/frontstage/faq/faq.html">常見問題todo</a></li>
				<li class="active"><a href="<%=request.getContextPath()%>/frontstage/article/article_main.jsp">討論區todo</a></li>
				<li><a href="<%=request.getContextPath()%>/frontstage/news/news.html">最新消息todo</a></li>
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
	<section id="article_conten">
		<div class="container" id="another">
			<div class="row">
				<div class="col-md-2" id="left_img";
                    style="border: 0px solid brown; margin-right: 10px; text-align: center;">

					<button type="button" class="btn ding-btn-org"
						style="margin-top: 50px;">引用食譜</button>
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
				<div class="col-md-9 " style="height: 700px;">

					<FORM METHOD="get" ACTION="<%=request.getContextPath()%>/ArticleServlet" name="form1">
						<input type="hidden" name="memberId" placeholder="輸入會員編號" value= userId size="45" /> 
							<input type="hidden" name="articleStatus"
							placeholder="輸入文章狀態(數字)" value="0" size="45" /> 
							
							<input  type="hidden" name="articleCount" placeholder="輸入回文數量" value="0"
							size="45" /> 
							
							<input type="hidden" name="viewCount"
							placeholder="輸入次數" value="0" size="45" /> 
						<select size="1"   name="articleCategory">
							<c:forEach var="artVO" items="${list}">
								<c:if test="${artVO.categoryStatus == 0 && artVO.articleCategoryNo > 1 }">
								<option value="${artVO.articleCategoryNo}">
									${artVO.articleCategory}
								</c:if>
							</c:forEach>
						</select> 
						<input type="text" id="edit_title" name="articleTitle"  placeholder="輸入標題"
							value="<%=(artVO2 == null) ? "" : artVO2.getArticleTitle()%>">

						<div style="width: auto; height: 500px;">
							<div id="editor" contenteditable="false"></div> <!--如果改成 true就不能複製貼上WHY?  -->
							
							<textarea id="hiddenContent" name="articleContent" style="display: none;"></textarea>


							<p style="margin-top: 5px;">
								驗證碼顯示位置: <input type="text" value="請輸入驗證碼">
								<input type="hidden" name="action" value="insert">
								<button type="submit" class="btn ding-btn-org" id="btn_confirm">確定</button>

								<input type="hidden" name="action" value="insert">
								<button type="submit" class="btn ding-btn-org" id="btn_drawft">儲存草稿</button>

								<button class="btn ding-btn-org" id="btn_clean">清除</button>
								<button class="btn ding-btn-org" id="btn_cancel">取消</button>
							</p>
					</form>
				</div>
			</div>
		</div>

		</div>
	</section>
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
	<script src="<%=request.getContextPath()%>/frontstage/article/js/quill.js"></script>
	<script src="<%=request.getContextPath()%>/frontstage/article/js/HO.js"></script>
	<script src="../js/TomJS.js"></script>

</body>

</html>