<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ page import="com.cooklab.article.model.ArticleVO"%>
<%@ page import="com.cooklab.article_category.model.ArticleCategoryVO"%>


<!DOCTYPE html>
<html lang="zxx">

<head>
    <meta charset="UTF-8">
    <meta name="description" content="Ogani Template">
    <meta name="keywords" content="Ogani, unica, creative, html">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Ogani | Template</title>

    <!-- Google Font -->
    <link href="https://fonts.googleapis.com/css2?family=Cairo:wght@200;300;400;600;900&display=swap" rel="stylesheet">

    <!-- Css Styles -->
    <link rel="stylesheet" href="css/bootstrap.min.css" type="text/css">
    <link rel="stylesheet" href="css/font-awesome.min.css" type="text/css">
    <link rel="stylesheet" href="css/elegant-icons.css" type="text/css">
    <link rel="stylesheet" href="css/nice-select.css" type="text/css">
    <link rel="stylesheet" href="css/jquery-ui.min.css" type="text/css">
    <link rel="stylesheet" href="css/owl.carousel.min.css" type="text/css">
    <link rel="stylesheet" href="css/slicknav.min.css" type="text/css">
    <link rel="stylesheet" href="css/style.css" type="text/css">
    <link rel="stylesheet" href="css/HO.css" type="text/css">
    <link rel="stylesheet" href="css/ding.css" type="text/css">
    <!--�U�����O�v���s�边-->
    <link href="https://cdn.quilljs.com/1.3.6/quill.snow.css" rel="stylesheet" />
    <script src="https://cdn.quilljs.com/1.3.6/quill.js"></script>
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
                <li class="active"><a href="./index.html">����</a></li>
                <li><a href="./shop-grid.html">�ӫ�</a></li>
                <li><a href="./blog.html">�����`��</a></li>
                <li><a href="./contact.html">�`�����D</a></li>
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
                                    <span class="ding-nav-text">�ʪ���</span>
                                </a>
                            </div>
                            <div class="header__top__right__auth">
                                <a href="#">
                                    <i class="fa fa-user m-0 ml-2 fa-lg"></i>
                                    <span class="ding-nav-text">�|������</span>
                                </a>
                            </div>
                            <div class="header__top__right__auth">
                                <a href="#" class="m-0 ml-2 ding-nav-text">�n�J/���U</a>
                            </div>
                            <div class="header__top__right__auth">
                                <a href="#">
                                    <i class="bi bi-bell m-0 ml-2 fa-lg"></i>
                                    <span class="ding-nav-text">�q������</span>
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
                            <li><a href="./index.html">�����`��</a></li>
                            <li><a href="#">�s�W����</a></li>
                            <li><a href="#">���`����</a></li>
                            <li><a href="./shop-grid.html">�ӫ�</a></li>
                            <li class="active"><a href="#">�Q�װ�</a></li>
                            <li><a href="#">�ȪA����</a></li>
                            <li><a href="./contact.html">����ڭ�</a></li>
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
    <!--�W����Y����-->
    
    
    
    <section id="article_conten">
        <div class="container" id="another">
            <div class="row">
                <div class="col-md-2" id="left_img" ;
                    style="border: 0px solid brown; margin-right: 10px; text-align: center;">

                    <button type="button" class="btn ding-btn-org" style="margin-top: 50px;">�ޥέ���</button>
       

                </div>
                <div class="col-md-9 " style="height: 700px; ">
                <form method="post" action="/com.tha103.cooklab/ArticleServlet" name="form1">
                
                    <select name="articleCategory">
                        <option value="1">��X����</option>
                        <option value="2">���аQ��</option>
                        <option value="3">�����Q��</option>F
                        <option value="4">���ʸ�T</option>
                        <option value="5">�߱o����</option>
                    </select>
                    <select size="1" name="articleCategory">
         				<c:forEach var="artVO" items="${artSvc.all}" > 
          					<option value="${artVO.articl}">${artVO.articleTitle}
         				</c:forEach>   
       				</select>
                    <input type="text" id="edit_title" value="�п�J���D">

                    <div style="width: auto; height: 500px;">
                        <div  id="editor">
                            <h2 class="firstview">�֨Ӽg�U�A���峹</h2>
                        </div>
                        <textarea name="quillContent"></textarea> <!-- �o�O�n����Quill�s�边���e��textarea -->
                        <p style="margin-top: 5px;">���ҽX��ܦ�m: <input type="text" value="�п�J���ҽX">
                            <button class="btn ding-btn-org" id="btn_confirm">�T�w</button>
                            <button class="btn ding-btn-org" id ="btn_drawft">�x�s��Z</button>
                            <button class="btn ding-btn-org" id="btn_clean">�M��</button>
                            <button class="btn ding-btn-org" id="btn_cancel">����</button>
                        </p>
                    </div>
                </div>
				</form>
            </div>
        </div>
    </section>








    <!-- Footer Section Begin -->
    <footer class="footer spad">
        <div class="container">
            <div class="row">
                <div class="col-lg-4">
                    <div class="footer__about">
                        <ul>
                            <li>���q�a�}�G�x�_�����s�ϫn�ʪF���T�q219��5��</li>
                            <li>�q�ܡG(02)27120589</li>
                            <li>�q�l�H�c�Gtomato@cooklab.com</li>
                        </ul>
                    </div>
                </div>
                <div class="col-lg-4">
                    <div class="footer__widget">
                        <ul>
                            <li><a href="#">����ڭ�</a></li>
                            <li><a href="#">����ө�</a></li>
                            <li><a href="#">�w���ʪ�</a></li>
                        </ul>
                        <ul>
                            <li><a href="#">�B�e��T</a></li>
                            <li><a href="#">���p�F��</a></li>
                            <li><a href="#">�`�����D</a></li>
                        </ul>
                    </div>
                </div>
                <div class="col-lg-4 col-md-12">
                    <div class="footer__widget">
                        <h6>�ߧY�q�\�q�l��</h6>
                        <p>�H�ɱ����ڭ̪��̷s�����H���u�f�T��</p>
                        <form action="#">
                            <input type="text" placeholder="��J�z���q�l�H�c" />
                            <button type="submit" class="btn ding-btn-org">�q�\</button>
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
    <script src="./js/ckeditor.js"></script>
    <script src="./js/quill.js"></script>
    <script src="./js/HO.js"></script>

</body>

</html>