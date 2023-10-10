<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.cooklab.article.model.ArticleService"%>
<%@ page import="com.cooklab.article.model.ArticleVO"%>
<%
	ArticleService artSvc = new ArticleService();
	List<ArticleVO> list = artSvc.getAll();
    pageContext.setAttribute("list",list);
%>

<!DOCTYPE html>
<html lang="zxx">
  <head>
    <meta charset="UTF-8" />
    <meta name="description" content="Ogani Template" />
    <meta name="keywords" content="Ogani, unica, creative, html" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta http-equiv="X-UA-Compatible" content="ie=edge" />
    <title>Ogani | Template</title>

    <!-- Google Font -->
    <link
      href="https://fonts.googleapis.com/css2?family=Cairo:wght@200;300;400;600;900&display=swap"
      rel="stylesheet"
    />

    <!-- Css Styles -->
    <link rel="stylesheet" href="css/bootstrap.min.css" type="text/css" />
    <link rel="stylesheet" href="css/font-awesome.min.css" type="text/css" />
    <link rel="stylesheet" href="css/elegant-icons.css" type="text/css" />
    <link rel="stylesheet" href="css/nice-select.css" type="text/css" />
    <link rel="stylesheet" href="css/jquery-ui.min.css" type="text/css" />
    <link rel="stylesheet" href="css/owl.carousel.min.css" type="text/css" />
    <link rel="stylesheet" href="css/slicknav.min.css" type="text/css" />
    <link rel="stylesheet" href="css/style.css" type="text/css" />
    
    <link rel="stylesheet" href="css/ding.css" type="text/css" />
    <link rel="stylesheet" href="css/HO.css" type="text/css" />
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
        <div
          class="humberger__menu__widget row d-flex justify-content-between align-items-center"
        >
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
              <a href="./index.html"
                ><img style="height: 150px" src="img/indexlogo.png" alt=""
              /></a>
            </div>
          </div>
          <div class="col-lg-9 d-flex align-items-center">
            <nav class="header__menu">
              <ul>
                <li><a href="./index.html">食譜總覽</a></li>
                <li><a href="#">新增食譜</a></li>
                <li><a href="#">關注食譜</a></li>
                <li><a href="./shop-grid.html">商城</a></li>
                <li class="active"><a href="#">討論區</a></li>
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
    <!--上方表頭結束-->

    <div class="container" id="article_cat_btn">
      <div class="row">
        <div class="col-md-9" id="articel_cat">
          <button type="button" class="btn custom-btn btn HO-btn-org">
            全部主題
          </button>
          <button type="button" class="btn custom-btn">
            綜合討論
          </button>
          <button type="button" class="btn custom-btn">
            生活情報
          </button>
          <button type="button" class="btn custom-btn">
            食譜討論
          </button>
          <button type="button" class="btn custom-btn">
            食材討論
          </button>
        </div>
        <button
          type="button"
          class="btn btn-outline-primary btn-lg"
         
          id="article_sumbit"
        >
          發文
        </button>
      </div>
      <span></span>
    </div>

    <section id="article_conten">
      <div class="container">
        <div class="row">
          <div class="col-lg-9" style="height: 600px;">
            <div class="th" id="article_top_title">
              篩選:
              <a href="" style="margin-left: 480px; color: black">發文作者</a>
              <a href="" style="margin-left: 30px; color: black">最後更新時間</a>
              <a href="" style="margin-left: 30px; color: black">點擊次數</a>
            </div>

            <div >
              <table>
              <c:forEach var="artVO" items="${list}" >
                <tr class="title_colum">
                  <td id="title_colum_td">
                    <a id="cat_view">${artVO.articleCategory}</a>
                    <a id="title_view">${artVO.articleTitle}</a>
                  </td>
                  <td id="article_creator">${artVO.memberId}</td>
                  <td id="article_lasttime">${artVO.lastEditTimestamp}</td>

                  <td id="view_count">${artVO.viewCount}</td>
                </tr>
                </c:forEach>
              </table>
            </div>
          </div>
          <div class="col-lg-3"  >
            
            <div class="statusOutput" id="statusOutput">CookTalk</div>
            <textarea id="messagesArea" class="panel message-area" readonly></textarea>
            <div class="panel input-area">
              <input id="message" class="text-field"  type="text" placeholder="Message" onkeydown="if (event.keyCode == 13) sendMessage();" /> 
		
              <input type="submit" id="sendMessage" class="btn ding-btn-org" value="Send" onclick="sendMessage();" />  


            </div>
            
          </div>
        </div>
      </div>
    </section>

    <!-- 頁簽-->
    <div class="d-flex justify-content-center">
      <nav aria-label="Page navigation example" style="margin-top: 5px">
        <ul class="pagination">
          <li class="page-item"><a class="page-link_pr" href="#">Previous</a></li>
          <li class="page-item"><a class="page-link" href="#">1</a></li><!--Q2 active 沒有效果-->
          <li class="page-item"><a class="page-link" href="#">2</a></li>
          <li class="page-item"><a class="page-link" href="#">3</a></li> 
          <li class="page-item"><a class="page-link_ne" href="#">Next</a></li>
        </ul>
      </nav>
    </div>

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
    <script src="./js/jquery-3.3.1.min.js"></script>
    <script src="./js/bootstrap.min.js"></script>
    <script src="./js/jquery.nice-select.min.js"></script>
    <script src="./js/jquery-ui.min.js"></script>
    <script src="./js/jquery.slicknav.js"></script>
    <script src="./js/mixitup.min.js"></script>
    <script src="./js/owl.carousel.min.js"></script>
    <script src="./js/main.js"></script>
    <script src="./js/HO.js"></script>
  </body>
</html>
