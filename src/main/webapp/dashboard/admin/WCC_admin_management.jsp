<%@ page language="java" contentType="text/html; charset=BIG5"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="java.sql.Timestamp" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="com.cooklab.admins.model.*" %>
<%@ page import="com.google.gson.Gson" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>DataTable - Mazer Admin Dashboard</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=yes">
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
                            <div class="row" style="background-color: white">
                             <div class="col-md-3"  style=" text-align: right; display: flex; flex-direction: column; justify-content: center;"> 
                           <select class="wcc" id="selectsearch" style="background-color:white; padding-left: 20px;: border-color:white;">
                        <option value="adminNo">管理者編號</option>
                        <option value="adminNickname">管理者暱稱</option>              
                       <option value="permissionNo">管理者權限編號</option>                                 
                        <option value="adminAccount">管理者帳號</option>                                         
                          <option value="createdTimestamp">時間</option>                       
                        <option value="wcc" selected>所有欄位</option>
                        </select>                                 </div>    
                               <div class="col-md-6"  >                    
                             
                       <input type="text"  id="searchbar" class="form-control" placeholder="請輸入 編號、管理員、帳號、暱稱或時間" style="pading-color:  rgb(208, 250, 255);" >
                                                     </div>
                              
                                <div class="datable dropdown" style="padding:10px;">
                        <select class="wcc" id="select1">
                        <option value="5">5</option>
                        <option value="10">10</option>
                        <option value="15">15</option>
                        <option value="20">20</option>
                        </select>
                        <label>每頁展示筆數</label>
                        </div>
                            <div class="table-datatable " style="width: 100%; height: 400px;  overflow-y: scroll; overflow-x: scroll; padding:0;">
                                <table class="table-container" id="table1" style="width: 100%; font-size: 20px; border-top:40px;">
                                    <thead style="background-color: rgb(212, 212, 212);">
                                        <tr>
                                            <th class="number" name="adminNo" style="width: 146;">管理者編號</th>
                                            <th>管理者暱稱</th>
                                            <th class="number" name="permissionNo">管理者權限編號</th>
                                              <th>管理者帳號</th>
                                                <th>管理者建立時間</th>
                                                   <th>操作</th>
                                        </tr>
                                    </thead>
                                    <tbody id="tbody">
           


                                    </tbody>
                                </table>
                            </div>
                              </div>
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
                                <a   class="btn btn-info rounded-pill" id="enter0" value=0>新增內容</a>
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
    <script src="<%=request.getContextPath() %>/dashboard/assets\vendors\jquery-3.7.1.min.js"></script>
    <script src="<%=request.getContextPath() %>/dashboard/assets/vendors/perfect-scrollbar/perfect-scrollbar.min.js"></script>
    <script src="<%=request.getContextPath() %>/dashboard/assets/js/bootstrap.bundle.min.js"></script>
    <script src="<%=request.getContextPath() %>/dashboard/assets/vendors/simple-datatables/simple-datatables.js"></script>
    <script src="<%=request.getContextPath() %>/dashboard/assets/js/main.js"></script>
    <script src="<%=request.getContextPath() %>/dashboard/assets\js\menu_ative.js"></script>

<script src="<%=request.getContextPath() %>/dashboard/assets\vendors\jquery-3.7.1.min.js"></script>

<script>
document.addEventListener("DOMContentLoaded", function () {
// 	================================
   $("#searchbar").on("keyup", function() {
    var value1 = $(this).val().toLowerCase();
    var detail = "td"+"."+$("#selectsearch").val();
  $("table#table1").children("tbody").empty();
  $("table#table1").children("tbody").append(textall);
    
    let line =  $("#tbody tr").filter(function() {
 return $(this).toggle($(this).find(detail).text().toLowerCase().indexOf(value1) > -1);
    });
	   });	
	
// 	=================================
 var rowsPerPage = 5;
 var currentPage = 1;
 var myList;

 if('${json}'){
	 myList=JSON.parse('${json}');
	}else{ 
		console.log("reload");
		var form = $("<form>", {
            action: "<%=request.getContextPath() %>/AdminsServlet", // 表单提交的URL
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
	}
//  =================================
	$(document).on("click","a#enter0",function(){
		var form1 = $("<form>", {
            action: "<%=request.getContextPath() %>/AdminsServlet", // 表单提交的URL
            method: "post", // 提交方法，可以是 "post" 或 "get"，根据需求设置
        });
	    
	       form1.append($("<input>", {
               type: "text",
               name: "action",
               value: "insert"
           }));
	       form1.appendTo("body").hide();
	       form1.submit();
	       form1.remove();
	})
 
 
 
 
//  ====================================
var tbodyall;
 var number =myList.length;
	var textall = "";
console.log(number);
	var onload = function(){
		let tableBodya = $("table#table1").children("tbody");
 	   for (let i=0;i <myList.length;i++){
     	  let aa = myList[i];
  		let text = "";
  		text += "<tr>";
  	  text += "<td class='wcc adminNo'>"+aa.adminNo+"</td>";
  	  text +=" <td class='wcc adminNickname'>"+aa.adminNickname+"</td>";
  	  text +=" <td class='wcc permissionNo'>"+aa.permissionNo+"</td>";
  	  text +=" <td class='wcc adminAccount'>"+aa.adminAccount+"</td>";
  	  text +=" <td class='wcc createdTimestamp'>"+aa.createdTimestamp+"</td>";
  	  text +=`
  			<td>
  		  <FORM METHOD="post" ACTION="<%=request.getContextPath() %>/AdminsServlet" style="margin-bottom: 0px;">
  		     <input type="submit" value="修改">
  		     <input type="hidden" name="adminNo"  value=`;
  		    text +=aa.adminNo;
  		    text +=` >
  		     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
  		</td>
  		<td>
  		  <FORM METHOD="post" ACTION="<%=request.getContextPath() %>/AdminsServlet" style="margin-bottom: 0px;">
  		     <input type="submit" value="刪除">
  		     <input type="hidden" name="adminNo"  value=`;
  	  text +=aa.adminNo;
  	  text +=`>
  		     <input type="hidden" name="action" value="delete"></FORM>
  		</td>
  		  `;
  	  text += "</tr>";
  	textall+=text;
 	   }
  	  tableBodya.append(textall);
  	 tbodyall =$('#tbody tr').toArray();
	console.log(tbodyall);
	
	}
	function updateTable() {    
		var startIndex = (currentPage - 1) * rowsPerPage;
		var endIndex = startIndex + rowsPerPage;
		var tableBody = $("table#table1").children("tbody");
		tableBody.empty();

		          for(let i = startIndex ; i<endIndex ;i++){
		        	  
		        	   if (i <  tbodyall.length){
		        	  let aa = tbodyall[i];

		           $("table#table1").children("tbody").append(aa);
		        	   }
		          }
		          $("#current-page").text(currentPage);
		          var totalPages = Math.ceil(myList.length / rowsPerPage);
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
    
// ====================
$(document).on("click","tr th.number",function(e){
	var column ="td."+$(e.target).attr("name");
	var textArray = [];
	$(column).each(function() {
	    textArray.push($(this).text());
	});
	var sortedArray = textArray.slice().sort();
	var isSorted = JSON.stringify(sortedArray) === JSON.stringify(textArray);
if(isSorted){
	tbodyall.sort(function(a,b){
		var dateA = $(a).find(column).text(); 
        var dateB = $(b).find(column).text();
        return dateB - dateA;
	})
	
}else{
	tbodyall.sort(function(a,b){
		var dateA = $(a).find(column).text(); 
        var dateB = $(b).find(column).text();
        return dateA - dateB;
	})
}
    updateTable();

	 
	})
	
	onload();
//  ==========================   
    updateTable();
    
	
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