<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> <%@ page import="java.util.*"%> <%@ page
import="com.cooklab.purchase_order.model.*"%>
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
        <link href="https://fonts.googleapis.com/css2?family=Cairo:wght@200;300;400;600;900&display=swap" rel="stylesheet" />

        <!-- Css Styles -->
        <link rel="stylesheet" href="../css/bootstrap.min.css" type="text/css" />
        <link rel="stylesheet" href="../css/font-awesome.min.css" type="text/css" />
        <link rel="stylesheet" href="../css/elegant-icons.css" type="text/css" />
        <link rel="stylesheet" href="../css/nice-select.css" type="text/css" />
        <link rel="stylesheet" href="../css/jquery-ui.min.css" type="text/css" />
        <link rel="stylesheet" href="../css/owl.carousel.min.css" type="text/css" />
        <link rel="stylesheet" href="../css/slicknav.min.css" type="text/css" />
        <link rel="stylesheet" href="../css/style.css" type="text/css" />
        <!-- bootstrap-icon -->
        <link rel="stylesheet" href="../bootstrap-icons-1.10.5/font/bootstrap-icons.css" />
        <!-- 自增CSS -->
        <link rel="stylesheet" href="css/styleHungTse.css" type="text/css" />
        <!-- header&footer-CSS -->
        <link rel="stylesheet" href="../css/ding.css" type="text/css" />
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
                <a href="#"><img src="../img/indexlogo.png" alt="" /></a>
            </div>
            <section class="container">
                <div class="humberger__menu__widget row d-flex justify-content-between align-items-center">
                    <div class="">
                        <a href="#"> <i class="bi bi-cart3 fa-3x"></i> </a>
                    </div>

                    <div class="">
                        <a href="#"> <i class="fa fa-user fa-3x"></i> </a>
                    </div>

                    <div class="">
                        <a href="#"> <i class="bi bi-bell fa-3x"></i> </a>
                    </div>
                </div>
            </section>
            <nav class="humberger__menu__nav mobile-menu">
                <ul>
                    <li class="active"><a href="./index.html">首頁</a></li>
                    <li><a href="./shop-grid.html">商城todo</a></li>
                    <li><a href="../recipe/recipe_overview.jsp">食譜總覽todo</a></li>
                    <li><a href="./contact.html">常見問題todo</a></li>
                    <li><a href="../article/article_main.jsp">討論區todo</a></li>
                    <li><a href="../news/news.html">最新消息todo</a></li>
                </ul>
            </nav>
            <div id="mobile-menu-wrap"></div>
            <div class="header__top__right__social">
                <a href="#"><i class="fa fa-2x fa-facebook"></i></a> <a href="#"><i class="fa fa-2x fa-instagram"></i></a> <a href="#"><i class="fa fa-2x fa-twitter"></i></a>
            </div>
            <div class="humberger__menu__contact">
                <ul>
                    <li><i class="fa fa-envelope"></i> tomato@cooklab.com</li>
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
                                    <a href="../shopstage/"> <i class="bi bi-cart3 m-0 ml-2 fa-lg"></i> <span class="ding-nav-text">購物車</span> </a>
                                </div>
                                <div class="header__top__right__auth">
                                    <a href="../members/member-panel.jsp"> <i class="fa fa-user m-0 ml-2 fa-lg"></i> <span class="ding-nav-text">會員中心</span> </a>
                                </div>
                                <div class="header__top__right__auth">
                                    <a href="../members/login.html" class="m-0 ml-2 ding-nav-text">登入/註冊</a>
                                </div>
                                <div class="header__top__right__auth">
                                    <a href="../members/member-panel-news.html"> <i class="bi bi-bell m-0 ml-2 fa-lg"></i> <span class="ding-nav-text">通知中心</span> </a>
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
                            <a href="./index.html"><img style="height: 150px" src="../img/indexlogo.png" alt="" /></a>
                        </div>
                    </div>
                    <div class="col-lg-9 d-flex align-items-center">
                        <nav class="header__menu">
                            <ul>
                                <li class="active"><a href="./recipe_overview.jsp">食譜總覽</a></li>
                                <li><a href="./recipe_create.jsp">新增食譜</a></li>
                                <li><a href="../members/member-panel-follow.html">關注食譜</a></li>
                                <li><a href="../shopstage/">商城</a></li>
                                <li><a href="../article/article_main.jsp">討論區</a></li>
                                <li><a href="../news/news.html">最新消息</a></li>
                                <li><a href="./news/news.html">關於我們</a></li>
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

        <!-- 創建食譜頁面 -->
        <section class="hero hero-normal" style="background-color: rgb(226, 222, 222); padding: 30px">
            <div class="row">
                <div class="col-md-2"></div>
                <div class="col-md-8 recipe-content">
                    <div class="row">
                        <div class="col-md-2">
                            <span id="recipeeName" class="recipe_content">食譜名稱:</span>
                        </div>
                        <div class="col-md-6">
                            <span id="recipeName" class="form-control recipe-name"></span>
                        </div>
                        <div id="collectionStatus">
                            <i id="addCollection" class="bi bi-bookmark col-md-4 none" style="font-size: 30px; margin-left: 250px"></i>
                            <i id="removeCollection" class="bi bi-bookmark-fill col-md-4 none" style="font-size: 30px; margin-left: 250px"></i>
                        </div>
                    </div>
                    <p>使用標籤:</p>
                    <div id="useHashTag" class="form-control d-flex align-items-center" style="border: none"></div>

                    <div class="text-center" style="margin: 30px">
                        <div id="coverImageView">
                            <img id="coverImage" src="" alt="無法讀取圖片" />
                        </div>
                    </div>
                    <div>
                        <span class="recipe_content">食譜簡介:</span>
                        <textarea id="introduction" class="form-control martin-textarea" aria-label="With textarea" readonly="readonly"></textarea>
                    </div>

                    <div class="row">
                        <div class="col-lg-2">
                            <span class="recipe_content">份量(人數):</span>
                        </div>
                        <span id="recipeQuantity" class="col-md-2 form-control text-center"></span>
                    </div>

                    <div>
                        <div id="listIngredient" class="search-init">
                            <span class="recipe_content">食材:</span>
                        </div>
                    </div>

                    <div>
                        <span class="recipe_content">廚具:</span>
                        <div id="listKitchenware" class="search-init"></div>
                    </div>

                    <div>
                        <span class="recipe_content">步驟:</span>
                        <div id="listStep"></div>
                    </div>
                    <div>
                        <span class="recipe_content mx-auto">補充:</span>
                        <textarea id="additionalExplanation" class="form-control martin-textarea" aria-label="With textarea" placeholder="內容" readonly="readonly"></textarea>
                    </div>
                    <!-- <div id="reaction" class="row text-center">
                        <div class="col-md-8">
                            <i class="bi bi-heart mx-auto" style="font-size: 20px"></i>
                        </div>
                        <div class="col-md-4">
                            <i class="bi bi-heartbreak mx-auto" style="font-size: 20px"></i>
                        </div>
                    </div> -->
                    <div class="member-profile">
                        <div class="avatar-container">
                            <!-- <img src="../avatar.jpg" alt="Member Avatar" class="avatar" /> -->
                        </div>
                        <div class="info-container">
                            <h4 id="author"></h4>
                        </div>
                        <div class="follow-button">
                            <button type="button" class="btn btn-outline-danger">關注</button>
                        </div>
                    </div>
                </div>
            </div>
            <div id="comments" class="col-md-8 recipe-content mx-auto">
                <div class="input-group mb-3">
                    <input id="inputComment" type="text" class="form-control" placeholder="留言" aria-label="Recipient's username" aria-describedby="button-addon2" />
                    <button id="submitComment" class="btn btn-outline-secondary" type="button" id="button-addon2">送出</button>
                </div>
            </div>
        </section>
        <!-- /創建食譜頁面 -->

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
                                <a href="#"><i class="fa fa-facebook"></i></a> <a href="#"><i class="fa fa-instagram"></i></a> <a href="#"><i class="fa fa-twitter"></i></a>
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
                                    All rights reserved | This template is made with <i class="fa fa-heart" aria-hidden="true"></i> by <a href="https://colorlib.com" target="_blank">Colorlib</a>
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
        <script src="../js/jquery-3.3.1.min.js"></script>
        <script src="../js/bootstrap.min.js"></script>
        <script src="../js/jquery.nice-select.min.js"></script>
        <script src="../js/jquery-ui.min.js"></script>
        <script src="../js/jquery.slicknav.js"></script>
        <script src="../js/mixitup.min.js"></script>
        <script src="../js/owl.carousel.min.js"></script>
        <script src="../js/main.js"></script>
        <!-- browse js -->
        <script src="../js/TomJS.js"></script>
        <script src="js/recipe_browse.js"></script>
    </body>
</html>
