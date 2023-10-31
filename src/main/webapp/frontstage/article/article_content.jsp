<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.cooklab.article.model.*"%>
<%@ page import="com.cooklab.article_sub.model.*"%>
<%@ page import="com.cooklab.article_reaction.model.*"%>
<%@ page import="com.cooklab.article_sub_reaction.model.*"%>
<%@ page import="com.cooklab.members.model.*"%>
<%@ page import="java.util.*"%>
<%
	//以下接收會員資訊
	
	session.getAttribute("account");                    
	session.getAttribute("userId" );                        
	session.getAttribute("membersVO");
	Object mem = session.getAttribute("userId" ); 
	
	Integer userId= Integer.valueOf(mem.toString());
// 	System.out.print("我是新的" +userId);
//  這個artVO是變數名稱用在此網頁帶資料,後面的artVO是後端傳進來的變數名稱
    ArticleVO artVO = (ArticleVO) request.getAttribute("artVO");
    pageContext.setAttribute("artVO",artVO);
	//下面是用於主文reaction的查詢
	ArticleReactionService reaSvc = new ArticleReactionService();

	Byte like = 1;
	Byte dislike =2;
	Long reaLike = reaSvc.allCount(artVO.getArticleNo(), like);
	Long reaDislike =reaSvc.allCount(artVO.getArticleNo(), dislike);
	//Servlet P204 、P337你沒有setAttribute用EL 就會抓不到值
	pageContext.setAttribute("reaLike",reaLike);
	pageContext.setAttribute("reaDislike",reaDislike);
	pageContext.setAttribute("like", like);
	pageContext.setAttribute("dislike", dislike);
	
	//下面用來判斷是否有按過讚，如果查無資料也不會導致網頁崩壞
	ArticleReactionService reaSvc2 = new ArticleReactionService();
	ArticleReactionVO  reaVO2 =reaSvc2.findTwo(userId, artVO.getArticleNo());
	
	if(reaVO2!= null ){
		System.out.println("按讚狀態"+reaVO2.getStatus());
	}else{
		System.out.println("沒有資料");
	}
	pageContext.setAttribute("reaVO2",reaVO2);
	
	

	ArticleSubService artSvc2 =new ArticleSubService();
	List<ArticleSubVO> list2 = artSvc2.getAll();
	pageContext.setAttribute("list2",list2);			
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
		
        <div class="container" style="margin-top: 30px;">
            <div class="row">
                <div id="c_user" class="col-md-3"
                    style="width: 200px; height: 250px; display: flex; flex-direction: column; align-items: center;">
                    <a>${artVO.members.memberPicture}</a>
                    
                    <a href="">${artVO.members.memberNickname}</a>
                </div>
                <div class="col-8">
                    <div class="row">
                        <div id="article_content" class="col" style="position: relative;">
                            <h7 class="conten_title"> 
                            <span>[${artVO.articleCategory.articleCategory}] ${artVO.articleTitle}</span>
                            </h7>
                            <p>
                                發表時間:<fmt:formatDate value="${artVO.lastEditTimestamp}"
                                    pattern="yyyy-MM-dd HH:mm:ss" />
                            </p>						
                            <p> ${artVO.articleContent}</p>
							<br> <br>
							
							
							<div class="like-dislike">
								<c:choose> 
									<c:when test="${reaVO2.status == 1}">
										<img class="clickable like" src="<%=request.getContextPath()%>/frontstage/article/img/HO/like.png"
											alt="Like" style="width: 30px; height: 30px; margin-right:20px;" 
											data-gjStatus="1" data-memberId="${membersVO.memberId}" data-articleNo="${artVO.articleNo}">
									</c:when>
									<c:otherwise>
										<img class="clickable like" src="<%=request.getContextPath()%>/frontstage/article/img/HO/like.png"
											alt="Like" style="width: 30px; height: 30px; margin-right:20px;" 
											data-gjStatus="0" data-memberId="${membersVO.memberId}" data-articleNo="${artVO.articleNo}">
									</c:otherwise>
								</c:choose>
								<span class="likeValue" style="margin-right: 50px;">${reaLike}</span>
								
								<c:choose>
									<c:when test="${reaVO2.status == 2}">
									<img class="clickable dislike" src="<%=request.getContextPath()%>/frontstage/article/img/HO/dislike.png"
										alt="Dislike" style="width:30px; height:30px; margin-right:20px;"
										data-gjStatus="2" data-memberId="${membersVO.memberId}" data-articleNo="${artVO.articleNo}">
									</c:when>
									<c:otherwise>
										<img class="clickable dislike" src="<%=request.getContextPath()%>/frontstage/article/img/HO/dislike.png"
										alt="Dislike" style="width:30px; height:30px; margin-right:20px;"
										data-gjStatus="0" data-memberId="${membersVO.memberId}" data-articleNo="${artVO.articleNo}">
									</c:otherwise>
								</c:choose>
								<span class="dislikeValue" style="margin-right: 50px;">${reaDislike}</span>
							
	 						<FORM  METHOD="post" ACTION="<%=request.getContextPath()%>/ArticleSubServlet" style="margin-bottom: 0px;">
								
								<input type="submit" class="btn custom-btn" name="report" 
									value="檢舉" style="color:red; position: absolute; bottom: 10%; left: 50%; transform: translate(-50%, 50%);" ;"> 
								<input type="hidden" name="articleNo" value="${artVO.articleNo}">
								<input type="hidden" name="action" value="reportSearch">
							</FORM>
							<FORM  METHOD="post" ACTION="<%=request.getContextPath()%>/ArticleSubServlet" style="margin-bottom: 0px;">
	
								<input type="submit" class="btn custom-btn" name="response"
									value="回覆" style="float:right;"> 
								<input type="hidden" name="articleNo" value="${artVO.articleNo}">
								<input type="hidden" name="action" value="subSearch">
							</FORM>
							</div>
						</div>
					</div>
					<hr  style="border: 1px solid #F29422; ">
				</div>
			</div>
		</div>
    
    <% 
	    ArticleSubReactionService  reaSubSvc = new ArticleSubReactionService();
	    ArticleSubReactionVO  reaSubVO = new ArticleSubReactionVO();
	    pageContext.setAttribute("reaSubSvc", reaSubSvc);
	    
    %>
        
    <c:forEach var="artVO2" items="${list2}">
	    <c:set var="articleSubNo" value="${artVO2.articleSubNo}" />
	     
	    <!-- 下面遞迴會員有沒有對回文文章按讚的status -->
	    <c:set var="reaSubVO" value="${reaSubSvc.findTwo(userId, articleSubNo)}" />
	    <!-- 下面遞迴回文文章的按讚數量 -->
	 	<c:set var="subLike" value="${reaSubSvc.allCount(artVO2.articleSubNo, like)}"/>
    	<c:set var="subDisLike" value="${reaSubSvc.allCount(artVO2.articleSubNo,dislike)}"/>
		

        <c:if test="${artVO2.articleNo == artVO.articleNo}">
            <FORM  METHOD="post" ACTION="<%=request.getContextPath()%>/ArticleSubServlet" style="margin-bottom: 0px;">
                <div class="container" style="margin-top: 30px;">
                    <div class="row">
                        <div id="c_user" class="col-md-3"
                            style="width: 200px; height: 250px; display: flex; flex-direction: column; align-items: center;">
                            <a>${artVO2.members.memberPicture}</a>
                            <a href=""id="creator">${artVO2.members.memberNickname}</a>
                        </div>
                        <div class="col-8">
                            <div class="row">
                                <div id="article_content" class="col" style="position: relative;">
                                    <h7 class="conten_title"> 
                                        <span>RE#[${artVO.articleCategory.articleCategory}] ${artVO.articleTitle}</span>
                                    </h7>
                                    <p>
                                        發表時間:<fmt:formatDate value="${artVO.lastEditTimestamp}"
                                            pattern="yyyy-MM-dd HH:mm:ss" />
                                    </p>						
	                                <p>${artVO2.articleSubContent}</p>
                                    <br> <br>
                                     <div class = "like-dislike" > 
		                             	<c:choose> 
			                             	<c:when test="${reaSubVO.status == 1}">
				                                 <img class="clickable like" src="<%=request.getContextPath()%>/frontstage/article/img/HO/like.png"
				                                       alt="Like"  style="width: 30px; height: 30px; margin-right:20px;" 
				                                    data-gjStatus="1"  data-memberId ="${membersVO.memberId}"   data-articleSubNo ="${artVO2.articleSubNo}" >
			                                </c:when>
			                                <c:otherwise>
				                                 <img class="clickable like" src="<%=request.getContextPath()%>/frontstage/article/img/HO/like.png"
				                                       alt="Like"  style="width: 30px; height: 30px; margin-right:20px;" 
				                                    data-gjStatus="0"  data-memberId ="${membersVO.memberId}"  data-articleSubNo ="${artVO2.articleSubNo}" >
			                                </c:otherwise>
		                                </c:choose>
			                            <span  class="likeValue" style="margin-right: 50px;">${subLike}</span>
		                                
		      							<c:choose>
			                                <c:when test="${reaSubVO.status == 2}">
			                                <img class="clickable dislike" src="<%=request.getContextPath()%>/frontstage/article/img/HO/dislike.png"
			                                    alt="Dislike" style="width:30px; height:30px; margin-right:20px;"
			                                    data-gjStatus="2"  data-memberId ="${membersVO.memberId}"   data-articleSubNo ="${artVO2.articleSubNo}" >
			                                </c:when>
			                                <c:otherwise>
				                                <img class="clickable dislike" src="<%=request.getContextPath()%>/frontstage/article/img/HO/dislike.png"
				                                    alt="Dislike" style="width:30px; height:30px; margin-right:20px;"
				                                    data-gjStatus="0"   data-memberId ="${membersVO.memberId}"   data-articleSubNo ="${artVO2.articleSubNo}" >
			                                </c:otherwise>
		                                </c:choose>
		                                <span  class="dislikeValue" style="margin-right: 50px;">${subDisLike}</span>
                              <FORM  METHOD="post" ACTION="<%=request.getContextPath()%>/ArticleSubServlet" style="margin-bottom: 0px;">
								
								<input type="submit" class="btn custom-btn" name="report" 
									value="檢舉" style="color:red; position: absolute; bottom: 0;" > 
								<input type="hidden" name="articleNo" value="${artVO.articleNo}">
								<input type="hidden" name="action" value="reportSearch2">
							</FORM>
							<FORM  METHOD="post" ACTION="<%=request.getContextPath()%>/ArticleSubServlet" style="margin-bottom: 0px;">
	
								<input type="submit" class="btn custom-btn" name="response"
									value="回覆" style="float:right;"> 
								<input type="hidden" name="articleNo" value="${artVO.articleNo}">
								<input type="hidden" name="action" value="subSearch">
							</FORM>
							</div>
						</div>
					</div>
					<hr  style="border: 1px solid #F29422; ">
				</div>
			</div>
		</div>
     </c:if>
    </c:forEach>
    
    
    <FORM  METHOD="post" ACTION="<%=request.getContextPath()%>/ArticleSubServlet" style="margin-bottom: 0px;">
        <div class="container" style="margin-top: 10px;">
            <div class="row">
                <div id="c_user" class="col-md-3"
                    style="width: 200px; height: 250px; display: flex; flex-direction: column; align-items: center;">
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
                <div class="col-8">
                    <div class="row">
                        <div id="article_content" class="col" style="position: relative;">
    
                            <div id="reply" >
                                <textarea name="articleSubContent" id="reply_input" style="width:100%; height: 80px;" ></textarea>
                            </div>
                            <div class="d-flex justify-content-start" style="margin-top: 3px;">
                                <input type="submit" class="btn custom-btn" value="快速回覆" style="margin-top:5px;"> 
                                <input type="hidden" name="articleNo" value="${artVO.articleNo}">
                                <input type="hidden" name="memberId" value= "${membersVO.memberId}" size="45" /> 
                                <input type="hidden" name="articleSubStatus" value="0" size="45" /> 
                                <input type="hidden" name="articleSubCount"  value="0" size="45" /> 
                                <input type="hidden" name="action" value="insert">
                                
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
   </FORM>
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
	//強制回到article_main.jsp(目前沒有效)
	window.onbeforeunload = function (event) {
	    if (document.referrer !== 'http://localhost:8081/CookLab/article/article_main.jsp') {
	        window.location.href = 'http://localhost:8081/CookLab/article/article_main.jsp';
	    }
	}
	

	//===============按讚的JS========================
	$(document).ready(function () {
	$(".like, .dislike").each(function () {
		const $image = $(this);
		const $container = $image.closest('.like-dislike');
		var value = parseInt($image.attr("data-gjStatus"));
		switch (value) {
			case 1:
				$container.find(".clickable.like").css("transform", "scale(2)");
				$image.data("enlarged", true);
				break;
			case 2:
				$container.find(".clickable.dislike").css("transform", "scale(2)");
				$image.data("enlarged", true);
				break;
		}
	});
	$(".like, .dislike").click(function () {
		const $image = $(this);
		const $container = $image.closest('.like-dislike');

		const isEnlarged = $image.data("enlarged");
		var value = parseInt($image.attr("data-gjStatus"));

		var likeStatus = parseInt($container.find(".like").attr("data-gjStatus"));
		var dislikeStatus = parseInt($container.find(".dislike").attr("data-gjStatus"));

		let likeValue = parseInt($(this).next(".likeValue").text(), 10);
		let dislikeValue = parseInt($(this).next(".dislikeValue").text(), 10);

		if ($image.hasClass("like")) {
			if (dislikeStatus === 2 && value === 0) {
				const $clickableDislike = $container.find('.clickable.dislike');
				$clickableDislike.css("transform", "scale(1)");

				$image.css("transform", "scale(2)");
				$image.data("enlarged", true);
				value = 1;
				likeValue += 1;
				dislikeValue = parseInt($container.find('.dislikeValue').text(), 10);
				dislikeValue -= 1;
				console.log("dislike後:" + dislikeValue);
				$(this).next(".likeValue").text(likeValue);
				$container.find(".dislikeValue").text(dislikeValue);

				$clickableDislike.attr("data-gjStatus", 0);
				console.log("啟用like+1 ,dislike-1");
			} else if (value === 0) {


				$container.find(".clickable").css("transform", "scale(1)");
				$image.css("transform", "scale(2)");
				$image.data("enlarged", true);
				value = 1;
				likeValue += 1;
				console.log("After likeValue += 1:", likeValue);
				$(this).next(".likeValue").text(likeValue);
				$(this).next(".dislikeValue").text(dislikeValue);
				console.log("啟用likeValue + 1");

			} else {
				$image.css("transform", "scale(1)")
				$image.data("enlarged", false);
				value = 0;
				likeValue -= 1;
				$(this).next(".likeValue").text(likeValue);
				$(this).next(".dislikeValue").text(dislikeValue);
				console.log("啟用likeValue-1")

			}
		} else if ($image.hasClass("dislike")) {
			if (likeStatus === 1 && value === 0) {
				const $clickablelike = $container.find('.clickable.like');
				$clickablelike.css("transform", "scale(1)");

				$image.css("transform", "scale(2)");
				$image.data("enlarged", true);


				value = 2;
				dislikeValue += 1;

				likeValue = parseInt($container.find('.likeValue').text(), 10);
				likeValue -= 1;

				$(this).next(".dislikeValue").text(dislikeValue);
				$container.find(".likeValue").text(likeValue);


				$clickablelike.attr("data-gjStatus", 0);
				console.log("dislike +1,like -1")
			} else if (value === 0) {

				$container.find(".clickable").css("transform", "scale(1)");
				$image.css("transform", "scale(2)");
				$image.data("enlarged", true);
				value = 2;
				dislikeValue += 1;
				$(this).next(".likeValue").text(likeValue);
				$(this).next(".dislikeValue").text(dislikeValue);


			} else {

				$image.css("transform", "scale(1)");
				$image.data("enlarged", false);
				value = 0;
				dislikeValue -= 1;
				$(this).next(".likeValue").text(likeValue);
				$(this).next(".dislikeValue").text(dislikeValue);
			}
		}
		
        $image.attr("data-gjStatus", value);
        //取得數據//下方ajax
        const memberId = $image.attr("data-memberId");
        const articleNo = $image.attr("data-articleNo");
        const status = $image.attr("data-gjStatus");
        const articleSubNo = $image.attr("data-articleSubNo");

        console.log("會員編號" + memberId);
        console.log("文章編號" + articleNo);
        console.log("回文文章編號" + articleSubNo)
        console.log("狀態" + status);

        if (articleNo) { //判斷是否有值
            $.ajax({
                url: "/CookLab/ArticleReactionServlet", // 後端Servlet的URL
                type: "POST", 
                dataType: "json",
                data: {
                    action: "saveOrUpdate",
                    memberId: memberId,
                    articleNo: articleNo,
                    status: status
                },
                statusCode: {
                    //狀態碼
                    200: function (res) {},
                    404: function (res) {},
                    500: function (res) {},
                },
                success: function (data) {
                    // 處理成功回報
                    console.log(data);
                    console.log("回傳成功：" + response);
                },
                error: function (xhr, status, error) {
                    // 處理失敗回報
                    console.log("請求失敗，的狀態碼" + xhr.status);
                    console.log("會員編號" + memberId);
                    console.log("文章編號" + articleNo);
                    console.log("狀態" + status);
                    console.log(xhr);
                }
            });
        } else {
            $.ajax({
                url: "/CookLab/ArticleSubReactionServlet", // 後端Servlet的URL
                type: "POST", 
                dataType: "json",
                data: {
                    action: "saveOrUpdate",
                    memberId: memberId,
                    articleSubNo: articleSubNo,
                    status: status
                },
                statusCode: {
                    //狀態碼
                    200: function (res) {},
                    404: function (res) {},
                    500: function (res) {},
                },
                success: function (data) {
                    // 處理成功回報
                    console.log(data);
                    console.log("回傳成功：" + response);
                },
                error: function (xhr, status, error) {
                    // 處理失敗回報
                    console.log("請求失敗，的狀態碼" + xhr.status);
                    console.log("會員編號" + memberId);
                    console.log("回文文章編號" + articleSubNo);
                    console.log("狀態" + status);
                    console.log(xhr);
                }
            });
        }
    });
});
	</script>

</body>

</html>