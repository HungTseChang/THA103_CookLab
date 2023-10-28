<%@ page language="java" contentType="text/html; charset=UTF8"
    pageEncoding="UTF8"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Dashboard - Mazer Admin Dashboard</title>

    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Nunito:wght@300;400;600;700;800&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/dashboard/assets/css/bootstrap.css">

    <link rel="stylesheet" href="<%=request.getContextPath()%>/dashboard/assets/vendors/iconly/bold.css">

    <link rel="stylesheet" href="<%=request.getContextPath()%>/dashboard/assets/vendors/perfect-scrollbar/perfect-scrollbar.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/dashboard/assets/vendors/bootstrap-icons/bootstrap-icons.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/dashboard/assets/css/app.css">
    <link rel="shortcut icon" href="<%=request.getContextPath()%>/dashboard/assets/images/favicon.svg" type="image/x-icon">
    
    
    <style>
    
        div#app,div#main{
    display: flex; /* 使用 Flexbox 布局 */
    flex-direction: column;
    justify-content: center; /* 在水平方向上居中 */
    align-items: center; /* 在垂直方向上居中 */
    height: 200px; /* 设置容器的高度，可以根据需要调整 */
        }
        div.none{
        
            display:none;
        
        }
        div.col-md-6 col-12{
                width: 562px;
        height: 400px;
        
        }
           div.AA{
          width: 500px;
        height: 400px;
        
        }
    </style>
</head>

<body>
    <div id="app">
        
        <div id="main" style=" margin: 50px;">
<div id="1" style="hight: 100px;">
            <header class="mb-3">
                <a href="#" class="burger-btn d-block d-xl-none">
                    <i class="bi bi-justify fs-3"></i>
                </a>
            </header>
</div>
        <div id="2"  style="position: relative; top: 200px;">
            <section >
                <div class="row match-height " style="  display: flex;
                    justify-content: center;">
                    <div class="col-md-12 col-12" style="align-self: center;"><div style="width:500px">
                        <div class="card AA">
                            <div class="card-header">
                                <h4 class="card-title">登入</h4>
                            </div>
                            <div class="card-content">
                                <div class="card-body">
                                    <form class="form form-horizontal">
                                        <div class="form-body">
                                            <div class="row">
                                                <div class="col-md-4">
                                                    <label>帳號</label>
                                                </div>
                                                <div class="col-md-8">
                                                    <div class="form-group has-icon-left">
                                                        <div class="position-relative">
                                                            <input type="text" class="form-control" placeholder="帳號"
                                                                id="account">
                                                            <div class="form-control-icon">
                                                                <i class="bi bi-person"></i>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>


                                                <div class="col-md-4">
                                                    <label>Password</label>
                                                </div>
                                                <div class="col-md-8">
                                                    <div class="form-group has-icon-left">
                                                        <div class="position-relative">
                                                            <input type="password" class="form-control"
                                                                placeholder="Password" id="password">
                                                            <div class="form-control-icon">
                                                                <i class="bi bi-lock"></i>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="form-group col-md-8 offset-md-4">
                                                    <div class='form-check'>
                                                        <div class="checkbox">
                                                            <input type="checkbox" id="checkbox2"
                                                                class='form-check-input' checked>
                                                            <label for="checkbox2">Remember Me</label><label style="color:red;">	&nbsp;	&nbsp;${error}</label>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="col-12 d-flex justify-content-end">
                                                    <a type="submit" class="switch"
                                                        style="padding-top: 10px; padding-right: 5%;">忘記密碼?</a>
                                                    <a type="submit"
                                                        class="btn btn-primary me-1 mb-1" id="Submit">送出</a>
                                                    <button type="reset"
                                                        class="btn btn-light-secondary me-1 mb-1">清除欄位</button>
                                                </div>
                                            </div>
                                        </div>
                                    </form>
                                </div>
                            </div>
                       </div>
                                     <div class="card AA none" style="width:537.667">
                            <div class="card-header">
                                <h4 class="card-title">忘記密碼</h4>
                            </div>
                            <div class="card-content">
                                <div class="card-body">
                                    <form class="form form-horizontal">
                                        <div class="form-body">
                                            <div class="row">
                                                <div class="col-md-4">
                                                    <label>帳號</label>
                                                </div>
                                                <div class="col-md-8">
                                                    <div class="form-group has-icon-left">
                                                        <div class="position-relative">
                                                            <input type="text" class="form-control" placeholder="目前版本帳號即信箱"
                                                                id="account2">
                                                            <div class="form-control-icon">
                                                                <i class="bi bi-person"></i>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>


                                                <div class="col-md-4">
                                                    <label>信箱</label>
                                                </div>
                                                <div class="col-md-8">
                                                    <div class="form-group has-icon-left">
                                                        <div class="position-relative">
                                                            <input type="text" class="form-control"
                                                                placeholder="請輸入您註冊信箱" id="email">
                                                            <div class="form-control-icon">
                                                                <i class="bi bi-lock"></i>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="form-group col-md-8 offset-md-4">
                                                     <div class='form-check'>
                                                        <div class="checkbox">
 
                                                            <label >&nbsp&nbsp</label>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="col-12 d-flex justify-content-end">
                                                    <a class="switch" type="submit"
                                                        style="padding-top: 10px; padding-right: 5%;">返回登入頁面</a>
                                                    <a type="submit"
                                                        class="btn btn-primary me-1 mb-1" id="Submit2">送出</a>
                                                    <button type="reset"
                                                        class="btn btn-light-secondary me-1 mb-1">清除欄位</button>
                                                </div>
                                            </div>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
               </div></div>
            </section>
</div>



    </div>
    </div>

    		<script src="<%=request.getContextPath()%>/dashboard/assets/vendors/perfect-scrollbar/perfect-scrollbar.min.js"></script>
	<script src="<%=request.getContextPath()%>/dashboard/assets/js/bootstrap.bundle.min.js"></script>
	<script src="<%=request.getContextPath()%>/dashboard/assets/vendors/sweetalert2/sweetalert2.all.min.js"></script>
	<script src="<%=request.getContextPath()%>/dashboard/assets/vendors/simple-datatables/simple-datatables.js"></script>
	<script src="<%=request.getContextPath()%>/dashboard/assets/js/main.js"></script>
	<script src="<%=request.getContextPath()%>/dashboard/assets\js\menu_ative.js"></script>
		<script	src="<%=request.getContextPath()%>/dashboard/assets\vendors\jquery-3.7.1.min.js"></script>
    <script>

    $(document).ready(function(){
    	$("a#Submit").on("click",function(e){
    		e.preventDefault;
    		var account = $("input#account").val();
    		var password = $("input#password").val();

    		var form = $("<form>", {
                action: "<%=request.getContextPath() %>/LoginServlet", 
                method: "post", 
            });
    	       form.append($("<input>", {
                   type: "text",
                   name: "account",
                   value: account
               }));
    	       form.append($("<input>", {
                   type: "text",
                   name: "password",
                   value: password
               }));
    	    
    	       form.append($("<input>", {
                   type: "text",
                   name: "action",
                   value: "login"
               }));
    	       form.appendTo("body").hide();
    	       form.submit();
    	       console.log(form);
    	       form.remove();
    		
    	})
    	
    	$("a.switch").on("click",function(e){
    		e.preventDefault;
    		$("div.AA").toggleClass('none');
 		
    	})
    	

    	     	$("#Submit2").on("click",function(e){
            	if(!confirm("確定要重設密碼?")){ return;}
        		var account = $("input#account2").val();
        		var email = $("input#email").val();
            	  $.ajax({
                      type: "POST",
                      url:  "<%=request.getContextPath()%>/LoginServlet",
                      data: {
                          action:"forgetpassword",
                          account:account,
                          email:email
                      },
                      success: function(response) {
                    	  console.log(response);
                    	  if(response==="success"){
                          Swal.fire({
                              icon: "success",
                              title: account +"已成功寄出隨機密碼的信件到指定信箱!"
                              
                          })}else{
                              Swal.fire({
                                  icon: "danger",
                                  title: account+response
                              })
                          }
                    	  
                    	  
                      }
                  });
            	          	

            })
    	
    	
    	
        
    });    
    
    
    </script>
</body>

</html>