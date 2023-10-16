<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.cooklab.article.model.ArticleVO"%>
<%@ page import="com.cooklab.article_report.model.*" %>
<%@ page import="java.util.HashMap"%>
<%@ page import="java.util.Map"%>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<% //見com.emp.controller.EmpServlet.java第163行存入req的empVO物件 (此為從資料庫取出的empVO, 也可以是輸入格式有錯誤時的empVO物件)
ArticleReportVO ArticleReportVO = (ArticleReportVO) request.getAttribute("ArticleReportVO");
Map<Integer, String> map = new HashMap<>();
map.put(0, "已處理");
map.put(1, "未處理");
List<Integer> ArtNolist = (ArrayList)request.getAttribute("ArtNolist");
List<String> ArtTitlelist = (ArrayList)request.getAttribute("ArtTitlelist");
List<Integer> MemIdlist = (ArrayList)request.getAttribute("MemIdlist");
List<String> MemNiKlist = (ArrayList)request.getAttribute("MemNiKlist");
%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>文章資料修改 - update_ArticleReport_input.jsp</title>

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
		 <h3>文章資料修改 - update_ArticleReport_input.jsp</h3>
		 <h4><a href="/com.tha103.cooklab/articlereport/select_page.jsp"><img src="${pageContext.request.contextPath}/articlereport/images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>

<h3>資料修改:</h3>

<%-- 錯誤表列 --%>
<c:if test="${not empty error}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${error}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="/com.tha103.cooklab/ArticleReportServlet" name="form1">
<table>
	<tr>
		<td>檢舉編號:<font color=red><b>*</b></font></td>
		<td><%=ArticleReportVO.getArticleReportNo()%></td>
	</tr>
	<tr>
		<td>文章編號:</td>
<%-- 		<td><input type="TEXT" name="articleNo" value="<%=ArticleReportVO.getArticleNo()%>" size="45"/></td> --%>
		<td>
        <select name="articleNo">
          <% for (int i = 0; i < ArtNolist.size(); i++) { %>
                <option value="<%= ArtNolist.get(i) %>"  <%=ArtNolist.get(i) ==ArticleReportVO.getArticleNo()? "selected":""%> >
                   文章編號: <%= ArtNolist.get(i) %> - 文章標題: <%= ArtTitlelist.get(i) %>
                </option>
            <% } %>
        </select>
        </td>>
	</tr>
	<tr>
		<td>檢舉者編號:</td>
<%-- 		<td><input type="TEXT" name="reporterId"   value="<%=ArticleReportVO.getReporterId()%>" size="45"/></td> --%>
		<td>
        <select name="reporterId">
          <% for (int i = 0; i < MemIdlist.size(); i++) { %>
                <option value="<%= MemIdlist.get(i) %>" <%=MemIdlist.get(i) ==ArticleReportVO.getReporterId()? "selected":""%> >
                   檢舉者ID: <%= MemIdlist.get(i) %> - 檢舉者暱稱: <%= MemNiKlist.get(i) %>
                </option>
            <% } %>
        </select>
        </td>>
	</tr>
	<tr>
		<td>檢舉理由:</td>
		<td><input type="TEXT" name="reportingReason"   value="<%=ArticleReportVO.getReportingReason()%>" size="45"/></td>
	</tr>
	<tr>
		<td>檢舉狀態:</td>
		
		<td>
		<label for="dealA">已處裡</label>
		<input type="radio" id="dealA"  name="reportingStatus"  value="0" size="45" <%=ArticleReportVO.getReportingStatus()==0? "checked":""%>/>
        <label for="dealB">未處理</label>
		<input type="radio" id="dealB "name="reportingStatus"  value="1" size="45"  <%=ArticleReportVO.getReportingStatus()==0? "":"checked"%>/>
		</td>	
	</tr>
	<tr>
		<td>檢舉時間:</td>
		<td><input type="TEXT" name="createdTimestamp"  value="<%=ArticleReportVO.getCreatedTimestamp()%>" size="45"
		placeholder="格式為:YYYY-MM-DD hh:mm:ss.ms"
		/></td>
	</tr>

</table>

<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="articleReportNo" value="<%=ArticleReportVO.getArticleReportNo()%>">
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