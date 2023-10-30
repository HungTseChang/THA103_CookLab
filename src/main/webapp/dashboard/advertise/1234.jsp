<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.cooklab.advertise.model.*"%>

<%
//��com.emp.controller.EmpServlet.java��238��s�Jreq��empVO���� (������J�榡�����~�ɪ�empVO����)
AdvertiseVO advertiseVO = (AdvertiseVO) request.getAttribute("advertiseVO");
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
	rel="stylesheet" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/dashboard/assets/css/bootstrap.css" />

<link rel="stylesheet"
	href="<%=request.getContextPath()%>/dashboard/assets/vendors/simple-datatables/style.css" />

<link rel="stylesheet"
	href="<%=request.getContextPath()%>/dashboard/assets/vendors/perfect-scrollbar/perfect-scrollbar.css" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/dashboard/assets/vendors/bootstrap-icons/bootstrap-icons.css" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/dashboard/assets/css/app.css" />
<link rel="shortcut icon"
	href="<%=request.getContextPath()%>/dashboard/assets/images/favicon.svg"
	type="image/x-icon" />
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
								<li class="submenu-item"><a href=".\shopview.html">�ӫ~�]�w</a>
								</li>
								<li class="submenu-item "><a
									href="TYT_order_management.html">�q��޲z</a></li>
								<li class="submenu-item"><a href=".\GCpromo_info.html">�s�W�u�f��</a>
								</li>
								<li class="submenu-item  "><a href=".\GCpromo.html">�u�f��޲z</a>
								</li>
								<li class="submenu-item"><a href=".\GCadvertise.html">�s�i�޲z</a>
								</li>
								<li class="submenu-item active"><a
									href=".\GCadvertise_info.html">�s�W�s�i</a></li>
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
									href="ding-support-tickets-table.html">���D���</a>
									</li>
							</ul></li>
						<!-- ======================================================================================================== -->
					</ul>
				</div>
				<button class="sidebar-toggler btn x"><i data-feather="x"></i></button>
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
							<h3>�s�i�]�w</h3>
							<p class="text-subtitle text-muted">Multiple form layout you
								can use</p>
						</div>
						<div class="col-12 col-md-6 order-md-2 order-first">
							<nav aria-label="breadcrumb"
								class="breadcrumb-header float-start float-lg-end">
								<ol class="breadcrumb">
									<li class="breadcrumb-item"><a
										href="<%=request.getContextPath()%>/dashboard/advertise/advertise_allview.jsp">�s�i�`��</a></li>
									<li class="breadcrumb-item active" aria-current="page">�s�i�]�w</li>
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
									<h4 class="card-title">�s�i�]�w</h4>
								</div>
								<div class="card-content">
									<div class="card-body">
										<form class="form form-vertical" METHOD="post"
											ACTION="<%=request.getContextPath()%>/AdvertiseServlet"
											enctype="multipart/form-data">
											<div class="form-body">
												<div class="row">
													<div class="col-12">
														<input type="file" id="p_file" class="form-control"
															name="advertise_img">
														<div id="preview">
															<span class="text">�w����</span>
														</div>
													</div>
													<div class="col-12" style="margin-top: 20px;">
														<div class="form-group">
															<label for="advertiseName-vertical">�s�i�W��</label> <input
																type="text" id="advertiseName-vertical"
																class="form-control" name="advertise_name"
																value="1" placeholder="�s�i�W��">
														</div>
													</div>
													<div class="col-12">
														<div class="form-group">
															<label for="advertiseShelfTime-vertical">�W�[�ɶ�</label> 
														</div>
													</div>
													<div class="col-12">
														<div class="form-group">
															<label for="advertiseOffsaleTime-vertical">�U�[�ɶ�</label> 
														</div>
													</div>
													<div class="col-12">
														<div class="form-group">
															<label for="advertiseUrl-vertical">�s�iURL</label> <input
																type="text" step="1" id="advertiseUrl-vertical"
																value="1" class="form-control"
																name="advertise_url">
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
		src="<%=request.getContextPath()%>/dashboard/assets/vendors/jquery-3.7.1.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/dashboard/assets/vendors/perfect-scrollbar/perfect-scrollbar.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/dashboard/assets/js/bootstrap.bundle.min.js"></script>
	<script src="<%=request.getContextPath()%>/dashboard/assets/vendors/simple-datatables/simple-datatables.js"></script>

	<script src="<%=request.getContextPath()%>/dashboard/assets/js/main.js"></script>
	<script src="<%=request.getContextPath()%>/dashboard/assets/js/menu_ative.js"></script>


</body>

</html>