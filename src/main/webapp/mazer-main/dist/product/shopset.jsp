<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.cooklab.product.model.*"%>

<%
//見com.emp.controller.EmpServlet.java第238行存入req的empVO物件 (此為輸入格式有錯誤時的empVO物件)
ProductVO productVO = (ProductVO) request.getAttribute("productVO");
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
</style>

</head>

<body>
	<div id="app">
		<div id="sidebar" class="active">
			<div class="sidebar-wrapper active">
				<div class="sidebar-header">
					<div class="d-flex justify-content-between">
						<div class="logo">
							<a href="index.html"><img src="assets/images/logo/logo.png"
								alt="Logo" srcset=""></a>
						</div>
						<div class="toggler">
							<a href="#" class="sidebar-hide d-xl-none d-block"><i
								class="bi bi-x bi-middle"></i></a>
						</div>
					</div>
				</div>
				<div class="sidebar-menu">
					<ul class="menu">
						<li class="sidebar-title">Menu</li>
						<!-- ============================================================================================== -->
						<li class="sidebar-item  "><a href="WCC_Homepage.html"
							class='sidebar-link'> <i class="bi bi-grid-fill"></i> <span>後台首頁</span>
						</a></li>


						<li class="sidebar-item "><a href="WCC_memeber.html"
							class='sidebar-link'> <i
								class="bi bi-file-earmark-spreadsheet-fill"></i> <span>會員管理</span>
						</a></li>


						<li class="sidebar-item  has-sub "><a href="#"
							class='sidebar-link'> <i class="bi bi-stack"></i> <span>權限管理</span>
						</a>
							<ul class="submenu ">

								<li class="submenu-item "><a
									href=".\WCC_permission_management.html">管理管理者</a></li>

								<li class="submenu-item "><a
									href=".\WCC_permission_createrule.html">創立權限規則</a></li>

							</ul></li>

						<li class="sidebar-item has-sub "><a href="#"
							class="sidebar-link"> <i class="bi bi-collection-fill"></i> <span>食譜管理</span>
						</a>
							<ul class="submenu">
								<li class="submenu-item"><a href=".\recipe_form.html">食譜表單</a>
								</li>
								<li class="submenu-item "><a href=".\hashtag_form.html">標籤管理</a>
								</li>
							</ul></li>

						<li class="sidebar-item  has-sub"><a href="#"
							class='sidebar-link'> <i class="bi bi-grid-1x2-fill"></i> <span>商城管理</span>
						</a>
							<ul class="submenu " style="display: block;">
								<li class="submenu-item active"><a href=".\shopview.html">商品設定</a>
								</li>
								<li class="submenu-item "><a
									href="TYT_order_management.html">訂單管理</a></li>
								<li class="submenu-item"><a href=".\GCpromo_info.html">新增優惠券</a>
								</li>
								<li class="submenu-item  "><a href=".\GCpromo.html">優惠券管理</a>
								</li>
								<li class="submenu-item"><a href=".\GCadvertise.html">廣告管理</a>
								</li>
								<li class="submenu-item"><a href=".\GCadvertise_info.html">新增廣告</a>
								</li>
								<li class="submenu-item "><a
									href="TYT_purchase_order_allView.html">進貨表單</a></li>
							</ul></li>
						<li class="sidebar-item  has-sub"><a href="#"
							class='sidebar-link'> <i class="bi bi-hexagon-fill"></i> <span>討論區管理</span>
						</a>

							<ul class="submenu ">
								<li class="submenu-item "><a
									href=".\HO_discussion_cate.html">看板分類</a></li>
								<li class="submenu-item "><a
									href=".\HO_discussion_info.html">文章管理</a></li>
							</ul></li>
						<li class="sidebar-item  has-sub"><a href="#"
							class='sidebar-link'> <i class="bi bi-pen-fill"></i> <span>數據分析</span>
						</a>
							<ul class="submenu ">
								<li class="submenu-item "><a href="#">會員數據</a></li>
								<li class="submenu-item "><a href="#">食譜數據</a></li>
								<li class="submenu-item "><a href="#">商城數據</a></li>
								<li class="submenu-item "><a href="#">文章數據</a></li>
							</ul></li>
						<li class="sidebar-item  has-sub "><a href="#"
							class='sidebar-link'> <i class="bi bi-pen-fill"></i> <span>客服中心</span>
						</a>
							<ul class="submenu ">
								<li class="submenu-item "><a href="WCC_recipe_report.html">食譜檢舉</a>
								</li>
								<li class="submenu-item "><a
									href="WCC_recipe_sub_report.html">食譜回文檢舉</a></li>
								<li class="submenu-item "><a href="WCC_article_report.html">討論區檢舉</a>
								</li>
								<li class="submenu-item "><a
									href="WCC_official_notify.html">系統通知</a></li>
								<li class="submenu-item "><a
									href="ding-support-tickets-table.html">問題表單</a></li>
							</ul></li>
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
							<h3>商品設定</h3>
							<p class="text-subtitle text-muted">Multiple form layout you
								can use</p>
						</div>
						<div class="col-12 col-md-6 order-md-2 order-first">
							<nav aria-label="breadcrumb"
								class="breadcrumb-header float-start float-lg-end">
								<ol class="breadcrumb">
									<li class="breadcrumb-item"><a href="./shopview.html">商品總覽</a></li>
									<li class="breadcrumb-item active" aria-current="page">商品設定</li>
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
									<h4 class="card-title">商品設定</h4>
								</div>
								<div class="card-content">
									<div class="card-body">
										<form class="form form-vertical" METHOD="post"
											ACTION="<%=request.getContextPath()%>/ProductServlet"
											enctype="multipart/form-data">
											<div class="form-body">
												<div class="row">
													<div class="col-12">
														<input type="file" id="p_file" class="form-control">
														<div id="preview">
															<span class="text">預覽圖</span>
														</div>
													</div>
													<div class="col-12" style="margin-top: 20px;">
														<div class="form-group">
															<label for="productname-vertical">商品名稱</label> <input
																type="text" id="productname-vertical"
																class="form-control" name="productname"
																value="${productVO.productName}" placeholder="商品名稱">
														</div>
													</div>
													<div class="col-12">
														<div class="form-group">
															<label for="productprice-vertical">商品售價</label> <input
																type="number" id="productprice-vertical"
																class="form-control" name="productprice"
																value="${productVO.productPrice}" placeholder="商品售價">
														</div>
													</div>
													<div class="col-12">
														<div class="form-group">
															<label for="productprice-vertical">商品數量</label> <input
																type="number" id="productprice-vertical"
																class="form-control" name="productprice"
																value="${productVO.saleQty}" placeholder="商品數量">
														</div>
													</div>
													<div class="col-12">
														<div class="form-group">
															<label for="productprice-vertical">商品庫存</label> <input
																type="number" id="productprice-vertical"
																class="form-control" name="productprice"
																value="${productVO.storageQty}" placeholder="商品庫存">
														</div>
													</div>
													<div class="col-12">
														<div class="form-group">
															<label for="uptime-vertical">上架時間</label> <input
																type="datetime-local" step="1" id="uptime-vertical"
																value="${productVO.shelfTime}" class="form-control"
																name="uptime">
														</div>
													</div>
													<div class="col-12">
														<div class="form-group">
															<label for="downtime-vertical">下架時間</label> <input
																type="datetime-local" step="1" id="downtime-vertical"
																value="${productVO.offsaleTime}" class="form-control"
																name="downtime">
														</div>
													</div>
													<div class="row">
														<div class="col-md-6 mb-4">
															<h6>食材標籤</h6>
															<div class="form-group">
																<jsp:useBean id="ingredientSvc" scope="page"
																	class="com.cooklab.ingredient_category.model.IngredientService" />
																<tr>
																	<td><select size="1" name="ingredientCategoryNo"
																		class="choices form-select">
																			<c:forEach var="ingredientCategoryVO"
																				items="${ingredientSvc.all}">
																				<option
																					value="${ingredientCategoryVO.ingredientCategoryNo}"
																					${(productVO.ingredientCategoryNo==ingredientCategoryVO.ingredientCategoryNo)? 'selected':'' }>${ingredientCategoryVO.categoryName}
																			</c:forEach>
																	</select></td>
																</tr>
															</div>
														</div>
													</div>
													<div class="row">
														<div class="col-md-6 mb-4">
															<h6>廚具標籤</h6>
															<div class="form-group">
																<jsp:useBean id="kitchenwareCategorySvc" scope="page"
																	class="com.cooklab.kitchenware_category.model.KitchenwareCategoryService" />
																<tr>
																	<td><select size="1" name="kitchenwareCategoryNo"
																		class="choices form-select">
																			<c:forEach var="KitchenwareCategoryVO"
																				items="${kitchenwareCategorySvc.all}">
																				<option
																					value="${KitchenwareCategoryVO.kitchenwareCategoryNo}"
																					${(productVO.kitchenwareCategoryNo==KitchenwareCategoryVO.kitchenwareCategoryNo)? 'selected':'' }>${KitchenwareCategoryVO.categoryName}
																			</c:forEach>
																	</select></td>
																</tr>
															</div>
														</div>
													</div>
													<div class="col-12">
														<div class="card">
															<div class="card-header"
																style="color: rgba(35, 28, 99, .7); font-weight: 600; padding-left: 0;">
																商品簡介</div>
															<div class="card-body" style="padding-left: 0;">
																<div class="form-floating">
																	<textarea class="form-control" id="floatingTextarea" name="productDec"
																		value="${productVO.productDec}"></textarea>
																	<label for="floatingTextarea">簡介</label>
																</div>
															</div>
														</div>
													</div>
													<div class="row">
														<div class="col-12">
															<div class="card">
																<div class="card-header" style="padding-left: 0;">
																	<h4 class="card-title">商品詳情</h4>
																</div>
																<div class="card-body" style="padding-left: 0;">
																	<div style="width: auto; height: auto;">
																		<div id="editor" contenteditable="false"></div>
																		<!--如果改成 true就不能複製貼上WHY?  -->
																		<textarea id="hiddenContent" name="productIntroduction"
																			style="display: none;"
																			value="${productVO.productIntroduction}"></textarea>
																	</div>
																</div>
															</div>
														</div>
														<div class="col-12">
															<div class="col-12 d-flex justify-content-end">
																<input type="hidden" name="action" value="insert">
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


</body>

</html>