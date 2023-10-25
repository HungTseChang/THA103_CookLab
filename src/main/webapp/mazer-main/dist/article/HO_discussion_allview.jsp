<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.cooklab.article.model.*"%>
<%@ page import="com.cooklab.article_sub.model.*"%>

<!-- 1�ϥ�EL��IF�y�k�A��Ʀr���r�A2.�b��ݳB�z�n�ഫ�A�ǰe -->

<%
    ArticleService artSvc = new ArticleService();
    List<ArticleVO> list = artSvc.getAll();
    pageContext.setAttribute("list",list);
    
    ArticleSubService artSvc2 = new ArticleSubService();
    List<ArticleSubVO> list2 = artSvc2.getAll();
	pageContext.setAttribute("list2",list2); 
%>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>DataTable - Mazer Admin Dashboard</title>
    
    <link rel="preconnect" href="https://fonts.gstatic.com" />
<link
	href="https://fonts.googleapis.com/css2?family=Nunito:wght@300;400;600;700;800&display=swap"
	rel="stylesheet" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/mazer-main/dist/assets/css/bootstrap.css" />

<link rel="stylesheet"
	href="<%=request.getContextPath()%>/mazer-main/dist/assets/vendors/simple-datatables/style.css" />

<link rel="stylesheet"
	href="<%=request.getContextPath()%>/mazer-main/dist/assets/vendors/perfect-scrollbar/perfect-scrollbar.css" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/mazer-main/dist/assets/vendors/bootstrap-icons/bootstrap-icons.css" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/mazer-main/dist/assets/css/app.css" />
<link rel="shortcut icon"
	href="<%=request.getContextPath()%>/mazer-main/dist/assets/images/favicon.svg"
	type="image/x-icon" />
</head>




<body>
  <div id="app">
    <div id="sidebar" class="active">
      <div class="sidebar-wrapper active">
        <div class="sidebar-header">
          <div class="d-flex justify-content-between">
            <div class="logo">
              <a href="index.html"><img src="assets/images/logo/logo.png" alt="Logo" srcset="" /></a>
            </div>
            <div class="toggler">
              <a href="#" class="sidebar-hide d-xl-none d-block"><i class="bi bi-x bi-middle"></i></a>
            </div>
          </div>
        </div>
        <div class="sidebar-menu">
          <ul class="menu">
            <!-------------------------------------------------------------------------------->
            <li class="sidebar-item">
              <a href="WCC_Homepage.html" class="sidebar-link">
                <i class="bi bi-grid-fill"></i>
                <span>��x����</span>
              </a>
            </li>

            <li class="sidebar-item">
              <a href="WCC_memeber.html" class="sidebar-link">
                <i class="bi bi-file-earmark-spreadsheet-fill"></i>
                <span>�|���޲z</span>
              </a>
            </li>

            <li class="sidebar-item">
              <a href="#" class="sidebar-link">
                <i class="bi bi-stack"></i>
                <span>�v���޲z</span>
              </a>
              <ul class="submenu">
                <li class="submenu-item">
                  <a href=".\WCC_permission_createnew.html">�Ыغ޲z</a>
                </li>
                <li class="submenu-item">
                  <a href=".\WCC_permission_management.html">�޲z�޲z��</a>
                </li>
                <li class="submenu-item">
                  <a href="#">�s��޲z��</a>
                </li>
                <li class="submenu-item">
                  <a href=".\WCC_permission_createrule.html">�Х��v���W�h</a>
                </li>
                <li class="submenu-item">
                  <a href="#">�v���޲z-�s��/�d���v���W�h-�d��</a>
                </li>
              </ul>
            </li>

            <li class="sidebar-item has-sub">
              <a href="#" class="sidebar-link">
                <i class="bi bi-collection-fill"></i>
                <span>���к޲z</span>
              </a>
              <!-- <ul class="submenu ">
        <li class="submenu-item ">
            <a href="#">���к޲z</a>
        </li>
    </ul>
</li> -->
            </li>

            <li class="sidebar-item has-sub">
              <a href="#" class="sidebar-link">
                <i class="bi bi-grid-1x2-fill"></i>
                <span>�ӫ��޲z</span>
              </a>
              <ul class="submenu">
                <li class="submenu-item">
                  <a href="#">�ӫ~�]�w</a>
                </li>
                <li class="submenu-item">
                  <a href="#">�q��޲z</a>
                </li>
                <li class="submenu-item">
                  <a href="#">�u�f��]�w</a>
                </li>
                <li class="submenu-item">
                  <a href="#">�s�i�]�w</a>
                </li>
                <li class="submenu-item">
                  <a href="#">�s�W�i�f���</a>
                </li>
              </ul>
            </li>
            <li class="sidebar-item has-sub active">
              <a href="#" class="sidebar-link">
                <i class="bi bi-hexagon-fill"></i>
                <span>�Q�װϺ޲z</span>
              </a>

              <ul class="submenu" style="display: block">
                <li class="submenu-item">
                  <a href="#">�ݪO����</a>
                </li>
                <li class="submenu-item active">
                  <a href="#" class="sub_title ">�峹�޲z</a>
                </li>
              </ul>
            </li>
            <li class="sidebar-item has-sub">
              <a href="#" class="sidebar-link">
                <i class="bi bi-pen-fill"></i>
                <span>�ƾڤ��R</span>
              </a>
              <ul class="submenu">
                <li class="submenu-item">
                  <a href="#">�|���ƾ�</a>
                </li>
                <li class="submenu-item">
                  <a href="#">���мƾ�</a>
                </li>
                <li class="submenu-item">
                  <a href="#">�ӫ��ƾ�</a>
                </li>
                <li class="submenu-item">
                  <a href="#">�峹�ƾ�</a>
                </li>
              </ul>
            </li>
            <li class="sidebar-item has-sub">
              <a href="#" class="sidebar-link">
                <i class="bi bi-pen-fill"></i>
                <span>�ȪA����</span>
              </a>
              <ul class="submenu">
                <li class="submenu-item">
                  <a href="#">�������|</a>
                </li>
                <li class="submenu-item">
                  <a href="#">�Q�װ����|</a>
                </li>
                <li class="submenu-item">
                  <a href="#">�t�γq��</a>
                </li>
              </ul>
            </li>
            <!----------------------------------------------------------------------------->
          </ul>
        </div>
        <button class="sidebar-toggler btn x">
          <i data-feather="x"></i>
        </button>
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
        li a.sub_title {
          background-color: #435ebe;
          color: white !important;
          display: block;
          padding: 0.2rem 0.5rem !important;
          font-size: 1rem;
          display: flex;
          align-items: center;
          border-radius: 0.5rem;
          transition: all .5s;
          text-decoration: none;
          color: #25396f;
        }

        td a.wcc {
          border: 1px solid rgb(151, 135, 249);
          background-color: rgb(195, 241, 253);
          padding: 4px;
          border-radius: 20px;
        }
        
         td input.wcc {
          border: 1px solid rgb(151, 135, 249);
          background-color: rgb(195, 241, 253);
          padding: 4px;
          border-radius: 20px;
        }

        td button.wcc {
          border-radius: 20px;
        }
        td.HO_article_title{
          /* width: 15%; */
          /* white-space: wrap; */
            /*�]�w��r�@��*/
          text-overflow: ellipsis;
          /*clip(�w�])|ellipsis(�ٲ��Ÿ�)*/
          /* overflow: hidden; */
        }
      
      </style>
      <!-- ////////////////////////////////////////////////////////////////////////////////////////// -->
      <div class="page-heading">
        <div class="page-title">
          <div class="row">
            <div class="col-12 col-md-6 order-md-1 order-last">
              <h3>�峹�޲z</h3>
            </div>
            <div class="col-12 col-md-6 order-md-2 order-first">
              <nav aria-label="breadcrumb" class="breadcrumb-header float-start float-lg-end">
                <ol class="breadcrumb">
                  <li class="breadcrumb-item">
                    <a href="index.html">Dashboard</a>
                  </li>
                  <li class="breadcrumb-item active" aria-current="page">
                    �峹�޲z
                  </li>
                </ol>
              </nav>
            </div>
          </div>
        </div>
        <section class="section">
          <div class="card">

            <div class="card-body">
              <table class="table table-striped" id="table1">
                <thead>
                  <tr>
                    <th class="first_col">�峹�s��</th>
                    <th>�峹����</th>
                    <th class="sec_col">�峹���D</th>
                    <th class="thrid_col">�峹���A</th>
                    <th class="forth_col">�峹�@��</th>
                    <th>�o��ɶ�</th>
                    <th class="last_column">�ާ@</th>
                  </tr>
                </thead>
                <tbody>
                <c:forEach var="artVO" items="${list}">
                  <tr>
                    <td>${artVO.articleNo}</td>
                    <td>${artVO.articleCategory.articleCategory}</td>
                    <td class="HO_article_title">${artVO.articleTitle}</td>
                    <td class="artice_status">
                      <a href="#" class="btn btn-success rounded-pill btn article_status" >
                      ${artVO.articleStatus}</a>
                      </td>
                    <td>${artVO.members.memberNickname}</td>
        			<td><fmt:formatDate value="${artVO.createdTimestamp}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
														
                    <td>
                    <FORM METHOD="post" ACTION="<%= request.getContextPath() %>/ArticleServlet" >
                  
					  <input type="hidden" name="articleNo" value="${artVO.articleNo}">
					  <input type="hidden" name="action" value="getOne_For_Display">
					  <input type="submit" class="wcc"  value="�d�ݤ峹"> 
					  </FORM>
					 </td> 
					 <td>
					  <FORM METHOD="post" ACTION="<%= request.getContextPath() %>/ArticleServlet" >
                      <select class="ch_artice_status" name="articleStatus">
                        <option>��ܪ��A</option>
                        <option id="artice_Option1" value= 0>���}</option>
                        <option id="artice_Option2"	value= 1>�D���}</option>
                        <option id="artice_Option3"	value= 2>��Z</option>
                        <option id="artice_Option4"	value= 3>�R��</option>
                      </select>
                      <input type="hidden" name="articleNo" value="${artVO.articleNo}">
                  	 <input type="hidden" name="action" value="getStatusUpdate">
					  <input type="submit" >
                      </FORM>
                    </td>
                  </c:forEach>
                  
				<!--     ===========�U��sub_art��forEach =======       -->
                  
                  <c:forEach var="artVO2" items="${list2}">
                  <tr>
                    <td>${artVO2.articleSubNo}</td>
                    <td>${artVO2.article.articleNo}���^��</td>
                    <td class="HO_article_title">RE# ${artVO2.article.articleTitle}</td>
                    <td class="artice_status">
                      <a href="#" class="btn btn-success rounded-pill btn article_status" >
                      ${artVO2.articleSubStatus}</a>
                    </td>
                    <td>${artVO2.members.memberNickname}</td>
        			<td><fmt:formatDate 
        			value="${artVO2.createdTimestamp}"
 					pattern="yyyy-MM-dd HH:mm:ss" /> 
					</td>									
                    <td>
                    
                    <FORM METHOD="post" ACTION="<%= request.getContextPath() %>/ArticleSubServlet" >
<!--                 		 <a class="wcc" href="">�d�ݤ峹</a> -->
					  <input type="hidden" name="articleSubNo" value="${artVO2.articleSubNo}">
					  <input type="hidden" name="action" value="getOne_For_Display">
					  <input type="submit" class="wcc"  value="�d�ݤ峹"> 
					  </FORM>
					 </td> 
					 <td>
					  <FORM METHOD="post" ACTION="<%= request.getContextPath() %>/ArticleSubServlet" >
                      <select class="ch_artice_status" name="articleSubStatus">
                        <option>��ܪ��A</option>
                        <option id="artice_Option1" value= 0>���}</option>
                        <option id="artice_Option2"	value= 1>�D���}</option>
                        <option id="artice_Option3"	value= 2>��Z</option>
                        <option id="artice_Option4"	value= 3>�R��</option>
                      </select>
                      <input type="hidden" name="articleNo" value="${artVO2.articleSubNo}">
                  	 <input type="hidden" name="action" value="update">
					  <input type="submit" >
                      </FORM>
                    </td>
                  </c:forEach>
    			</tr>
                </tbody>
              </table>
            </div>
          </div>
        </section>
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
  	<script>
// 		Simple Datatable
// 		        let table1 = document.querySelector('#table1');
// 		        let dataTable = new simpleDatatables.DataTable(table1);
	</script>	
	<script src="<%=request.getContextPath()%>/mazer-main/dist/assets/vendors/jquery-3.7.1.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/mazer-main/dist/assets/vendors/perfect-scrollbar/perfect-scrollbar.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/mazer-main/dist/assets/js/bootstrap.bundle.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/mazer-main/dist/assets/vendors/simple-datatables/simple-datatables.js"></script>

	<script
		src="<%=request.getContextPath()%>/mazer-main/dist/assets/js/main.js"></script>
	<script
		src="<%=request.getContextPath()%>/mazer-main/dist/assets/js/menu_ative.js"></script>


  <script>
  	let table1 = document.querySelector("#table1");
  	let dataTable = new simpleDatatables.DataTable(table1);
    $(function () {
    	//jsp���ϥ�read���ܡA�|�~�{��ajax�n�౵�����n���ready
      $(document).ready("change", ".btn.article_status", function () { 
        // console.log("you touch me");
      });
    	

      $(document).ready(function () {
        //���o�Ʀr
        var $articleStatusBtn = $('.article_status');
        // ��C��a���Ҫ��Ʀr�ର��r
        $articleStatusBtn.each(function () {
          var $this = $(this);
          var status = parseInt($this.text());

          switch (status) {
            case 0:
              $this.text('���}');
              break;
            case 1:
              $this.text('�D���}');
              break;
            case 2:
              $this.text('��Z');
              break;
            case 3:
              $this.text('�R��');
              break;
          }
        });
      });

      $(document).on("change", ".ch_artice_status", function () {
        let selectedOption = $(this).find("option:selected").text();
        console.log(selectedOption);
        let parentRow = $(this).closest("tr"); //�Ψӧ�M�Ӱϰ쪺������<tr>�A
        //�ϥ�find��������<tr>�ϰ�A�~�i�H���w��<tr>�ϰ쪺���e���ܡA�i�Ӥ��v�T���L<tr>�ϰ�
        switch (selectedOption) {
          case "0":
            let new_status1 = `<a href="#" class="btn btn-success rounded-pill btn article_status">���}</a>`;
            parentRow.find(".btn.article_status").remove();
            parentRow.find(".artice_status").append(new_status1);
            break;
          case "1":
            let new_status2 = `<a href="#" class="btn btn-info rounded-pill btn article_status">�D���}</a>`;
            parentRow.find(".btn.article_status").remove();
            parentRow.find(".artice_status").append(new_status2);
            break;
          case "2":
            let new_status3 = `<a href="#" class="btn btn-warning rounded-pill btn article_status">��Z</a>`;
            parentRow.find(".btn.article_status").remove();
            parentRow.find(".artice_status").append(new_status3);
            break;
          case "3":
            let new_status4 = `<a href="#" class="btn btn-danger rounded-pill btn article_status">�R��</a>`;
            parentRow.find(".btn.article_status").remove();
            parentRow.find(".artice_status").append(new_status4);
            break;
        }        
      });
    });
  </script>
</body>

</html>