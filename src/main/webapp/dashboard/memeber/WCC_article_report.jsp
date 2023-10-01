<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.cooklab.article_report.model.*"%>
<%@ page import =" java.util.List"%>
<%@ page import="org.hibernate.Session"%>
<%@ page import="com.cooklab.util.HibernateUtil" %>
<%@ page import="com.cooklab.article_report.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 <%
 ArticleReportTest art = new ArticleReportTest();
Session Ssion = HibernateUtil.getSessionFactory().getCurrentSession();
try {
	Ssion.beginTransaction();
	List<ArticleReportVO> list1 = Ssion.createQuery("from ArticleReportVO", ArticleReportVO.class).list();

// 	for(int i=0; i<list1.size();i++) {
// 	System.out.println(
// 			"ArticleReportNo :"+list1.get(i).getArticleReportNo()+"\n  "
// 		+ "ArticleNo :"+list1.get(i).getArticleNo()+"\n"
// 		+"ReporterId :"+ list1.get(i).getReporterId()+"\n"
// 		+"ReportingReason :"+list1.get(i).getReportingReason()+"\n"
// 		+"ReportingStatus :"+list1.get(i).getReportingStatus()+"\n"
// 		+"tCreatedTimestamp :"+list1.get(i).getCreatedTimestamp()
// 		+"\n"+"============================================="
// 				); 
// 	}
	Ssion.getTransaction().commit();
	Ssion.close();
}catch (Exception e) {
	e.printStackTrace();
	Ssion.getTransaction().rollback();
} finally {
	HibernateUtil.shutdown();
}
%>
    
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>DataTable - Mazer </title>

    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Nunito:wght@300;400;600;700;800&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="assets/css/bootstrap.css">

    <link rel="stylesheet" href="assets/vendors/simple-datatables/style.css">

    <link rel="stylesheet" href="assets/vendors/perfect-scrollbar/perfect-scrollbar.css">
    <link rel="stylesheet" href="assets/vendors/bootstrap-icons/bootstrap-icons.css">
    <link rel="stylesheet" href="assets/css/app.css">
</head>

<body>
    <div id="app">
        <div id="sidebar" class="active">
            <div class="sidebar-wrapper active">
                <div class="sidebar-header">
                    <div class="d-flex justify-content-between">
                        <div class="logo">
                            <a href="index.html"><img src="assets/images/logo/logo.png" alt="Logo" srcset=""></a>
                        </div>
                        <div class="toggler">
                            <a href="#" class="sidebar-hide d-xl-none d-block"><i class="bi bi-x bi-middle"></i></a>
                        </div>
                    </div>
                </div>
                <div class="sidebar-menu">
                    <ul class="menu">
                        <li class="sidebar-title">Menu</li>


                        <!-- ============================================================================================== -->
                        <li class="sidebar-item  ">
                            <a href="WCC_Homepage.html" class='sidebar-link'>
                                <i class="bi bi-grid-fill"></i>
                                <span>後台首頁</span>
                            </a>
                        </li>


                        <li class="sidebar-item ">
                            <a href="WCC_memeber.html" class='sidebar-link'>
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
                                    <a href=".\WCC_permission_management.html">管理管理者</a>
                                </li>
                             
                                <li class="submenu-item ">
                                    <a href=".\WCC_permission_createrule.html">創立權限規則</a>
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
                                    <a href=".\recipe_form.html">食譜表單</a>
                                </li>
                                <li class="submenu-item ">
                                    <a href=".\hashtag_form.html">標籤管理</a>
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
                                    <a href=".\shopview.html">商品設定</a>
                                </li>
                                <li class="submenu-item ">
                                    <a href="TYT_order_management.html">訂單管理</a>
                                </li>
                                <li class="submenu-item">
                                    <a href=".\GCpromo_info.html">新增優惠券</a>
                                </li>
                                <li class="submenu-item  ">
                                    <a href=".\GCpromo.html">優惠券管理</a>
                                </li>
                                <li class="submenu-item">
                                    <a href=".\GCadvertise.html" >廣告管理</a>
                                </li>
                                <li class="submenu-item">
                                    <a href=".\GCadvertise_info.html">新增廣告</a>
                                </li>
                                <li class="submenu-item ">
                                    <a href="TYT_purchase_order_allView.html">進貨表單</a>
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
                                    <a href=".\HO_discussion_cate.html">看板分類</a>
                                  </li>
                                  <li class="submenu-item ">
                                    <a href=".\HO_discussion_info.html" >文章管理</a>
                                  </li>
                            </ul>
                        </li>
                        <li class="sidebar-item  has-sub">
                            <a href="#" class='sidebar-link'>
                                <i class="bi bi-pen-fill"></i>
                                <span>數據分析</span>
                            </a>
                            <ul class="submenu ">
                                <li class="submenu-item ">
                                    <a href="#">會員數據</a>
                                </li>
                                <li class="submenu-item ">
                                    <a href="#">食譜數據</a>
                                </li>
                                <li class="submenu-item ">
                                    <a href="#">商城數據</a>
                                </li>
                                <li class="submenu-item ">
                                    <a href="#">文章數據</a>
                                </li>
                            </ul>
                        </li>
                        <li class="sidebar-item  has-sub ">
                            <a href="#" class='sidebar-link'>
                                <i class="bi bi-pen-fill"></i>
                                <span>客服中心</span>
                            </a>
                            <ul class="submenu " style="display: block;">
                                <li class="submenu-item ">
                                    <a href="WCC_recipe_report.html">食譜檢舉</a>
                                </li>
                                <li class="submenu-item ">
                                    <a href="WCC_recipe_sub_report.html">食譜回文檢舉</a>
                                </li>
                                <li class="submenu-item active">
                                    <a href="WCC_article_report.html">討論區檢舉</a>
                                </li>
                                <li class="submenu-item ">
                                    <a href="WCC_official_notify.html">系統通知</a>
                                </li>
                                <li class="submenu-item ">
                                    <a href="ding-support-tickets-table.html">問題表單</a>
                                </li>
                            </ul>
                        </li>
                        <!-- ======================================================================================================== -->
                    </ul>
                </div>
                <button class="sidebar-toggler btn x"><i data-feather="x"></i></button>
            </div>
        </div>
        <div id="main">
            <header class="mb-3">
                <a href="#" class="burger-btn d-block d-xl-none">
                    <i class="bi bi-justify fs-3"></i>
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
                td{
                    /* max-width: 150px; */
                    white-space: nowrap;
                    /* overflow: hidden;
                    text-overflow: ellipsis; */
                }
                th{
                    /* max-width: 150px; */
                    white-space: nowrap;
                    /* overflow: hidden;
                    text-overflow: ellipsis; */
                }
            </style>
            <!-- ////////////////////////////////////////////////////////////////////////////////////////// -->
            <div class="page-heading">
                <div class="page-title">
                    <div class="row">
                        <div class="col-12 col-md-6 order-md-1 order-last">
                            <h3>討論區檢舉</h3>
                            <p class="text-subtitle text-muted"></p>
                        </div>
                        <div class="col-12 col-md-6 order-md-2 order-first">
                            <nav aria-label="breadcrumb" class="breadcrumb-header float-start float-lg-end">
                                <ol class="breadcrumb">
                                    <li class="breadcrumb-item"><a href="index.html"></a></li>
                                    <li class="breadcrumb-item active" aria-current="page">討論區檢舉</li>
                                </ol>
                            </nav>
                        </div>
                    </div>
                </div>
                <section class="section">
                    <div class="card">
                        <div class="card-header">
                        </div>
                        <div class="card-body" style="width: 100%; overflow: scroll">
                            <table class="table table-striped" id="table1">
                                <thead>
                                    <tr>
                                        <th>文章檢舉編號</th>
                                        <th>會員編號(檢舉者)</th>
                                        <th>文章編號</th>
                                        <th>檢舉理由</th>
                                        <th>檢舉狀態</th>
                                        <th>檢舉時間</th>
                                        <th>操作</th>
                                    </tr>
                                </thead>
                                <tbody>
<c:forEach var="ArticleReportVO" items="${list1}" >

<tr>
			<td>${ArticleReportVO.getArticleReportNo}</td>
			<td>${ArticleReportVO.getArticleNo}</td>
			<td>${ArticleReportVO.getReporterId}</td>
			<td>${ArticleReportVO.getReportingReason}</td>
			<td>${ArticleReportVO.getReportingStatus}</td>
			<td>${ArticleReportVO.getCreatedTimestamp}</td> 
			<td><%=request.getContextPath()%></td> 
			
<%-- 			<td>  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/" style="margin-bottom: 0px;"> --%>
<!-- 			     <input type="submit" value="刪除"> -->
<%-- 			     <input type="hidden" name="empno"  value="${empVO.empno}"> --%>
<!-- 			     <input type="hidden" name="action" value="delete"></FORM> -->
<!-- 			     </td>  -->
<%-- 			<td>${ArticleReportVO.getCreatedTimestamp}</td>  --%>
		</tr>

</c:forEach>
<%--   <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/emp/emp.do" style="margin-bottom: 0px;"> --%>
<!-- 			     <input type="submit" value="刪除"> -->
<%-- 			     <input type="hidden" name="empno"  value="${empVO.empno}"> --%>
<!-- 			     <input type="hidden" name="action" value="delete"></FORM> -->
<!--                                     <tr> -->
<!--                                         <td>0000001</td> -->
<!--                                         <td>紅番茄</td> -->
<!--                                         <td>0002310</td> -->
<!--                                         <td>測試一下</td> -->
<!--                                         <td> <span class="badge bg-success">已通過</span></td> -->
<!--                                         <td>2023/09/17</td> -->
<!--                                         <td> -->
<!--                                             <a class="wcc" href="WCC_article_report_info.html">詳細資料</a> -->
<!--                                             <button class="wcc">修改狀態</button> -->
<!--                                         </td> -->
<!--                                     </tr> -->
<!--                                     <tr> -->
<!--                                         <td>0000002</td> -->
<!--                                         <td>橙番茄</td> -->
<!--                                         <td>0002311</td> -->
<!--                                         <td>檢舉好玩的</td> -->
<!--                                         <td> <span class="badge bg-success">已通過</span></td> -->
<!--                                         <td>2023/09/17</td> -->
<!--                                         <td> -->
<!--                                             <a class="wcc" href="WCC_article_report_info.html">詳細資料</a> -->
<!--                                             <button class="wcc">修改狀態</button> -->
<!--                                         </td> -->
<!--                                     </tr> -->
<!--                                     <tr> -->
<!--                                         <td>0000003</td> -->
<!--                                         <td>黃番茄</td> -->
<!--                                         <td>0004231</td> -->
<!--                                         <td>作法過於抽象........................................................................................</td> -->
<!--                                         <td> <span class="badge bg-warning">未通過</span></td> -->
<!--                                         <td>2023/09/17</td> -->
<!--                                         <td> -->
<!--                                             <a class="wcc" href="WCC_article_report_info.html">詳細資料</a> -->
<!--                                             <button class="wcc">修改狀態</button> -->
<!--                                         </td> -->
<!--                                     </tr> -->
<!--                                     <tr> -->
<!--                                         <td>0000004</td> -->
<!--                                         <td>綠番茄</td> -->
<!--                                         <td>0004231</td> -->
<!--                                         <td>今天天氣真好</td> -->
<!--                                         <td> <span class="badge bg-warning">未通過</span></td> -->
<!--                                         <td>2023/09/17</td> -->
<!--                                         <td> -->
<!--                                             <a class="wcc" href="WCC_article_report_info.html">詳細資料</a> -->
<!--                                             <button class="wcc">修改狀態</button> -->
<!--                                         </td> -->
<!--                                     </tr> -->
<!--                                     <tr> -->
<!--                                         <td>0000005</td> -->
<!--                                         <td>藍番茄</td> -->
<!--                                         <td>0012445</td> -->
<!--                                         <td>重複發文</td> -->
<!--                                         <td> <span class="badge bg-success">已通過</span></td> -->
<!--                                         <td>2023/09/17</td> -->
<!--                                         <td> -->
<!--                                             <a class="wcc" href="WCC_article_report_info.html">詳細資料</a> -->
<!--                                             <button class="wcc">修改狀態</button> -->
<!--                                         </td> -->
<!--                                     </tr> -->
<!--                                     <tr> -->
<!--                                         <td>0000006</td> -->
<!--                                         <td>靛番茄</td> -->
<!--                                         <td>003621</td> -->
<!--                                         <td>不好吃</td> -->
<!--                                         <td> <span class="badge bg-danger">未審核</span></td> -->
<!--                                         <td>2023/09/17</td> -->
<!--                                         <td> -->
<!--                                             <a class="wcc" href="WCC_article_report_info.html">詳細資料</a> -->
<!--                                             <button class="wcc">修改狀態</button> -->
<!--                                         </td> -->
<!--                                     </tr> -->
<!--                                     <tr> -->
<!--                                         <td>0000007</td> -->
<!--                                         <td>紫番茄</td> -->
<!--                                         <td>003621</td> -->
<!--                                         <td>抄襲</td> -->
<!--                                         <td> <span class="badge bg-danger">未審核</span></td> -->
<!--                                         <td>2023/09/17</td> -->
<!--                                         <td> -->
<!--                                             <a class="wcc" href="WCC_article_report_info.html">詳細資料</a> -->
<!--                                             <button class="wcc">修改狀態</button> -->
<!--                                         </td> -->
<!--                                     </tr> -->

                                </tbody>
                            </table>
                        </div>
                    </div>

                </section>
            </div>

            <footer>
                
            </footer>
        </div>
    </div>
    <script>
        document.addEventListener("DOMContentLoaded", function () {
            // $("button.wcc").on('click', (e) => {
            //     Swal.fire({
            //         icon: "success",
            //         title: "重設密碼成功!"
            //     })
            // })
        })
    </script>
    <script src="assets\vendors\jquery-3.7.1.min.js"></script>
    <script src="assets/vendors/perfect-scrollbar/perfect-scrollbar.min.js"></script>
    <script src="assets/js/bootstrap.bundle.min.js"></script>
    <script src="assets/vendors/sweetalert2/sweetalert2.all.min.js"></script>
    <script src="assets/vendors/simple-datatables/simple-datatables.js"></script>
    <!-- <script>
        // Simple Datatable
        let table1 = document.querySelector('#table1');
        let dataTable = new simpleDatatables.DataTable(table1);
    </script> -->
    
    <script src="assets/js/main.js"></script>
    <script src="assets\js\menu_ative.js"></script>

</body>

</html>