<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.cooklab.purchase_order.model.*"%>
<%
    PurchaseOrderService purchaseOrderSvc = new PurchaseOrderService();
    List<PurchaseOrderVO> list = purchaseOrderSvc.getAll();
    pageContext.setAttribute("list",list);
%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>DataTable - Mazer Admin Dashboard</title>

    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Nunito:wght@300;400;600;700;800&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="http://localhost:8081/com.tha103.cooklab/mazer-main/dist/assets/css/bootstrap.css">

    <link rel="stylesheet" href="http://localhost:8081/com.tha103.cooklab/mazer-main/dist/assets/vendors/simple-datatables/style.css">

    <link rel="stylesheet" href="http://localhost:8081/com.tha103.cooklab/mazer-main/dist/assets/vendors/perfect-scrollbar/perfect-scrollbar.css">
    <link rel="stylesheet" href="http://localhost:8081/com.tha103.cooklab/mazer-main/dist/assets/vendors/bootstrap-icons/bootstrap-icons.css">
    <link rel="stylesheet" href="http://localhost:8081/com.tha103.cooklab/mazer-main/dist/assets/css/app.css">
    <link rel="shortcut icon" href="http://localhost:8081/com.tha103.cooklab/mazer-main/dist/assets/images/favicon.svg" type="image/x-icon">
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
                                <span>��x����</span>
                            </a>
                        </li>


                        <li class="sidebar-item ">
                            <a href="WCC_memeber.html" class='sidebar-link'>
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
                                    <a href=".\WCC_permission_management.html">�޲z�޲z��</a>
                                </li>
                             
                                <li class="submenu-item ">
                                    <a href=".\WCC_permission_createrule.html">�Х��v���W�h</a>
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
                                    <a href=".\recipe_form.html">���Ъ��</a>
                                </li>
                                <li class="submenu-item ">
                                    <a href=".\hashtag_form.html">���Һ޲z</a>
                                </li>
                            </ul>
                        </li>

                        <li class="sidebar-item  has-sub">
                            <a href="#" class='sidebar-link'>
                                <i class="bi bi-grid-1x2-fill"></i>
                                <span>�ӫ��޲z</span>
                            </a>
                            <ul class="submenu " style="display: block;">
                                <li class="submenu-item ">
                                    <a href=".\shopview.html">�ӫ~�]�w</a>
                                </li>
                                <li class="submenu-item ">
                                    <a href="TYT_order_management.html">�q��޲z</a>
                                </li>
                                <li class="submenu-item">
                                    <a href=".\GCpromo_info.html">�s�W�u�f��</a>
                                </li>
                                <li class="submenu-item  ">
                                    <a href=".\GCpromo.html">�u�f��޲z</a>
                                </li>
                                <li class="submenu-item">
                                    <a href=".\GCadvertise.html" >�s�i�޲z</a>
                                </li>
                                <li class="submenu-item">
                                    <a href=".\GCadvertise_info.html">�s�W�s�i</a>
                                </li>
                                <li class="submenu-item active">
                                    <a href="TYT_purchase_order_allView.html">�i�f���</a>
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
                                    <a href=".\HO_discussion_cate.html">�ݪO����</a>
                                  </li>
                                  <li class="submenu-item ">
                                    <a href=".\HO_discussion_info.html" >�峹�޲z</a>
                                  </li>
                            </ul>
                        </li>
                        <li class="sidebar-item  has-sub">
                            <a href="#" class='sidebar-link'>
                                <i class="bi bi-pen-fill"></i>
                                <span>�ƾڤ��R</span>
                            </a>
                            <ul class="submenu ">
                                <li class="submenu-item ">
                                    <a href="#">�|���ƾ�</a>
                                </li>
                                <li class="submenu-item ">
                                    <a href="#">���мƾ�</a>
                                </li>
                                <li class="submenu-item ">
                                    <a href="#">�ӫ��ƾ�</a>
                                </li>
                                <li class="submenu-item ">
                                    <a href="#">�峹�ƾ�</a>
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
                                    <a href="WCC_recipe_report.html">�������|</a>
                                </li>
                                <li class="submenu-item ">
                                    <a href="WCC_recipe_sub_report.html">���Ц^�����|</a>
                                </li>
                                <li class="submenu-item ">
                                    <a href="WCC_article_report.html">�Q�װ����|</a>
                                </li>
                                <li class="submenu-item ">
                                    <a href="WCC_official_notify.html">�t�γq��</a>
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
            </style>
            <!-- ////////////////////////////////////////////////////////////////////////////////////////// -->
            <div class="page-heading">
                <div class="page-title">
                    <div class="row">
                        <div class="col-12 col-md-6 order-md-1 order-last">
                            <h3>�i�f���</h3>
                        </div>
                        <div class="col-12 col-md-6 order-md-2 order-first">
                            <nav aria-label="breadcrumb" class="breadcrumb-header float-start float-lg-end">
                                <ol class="breadcrumb">
                                    <li class="breadcrumb-item"><a href="#">�i�f���</a></li>
                                </ol>
                            </nav>
                        </div>
                    </div>
                </div>
                <section class="section">
                    <div class="card">
                        <!-- <div class="card-header">
                            
                        </div> -->
                        <div class="card-body">
                            <a href="TYT_purchase_order_newList.html"><button class="btn btn-success">�s�W�i�f���</button></a>
                            <table class="table table-striped" id="table1">
                                <thead>
                                    <tr>
                                        <th>�i�f�s��</th>
                                        <th>�i�f�t��</th>
                                        <th>�`���B</th>
                                        <th>�i�f�ɶ�</th>
                                    </tr>
                                </thead>
                                <tbody>
                                <c:forEach var="purchaseOrderVO" items="${list}">
                                <tr>
                                    <td>${purchaseOrderVO.purchaseOrderNo}</td>
					                <td>${purchaseOrderVO.purchaseOrderSupplier}</td>
					                <td>${purchaseOrderVO.purchaseOrderTotal}</td>
					                <td class="d-flex justify-content-between align-items-center">
					                <span>${purchaseOrderVO.purchaseOrderDate}</span>
					                <div class="d-flex justify-content-end align-items-center">
					                    <a href="TYT_purchase_order_edit.html" class="btn btn-outline-secondary" style="margin-right: 8px;">�s��άd��</a>
					
					                    <span></span>
					                    <form action="<%=request.getContextPath()%>/PurchaseOrderServlet" method="post">
						                    <input type="hidden" name="orderNo" value="${purchaseOrderVO.purchaseOrderNo}">
						                    <input type="hidden" name="action" value="delete">
						                    <button type="submit" class="btn btn-outline-danger">�R��</button>
						                </form>
					                </div>
					                </td>
					              </tr>
                                </c:forEach>
                                </tbody>
                        </div>
                    </div>

                </section>
            </div>

            <footer>

            </footer>
        </div>
    </div>
    <script>

    </script>

    <script src="../assets\vendors\jquery-3.7.1.min.js"></script>
    <script src="../assets/vendors/perfect-scrollbar/perfect-scrollbar.min.js"></script>
    <script src="../assets/js/bootstrap.bundle.min.js"></script>
    <script src="../assets/vendors/sweetalert2/sweetalert2.all.min.js"></script>
    <script src="../assets/vendors/simple-datatables/simple-datatables.js"></script>
    <script>
        // Simple Datatable
        let table1 = document.querySelector('#table1');
        let dataTable = new simpleDatatables.DataTable(table1);
    </script>
    
    <script src="../assets/js/main.js"></script>
    <script src="../assets\js\menu_ative.js"></script>

</body>

</html>