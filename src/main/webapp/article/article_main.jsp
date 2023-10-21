<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.cooklab.article.model.*"%>
<%@ page import="com.cooklab.article_category.model.*"%>
<%
    ArticleService artSvc = new ArticleService();
    List<ArticleVO> list = artSvc.getAll();
    pageContext.setAttribute("list", list);

	ArticleCategoryService artSvc2 =new ArticleCategoryService();
	List<ArticleCategoryVO> list2 = artSvc2.getAll();
	pageContext.setAttribute("list2",list2);
%>
<!DOCTYPE html>
<html lang="zxx">
<head>
<meta charset="UTF-8" />
<meta name="description" content="Ogani Template" />
<meta name="keywords" content="Ogani, unica, creative, html" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<meta http-equiv="X-UA-Compatible" content="ie=edge" />
<title>Ogani main_cate</title>

<!-- Google Font -->
<link
	href="https://fonts.googleapis.com/css2?family=Cairo:wght@200;300;400;600;900&display=swap"
	rel="stylesheet" />

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
	href="<%=request.getContextPath()%>/article/css/HO.css"
	type="text/css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/article/css/ding.css"
	type="text/css">
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<!-- 	沒有上面的jquery-3.6.0.min.js，就不能直接在js使用EL語法 -->
</head>

<body onload="connect();" onunload="disconnect();">
	<!-- Page Preloder -->
	<div id="preloder">
		<div class="loader"></div>
	</div>

	<!-- Humberger Begin -->
	<div class="humberger__menu__overlay"></div>
	<div class="humberger__menu__wrapper">
		<div class="humberger__menu__logo">
			<a href="#"><img src="<%=request.getContextPath()%>/article/img/indexlogo.png" alt="" /></a>
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
						<a href="./index.html"><img
							style="height: 150px" src="img/indexlogo.png" alt="" /></a>
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
<%-- <FORM  id="categoryForm" METHOD="post" ACTION="<%=request.getContextPath()%>/ArticleCategoryServlet" style="margin-bottom: 0px;"> --%>
    <div class="container" id="article_cat_btn">
      <div class="row">
        <div class="d-flex" id="articel_cat">
			<c:forEach var="artVO2" items="${list2}">
     			<c:if test="${artVO2.articleCategoryNo == 1 }">
						<button type="submit" class="btn custom-btn" name="articleCategoryNo" value="${artVO2.articleCategoryNo}"
						style="margin-right:3px;">
                		${artVO2.articleCategory}
            			</button>        			
            		</c:if>
    				<c:if test="${artVO2.categoryStatus == 0 && artVO2.articleCategoryNo != 1 }">
        			<form METHOD="post" ACTION="<%=request.getContextPath()%>/ArticleCategoryServlet" style="margin-bottom: 0px;">
            			<button type="submit" class="btn custom-btn" name="articleCategoryNo" value="${artVO2.articleCategoryNo}"
            			style="margin-right:3px;">
                		${artVO2.articleCategory}
            			</button>
            			<input type="hidden" name="action" value="cateSearch">
        			</form>
    			</c:if>
			</c:forEach>				
      		</div>
      	<span></span>
   	 	</div>
    </div>
<!--   </FORM> -->
	
	


	<section id="article_conten">
		<%@ include file="page1.file"%>
		<div class="container">
			<div class="row">
				<div class="col-lg-9" style="height: 600px;">
					<div>
						<table>
							<tr>
								<td id="title_colum_td">
								<a href="" id="cat_view">文章分類</a> 
								<a href="" id="title_view">標題</a>
								</td>
								<td id="article_creator">發文作者</td>
								<td id="article_date">發表時間</td>
								<td id="article_count">點擊次數</td>
							</tr>
							
<%-- 							 begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>" --%>
							<c:forEach var="artVO" items="${list}">
								<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/ArticleServlet" style="margin-bottom: 0px;">
								<c:if test="${artVO.articleStatus < 1 }">
 								
									<tr class="title_colum ${artVO.articleNo % 2 == 0 ? 'even' : 'odd'}">
										<td id="title_colum_td">
											<a href="" id="cat_view">[${artVO.articleCategory.articleCategory}]</a>
								
											<input type="submit" id="title_view" value="${artVO.articleTitle}"> 
											<input type="hidden" name="articleNo" value="${artVO.articleNo}">
											<input type="hidden" name="action" value="getOne_For_Display">
										</td>
									
										<td id="article_creator">${artVO.members.memberNickname}</td>
										<td id="article_date"><fmt:formatDate
											value="${artVO.lastEditTimestamp}"
											pattern="yyyy-MM-dd HH:mm:ss" />
										</td>
										<td id="article_count">
										${artVO.viewCount}
										</td>
									</tr>
 								</c:if>
 								</FORM>
							</c:forEach>
						
						</table>
					</div>
				</div>
			
				<!-- 以下群聊視窗範圍 -->
				<div class="col-lg-3">
					<a class="btn btn-outline-primary btn-lg" id="article_sumbit"
						href="<%=request.getContextPath()%>/article/article_edit.jsp">發文</a>

					<div class="statusOutput" id="statusOutput">CookTalk</div>
					<textarea id="messagesArea" class="panel message-area" readonly></textarea>
					<div class="panel input-area">

						<input id="message" class="text-field" type="text"
							placeholder="Message"
							onkeydown="if (event.keyCode == 13) sendMessage();" /> 
						<input  type="submit" id="sendMessage" class="btn ding-btn-org"
							value="Send" onclick="sendMessage();" />
					</div>

				</div>
			</div>
		</div>
	</section>

	<!-- 頁簽-->
	<%@ include file="page2.file"%>
	<div class="d-flex justify-content-center">
		<nav aria-label="Page navigation example" style="margin-top: 5px">
			<ul class="pagination">
				<li class="page-item"><a class="page-link_pr" href="#">Previous</a></li>
				<li class="page-item"><a class="page-link" href="#">1</a></li>
				<!--Q2 active 沒有效果-->
				<li class="page-item"><a class="page-link" href="#">2</a></li>
				<li class="page-item"><a class="page-link" href="#">3</a></li>
				<li class="page-item"><a class="page-link_ne" href="#">Next</a></li>
			</ul>
		</nav>
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
	<script src="<%=request.getContextPath()%>/article/js/jquery-3.3.1.min.js"></script>
	<script src="<%=request.getContextPath()%>/article/js/bootstrap.min.js"></script>
	<script src="<%=request.getContextPath()%>/article/js/jquery.nice-select.min.js"></script>
	<script src="<%=request.getContextPath()%>/article/js/jquery-ui.min.js"></script>
	<script src="<%=request.getContextPath()%>/article/js/jquery.slicknav.js"></script>
	<script src="<%=request.getContextPath()%>/article/js/mixitup.min.js"></script>
	<script src="<%=request.getContextPath()%>/article/js/owl.carousel.min.js"></script>
	<script src="<%=request.getContextPath()%>/article/js/main.js"></script>
	<script src="<%=request.getContextPath()%>/article/js/HO.js"></script>
</body>
</html>
