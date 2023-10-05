<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>DataTable - Mazer Admin Dashboard</title>

    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Nunito:wght@300;400;600;700;800&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="assets/css/bootstrap.css">

    <link rel="stylesheet" href="assets/vendors/simple-datatables/style.css">

    <link rel="stylesheet" href="assets/vendors/perfect-scrollbar/perfect-scrollbar.css">
    <link rel="stylesheet" href="assets/vendors/bootstrap-icons/bootstrap-icons.css">
    <link rel="stylesheet" href="assets/css/app.css">
    <link rel="shortcut icon" href="assets/images/favicon.svg" type="image/x-icon">
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
                            <ul class="submenu ">
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
                                <li class="submenu-item ">
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
                                <li class="submenu-item ">
                                    <a href="ding-support-tickets-table.html">���D���</a>
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
                            <h3>DataTable</h3>
                            <p class="text-subtitle text-muted">For user to check they list</p>
                        </div>
                        <div class="col-12 col-md-6 order-md-2 order-first">
                            <nav aria-label="breadcrumb" class="breadcrumb-header float-start float-lg-end">
                                <ol class="breadcrumb">
                                    <li class="breadcrumb-item"><a href="index.html">Dashboard</a></li>
                                    <li class="breadcrumb-item active" aria-current="page">DataTable</li>
                                </ol>
                            </nav>
                        </div>
                    </div>
                </div>
                <section class="section">
                    <div class="card">
                        <div class="card-header">
                            Simple Datatable
                        </div>
                        <div class="card-body">
                            <table class="table table-striped" id="table1">
                                <thead>
                                    <tr>
                                        <th>�|���s��</th>
                                        <th>�ʺ�</th>
                                        <th>�b��</th>
                                        <th>�q�l�H�c</th>
                                        <th>�|�����A</th>
                                        <th>�|���޲z</th>
                                    </tr>
                                </thead>
                                <tbody>

                                    <tr>
                                        <td>0000001</td>
                                        <td>���f�X</td>
                                        <td>tomato_red</td>
                                        <td>tomato_red@gmail.com</td>
                                        <td> <span class="badge bg-success">���`</span></td>
                                        <td>
                                            <a class="wcc" href="WCC_memeber_info.html">�ԲӸ��</a>
                                            <button class="wcc">���]�K�X</button>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>0000002</td>
                                        <td>��f�X</td>
                                        <td>tomato_orange</td>
                                        <td>tomato_orange@gmail.com</td>
                                        <td> <span class="badge bg-success">���`</span></td>
                                        <td>
                                            <a class="wcc" href="WCC_memeber_info.html">�ԲӸ��</a>
                                            <button class="wcc">���]�K�X</button>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>0000003</td>
                                        <td>���f�X</td>
                                        <td>tomato_yellow</td>
                                        <td>tomato_yellow@gmail.com</td>
                                        <td> <span class="badge bg-success">���`</span></td>
                                        <td>
                                            <a class="wcc" href="WCC_memeber_info.html">�ԲӸ��</a>
                                            <button class="wcc">���]�K�X</button>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>0000004</td>
                                        <td>��f�X</td>
                                        <td>tomato_green</td>
                                        <td>tomato_green@gmail.com</td>
                                        <td> <span class="badge bg-success">���`</span></td>
                                        <td>
                                            <a class="wcc" href="WCC_memeber_info.html">�ԲӸ��</a>
                                            <button class="wcc">���]�K�X</button>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>0000005</td>
                                        <td>�ŵf�X</td>
                                        <td>tomato_blue</td>
                                        <td>tomato_blue@gmail.com</td>
                                        <td> <span class="badge bg-success">���`</span></td>
                                        <td>
                                            <a class="wcc" href="WCC_memeber_info.html">�ԲӸ��</a>
                                            <button class="wcc">���]�K�X</button>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>0000006</td>
                                        <td>�Q�f�X</td>
                                        <td>tomato_indigo</td>
                                        <td>tomato_indigo@gmail.com</td>
                                        <td> <span class="badge bg-success">���`</span></td>
                                        <td>
                                            <a class="wcc" href="WCC_memeber_info.html">�ԲӸ��</a>
                                            <button class="wcc">���]�K�X</button>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>0000007</td>
                                        <td>���f�X</td>
                                        <td>tomato_purple</td>
                                        <td>tomato_purple@gmail.com</td>
                                        <td> <span class="badge bg-danger">���P</span></td>
                                        <td>
                                            <a class="wcc" href="WCC_memeber_info.html">�ԲӸ��</a>
                                            <button class="wcc">���]�K�X</button>
                                        </td>
                                    </tr>

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
                        <p>Crafted with <span class="text-danger"><i class="bi bi-heart"></i></span> by <a
                                href="http://ahmadsaugi.com">A. Saugi</a></p>
                    </div>
                </div>
            </footer>
        </div>
    </div>
    <script>
        document.addEventListener("DOMContentLoaded", function () {
            $("button.wcc").on('click', (e) => {
                Swal.fire({
                    icon: "success",
                    title: "���]�K�X���\!"
                })
            })




        })
    </script>
    <script src="assets\vendors\jquery-3.7.1.min.js"></script>
    <script src="assets/vendors/perfect-scrollbar/perfect-scrollbar.min.js"></script>
    <script src="assets/js/bootstrap.bundle.min.js"></script>
    <script src="assets/vendors/sweetalert2/sweetalert2.all.min.js"></script>
    <script src="assets/vendors/simple-datatables/simple-datatables.js"></script>
    <script>
        // Simple Datatable
        let table1 = document.querySelector('#table1');
        let dataTable = new simpleDatatables.DataTable(table1);
    </script>
    
    <script src="assets/js/main.js"></script>
    <script src="assets\js\menu_ative.js"></script>

</body>

</html>