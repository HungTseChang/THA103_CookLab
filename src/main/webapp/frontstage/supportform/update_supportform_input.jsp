<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.cooklab.support_form.model.*"%>

<% 
SupportFormVO sfVO = (SupportFormVO) request.getAttribute("sfVO");
%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>表單資料修改 - update_supportform_input.jsp</title>

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
		 <h3>表格資料修改 - update_emp_input.jsp</h3>
		 <h4><a href="/THA103_CookLab/supportform/select_page.jsp">回首頁</a></h4>
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

<FORM METHOD="post" ACTION="/THA103_CookLab/SupportFormServlet" name="form1">
<table>
	<tr>
		<td>表格編號:<font color=red><b>*</b></font></td>
		<td><%=sfVO.getFormNo()%></td>
	</tr>
	<tr>
		<td>客戶姓名:</td>
		<td><input type="TEXT" name="realName" value="<%=sfVO.getRealName()%>" size="45"/></td>
	</tr>
	<tr>
		<td>問題類別:</td>
		<td><input type="TEXT" name="supportFormCategoryId"   value="<%=sfVO.getSupportFormCategoryId()%>" size="45"/></td>
	</tr>
	<tr>
		<td>回覆信箱:</td>
		<td><input type="TEXT" name="replyEmail"   value="<%=sfVO.getReplyEmail()%>" size="45"/></td>
	</tr>
	<tr>
		<td>反應標題:</td>
		<td><input type="TEXT" name="formTitle"  value="<%=sfVO.getFormTitle()%>" size="45"/></td>
	</tr>
	<tr>
		<td>反應內文:</td>
		<td><textarea name="formContext"  ><%=sfVO.getFormContext()%></textarea></td>
	</tr>
	<tr>
		<td>表單狀態:</td>
				<td><input type="TEXT" name="formStatus"  value="<%=sfVO.getFormStatus()%>" size="45"/></td>
	</tr>
	<tr>
		<td>表單來源:</td>
		<td><input type="TEXT" name="formSource"  value="<%=sfVO.getFormSource()%>" size="45"/></td>
	</tr>
	<tr>
		<td>表單建立者:</td>
		<td><input type="TEXT" name="formSubmitter"  value="<%=sfVO.getFormSubmitter()%>" size="45"/></td>
	</tr>
	<tr>
		<td>表單處理者:</td>
		<td><input type="TEXT" name="formResponder"  value="<%=sfVO.getFormResponder()%>" size="45"/></td>
	</tr>
	<tr>
		<td>表格建立時間:</td>
				<td><input type="TEXT" name="createdTimestamp"  value="<%=sfVO.getCreatedTimestamp()%>" size="45"
				style="border:none; outline: none;" readonly
				/></td>
	</tr>

</table>
<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="formNo" value="<%=sfVO.getFormNo()%>">
<input type="submit" value="送出修改"></FORM>
</body>

</html>