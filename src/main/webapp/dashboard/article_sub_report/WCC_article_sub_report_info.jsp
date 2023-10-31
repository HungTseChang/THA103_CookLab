<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="com.cooklab.article_report.model.*"%>
    <%@ page import="com.cooklab.article_sub_report.model.*"%>
    <%@ page import="com.cooklab.members.model.*"%>
    
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
    <link rel="stylesheet" href="<%=request.getContextPath()%>/dashboard/assets/css/bootstrap.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/dashboard/assets/vendors/simple-datatables/style.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/dashboard/assets/vendors/perfect-scrollbar/perfect-scrollbar.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/dashboard/assets/vendors/bootstrap-icons/bootstrap-icons.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/dashboard/assets/css/app.css">
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
                                    <a href="<%=request.getContextPath()%>/dashboard/recipe/WCC_recipe.jsp">食譜表單</a>
                                </li>
                                <li class="submenu-item ">
                                    <a href="<%=request.getContextPath()%>/dashboard/hashtag/WCC_hashtag.jsp">標籤管理</a>
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
                                    <a href="<%=request.getContextPath()%>/dashboard/product/shopview.jsp">商品設定</a>
                                </li>
                                <li class="submenu-item ">
                                    <a href="<%=request.getContextPath()%>/dashboard/memberOrder/TYT_order_management.html">訂單管理</a>
                                </li>
                                <li class="submenu-item ">
                                    <a href="<%=request.getContextPath()%>/dashboard/productTag/tagview.html">商品種類管理</a>
                                </li>
                                <li class="submenu-item  ">
                                    <a href="<%=request.getContextPath()%>/dashboard/promo_code/promo_code_allview.jsp">優惠券管理</a>
                                </li>
                                <li class="submenu-item">
                                    <a href="<%=request.getContextPath()%>/dashboard/advertise/advertise_allview.jsp" >廣告管理</a>
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
                                    <a href="<%=request.getContextPath()%>/dashboard/article/HO_dscussion_cate.jsp">看板分類</a>
                                  </li>
                                  <li class="submenu-item ">
                                    <a href="<%=request.getContextPath()%>/dashboard/article/HO_discussion_allview.jsp" >文章管理</a>
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
                                    <a href="<%=request.getContextPath()%>/dashboard/notifycenter/notify-table.html">通知中心</a>
                                </li>
                                <li class="submenu-item ">
                                    <a href="<%=request.getContextPath()%>/dashboard//supportform-table.html">問題表單</a>
                                </li>
                                <li class="submenu-item active">
                                <a href="<%=request.getContextPath()%>/dashboard/news/news-table.html">最新消息</a>
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
                            <h3>討論區回文檢舉</h3>
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
                                                                <label>回文檢舉編號</label>
                                                            </div>
                                                            <div class="col-md-6 form-group">
                                                                <input type="text" value="${ArticleSubReportVO.getArticleSubReportNo()} " disabled>
                                                            </div>
                                                            <div class="col-md-6">
                                                                <label>回文檢舉時間</label>
                                                            </div>
                                                            <div class="col-md-6 form-group">
                                                                <input type="text" value="${ArticleSubReportVO.getCreatedTimestamp()}" disabled>
                                                            </div>
                                                            <div class="col-md-6">
                                                                <label>會員編號(檢舉者)</label>
                                                            </div>
                                                            <div class="col-md-6 form-group">
                                                                <input type="text" value="${ArticleSubReportVO.getReporterId()}" disabled>
                                                            </div>
                                                            <div class="col-md-6">
                                                                <label>會員帳號(檢舉者)</label>
                                                            </div>
                                                            <div class="col-md-6 form-group">
                                                                <input type="text" value="${ArticleSubReportVO.getMembers().getMemberAccount()}"disabled>
                                                            </div>
                                                            <div class="col-md-6">
                                                                <label>會員暱稱(檢舉者)</label>
                                                            </div>
                                                            <div class="col-md-6 form-group">
                                                                <input type="text" value="${ArticleSubReportVO.getMembers().getMemberNickname()}"
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
                                                                <input type="text" value="${Article.getArticleNo()}" disabled>
                                                            </div>

                                                            <div class="col-md-6">
                                                                <label>文章名稱</label>
                                                            </div>
                                                            <div class="col-md-6 form-group">
                                                                <input type="text" value="${Article.getArticleTitle()}" disabled>
                                                            </div>
                                                                    <div class="col-md-6">
                                                                <label>回文編號</label>
                                                            </div>
                                                            <div class="col-md-6 form-group">
                                                                <input type="text" value="${ArticleSubReportVO.getArticleSubNo()}" disabled>
                                                            </div>
                                                            <div class="col-md-6">
                                                                <label>會員編號(留言者)</label>
                                                            </div>
                                                            <div class="col-md-6 form-group">
                                                                <input type="text" value="${MembersVO.getMemberId()}" disabled>
                                                            </div>
                                                            <div class="col-md-6">
                                                                <label>會員帳號(留言者)</label>
                                                            </div>
                                                            <div class="col-md-6 form-group">
                                                                <input type="text" value="${MembersVO.getMemberAccount()}" disabled>
                                                            </div>
                                                            <div class="col-md-6">
                                                                <label>會員暱稱(留言者)</label>
                                                            </div>
                                                            <div class="col-md-6 form-group">
                                                                <input type="text" value="${MembersVO.getMemberNickname()}" disabled>
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
                                     disabled> ${ArticleSubReportVO.getReportingReason()}</textarea>

                                </div>
                            </div>
                                <div class="col-md-2"></div>
                            
                        </div>
                        <div class="row match-height" style=" position: relative; text-align:center">
                            <div class="col-md-2"></div>
                            <div class="col-md-8">
                                <div>
                                <label>處理結果:</label>
                                <input  type="radio" id="pass" name="result" value="0" style="margin-left: 60px;"><label class="badge bg-success" for="pass">已處裡</label>
                                <input type="radio" id="failed" name="result" value="1"><label class="badge bg-danger" for="failed">未處理</label>
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
                                <a class="btn btn-primary rounded-pill" id="confirm" style=" margin-bottom: 20px;">確認修改</a><a class="btn btn-primary rounded-pill" href="<%=request.getContextPath()%>/dashboard/article_sub_report/WCC_article_sub_report.jsp" style="margin-right: 90px; margin-bottom: 20px;">取消修改</a>
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
    
    <script src="<%=request.getContextPath()%>/dashboard/assets/vendors/jquery-3.7.1.min.js"></script>
    <script src="<%=request.getContextPath()%>/dashboard/assets/vendors/perfect-scrollbar/perfect-scrollbar.min.js"></script>
    <script src="<%=request.getContextPath()%>/dashboard/assets/js/bootstrap.bundle.min.js"></script>

    <script src="<%=request.getContextPath()%>/dashboard/assets/vendors/simple-datatables/simple-datatables.js"></script>
  <script>
        let table1 = document.querySelector('#table1');
        let dataTable = new simpleDatatables.DataTable(table1);
    </script> 

    <script src="<%=request.getContextPath()%>/dashboard/assets/js/main.js"></script>
    <script src="<%=request.getContextPath()%>/dashboard/assets\js\menu_ative.js"></script>

    <script>
        document.addEventListener("DOMContentLoaded",function () {
         $("#context").css("height", "auto");
         $("#context").css("height", document.getElementById("context").scrollHeight + "px");
        $("textarea").on("keydown",function () {
            this.style.height = 'auto';
            this.style.height = (this.scrollHeight) + 'px';
        });
           var status = "${ArticleSubReportVO.getReportingStatus()}";

          if(status==0){
        	  $("#pass").prop("checked", true);  }else{  $("#failed").prop("checked", true)}
          
         if("${ArticleSubReportVO.getReportingAnswer()}" != null){
        	 $("textarea#context1").val("${ArticleSubReportVO.getReportingAnswer()}");
         } 
          
          $("a#confirm").on("click",function(){
        	  var articleSubReportNO = "${ArticleSubReportVO.getArticleSubReportNo()}"
        	  var status= $("input[name='result']:checked").val()+"";
        	 var answer = $("textarea#context1").val();
        	
        	  
        	  var form = $("<form>", {
                  action: "<%=request.getContextPath()%>/ArticleSubReportServlet", // 表单提交的URL
                  method: "post", // 提交方法，可以是 "post" 或 "get"，根据需求设置
              });
      	    
      	       form.append($("<input>", {
                     type: "text",
                     name: "action",
                     value: "confirmArticleSubReport"
                 }));
    	       form.append($("<input>", {
                   type: "text",
                   name: "articleSubReportNo",
                   value: articleSubReportNO
               }));
    	       form.append($("<input>", {
                   type: "text",
                   name: "status",
                   value: status
               }));
    	       form.append($("<input>", {
                   type: "text",
                   name: "reportingAnswer",
                   value: answer
               }));
      	       form.appendTo("body").hide();
      	       form.submit();
      	       form.remove();
        	  
          })
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



    
})})
</script>
</body>

</html>