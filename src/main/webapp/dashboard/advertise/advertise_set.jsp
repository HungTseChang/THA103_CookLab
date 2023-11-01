<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
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
							<a href=""><img
								src="<%=request.getContextPath()%>/dashboard/assets/images/logo/logo.png"
								alt="Logo" srcset="" /></a>
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
                             <li class="sidebar-item  ">
                            <a href="<%=request.getContextPath()%>/dashboard/login/WCC_welcome.jsp" class='sidebar-link'>
                                <i class="bi bi-grid-fill"></i>
                                <span>��x����</span>
                            </a>
                        </li>

                        <li class="sidebar-item ">
                            <a href="<%=request.getContextPath()%>/dashboard/member/WCC_member.jsp" class='sidebar-link'>
                                <i class="bi bi-file-earmark-spreadsheet-fill"></i>
                                <span>�|���޲z</span>
                            </a>
                        </li>


                        <li class="sidebar-item  has-sub ">
                            <a href="#" class='sidebar-link'>
                                <i class="bi bi-stack"></i>
                                <span>�v���޲z</span>
                            </a>
                            <ul class="submenu ">
                          
                                <li class="submenu-item ">
                                    <a href="<%=request.getContextPath()%>/dashboard/admin/WCC_admin_management.jsp">�޲z�޲z��</a>
                                </li>
                             
                                <li class="submenu-item ">
                                    <a href="<%=request.getContextPath()%>/dashboard/permission/WCC_permission.jsp">�Х��v���W�h</a>
                                </li>
                            
                            </ul>
                        </li>

                        <li class="sidebar-item has-sub ">
                            <a href="#" class="sidebar-link">
                                <i class="bi bi-collection-fill"></i>
                                <span>���к޲z</span>
                            </a>
                            <ul class="submenu">
                                <li class="submenu-item">
                                    <a href="<%=request.getContextPath()%>/dashboard/recipe/WCC_recipe.jsp">���Ъ��</a>
                                </li>
                                <li class="submenu-item ">
                                    <a href="<%=request.getContextPath()%>/dashboard/hashtag/WCC_hashtag.jsp">���Һ޲z</a>
                                </li>
                            </ul>
                        </li>

                        <li class="sidebar-item  has-sub">
                            <a href="#" class='sidebar-link'>
                                <i class="bi bi-grid-1x2-fill"></i>
                                <span>�ӫ��޲z</span>
                            </a>
                            <ul class="submenu ">
                                <li class="submenu-item ">
                                    <a href="<%=request.getContextPath()%>/dashboard/product/shopview.html">�ӫ~�]�w</a>
                                </li>
                                <li class="submenu-item ">
                                    <a href="<%=request.getContextPath()%>/dashboard/memberOrder/TYT_order_management.html">�q��޲z</a>
                                </li>
                                <li class="submenu-item ">
                                    <a href="<%=request.getContextPath()%>/dashboard/productTag/tagview.html">�ӫ~�����޲z</a>
                                </li>
                                <li class="submenu-item  ">
                                    <a href="<%=request.getContextPath()%>/dashboard/promo_code/promo_code_allview.jsp">�u�f��޲z</a>
                                </li>
                                <li class="submenu-item">
                                    <a href="<%=request.getContextPath()%>/dashboard/advertise/advertise_allview.jsp" >�s�i�޲z</a>
                                </li>
                            </ul>
                        </li>
                        <li class="sidebar-item  has-sub">
                            <a href="#" class='sidebar-link'>
                                <i class="bi bi-hexagon-fill"></i>
                                <span>�Q�װϺ޲z</span>
                            </a>

                            <ul class="submenu ">
                                <li class="submenu-item ">
                                    <a href="<%=request.getContextPath()%>/dashboard/article/HO_dscussion_cate.jsp">�ݪO����</a>
                                  </li>
                                  <li class="submenu-item ">
                                    <a href="<%=request.getContextPath()%>/dashboard/article/HO_discussion_allview.jsp" >�峹�޲z</a>
                                  </li>
                            </ul>
                        </li>
                        <li class="sidebar-item  has-sub ">
                            <a href="#" class='sidebar-link'>
                                <i class="bi bi-pen-fill"></i>
                                <span>�ȪA����</span>
                            </a>
                            <ul class="submenu ">
                                <li class="submenu-item ">
                                    <a href="<%=request.getContextPath()%>/dashboard/recipe_report/WCC_recipe_report.jsp">�������|</a>
                                </li>
                                <li class="submenu-item ">
                                    <a href="<%=request.getContextPath()%>/dashboard/article_report/WCC_article_report.jsp">�Q�װ����|</a>
                                </li>
                                  <li class="submenu-item ">
                                    <a href="<%=request.getContextPath()%>/dashboard/article_sub_report/WCC_article_sub_report.jsp">�Q�װϦ^�����|</a>
                                </li>
                                <li class="submenu-item ">
                                    <a href="<%=request.getContextPath()%>/dashboard/question/question-table.html">�`�����D</a>
                                </li>
                                <li class="submenu-item ">
                                    <a href="<%=request.getContextPath()%>/dashboard/notifycenter/notify-table.html">�t�γq��</a>
                                </li>
                                <li class="submenu-item ">
                                    <a href="<%=request.getContextPath()%>/dashboard/supportform/supportform-table.html">���D���</a>
                                </li>
                                <li class="submenu-item active">
                                <a href="<%=request.getContextPath()%>/dashboard/news/news-table.html">�̷s����</a>
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


		<div class"col-md-6" id="main">
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
							<%-- ���~��C --%>
							<c:if test="${not empty errorMsgs}">
								<font style="color: red">�Эץ��H�U���~:</font>
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
																value="${advertiseVO.advertiseName}" placeholder="�s�i�W��">
														</div>
													</div>
													<div class="col-12">
														<div class="form-group">
															<label for="advertiseShelfTime-vertical">�W�[�ɶ�</label> <input
																type="datetime-local" id="advertiseShelfTime-vertical"
																class="form-control" name="advertise_shelf_time"
																value="${advertiseVO.advertiseShelfTime}"
																placeholder="�W�[�ɶ�">
														</div>
													</div>
													<div class="col-12">
														<div class="form-group">
															<label for="advertiseOffsaleTime-vertical">�U�[�ɶ�</label> <input
																type="datetime-local"
																id="padvertiseOffsaleTime-vertical" class="form-control"
																name="advertise_offsale_time"
																value="${advertiseVO.advertiseOffsaleTime}"
																placeholder="�U�[�ɶ�">
														</div>
													</div>
													<div class="col-12">
														<div class="form-group">
															<label for="advertiseUrl-vertical">�s�iURL</label> <input
																type="text" step="1" id="advertiseUrl-vertical"
																value="${advertiseVO.advertiseUrl}" class="form-control"
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
	<script>
		let table1 = document.querySelector("#table1");
		let dataTable = new simpleDatatables.DataTable(table1);
		// 		Simple Datatable
		// 		        let table1 = document.querySelector('#table1');
		// 		        let dataTable = new simpleDatatables.DataTable(table1);
	</script>
	<script
		src="<%=request.getContextPath()%>/dashboard/assets/vendors/jquery-3.7.1.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/dashboard/assets/vendors/perfect-scrollbar/perfect-scrollbar.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/dashboard/assets/js/bootstrap.bundle.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/dashboard/assets/vendors/simple-datatables/simple-datatables.js"></script>

	<script src="<%=request.getContextPath()%>/dashboard/assets/js/main.js"></script>
	<script
		src="<%=request.getContextPath()%>/dashboard/assets/js/menu_ative.js"></script>
</body>

</html>