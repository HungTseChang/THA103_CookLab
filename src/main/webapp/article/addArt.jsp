<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.cooklab.article.model.*"%>
<%@ page import="com.cooklab.article_category.model.*"%>

<% //��com.emp.controller.EmpServlet.java��163��s�Jreq��empVO���� (�����q��Ʈw���X��empVO, �]�i�H�O��J�榡�����~�ɪ�empVO����)
 ArticleVO artVO = (ArticleVO) request.getAttribute("artVO");



	ArticleCategoryService artSvc2 = new ArticleCategoryService();
	List<ArticleCategoryVO> list2 = artSvc2.getAll();
 	pageContext.setAttribute("list2", list2);
%>
--<%= artVO==null %>--<!-- line 114 -->
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>�峹��ƭק� - addArt.jsp</title>

<style>
  table#table-1 {
	background-color: #CCCCFF;
    border: 2px solid black;
    text-align: center;
  }
  table#table-1 h4 {
    color: red;
    display: block;
    margin-bottom: 1px;
  }
  h4 {
    color: blue;
    display: inline;
  }
</style>

<style>
  table {
	width: 450px;
	background-color: white;
	margin-top: 1px;
	margin-bottom: 1px;
  }
  table, th, td {
    border: 0px solid #CCCCFF;
  }
  th, td {
    padding: 1px;
  }
</style>

</head>
<body bgcolor='white'>

<table id="table-1">
	<tr><td>
		 <h3>�峹��Ʒs�W - addArt.jsp</h3>
		 <h4><a href="<%= request.getContextPath() %>/article/select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">�^����</a></h4>
	</td></tr>
</table>

<h3>��ƭק�:</h3>

<%-- ���~��C --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">�Эץ��H�U���~:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="<%= request.getContextPath() %>/ArticleServlet" name="form1">
<table>
	<tr>
		<td>�峹����:</td>
		<td>
			<select size="1"   name="articleCategory">
				<c:forEach var="artVO2" items="${list2}">
					<option value="${artVO2.articleCategoryNo}">
								${artVO2.articleCategory}
				</c:forEach>
			</select> 
		</td>
	</tr>
	<tr>
		<td>�峹���D:</td>
		<td><input type="TEXT" name="articleTitle"  placeholder="��J���D��T" value="<%= (artVO==null)? "" : artVO.getArticleTitle()%>" size="45"/></td>		
	</tr>
	<tr>
		<td>���u�s��:</td>
		<td><input type="TEXT" name="memberId"  placeholder="��J�|���s��" value="<%= (artVO==null)? "" : artVO.getMemberId()%>" size="45"/></td>		
	</tr>
	<tr>
		<td>�峹���A:</td>
		<td><input type="TEXT" name="articleStatus" placeholder="��J�峹���A(�Ʀr)" value="<%= (artVO==null)? "" : artVO.getArticleStatus()%>" size="45"/></td>		
	</tr>
	<tr>
		<td>�峹���e:</td>
		<td><input type="TEXT" name="articleContent"  placeholder="��J�峹���e" value="<%= (artVO==null)? "" : artVO.getArticleContent()%>" size="45"/></td>		
		
	</tr>
	<tr>
		<td>�^��ƶq:</td>
		<td><input type="TEXT" name="articleCount" placeholder="��J�^��ƶq"  value="<%= (artVO==null)? 0 : artVO.getArticleCount()%>" size="45"/></td>		
		
	</tr>
	<tr>
		<td>�I������:</td>
		<td><input type="TEXT" name="viewCount"  placeholder="0" value="<%= (artVO==null)? 0 : artVO.getViewCount()%>" size="45"/></td>		
		
	</tr>


</table>
<br>
<input type="hidden" name="action" value="insert">
<input type="submit" value="�e�X�s�W"></FORM>
</body>



<!-- =========================================�H�U�� datetimepicker �������]�w========================================== -->

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script>
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>

<style>
  .xdsoft_datetimepicker .xdsoft_datepicker {
           width:  300px;   /* width:  300px; */
  }
  .xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
           height: 151px;   /* height:  151px; */
  }
</style>

<script>

        
   
        // ----------------------------------------------------------�H�U�ΨӱƩw�L�k��ܪ����-----------------------------------------------------------

        //      1.�H�U���Y�@�Ѥ��e������L�k���
        //      var somedate1 = new Date('2017-06-15');
        //      $('#f_date1').datetimepicker({
        //          beforeShowDay: function(date) {
        //        	  if (  date.getYear() <  somedate1.getYear() || 
        //		           (date.getYear() == somedate1.getYear() && date.getMonth() <  somedate1.getMonth()) || 
        //		           (date.getYear() == somedate1.getYear() && date.getMonth() == somedate1.getMonth() && date.getDate() < somedate1.getDate())
        //              ) {
        //                   return [false, ""]
        //              }
        //              return [true, ""];
        //      }});

        
        //      2.�H�U���Y�@�Ѥ��᪺����L�k���
        //      var somedate2 = new Date('2017-06-15');
        //      $('#f_date1').datetimepicker({
        //          beforeShowDay: function(date) {
        //        	  if (  date.getYear() >  somedate2.getYear() || 
        //		           (date.getYear() == somedate2.getYear() && date.getMonth() >  somedate2.getMonth()) || 
        //		           (date.getYear() == somedate2.getYear() && date.getMonth() == somedate2.getMonth() && date.getDate() > somedate2.getDate())
        //              ) {
        //                   return [false, ""]
        //              }
        //              return [true, ""];
        //      }});


        //      3.�H�U����Ӥ�����~������L�k��� (�]�i���ݭn������L���)
        //      var somedate1 = new Date('2017-06-15');
        //      var somedate2 = new Date('2017-06-25');
        //      $('#f_date1').datetimepicker({
        //          beforeShowDay: function(date) {
        //        	  if (  date.getYear() <  somedate1.getYear() || 
        //		           (date.getYear() == somedate1.getYear() && date.getMonth() <  somedate1.getMonth()) || 
        //		           (date.getYear() == somedate1.getYear() && date.getMonth() == somedate1.getMonth() && date.getDate() < somedate1.getDate())
        //		             ||
        //		            date.getYear() >  somedate2.getYear() || 
        //		           (date.getYear() == somedate2.getYear() && date.getMonth() >  somedate2.getMonth()) || 
        //		           (date.getYear() == somedate2.getYear() && date.getMonth() == somedate2.getMonth() && date.getDate() > somedate2.getDate())
        //              ) {
        //                   return [false, ""]
        //              }
        //              return [true, ""];
        //      }});
        
</script>
</html>