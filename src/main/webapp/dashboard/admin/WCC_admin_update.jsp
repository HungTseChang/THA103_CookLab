<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.cooklab.admins.model.*" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>DataTable - Mazer Admin Dashboard</title>

    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Nunito:wght@300;400;600;700;800&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="<%=request.getContextPath() %>/dashboard/assets/css/bootstrap.css">

    <link rel="stylesheet" href="<%=request.getContextPath() %>/dashboard/assets/vendors/simple-datatables/style.css">

    <link rel="stylesheet" href="<%=request.getContextPath() %>/dashboard/assets/vendors/perfect-scrollbar/perfect-scrollbar.css">
    <link rel="stylesheet" href="<%=request.getContextPath() %>/dashboard/assets/vendors/bootstrap-icons/bootstrap-icons.css">
    <link rel="stylesheet" href="<%=request.getContextPath() %>/dashboard/assets/css/app.css">
    <link rel="shortcut icon" href="<%=request.getContextPath() %>/dashboard/assets/images/favicon.svg" type="image/x-icon">
    				<style>
				a.wccA{
					border: 1px solid rgb(151, 135, 249);
	background-color: rgb(195, 241, 253);
	padding: 4px;
	border-radius: 20px;
				}
				</style>
</head>

<body>
    <div id="app">
        <div id="sidebar" class="active">
            <div class="sidebar-wrapper active">
                <div class="sidebar-header">
                    <div class="d-flex justify-content-between">
  <!-- 						======================================== -->
						<div class="logo">
							<a href="index.html"><img
								src="<%=request.getContextPath()%>/dashboard/assets/images/logo/logo.png"
								alt="Logo" srcset=""></a>
								<div style="font-size:15px;" >會員：${thisaccount} ，你好 </div>
								<div style="font-size:10px;">&nbsp;</div>
								<div style="font-size:10px;  text-align: right;"><a class="wccA"id="logout" style="  margin-left: 40px;">登出</a><a class="wccA"id="design" style="  margin-left: 10px;">個人資訊</a></div>
						</div>
<!-- 						======================================== -->
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
                            <a href="<%=request.getContextPath()%>/dashboard/login/WCC_welcome.jsp" class='sidebar-link'>
                                <i class="bi bi-grid-fill"></i>
                                <span>後台首頁</span>
                            </a>
                        </li>

                        <li class="sidebar-item ">
                            <a href="<%=request.getContextPath()%>/dashboard/member/WCC_member.jsp" class='sidebar-link'>
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
                                    <a href="<%=request.getContextPath()%>/dashboard/admin/WCC_admin_management.jsp">管理管理者</a>
                                </li>
                             
                                <li class="submenu-item ">
                                    <a href="<%=request.getContextPath()%>/dashboard/permission/WCC_permission.jsp">創立權限規則</a>
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
                                    <a href="<%=request.getContextPath()%>/dashboard/article_report/WCC_recipe_report.jsp">食譜檢舉</a>
                                </li>
                                <li class="submenu-item ">
                                    <a href="src=<%=request.getContextPath()%>/dashboard//WCC_recipe_sub_report.jsp">食譜回文檢舉</a>
                                </li>
                                <li class="submenu-item ">
                                    <a href="<%=request.getContextPath()%>/dashboard/article_report/WCC_article_report.jsp">討論區檢舉</a>
                                </li>
                                  <li class="submenu-item ">
                                    <a href="<%=request.getContextPath()%>/dashboard/article_sub_report/WCC_article_sub_report.jsp">討論區回文檢舉</a>
                                </li>
                                <li class="submenu-item ">
                                    <a href="<%=request.getContextPath()%>/dashboard/notifycenter/official-notify.html">系統通知</a>
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
                            <h3>管理管理者權限</h3>
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
                            <div class="col-md-1" style="background-color: white;"></div>
                            <div class="col-md-5" style="background-color: white;">
                                <div class="card">
                                    <div class="card-header">
                                    </div>
                                    <div class="card-content">
                                        <div class="card-body">
                                            <form class="form form-horizontal">
                                                <div class="form-body">
                                                    <div class="row">
                                                      <div class="col-md-4">
                                                            <label>管理員編號</label>
                                                        </div>
                                                        <div class="col-md-8">
                                                            <div class="form-group has-icon-left">
                                                                <div class="position-relative">
                                                                    <input type="text"  name="adminNo" class="form-control" placeholder="帳號"
                                                                        id="first-name-icon" value="${AdminsVO.adminNo}" disabled>
                                                                    <div class="form-control-icon">
                                                                        <i class="bi bi-person"></i>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <div class="col-md-4">
                                                            <label>管理員帳號</label>
                                                        </div>
                                                        <div class="col-md-8">
                                                            <div class="form-group has-icon-left">
                                                                <div class="position-relative">
                                                                    <input type="text"  name="account" class="form-control" placeholder="帳號"
                                                                        id="first-name-icon" value="${AdminsVO.adminAccount}" disabled>
                                                                    <div class="form-control-icon">
                                                                        <i class="bi bi-person"></i>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <div class="col-md-4">
                                                            <label>管理員暱稱</label>
                                                        </div>
                                                        <div class="col-md-8">
                                                            <div class="form-group has-icon-left">
                                                                <div class="position-relative">
                                                                    <input type="text"  name="nickname" class="form-control" placeholder="暱稱"
                                                                        id="first-name-icon" value="${AdminsVO.adminNickname}" disabled>
                                                                    <div class="form-control-icon">
                                                                        <i class="bi bi-heart"> </i>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <div class="col-md-4">
                                                            <label>管理員密碼</label>
                                                        </div>
                                                        <div class="col-md-8">
                                                            <div class="form-group has-icon-left">
                                                                <div class="position-relative">
                                                                    <input type="password"  name="password"  class="form-control" placeholder="密碼"
                                                                        id="first-name-icon" value="${AdminsVO.adminPassword}" disabled>
                                                                    <div class="form-control-icon">
                                                                        <svg class="bi" width="1em" height="1em" fill="currentColor">
                                                                            <use xlink:href="<%=request.getContextPath() %>/dashboard/assets/vendors/bootstrap-icons/bootstrap-icons.svg#lock"></use>
                                                                        </svg>                                                                   
                                                                     </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                       <div class="col-md-4">
                                                            <label>密碼確認</label>
                                                        </div>
                                                        <div class="col-md-8">
                                                            <div class="form-group has-icon-left">
                                                                <div class="position-relative">
                                                                    <input type="password"  name="passwordcheck"  class="form-control" placeholder="密碼"
                                                                        id="first-name-icon" value="${AdminsVO.adminPassword}" disabled>
                                                                    <div class="form-control-icon">
                                                                        <svg class="bi" width="1em" height="1em" fill="currentColor">
                                                                            <use xlink:href="<%=request.getContextPath() %>/dashboard/assets/vendors/bootstrap-icons/bootstrap-icons.svg#lock"></use>
                                                                        </svg>                                                                   
                                                                     </div>
                                                                </div>
                                                            </div>
                                                        </div>                                                   
                                                    <div class ="col -md-12">
                                                   <lable id="error"></lable>
                                                    </div>
                                                </div></div>
                   						</form>
                              </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-4" style=" background-color:  rgb(208, 250, 255);">
                                <div class="card">
                                    <div class="card-header" style="background-color: rgb(208, 250, 255);">
                                        <span>權限規則</span>
                           <div class="datable dropdown">
                        <select class="wcc" id="select1">
                        <option value="5">5</option>
                        <option value="10" selected>10</option>
                        <option value="15">15</option>
                        <option value="20">20</option>
                        </select>
                        <label>每頁展示筆數</label>
                        </div>
                                    </div>
                                    <div class="table-datatable"
                                        style="width: 100%; max-height: 300px; overflow-y: scroll; ">
                                        <table class="table-container" id="table2"style="width: 100%;">
                                            <tbody>
                                               
                                        
                                            </tbody>
                                        </table>
                                    </div>

                                </div>

                                <div class="row">
                                    <div class="col-md-7">
        <span class="page-item wcc" id="prev-page">上一頁</span>
        <span class="page-item wcc" id="next-page">下一頁</span>
        <span id="current-page">1</span>
        <span id="total-pages">of 1</span>
                                    </div>
                                    <div class="col-md-5">
                                        <a href="#" id="insert"class="btn btn-info rounded-pill">確認新增</a>
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
                <div class="float-end .sidebar-item.active">
                    <p>Crafted with <span class="text-danger"><i class="bi bi-heart"></i></span> by <a
                            href="http://ahmadsaugi.com">A. Saugi</a></p>
                </div>
            </div>
        </footer>
    </div>
    </div>
  
  <script src="<%=request.getContextPath() %>/dashboard/assets\vendors\jquery-3.7.1.min.js"></script>
    <script src="<%=request.getContextPath() %>/dashboard/assets/vendors/perfect-scrollbar/perfect-scrollbar.min.js"></script>
    <script src="<%=request.getContextPath() %>/dashboard/assets/js/bootstrap.bundle.min.js"></script>

    <script src="<%=request.getContextPath() %>/dashboard/assets/vendors/simple-datatables/simple-datatables.js"></script>
    <script src="<%=request.getContextPath() %>/dashboard/assets/js/main.js"></script>
    <script src="<%=request.getContextPath() %>/dashboard/assets\js\menu_ative.js"></script>
     <script>document.addEventListener("DOMContentLoaded",function () {    
    $("a#insert").on("click",function(){
  	  var account= $("input[name='account']").val()+"";
	  var nickname= $("input[name='nickname']").val()+"";
	  var password= $("input[name='password']").val()+"";
	  var passwordcheck= $("input[name= 'passwordcheck']").val()+"";
	  var permission =$('input[name="permission"]:checked').val()
	  var adminNo=$('input[name="adminNo"]').val()
	  console.log(account+"||"+nickname+"||"+password+"||"+passwordcheck+"||"+permission+"||"+adminNo);
	  if(password != passwordcheck){
		  $("lable#error").append("密碼與密碼確認不相符，請重新輸入密碼");
		  return;
	  }
  	  var form = $("<form>", {
            action: "<%=request.getContextPath() %>/AdminsServlet", // 表单提交的URL
            method: "post", // 提交方法，可以是 "post" 或 "get"，根据需求设置
        });
	    
	       form.append($("<input>", {
               type: "text",
               name: "action",
               value: "updateAdmins"
           }));
	       form.append($("<input>", {
             type: "text",
             name: "account",
             value: account
         }));
	       form.append($("<input>", {
             type: "text",
             name: "nickname",
             value: nickname
         }));
	       form.append($("<input>", {
             type: "text",
             name: "password",
             value: password
         }));
	       form.append($("<input>", {
	             type: "text",
	             name: "permission",
	             value: permission
	         }));
	       form.append($("<input>", {
	             type: "text",
	             name: "adminNo",
	             value: adminNo
	         }));
	       form.appendTo("body").hide();
	       form.submit();
	       console.log(form);
	       form.remove();
  	  
    })
//     ======================================================
	
	var	 permissionList=JSON.parse('${json_permission}');
	var	 adminFake=JSON.parse('${json_AdminsVOFake}');
	 var number =permissionList.length;
	console.log(adminFake);

// 	=====================================
 var rowsPerPage = 10;
 var currentPage = 1;
 
    function updateTable() {    
    	var startIndex = (currentPage - 1) * rowsPerPage;
    	var endIndex = startIndex + rowsPerPage;
    	var tableBody = $("table#table2").children("tbody");
    	tableBody.empty();
	for(let i = startIndex ; i<endIndex ;i++){
    	        	  
  if (i <number){
  	   let ifcheck = adminFake.permissionNo;
  	   console.log(adminFake);
  	   console.log(ifcheck);
  	   var aftercheck;

	  let aa = permissionList[i];
 	   if(ifcheck == aa.permissionNo){ aftercheck='checked'; }else{aftercheck = ' ';}

  	  let text = "";
	  text += " <tr><th>";
	  text += ' <input class="form-check-input" type="radio" name="permission"  id="permission'+aa.permissionNo;
	  text +=' " value=" '+aa.permissionNo+' " '+aftercheck+'>';
	  text +='<label class="form-check-label" for="permission'+aa.permissionNo+' " > ';
	  text += aa.permissionTitle;
	  text +='</label></th></tr>';

	  tableBody.append(text);
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
    updateTable();
	//     ======================================================
     })
    </script>   
    
  <script>
document.addEventListener("DOMContentLoaded", function () {
$("a#logout").on("click",function(e){
    e.preventDefault;
var formlogout = $("<form>", {
action: "<%=request.getContextPath()%>/LoginServlet", // 表单提交的URL
    method: "post", // 提交方法，可以是 "post" 或 "get"，根据需求设置
});

formlogout.append($("<input>", {
type: "hidden",
name: "action",
value: "logout"
}));
   formlogout.appendTo("body").hide();
   formlogout.submit();
   formlogout.remove();



    
})


$("a#design").on("click",function(e){
	    e.preventDefault;
	var formdesign = $("<form>", {
	action: "<%=request.getContextPath()%>/AdminsServlet", // 表单提交的URL
	    method: "post", // 提交方法，可以是 "post" 或 "get"，根据需求设置
	});

	formdesign.append($("<input>", {
	type: "hidden",
	name: "action",
	value: "design"
	}));
	formdesign.appendTo("body").hide();
	formdesign.submit();
	formdesign.remove();
	
	
})


	let table1 = document.querySelector("#table1");
	let dataTable = new simpleDatatables.DataTable(table1);

})
</script>
</body>

</html>