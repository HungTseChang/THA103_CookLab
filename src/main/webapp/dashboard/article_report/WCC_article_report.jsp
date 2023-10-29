<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.cooklab.article_report.model.*"%>
<%@ page import =" java.util.List"%>
<%@ page import =" java.util.ArrayList"%>
<%@ page import="org.hibernate.Session"%>
<%@ page import="com.cooklab.util.HibernateUtil" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import ="com.google.gson.Gson"  %>
<%@ page import ="com.google.gson.JsonElement"  %>
<%@ page import ="com.google.gson.JsonParser"  %>
<%@ page import="java.sql.Timestamp" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="com.cooklab.article.model.ArticleVO" %>
<%@ page import="com.cooklab.article_report.model.*" %>
<%@ page import="java.util.*"%>



<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>DataTable - Mazer </title>

    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Nunito:wght@300;400;600;700;800&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/dashboard/assets/css/bootstrap.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/dashboard/assets/vendors/simple-datatables/style.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/dashboard/assets/vendors/perfect-scrollbar/perfect-scrollbar.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/dashboard/assets/vendors/bootstrap-icons/bootstrap-icons.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/dashboard/assets/css/app.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/resizable-columns/1.1.0/css/resizable-columns.min.css">
    
    <style>
				a.wccA{
					border: 1px solid rgb(151, 135, 249);
	background-color: rgb(195, 241, 253);
	padding: 4px;
	border-radius: 20px;
				}
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
                td.wcc{
                text-align: center;
                }
                th{
                    white-space: nowrap;
                }
                span.wcc{
                    border: 1px solid rgb(151, 135, 249);
                    background-color: rgb(195, 241, 253); 
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
                                <span style="font-size: 30px;">文章檢舉</span>
                        </div>
                        <div class="card-body" style="width: 100%; overflow: scroll">
						  <div class="row" style="background-color: white">
                        
                         <div class="col-md-2"  style=" text-align: right; display: flex; flex-direction: column; justify-content: center;">
                        <select class="wcc" id="selectsearch" style="background-color:white; padding-left: 20px;: border-color:white;">
                        <option value="articleReportNo">檢舉編號</option>
                        <option value="reporterId">檢舉者ID</option>              
                       <option value="reporterAccount">檢舉者帳號</option>                                 
                        <option value="reporterNikname">檢舉者暱稱</option>
                         <option value="articleNo">文章編號</option>
                        <option value="articleTitle">文章名稱</option>   
                        <option value="reportingStatus">檢舉狀態</option>                                           
                          <option value="createdTimestamp">時間</option>                       
                        <option value="wcc" selected>所有欄位</option>
                        </select>                          </div>    
                               <div class="col-md-6"  >                     
                       <input type="text"  id="searchbar" class="form-control" placeholder="請輸入 編號、ID、帳號、暱稱或時間" style="pading-color:  rgb(208, 250, 255);" >

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
                            <table class="table table-striped" id="table1" style="scrollCollapse: true">
                                <thead>
                                    <tr>
                                        <th class="resizable number articleReportNo" name="articleReportNo">文章檢舉編號</th>
                                        <th class="resizable number reporterId"name="reporterId">會員編號(檢舉者)</th>
                                        <th class="resizable reporterAccount"name=" reporterAccount">會員帳號(檢舉者)</th>                                    
                                        <th class="resizable reporterNikname"name=" reporterNikname">會員暱稱(檢舉者)</th>
                                        <th class="resizable number articleNo"name="articleNo">文章編號</th>
                                        <th class="resizable articleTitle"name=" articleTitle">文章名稱</th>                
                                        <th class="resizable reportingReason"name=" reportingReason">檢舉理由</th>
                                        <th class="resizable reportingStatus"name=" reportingStatus">檢舉狀態</th>
                                        <th class="resizable createdTimestamp"name=" createdTimestamp">檢舉時間</th>
                                        <th class="resizable">操作</th>
                                    </tr>
                                </thead>
                                <tbody id="tbody">


                                </tbody>
                            </table>
                        </div>
                                                      </div>
                    </div>
   <div class="pagination">
        <span class="page-item wcc" id="prev-page">上一頁</span>
        <span class="page-item wcc" id="next-page">下一頁</span>
        <span id="current-page">1</span>
        <span id="total-pages">of 1</span>
    </div>
                </section>
            </div>




            <footer>
                
            </footer>
        </div>
    </div>

<script src="<%=request.getContextPath()%>/dashboard/assets\vendors\jquery-3.7.1.min.js"></script>
    <script>
        document.addEventListener("DOMContentLoaded", function () {
//         	===========================================
   $("#searchbar").on("keyup", function() {
    var value1 = $(this).val().toLowerCase();
    var detail = "td"+"."+$("#selectsearch").val();
    $("#tbody tr").filter(function() {
      $(this).toggle($(this).find(detail).text().toLowerCase().indexOf(value1) > -1);
    });
  });
        		
//         =====================================
	var myList;     
	var nikname;
       var title;     	
       var account;
       var rowsPerPage =5;
       var currentPage = 1;
	if('${json}'){
	 myList=JSON.parse('${json}');
 	title = JSON.parse('${title}');
	nikname = JSON.parse('${nickname}');
	account = JSON.parse('${account}');
	}else{ 
		console.log("reload");
		var form = $("<form>", {
            action: "<%=request.getContextPath()%>/ArticleReportServlet", // 表单提交的URL
            method: "post", // 提交方法，可以是 "post" 或 "get"，根据需求设置
        });
	    
	       form.append($("<input>", {
               type: "text",
               name: "action",
               value: "getArticleReport"
           }));
	       form.appendTo("body").hide();
	       form.submit();
	       form.remove();
			console.log("reload");
	}
	
	
	
	
	var textall = "";
	var tbodyall;

	var onload = function(){
		let tableBodya = $("table#table1").children("tbody");
		let text = "";
 	   for (let i=0;i <  myList.length;i++){
     	  let aa = myList[i];
     	  let status ={
     			  0:" <span class='badge bg-success'>已處理</span>",
     			  1:" <span class='badge bg-danger'>未處理</span>",
     	  }		
     	  let text = "";
		  text += "<tr>";
		  text += "<td class='wcc articleReportNo'>"+aa.articleReportNo+"</td>";
		  text +=" <td class='wcc reporterId'>"+aa.reporterId+"</td>";
		  text +=" <td class='wcc  reporterAccount'>"+account[i]+"</td>";
		  text +=" <td class='wcc  reporterNikname'>"+nikname[i]+"</td>";
		  text +=" <td class='wcc articleNo'>"+aa.articleNo+"</td>";
		  text +=" <td class='wcc articleTitle'>"+title[i]+"</td>";
		  text +=" <td class='wcc reportingReason'>"+aa.reportingReason+"</td>";
		  text +=" <td class='wcc reportingStatus'>"+status[aa.reportingStatus]+"</td>";
		  text +=" <td class='wcc createdTimestamp'>"+aa.createdTimestamp+"</td>";
		  text += "<td>";
    	  text +=  " <form action='<%=request.getContextPath()%>/ArticleReportServlet' method='get'>"; 
    	  text +=  "<p ><input class='wcc' type='submit'  value='修改資料'></p>";
		  text += "<input type='hidden' name='articleReportNo' value='";
		  text += aa.articleReportNo+"'>";
		  text += "<input type='hidden' name='action' value='changeData'></form></td>";
		  text += "</tr>";	
		  textall+=text;
}
 	  tableBodya.append(textall);
 	 tbodyall =$('#tbody tr').toArray();
	}
	
	
	
	
	
	//         =====================================

        	



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
              var totalPages = Math.ceil(myList.length / rowsPerPage);
              if (currentPage < totalPages) {
                  currentPage++;
                  updateTable();
              }
          });
         
//         ==============
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
	
	
// 	==============
          
      onload();    
          updateTable();
        })
        
         </script>
    
    <script src="<%=request.getContextPath()%>/dashboard/assets/vendors/perfect-scrollbar/perfect-scrollbar.min.js"></script>
    <script src="<%=request.getContextPath()%>/dashboard/assets/js/bootstrap.bundle.min.js"></script>
    <script src="<%=request.getContextPath()%>/dashboard/assets/vendors/sweetalert2/sweetalert2.all.min.js"></script>
    <script src="<%=request.getContextPath()%>/dashboard/assets/vendors/simple-datatables/simple-datatables.js"></script>
    <script src="<%=request.getContextPath()%>/dashboard/assets/js/main.js"></script>
    <script src="<%=request.getContextPath()%>/dashboard/assets\js\menu_ative.js"></script>
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



    
})})
</script>
</body>

</html>