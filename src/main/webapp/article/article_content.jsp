<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.cooklab.article.model.*"%>
<%@ page import="com.cooklab.article_sub.model.*"%>
<%@ page import="com.cooklab.article_reaction.model.*"%>
<%@ page import="java.util.*"%>
<%
//  �o��artVO�O�ܼƦW�٥Φb�������a���,�᭱��artVO�O��ݶǶi�Ӫ��ܼƦW��
    ArticleVO artVO = (ArticleVO) request.getAttribute("artVO");


	ArticleReactionService reaSvc = new ArticleReactionService();
	ArticleReactionVO  a1 = new ArticleReactionVO();
	Byte like = 1 ;
	Byte dislike =2;
	Long reaLike = reaSvc.allCount(artVO.getArticleNo(), like);
	Long reaDislike =reaSvc.allCount(1, dislike);
	//Servlet P204 �BP337�A�S��setAttribute��EL �N�|�줣���
	pageContext.setAttribute("reaLike",reaLike);
	pageContext.setAttribute("reaDislike",reaDislike);

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
				<li class="active"><a href="./index.html">����</a></li>
				<li><a href="./shop-grid.html">�ӫ�</a></li>
				<li><a href="./blog.html">�����`��</a></li>
				<li><a href="./contact.html">�`�����D</a></li>
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
									class="ding-nav-text">�ʪ���</span>
								</a>
							</div>
							<div class="header__top__right__auth">
								<a href="#"> <i class="fa fa-user m-0 ml-2 fa-lg"></i> <span
									class="ding-nav-text">�|������</span>
								</a>
							</div>
							<div class="header__top__right__auth">
								<a href="#" class="m-0 ml-2 ding-nav-text">�n�J/���U</a>
							</div>
							<div class="header__top__right__auth">
								<a href="#"> <i class="bi bi-bell m-0 ml-2 fa-lg"></i> <span
									class="ding-nav-text">�q������</span>
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
							<li><a href="./index.html">�����`��</a></li>
							<li><a href="#">�s�W����</a></li>
							<li><a href="#">���`����</a></li>
							<li><a href="./shop-grid.html">�ӫ�</a></li>
							<li class="active"><a href="#">�Q�װ�</a></li>
							<li><a href="#">�ȪA����</a></li>
							<li><a href="./contact.html">����ڭ�</a></li>
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
	<!--�W����Y����-->
	
	<FORM  METHOD="post" ACTION="<%=request.getContextPath()%>/ArticleSubServlet" style="margin-bottom: 0px;">
		
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
							�o��ɶ�:<fmt:formatDate value="${artVO.lastEditTimestamp}"
								pattern="yyyy-MM-dd HH:mm:ss" />
						</p>						
						
						<p> ${artVO.articleContent}</p>

						<br> <br>
 						
 							<div id="like-dislike" > 
             					<img class="clickable like" src="<%=request.getContextPath()%>/article/img/HO/like.png"
                           			 alt="Like"  style="width: 30px; height: 30px; margin-right:20px;" 
                            		 data-gjStatus="0"  data-memberId = 5  data-articleNo ="${artVO.articleNo}" >
                  				<span  class="likeValue" style="margin-right: 50px;">${reaLike}</span>
                  			
                  				<input type="hidden" class="testMemberId" value="" style= "float:right;"> 
                  				
                            	<img class="clickable dislike" src="<%=request.getContextPath()%>/article/img/HO/dislike.png"
                            		alt="Dislike" style="width:30px; height:30px; margin-right:20px;"
                            		data-gjStatus="0">
                            		
                           		<span  class="dislikeValue" style="margin-right: 50px;">${reaDislike}</span>
                            
                            
	                		
	                		
							<input type="submit" class="btn custom-btn" value="�^��" style= "float:right;"> 
							<input type="hidden" name="articleNo" value="${artVO.articleNo}">
							<input type="hidden" name="action" value="subSearch">
						
						<hr  style="border: 1px solid #F29422; ">
					</div>
				</div>
			</div>
		</div>
	</div>
	</FORM>

	
	<c:forEach var="artVO2" items="${list2}">
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
							�o��ɶ�:<fmt:formatDate value="${artVO2.lastEditTimeStamp}"
								pattern="yyyy-MM-dd HH:mm:ss" />
						</p>
						<p>${artVO2.articleSubContent}</p>
						<br> <br>
						<div id="like-dislike" > 
 							
                            <img class="clickable like" src="<%=request.getContextPath()%>/article/img/HO/like.png"
                            	 alt="Like"  style="width: 30px; height: 30px; margin-right:20px;" 
                           		 data-gjStatus="0">
                            <span  class="likeValue" style="margin-right: 50px;">10</span>
                            <img class="clickable dislike" src="<%=request.getContextPath()%>/article/img/HO/dislike.png"
                            	alt="Dislike" style="width:30px; height:30px; margin-right:20px;"
                            	data-gjStatus="0">
                            <span  class="dislikeValue" style="margin-right: 50px;">10</span>
					        
	                			
							<input type="submit" class="btn custom-btn" value="�^��" style="float:right;"> 
							<input type="hidden" name="articleSubNo" value="${artVO2.articleSubNo}">
							<input type="hidden" name="action" value="subSearch2">
						
             			</div> 
						<hr  style="border: 1px solid #F29422; ">
					</div>
				</div>
			</div>
		</Form>
		</div>
	</div>
	</c:if>
	</c:forEach>
<FORM  METHOD="post" ACTION="<%=request.getContextPath()%>/ArticleSubServlet" style="margin-bottom: 0px;">
	<div class="container" style="margin-top: 10px;">
		<div class="row">
			<div id="c_user" class="col-md-3"
				style="width: 200px; height: 250px; display: flex; flex-direction: column; align-items: center;">
				<%-- ���~��C --%>
				<div style="margin-top :50px">
					<c:if test="${not empty errorMsgs}">
					<font style="color: red">�Эץ��H�U���~:</font>
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
							<input type="submit" class="btn custom-btn" value="�ֳt�^��"
							style="margin-top:5px;"> 
							<input type="hidden" name="articleNo" value="${artVO.articleNo}">
							<input type="hidden" name="memberId" value="3" size="45" /> 
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
							<li>���q�a�}�G�x�_�����s�ϫn�ʪF���T�q219��5��</li>
							<li>�q�ܡG(02)27120589</li>
							<li>�q�l�H�c�Gtomato@cooklab.com</li>
						</ul>
					</div>
				</div>
				<div class="col-lg-4">
					<div class="footer__widget">
						<ul>
							<li><a href="#">����ڭ�</a></li>
							<li><a href="#">����ө�</a></li>
							<li><a href="#">�w���ʪ�</a></li>
						</ul>
						<ul>
							<li><a href="#">�B�e��T</a></li>
							<li><a href="#">���p�F��</a></li>
							<li><a href="#">�`�����D</a></li>
						</ul>
					</div>
				</div>
				<div class="col-lg-4 col-md-12">
					<div class="footer__widget">
						<h6>�ߧY�q�\�q�l��</h6>
						<p>�H�ɱ����ڭ̪��̷s�����H���u�f�T��</p>
						<form action="#">
							<input type="text" placeholder="��J�z���q�l�H�c" />
							<button type="submit" class="btn ding-btn-org">�q�\</button>
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
	<script src="<%=request.getContextPath()%>/article/js/GJ.js"></script>
	
	
	<script>
	//�j��^��article_main.jsp(�ثe�S����)
	window.onbeforeunload = function (event) {
	    if (document.referrer !== 'http://localhost:8081/CookLab/article/article_main.jsp') {
	        window.location.href = 'http://localhost:8081/CookLab/article/article_main.jsp';
	    }
	}
	//===============���g��JS========================
	$(document).ready(function () {
	$(".like, .dislike").click(function () {
		  const $image = $(this);
		  const $container = $image.closest('.like-dislike');
		 
		  console.log($container);
		  const isEnlarged = $image.data("enlarged"); 
		  var value = parseInt($image.attr("data-gjStatus")); 
		  var dislikeStatus = parseInt($(".dislike").attr("data-gjStatus"));
		  var likeStatus = parseInt($(".like").attr("data-gjStatus"));

		  let likeValue = parseInt($(this).next(".likeValue").text(), 10);
		  let dislikeValue = parseInt($(this).next(".dislikeValue").text(), 10);
	  
		  if ($image.hasClass("like")) {
			if (dislikeStatus === 2 &&  value === 0 ){
				const $clickableDislike = $container.find('.clickable.dislike');
				$clickableDislike.css("transform", "scale(1)");

				$image.css("transform", "scale(2)");
				$image.data("enlarged", true);
				value = 1;
				likeValue += 1;
				dislikeValue = parseInt($container.find('.dislikeValue').text(),10);
				dislikeValue -= 1;
				console.log("dislike��:"+ dislikeValue);
				$(this).next(".likeValue").text(likeValue);
				$container.find(".dislikeValue").text(dislikeValue);

				$clickableDislike.attr("data-gjStatus", 0);
				console.log("�ҥ�like+1 ,dislike-1");
			}
			else if (value === 0) {
			  
				
			  $container.find(".clickable").css("transform", "scale(1)"); 
			  $image.css("transform", "scale(2)");
			  $image.data("enlarged", true);
			  value = 1;
			  likeValue += 1;
			  console.log("After likeValue += 1:", likeValue);
			  $(this).next(".likeValue").text(likeValue);
			  $(this).next(".dislikeValue").text(dislikeValue);
			  console.log("�ҥ�likeValue + 1");

			} else {
			  $image.css("transform", "scale(1)")
			  $image.data("enlarged", false);
			  value = 0;
			  likeValue -= 1;
			  $(this).next(".likeValue").text(likeValue);
			  $(this).next(".dislikeValue").text(dislikeValue);
			  console.log("�ҥ�likeValue-1")

			}
		  } else if ($image.hasClass("dislike")) {
			if (likeStatus === 1 && value === 0 ){
				const $clickablelike = $container.find('.clickable.like');
				$clickablelike.css("transform", "scale(1)");

				$image.css("transform", "scale(2)");
				$image.data("enlarged", true);
				
				
				value = 1;
				dislikeValue += 1;
				
				likeValue = parseInt($container.find('.likeValue').text(),10);
				likeValue -= 1;
				
				$(this).next(".dislikeValue").text(dislikeValue);
				$container.find(".likeValue").text(likeValue);


				$clickablelike.attr("data-gjStatus", 0);
				console.log("dislike +1,like -1")
			}
			else if (value === 0) {
			  
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
		//���o�ƾ�//�U��ajax
		const memberId =$image.attr("data-memberId");
		const articleNo = $image.attr("data-articleNo");
		const status = $image.attr("data-gjStatus");
		
		  let GJinfo = {
				  memberID : memberId,
				  articleNo : articleNo,
				  status  : status,
				  action : "saveOrUpdate" // ����servlet����k 
		  }

		$.ajax({
		  url: "/CookLab/ArticleReactionServlet", // ���Servlet��URL
		  type: "POST", // �i�H���u�ݨD�ϥ�GET��POST
		  dataType:"json",
		  data: {
			action: "saveOrUpdate",
			memberId: memberId,
			articleNo: articleNo,
			status: status
		  },
		  statusCode:{
			  //���A�X
			  200: function(res){},
			  404: function(res){},
			  500: function(res){},
		  },
		  success: function (data) {
			// �B�z���\�^��
			console.log(data);
			console.log("�^�Ǧ��\�G" + response);
		  },
		  error: function (xhr, status, error) {
			// �B�z���Ѧ^��
			console.log("�ШD���ѡA�����A�X" +xhr.status);
			console.log("�|���s��"+ memberId);
			console.log("�峹�s��"+ articleNo);
			console.log("���A"+ status);
			console.log(xhr);
		  }
		});
	  });
	});
	</script>

</body>

</html>