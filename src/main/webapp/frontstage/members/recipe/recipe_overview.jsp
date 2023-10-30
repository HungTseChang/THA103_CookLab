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
        <link rel="stylesheet" href="css/ding2.css" type="text/css" />
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
                <a href="#"><img src="../img/logo.png" alt="" /></a>
            </div>
            <div class="humberger__menu__cart">
                <ul>
                    <li>
                        <a href="#"><i class="fa fa-heart"></i> <span>1</span></a>
                    </li>
                    <li>
                        <a href="#"><i class="fa fa-shopping-bag"></i> <span>3</span></a>
                    </li>
                </ul>
                <div class="header__cart__price">item: <span>$150.00</span></div>
            </div>
            <div class="humberger__menu__widget">
                <div class="header__top__right__language">
                    <img src="../img/language.png" alt="" />
                    <div>English</div>
                    <span class="arrow_carrot-down"></span>
                    <ul>
                        <li><a href="#">Spanis</a></li>
                        <li><a href="#">English</a></li>
                    </ul>
                </div>
                <div class="header__top__right__auth">
                    <a href="#"><i class="fa fa-user"></i> Login</a>
                </div>
            </div>
            <nav class="humberger__menu__nav mobile-menu">
                <ul>
                    <li class="active"><a href="./index.html">Home</a></li>
                    <li><a href="./shop-grid.html">Shop</a></li>
                    <li>
                        <a href="#">Pages</a>
                        <ul class="header__menu__dropdown">
                            <li><a href="./shop-details.html">Shop Details</a></li>
                            <li><a href="./shoping-cart.html">Shoping Cart</a></li>
                            <li><a href="./checkout.html">Check Out</a></li>
                            <li><a href="./blog-details.html">Blog Details</a></li>
                        </ul>
                    </li>
                    <li><a href="./blog.html">Blog</a></li>
                    <li><a href="./contact.html">Contact</a></li>
                </ul>
            </nav>
            <div id="mobile-menu-wrap"></div>
            <div class="header__top__right__social">
                <a href="#"><i class="fa fa-facebook"></i></a> <a href="#"><i class="fa fa-twitter"></i></a> <a href="#"><i class="fa fa-linkedin"></i></a>
                <a href="#"><i class="fa fa-pinterest-p"></i></a>
            </div>
            <div class="humberger__menu__contact">
                <ul>
                    <li><i class="fa fa-envelope"></i> hello@colorlib.com</li>
                    <li>Free Shipping for all Order of $99</li>
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
                                    <a href="#"> <i class="bi bi-cart3 m-0 ml-2 fa-lg"></i> <span class="ding-nav-text">購物車</span> </a>
                                </div>
                                <div class="header__top__right__auth">
                                    <a href="#"> <i class="fa fa-user m-0 ml-2 fa-lg"></i> <span class="ding-nav-text">會員中心</span> </a>
                                </div>
                                <div class="header__top__right__auth">
                                    <a href="#" class="m-0 ml-2 ding-nav-text">登入/註冊</a>
                                </div>
                                <div class="header__top__right__auth">
                                    <a href="#"> <i class="bi bi-bell m-0 ml-2 fa-lg"></i> <span class="ding-nav-text">通知中心</span> </a>
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
                                <li><a href="./index.html">食譜總覽</a></li>
                                <li class="active"><a href="#">新增食譜</a></li>
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

        <!-- Hero Section Begin -->
        <section class="hero hero-normal" id="rs_hero_felx">
            <div class="container">
                <div class="row">
                    <div class="col-lg-2"></div>
                    <section class="container mb-3">
                        <main class="searchbar-block border rounded d-flex">
                            <!-- 排序選單起始-->
                            <div class="dropdown searchbar-dd">
                                <button class="btn ding-btn-org dropdown-toggle searchbar-ddbtn" type="button" id="dropdownMenu1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                    排序
                                </button>
                                <ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu">
                                    <li class="dropdown-item"><a href="javascript:void(0);" onclick="sort(1)">由新到舊</a></li>
                                    <li class="dropdown-item"><a href="javascript:void(0);" onclick="sort(2)">由舊到新</a></li>
                                    <li class="dropdown-item"><a href="javascript:void(0);" onclick="sort(3)">最多觀看</a></li>
                                    <li class="dropdown-item"><a href="javascript:void(0);" onclick="sort(4)">最少觀看</a></li>
                                </ul>
                            </div>
                            <!-- 排序選單結束-->

                            <!-- 搜尋框起始 -->
                            <form id="search" class="d-flex searchbar-form">
                                <input type="text" class="form-control border-0 searchbar-input" id="index-searchbar" aria-describedby="searchbar" placeholder="查詢 食譜名稱 食材 廚具" />
                                <button type="button" class="btn ding-btn-org searchbar-btn">
                                    <i class="bi bi-search"></i>
                                </button>
                            </form>
                            <!-- 搜尋框結束 -->
                        </main>
                    </section>
                    <!-- 食譜首頁搜尋列區塊結束 -->
                </div>
            </div>
        </section>
        <section class="blog spad">
            <div class="container">
                <div class="row">
                    <div class="col-lg-12 col-md-12">
                        <div id="recipeList" class="row"></div>
                    </div>
                </div>
            </div>
        </section>
        <div id="page" class="text-center"></div>

        <!-- Blog Section End -->

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
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
        <script src="../js/jquery-3.3.1.min.js"></script>
        <script src="../js/bootstrap.min.js"></script>
        <script src="../js/jquery.nice-select.min.js"></script>
        <script src="../js/jquery-ui.min.js"></script>
        <script src="../js/jquery.slicknav.js"></script>
        <script src="../js/mixitup.min.js"></script>
        <script src="../js/owl.carousel.min.js"></script>
        <script src="../js/main.js"></script>

        <!-- overview js -->
        <script src="../js/TomJS.js"></script>
        <script src="js/recipe_overview.js"></script>
    </body>
</html>
