
<%@ page language="java" contentType="text/html; charset=UTF-8"
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

    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Nunito:wght@300;400;600;700;800&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="<%=request.getContextPath() %>/dashboard/assets/css/bootstrap.css">

    <link rel="stylesheet" href="<%=request.getContextPath() %>/dashboard/assets/vendors/simple-datatables/style.css">

    <link rel="stylesheet" href="<%=request.getContextPath() %>/dashboard/assets/vendors/perfect-scrollbar/perfect-scrollbar.css">
    <link rel="stylesheet" href="<%=request.getContextPath() %>/dashboard/assets/vendors/bootstrap-icons/bootstrap-icons.css">
    <link rel="stylesheet" href="<%=request.getContextPath() %>/dashboard/assets/css/app.css">
    <link rel="shortcut icon" href="<%=request.getContextPath() %>/dashboard/assets/images/favicon.svg" type="image/x-icon">
</head>

<body>
    <div id="app">
        <div id="sidebar" class="active">
            <div class="sidebar-wrapper active">
                <div class="sidebar-header">
                    <div class="d-flex justify-content-between">
                        <div class="logo">
                            <a href="index.html"><img src="<%=request.getContextPath() %>/dashboard/assets/images/logo/logo.png" alt="Logo" srcset=""></a>
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
                            <ul class="submenu ">
                          
                                <li class="submenu-item ">
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
            <!--/////////////////////////////////////////////////////////////////////////////////////////  -->
            <style>
                a.wcc {
                    border: 1px solid rgb(151, 135, 249);
                    background-color: rgb(195, 241, 253);
                    padding: 4px;
                    border-radius: 20px;
                }

                td button.wcc {
                    border-radius: 20px;
                }
                input.WCC_memeber_info{
                    width: 60%;
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
                <!-- /////////////////////////////////////////////////////// -->

            </div>
            <div class="container">
                <div class="row" style="background-color: white;">
                    <div class="col-lg-12">
                        <section id="basic-horizontal-layouts">
                            <div class="row match-height" style=" position: relative;">
                                <div class="col-md-8 col-8">
                                    <div class="card">
                                        <div class="card-header">
                                            <h4 class="card-title">Horizontal Form</h4>
                                        </div>
                                        <div class="card-content">
                                            <div class="card-body">
                                                <form class="form form-horizontal">
                                                    <div class="form-body">
                                                        <div class="row">
                                                            <div class="col-md-4">
                                                                <label>會員編號</label>
                                                            </div>
                                                            <div class="col-md-8 form-group">
                                                                <input type="text" class="WCC_memeber_info"
                                                                    id="member_id" name="member_id" value="000001"
                                                                    disabled >
                                                            </div>
                                                            <div class="col-md-4">
                                                                <label>帳號</label>
                                                            </div>
                                                            <div class="col-md-8 form-group">
                                                                <input type="text" id="member_account"
                                                                    class="WCC_memeber_info" name="member_account"
                                                                    value="tomato_red" disabled>
                                                            </div>
                                                            <div class="col-md-4">
                                                                <label>密碼</label>
                                                            </div>
                                                            <div class="col-md-8 form-group">
                                                                <input type="password" id="member_password"
                                                                    class="WCC_memeber_info wcc" name="member_password"
                                                                    value="XXXXXXX" disabled>
                                                                <input type="text" id="member_password2"
                                                                    class="WCC_memeber_info wcc none" name="member_password"
                                                                    value="XXXXXXX" >
                                                                    <a class="wcc " id="changecode1">更改密碼</a>
                                                            </div>
                                                            <div class="col-md-4">
                                                                <label>暱稱</label>
                                                            </div>
                                                            <div class="col-md-8 form-group">
                                                                <input type="text" class="WCC_memeber_info"
                                                                    name="member_nickname" id="member_nickname"
                                                                    value="紅番茄" disabled>
                                                            </div>

                                                            <div class="col-md-4">
                                                                <label>性別</label>
                                                            </div>
                                                            <div class="col-md-8 form-group">
                                                                <input type="text" id="member_gender"
                                                                    class="WCC_memeber_info" name="member_gender"
                                                                    value="孢子體時期無性別" disabled>
                                                            </div>
                                                            <div class="col-md-4">
                                                                <label>手機號碼</label>
                                                            </div>
                                                            <div class="col-md-8 form-group">
                                                                <input type="text" id="member_cellphone"
                                                                    class="WCC_memeber_info" name="member_cellphone"
                                                                    value="0911123456" disabled>
                                                            </div>
                                                            <div class="col-md-4">
                                                                <label>電子信箱</label>
                                                            </div>
                                                            <div class="col-md-8 form-group">
                                                                <input type="text" id="member_mail"
                                                                    class="WCC_memeber_info" name="member_mail"
                                                                    value="tomato_red@gmail.com" disabled>
                                                            </div>
                                                            <div class="col-md-4">
                                                                <label>生日</label>
                                                            </div>
                                                            <div class="col-md-8 form-group">
                                                                <input type="text" id="member_date"
                                                                    class="WCC_memeber_info" name="member_date"
                                                                    value="2023/08/31" disabled>
                                                            </div>
                                                            <div class="col-md-4">
                                                                <label>地址</label>
                                                            </div>
                                                            <div class="col-md-8 form-group">
                                                                <input type="text" id="member_address"
                                                                    class="WCC_memeber_info" name="member_address"
                                                                    value="太陽市火星區沒有路94巷1號1樓" disabled>
                                                            </div>
                                                            <div class="col-md-4">
                                                                <label>國別</label>
                                                            </div>
                                                            <div class="col-md-8 form-group">
                                                                <input type="text" id="member_country"
                                                                    class="WCC_memeber_info" name="member_country"
                                                                    value="太陽系" disabled>
                                                            </div>
                                                            <div class="col-md-4">
                                                                <label>會員狀態</label>
                                                            </div>
                                                            <div class="col-md-8 form-group">
                                                                <select disabled id="member_status">
                                                                    <option value="0">1:正常</option>
                                                                    <option value="1">2:註銷</option>
                                                                </select>
                                                                    <a class="wcc">更改狀態</a>
                                                            </div>

                                                        </div>
                                                    </div>
                                                </form>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-md-4 " style="position: relative;">
                                    <div  class="card" style="top: 80px; right: 20px; max-width: 100%">
                                        <span>人物頭像</span>
                                        <img id="member_picture" src=""  alt="Member's Picture">                                        
                                    </div>
                                    <div style="position: absolute;  bottom: 20px;  left: 0; "><a class=wcc id="confirm" >儲存變動</a><a class=wcc href="<%=request.getContextPath() %>/dashboard/member/WCC_members.jsp" style="margin-left:60px" >回到會員列表</a></div>
                                </div>
                                <!-- ///////////////// -->
                                <div class="col-md-12 col-8"></div>
                            </div>
                        </section>
                    </div>

                </div>
            <!-- ======================================== -->
                <div class="wcc none" style="width: 100%; height:100%; position: fixed; 
                top: 0;
                left: 0;
                background-color: rgb(174, 172, 172, 0.5);  " id="allblock">
                   <div style="width: 30%; height: 30%; position: fixed; top: 50%; left: 45%; 
                   transform: translateX(-50%);
                   transform: translateY(-50%); 
                   background-color: white; opacity:1.0; display: block;">
                   <div style="opacity:1.0; margin-top: 40px; margin-left: 20px;
                   margin-right: 20px;
                    color: red; font-size: 16px; font-weight: bolder;">
                    警告:您正在修改他人密碼，此做法嚴重侵害他人權益，並可能需擔負法律責任。
                    您的行為將會被記錄。請確認是否要繼續進行此操作?
                    <div style="position: relative; top: 50px; left: 100px;"> 
                        <a class="wcc" style="margin-right:100px ;"id="changecode2">修改密碼</a> 
                        <a class="wcc" id="changecode3">取消操作</a>
                    </div>
                    </div>
                    
                   
                </div>
                 </div>
            <!-- ============================= -->
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
    <script src="<%=request.getContextPath() %>/dashboard/assets/vendors/jquery-3.7.1.min.js"></script>
    <script src="<%=request.getContextPath() %>/dashboard/assets/vendors/perfect-scrollbar/perfect-scrollbar.min.js"></script>
    <script src="<%=request.getContextPath() %>/dashboard/assets/js/bootstrap.bundle.min.js"></script>

    <script src="<%=request.getContextPath() %>/dashboard/assets/vendors/simple-datatables/simple-datatables.js"></script>
    <script src="<%=request.getContextPath() %>/dashboard/assets/vendors/sweetalert2/sweetalert2.all.min.js"></script>

    <script src="<%=request.getContextPath() %>/dashboard/assets/js/menu_ative.js"></script>
    <script>
    $(document).ready(function(){
   	 var member;
    	
   	member=JSON.parse('${json}');
   let	member_id =member.memberId
    $("#member_id").val(member_id);	
   let member_account = member.memberAccount;
    $("#member_account").val(member_account);	
   let member_password = member.memberPassword;
    $("#member_password").val(member_password);	
    
   let member_nickname = member.memberNickname;
    $("#member_nickname").val(member_nickname);	
    
    let witchGender ={0:"男",1:"女"}
   let member_gender = member.memberGender;
    $("#member_gender").val(witchGender[member_gender]);	
    
    
   let member_cellphone = member.memberCellphone;
    $("#member_cellphone").val(member_cellphone);	
    
   let member_mail = member.memberMail;
    $("#member_mail").val(member_mail);
    
   let member_date = member.memberDate;
    $("#member_date").val(member_date);	
    
   let member_address = member.memberAddress;
    $("#member_address").val(member_address);	
   let member_country = member.memberCountry;
    $("#member_country").val(member_country);
    
   let member_status = member.memberStatus;
    $("#member_status").val(member_status);
    let option = "option[value='"+member_status+"']";
    $("#member_status").find(option).prop("selected", true);
    
    let member_picture = "data:image/jpeg;base64,"+JSON.parse('${picture}');
    $("img#member_picture").attr("src", member_picture);
	
    	
   $("a#confirm").on("click",function(){
	   var id =    $("#member_id").val();
	   var password = $("input#member_password2").val();
	   var status = $("#member_status").val();
		var form = $("<form>", {
            action: "<%=request.getContextPath() %>/MemberdashboardServlet", // 表单提交的URL
            method: "post", // 提交方法，可以是 "post" 或 "get"，根据需求设置
        });
	       form.append($("<input>", {
               type: "text",
               name: "memberID",
               value: id
           }));
	       form.append($("<input>", {
               type: "text",
               name: "password",
               value: password
           }));
	       form.append($("<input>", {
               type: "text",
               name: "memberStatus",
               value: status
           }));	    
	       form.append($("<input>", {
               type: "text",
               name: "action",
               value: "update"
           }));
	       form.appendTo("body").hide();
	       form.submit();
	       form.remove();
	   
	   
   }) 	

    	
    })
    
    
    
        $(function(){
            $("a.wcc").on("click",function(e){
               if( $(e.target).closest("div").find("select").attr('disabled')){$(e.target).closest("div").find("select").removeAttr('disabled');}
               else{$(e.target).closest("div").find("select").attr('disabled','disabled');}
            })
            $("a#changecode1").on("click",function(e){
                if($(e.target).closest("div").find("#member_password2").hasClass("none")
                ){
                $("#allblock").removeClass("none");
                }else{
                  let a =  $("#member_password2").val();
                  $("#member_password").val(a);
                  console.log($("#member_password").val());
                  $("input#member_password2").addClass("none")
                  $("input#member_password").removeClass("none");
                  Swal.fire({
                    icon: "success",
                    title: "重設密碼成功!"
                })
                }

            })
            $("a#changecode2").on("click",function(){
                $("#member_password").addClass("none");
                $("input#member_password2").removeClass("none");
                $("#allblock").addClass("none");
            })
            $("a#changecode3").on("click",function(){
                $("#allblock").addClass("none");
            })

        })
    </script>
</body>

</html>