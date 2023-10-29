<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.cooklab.support_form.model.*"%>

<% 
SupportFormVO sfVO = (SupportFormVO) request.getAttribute("sfVO");
%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>����ƭק� - update_supportform_input.jsp</title>

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
		 <h3>����ƭק� - update_emp_input.jsp</h3>
		 <h4><a href="/THA103_CookLab/supportform/select_page.jsp">�^����</a></h4>
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

<FORM METHOD="post" ACTION="/THA103_CookLab/SupportFormServlet" name="form1">
<table>
	<tr>
		<td>���s��:<font color=red><b>*</b></font></td>
		<td><%=sfVO.getFormNo()%></td>
	</tr>
	<tr>
		<td>�Ȥ�m�W:</td>
		<td><input type="TEXT" name="realName" value="<%=sfVO.getRealName()%>" size="45"/></td>
	</tr>
	<tr>
		<td>���D���O:</td>
		<td><input type="TEXT" name="supportFormCategoryId"   value="<%=sfVO.getSupportFormCategoryId()%>" size="45"/></td>
	</tr>
	<tr>
		<td>�^�ЫH�c:</td>
		<td><input type="TEXT" name="replyEmail"   value="<%=sfVO.getReplyEmail()%>" size="45"/></td>
	</tr>
	<tr>
		<td>�������D:</td>
		<td><input type="TEXT" name="formTitle"  value="<%=sfVO.getFormTitle()%>" size="45"/></td>
	</tr>
	<tr>
		<td>��������:</td>
		<td><textarea name="formContext"  ><%=sfVO.getFormContext()%></textarea></td>
	</tr>
	<tr>
		<td>��檬�A:</td>
				<td><input type="TEXT" name="formStatus"  value="<%=sfVO.getFormStatus()%>" size="45"/></td>
	</tr>
	<tr>
		<td>���ӷ�:</td>
		<td><input type="TEXT" name="formSource"  value="<%=sfVO.getFormSource()%>" size="45"/></td>
	</tr>
	<tr>
		<td>���إߪ�:</td>
		<td><input type="TEXT" name="formSubmitter"  value="<%=sfVO.getFormSubmitter()%>" size="45"/></td>
	</tr>
	<tr>
		<td>���B�z��:</td>
		<td><input type="TEXT" name="formResponder"  value="<%=sfVO.getFormResponder()%>" size="45"/></td>
	</tr>
	<tr>
		<td>���إ߮ɶ�:</td>
				<td><input type="TEXT" name="createdTimestamp"  value="<%=sfVO.getCreatedTimestamp()%>" size="45"
				style="border:none; outline: none;" readonly
				/></td>
	</tr>

</table>
<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="formNo" value="<%=sfVO.getFormNo()%>">
<input type="submit" value="�e�X�ק�"></FORM>
</body>

</html>