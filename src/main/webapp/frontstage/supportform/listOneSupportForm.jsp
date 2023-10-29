<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.cooklab.support_form.model.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
SupportFormVO sfVO = (SupportFormVO) request.getAttribute("sfVO"); 
%>

<html>
<head>
<title>表單資料 - listOneSupportForm.jsp</title>

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
	width: 600px;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
  }
  table, th, td {
    border: 1px solid #CCCCFF;
  }
  th, td {
    padding: 5px;
    text-align: center;
  }
</style>

</head>
<body bgcolor='white'>

<h4>此頁暫練習採用 Script 的寫法取值:</h4>
<table id="table-1">
	<tr><td>
		 <h3>表單資料 - listOneSupportForm.jsp</h3>
		 <h4><a href="/THA103_CookLab/supportform/select_page.jsp">回首頁</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>表單編號</th>
		<th>客戶姓名</th>
		<th>問題類別</th>
		<th>回覆信箱</th>
		<th>反應標題</th>
		<th>表單內文</th>
		<th>處理狀態</th>
		<th>處理來源</th>
		<th>表單建立者</th>
		<th>表單處理者</th>
		<th>表單建立時間</th>
	</tr>
	<tr>
		<td><%=sfVO.getFormNo()%></td>
		<td><%=sfVO.getRealName()%></td>
		<td><%=sfVO.getSupportFormCategoryId()%></td>
		<td><%=sfVO.getReplyEmail()%></td>
		<td><%=sfVO.getFormTitle()%></td>
		<td><%=sfVO.getFormContext()%></td>
		<td><%=sfVO.getFormStatus()%></td>
		<td><%=sfVO.getFormSource()%></td>
		<td><%=sfVO.getFormSubmitter()%></td>
		<td><%=sfVO.getFormResponder()%></td>
		<fmt:formatDate value="${sfVO.createdTimestamp}" pattern="yyyy-MM-dd HH:mm:ss" var="formattedTimestamp" />
		<td>${formattedTimestamp}</td>
	</tr>
</table>

</body>
</html>