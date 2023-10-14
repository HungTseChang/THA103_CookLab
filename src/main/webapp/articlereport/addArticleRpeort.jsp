<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.cooklab.article.model.ArticleVO"%>
<%@ page import="com.cooklab.article_report.model.*" %>
<%@ page import="java.util.HashMap"%>
<%@ page import="java.util.Map"%>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.sql.Timestamp" %>
<% //��com.emp.controller.EmpServlet.java��163��s�Jreq��empVO���� (�����q��Ʈw���X��empVO, �]�i�H�O��J�榡�����~�ɪ�empVO����)
Map<Integer, String> map = new HashMap<>();
map.put(0, "�w�B�z");
map.put(1, "���B�z");
List<Integer> ArtNolist = (ArrayList)request.getAttribute("ArtNolist");
List<String> ArtTitlelist = (ArrayList)request.getAttribute("ArtTitlelist");
List<Integer> MemIdlist = (ArrayList)request.getAttribute("MemIdlist");
List<String> MemNiKlist = (ArrayList)request.getAttribute("MemNiKlist");
Timestamp createTime= (Timestamp)request.getAttribute("createTime");
%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>�峹��ƭק� - update_ArticleReport_input.jsp</title>

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
		 <h3>�峹��Ʒs�W - addArticleReport.jsp</h3>
		 <h4><a href="/com.tha103.cooklab/articlereport/select_page.jsp"><img src="${pageContext.request.contextPath}/articlereport/images/back1.gif" width="100" height="32" border="0">�^����</a></h4>
	</td></tr>
</table>

<h3>��ƭק�:</h3>

<%-- ���~��C --%>
<c:if test="${not empty error}">
	<font style="color:red">�Эץ��H�U���~:</font>
	<ul>
		<c:forEach var="message" items="${error}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="/com.tha103.cooklab/ArticleReportServlet" name="form1">
<table>
	<tr>
		<td>���|�s��:<font color=red><b>*</b></font></td>
		<td></td>
	</tr>
	<tr>
		<td>�峹�s��:</td>
<%-- 		<td><input type="TEXT" name="articleNo" value="<%=ArticleReportVO.getArticleNo()%>" size="45"/></td> --%>
		<td>
        <select name="articleNo">
          <% for (int i = 0; i < ArtNolist.size(); i++) { %>
                <option value="<%= ArtNolist.get(i) %>">
                   �峹�s��: <%= ArtNolist.get(i) %> - �峹���D: <%= ArtTitlelist.get(i) %>
                </option>
            <% } %>
        </select>
        </td>>
	</tr>
	<tr>
		<td>���|�̽s��:</td>
<%-- 		<td><input type="TEXT" name="reporterId"   value="<%=ArticleReportVO.getReporterId()%>" size="45"/></td> --%>
		<td>
        <select name="reporterId">
          <% for (int i = 0; i < MemIdlist.size(); i++) { %>
                <option value="<%= MemIdlist.get(i) %>">
                   ���|��ID: <%= MemIdlist.get(i) %> - ���|�̼ʺ�: <%= MemNiKlist.get(i) %>
                </option>
            <% } %>
        </select>
        </td>>
	</tr>
	<tr>
		<td>���|�z��:</td>
		<td><input type="TEXT" name="reportingReason"   value="" size="45"/></td>
	</tr>
	<tr>
		<td>���|���A:</td>
		
		<td>
		<label for="dealA">�w�B��</label>
		<input type="radio" id="dealA"  name="reportingStatus"  value="0" size="45"/>
        <label for="dealB">���B�z</label>
		<input type="radio" id="dealB "name="reportingStatus"  value="1" size="45" checked />
		</td>	
	</tr>
	<tr>
		<td>���|�ɶ�:</td>
		<td><input type="TEXT" name="createdTimestamp"  value="<%= createTime%>" size="45"
		placeholder="�榡��:YYYY-MM-DD hh:mm:ss.ms"
		/></td>
	</tr>
</table>

<br>
<input type="hidden" name="action" value="insert">
<input type="submit" value="�T�{�s�W"></FORM>
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