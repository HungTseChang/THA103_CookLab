<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.cooklab.promo_code.model.*"%>



<%
PromoCodeService pcSvc = new PromoCodeService();
List<PromoCodeVO> list = pcSvc.getAll();
pageContext.setAttribute("list", list);
%>
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
</head>

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
	transition: all .5s;
	text-decoration: none;
	color: #25396f;
}

td a.wcc {
	border: 1px solid rgb(151, 135, 249);
	background-color: rgb(195, 241, 253);
	padding: 4px;
	border-radius: 20px;
}

td input.wcc {
	border: 1px solid rgb(151, 135, 249);
	background-color: rgb(195, 241, 253);
	padding: 4px;
	border-radius: 20px;
}

td button.wcc {
	border-radius: 20px;
}

td.HO_article_title {
	/* width: 15%; */
	/* white-space: wrap; */
	/*�]�w��r�@��*/
	text-overflow: ellipsis;
	/*clip(�w�])|ellipsis(�ٲ��Ÿ�)*/
	/* overflow: hidden; */
}
</style>


<body>
	<div id="app">
		<div id="sidebar" class="active">
			<div class="sidebar-wrapper active">
				<div class="sidebar-header">
					<div class="d-flex justify-content-between">
						<div class="logo">
							<a href=""><img
								src="<%=request.getContextPath()%>/dashboard/assets/images/logo/logo.png" alt="Logo" srcset="" /></a>
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
                                <li class="submenu-item  active">
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
<!--                                 <li class="submenu-item "> -->
<%--                                     <a href="<%=request.getContextPath()%>/dashboard/article_report/WCC_article_report.jsp">�������|</a> --%>
<!--                                 </li> -->
<!--                                 <li class="submenu-item "> -->
<%--                                     <a href="src=<%=request.getContextPath()%>/dashboard//WCC_recipe_sub_report.jsp">���Ц^�����|</a> --%>
<!--                                 </li> -->
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
                                <li class="submenu-item ">
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
							<h3>�u�f��޲z</h3>
						</div>
						<div class="col-12 col-md-6 order-md-2 order-first">
							<nav aria-label="breadcrumb"
								class="breadcrumb-header float-start float-lg-end">
								<ol class="breadcrumb">
									<li class="breadcrumb-item"><a
										href="<%=request.getContextPath()%>/dashboard/promo_code/promo_code_set.jsp">�u�f��]�w</a>
									</li>
									<li class="breadcrumb-item active" aria-currentx="page">
										�u�f��޲z</li>
								</ol>
							</nav>
						</div>
					</div>
				</div>
				<section class="section">
					<!--  <div class="col-md-6">
						<input type="text" id="searchbar" class="form-control"
							placeholder="�п�J �s���B�馩���B�ήɶ�"
							style="pading-color: rgb(208, 250, 255);">
					</div>
					-->
					<div class="card">

						<div class="card-body">
							<table class="table table-striped" id="table1">
								<thead>
									<tr>
										<th class="first_col">�u�f��s��</th>
										<th class="sec_col">�u�f��Ǹ�</th>
										<th class="thrid_col">�ͮĮɶ�</th>
										<th class="forth_col">���Įɶ�</th>
										<th class="fifth_col">�ʤ��������B</th>
										<th class="sixth_col">�T�w������B</th>
										<th class="seventh_col">�i�ϥΦ���</th>
										<th class="eighth_col">�̧C���O���e</th>
										<th class="last_column">�̫�s��ɶ�</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="pcVO" items="${list}">

										<tr>
											<td>${pcVO.promoCodeNo}</td>
											<td>${pcVO.promoCodeSerialNumber}</td>
											<td>${pcVO.startTime}</td>
											<td>${pcVO.endTime}</td>
											<td>${pcVO.percentageDiscountAmount}</td>
											<td>${pcVO.fixedDiscountAmount}</td>
											<td>${pcVO.usagesAllowed}</td>
											<td>${pcVO.minimumConsumption}</td>
											<td>${pcVO.createdTimestamp}</td>


											<td>
												<FORM METHOD="post"
													ACTION="<%=request.getContextPath()%>/PromoCodeServlet"
													style="margin-bottom: 0px;">
													<input type="submit" value="�ק�"> <input
														type="hidden" name="promo_code_no"
														value="${pcVO.promoCodeNo}"> <input type="hidden"
														name="action" value="getOne_For_Display">
												</FORM>
											</td>
											<td>
												<FORM METHOD="post"
													ACTION="<%=request.getContextPath()%>/PromoCodeServlet"
													style="margin-bottom: 0px;">

													<input type="hidden" name="promo_code_no"
														value="${pcVO.getPromoCodeNo()}"> <input
														type="hidden" name="action" value="delete"> <input
														type="submit" value="�R��">
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