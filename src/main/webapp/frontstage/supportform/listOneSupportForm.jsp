<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.cooklab.support_form.model.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%-- �����Ƚm�߱ĥ� Script ���g�k���� --%>

<%
SupportFormVO sfVO = (SupportFormVO) request.getAttribute("sfVO"); 
%>

<html>
<head>
<title>����� - listOneSupportForm.jsp</title>

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

<h4>�����Ƚm�߱ĥ� Script ���g�k����:</h4>
<table id="table-1">
	<tr><td>
		 <h3>����� - listOneSupportForm.jsp</h3>
		 <h4><a href="/THA103_CookLab/supportform/select_page.jsp">�^����</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>���s��</th>
		<th>�Ȥ�m�W</th>
		<th>���D���O</th>
		<th>�^�ЫH�c</th>
		<th>�������D</th>
		<th>��椺��</th>
		<th>�B�z���A</th>
		<th>�B�z�ӷ�</th>
		<th>���إߪ�</th>
		<th>���B�z��</th>
		<th>���إ߮ɶ�</th>
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