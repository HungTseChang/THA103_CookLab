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
                input.red-placeholder::placeholder, textarea.red-placeholder::placeholder {
    color: red; 
                   }
            </style>
            <!-- ////////////////////////////////////////////////////////////////////////////////////////// -->
            <div class="page-heading">
                <div class="page-title">
                    <div class="row">
                        <div class="col-12 col-md-6 order-md-1 order-last">
                            <h3>權限管理</h3>
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
                    <div class="row" style="background-color: white">
								<div class="col-md-2"
									style="text-align: right; display: flex; flex-direction: column; justify-content: center;">
									<select class="wcc" id="selectsearch"
										style="background-color: white; padding-left: 20px; : border-color: white;">
										<option value="hashtagNO">標籤編號</option>
										<option value="hashtagName">標籤名稱</option>
										<option value="categoryTags">標籤種類</option>
										<option value="searchCount">搜尋次數</option>
										<option value="useCount">使用次數</option>
										<option value="officialTags">官方標籤</option>
										<option value="createdTimestamp">時間</option>
										<option value="wcc" selected>上述所有欄位</option>
									</select>
								</div>
								<div class="col-md-6">
									<input type="text" id="searchbar" class="form-control"
										placeholder="請輸入 編號、名稱、次數或時間"
										style="pading-color: rgb(208, 250, 255);">
								</div>
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
                        <lable id="error"> </lable>
                        
                            <table class="table-container" id="table1"
                                style="width: 100%;white-space: nowrap; overflow: auto; border-collapse: collapse; border: 1px solid #000;">
                                <thead style="position: sticky;top: 0; background-color: white;">
                                    <tr class="title">
                                        <th class="number" name="hashtagNO">標籤編號</th>
                                        <th class="permit" name="hashtagName">標籤名稱</th>
                                        <th class="permit" name="categoryTags">標籤種類</th>
                                        <th class="number" name="searchCount">搜尋次數</th>
                                        <th class="number" name="useCount">使用次數</th>
                                        <th class="permit" name="officialTags">官方標籤</th>
                                         <th class="wcc createdTimestamp ">創建時間</th>
                                    </tr>
                                </thead>
                                <tbody id="tbody" style="white-space: nowrap; overflow: auto;">
                                  


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
                                <a class="rounded-pill btn btn-primary wcc" id="enter0" style="position: relative;">新增標籤</a>
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
    <script src="<%=request.getContextPath() %>/dashboard/assets\vendors\jquery-3.7.1.min.js"></script>
    <script src="<%=request.getContextPath() %>/dashboard/assets/vendors/perfect-scrollbar/perfect-scrollbar.min.js"></script>
    <script src="<%=request.getContextPath() %>/dashboard/assets/js/bootstrap.bundle.min.js"></script>
    <script src="<%=request.getContextPath() %>/dashboard/assets/vendors/simple-datatables/simple-datatables.js"></script>

    <script src="<%=request.getContextPath() %>/dashboard/assets/js/main.js"></script>
    <script src="<%=request.getContextPath() %>/dashboard/assets\js\menu_ative.js"></script>
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
        	            action: "<%=request.getContextPath() %>/DisboardHastagServlet", // 表单提交的URL
        	            method: "post", // 提交方法，可以是 "post" 或 "get"，根据需求设置
        	        });
        		    
        		       form.append($("<input>", {
        	               type: "text",
        	               name: "action",
        	               value: "getHastag"
        	           }));
        		       form.appendTo("body").hide();
        		       form.submit();
        		       form.remove();
        				console.log("reload");
        		}
       
// ====================搜尋欄==============================================    
	
	   $("#searchbar").on("keyup", function() {
    var value1 = $(this).val().toLowerCase();
    var detail = "td"+"."+$("#selectsearch").val();
    $("#tbody tr").filter(function() {
      $(this).toggle($(this).find(detail).text().toLowerCase().indexOf(value1) > -1);
    });
  });	
	
// ========================放入資料========================================	
var textall = "";
var tbodyall;
var number =myList.length;	
const status ={
		  0:" <span class='badge bg-success' value='0'>官方</span>",
		  1:" <span class='badge bg-secondary' value='1'>非官方</span>",
 }		
   
        
		var onload = function() {
			let tableBodya = $("table#table1").children("tbody");
	    	tableBodya.empty();
			for (let i = 0; i < number; i++) {
				let aa = myList[i];

	    	    let text = "";
	    	  text += "<tr>";
	   		  text += "<td class='wcc hashtagNO'  name='hashtagNO' value="+aa.hashtagNO+">"+aa.hashtagNO+"</td>";
	   		  text +=" <td class='wcc hashtagName'  name='hashtagName'>"+aa.hashtagName+"</td>";
	   		  text +=" <td class='wcc categoryTags'  name='categoryTags'>"+aa.categoryTags+"</td>";
	   		  text +=" <td class='wcc searchCount'  name='searchCount'>"+aa.searchCount+"</td>";
	   		  text +=" <td class='wcc useCount'  name='useCount'>"+aa.useCount+"</td>";
    		  text +=" <td  class='wcc ' name='officialTags'>"+status[aa.officialTags]+"</td>"; 
	   		  text +=" <td class='wcc  ' createdTimestamp'  name='createdTimestamp'>"+aa.createdTimestamp+"</td>"; 
	   		  text +=`
	   			<td>
	                  <a  class="modify wcc" style="margin-bottom: 0px;">修改</a>
	   			</td>
	    		<td>
   			  <FORM METHOD="post" ACTION="<%=request.getContextPath() %>/DisboardHastagServlet" style="margin-bottom: 0px;">
	  			     <input type="submit" value="刪除">
	    		     <input type="hidden" name="hashtagNO"  value=`;
	    		  text +=aa.hashtagNO;
	    		  text +=`>
	    			     <input type="hidden" name="action" value="delete"></FORM>
	    			</td>
	    			  `;
	    		  text += "</tr>";
			
				textall += text;

			}
			tableBodya.append(textall);
			tbodyall = $('#tbody tr').toArray();
		}
        
        
//    ====================================================== 	       
    	       
        
       let  updateTable  = function(){
    	    	var startIndex = (currentPage - 1) * rowsPerPage;
    	    	var endIndex = startIndex + rowsPerPage;
    	    	var tableBody = $("table#table1").children("tbody");
    	    	tableBody.empty();

    		for(let i = startIndex ; i<endIndex ;i++){
    	    	        	  
    	  if (i <number){
    		  tableBody.append(tbodyall[i]);
    	     	   }
    		}   	
    		$("#current-page").text(currentPage);
    		var totalPages = Math.ceil(number/ rowsPerPage);
    		$("#total-pages").text("of " + totalPages);
       }
       

//         =============================================
       let saveupdate = function(e){
        let	hashtagNO= $(e.target).closest("tr").find("td[name='hashtagNO']").text();
        let	 hashtagName= $(e.target).closest("tr").find("td[name='hashtagName']").find("input").val();
        let	categoryTags= $(e.target).closest("tr").find("td[name='categoryTags']").find("select").val();
        let	 searchCount= $(e.target).closest("tr").find("td[name='searchCount']").text();
        let	 useCount= $(e.target).closest("tr").find("td[name='useCount']").text();
        let	 officialTags= $(e.target).closest("tr").find("td[name='officialTags']").find("select").val();

        	var save = $("<form>", {
	            action: "<%=request.getContextPath() %>/DisboardHastagServlet", // 表单提交的URL
	            method: "post", // 提交方法，可以是 "post" 或 "get"，根据需求设置
	        });
        	save.append($("<input>", {
	               type: "text",
	               name: "hashtagNO",
	               value: hashtagNO
	           }));
        	save.append($("<input>", {
	               type: "text",
	               name: "hashtagName",
	               value:hashtagName
	           }));
        	save.append($("<input>", {
	               type: "text",
	               name: "categoryTags",
	               value: categoryTags
	           }));
        	save.append($("<input>", {
	               type: "text",
	               name: "searchCount",
	               value:searchCount
	           }));
        	save.append($("<input>", {
	               type: "text",
	               name: "useCount",
	               value: useCount
	           }));
        	save.append($("<input>", {
	               type: "text",
	               name: "officialTags",
	               value: officialTags
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
       var hashtagName_old;
       var categoryTags_old;
       var searchCount_old;
       var useCount_old;
       var officialTags_old;
       
       
       const categoryTagsList= `
           <select class="wcc" id="select2">
           <option value="烹飪方式">烹飪方式</option>
           <option value="餐點類型">餐點類型</option>
           <option value="特殊飲食需求">特殊飲食需求</option>
           <option value="節日">節日</option>
           </select>
       `;
       const officialTagsList= `
           <select class="wcc" id="select3">
           <option value="0">官方</option>
           <option value="1">非官方</option>
           </select>
       `;
      const hashtagNamestring = ' <input type="text" id="hashtagNamestring" class="wcc" placeholder="標籤名稱"> ';
//        ===========================================
	
       $(document).on("click", "a.modify", function(e){
    	   if ( $("tr.hightlight").length == 0){
    	   light(e);
    	   $(e.target).text("儲存修改");
           $(e.target).closest("td").append( '<a  class="cancelmodify wcc" style="margin-bottom: 0px;" >取消修改</a> ');
           hashtagName_old= $(e.target).closest("tr").find("td[name='hashtagName']").text();
           categoryTags_old= $(e.target).closest("tr").find("td[name='categoryTags']").text();
           officialTags_old= $(e.target).closest("tr").find("td[name='officialTags']").find("span").attr("value");     
           
           

        	   

      $(e.target).closest("tr").find("td[name='hashtagName']").html(hashtagNamestring);
      $(e.target).closest("tr").find("td[name='hashtagName']").find("#hashtagNamestring").val(hashtagName_old);
      $(e.target).closest("tr").find("td[name='categoryTags']").html(categoryTagsList);
     $(e.target).closest("tr").find("td[name='officialTags']").html(officialTagsList);



    	   }else if(
    			   $(e.target).closest("tr").hasClass("hightlight")
    	   ){
    		   $("tr.hightlight").removeClass("hightlight");
    		   $(e.target).text("修改");
    		   $(e.target).closest("td").find('a.cancelmodify').remove();
    		   saveupdate(e);
    	   }else{
    		   alter("已有其他欄位正在修改");
    	   }
    	   
       });
//  ===================取消修改========================================      
        $(document).on("click", "a.cancelmodify", function(e){
           $(e.target).closest("tr").find("td[name='hashtagName']").text(hashtagName_old);
       	    $(e.target).closest("tr").find("td[name='categoryTags']").text(categoryTags_old);
       	    $(e.target).closest("tr").find("td[name='officialTags']").html(status[officialTags_old]);
		   $("tr.hightlight").removeClass("hightlight");
		   $(e.target).closest("tr").find('a.modify').text("修改");
		   $(e.target).closest("td").find('a.cancelmodify').remove();
        });
       
       
       
       
       
//        ==========改變頁數==========
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
        
//         ===========新增============================================================
     //   =====================
        let addnew = function(){
        	 if (! $("tr.hightlight").length == 0){return;}
            var currentTime = new Date();
            var formattedTime = currentTime.getFullYear() + "-"
                + (currentTime.getMonth() + 1) + "-"
                + currentTime.getDate() + " "
                + currentTime.getHours() + ":"
                + currentTime.getMinutes() + ":"
                + currentTime.getSeconds();
        	
        		
        		
        	let newone = ""
        		newone += "<tr class='hightlight'>";
        		newone += "<td name='hashtagNO'>"+"新的權限"+"</td>";
        		newone +=" <td name='hashtagName'>"+hashtagNamestring+"</td>";
        		newone +=" <td name='categoryTags'>"+categoryTagsList+"</td>";
        		newone +=" <td  name='searchCount'>"+0+"</td>";
        		newone +=" <td name='useCount'>"+0+"</td>"; 
        		newone +=" <td name='officialTags'>"+officialTagsList+"</td>"; 
        		newone +=" <td name='createdTimestamp'>"+formattedTime+"</td>"; 
        		newone +=`
      				<td>
						<a  class="insert wcc" style="margin-bottom: 0px;">新增</a>
		    			</td>
      			<td>
                <a  class="cancel wcc" style="margin-bottom: 0px;">取消新增</a>
			</td>
      			`
        		newone +="</tr>";
        		 $("table#table1").children("tbody").append(newone);
         		$("div.table-datatable").scrollTop($("div.table-datatable")[0].scrollHeight);
         		$("div.table-datatable").scrollLeft(0);
        }
        
        $("a#enter0").on("click",function(){
        	addnew();
        })
        
        
        
         $(document).on("click", "a.insert", function(e){
        	let hashtagName_new= $(e.target).closest("tr").find("td[name='hashtagName']").find("input").val();
        	if(hashtagName_new==null || hashtagName_new.trim()==""){
        		$(e.target).closest("tr").find("td[name='hashtagName']").find("input").attr("placeholder","title不可為空");
        		$(e.target).closest("tr").find("td[name='hashtagName']").find("input").addClass("red-placeholder");
        		$("div.table-datatable").scrollLeft(0);
        		$("div.table-datatable").scrollTop($("div.table-datatable")[0].scrollHeight);

           	 console.log("結果為空值");
        		return;
        	}
          	 console.log("結果非空值");

        	let categoryTags_new= $(e.target).closest("tr").find("td[name='categoryTags']").find("select").val();
        	let officialTags_new= $(e.target).closest("tr").find("td[name='officialTags']").find("select").val();
        	
        	var save = $("<form>", {
	            action: "<%=request.getContextPath() %>/DisboardHastagServlet", // 表单提交的URL
	            method: "post", // 提交方法，可以是 "post" 或 "get"，根据需求设置
	        });
        	save.append($("<input>", {
	               type: "text",
	               name: "hashtagName",
	               value:hashtagName_new
	           }));
        	save.append($("<input>", {
	               type: "text",
	               name: "categoryTags",
	               value: categoryTags_new
	           }));
        	save.append($("<input>", {
	               type: "text",
	               name: "officialTags",
	               value: officialTags_new
	           }));
       
        	save.append($("<input>", {
	               type: "text",
	               name: "action",
	               value: "insert"
	           }));
        	save.appendTo("body").hide();
        	save.submit();
        	save.remove();
        	 
        	 
         })
         
                  $(document).on("click", "a.cancel", function(e){
                	  $(e.target).closest("tr").remove()
                  })
         
//  ======================大小排序======================================        
		var toggele = true;
	 $(document).on("click","tr th.number",function(e){
			var column ="td."+$(e.target).attr("name");
			var textArray = [];
			$(column).each(function() {
			    textArray.push($(this).text());
			});

			var sortedArray = textArray.slice().sort();
			var isSorted = JSON.stringify(sortedArray) === JSON.stringify(textArray);
		if(isSorted && toggele){
			tbodyall.sort(function(a,b){
				var dateA = $(a).find(column).text(); 
		        var dateB = $(b).find(column).text();
		        toggele = false;

		        return dateB - dateA;
			})
			
		}else{
			tbodyall.sort(function(a,b){
				var dateA = $(a).find(column).text(); 
		        var dateB = $(b).find(column).text();
		        toggele = true;

		        return dateA - dateB;
			})
		}
		    updateTable();
			 
			})    
//          ===============權限部分大小順序牌=================================
	
	
	
// 	======================================
        onload();
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



    
})})
</script>
</body>

</html>