<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.cooklab.article_report.model.*"%>
<%@ page import =" java.util.List"%>
<%@ page import="org.hibernate.Session"%>
<%@ page import="com.cooklab.util.HibernateUtil" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import ="com.google.gson.Gson"  %>
<%@ page import ="com.google.gson.JsonElement"  %>
<%@ page import ="com.google.gson.JsonParser"  %>


<%--  <% --%>
<!-- //  ArticleReportTest art = new ArticleReportTest(); -->
<!-- // Session Ssion = HibernateUtil.getSessionFactory().getCurrentSession(); -->
<!-- // try { -->
<!-- // 	Ssion.beginTransaction(); -->
<!-- // 	List<ArticleReportVO> list1 = Ssion.createQuery("from ArticleReportVO", ArticleReportVO.class).list(); -->
<!-- // 	  pageContext.setAttribute("list1",list1); -->
<%-- %> --%>
<%
 ArticleReportJDBCDAOIm arjm = new ArticleReportJDBCDAOIm();
 List<ArticleReportVO> list1=   arjm.getAll();
 pageContext.setAttribute("list1",list1);
 String json = new Gson().toJson(list1);
%> 
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>DataTable - Mazer </title>

    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Nunito:wght@300;400;600;700;800&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/dashboard/memeber/assets/css/bootstrap.css">

    <link rel="stylesheet" href="${pageContext.request.contextPath}/dashboard/memeber/assets/vendors/simple-datatables/style.css">

    <link rel="stylesheet" href="${pageContext.request.contextPath}/dashboard/memeber/assets/vendors/perfect-scrollbar/perfect-scrollbar.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/dashboard/memeber/assets/vendors/bootstrap-icons/bootstrap-icons.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/dashboard/memeber/assets/css/app.css">
    
    <style>
                td a.wcc {
                    border: 1px solid rgb(151, 135, 249);
                    background-color: rgb(195, 241, 253);
                    padding: 4px;
                    border-radius: 20px;
                }

                td input.wcc {
                    border-radius: 20px;
                }
                td{
                    white-space: nowrap;

                }
                th{
                    white-space: nowrap;
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
                            <a href="index.html"><img src="${pageContext.request.contextPath}/dashboard/memeber/assets/images/logo/logo.png" alt="Logo" srcset=""></a>
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
<%-- <c:forEach var="a" items=" ${list1}"> --%>
<!--     <tr> -->
<%-- <%--         <td>${a.articleReportNo}</td> --%> 
<%--         <td>${a.isEmpty()}</td> --%>
<%-- <%--         <td>${a.articleReportNo}</td> --%> 
<%-- <%--         <td>${a.getReportingReason()}</td> --%> 
<%-- <%--         <td>${a.getReportingStatus()}</td> --%> 
<%-- <%--         <td>${a.getCreatedTimestamp()}</td>  --%> 
<!--         <td>1</td> -->
<!--     </tr> -->
<%-- </c:forEach> --%>

                                </tbody>
                            </table>
                        </div>
                    </div>

                </section>
            </div>
<%-- 			<td><%=request.getContextPath()%></td>  --%>
			
<%-- 			<td>  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/" style="margin-bottom: 0px;"> --%>
<!-- 			     <input type="submit" value="刪除"> -->
<%-- 			     <input type="hidden" name="empno"  value="${empVO.empno}"> --%>
<!-- 			     <input type="hidden" name="action" value="delete"></FORM> -->
<!-- 			     </td>  -->
<%-- 			<td>${ArticleReportVO.getCreatedTimestamp}</td>  --%>
<%--   <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/emp/emp.do" style="margin-bottom: 0px;"> --%>
<!-- 			     <input type="submit" value="刪除"> -->
<%-- 			     <input type="hidden" name="empno"  value="${empVO.empno}"> --%>
<!-- 			     <input type="hidden" name="action" value="delete"></FORM> -->



            <footer>
                
            </footer>
        </div>
    </div>
<%--   <%	Ssion.getTransaction().commit(); --%>
<!-- // 	Ssion.close(); -->
<!-- // }catch (Exception e) { -->
<!-- // 	e.printStackTrace(); -->
<!-- // 	Ssion.getTransaction().rollback(); -->
<!-- // } finally { -->
<!-- // 	HibernateUtil.shutdown(); -->
<%-- } %>   --%>
<script src="assets\vendors\jquery-3.7.1.min.js"></script>
    <script>
        document.addEventListener("DOMContentLoaded", function () {
        	
        	var myList = JSON.parse('<%= json %>');
    
          console.log(myList);
          for(let i = 0 ; i< myList.length;i++){
        	  let aa = myList[i];
        	  console.log(aa.articleReportNo);
        	  let status ={
        			  0:" <span class='badge bg-success'>通過</span>",
        			  1:"<span class='badge bg-warning'>尚未決定</span>",
        			  2:" <span class='badge bg-danger'>否決</span>",
        	  }
        	  let text = "";
        		  text += "<tr>";
        		  text += "<td>"+aa.articleReportNo+"</td>";
        		  text +=" <td>"+aa.articleNo+"</td>";
        		  text +=" <td>"+aa.reporterId+"</td>";
        		  text +=" <td>"+aa.reportingReason+"</td>";
        		  text +=" <td>"+status[aa.reportingStatus]+"</td>";
        		  text +=" <td>"+aa.createdTimestamp+"</td>";
        		  text += "<td>";
            	  text +=  " <form action='${pageContext.request.contextPath}/ArticleReportServlet' method='get'>"; 
            	  text +=  "<p ><input class='wcc' type='submit'  value='修改資料'></p>";
        		  text += "<input type='hidden' name='articleReportNo' value='";
        		  text += aa.articleReportNo+"'>";
        		  text += "<input type='hidden' name='action' value='changeData'></form></td>";
        		  text += "</tr>";
        		console.log(text);
           $("table#table1").children("tbody").append(text)      	  
          }
          
            
            
            
        })
        
         </script>
    
    <script src="${pageContext.request.contextPath}/dashboard/memeber/assets/vendors/perfect-scrollbar/perfect-scrollbar.min.js"></script>
    <script src="${pageContext.request.contextPath}/dashboard/memeber/assets/js/bootstrap.bundle.min.js"></script>
    <script src="${pageContext.request.contextPath}/dashboard/memeber/assets/vendors/sweetalert2/sweetalert2.all.min.js"></script>
    <script src="${pageContext.request.contextPath}/dashboard/memeber/assets/vendors/simple-datatables/simple-datatables.js"></script>
    <!-- <script>
        // Simple Datatable
        let table1 = document.querySelector('#table1');
        let dataTable = new simpleDatatables.DataTable(table1);
    </script> -->
    
    <script src="${pageContext.request.contextPath}/dashboard/memeber/assets/js/main.js"></script>
    <script src="${pageContext.request.contextPath}/dashboard/memeber/assets\js\menu_ative.js"></script>

</body>

</html>