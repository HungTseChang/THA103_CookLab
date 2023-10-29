<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.cooklab.product.model.*"%>
<%-- �����m�߱ĥ� EL ���g�k���� --%>

<%
ProductService productSvc = new ProductService();
List<ProductVO> list = productSvc.getAll();
pageContext.setAttribute("list", list);
%>
<!DOCTYPE html>
<html lang="en">

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

<link href="https://cdn.quilljs.com/1.3.6/quill.snow.css"
	rel="stylesheet" />
</head>

<body>
	<div id="app">
		<div id="sidebar" class="active">
			<div class="sidebar-wrapper active">
				<div class="sidebar-header">
					<div class="d-flex justify-content-between">
						<div class="logo">
							<a href="index.html"><img src="<%=request.getContextPath()%>/mazer-main/dist/assets/images/logo/logo.png"
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
							class='sidebar-link'> <i class="bi bi-grid-fill"></i> <span>��x����</span>
						</a></li>


						<li class="sidebar-item "><a href="WCC_memeber.html"
							class='sidebar-link'> <i
								class="bi bi-file-earmark-spreadsheet-fill"></i> <span>�|���޲z</span>
						</a></li>


						<li class="sidebar-item  has-sub "><a href="#"
							class='sidebar-link'> <i class="bi bi-stack"></i> <span>�v���޲z</span>
						</a>
							<ul class="submenu ">

								<li class="submenu-item "><a
									href=".\WCC_permission_management.html">�޲z�޲z��</a></li>

								<li class="submenu-item "><a
									href=".\WCC_permission_createrule.html">�Х��v���W�h</a></li>

							</ul></li>

						<li class="sidebar-item has-sub "><a href="#"
							class="sidebar-link"> <i class="bi bi-collection-fill"></i> <span>���к޲z</span>
						</a>
							<ul class="submenu">
								<li class="submenu-item"><a href=".\recipe_form.html">���Ъ��</a>
								</li>
								<li class="submenu-item "><a href=".\hashtag_form.html">���Һ޲z</a>
								</li>
							</ul></li>

						<li class="sidebar-item  has-sub"><a href="#"
							class='sidebar-link'> <i class="bi bi-grid-1x2-fill"></i> <span>�ӫ��޲z</span>
						</a>
							<ul class="submenu " style="display: block;">
								<li class="submenu-item active"><a href=".\shopview.html">�ӫ~�]�w</a>
								</li>
								<li class="submenu-item "><a
									href="TYT_order_management.html">�q��޲z</a></li>
								<li class="submenu-item"><a href=".\GCpromo_info.html">�s�W�u�f��</a>
								</li>
								<li class="submenu-item  "><a href=".\GCpromo.html">�u�f��޲z</a>
								</li>
								<li class="submenu-item"><a href=".\GCadvertise.html">�s�i�޲z</a>
								</li>
								<li class="submenu-item"><a href=".\GCadvertise_info.html">�s�W�s�i</a>
								</li>
								<li class="submenu-item "><a
									href="TYT_purchase_order_allView.html">�i�f���</a></li>
							</ul></li>
						<li class="sidebar-item  has-sub"><a href="#"
							class='sidebar-link'> <i class="bi bi-hexagon-fill"></i> <span>�Q�װϺ޲z</span>
						</a>

							<ul class="submenu ">
								<li class="submenu-item "><a
									href=".\HO_discussion_cate.html">�ݪO����</a></li>
								<li class="submenu-item "><a
									href=".\HO_discussion_info.html">�峹�޲z</a></li>
							</ul></li>
						<li class="sidebar-item  has-sub"><a href="#"
							class='sidebar-link'> <i class="bi bi-pen-fill"></i> <span>�ƾڤ��R</span>
						</a>
							<ul class="submenu ">
								<li class="submenu-item "><a href="#">�|���ƾ�</a></li>
								<li class="submenu-item "><a href="#">���мƾ�</a></li>
								<li class="submenu-item "><a href="#">�ӫ��ƾ�</a></li>
								<li class="submenu-item "><a href="#">�峹�ƾ�</a></li>
							</ul></li>
						<li class="sidebar-item  has-sub "><a href="#"
							class='sidebar-link'> <i class="bi bi-pen-fill"></i> <span>�ȪA����</span>
						</a>
							<ul class="submenu ">
								<li class="submenu-item "><a href="WCC_recipe_report.html">�������|</a>
								</li>
								<li class="submenu-item "><a
									href="WCC_recipe_sub_report.html">���Ц^�����|</a></li>
								<li class="submenu-item "><a href="WCC_article_report.html">�Q�װ����|</a>
								</li>
								<li class="submenu-item "><a
									href="WCC_official_notify.html">�t�γq��</a></li>
								<li class="submenu-item "><a
									href="ding-support-tickets-table.html">���D���</a></li>
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
							<h3>�ӫ~�`��</h3>
							<p class="text-subtitle text-muted">For user to check they
								list</p>
						</div>
						<div class="col-12 col-md-6 order-md-2 order-first">
							<nav aria-label="breadcrumb"
								class="breadcrumb-header float-start float-lg-end">
								<ol class="breadcrumb">
									<li class="breadcrumb-item"><a href="index.html">����</a></li>
									<li class="breadcrumb-item active" aria-current="page">�ӫ~�`��</li>
								</ol>
							</nav>
						</div>
					</div>
				</div>
				<section class="section">
					<div class="card">
						<div class="row">
							<div class="col-12">
								<div class="card-header">
									<a class="btn btn-success rounded-pill"
										href="<%=request.getContextPath()%>/mazer-main/dist/product/shopset.jsp"
										style="margin-left: 20px;">�s�W�ӫ~</a>
								</div>
							</div>
						</div>
						<div class="card-body">
							<table class="table table-striped" id="table1">
								<thead>
									<tr>
										<th>�ӫ~�s��</th>
										<th>�ӫ~�Ϥ�</th>
										<th>�ӫ~�W��</th>
										<th>�ӫ~���</th>
										<th>�U�[�ɶ�</th>
										<th>�W�[�ɶ�</th>
										<th>�w�s�ƶq</th>
										<th>�ӫ~����</th>
										<th></th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="productVO" items="${list}">
										<tr>
											<td>${productVO.productNo}</td>
											<td><img style="max-width: 200px; max-height: 200px;"
												src="<%= request.getContextPath() %>/ProductImgServlet?productNo=${productVO.productNo}"></td>
											<td>${productVO.productName}</td>
											<td>${productVO.productPrice}</td>
											<td>${productVO.offsaleTime}</td>
											<td>${productVO.shelfTime}</td>
											<td>${productVO.storageQty}</td>
											<c:if test="${not empty productVO.ingredientCategoryNo}">
												<td>${productVO.ingredientCategory.categoryName}</td>
											</c:if>
											<c:if test="${not empty productVO.kitchenwareCategoryNo}">
												<td>${productVO.KitchenwareCategory.categoryName}</td>
											</c:if>
											<td>
												<FORM METHOD="post"
													ACTION="<%=request.getContextPath()%>/ProductServlet"
													style="margin-bottom: 0px;">
													<input type="submit" value="�ק�" class="btn btn-info">
													<input type="hidden" name="productNo"
														value="${productVO.productNo}"> <input
														type="hidden" name="action" value="getOne_For_Update">
												</FORM>
											</td>
										</tr>
									</c:forEach>
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
	<script
		src="<%=request.getContextPath()%>/mazer-main/dist/assets\vendors\jquery-3.7.1.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/mazer-main/dist/assets/vendors/perfect-scrollbar/perfect-scrollbar.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/mazer-main/dist/assets/js/bootstrap.bundle.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/mazer-main/dist/assets/vendors/sweetalert2/sweetalert2.all.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/mazer-main/dist/assets/vendors/simple-datatables/simple-datatables.js"></script>
	<script>
		// Simple Datatable
		let table1 = document.querySelector('#table1');
		let dataTable = new simpleDatatables.DataTable(table1);
	</script>

	<script
		src="<%=request.getContextPath()%>/mazer-main/dist/assets/js/main.js"></script>
	<script
		src="<%=request.getContextPath()%>/mazer-main/dist/assets\js\menu_ative.js"></script>

</body>

</html>