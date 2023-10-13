<%@ page language="java" contentType="text/html; charset=BIG5"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="java.sql.Timestamp" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="com.cooklab.admins.model.*" %>
<%@ page import="com.google.gson.Gson" %>
<%
AdminsService AdminsService = new AdminsService(); 
List<AdminsVO> list = AdminsService.getAll();
String json = new Gson().toJson(list);
pageContext.setAttribute("json",json);
%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>DataTable - Mazer Admin Dashboard</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=yes">
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Nunito:wght@300;400;600;700;800&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/dashboard/assets/css/bootstrap.css">

    <link rel="stylesheet" href="${pageContext.request.contextPath}/dashboard/assets/vendors/simple-datatables/style.css">

    <link rel="stylesheet" href="${pageContext.request.contextPath}/dashboard/assets/vendors/perfect-scrollbar/perfect-scrollbar.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/dashboard/assets/vendors/bootstrap-icons/bootstrap-icons.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/dashboard/assets/css/app.css">
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/dashboard/assets/images/favicon.svg" type="image/x-icon">
</head>

<body>
    <div id="app">
        <div id="sidebar" class="active">
            <div class="sidebar-wrapper active">
                <div class="sidebar-header">
                    <div class="d-flex justify-content-between">
                        <div class="logo">
                            <a href="index.html"><img src="${pageContext.request.contextPath}/dashboard/assets/images/logo/logo.png" alt="Logo" srcset=""></a>
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
                            <ul class="submenu " style="display: block;">
                          
                                <li class="submenu-item active">
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
                            <ul class="submenu ">
                                <li class="submenu-item ">
                                    <a href="WCC_recipe_report.html">食譜檢舉</a>
                                </li>
                                <li class="submenu-item ">
                                    <a href="WCC_recipe_sub_report.html">食譜回文檢舉</a>
                                </li>
                                <li class="submenu-item ">
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
            <!--   /////////////////////////////////////////////////////////////////////////////////////////  -->
            <style>
            table{
              border-collapse: collapse;
        width: 100%;
        }
                td a.wcc {
                    border: 1px solid rgb(151, 135, 249);
                    background-color: rgb(195, 241, 253);
                    padding: 4px;
                    border-radius: 20px;
                }

                td button.wcc {
                    border-radius: 20px;}
                   table, th, td {
                    border: 1px solid rgb(47, 46, 48);
                    padding: 5px;
                     white-space: nowrap
                     
                }
                td{
                        white-space: nowrap; 
      					  overflow: hidden; 
      					  text-overflow: ellipsis; 
                }

                .hightlight {
                    border-color: blue;
                    background-color: rgb(163, 163, 248);
                }

                .-none {
                    display: none;
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
                <div class="col-md-2 col-12"></div>
            </div>
            <section class="section" style="background-color: rgb(208, 250, 255);   height: 500px;">
                <div class="row" style="height: 100%;">
                    <div class="col-md-1" style="background-color:rgb(208, 250, 255);"></div>
                    <div class="col-md-10" style=" background-color:  rgb(208, 250, 255);">
                        <div class="card">
                            <div class="card-header" style="background-color: rgb(208, 250, 255);">
                                <span style="font-size: 30px;">權限規則</span>
                            </div>
                            <div class="row">
                             <div class="col-md-2"  style="background-color: rgb(208, 250, 255);"> <lable style=" ">資料查詢</lable></div>    
                               <div class="col-md-4" >                     
                       <input type="text"  id="searchbar" class="form-control" placeholder="請輸入 管理員、帳號或暱稱" style="pading-color:  rgb(208, 250, 255);" >
                              </div>
                              </div>
                         <div class="datable dropdown">
                        <select class="wcc" id="select1">
                        <option value="5">5</option>
                        <option value="10">10</option>
                        <option value="15">15</option>
                        <option value="20">20</option>
                        </select>
                        <label>每頁展示筆數</label>
                        </div>
                            <div class="table-datatable " style="width: 100%; height: 400px;  overflow-y: scroll; overflow-x: scroll;">
                                <table class="table-container" id="table1" style="width: 100%; font-size: 20px;">
                                    <thead style="background-color: rgb(212, 212, 212);">
                                        <tr>
                                            <th style="width: 146;">管理者編號</th>
                                            <th>管理者暱稱</th>
                                            <th>管理者權限編號</th>
                                              <th>管理者帳號</th>
                                                <th>管理者建立時間</th>
                                        </tr>
                                    </thead>
                                    <tbody>
           


                                    </tbody>
                                </table>
                            </div>

                        </div>

                        <div class="row">
                           <div class="col-md-4 pagination">
        <span class="page-item wcc" id="prev-page">上一頁</span>
        <span class="page-item wcc" id="next-page">下一頁</span>
        <span id="current-page">1</span>
        <span id="total-pages">of 1</span>
   								 </div>
                            <div class="col-md-8">
                                <a href="${pageContext.request.contextPath}/dashboard/admin/WCC_permission_createnew.jsp" class="btn btn-info rounded-pill" id="enter0" value=0>新增內容</a>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-1" style="background-color: rgb(208, 250, 255);"></div>
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
    <script src="${pageContext.request.contextPath}/dashboard/assets\vendors\jquery-3.7.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/dashboard/assets/vendors/perfect-scrollbar/perfect-scrollbar.min.js"></script>
    <script src="${pageContext.request.contextPath}/dashboard/assets/js/bootstrap.bundle.min.js"></script>

    <script src="${pageContext.request.contextPath}/dashboard/assets/vendors/simple-datatables/simple-datatables.js"></script>


    <script src="${pageContext.request.contextPath}/dashboard/assets/js/main.js"></script>
    <script src="${pageContext.request.contextPath}/dashboard/assets\js\menu_ative.js"></script>

<script src="${pageContext.request.contextPath}/dashboard/assets\vendors\jquery-3.7.1.min.js"></script>

<script>
document.addEventListener("DOMContentLoaded", function () {
 var rowsPerPage = 5;
 var currentPage = 1;
 var myList;

 if('${json}'){
	 myList=JSON.parse('${json}');
	}else{ 
		console.log("reload");
		var form = $("<form>", {
            action: "${pageContext.request.contextPath}/AdmisServlet", // 表单提交的URL
            method: "post", // 提交方法，可以是 "post" 或 "get"，根据需求设置
        });
	    
	       form.append($("<input>", {
               type: "text",
               name: "action",
               value: "getAlladmins"
           }));
	       form.appendTo("body").hide();
	       form.submit();
	       form.remove();
			console.log("reload");
	}
console.log(myList);
 var number =myList.length;
    function updateTable() {    
    	var startIndex = (currentPage - 1) * rowsPerPage;
    	var endIndex = startIndex + rowsPerPage;
    	var tableBody = $("table#table1").children("tbody");
    	tableBody.empty();
	for(let i = startIndex ; i<endIndex ;i++){
    	        	  
  if (i <number){
	  let aa = myList[i];
  	  let text = "";
	  text += "<tr>";
	  text += "<td class='wcc'>"+aa.adminNo+"</td>";
	  text +=" <td class='wcc'>"+aa.adminNickname+"</td>";
	  text +=" <td class='wcc'>"+aa.permissionNo+"</td>";
	  text +=" <td>"+aa.adminAccount+"</td>";
	  text +=" <td>"+aa.createdTimestamp+"</td>";
	  text +=`
			<td>
		  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/AdminsServlet" style="margin-bottom: 0px;">
		     <input type="submit" value="修改">
		     <input type="hidden" name="adminNo"  value=`;
		    text +=aa.adminNo;
		    text +=` >
		     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
		</td>
		<td>
		  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/AdminsServlet" style="margin-bottom: 0px;">
		     <input type="submit" value="刪除">
		     <input type="hidden" name="adminNo"  value=`;
	  text +=aa.adminNo;
	  text +=`>
		     <input type="hidden" name="action" value="delete"></FORM>
		</td>
		  `;
	  text += "<tr>";
	  $("table#table1").children("tbody").append(text);
     	   }
	}
$("#current-page").text(currentPage);
var totalPages = Math.ceil(number/ rowsPerPage);
$("#total-pages").text("of " + totalPages);
	}
    $("#select1").change(function() {
 	   rowsPerPage = $(this).val();
 	   currentPage = 1;
 	   updateTable();
  });
    $("#prev-page").click(function() {
        if (currentPage > 1) {
            currentPage--;
            updateTable();
        }
        
        
    });
    
    $("#next-page").click(function() {
        var totalPages = Math.ceil(number / rowsPerPage);
        if (currentPage < totalPages) {
            currentPage++;
            updateTable();
        }
    });
    
    function searchbar(){
    	
    	
    }
    
    
    
    updateTable();
    
	
    })

</script>

</body>

</html>