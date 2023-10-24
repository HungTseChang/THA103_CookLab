<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.cooklab.promo_code.model.*"%>

<%
PromoCodeService pcSvc = new PromoCodeService();
List<PromoCodeVO> list = pcSvc.getAll();
pageContext.setAttribute("list", list);

PromoCodeService pcSvc2 = new PromoCodeService();
List<PromoCodeVO> list2 = pcSvc2.getAll();
pageContext.setAttribute("list2", list2);
%>
<!DOCTYPE html>
<html lang="zh-tw">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>DataTable - Mazer Admin Dashboard</title>

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
								<li class="submenu-item "><a href="#">新增優惠券</a></li>
								<li class="submenu-item active "><a href="#">優惠券管理</a></li>
								<li class="submenu-item "><a href="#">廣告管理</a></li>
								<li class="submenu-item"><a href="#">廣告管理</a></li>
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
</style>
			<!-- ////////////////////////////////////////////////////////////////////////////////////////// -->
			<div class="page-heading">
				<div class="page-title">
					<div class="row">
						<div class="col-12 col-md-6 order-md-1 order-last">
							<h3>優惠券總覽</h3>
							<p class="text-subtitle text-muted">For user to check they
								list</p>
						</div>
						<div class="col-12 col-md-6 order-md-2 order-first">
							<nav aria-label="breadcrumb"
								class="breadcrumb-header float-start float-lg-end">
								<ol class="breadcrumb">
									<li class="breadcrumb-item"><a href="./promo_code_set.jsp">優惠券設定</a></li>
									<li class="breadcrumb-item active" aria-current="page">優惠券總覽</li>
								</ol>
							</nav>
						</div>
					</div>
				</div>
				<section class="section">
					<div class="card">
						<div class="card-header">優惠碼總覽</div>
						<div class="card-body">
							<table class="table table-striped" id="table1">
								<thead>
									<tr>
										<th>優惠碼編號</th>
										<th>優惠碼名稱</th>
										<th>優惠碼序號</th>
										<th>優惠碼開始</th>
										<th>優惠碼結束</th>
										<th>優惠碼總共次數</th>
										<th>優惠碼剩餘次數</th>
									</tr>
								</thead>
								<tbody>

									<tr>
										<td>0000001</td>
										<td>滿兩千免運費</td>
										<td>ZXC1234</td>
										<td>2023/01/01</td>
										<td>2023/12/30</td>
										<td>100</td>
										<td>20</td>
										<td><span class="badge bg-success">上架中</span></td>
										<td><button class="wcc">刪除優惠碼</button></td>
									</tr>
									<tr>
										<td>0000002</td>
										<td>滿兩千免運費</td>
										<td>ZXC1234</td>
										<td>2023/01/01</td>
										<td>2023/12/30</td>
										<td>100</td>
										<td>50</td>
										<td><span class="badge bg-success">上架中</span></td>
										<td><button class="wcc">刪除優惠碼</button></td>
									</tr>
									<tr>
										<td>0000003</td>
										<td>滿兩千免運費</td>
										<td>ZXC1234</td>
										<td>2023/01/01</td>
										<td>2023/12/30</td>
										<td>100</td>
										<td>50</td>
										<td><span class="badge bg-success">上架中</span></td>
										<td><button class="wcc">刪除優惠碼</button></td>
									</tr>
									<tr>
										<td>0000004</td>
										<td>滿兩千免運費</td>
										<td>ZXC1234</td>
										<td>2023/01/01</td>
										<td>2023/12/30</td>
										<td>100</td>
										<td>50</td>
										<td><span class="badge bg-success">上架中</span></td>
										<td><button class="wcc">刪除優惠碼</button></td>
									</tr>
									<tr>
										<td>0000005</td>
										<td>滿兩千免運費</td>
										<td>ZXC1234</td>
										<td>2023/01/01</td>
										<td>2023/12/30</td>
										<td>100</td>
										<td>50</td>
										<td><span class="badge bg-success">上架中</span></td>
										<td><button class="wcc">刪除優惠碼</button></td>
									</tr>
									<tr>
										<td>0000006</td>
										<td>滿兩千免運費</td>
										<td>ZXC1234</td>
										<td>2023/01/01</td>
										<td>2023/12/30</td>
										<td>100</td>
										<td>50</td>
										<td><span class="badge bg-success">上架中</span></td>
										<td><button class="wcc">刪除優惠碼</button></td>
									</tr>
									<tr>
										<td>0000007</td>
										<td>新會員首購滿一千折兩百</td>
										<td>ASD1234</td>
										<td>2023/01/01</td>
										<td>2023/01/30</td>
										<td>100</td>
										<td>0</td>
										<td><span class="badge bg-danger">下架</span></td>
										<td><button class="wcc">刪除優惠碼</button></td>
									</tr>

								</tbody>
							</table>
						</div>
					</div>

				</section>
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
	<script>
        document.addEventListener("DOMContentLoaded", function () {
            $("button.wcc").on('click', (e) => {
                Swal.fire({
                    icon: "success",
                    title: "刪除優惠券成功!"
                })
            })




        })
    </script>
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