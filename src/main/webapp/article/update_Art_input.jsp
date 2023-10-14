<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.cooklab.article.model.ArticleVO"%>

<% //見com.emp.controller.EmpServlet.java第163行存入req的empVO物件 (此為從資料庫取出的empVO, 也可以是輸入格式有錯誤時的empVO物件)
  ArticleVO artVO = (ArticleVO) request.getAttribute("artVO");
%>
--<%= artVO==null %>--<!-- line 114 -->
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>文章資料修改 - update_Art_input.jsp</title>

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
		 <h3>文章資料修改 - update_Art_input.jsp</h3>
		 <h4><a href="<%= request.getContextPath() %>/article/select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>

<h3>資料修改:</h3>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="<%= request.getContextPath() %>/ArticleServlet" name="form1">
<table>
	<tr>
		<td>文章編號:<font color=red><b>*</b></font></td>
		<td><%=artVO.getArticleNo() %></td>
	</tr>
	<tr>
		<td>文章分類:</td>
		<!-- <td><input type="TEXT" name="articleCategory" value="<%=artVO.getArticleCategoryVO().getArticleCategory()%>" size="45"/></td> -->
		<td><input type="TEXT" name="articleCategory" value="${artVO.articleCategoryVO.articleCategory}" size="45"/></td>
	</tr>
	<tr>
		<td>文章標題:</td>
		<td><input type="TEXT" name="articleTitle"   value="${artVO.articleTitle}" size="45"/></td>
	</tr>
	<tr>
		<td>會員編號:</td>
		<td><input type="TEXT" name="memberId"   value="<%=artVO.getMemberId()%>" size="45"/></td>
	</tr>
	<tr>
		<td>文章狀態:</td>
		<td><input type="TEXT" name="articleStatus"  value="<%=artVO.getArticleStatus()%>" size="45"/></td>
	</tr>
	<tr>
		<td>文章內容:</td>
		<td><input type="TEXT" name="articleContent"  value="<%=artVO.getArticleContent()%>" size="45"/></td>
	</tr>
	<tr>
		<td>文章回文數:</td>
		<td><input type="TEXT" name="articleCount"  value="<%=artVO.getArticleCount()%>" size="45"/></td>
	</tr>
	<tr>
		<td>文章點擊數:</td>
		<td><input type="TEXT" name="viewCount"  value="<%=artVO.getViewCount()%>" size="45"/></td>
	</tr>
	<tr>
		<td>最後編輯時間:<font color=red><b>*</b></font></td>
		<td><%=artVO.getLastEditTimestamp()%></td>
	</tr>
</table>

<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="articleNo" value="<%=artVO.getArticleNo()%>">
<input type="submit" value="送出修改"></FORM>
</body>



<!-- =========================================以下為 datetimepicker 之相關設定========================================== -->

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

   
        // ----------------------------------------------------------以下用來排定無法選擇的日期-----------------------------------------------------------

        //      1.以下為某一天之前的日期無法選擇
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

        
        //      2.以下為某一天之後的日期無法選擇
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


        //      3.以下為兩個日期之外的日期無法選擇 (也可按需要換成其他日期)
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