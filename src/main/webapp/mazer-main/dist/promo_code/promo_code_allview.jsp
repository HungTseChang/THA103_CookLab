<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
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
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
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
			<div class="sidebar-wrapper active">
				<div class="sidebar-header">
					<div class="d-flex justify-content-between">
						<div class="logo">
							<a href="index.html"><img src="assets/images/logo/logo.png"
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
						<!-------------------------------------------------------------------------------->
						<li class="sidebar-item"><a href="WCC_Homepage.html"
							class="sidebar-link"> <i class="bi bi-grid-fill"></i> <span>後台首頁</span>
						</a></li>

						<li class="sidebar-item"><a href="WCC_memeber.html"
							class="sidebar-link"> <i
								class="bi bi-file-earmark-spreadsheet-fill"></i> <span>會員管理</span>
						</a></li>

						<li class="sidebar-item"><a href="#" class="sidebar-link">
								<i class="bi bi-stack"></i> <span>權限管理</span>
						</a>
							<ul class="submenu">
								<li class="submenu-item"><a
									href=".\WCC_permission_createnew.html">創建管理</a></li>
								<li class="submenu-item"><a
									href=".\WCC_permission_management.html">管理管理者</a></li>
								<li class="submenu-item"><a href="#">編輯管理者</a></li>
								<li class="submenu-item"><a
									href=".\WCC_permission_createrule.html">創立權限規則</a></li>
								<li class="submenu-item"><a href="#">權限管理-編輯/查詢權限規則-查詢</a>
								</li>
							</ul></li>

						<li class="sidebar-item has-sub"><a href="#"
							class="sidebar-link"> <i class="bi bi-collection-fill"></i> <span>食譜管理</span>
						</a> <!-- <ul class="submenu ">
        <li class="submenu-item ">
            <a href="#">食譜管理</a>
        </li>
    </ul>
</li> --></li>

						<li class="sidebar-item has-sub active"><a href="#"
							class="sidebar-link"> <i class="bi bi-grid-1x2-fill"></i> <span>商城管理</span>
						</a>
							<ul class="submenu" style="display: block">
								<li class="submenu-item"><a href="#">商品設定</a></li>
								<li class="submenu-item"><a href="#">訂單管理</a></li>
								<li class="submenu-item active"><a href="#">優惠券設定</a></li>
								<li class="submenu-item"><a href="#">廣告設定</a></li>
								<li class="submenu-item"><a href="#">新增進貨表單</a></li>
							</ul></li>
						<li class="sidebar-item has-sub"><a href="#"
							class="sidebar-link"> <i class="bi bi-hexagon-fill"></i> <span>討論區管理</span>
						</a>

							<ul class="submenu" >
								<li class="submenu-item"><a href="#">看板分類</a></li>
								<li class="submenu-item"><a href="#"
									class="sub_title ">文章管理</a></li>
							</ul></li>
						<li class="sidebar-item has-sub"><a href="#"
							class="sidebar-link"> <i class="bi bi-pen-fill"></i> <span>數據分析</span>
						</a>
							<ul class="submenu">
								<li class="submenu-item"><a href="#">會員數據</a></li>
								<li class="submenu-item"><a href="#">食譜數據</a></li>
								<li class="submenu-item"><a href="#">商城數據</a></li>
								<li class="submenu-item"><a href="#">文章數據</a></li>
							</ul></li>
						<li class="sidebar-item has-sub"><a href="#"
							class="sidebar-link"> <i class="bi bi-pen-fill"></i> <span>客服中心</span>
						</a>
							<ul class="submenu">
								<li class="submenu-item"><a href="#">食譜檢舉</a></li>
								<li class="submenu-item"><a href="#">討論區檢舉</a></li>
								<li class="submenu-item"><a href="#">系統通知</a></li>
							</ul></li>
						<!----------------------------------------------------------------------------->
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
	/*設定文字一行*/
	text-overflow: ellipsis;
	/*clip(預設)|ellipsis(省略符號)*/
	/* overflow: hidden; */
}
</style>
			<!-- ////////////////////////////////////////////////////////////////////////////////////////// -->
			<div class="page-heading">
				<div class="page-title">
					<div class="row">
						<div class="col-12 col-md-6 order-md-1 order-last">
							<h3>優惠券管理</h3>
						</div>
						<div class="col-12 col-md-6 order-md-2 order-first">
							<nav aria-label="breadcrumb"
								class="breadcrumb-header float-start float-lg-end">
								<ol class="breadcrumb">
									<li class="breadcrumb-item"><a href="./promo_code_set.jsp">優惠券設定</a>
									</li>
									<li class="breadcrumb-item active" aria-currentx="page">
										優惠券管理</li>
								</ol>
							</nav>
						</div>
					</div>
				</div>
				<section class="section">
					<div class="card">

						<div class="card-body">
							<table class="table table-striped" id="table1">
								<thead>
									<tr>
										<th class="first_col">優惠券編號</th>
										<th class="sec_col">優惠券序號</th>
										<th class="thrid_col">生效時間</th>
										<th class="forth_col">失效時間</th>
										<th class="fifth_col">百分比折價金額</th>
										<th class="sixth_col">固定折價金額</th>
										<th class="seventh_col">可使用次數</th>
										<th class="eighth_col">最低消費門檻</th>
										<th class="last_column">最後編輯時間</th>
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
													<input type="submit" value="修改"> <input
														type="hidden" name="promoCodeNo"
														value="${pcVO.promoCodeNo}"> <input type="hidden"
														name="action" value="getOne_For_Update">
												</FORM>
											</td>
											<td>
												<FORM METHOD="post"
													ACTION="<%=request.getContextPath()%>/PromoCodeServlet"
													style="margin-bottom: 0px;">
													<input type="submit" value="刪除"> <input
														type="hidden" name="promoCodeNo"
														value="${pcVO.promoCodeNo}"> <input type="hidden"
														name="action" value="delete">
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
		// 		Simple Datatable
		// 		        let table1 = document.querySelector('#table1');
		// 		        let dataTable = new simpleDatatables.DataTable(table1);
	</script>
	<script
		src="<%=request.getContextPath()%>/mazer-main/dist/assets/vendors/jquery-3.7.1.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/mazer-main/dist/assets/vendors/perfect-scrollbar/perfect-scrollbar.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/mazer-main/dist/assets/js/bootstrap.bundle.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/mazer-main/dist/assets/vendors/simple-datatables/simple-datatables.js"></script>

	<script
		src="<%=request.getContextPath()%>/mazer-main/dist/assets/js/main.js"></script>
	<script
		src="<%=request.getContextPath()%>/mazer-main/dist/assets/js/menu_ative.js"></script>


	<script>
<!--
		let table1 = document.querySelector("#table1");
		let dataTable = new simpleDatatables.DataTable(table1);
		$(function() {
			//jsp中使用read的話，會誤認為ajax要轉接網頁要改用ready
			$(document).ready("change", ".btn.article_status", function() {
				// console.log("you touch me");
			});

			$(document).ready(function() {
				//取得數字
				var $articleStatusBtn = $('.article_status');
				// 把每個a標籤的數字轉為文字
				$articleStatusBtn.each(function() {
					var $this = $(this);
					var status = parseInt($this.text());

					switch (status) {
					case 0:
						$this.text('公開');
						break;
					case 1:
						$this.text('非公開');
						break;
					case 2:
						$this.text('草稿');
						break;
					case 3:
						$this.text('刪除');
						break;
					}
				});
			});

			$(document)
					.on(
							"change",
							".ch_artice_status",
							function() {
								let selectedOption = $(this).find(
										"option:selected").text();
								console.log(selectedOption);
								let parentRow = $(this).closest("tr"); //用來找尋該區域的父元素<tr>，
								//使用find找到父元素<tr>區域，才可以指定該<tr>區域的內容改變，進而不影響到其他<tr>區域
								switch (selectedOption) {
								case "0":
									let new_status1 = `<a href="#" class="btn btn-success rounded-pill btn article_status">公開</a>`;
									parentRow.find(".btn.article_status")
											.remove();
									parentRow.find(".artice_status").append(
											new_status1);
									break;
								case "1":
									let new_status2 = `<a href="#" class="btn btn-info rounded-pill btn article_status">非公開</a>`;
									parentRow.find(".btn.article_status")
											.remove();
									parentRow.find(".artice_status").append(
											new_status2);
									break;
								case "2":
									let new_status3 = `<a href="#" class="btn btn-warning rounded-pill btn article_status">草稿</a>`;
									parentRow.find(".btn.article_status")
											.remove();
									parentRow.find(".artice_status").append(
											new_status3);
									break;
								case "3":
									let new_status4 = `<a href="#" class="btn btn-danger rounded-pill btn article_status">刪除</a>`;
									parentRow.find(".btn.article_status")
											.remove();
									parentRow.find(".artice_status").append(
											new_status4);
									break;
								}
							});
		}); -->
	</script>
</body>

</html>