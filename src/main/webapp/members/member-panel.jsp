<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.cooklab.members.model.*"%>

<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
//获取名为 "username" 的属性值
MembersVO memVO = (MembersVO) session.getAttribute("membersVO"); 

%>

<html lang="zxx">
  <head>
    <meta charset="UTF-8" />
    <meta name="description" content="cooking recipe" />
    <meta name="keywords" content="cooking, recipe, html" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta http-equiv="X-UA-Compatible" content="ie=edge" />
    <title>廚藝實驗室</title>

    <!-- Google Font -->
    <link href="https://fonts.googleapis.com/css2?family=Cairo:wght@200;300;400;600;900&display=swap" rel="stylesheet" />

    <!-- Css Styles -->
    <link rel="stylesheet" href="css/bootstrap.min.css" type="text/css" />
    <link rel="stylesheet" href="css/font-awesome.min.css" type="text/css" />
    <link rel="stylesheet" href="css/elegant-icons.css" type="text/css" />
    <link rel="stylesheet" href="css/nice-select.css" type="text/css" />
    <link rel="stylesheet" href="css/jquery-ui.min.css" type="text/css" />
    <link rel="stylesheet" href="css/owl.carousel.min.css" type="text/css" />
    <link rel="stylesheet" href="css/slicknav.min.css" type="text/css" />
    <link rel="stylesheet" href="css/style.css" type="text/css" />
    <!-- bootstrap icon -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.0/font/bootstrap-icons.css" />
    <link rel="stylesheet" href="css/ding.css" type="text/css" />
  </head>

  <body>
    <!-- Page Preloder -->
    <div id="preloder">
      <div class="loader"></div>
    </div>

    <!-- Humberger Begin -->
    <div class="humberger__menu__overlay"></div>
    <div class="humberger__menu__wrapper">
      <div class="humberger__menu__logo">
        <a href="#"><img src="img/indexlogo.png" alt="" /></a>
      </div>
      <section class="container">
        <div class="humberger__menu__widget row d-flex justify-content-between align-items-center">
          <div class="">
            <a href="#">
              <i class="bi bi-cart3 fa-3x"></i>
            </a>
          </div>

          <div class="">
            <a href="#">
              <i class="fa fa-user fa-3x"></i>
            </a>
          </div>

          <div class="">
            <a href="#">
              <i class="bi bi-bell fa-3x"></i>
            </a>
          </div>
        </div>
      </section>
      <nav class="humberger__menu__nav mobile-menu">
        <ul>
          <li class="active"><a href="./index.html">首頁</a></li>
          <li><a href="./shop-grid.html">商城</a></li>
          <li><a href="./blog.html">食譜總覽</a></li>
          <li><a href="./contact.html">常見問題</a></li>
        </ul>
      </nav>
      <div id="mobile-menu-wrap"></div>
      <div class="header__top__right__social">
        <a href="#"><i class="fa fa-2x fa-facebook"></i></a>
        <a href="#"><i class="fa fa-2x fa-instagram"></i></a>
        <a href="#"><i class="fa fa-2x fa-twitter"></i></a>
      </div>
      <div class="humberger__menu__contact">
        <ul>
          <li>
            <i class="fa fa-envelope"></i>
            tomato@cooklab.com
          </li>
        </ul>
      </div>
    </div>
    <!-- Humberger End -->

    <!-- Header Section Begin -->
    <header class="header">
      <div class="header__top">
        <div class="container">
          <div class="row">
            <div class="col-lg-6"></div>
            <div class="col-lg-6">
              <div class="header__top__right">
                <div class="header__top__right__auth">
                  <a href="#">
                    <i class="bi bi-cart3 m-0 ml-2 fa-lg"></i>
                    <span class="ding-nav-text">購物車</span>
                  </a>
                </div>
                <div class="header__top__right__auth">
                  <a href="#">
                    <i class="fa fa-user m-0 ml-2 fa-lg"></i>
                    <span class="ding-nav-text">會員中心</span>
                  </a>
                </div>
                <div class="header__top__right__auth">
                  <a href="#" class="m-0 ml-2 ding-nav-text">登入/註冊</a>
                </div>
                <div class="header__top__right__auth">
                  <a href="#">
                    <i class="bi bi-bell m-0 ml-2 fa-lg"></i>
                    <span class="ding-nav-text">通知中心</span>
                  </a>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
      <div class="container">
        <div class="row">
          <div class="col-lg-3">
            <div class="header__logo">
              <a href="./index.html"><img style="height: 150px" src="img/indexlogo.png" alt="" /></a>
            </div>
          </div>
          <div class="col-lg-9 d-flex align-items-center">
            <nav class="header__menu">
              <ul>
                <li class="active"><a href="./index.html">食譜總覽</a></li>
                <li><a href="#">新增食譜</a></li>
                <li><a href="#">關注食譜</a></li>
                <li><a href="./shop-grid.html">商城</a></li>
                <li><a href="#">討論區</a></li>
                <li><a href="#">客服中心</a></li>
                <li><a href="./contact.html">關於我們</a></li>
              </ul>
            </nav>
          </div>
        </div>
        <div class="humberger__open">
          <i class="fa fa-bars"></i>
        </div>
      </div>
    </header>
    <!-- Header Section End -->

    <!-- 會員中心區塊開始 -->
    <section>
      <div class="container">
        <div class="row ding-panel">
          <aside class="col-3">
            <div>
              <ul class="list-group text-center">
                <li class="list-group-item list-group-item-action active mem-panel-asidebar" aria-current="true">帳戶</li>
                <li class="list-group-item list-group-item-action">訂單</li>
                <li class="list-group-item list-group-item-action">關注</li>
                <li class="list-group-item list-group-item-action">食譜</li>
                <li class="list-group-item list-group-item-action">討論區文章</li>
                <li class="list-group-item list-group-item-action">通知中心</li>
              </ul>
            </div>
          </aside>
          <div class="col-9 p-2 bg-light border">
            <div class="row">
              <div class="col-8">
                <!-- -----------會員資料表單(僅檢視)開始----------->
                <form class="pl-5">
                   <div class="form-group form-row">
                    <label for="memberstatus" class="col-sm-2 col-form-label ding-data-short">狀態：</label>
                    <div class="col-sm-10">
                      <input type="text" readonly   class="form-control-plaintext" id="memberStatus" name="member_status" value="<%=memVO.getMemberStatus()%>" />
                    </div>
                  </div>
                  <div class="form-group form-row">
                    <label for="account" class="col-sm-2 col-form-label ding-data-short">帳號：</label>
                    <div class="col-sm-10">
                      <input type="text" readonly class="form-control-plaintext" id="account" value="<%=memVO.getMemberAccount()%>" />
                    </div>
                  </div>
                  <div class="form-group form-row">
                    <label for="gender" class="col-sm-2 col-form-label ding-data-short">性別：</label>
                    <div class="col-sm-10">
                      <input type="text" class="form-control-plaintext" id="gender" value="<%= memVO.getMemberGender() == 0 ? "男" : "女" %>" />
                    </div>
                  </div>
                  <div class="form-group form-row">
                    <label for="nickname" class="col-sm-2 col-form-label ding-data-short">暱稱：</label>
                    <div class="col-sm-10">
                      <input type="text" class="form-control-plaintext" id="nickname" value="<%=memVO.getMemberNickname()%>" />
                    </div>
                  </div>
                  <div class="form-group form-row">
                    <label for="birthdate" class="col-sm-2 col-form-label ding-data-short">生日：</label>
                    <div class="col-sm-10">
                      <input type="text" class="form-control-plaintext" id="birthdate" value="<%=memVO.getMemberDate()%>" />
                    </div>
                  </div>
                  <div class="form-group form-row">
                    <label for="address" class="col-sm-2 col-form-label ding-data-short">地址：</label>
                    <div class="col-sm-10">
                      <input type="text" class="form-control-plaintext" id="address" value="<%=memVO.getMemberAddress()%>" />
                    </div>
                  </div>
                  <div class="form-group form-row">
                    <label for="location" class="col-sm-3 col-form-label ding-data-long">國別：</label>
                    <div class="col-sm-9">
                      <input type="text" class="form-control-plaintext" id="location" value="<%=memVO.getMemberCountry()%>" />
                    </div>
                  </div>
                  <div class="form-group form-row">
                    <label for="phonenumber" class="col-sm-3 col-form-label ding-data-long">聯絡電話：</label>
                    <div class="col-sm-9">
                      <input type="text" class="form-control-plaintext" id="phonenumber" value="<%=memVO.getMemberCellphone()%>" />
                    </div>
                  </div>
                  <div class="form-group row">
                    <label for="email" class="col-sm-3 col-form-label ding-data-long">電子信箱：</label>
                    <div class="col-sm-9">
                      <input type="text" class="form-control-plaintext" id="email" value="<%=memVO.getMemberMail()%>" />
                    </div>
                  </div>
                  <div class="form-group row">
					  <label for="introduction" class="col-sm-3 col-form-label ding-data-long">簡介：</label>
					  <div class="col-sm-9">
					    <textarea class="form-control" readonly  name="member_introduce" id="introduction" rows="5"><%= memVO.getMemberIntroduce()%></textarea>
					  </div>
					</div>
					                  
                  <div class="ding-mem-btn">
                    <button type="button" class="btn ding-btn-org" id="editMemberInfoBtn" onclick="redirectToEdit()">編輯資料</button>
                    <button type="button" class="btn ding-btn-org" id="revisePasswordBtn">修改密碼</button>
                  </div>
                </form>
                <!-- -----------會員資料表單(僅檢視)結束--------- -->
              </div>
              <!-- -------------會員頭像開始------------ -->
              <div class="col-3 ding-mem-img">
              	<img style="max-width: 200px; max-height: 200px;" 
			 alt="會員頭像" src="/CookLab/MembersImgServlet?memberId=<%=memVO.getMemberId()%>" class="rounded-circle mt-4">

              </div>
              <!-- -------------會員頭像結束------------ -->
            </div>
          </div>
        </div>
      </div>
    </section>
    <!-- 會員中心區塊結束 -->

    <!-- Footer Section Begin -->
    <footer class="footer spad">
      <div class="container">
        <div class="row">
          <div class="col-lg-4">
            <div class="footer__about">
              <ul>
                <li>公司地址：台北市中山區南京東路三段219號5樓</li>
                <li>電話：(02)27120589</li>
                <li>電子信箱：tomato@cooklab.com</li>
              </ul>
            </div>
          </div>
          <div class="col-lg-4">
            <div class="footer__widget">
              <ul>
                <li><a href="#">關於我們</a></li>
                <li><a href="#">關於商店</a></li>
                <li><a href="#">安心購物</a></li>
              </ul>
              <ul>
                <li><a href="#">運送資訊</a></li>
                <li><a href="#">隱私政策</a></li>
                <li><a href="#">常見問題</a></li>
              </ul>
            </div>
          </div>
          <div class="col-lg-4 col-md-12">
            <div class="footer__widget">
              <h6>立即訂閱電子報</h6>
              <p>隨時接收我們的最新消息以及優惠訊息</p>
              <form action="#">
                <input type="text" placeholder="輸入您的電子信箱" />
                <button type="submit" class="btn ding-btn-org">訂閱</button>
              </form>
              <div class="footer__widget__social">
                <a href="#"><i class="fa fa-facebook"></i></a>
                <a href="#"><i class="fa fa-instagram"></i></a>
                <a href="#"><i class="fa fa-twitter"></i></a>
              </div>
            </div>
          </div>
        </div>
        <div class="row">
          <div class="col-lg-12">
            <div class="footer__copyright">
              <div class="footer__copyright__text">
                <p>
                  <!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
                  Copyright &copy;
                  <script>
                    document.write(new Date().getFullYear());
                  </script>
                  All rights reserved | This template is made with
                  <i class="fa fa-heart" aria-hidden="true"></i>
                  by
                  <a href="https://colorlib.com" target="_blank">Colorlib</a>
                  <!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
                </p>
              </div>
            </div>
          </div>
        </div>
      </div>
    </footer>
    <!-- Footer Section End -->

    <!-- Js Plugins -->
    <script src="js/jquery-3.3.1.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/jquery.nice-select.min.js"></script>
    <script src="js/jquery-ui.min.js"></script>
    <script src="js/jquery.slicknav.js"></script>
    <script src="js/mixitup.min.js"></script>
    <script src="js/owl.carousel.min.js"></script>
    <script src="js/main.js"></script>
    <script>
    	function redirectToEdit(){
    		window.location.href = "member-panel-editting.jsp";
    	}
	    //置換會員狀態的顯示
	    
	    var statusValue = document.getElementById("memberStatus");

    	
	    if (statusValue.getAttribute('value') == "0") {
	    	statusValue.setAttribute('value','已啟用');
	    } else if (statusValue.getAttribute('value') == "1") {
	    	statusValue.setAttribute('value','未驗證');
	    } else if (statusValue.getAttribute('value') == "2") {
	    	statusValue.setAttribute('value','已註銷');
	    } else {
	    	statusValue.setAttribute('value','未知');;
	    }
    </script>
  </body>
</html>
