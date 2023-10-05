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
<!DOCTYPE html>
<html lang="en">

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
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/dashboard/memeber/assets/images/favicon.svg" type="image/x-icon">
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
                        <li class="sidebar-item  has-sub  ">
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

                input.WCC_memeber_info {
                    width: 60%;
                }
            </style>
            <!-- ////////////////////////////////////////////////////////////////////////////////////////// -->
            <div class="page-heading">
                <div class="page-title">
                    <div class="row">
                        <div class="col-12 col-md-6 order-md-1 order-last">
                            <h3>討論區檢舉</h3>
                        </div>
                        <div class="col-12 col-md-6 order-md-2 order-first">
                            <nav aria-label="breadcrumb" class="breadcrumb-header float-start float-lg-end">
                                <ol class="breadcrumb">
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
                            <div class="row match-height" style=" position: relative;">
                                <div class="col-md-1"></div>
                                <div class="col-md-5">
                                    <div class="card">
                                        <div class="card-header">
                                            <h4 class="card-title">&nbsp;</h4>
                                        </div>
                                        <div class="card-content">
                                            <div class="card-body">
                                                <form class="form form-horizontal">
                                                    <div class="form-body">
                                                        <div class="row">
                                                            <div class="col-md-6">
                                                                <label>文章檢舉編號</label>
                                                            </div>
                                                            <div class="col-md-6 form-group">
                                                                <input type="text" value="${ArticleReportVO.getArticleReportNo()} " disabled>
                                                            </div>
                                                            <div class="col-md-6">
                                                                <label>文章檢舉時間</label>
                                                            </div>
                                                            <div class="col-md-6 form-group">
                                                                <input type="text" value="${ArticleReportVO.getCreatedTimestamp()}" disabled>
                                                            </div>
                                                            <div class="col-md-6">
                                                                <label>會員編號(檢舉者)</label>
                                                            </div>
                                                            <div class="col-md-6 form-group">
                                                                <input type="text" value="${ArticleReportVO.getReporterId()}" disabled>
                                                            </div>
                                                            <div class="col-md-6">
                                                                <label>會員帳號(檢舉者)</label>
                                                            </div>
                                                            <div class="col-md-6 form-group">
                                                                <input type="text" value="等待連結" disabled>
                                                            </div>
                                                            <div class="col-md-6">
                                                                <label>會員暱稱(檢舉者)</label>
                                                            </div>
                                                            <div class="col-md-6 form-group">
                                                                <input type="text" value="等待連結"
                                                                    disabled>
                                                            </div>

                                                            </div>
                                                        </div>
                                                    </div>
                                                </form>
                                            </div>
                                        </div>
                                    </div>
                            
                                <div class="col-md-5">
                                    <div class="card">
                                        <div class="card-header">
                                            <h4 class="card-title">&nbsp;</h4>
                                        </div>
                                        <div class="card-content">
                                            <div class="card-body">
                                                <form class="form form-horizontal">
                                                    <div class="form-body">
                                                        <div class="row">
                                                            <div class="col-md-6">
                                                                <label>文章編號</label>
                                                            </div>
                                                            <div class="col-md-6 form-group">
                                                                <input type="text" value="${ArticleReportVO.getArticleNo()}" disabled>
                                                            </div>

                                                            <div class="col-md-6">
                                                                <label>文章名稱</label>
                                                            </div>
                                                            <div class="col-md-6 form-group">
                                                                <input type="text" value="等待連結" disabled>
                                                            </div>
                                                            <div class="col-md-6">
                                                                <label>會員編號(食譜作者)</label>
                                                            </div>
                                                            <div class="col-md-6 form-group">
                                                                <input type="text" value="等待連結" disabled>
                                                            </div>
                                                            <div class="col-md-6">
                                                                <label>會員帳號(食譜作者)</label>
                                                            </div>
                                                            <div class="col-md-6 form-group">
                                                                <input type="text" value="等待連結" disabled>
                                                            </div>
                                                            <div class="col-md-6">
                                                                <label>會員暱稱(食譜作者)</label>
                                                            </div>
                                                            <div class="col-md-6 form-group">
                                                                <input type="text" value="等待連結" disabled>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </form>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-md-1"></div>
                            </div>
                        <div class="row match-height" style=" position: relative; text-align:center">
                            <div class="col-md-2"></div>
                            <div class="col-md-8">
                                <div>
                                <label>檢舉理由:</label>
                                </div>
                                <div style="width: 100%; min-height: 100px; padding-left:80PX">
                                    <textarea id="context" style="width: 70%; 
                                    resize: none; overflow: hidden;  padding:10PX; min-height:70px;" 
                                     disabled> ${ArticleReportVO.getReportingReason()}</textarea>

                                </div>
                            </div>
                                <div class="col-md-2"></div>
                            
                        </div>
                        <div class="row match-height" style=" position: relative; text-align:center">
                            <div class="col-md-2"></div>
                            <div class="col-md-8">
                                <div>
                                <label>處理結果:</label>
                                <input  type="radio" id="pass" name="result" value="1" style="margin-left: 60px;"><label class="badge bg-success" for="pass">通過</label>
                                <input type="radio" id="wait" name="result" value="2" checked><label class="badge bg-warning" for="wait" >尚未決定</label>
                                <input type="radio" id="failed" name="result" value="3"><label class="badge bg-danger" for="failed">未通過</label>
                            </div>
                            <div style="text-align: left; padding-left:160PX">
                            <label>理由:</label>
                             </div>
                                <div style="width: 100%; min-height: 100px; padding-left:80PX">
                                    <textarea id="context1" style="width: 70%; 
                                    resize: none; overflow: hidden;  padding:10PX; min-height:70px;" 
                                     >尚未做決定</textarea>

                                </div>
                                <div style="text-align: right;">
                                <a class="btn btn-primary rounded-pill" style=" margin-bottom: 20px;">確認修改</a><a class="btn btn-primary rounded-pill" href="${pageContext.request.contextPath}/dashboard/memeber/WCC_article_report.jsp" style="margin-right: 90px; margin-bottom: 20px;">取消修改</a>
                                </div>
                            </div>
                                <div class="col-md-2"></div>
                            
                        </div>
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
                        <p>Crafted with <span class="text-danger"><i class="bi bi-heart"></i></span> by <a
                                href="http://ahmadsaugi.com">A. Saugi</a></p>
                    </div>
                </div>
            </footer>
        </div>
    </div>
    <script src="${pageContext.request.contextPath}/dashboard/memeber/assets/vendors/jquery-3.7.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/dashboard/memeber/assets/vendors/perfect-scrollbar/perfect-scrollbar.min.js"></script>
    <script src="${pageContext.request.contextPath}/dashboard/memeber/assets/js/bootstrap.bundle.min.js"></script>

    <script src="${pageContext.request.contextPath}/dashboard/memeber/assets/vendors/simple-datatables/simple-datatables.js"></script>
    <!-- <script>
        // Simple Datatable
        let table1 = document.querySelector('#table1');
        let dataTable = new simpleDatatables.DataTable(table1);
    </script> -->

    <script src="${pageContext.request.contextPath}/dashboard/memeber/assets/js/main.js"></script>
    <script src="${pageContext.request.contextPath}/dashboard/memeber/assets\js\menu_ative.js"></script>

    <script>
        document.addEventListener("DOMContentLoaded",function () {
         $("#context").val("測試\n測試\n測試\n測試") ;
         $("#context").css("height", "auto");
         $("#context").css("height", document.getElementById("context").scrollHeight + "px");
        $("textarea").on("keydown",function () {
            this.style.height = 'auto';
            this.style.height = (this.scrollHeight) + 'px';
        });

    })
        // 监听<textarea>的输入事件
    </script>
</body>

</html>