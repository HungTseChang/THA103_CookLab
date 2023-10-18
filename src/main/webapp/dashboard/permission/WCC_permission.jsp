<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>DataTable - Mazer Admin Dashboard</title>

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
                          
                                <li class="submenu-item ">
                                    <a href=".\WCC_permission_management.html">管理管理者</a>
                                </li>
                             
                                <li class="submenu-item active">
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
                td a.wcc {
                    border: 1px solid rgb(151, 135, 249);
                    background-color: rgb(195, 241, 253);
                    padding: 4px;
                    border-radius: 20px;
                }

                td button.wcc {
                    border-radius: 20px;
                }

                .table-container th,
                td {
                    text-align: center;
                    padding: 8px;
                    border: 1px solid #ddd;
                }

                .hightlight {
                    border-color: blue;
                    background-color: rgb(163, 163, 248);
                }

                .morelight {
                    border-color: blue;
                    background-color: rgb(246, 249, 184);
                }

                .-none {
                    display: none;
                }
                .wcc.none{
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
                <div class="col-md-12 card-header"
                    style="background-color: rgb(208, 250, 255); padding-bottom: 10px;  border: 10px solid rgb(170, 199, 234);">
                    <div class="card">
                         <div class="datable dropdown">
                        <select class="wcc" id="select1">
                        <option value="5">5</option>
                        <option value="10">10</option>
                        <option value="15">15</option>
                        <option value="20">20</option>
                        </select>
                        <label>每頁展示筆數</label>
                        </div>
                        <div class="table-datatable" style="max-height: 400px; overflow-y: scroll;">
                            <table class="table-container" id="table1"
                                style="width: 100%;white-space: nowrap; overflow: auto; border-collapse: collapse; border: 1px solid #000;">
                                <thead style="position: sticky;top: 0; background-color: white;">
                                    <tr class="title">
                                        <th>職稱編號</th>
                                        <th>職稱名稱</th>
                                        <th>總管理權限</th>
                                        <th>停止所有權限</th>
                                        <th>會員管理權限</th>
                                        <th>廣告投放權限</th>
                                        <th>檢舉管理權限</th>
                                        <th>討論區權限</th>
                                        <th>食譜管理權限</th>
                                         <th>創建時間</th>
                                    </tr>
                                </thead>
                                <tbody style="white-space: nowrap; overflow: auto;">
                                  


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
                    <div  class="col-md-8  style="position: relative; left: 70%;">
                                <a class="rounded-pill btn btn-primary wcc" id="enter0" style="position: relative;">新增職稱</a>
                    </div>
                    </div>
                </div>
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
    <script src="${pageContext.request.contextPath}/dashboard/assets\vendors\jquery-3.7.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/dashboard/assets/vendors/perfect-scrollbar/perfect-scrollbar.min.js"></script>
    <script src="${pageContext.request.contextPath}/dashboard/assets/js/bootstrap.bundle.min.js"></script>
    <script src="${pageContext.request.contextPath}/dashboard/assets/vendors/simple-datatables/simple-datatables.js"></script>

    <script src="${pageContext.request.contextPath}/dashboard/assets/js/main.js"></script>
    <script src="${pageContext.request.contextPath}/dashboard/assets\js\menu_ative.js"></script>
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
        	            action: "${pageContext.request.contextPath}/PermissionServlet", // 表单提交的URL
        	            method: "post", // 提交方法，可以是 "post" 或 "get"，根据需求设置
        	        });
        		    
        		       form.append($("<input>", {
        	               type: "text",
        	               name: "action",
        	               value: "getPermission"
        	           }));
        		       form.appendTo("body").hide();
        		       form.submit();
        		       form.remove();
        				console.log("reload");
        		}
        	console.log(myList);
 var number =myList.length;	
       
        
        let permission = { 
    	        0: `
    	           <a class="btn btn-danger rounded-pill " value="0">X</a> 		   
    	    		   `,
    	    	1: `
    	           <a class="btn btn-primary rounded-pill" value="1">O</a>
    	    	`	   
    	       } 
        
       let  updateTable  = function(){
    	    	var startIndex = (currentPage - 1) * rowsPerPage;
    	    	var endIndex = startIndex + rowsPerPage;
    	    	var tableBody = $("table#table1").children("tbody");
    	    	tableBody.empty();

    		for(let i = startIndex ; i<endIndex ;i++){
    	    	        	  
    	  if (i <number){
    		  let aa = myList[i];
    	  	  let text = "";
    		  text += "<tr>";
    		  text += "<td name='permissionNo' value="+aa.permissionNo+">"+aa.permissionNo+"</td>";
    		  text +=" <td name='permissionTitle'>"+aa.permissionTitle+"</td>";
    		  text +=" <td name='superAdmin'>"+permission[aa.superAdmin]+"</td>";
    		  text +=" <td  name='cancelAllPermission'>"+permission[aa.cancelAllPermission]+"</td>";
    		  text +=" <td name='membershipManagement'>"+permission[aa.membershipManagement]+"</td>"; 
    		  text +=" <td name='advertisingManagement'>"+permission[aa.advertisingManagement]+"</td>"; 
    		  text +=" <td name='reportingManagement'>"+permission[aa.reportingManagement]+"</td>"; 
    		  text +=" <td name='articleManagement'>"+permission[aa.articleManagement]+"</td>"; 
    		  text +=" <td name='recipeManagement'>"+permission[aa.recipeManagement]+"</td>"; 
    		  text +=" <td name='createdTimestamp'>"+aa.createdTimestamp+"</td>"; 
    		  text +=`
    				<td>
                     <a  class="modify wcc" style="margin-bottom: 0px;">修改</a>
    			</td>
    			<td>
    			  <FORM METHOD="post" ACTION="${pageContext.request.contextPath}/PermissionServlet" style="margin-bottom: 0px;">
    			     <input type="submit" value="刪除">
    			     <input type="hidden" name="permissionNo"  value=`;
    		  text +=aa.permissionNo;
    		  text +=`>
    			     <input type="hidden" name="action" value="delete"></FORM>
    			</td>
    			  `;
    		  text += "</tr>";

    		  tableBody.append(text);
    	     	   }
    		}   	
    		$("#current-page").text(currentPage);
    		var totalPages = Math.ceil(number/ rowsPerPage);
    		$("#total-pages").text("of " + totalPages);
       }
       
//         =============================================
       let saveupdate = function(e){
        	let permissionNo= $(e.target).closest("tr").find("td[name='permissionNo']").text();
        	let permissionTitle= $(e.target).closest("tr").find("td[name='permissionTitle']").text();
        	let superAdmin= $(e.target).closest("tr").find("td[name='superAdmin']").find("a").attr("value");
        	let cancelAllPermission= $(e.target).closest("tr").find("td[name='cancelAllPermission']").find("a").attr("value");
        	let membershipManagement= $(e.target).closest("tr").find("td[name='membershipManagement']").find("a").attr("value");
        	let advertisingManagement= $(e.target).closest("tr").find("td[name='advertisingManagement']").find("a").attr("value");
        	let reportingManagement= $(e.target).closest("tr").find("td[name='reportingManagement']").find("a").attr("value");
        	let articleManagement= $(e.target).closest("tr").find("td[name='articleManagement']").find("a").attr("value");
        	let recipeManagement= $(e.target).closest("tr").find("td[name='recipeManagement']").find("a").attr("value");
        	var save = $("<form>", {
	            action: "${pageContext.request.contextPath}/PermissionServletServlet", // 表单提交的URL
	            method: "post", // 提交方法，可以是 "post" 或 "get"，根据需求设置
	        });
        	save.append($("<input>", {
	               type: "text",
	               name: "permissionNo",
	               value: permissionNo
	           }));
        	save.append($("<input>", {
	               type: "text",
	               name: "permissionTitle",
	               value:permissionTitle
	           }));
        	save.append($("<input>", {
	               type: "text",
	               name: "superAdmin",
	               value: superAdmin
	           }));
        	save.append($("<input>", {
	               type: "text",
	               name: "cancelAllPermission",
	               value:cancelAllPermission
	           }));
        	save.append($("<input>", {
	               type: "text",
	               name: "membershipManagement",
	               value: membershipManagement
	           }));
        	save.append($("<input>", {
	               type: "text",
	               name: "advertisingManagement",
	               value: advertisingManagement
	           }));
        	save.append($("<input>", {
	               type: "text",
	               name: "reportingManagement",
	               value: reportingManagement
	           }));
        	save.append($("<input>", {
	               type: "text",
	               name: "articleManagement",
	               value: articleManagement
	           }));
        	save.append($("<input>", {
	               type: "text",
	               name: "recipeManagement",
	               value: recipeManagement
	           }));
        	save.append($("<input>", {
	               type: "text",
	               name: "action",
	               value: "update"
	           }));
        	save.appendTo("body").hide();
        	save.submit();
        	save.remove();
    	   
       }
//         ==============================================
       let light = function (e) {
       if ( $("tr.hightlight").length == 0){
           $(e.target).closest("tr").addClass("hightlight");  
       }}
//        ===============================================
       let modify = function (e) {
           e.preventDefault();
            let value =  $(e.target).attr("value")
           console.log(value);
           if ($(e.target).closest("tr").hasClass("hightlight")){
        	 if(value==0){
        		 $(e.target).closest("td").html(permission[1]);
        	 }else{ $(e.target).closest("td").html(permission[0]); }  
           }      
       }
       
       $(document).on("click", "a.btn", function(e){
    	   modify(e);
       });
//        ===========================================
       $(document).on("click", "a.modify", function(e){
    	   if ( $("tr.hightlight").length == 0){
    	   light(e);
    	   console.log("AA")
    	   $(e.target).text("儲存修改")}else if(
    			   $(e.target).closest("tr").hasClass("hightlight")
    	   ){
    		   $("tr.hightlight").removeClass("hightlight");
    		   $(e.target).text("修改");
    		   saveupdate(e);
    	   }else{
    		   alter("已有其他欄位正在修改");
    	   }
    	   
       });
//        ======================
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
        
        
        
        let addnew = function(){
            var currentTime = new Date();
               // 格式化时间为 "YYYY-MM-DD HH:MM:SS" 形式
            var formattedTime = currentTime.getFullYear() + "-"
                + (currentTime.getMonth() + 1) + "-"
                + currentTime.getDate() + " "
                + currentTime.getHours() + ":"
                + currentTime.getMinutes() + ":"
                + currentTime.getSeconds();
        	
//                 let selection = "<select>";
//                 selection += "<option value='0'>X</option>";
//                 selection += "<option value='1'>O</option>";
//                 selection += "</select>";
        		let selection = ' <a class="btn btn-danger rounded-pill " value="0">X</a> 	';
        		
        		
        	let newone = ""
        		newone += "<tr class='hightlight'>";
        		newone += "<td name='permissionNo'>"+"新的權限"+"</td>";
        		newone +=" <td name='permissionTitle'>"+"<input id='newtitle' type='text'>"+"</td>";
        		newone +=" <td name='superAdmin'>"+selection+"</td>";
        		newone +=" <td  name='cancelAllPermission'>"+selection+"</td>";
        		newone +=" <td name='membershipManagement'>"+selection+"</td>"; 
        		newone +=" <td name='advertisingManagement'>"+selection+"</td>"; 
        		newone +=" <td name='reportingManagement'>"+selection+"</td>"; 
        		newone +=" <td name='articleManagement'>"+selection+"</td>"; 
        		newone +=" <td name='recipeManagement'>"+selection+"</td>"; 
        		newone +=" <td name='createdTimestamp'>"+formattedTime+"</td>"; 
        		newone +=`
      				<td>
//         			  <FORM METHOD="post" ACTION="${pageContext.request.contextPath}/PermissionServlet" style="margin-bottom: 0px;">
//    			     <input type="submit" value="新增">
//    			     <input type="hidden" name="action" value="insert"></FORM>
						<a  class="insert wcc" style="margin-bottom: 0px;">新增</a>
		    			</td>
      			<td>
                <a  class="cancel wcc" style="margin-bottom: 0px;">取消新增</a>
			</td>
      			`
        		newone +="</tr>";
        		 $("table#table1").children("tbody").append(newone);
        		
        }
        
        $("a#enter0").on("click",function(){
        	addnew();
        })
        
         $(document).on("click", "a.insert", function(e){
         	let permissionNo= $(e.target).closest("tr").find("td[name='permissionNo']").text();
        	let permissionTitle= $(e.target).closest("tr").find("td[name='permissionTitle']").text();
        	let superAdmin= $(e.target).closest("tr").find("td[name='superAdmin']").find("input").attr("value");
        	let cancelAllPermission= $(e.target).closest("tr").find("td[name='cancelAllPermission']").attr("value");
        	let membershipManagement= $(e.target).closest("tr").find("td[name='membershipManagement']").attr("value");
        	let advertisingManagement= $(e.target).closest("tr").find("td[name='advertisingManagement']").attr("value");
        	let reportingManagement= $(e.target).closest("tr").find("td[name='reportingManagement']").attr("value");
        	let articleManagement= $(e.target).closest("tr").find("td[name='articleManagement']").attr("value");
        	let recipeManagement= $(e.target).closest("tr").find("td[name='recipeManagement']").attr("value");
        	var save = $("<form>", {
	            action: "${pageContext.request.contextPath}/PermissionServletServlet", // 表单提交的URL
	            method: "post", // 提交方法，可以是 "post" 或 "get"，根据需求设置
	        });
        	save.append($("<input>", {
	               type: "text",
	               name: "permissionNo",
	               value: permissionNo
	           }));
        	save.append($("<input>", {
	               type: "text",
	               name: "permissionTitle",
	               value:permissionTitle
	           }));
        	save.append($("<input>", {
	               type: "text",
	               name: "superAdmin",
	               value: superAdmin
	           }));
        	save.append($("<input>", {
	               type: "text",
	               name: "cancelAllPermission",
	               value:cancelAllPermission
	           }));
        	save.append($("<input>", {
	               type: "text",
	               name: "membershipManagement",
	               value: membershipManagement
	           }));
        	save.append($("<input>", {
	               type: "text",
	               name: "advertisingManagement",
	               value: advertisingManagement
	           }));
        	save.append($("<input>", {
	               type: "text",
	               name: "reportingManagement",
	               value: reportingManagement
	           }));
        	save.append($("<input>", {
	               type: "text",
	               name: "articleManagement",
	               value: articleManagement
	           }));
        	save.append($("<input>", {
	               type: "text",
	               name: "recipeManagement",
	               value: recipeManagement
	           }));
        	save.append($("<input>", {
	               type: "text",
	               name: "action",
	               value: "update"
	           }));
        	save.appendTo("body").hide();
        	save.submit();
        	save.remove();
        	 
        	 
         })
        
       updateTable();
        })


    </script>
</body>

</html>