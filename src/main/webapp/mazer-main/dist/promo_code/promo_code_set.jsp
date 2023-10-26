<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.cooklab.promo_code.model.*"%>
<%
PromoCodeVO promoCodeVO = (PromoCodeVO) request.getAttribute("promoCodeVO");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="BIG5">
<title>Insert title here</title>
<link rel="preconnect" href="https://fonts.gstatic.com" />

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
td a.wcc {
	border: 1px solid rgb(151, 135, 249);
	background-color: rgb(195, 241, 253);
	padding: 4px;
	border-radius: 20px;
}

td button.wcc {
	border-radius: 20px;
}

input.WCC_memeber_info {
	width: 60%;
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
						<li class="sidebar-item "><a href="WCC_Homepage.html"
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
									href=".\WCC_permission_createnew.html">創建管理</a></li>
								<li class="submenu-item "><a
									href=".\WCC_permission_management.html">管理管理者</a></li>
								<li class="submenu-item "><a href="#">編輯管理者</a></li>
								<li class="submenu-item "><a
									href=".\WCC_permission_createrule.html">創立權限規則</a></li>
								<li class="submenu-item "><a href="#">權限管理-編輯/查詢權限規則-查詢</a>
								</li>
							</ul></li>

						<li class="sidebar-item  has-sub"><a href="#"
							class='sidebar-link'> <i class="bi bi-collection-fill"></i> <span>食譜管理</span>
						</a> <!-- <ul class="submenu ">
        <li class="submenu-item ">
            <a href="#">食譜管理</a>
        </li>
    </ul>
</li> -->
						<li class="sidebar-item  has-sub active"><a href="#"
							class='sidebar-link'> <i class="bi bi-grid-1x2-fill"></i> <span>商城管理</span>
						</a>
							<ul class="submenu " style="display: block">
								<li class="submenu-item "><a href="#">商品設定</a></li>
								<li class="submenu-item "><a href="#">訂單管理</a></li>
								<li class="submenu-item active "><a href="#">新增優惠券</a></li>
								<li class="submenu-item "><a href="#">優惠券管理</a></li>
								<li class="submenu-item "><a href="#">新增廣告</a></li>
								<li class="submenu-item "><a href="#">廣告管理</a></li>
								<li class="submenu-item "><a href="#">新增進貨表單</a></li>
							</ul></li>
						<li class="sidebar-item  has-sub"><a href="#"
							class='sidebar-link'> <i class="bi bi-hexagon-fill"></i> <span>討論區管理</span>
						</a>

							<ul class="submenu ">
								<li class="submenu-item "><a href="#">看板分類</a></li>
								<li class="submenu-item "><a href="#">文章管理</a></li>
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
						<li class="sidebar-item  has-sub"><a href="#"
							class='sidebar-link'> <i class="bi bi-pen-fill"></i> <span>客服中心</span>
						</a>
							<ul class="submenu ">
								<li class="submenu-item "><a href="#">食譜檢舉</a></li>
								<li class="submenu-item "><a href="#">討論區檢舉</a></li>
								<li class="submenu-item "><a href="#">系統通知</a></li>
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
			<!--/////////////////////////////////////////////////////////////////////////////////////////  -->

			<!-- ////////////////////////////////////////////////////////////////////////////////////////// -->
			<div class="page-heading">
				<div class="page-title">
					<div class="row">
						<div class="col-12 col-md-6 order-md-1 order-last">
							<h3>優惠券設定</h3>
							<p class="text-subtitle text-muted">For user to check they
								list</p>
						</div>
						<div class="col-12 col-md-6 order-md-2 order-first">
							<nav aria-label="breadcrumb"
								class="breadcrumb-header float-start float-lg-end">
								<ol class="breadcrumb">
									<li class="breadcrumb-item"><a
										href="./promo_code_allview.jsp">優惠券管理</a></li>
									<li class="breadcrumb-item active" aria-current="page">優惠券設定</li>
								</ol>
							</nav>
						</div>
					</div>
				</div>
				<!-- /////////////////////////////////////////////////////// -->

			</div>
			<div class="container">
				<div class="row" style="background-color: white;">
					<div class="col-lg-12">

						<section id="basic-horizontal-layouts">
							<div class="row match-height" style="position: relative;">
								<div class="col-md-8 col-8">
									<div class="card">
										<div class="card-header">
											<h4 class="card-title">優惠券設定</h4>
										</div>
										<div class="card-content">
											<div class="card-body">
												<form class="form form-horizontal" METHOD="post"
													ACTION="<%=request.getContextPath()%>/PromoCodeServlet">
													<div class="form-body">
														<div class="row">
															<!--  				
															<div class="col-md-4">
																<label>優惠碼編號</label>
															</div>
															<div class="col-md-8 form-group">
																<input type="text" class="GCpromo_info" id="promo_id"
																	name="promo_info" value="000001">
																	
															</div>
															<div class="col-md-4">
																<label>優惠碼名稱</label>
															</div>
															<div class="col-md-8 form-group">
																<input type="text" id="promo_name" class="GCpromo_info"
																	name="promo_info" value="滿兩千免運費">
															</div>
															-->
															<div class="col-md-4">
																<label>優惠碼序號</label>
															</div>
															<div class="col-md-8 form-group">
																<input type="text" id="" class="GCpromo_info"
																	name="promo_code_serial_number" value="ZXC1234">
															</div>
															<div class="col-md-4">
																<label>生效時間</label>
															</div>
															<div class="col-md-8 form-group">
																<input type="datetime-local" step="1"
																	class="GCpromo_info" name="start_time" id=""
																	value="${promoCodeVO.startTime.toLocalDateTime().format(DateTimeFormatter.ofPattern('yyyy-MM-dd\'T\'HH:mm:ss'))}">
															</div>
															<div class="col-md-4">
																<label>失效時間</label>
															</div>
															<div class="col-md-8 form-group">
																<input type="datetime-local" step="1"
																	class="GCpromo_info" name="end_time" id=""
																	value="${promoCodeVO.endTime.toLocalDateTime().format(DateTimeFormatter.ofPattern('yyyy-MM-dd\'T\'HH:mm:ss'))}">
															</div>
															<div class="col-md-4">
																<label>百分比折價金額</label>
															</div>
															<div class="col-md-8 form-group">
																<input type="text" class="GCpromo_info"
																	name="percentage_discount_amount" id="" value="100">
															</div>
															<div class="col-md-4">
																<label>固定折價金額</label>
															</div>
															<div class="col-md-8 form-group">
																<input type="text" class="GCpromo_info"
																	name="fixed_discount_amount" id="" value="100">
															</div>
															<div class="col-md-4">
																<label>可使用次數</label>
															</div>
															<div class="col-md-8 form-group">
																<input type="text" class="GCpromo_info"
																	name="usages_allowed" id="" value="100">
															</div>
															<div class="col-md-4">
																<label>最低消費門檻</label>
															</div>
															<div class="col-md-8 form-group">
																<input type="text" class="GCpromo_info"
																	name="minimum_consumption" id="" value="50">
															</div>
														</div>
													</div>
													<div class="col-md-6 ">
														<input type="hidden" name="action" value="insert">
														<input type="submit" value="送出新增">
													</div>
												</form>
											</div>
										</div>
									</div>
								</div>

							</div>

							<!-- ///////////////// -->
							<div class="col-md-12 col-8"></div>

						</section>
					</div>

				</div>

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
		// Simple Datatable
		let table1 = document.querySelector('#table1');
		let dataTable = new simpleDatatables.DataTable(table1);
	</script>
	<script
		src="<%=request.getContextPath()%>/mazer-main/dist/assets/js/main.js"></script>
	<script src="https://cdn.quilljs.com/1.3.6/quill.js"></script>
	<script
		src="<%=request.getContextPath()%>/mazer-main/dist/assets/js/quill.js"></script>
	<script
		src="<%=request.getContextPath()%>/mazer-main/dist/assets/vendors/choices.js/choices.min.js"></script>



	<script
		src="<%=request.getContextPath()%>/mazer-main/dist/assets/vendors/summernote/summernote-lite.min.js"></script>
</body>


</html>