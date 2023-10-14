<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.cooklab.article_category.model.*"%>
<%
ArticleCategoryVO artVO2 = (ArticleCategoryVO) request.getAttribute("artVO");

ArticleCategoryService artSvc = new ArticleCategoryService();
List<ArticleCategoryVO> list = artSvc.getAll();
pageContext.setAttribute("list", list);
%>



<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>DataTable - Mazer Admin Dashboard</title>

<link rel="preconnect" href="https://fonts.gstatic.com" />
<link
	href="https://fonts.googleapis.com/css2?family=Nunito:wght@300;400;600;700;800&display=swap"
	rel="stylesheet" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/mazer-main/dist/assets/css/bootstrap.css" />

<link rel="stylesheet"
	href="<%=request.getContextPath()%>/mazer-main/dist/assets/vendors/simple-datatables/style.css" />

<link rel="stylesheet"
	href="<%=request.getContextPath()%>/mazer-main/dist/assets/vendors/perfect-scrollbar/perfect-scrollbar.css" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/mazer-main/dist/assets/vendors/bootstrap-icons/bootstrap-icons.css" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/mazer-main/dist/assets/css/app.css" />
<link rel="shortcut icon"
	href="<%=request.getContextPath()%>/mazer-main/dist/assets/images/favicon.svg"
	type="image/x-icon" />
</head>

<body>
	<div id="app">
		<div id="sidebar" class="active">
			<div class="sidebar-wrapper">
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
						<li class="sidebar-item"><a href="WCC_Homepage.html"
							class="sidebar-link"> <i class="bi bi-grid-fill"></i> <span>��x����</span>
						</a></li>

						<li class="sidebar-item"><a href="WCC_memeber.html"
							class="sidebar-link"> <i
								class="bi bi-file-earmark-spreadsheet-fill"></i> <span>�|���޲z</span>
						</a></li>

						<li class="sidebar-item "><a href="#" class="sidebar-link">
								<i class="bi bi-stack"></i> <span>�v���޲z</span>
						</a>
							<ul class="submenu">
								<li class="submenu-item"><a
									href=".\WCC_permission_createnew.html">�Ыغ޲z</a></li>
								<li class="submenu-item"><a
									href=".\WCC_permission_management.html">�޲z�޲z��</a></li>
								<li class="submenu-item"><a href="#">�s��޲z��</a></li>
								<li class="submenu-item"><a
									href=".\WCC_permission_createrule.html">�Х��v���W�h</a></li>
								<li class="submenu-item"><a href="#">�v���޲z-�s��/�d���v���W�h-�d��</a>
								</li>
							</ul></li>

						<li class="sidebar-item has-sub"><a href="#"
							class="sidebar-link"> <i class="bi bi-collection-fill"></i> <span>���к޲z</span>
						</a> <!-- <ul class="submenu ">
        <li class="submenu-item ">
            <a href="#">���к޲z</a>
        </li>
    </ul>
</li> --></li>

						<li class="sidebar-item has-sub"><a href="#"
							class="sidebar-link"> <i class="bi bi-grid-1x2-fill"></i> <span>�ӫ��޲z</span>
						</a>
							<ul class="submenu">
								<li class="submenu-item"><a href="#">�ӫ~�]�w</a></li>
								<li class="submenu-item"><a href="#">�q��޲z</a></li>
								<li class="submenu-item"><a href="#">�u�f��]�w</a></li>
								<li class="submenu-item"><a href="#">�s�i�]�w</a></li>
								<li class="submenu-item"><a href="#">�s�W�i�f���</a></li>
							</ul></li>
						<li class="sidebar-item has-sub active"><a href="#"
							class="sidebar-link toggle-submenu" data-toggle="submenu"> <i
								class="bi bi-hexagon-fill"></i> <span>�Q�װϺ޲z</span>
						</a>

							<ul class="submenu " style="display: block">
								<li class="submenu-item "><a href="#" class="sub_title ">�ݪO����</a>
								</li>
								<li class="submenu-item"><a href="#">�峹�޲z</a></li>
							</ul></li>
						<li class="sidebar-item has-sub"><a href="#"
							class="sidebar-link"> <i class="bi bi-pen-fill"></i> <span>�ƾڤ��R</span>
						</a>
							<ul class="submenu">
								<li class="submenu-item"><a href="#">�|���ƾ�</a></li>
								<li class="submenu-item"><a href="#">���мƾ�</a></li>
								<li class="submenu-item"><a href="#">�ӫ��ƾ�</a></li>
								<li class="submenu-item"><a href="#">�峹�ƾ�</a></li>
							</ul></li>
						<li class="sidebar-item has-sub"><a href="#"
							class="sidebar-link"> <i class="bi bi-pen-fill"></i> <span>�ȪA����</span>
						</a>
							<ul class="submenu">
								<li class="submenu-item"><a href="#">�������|</a></li>
								<li class="submenu-item"><a href="#">�Q�װ����|</a></li>
								<li class="submenu-item"><a href="#">�t�γq��</a></li>
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
			<!--   /////////////////////////////////////////////////////////////////////////////////////////  -->
			<style>
li a.sub_title {
	background-color: #435ebe;
	color: white !important;
	display: block;
	padding: 0.2rem 0.5rem !important;
	font-size: 1rem;
	display: flex;
	align-items: center;
	border-radius: 0.5rem;
	transition: all 0.5s;
	text-decoration: none;
	color: #25396f;
}

td a.wcc {
	border: 1px solid rgb(151, 135, 249);
	background-color: rgb(195, 241, 253);
	padding: 4px;
	border-radius: 20px;
}

td button.wcc {
	border-radius: 20px;
}

.table-container th, td {
	text-align: center;
	padding: 8px;
	border: 1px solid #ddd;
}

.hightlight {
	border-color: blue;
	background-color: rgb(163, 163, 248);
}
</style>
			<!-- ////////////////////////////////////////////////////////////////////////////////////////// -->
			<div class="page-heading">
				<div class="page-title">
					<div class="row">
						<div class="col-9 col-md-6 order-md-1 order-last">
							<h3>�峹����</h3>
						</div>
						<div class="col-9 col-md-6 order-md-2 order-first">
							<nav aria-label="breadcrumb"
								class="breadcrumb-header float-start float-lg-end">
								<ol class="breadcrumb">
									<li class="breadcrumb-item"><a href="index.html">Dashboard</a>
									</li>
									<li class="breadcrumb-item active" aria-current="page">
										�峹����</li>
								</ol>
							</nav>
						</div>
					</div>
				</div>
				<section class="section">
					<div class="row">
						<div class="col-md-9 card-header"
							style="background-color: rgb(208, 250, 255); padding-bottom: 10px; border: 10px solid rgb(170, 199, 234);">
							<div class="card">

								<div class="table-datatable"
									style="max-height: 400px; overflow-y: scroll;">
									<%-- ���~��C --%>
									<c:if test="${not empty errorMsgs}">
										<font style="color: red">�Эץ��H�U���~:</font>
										<ul>
											<c:forEach var="message" items="${errorMsgs}">
												<li style="color: red">${message}</li>
											</c:forEach>
										</ul>
									</c:if>

									<table class="table-container" style="width: 100%">
										<tbody class="cate_list">
											<c:forEach var="artVO" items="${list}">
												<tr>
													<td><input class="form-check-input" type="checkbox"
														name="${artVO.articleCategoryNo}" id="permission1" /> <label
														class="form-check-label" for="permission1">
															${artVO.articleCategory} </label></td>
												</tr>
											</c:forEach>
										</tbody>
									</table>
								</div>
							</div>
							<div style="position: relative; left: 10%">

								<FORM METHOD="POST"
									ACTION="<%=request.getContextPath()%>/ArticleCategoryServlet">

									<label for="newruletitle">�s�峹����</label> <input type="text"
										id="newruletitle" style="position: relative"
										name="articleCategory" placeholder="��J����"
										value="<%=(artVO2 == null) ? "" : artVO2.getArticleCategory()%>"%>
									<input type="hidden" name="action" value="insert">
									<button class="rounded-pill btn btn-primary"
										style="position: relative" id="add_article_cate" type="submit">�s�W�峹����</button>
								</Form>



								<FORM METHOD="POST"
									ACTION="<%=request.getContextPath()%>/ArticleCategoryServlet">
									<a type="hidden" name="articleCategoryNo"
										value="${artVO.articleCategoryNo}"></a> <a type="hidden"
										name="action" value="delete"></a> <a
										class="rounded-pill btn btn-primary"
										style="position: relative" id="del_article_cate">�R���峹���� </a>
								</Form>
							</div>
						</div>

					</div>
				</section>
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
	<script src="../assets\vendors\jquery-3.7.1.min.js"></script>
	<script
		src="../assets/vendors/perfect-scrollbar/perfect-scrollbar.min.js"></script>
	<script src="../assets/js/bootstrap.bundle.min.js"></script>
	<script src="../assets/vendors/simple-datatables/simple-datatables.js"></script>

	<script src="../assets/js/main.js"></script>
	<script src="../assets\js\menu_ative.js"></script>
	<script>
		// Simple Datatable
		//         let table1 = document.querySelector('#table1');
		//         let dataTable = new simpleDatatables.DataTable(table1);
	</script>

	<script>
		document.addEventListener("DOMContentLoaded", function() {
			document.addEventListener("click", function(e) {
				if ($(e.target.closest("tr")).hasClass("option")) {
					// console.log(e.target);
					$("tr.option").removeClass("hightlight");
					$(e.target.closest("tr")).addClass("hightlight");
				}
			});
		});

		$(document).ready(function() {

			$(document).on("change", ".form-check-input", function() {
				if ($(this).is(":checked")) {
					console.log("you touch")
				}
			});

			$(document).on("click", "#del_article_cate", function() {
				$(".form-check-input").each(function() {
					if ($(this).is(":checked")) {
						$(this).closest("tr").remove();
					}
				});

			});

		});
	</script>
</body>

</html>