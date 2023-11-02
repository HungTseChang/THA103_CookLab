<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.cooklab.article.model.*"%>
<%@ page import="com.cooklab.article_sub.model.*"%>

<!-- 1使用EL的IF語法，把數字改文字，2.在後端處理好轉換再傳送 -->

<%
// 	ArticleVO artVO2 = (ArticleVO) request.getAttribute("updatedArtVO");	

    ArticleService artSvc = new ArticleService();
    List<ArticleVO> list = artSvc.getAll();
    pageContext.setAttribute("list",list);
    
    ArticleSubService artSvc2 = new ArticleSubService();
    List<ArticleSubVO> list2 = artSvc2.getAll();
	pageContext.setAttribute("list2",list2); 
%>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>DataTable - Mazer Admin Dashboard</title>
    
    <link rel="preconnect" href="https://fonts.gstatic.com" />
<link
	href="https://fonts.googleapis.com/css2?family=Nunito:wght@300;400;600;700;800&display=swap"
	rel="stylesheet" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/mazer-main/dist/assets/css/bootstrap.css" />

<link rel="stylesheet"
	href="<%=request.getContextPath()%>/mazer-main/dist/assets/vendors/simple-datatables/style.css" />

<link rel="stylesheet"
	href="<%=request.getContextPath()%>/mazer-main/dist/assets/vendors/perfect-scrollbar/perfect-scrollbar.css" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/mazer-main/dist/assets/vendors/bootstrap-icons/bootstrap-icons.css" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/mazer-main/dist/assets/css/app.css" />
<link rel="shortcut icon"
	href="<%=request.getContextPath()%>/mazer-main/dist/assets/images/favicon.svg"
	type="image/x-icon" />
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
						<div class="logo">
							<a href="index.html"><img
								src="<%=request.getContextPath()%>/dashboard/assets/images/logo/logo.png"
								alt="Logo" srcset=""></a>
								<div style="font-size:15px;" >會員：${thisaccount} ，你好 </div>
								<div style="font-size:10px;">&nbsp;</div>
								<div style="font-size:10px;  text-align: right;"><a class="wccA"id="logout" style="  margin-left: 40px;">登出</a>
								<a class="wccA"id="design" value="${thisaccount}" style="  margin-left: 10px;" >個人資訊</a></div>
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
                                    <a href="<%=request.getContextPath()%>/dashboard/product/shopview.html">商品設定</a>
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
<!--                                 <li class="submenu-item "> -->
<%--                                     <a href="<%=request.getContextPath()%>/dashboard/article_report/WCC_article_report.jsp">食譜檢舉</a> --%>
<!--                                 </li> -->
<!--                                 <li class="submenu-item "> -->
<%--                                     <a href="src=<%=request.getContextPath()%>/dashboard//WCC_recipe_sub_report.jsp">食譜回文檢舉</a> --%>
<!--                                 </li> -->
                                <li class="submenu-item ">
                                    <a href="<%=request.getContextPath()%>/dashboard/article_report/WCC_article_report.jsp">討論區檢舉</a>
                                </li>
                                  <li class="submenu-item ">
                                    <a href="<%=request.getContextPath()%>/dashboard/article_sub_report/WCC_article_sub_report.jsp">討論區回文檢舉</a>
                                </li>
                                <li class="submenu-item ">
                                    <a href="<%=request.getContextPath()%>/dashboard/question/question-table.html">常見問題</a>
                                </li>
                                <li class="submenu-item ">
                                    <a href="<%=request.getContextPath()%>/dashboard/notifycenter/notify-table.html">系統通知</a>
                                </li>
                                <li class="submenu-item ">
                                    <a href="<%=request.getContextPath()%>/dashboard/supportform/supportform-table.html">問題表單</a>
                                </li>
                                <li class="submenu-item active">
                                <a href="<%=request.getContextPath()%>/dashboard/news/news-table.html">最新消息</a>
                                 </li>
                            </ul>
                        </li>
                        <!-- ======================================================================================================== -->
                    </ul>
                </div>
        <button class="sidebar-toggler btn x">
          <i data-feather="x"></i>
        </button>
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
        li a.sub_title {
          background-color: #435ebe;
          color: white !important;
          display: block;
          padding: 0.2rem 0.5rem !important;
          font-size: 1rem;
          display: flex;
          align-items: center;
          border-radius: 0.5rem;
          transition: all .5s;
          text-decoration: none;
          color: #25396f;
        }

        td a.wcc {
          border: 1px solid rgb(151, 135, 249);
          background-color: rgb(195, 241, 253);
          padding: 4px;
          border-radius: 20px;
        }
        
         td input.wcc {
          border: 1px solid rgb(151, 135, 249);
          background-color: rgb(195, 241, 253);
          padding: 4px;
          border-radius: 20px;
        }

        td button.wcc {
          border-radius: 20px;
        }
        td.HO_article_title{
          /* width: 15%; */
          /* white-space: wrap; */
            /*設定文字一行*/
          text-overflow: ellipsis;
          /*clip(預設)|ellipsis(省略符號)*/
          /* overflow: hidden; */
        }
      
      </style>
      <!-- ////////////////////////////////////////////////////////////////////////////////////////// -->
      <div class="page-heading">
        <div class="page-title">
          <div class="row">
            <div class="col-12 col-md-6 order-md-1 order-last">
              <h3>文章管理</h3>
            </div>
            <div class="col-12 col-md-6 order-md-2 order-first">
              <nav aria-label="breadcrumb" class="breadcrumb-header float-start float-lg-end">
                <ol class="breadcrumb">
                  <li class="breadcrumb-item">
                    <a href="index.html">Dashboard</a>
                  </li>
                  <li class="breadcrumb-item active" aria-current="page">
                    文章管理
                  </li>
                </ol>
              </nav>
            </div>
          </div>
        </div>
        <section class="section">
        	<%-- 錯誤表列 --%>
					<div style="margin-top :50px">
					<c:if test="${not empty errorMsgs}">
						<font style="color: red">請修正以下錯誤:</font>
						<ul>
							<c:forEach var="message" items="${errorMsgs}">
								<li style="color: red">${message}</li>
							</c:forEach>
						</ul>
					</c:if>
					</div>
          <div class="card">
            <div class="card-body">
              <table class="table table-striped" id="table1">
                <thead>
                  <tr>
                    <th class="first_col">文章編號</th>
                    <th>文章分類</th>
                    <th class="sec_col">文章標題</th>
                    <th class="thrid_col">文章狀態</th>
                    <th class="forth_col">文章作者</th>
                    <th>發文時間</th>
                    <th class="last_column">操作</th>
                  </tr>
                </thead>
                <tbody>
                <c:forEach var="artVO" items="${list}">
                  <tr>
                    <td>${artVO.articleNo}</td>
                    <td>${artVO.articleCategory.articleCategory}</td>
                    <td class="HO_article_title">${artVO.articleTitle}</td>
                    <td class="artice_status">
                    <!-- EL語法不能有空白,ex:test=" ${artVO.articleStatus == 0}"這樣不會報錯誤也不會有值  -->
                      <c:choose>
                      	<c:when test="${artVO.articleStatus == 0}">
                      		<a href="#" class="btn btn-success rounded-pill btn article_status" >公開</a>
                      	</c:when>
                      	<c:when test="${artVO.articleStatus == 1}">
                      		<a href="#" class="btn btn-secondary rounded-pill btn article_status">非公開</a>
                      	</c:when>
                      	    <c:when test="${artVO.articleStatus == 2}">
                      		<a href="#" class="btn btn-danger rounded-pill btn article_status" >刪除</a>
                      	 </c:when>
                      </c:choose>	
                      </td>
                    <td>${artVO.members.memberNickname}</td>
        			<td><fmt:formatDate value="${artVO.createdTimestamp}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
														
                    <td>
                    <FORM METHOD="post" ACTION="<%= request.getContextPath() %>/ArticleServlet" >
                  
					  <input type="hidden" name="articleNo" value="${artVO.articleNo}">
					  <input type="hidden" name="action" value="getOne_For_Display">
					  <input type="submit" class="wcc"  value="查看文章"> 
					</FORM>
					 </td> 
					 <td>
					  <FORM METHOD="post" ACTION="<%= request.getContextPath() %>/ArticleServlet" >
                      <select class="ch_artice_status" name="articleStatus">
                        <option>選擇狀態</option>
                        <option id="artice_Option1" value= 0>公開</option>
                        <option id="artice_Option3"	value= 1>草稿</option>
                        <option id="artice_Option4"	value= 2>刪除</option>
                      </select>
                      <input type="hidden" name="articleNo" value="${artVO.articleNo}">
                  	 <input type="hidden" name="action" value="getStatusUpdate">
					  <input type="submit" >
                      </FORM>
                    </td>
                  </c:forEach>
                  
				<!--     ===========下面sub_art的forEach =======       -->
                  
                  <c:forEach var="artVO2" items="${list2}">
                  <tr>
                    <td>${artVO2.articleSubNo}</td>
                    <td>${artVO2.article.articleNo}的回文</td>
                    <td class="HO_article_title">RE#${artVO2.article.articleTitle}</td>
                    <td class="artice_status">
                      <c:choose>
                      	<c:when test="${artVO2.articleSubStatus == 0}">
                      		<a href="#" class="btn btn-success rounded-pill btn article_status" >公開</a>
                      	</c:when>
                      	<c:when test="${artVO2.articleSubStatus == 1}">
                      		<a href="#" class="btn btn-primary rounded-pill btn article_status" >
                      		非公開</a>
                      	</c:when>
                      	    <c:when test="${artVO2.articleSubStatus == 2}">
                      		<a href="#" class="btn btn-danger rounded-pill btn article_status" >
                      		 刪除</a>
                      	 </c:when>
                      </c:choose>
                    </td>
                    <td>${artVO2.members.memberNickname}</td>
        			<td><fmt:formatDate 
        			value="${artVO2.createdTimestamp}"
 					pattern="yyyy-MM-dd HH:mm:ss" /> 
					</td>									
                    <td>
                    
                    <FORM METHOD="post" ACTION="<%= request.getContextPath() %>/ArticleSubServlet" >
<!--                 		 <a class="wcc" href="">查看文章</a> -->
					  <input type="hidden" name="articleSubNo" value="${artVO2.articleSubNo}">
					  <input type="hidden" name="action" value="getOne_For_Display">
					  <input type="submit" class="wcc"  value="查看文章"> 
					  </FORM>
					 </td> 
					 <td>
					  <FORM METHOD="post" ACTION="<%= request.getContextPath() %>/ArticleSubServlet" >
                      <select class="ch_artice_status" name="articleSubStatus">
                        <option>選擇狀態</option>
                        <option id="artice_Option1" value= 0>公開</option>
                        <option id="artice_Option3"	value= 1>草稿</option>
                        <option id="artice_Option4"	value= 2>刪除</option>
                      </select>
                      <input type="hidden" name="articleSubNo" value="${artVO2.articleSubNo}">
                  	 <input type="hidden" name="action" value="getStatusUpdate">
					  <input type="submit" >
                      </FORM>
                    </td>
                  </c:forEach>
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
		Simple Datatable
		        let table1 = document.querySelector('#table1');
		        let dataTable = new simpleDatatables.DataTable(table1);
	</script>	
	<script src="<%=request.getContextPath()%>/mazer-main/dist/assets/vendors/jquery-3.7.1.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/mazer-main/dist/assetss/vendors/perfect-scrollbar/perfect-scrollbar.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/mazer-main/dist/assets/jss/bootstrap.bundle.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/mazer-main/dist/assets/vendors/simple-datatables/simple-datatables.js"></script>

	<script
		src="<%=request.getContextPath()%>/mazer-main/dist/assets/js/main.js"></script>
	<script
		src="<%=request.getContextPath()%>/mazer-main/dist/assets/js/menu_ative.js"></script>


  <script>
  	let table1 = document.querySelector("#table1");
  	let dataTable = new simpleDatatables.DataTable(table1);
  	
    $(function () {
    	//jsp中使用read的話，會誤認為ajax要轉接網頁要改用ready
      $(document).ready("change", ".btn.article_status", function () { 
        // console.log("you touch me");
      });

      $(document).on("change", ".ch_artice_status", function () {
        let selectedOption = $(this).find("option:selected").text();
        console.log(selectedOption);
        let parentRow = $(this).closest("tr"); //用來找尋該區域的父元素<tr>，
        //使用find找到父元素<tr>區域，才可以指定該<tr>區域的內容改變，進而不影響到其他<tr>區域
        switch (selectedOption) {
          case "0":
            let new_status1 = `<a href="#" class="btn btn-success rounded-pill btn article_status">公開</a>`;
            parentRow.find(".btn.article_status").remove();
            parentRow.find(".artice_status").append(new_status1);
            break;
          case "1":
            let new_status2 = `<a href="#" class="btn btn-info rounded-pill btn article_status">非公開</a>`;
            parentRow.find(".btn.article_status").remove();
            parentRow.find(".artice_status").append(new_status2);
            break;
          case "2":
            let new_status3 = `<a href="#" class="btn btn-warning rounded-pill btn article_status">草稿</a>`;
            parentRow.find(".btn.article_status").remove();
            parentRow.find(".artice_status").append(new_status3);
            break;
          case "3":
            let new_status4 = `<a href="#" class="btn btn-danger rounded-pill btn article_status">刪除</a>`;
            parentRow.find(".btn.article_status").remove();
            parentRow.find(".artice_status").append(new_status4);
            break;
        }        
      });
    });
  </script>
   <script>
document.addEventListener("DOMContentLoaded", function () {
$("a#logout").on("click",function(e){
    e.preventDefault();
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
    e.preventDefault();
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



})
</script>
 <script>
document.addEventListener("DOMContentLoaded", function () {
$("a#logout").on("click",function(e){
    e.preventDefault();
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
    e.preventDefault();
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



})
</script>
</body>

</html>