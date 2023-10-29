<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>DataTable - Mazer Admin Dashboard</title>

    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Nunito:wght@300;400;600;700;800&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="../assets/css/bootstrap.css">

    <link rel="stylesheet" href="../assets/vendors/simple-datatables/style.css">

    <link rel="stylesheet" href="../assets/vendors/perfect-scrollbar/perfect-scrollbar.css">
    <link rel="stylesheet" href="../assets/vendors/bootstrap-icons/bootstrap-icons.css">
    <link rel="stylesheet" href="../assets/css/app.css">
    <link rel="shortcut icon" href="../assets/images/favicon.svg" type="image/x-icon">
</head>

<body>
    <div id="app">
       <%@ include file="sidebar.file" %>
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
                            <h3>進貨表單</h3>
                        </div>
                        <div class="col-12 col-md-6 order-md-2 order-first">
                            <nav aria-label="breadcrumb" class="breadcrumb-header float-start float-lg-end">
                                <ol class="breadcrumb">
                                    <li class="breadcrumb-item"><a href="#">進貨表單</a></li>
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
                            <a href="TYT_purchase_order_newList.html"><button class="btn btn-success">新增進貨表單</button></a>
                            <table class="table table-striped" id="table1">
                                <thead>
                                    <tr>
                                        <th>進貨編號</th>
                                        <th>進貨廠商</th>
                                        <th>總金額</th>
                                        <th>進貨時間</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <!-- JavaScript將在這裡動態添加假資料 -->
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
        // 創建20份假資料
        for (let i = 1; i <= 20; i++) {
            const purchaseNumber = String(i).padStart(8, '0'); // 將數字填充為八位數字
            const supplier = `Supplier ${i}`;
            const totalMoney = Math.floor(Math.random() * (365 * 24 * 60 ));
            // 生成隨機的進貨時間，範圍在過去一年內
            const oneYearAgo = new Date();
            oneYearAgo.setFullYear(oneYearAgo.getFullYear() - 1);
            const purchaseTime = new Date(
                oneYearAgo.getTime() + Math.random() * (365 * 24 * 60 * 60 * 1000)
            );
    
            // 格式化進貨時間為 yyyy-mm-dd hh:mm:ss
            const formattedPurchaseTime = purchaseTime.toISOString().slice(0, 19).replace("T", " ");
    
            // 創建新的表格行並填充假資料
            const newRow = document.createElement('tr');
            newRow.innerHTML = `
                <td>${purchaseNumber}</td>
                <td>${supplier}</td>
                <td>${totalMoney}</td>
                <td class="d-flex justify-content-between align-items-center">
                <span>${formattedPurchaseTime}</span>
                <div class="d-flex justify-content-end align-items-center">
                    <a href="TYT_purchase_order_edit.html" class="btn btn-outline-secondary" style="margin-right: 8px;">編輯或查看</a>

                    <span></span>
                    <button class="btn btn-outline-danger">刪除</button>
                </div>
                </td>

            `;
    
            // 將新行添加到表格中
            document.querySelector('#table1 tbody').appendChild(newRow);
        }
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