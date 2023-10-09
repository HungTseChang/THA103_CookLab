<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="BIG5"%>
<!DOCTYPE html>
<html>
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
                            <ul class="submenu " style="display: block;">
                          
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
            <!--   /////////////////////////////////////////////////////////////////////////////////////////  -->
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
                            <h3>�s�W�޲z��</h3>
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
                              
                    <section class="section" style="background-color: white;">
                        <div class="row">
                            <div class="col-md-2" style="background-color: white;"></div>
                            <div class="col-md-4" style="background-color: white;">
                                <div class="card">
                                    <div class="card-header">
                                    </div>
                                    <div class="card-content">
                                        <div class="card-body">
                                            <form class="form form-horizontal">
                                                <div class="form-body">
                                                    <div class="row">
                                                        <div class="col-md-4">
                                                            <label>�޲z���b��</label>
                                                        </div>
                                                        <div class="col-md-8">
                                                            <div class="form-group has-icon-left">
                                                                <div class="position-relative">
                                                                    <input type="text" class="form-control" placeholder="�b��"
                                                                        id="first-name-icon">
                                                                    <div class="form-control-icon">
                                                                        <i class="bi bi-person"></i>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <div class="col-md-4">
                                                            <label>�޲z���ʺ�</label>
                                                        </div>
                                                        <div class="col-md-8">
                                                            <div class="form-group has-icon-left">
                                                                <div class="position-relative">
                                                                    <input type="text" class="form-control" placeholder="�ʺ�"
                                                                        id="first-name-icon">
                                                                    <div class="form-control-icon">
                                                                        <i class="bi bi-heart"> </i>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <div class="col-md-4">
                                                            <label>�޲z���K�X</label>
                                                        </div>
                                                        <div class="col-md-8">
                                                            <div class="form-group has-icon-left">
                                                                <div class="position-relative">
                                                                    <input type="password" class="form-control" placeholder="�K�X"
                                                                        id="first-name-icon">
                                                                    <div class="form-control-icon">
                                                                        <svg class="bi" width="1em" height="1em" fill="currentColor">
                                                                            <use xlink:href="assets/vendors/bootstrap-icons/bootstrap-icons.svg#lock"></use>
                                                                        </svg>                                                                   
                                                                     </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <div class="card-header">
                                                        </div>
                                                    </div>
                                                </div>
                                        </div>
                                        </form>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-4" style=" background-color:  rgb(208, 250, 255);">
                                <div class="card">
                                    <div class="card-header" style="background-color: rgb(208, 250, 255);">
                                        <span>�v���W�h</span>
                                    </div>
                                    <div class="table-datatable"
                                        style="width: 100%; max-height: 300px; overflow-y: scroll; ">
                                        <table class="table-container" style="width: 100%;">
                                            <tbody>
                                                <tr>
                                                    <th>
                                                        <input class="form-check-input" type="radio" name="permission"
                                                            id="permission1">
                                                        <label class="form-check-label" for="permission1">
                                                            �`�޲z��
                                                        </label>
                                                    </th>
                                                </tr>
                                                <tr><th>
                                                    <input class="form-check-input" type="radio" name="permission"
                                                    id="permission2">
                                                <label class="form-check-label" for="permission2">
                                                    ���к޲z��
                                                </label>
                                                </th>
                                                </tr>
                                                <tr><th>
                                                    <input class="form-check-input" type="radio" name="permission"
                                                    id="permission3">
                                                <label class="form-check-label" for="permission3">
                                                    �|���޲z��
                                                </label>
                                            </th>
                                                </tr>
                                                <tr><th>
                                                    <input class="form-check-input" type="radio" name="permission"
                                                    id="permission4">
                                                <label class="form-check-label" for="permission4">
                                                    �s�i���H��
                                                </label>
                                            </th>
                                                </tr>
                                                <tr><th>
                                                    <input class="form-check-input" type="radio" name="permission"
                                                    id="permission5">
                                                <label class="form-check-label" for="permission5">
                                                    �ȪA�H��
                                                </label>
                                            </th>
                                                </tr>
                                                <tr><th>
                                                    <input class="form-check-input" type="radio" name="permission"
                                                    id="permission6">
                                                <label class="form-check-label" for="permission6">
                                                    �uŪ�� 
                                                </label>
                                            </th>
                                                </tr>
                                                <tr><th>
                                                    <input class="form-check-input" type="radio" name="permission"
                                                    id="permission7">
                                                <label class="form-check-label" for="permission7">
                                                    �Q�װϺ޲z�� 
                                                </label>
                                            </th>
                                                </tr>
                                        
                                            </tbody>
                                        </table>
                                    </div>

                                </div>

                                <div class="row">
                                    <div class="col-md-4"></div>
                                    <div class="col-md-4"></div>
                                    <div class="col-md-4">
                                        <a href="#" class="btn btn-info rounded-pill">�T�{�s�W</a>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-2" style="background-color: white;"></div>
                        </div>
                    </section>
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
    <script src="assets\vendors\jquery-3.7.1.min.js"></script>
    <script src="assets/vendors/perfect-scrollbar/perfect-scrollbar.min.js"></script>
    <script src="assets/js/bootstrap.bundle.min.js"></script>

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