<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page import="java.util.*"%>
<%@ page import="com.cooklab.support_form.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
SupportFormService sfSvc = new SupportFormService();
    List<SupportFormVO> list = sfSvc.getAll();
    pageContext.setAttribute("list",list);
%>


<html>
<head>
<title>所有表單資料 - listAllEmp.jsp</title>

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
	width: 800px;
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

<h4>此頁練習採用 EL 的寫法取值:</h4>
<table id="table-1">
	<tr><td>
		 <h3>所有表單資料 - listAllSupportForm.jsp</h3>
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
	<%@ include file="page1.file" %> 
	<c:forEach var="sfVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		
		<tr>
			<td>${sfVO.formNo}</td>
			<td>${sfVO.realName}</td>
			<td>${sfVO.supportFormCategoryId}</td>
			<td>${sfVO.replyEmail}</td>
			<td>${sfVO.formTitle}</td>
			<td>${sfVO.formContext}</td> 
			<td>${sfVO.formStatus}</td>
			<td>${sfVO.formSource}</td>
			<td>${sfVO.formSubmitter}</td>
			<td>${sfVO.formResponder}</td>
		<fmt:formatDate value="${sfVO.createdTimestamp}" pattern="yyyy-MM-dd HH:mm:ss" var="formattedTimestamp" />
		<td>${formattedTimestamp}</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/SupportFormServlet" style="margin-bottom: 0px;">
			     <input type="submit" value="修改">
			     <input type="hidden" name="formNo"  value="${sfVO.formNo}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/SupportFormServlet" style="margin-bottom: 0px;">
			     <input type="submit" value="刪除">
			     <input type="hidden" name="formNo"  value="${sfVO.formNo}">
			     <input type="hidden" name="action" value="delete"></FORM>
			</td>
		</tr>
	</c:forEach>
</table>
<%@ include file="page2.file" %>

</body>
</html>