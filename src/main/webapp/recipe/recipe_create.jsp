<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.cooklab.purchase_order.model.*"%>
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
        <link rel="stylesheet" href="css/bootstrap.min.css" type="text/css" />
        <link rel="stylesheet" href="css/font-awesome.min.css" type="text/css" />
        <link rel="stylesheet" href="css/elegant-icons.css" type="text/css" />
        <link rel="stylesheet" href="css/nice-select.css" type="text/css" />
        <link rel="stylesheet" href="css/jquery-ui.min.css" type="text/css" />
        <link rel="stylesheet" href="css/owl.carousel.min.css" type="text/css" />
        <link rel="stylesheet" href="css/slicknav.min.css" type="text/css" />
        <link rel="stylesheet" href="css/style.css" type="text/css" />
        <!-- bootstrap-icon -->
        <link rel="stylesheet" href="./bootstrap-icons-1.10.5/font/bootstrap-icons.css" />
        <!-- 自增CSS -->
        <link rel="stylesheet" href="css/styleHungTse.css" type="text/css" />
        <!-- header&footer-CSS -->
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
                <a href="#"><img src="img/logo.png" alt="" /></a>
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
                    <img src="img/language.png" alt="" />
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
                <a href="#"><i class="fa fa-facebook"></i></a>
                <a href="#"><i class="fa fa-twitter"></i></a>
                <a href="#"><i class="fa fa-linkedin"></i></a>
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

        <!-- 創建食譜頁面 -->
        <section class="hero hero-normal" style="background-color: rgb(226, 222, 222); padding: 30px">
            <div class="row">
                <div class="col-md-2"></div>
                <div class="col-md-8 recipe-content">
                    <div class="row">
                        <div class="col-md-2">
                            <span class="recipe_content">食譜名稱:</span>
                        </div>
                        <div class="col-md-6">
                            <input type="text" class="form-control recipe-name" placeholder="請輸入食譜名稱" />
                        </div>
                        <div class="col-md-2">
                            <button type="submit" class="site-btn" style="background-color: #f27405">確認名稱</button>
                        </div>
                    </div>

                    <button type="button" id="btnTag">標籤選取</button>
                    <div id="tagBox" class="none">
                        <article>
                            <p>已選擇標籤:</p>
                            <div id="selectTag" class="form-control d-flex align-items-center">
                                <input type="text" class="searchTag" />
                            </div>
                            <p>熱門標籤:</p>
                            <button class="addTag badge badge-secondary">番茄</button>
                            <button class="addTag badge badge-secondary">湯姆</button>
                            <p>烹飪方式:</p>
                            <button class="addTag badge badge-secondary">烤</button>
                            <button class="addTag badge badge-secondary">煮</button>
                            <button class="addTag badge badge-secondary">燉</button>
                            <button class="addTag badge badge-secondary">生食</button>
                            <button class="addTag badge badge-secondary">蒸</button>
                            <button class="addTag badge badge-secondary">炸</button>
                            <button class="addTag badge badge-secondary">炒</button>
                            <button class="addTag badge badge-secondary">涼拌</button>
                            <button class="addTag badge badge-secondary">煮</button>
                            <p>餐點類型:</p>
                            <button class="addTag badge badge-secondary">早餐</button>
                            <button class="addTag badge badge-secondary">午餐</button>
                            <button class="addTag badge badge-secondary">晚餐</button>
                            <button class="addTag badge badge-secondary">點心</button>
                            <button class="addTag badge badge-secondary">甜點</button>
                            <button class="addTag badge badge-secondary">點心</button>
                            <button class="addTag badge badge-secondary">湯</button>
                            <button class="addTag badge badge-secondary">沙拉</button>
                            <button class="addTag badge badge-secondary">前菜</button>
                            <button class="addTag badge badge-secondary">主菜</button>
                            <p>特殊飲食需求:</p>
                            <button class="addTag badge badge-secondary">素食</button>
                            <button class="addTag badge badge-secondary">無麩質</button>
                            <button class="addTag badge badge-secondary">低卡路里</button>
                            <button class="addTag badge badge-secondary">無糖</button>
                            <button class="addTag badge badge-secondary">甜點</button>
                            <p>節日:</p>
                            <button class="addTag badge badge-secondary">春節</button>
                            <button class="addTag badge badge-secondary">情人節</button>
                            <button class="addTag badge badge-secondary">清明節</button>
                            <button class="addTag badge badge-secondary">母親節</button>
                            <br />
                            <div class="text-right"><button type="button" class="btn_modal_close text-right">關閉</button></div>
                        </article>
                    </div>

                    <div class="text-center" style="margin: 30px">
                        <div class="preview_finished"><span class="text">成品圖片</span></div>
                    </div>

                    <div>
                        <span class="recipe_content">食譜簡介:</span>
                        <textarea class="form-control martin-textarea" aria-label="With textarea" placeholder="內容"></textarea>
                    </div>

                    <div class="row">
                        <div class="col-lg-2"><span class="recipe_content">份量(人數):</span></div>
                        <select class="col-md-2">
                            <option value="option1">1</option>
                            <option value="option2">2</option>
                            <option value="option3">3</option>
                            <option value="option1">4</option>
                            <option value="option2">5</option>
                            <option value="option3">6</option>
                            <option value="option1">7</option>
                            <option value="option2">8</option>
                            <option value="option3">9</option>
                            <option value="option3">10+</option>
                        </select>
                    </div>

                    <div>
                        <span class="recipe_content">食材:</span>

                        <div class="row align-items-center" style="margin: 5px">
                            <div class="col-md-5">
                                <input type="text" class="form-control" placeholder="請輸入食材" />
                            </div>
                            <div class="col-md-4">
                                <input type="text" class="form-control" placeholder="份量" />
                            </div>
                            <i class="bi bi-list">&emsp;</i>
                            <i class="bi bi-trash3-fill"></i>
                        </div>

                        <div class="row align-items-center" style="margin: 5px">
                            <div class="col-md-5">
                                <input type="text" class="form-control" placeholder="請輸入食材" />
                            </div>
                            <div class="col-md-4">
                                <input type="text" class="form-control" placeholder="份量" />
                            </div>
                            <i class="bi bi-list">&emsp;</i>
                            <i class="bi bi-trash3-fill"></i>
                        </div>

                        <div class="row align-items-center" style="margin: 5px">
                            <div class="col-md-5">
                                <input type="text" class="form-control" placeholder="請輸入食材" />
                            </div>
                            <div class="col-md-4">
                                <input type="text" class="form-control" placeholder="份量" />
                            </div>
                            <i class="bi bi-list">&emsp;</i>
                            <i class="bi bi-trash3-fill"></i>
                        </div>

                        <button class="col-md-9 form-control add-ingredients">加入食材</button>
                    </div>

                    <div>
                        <span class="recipe_content">廚具:</span>

                        <div class="row align-items-center" style="margin: 5px">
                            <div class="col-md-5">
                                <input type="text" class="form-control" placeholder="請輸入廚具" />
                            </div>

                            <i class="bi bi-list">&emsp;</i>
                            <i class="bi bi-trash3-fill"></i>
                        </div>
                        <div class="row align-items-center" style="margin: 5px">
                            <div class="col-md-5">
                                <input type="text" class="form-control" placeholder="請輸入廚具" />
                            </div>

                            <i class="bi bi-list">&emsp;</i>
                            <i class="bi bi-trash3-fill"></i>
                        </div>
                        <div class="row align-items-center" style="margin: 5px">
                            <div class="col-md-5">
                                <input type="text" class="form-control" placeholder="請輸入廚具" />
                            </div>

                            <i class="bi bi-list">&emsp;</i>
                            <i class="bi bi-trash3-fill"></i>
                        </div>

                        <button class="col-md-5 form-control add-ingredients">加入廚具</button>
                    </div>

                    <div>
                        <span class="recipe_content">步驟:</span>
                        <div class="row">
                            <div class="col-md-3 text-center">
                                <div class="preview"><span class="text">步驟圖片</span></div>
                            </div>

                            <div class="col-md-8">
                                <div class="row">
                                    <span class="recipe_content col-md-2">步驟1:</span>
                                    <input type="text" class="form-control col-md-3" placeholder="花費時間(分鐘)" />
                                </div>
                                <textarea class="form-control martin-textarea" aria-label="With textarea" placeholder="步驟說明"></textarea>
                            </div>

                            <i class="bi bi-list">&emsp;</i>
                            <i class="bi bi-trash3-fill"></i>
                        </div>

                        <div class="row">
                            <div class="col-md-3 text-center">
                                <div class="preview"><span class="text">步驟圖片</span></div>
                            </div>

                            <div class="col-md-8">
                                <div class="row">
                                    <span class="recipe_content col-md-2">步驟2:</span>
                                    <input type="text" class="form-control col-md-3" placeholder="花費時間(分鐘)" />
                                </div>

                                <textarea class="form-control martin-textarea" aria-label="With textarea" placeholder="步驟說明"></textarea>
                            </div>

                            <i class="bi bi-list">&emsp;</i>
                            <i class="bi bi-trash3-fill"></i>
                        </div>

                        <div class="row">
                            <div class="col-md-3 text-center">
                                <div class="preview"><span class="text">步驟圖片</span></div>
                            </div>

                            <div class="col-md-8">
                                <div class="row">
                                    <span class="recipe_content col-md-2">步驟3:</span>
                                    <input type="text" class="form-control col-md-3" placeholder="花費時間(分鐘)" />
                                </div>

                                <textarea class="form-control martin-textarea" aria-label="With textarea" placeholder="步驟說明"></textarea>
                            </div>

                            <i class="bi bi-list">&emsp;</i>
                            <i class="bi bi-trash3-fill"></i>
                        </div>

                        <button class="form-control add-ingredients">新增步驟</button>
                    </div>
                    <div>
                        <span class="recipe_content">補充:</span>
                        <textarea class="form-control martin-textarea" aria-label="With textarea" placeholder="內容"></textarea>
                    </div>
                </div>

                <div class="col-md-2">
                    <div class="sticky-sidebar">
                        <div class="right-column">儲存</div>
                        <div class="right-column">取消</div>
                        <div class="right-column">清除</div>
                        <div class="right-column">發布</div>
                    </div>
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
        <script src="js/recipe_create.js"></script>
    </body>
</html>
