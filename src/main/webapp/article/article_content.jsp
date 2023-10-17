<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.cooklab.article.model.*"%>

<%
ArticleVO artVO = (ArticleVO) request.getAttribute("artVO");
//EmpServlet.java(Concroller), 存入req的empVO物件
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

<!-- Google Font -->
<link
	href="https://fonts.googleapis.com/css2?family=Cairo:wght@200;300;400;600;900&display=swap"
	rel="stylesheet">

<!-- Css Styles -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/article/css/bootstrap.min.css"
	type="text/css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/article/css/font-awesome.min.css"
	type="text/css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/article/css/elegant-icons.css"
	type="text/css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/article/css/nice-select.css"
	type="text/css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/article/css/jquery-ui.min.css"
	type="text/css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/article/css/owl.carousel.min.css"
	type="text/css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/article/css/slicknav.min.css"
	type="text/css">

<link rel="stylesheet"
	href="<%=request.getContextPath()%>/article/css/style.css"
	type="text/css">

<link rel="stylesheet"
	href="<%=request.getContextPath()%>/article/css/HO.css" type="text/css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/article/css/ding.css"
	type="text/css">

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
				<li class="active"><a href="./index.html">首頁</a></li>
				<li><a href="./shop-grid.html">商城</a></li>
				<li><a href="./blog.html">食譜總覽</a></li>
				<li><a href="./contact.html">常見問題</a></li>
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
							<li><a href="./shop-grid.html">商城</a></li>
							<li class="active"><a href="#">討論區</a></li>
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
	<!--上方表頭結束-->


	<div class="container" style="margin-top: 30px;">
		<div class="row">
			<div id="c_user" class="col-md-3"
				style="width: 200px; height: 250px; display: flex; flex-direction: column; align-items: center;">
				<a>
<%-- 				${artVO.members.memberPicture} --%>
				<c:choose>
                <c:when test="${artVO.members.memberPicture.startsWith('/9j/4AAQSkZJRgABAQEAZABkAAD/2wBDAAMCAgMCA')}">
                    <!-- 這是Base64圖片，使用<img>元素顯示 -->
                    <img src="data:image/jpeg;base64,${artVO.members.memberPicture}" alt="圖片描述">
                </c:when>
                <c:otherwise>
                    <!-- 這是文本，直接顯示 -->
                    ${artVO.members.memberPicture}
                </c:otherwise>
            </c:choose>
				</a>
				<a href="" id="creator"  style="color: black;">
					${artVO.members.memberNickname}</a>
			</div>


			<div class="col-8">
				<div class="row">
					<div id="article_content" class="col" style="position: relative;">
						<h7 class="conten_title"> <span>[${artVO.articleCategoryVO.articleCategory}]${artVO.articleTitle}</span>
						</h7>
						<p>
							發表時間:
							<fmt:formatDate value="${artVO.lastEditTimestamp}"
								pattern="yyyy-MM-dd HH:mm:ss" />
						</p>
						<td>
							<!-- 這是文本，直接顯示 ，在老師的網面上需要使用base64來讀網頁，
             			     可是使用quill新增的圖文，可以直接顯示--> ${artVO.articleContent}
						</td> <br> <br>
						<div id="like-dislike">
							<img src="<%=request.getContextPath()%>/article/img/HO/like.png"
								alt=""><span style="margin-right: 10px;">10</span> <img
								src="<%=request.getContextPath()%>/article/img/HO/dislike.png"
								alt=""><span>10</span>
							<button type="submit">回覆</button>
						</div>


						<!-- <div id="replied"><img id="user_avatar" src="https://picsum.photos/id/237/50" alt=""><p>Lorem ipsum dolor sit amet consectetur adipisicing elit. Quis nihil harum molestiae, tenetur quod, dolore officiis, consequuntur error facilis veniam porro nemo eum sequi earum totam. Quaerat possimus nam consequatur?</p></div> -->
						<hr>

						<div class="col" style="height: 150px; width: 1000px;">
							<div id="reply" class="d-flex justify-content-start ">
								<textarea name="" id="reply_input">這邊可以快速留言....</textarea>

							</div>
							<button type="submit">送出</button>
						</div>
					</div>
				</div>
			</div>
		</div>


	</div>



	</div>

	</div>

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
		src="<%=request.getContextPath()%>/article/js/jquery-3.3.1.min.js"></script>
	<script src="<%=request.getContextPath()%>/article/js/bootstrap.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/article/js/jquery.nice-select.min.js"></script>
	<script src="<%=request.getContextPath()%>/article/js/jquery-ui.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/article/js/jquery.slicknav.js"></script>
	<script src="<%=request.getContextPath()%>/article/js/mixitup.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/article/js/owl.carousel.min.js"></script>
	<script src="<%=request.getContextPath()%>/article/js/main.js"></script>
	<script src="<%=request.getContextPath()%>/article/js/HO.js"></script>



</body>

</html>