<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.cooklab.advertise.model.*"%>

<%
//見com.emp.controller.EmpServlet.java第238行存入req的empVO物件 (此為輸入格式有錯誤時的empVO物件)
AdvertiseVO advertiseVO = (AdvertiseVO) request.getAttribute("advertiseVO");
%>
<!DOCTYPE html>
<html lang="zh-tw">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>DataTable - Mazer Admin Dashboard</title>

<link rel="preconnect" href="https://fonts.gstatic.com">
<link
	href="https://fonts.googleapis.com/css2?family=Nunito:wght@300;400;600;700;800&display=swap"
	rel="stylesheet">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/mazer-main/dist/assets/css/bootstrap.css">

<link rel="stylesheet"
	href="<%=request.getContextPath()%>/mazer-main/dist/assets/vendors/simple-datatables/style.css">

<link rel="stylesheet"
	href="<%=request.getContextPath()%>/mazer-main/dist/assets/vendors/perfect-scrollbar/perfect-scrollbar.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/mazer-main/dist/assets/vendors/bootstrap-icons/bootstrap-icons.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/mazer-main/dist/assets/css/app.css">
<link rel="shortcut icon"
	href="<%=request.getContextPath()%>/mazer-main/dist/assets/images/favicon.svg"
	type="image/x-icon">

<link rel="stylesheet"
	href="<%=request.getContextPath()%>/mazer-main/dist/assets/vendors/choices.js/choices.min.css" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/mazer-main/dist/assets/vendors/summernote/summernote-lite.min.css">
<link href="//cdn.quilljs.com/1.0.0/quill.snow.css" rel="stylesheet" />
<link href="//cdn.quilljs.com/1.0.0/quill.bubble.css" rel="stylesheet" />
<style>
#preview {
	border: 1px solid lightgray;
	display: inline-block;
	width: 100px;
	min-height: 100px;
	position: relative;
}

#preview span.text {
	position: absolute;
	display: inline-block;
	left: 50%;
	top: 50%;
	transform: translate(-50%, -50%);
	z-index: -1;
	color: lightgray;
}

#preview img.preview_img {
	width: 100%;
}
				a.wccA{
					border: 1px solid rgb(151, 135, 249);
	background-color: rgb(195, 241, 253);
	padding: 4px;
	border-radius: 20px;
				}
</style>

</head>

<body>
	<div id="app">
		<div id="sidebar" class="active">
			<div class="sidebar-wrapper active">
				<div class="sidebar-header">
					<div class="d-flex justify-content-between">
						<div class="logo">
							<a href="index.html"><img
								src="<%=request.getContextPath()%>/dashboard/assets/images/logo/logo.png"
								alt="Logo" srcset=""></a>
								<div style="font-size:15px;" >會員：${thisaccount} ，你好 </div>
								<div style="font-size:10px;">&nbsp;</div>
								<div style="font-size:10px;  text-align: right;"><a class="wccA"id="logout" style="  margin-left: 40px;">登出</a>
								<a class="wccA"id="design" value="${thisaccount}" style="  margin-left: 10px;" >個人資訊</a></div>
						</div>
						<div class="toggler">
							<a href="#" class="sidebar-hide d-xl-none d-block"><i
								class="bi bi-x bi-middle"></i></a>
						</div>
					</div>
				</div>

                <div class="sidebar-menu">
                    <ul class="menu">
                        <li class="sidebar-title"></li>
                        <!-- ============================================================================================== -->
                                                  <li class="sidebar-item  ">
                            <a href="<%=request.getContextPath()%>/dashboard/login/WCC_welcome.jsp" class='sidebar-link'>
                                <i class="bi bi-grid-fill"></i>
                                <span>後台首頁</span>
                            </a>
                        </li>

                        <li class="sidebar-item ">
                            <a href="<%=request.getContextPath()%>/dashboard/member/WCC_member.jsp" class='sidebar-link'>
                                <i class="bi bi-file-earmark-spreadsheet-fill"></i>
                                <span>會員管理</span>
                            </a>
                        </li>


                        <li class="sidebar-item  has-sub ">
                            <a href="#" class='sidebar-link'>
                                <i class="bi bi-stack"></i>
                                <span>權限管理</span>
                            </a>
                            <ul class="submenu ">
                          
                                <li class="submenu-item ">
                                    <a href="<%=request.getContextPath()%>/dashboard/admin/WCC_admin_management.jsp">管理管理者</a>
                                </li>
                             
                                <li class="submenu-item ">
                                    <a href="<%=request.getContextPath()%>/dashboard/permission/WCC_permission.jsp">創立權限規則</a>
                                </li>
                            
                            </ul>
                        </li>

                        <li class="sidebar-item has-sub ">
                            <a href="#" class="sidebar-link">
                                <i class="bi bi-collection-fill"></i>
                                <span>食譜管理</span>
                            </a>
                            <ul class="submenu">
                                <li class="submenu-item">
                                    <a href="<%=request.getContextPath()%>/dashboard/recipe/WCC_recipe.jsp">食譜表單</a>
                                </li>
                                <li class="submenu-item ">
                                    <a href="<%=request.getContextPath()%>/dashboard/hashtag/WCC_hashtag.jsp">標籤管理</a>
                                </li>
                            </ul>
                        </li>

                        <li class="sidebar-item  has-sub">
                            <a href="#" class='sidebar-link'>
                                <i class="bi bi-grid-1x2-fill"></i>
                                <span>商城管理</span>
                            </a>
                            <ul class="submenu ">
                                <li class="submenu-item ">
                                    <a href="<%=request.getContextPath()%>/dashboard/product/shopview.html">商品設定</a>
                                </li>
                                <li class="submenu-item ">
                                    <a href="<%=request.getContextPath()%>/dashboard/memberOrder/TYT_order_management.html">訂單管理</a>
                                </li>
                                <li class="submenu-item ">
                                    <a href="<%=request.getContextPath()%>/dashboard/productTag/tagview.html">商品種類管理</a>
                                </li>
                                <li class="submenu-item  ">
                                    <a href="<%=request.getContextPath()%>/dashboard/promo_code/promo_code_allview.jsp">優惠券管理</a>
                                </li>
                                <li class="submenu-item">
                                    <a href="<%=request.getContextPath()%>/dashboard/advertise/advertise_allview.jsp" >廣告管理</a>
                                </li>
                            </ul>
                        </li>
                        <li class="sidebar-item  has-sub">
                            <a href="#" class='sidebar-link'>
                                <i class="bi bi-hexagon-fill"></i>
                                <span>討論區管理</span>
                            </a>

                            <ul class="submenu ">
                                <li class="submenu-item ">
                                    <a href="<%=request.getContextPath()%>/dashboard/article/HO_dscussion_cate.jsp">看板分類</a>
                                  </li>
                                  <li class="submenu-item ">
                                    <a href="<%=request.getContextPath()%>/dashboard/article/HO_discussion_allview.jsp" >文章管理</a>
                                  </li>
                            </ul>
                        </li>
                        <li class="sidebar-item  has-sub ">
                            <a href="#" class='sidebar-link'>
                                <i class="bi bi-pen-fill"></i>
                                <span>客服中心</span>
                            </a>
                            <ul class="submenu ">
                                <li class="submenu-item ">
                                    <a href="<%=request.getContextPath()%>/dashboard/recipe_report/WCC_recipe_report.jsp">食譜檢舉</a>
                                </li>
                                <li class="submenu-item ">
                                    <a href="<%=request.getContextPath()%>/dashboard/article_report/WCC_article_report.jsp">討論區檢舉</a>
                                </li>
                                  <li class="submenu-item ">
                                    <a href="<%=request.getContextPath()%>/dashboard/article_sub_report/WCC_article_sub_report.jsp">討論區回文檢舉</a>
                                </li>
                                <li class="submenu-item ">
                                    <a href="<%=request.getContextPath()%>/dashboard/question/question-table.html">常見問題</a>
                                </li>
                                <li class="submenu-item ">
                                    <a href="<%=request.getContextPath()%>/dashboard/notifycenter/notify-table.html">系統通知</a>
                                </li>
                                <li class="submenu-item ">
                                    <a href="<%=request.getContextPath()%>/dashboard/supportform/supportform-table.html">問題表單</a>
                                </li>
                                <li class="submenu-item active">
                                <a href="<%=request.getContextPath()%>/dashboard/news/news-table.html">最新消息</a>
                                 </li>
                            </ul>
                        </li>
						<!-- ======================================================================================================== -->
									</ul>
				</div>
				<button class="sidebar-toggler btn x">
					<i data-feather="x"></i>
				</button>
			</div>
		</div>
		
		<div id="main">
			<header class="mb-3">
				<a href="#" class="burger-btn d-block d-xl-none"> <i
					class="bi bi-justify fs-3"></i>
				</a>
			</header>

			<div class="page-heading">
				<div class="page-title">
					<div class="row">
						<div class="col-12 col-md-6 order-md-1 order-last">
							<h3>廣告設定</h3>
							<p class="text-subtitle text-muted">Multiple form layout you
								can use</p>
								<%-- 錯誤表列 --%>
		<c:if test="${not empty errorMsgs}">
			<font style="color: red">請修正以下錯誤:</font>
			<ul>
				<c:forEach var="message" items="${errorMsgs}">
					<li style="color: red">${message}</li>
				</c:forEach>
			</ul>
		</c:if>
						</div>
						<div class="col-12 col-md-6 order-md-2 order-first">
							<nav aria-label="breadcrumb"
								class="breadcrumb-header float-start float-lg-end">
								<ol class="breadcrumb">
									<li class="breadcrumb-item"><a
										href="<%=request.getContextPath()%>/dashboard/advertise/advertise_allview.jsp">廣告總覽</a></li>
									<li class="breadcrumb-item active" aria-current="page">廣告設定</li>
								</ol>
							</nav>
						</div>
					</div>
				</div>

				<!-- Basic Vertical form layout section start -->
				<section id="basic-vertical-layouts">
					<div class="row match-height">
						<div class="col-md-12 col-12">
							<div class="card">
								<div class="card-header">
									<h4 class="card-title">廣告設定</h4>
								</div>
								<div class="card-content">
									<div class="card-body">
										<form class="form form-vertical" METHOD="post"
											ACTION="<%=request.getContextPath()%>/AdvertiseServlet"
											enctype="multipart/form-data">
											<div class="form-body">
												<div class="row">
													<div class="col-12" style="margin-top: 20px;">
														<div class="form-group">
															<label for="advertiseNo-vertical">廣告編號</label> <input
																type="text" id="advertiseNo-vertical"
																class="form-control" name="advertise_no"
																value="${advertiseVO.advertiseNo}" placeholder="廣告編號">
														</div>
													</div>
													<div class="col-12">
														<input type="file" id="p_file" class="form-control"
															name="advertise_img">
														<div id="preview">
															<span class="text">預覽圖</span>
														</div>
													</div>
													<div class="col-12" style="margin-top: 20px;">
														<div class="form-group">
															<label for="advertiseName-vertical">廣告名稱</label> <input
																type="text" id="advertiseName-vertical"
																class="form-control" name="advertise_name"
																value="${advertiseVO.advertiseName}" placeholder="廣告名稱">
														</div>
													</div>
													<div class="col-12">
														<div class="form-group">
															<label for="advertiseShelfTime-vertical">上架時間</label> <input
																type="datetime-local" id="advertiseShelfTime-vertical"
																class="form-control" name="advertise_shelf_time"
																value="${advertiseVO.advertiseShelfTime}"
																placeholder="上架時間">
														</div>
													</div>
													<div class="col-12">
														<div class="form-group">
															<label for="advertiseOffsaleTime-vertical">下架時間</label> <input
																type="datetime-local"
																id="padvertiseOffsaleTime-vertical" class="form-control"
																name="advertise_offsale_time"
																value="${advertiseVO.advertiseOffsaleTime}"
																placeholder="下架時間">
														</div>
													</div>
													<div class="col-12">
														<div class="form-group">
															<label for="advertiseUrl-vertical">廣告URL</label> <input
																type="text" step="1" id="advertiseUrl-vertical"
																value="${advertiseVO.advertiseUrl}" class="form-control"
																name="advertise_url">
														</div>
													</div>
												</div>
												<div class="col-12">
													<div class="col-12 d-flex justify-content-end">
														<input type="hidden" name="action" value="update">
														<button type="submit" class="btn btn-primary me-1 mb-1">Submit</button>
														<button type="reset"
															class="btn btn-light-secondary me-1 mb-1">Reset</button>
													</div>
												</div>
											</div>
										</form>
									</div>
								</div>
							</div>
						</div>
					</div>
				</section>
				<!-- // Basic Vertical form layout section end -->



			</div>

			<footer>
				<div class="footer clearfix mb-0 text-muted">
					<div class="float-start">
						<p>2021 &copy; Mazer</p>
					</div>
					<div class="float-end">
						<p>
							Crafted with <span class="text-danger"><i
								class="bi bi-heart"></i></span> by <a href="http://ahmadsaugi.com">A.
								Saugi</a>
						</p>
					</div>
				</div>
			</footer>
		</div>
	</div>
	<script
		src="<%=request.getContextPath()%>/mazer-main/dist/assets\vendors\jquery-3.7.1.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/mazer-main/dist/assets/vendors/perfect-scrollbar/perfect-scrollbar.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/mazer-main/dist/assets/js/bootstrap.bundle.min.js"></script>

	<script
		src="<%=request.getContextPath()%>/mazer-main/dist/assets/vendors/perfect-scrollbar/perfect-scrollbar.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/mazer-main/dist/assets/js/bootstrap.bundle.min.js"></script>
	<script
		src="https://unpkg.com/filepond-plugin-file-validate-type/dist/filepond-plugin-file-validate-type.min.js"></script>
	<script>
		var preview_el = document.getElementById("preview");
		var p_file_el = document.getElementById("p_file");
		var preview_img = function(file) {

			var reader = new FileReader(); // 用來讀取檔案
			reader.readAsDataURL(file); // 讀取檔案
			reader
					.addEventListener(
							"load",
							function() {
								//console.log(reader.result);
								/*
								let img_node = document.createElement("img"); // <img>
								img_node.setAttribute("src", reader.result); // <img src="base64">
								img_node.setAttribute("class", "preview_img"); // <img src="base64" class="preview_img">
								preview_el.innerHTML = '';
								preview_el.append(img_node);
								 */

								let img_str = '<img src="' + reader.result + '" class="preview_img">';
								preview_el.innerHTML = img_str;
							});
		};

		p_file_el.addEventListener("change", function(e) {
			if (this.files.length > 0) {
				preview_img(this.files[0]);
			} else {
				preview_el.innerHTML = '<span class="text">預覽圖</span>';
			}
		});
	</script>

	<!-- Include Choices JavaScript -->
	<script
		src="<%=request.getContextPath()%>/mazer-main/dist/assets/vendors/choices.js/choices.min.js"></script>



	<script
		src="<%=request.getContextPath()%>/mazer-main/dist/assets/vendors/summernote/summernote-lite.min.js"></script>
	<script>
		let table1 = document.querySelector("#table1");
		let dataTable = new simpleDatatables.DataTable(table1);

		$('#summernote').summernote({
			tabsize : 2,
			height : 120,
		})
		$("#hint").summernote({
			height : 100,
			toolbar : false,
			placeholder : 'type with apple, orange, watermelon and lemon',
			hint : {
				words : [ 'apple', 'orange', 'watermelon', 'lemon' ],
				match : /\b(\w{1,})$/,
				search : function(keyword, callback) {
					callback($.grep(this.words, function(item) {
						return item.indexOf(keyword) === 0;
					}));
				}
			}
		});
	</script>
	<script
		src="<%=request.getContextPath()%>/mazer-main/dist/assets/js/main.js"></script>
	<script src="https://cdn.quilljs.com/1.3.6/quill.js"></script>
	<script
		src="<%=request.getContextPath()%>/mazer-main/dist/assets/js/quill.js"></script>

 <script>
document.addEventListener("DOMContentLoaded", function () {
$("a#logout").on("click",function(e){
    e.preventDefault();
var formlogout = $("<form>", {
action: "<%=request.getContextPath()%>/LoginServlet", // 表单提交的URL
    method: "post", // 提交方法，可以是 "post" 或 "get"，根据需求设置
});

formlogout.append($("<input>", {
type: "hidden",
name: "action",
value: "logout"
}));
   formlogout.appendTo("body").hide();
   formlogout.submit();
   formlogout.remove();



    
})


$("a#design").on("click",function(e){
    e.preventDefault();
	var formdesign = $("<form>", {
	action: "<%=request.getContextPath()%>/AdminsServlet", // 表单提交的URL
	    method: "post", // 提交方法，可以是 "post" 或 "get"，根据需求设置
	});

	formdesign.append($("<input>", {
	type: "hidden",
	name: "action",
	value: "design"
	}));
	formdesign.appendTo("body").hide();
	formdesign.submit();
	formdesign.remove();
	
	
})



})
</script>
</body>

</html>