<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.cooklab.purchase_order_detail.model.*"%>
<%@ page import="com.cooklab.purchase_order.model.*"%>
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
                            <ul class="submenu "style="display: block;">
                                <li class="submenu-item " >
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
                input.WCC_memeber_info{
                    width: 60%;
                }
            </style>
            <!-- ////////////////////////////////////////////////////////////////////////////////////////// -->
            <div class="page-heading">
                <div class="page-title">
                    <div class="row">
                        <div class="col-12 col-md-6 order-md-1 order-last">
                            <h3>�s�W�i�f���</h3>
                            <p class="text-subtitle text-muted"></p>
                        </div>
                        <div class="col-12 col-md-6 order-md-2 order-first">
                            <nav aria-label="breadcrumb" class="breadcrumb-header float-start float-lg-end">
                                <ol class="breadcrumb">
                                    <li class="breadcrumb-item"><a href="TYT_purchase_order_allView.html">�i�f���</a></li>
                                    <li class="breadcrumb-item active" aria-current="page">�s�W�i�f���</li>
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
                                <!-- <div class="col-md-8 col-8"> -->
                                    <div class="card">
                                        <div class="card-body">
                                            <!-- <h5 class="card-title">�s�W�i�f���</h5> -->
                                            <form action="<%=request.getContextPath()%>/PurchaseOrderServlet" method="post">
                                                <div class="mb-3">
                                                    <label for="supplierInput" class="form-label">�i�f�t��</label>
                                                    <input type="text" class="form-control" id="supplierInput" placeholder="�п�J�i�f�t��">
                                                </div>
                                                <div class="mb-3">
                                                    <label for="purchaseTimeInput" class="form-label">�i�f�ɶ�</label>
                                                    <input type="datetime-local" step="1" class="form-control" id="purchaseTimeInput" >
                                                </div>
                                                <h6>�i�f�ӫ~</h6>
                                                <table class="table" id="purchaseTable">
                                                    <thead>
                                                        <tr>
                                                            <th>�ӫ~�W��</th>
                                                            <th>�i�f�ƶq</th>
                                                            <th>������</th>
                                                            <th>�O�s����</th>
                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                        <tr>
                                                            <td><input type="text" class="form-control" name="product[]"></td>
                                                            <td><input type="number" class="form-control" name="quantity[]"></td>
                                                            <td><input type="number" class="form-control" name="price[]"></td>
                                                            <td><input type="datetime-local" step="1" class="form-control" name="expiration[]"></td>
                                                        </tr>
                                                    </tbody>
                                                </table>
                                                <button type="button" class="btn btn-outline-primary" id="addRowBtn">�s�W�U�@���ӫ~</button>

                                                <div class="text-end">
                                                    <button type="submit" class="btn btn-success">�x�s�i�f���</button>
                                                </div>
                                            </form>
                                        </div>
                                    </div>
                                <!-- </div> -->
                                <!-- <div class="col-md-4 ">
                                    <div class="card" style="top: 80px; right: 20px; max-width: 100%">
                                        <span>�H���Y��</span>
                                        <img src=".\assets\images\faces\2.jpg" alt="">
                                    </div>
                                </div> -->
                                <!-- ///////////////// -->
                                <div class="col-md-12 col-8"></div>
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
     <script>    // �s�W�ӫ~�檺���s�I���ƥ� 
         document.getElementById('addRowBtn').addEventListener('click', function () {
             const tableBody = document.querySelector('#purchaseTable tbody');
             const newRow = document.createElement('tr');
             newRow.innerHTML = `
                 <td><input type="text" class="form-control" name="product[]"></td>
                 <td><input type="number" class="form-control" name="quantity[]"></td>
                 <td><input type="number" class="form-control" name="price[]"></td>
                 <td><input type="datetime-local" step="1" class="form-control" name="expiration[]"></td>
             `;
             tableBody.appendChild(newRow);
         });
     </script> 
    <script src="http://localhost:8081/com.tha103.cooklab/mazer-main/dist/assets\vendors\jquery-3.7.1.min.js"></script>
    <script src="http://localhost:8081/com.tha103.cooklab/mazer-main/dist/assets/vendors/perfect-scrollbar/perfect-scrollbar.min.js"></script>
    <script src="http://localhost:8081/com.tha103.cooklab/mazer-main/dist/assets/js/bootstrap.bundle.min.js"></script>

    <script src="http://localhost:8081/com.tha103.cooklab/mazer-main/dist/assets/vendors/simple-datatables/simple-datatables.js"></script>
    <script>
        // Simple Datatable
        let table1 = document.querySelector('#table1');
        let dataTable = new simpleDatatables.DataTable(table1);
    </script>

    <script src="http://localhost:8081/com.tha103.cooklab/mazer-main/dist/assets/js/main.js"></script>
    <script src="http://localhost:8081/com.tha103.cooklab/mazer-main/dist/assets\js\menu_ative.js"></script>
</body>

</html>