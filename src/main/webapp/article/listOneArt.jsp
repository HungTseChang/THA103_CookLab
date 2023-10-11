<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ page import="com.cooklab.article.model.*"%>
<%-- �����Ƚm�߱ĥ� Script ���g�k���� --%>

<%
ArticleVO artVO = (ArticleVO) request.getAttribute("artVO"); //EmpServlet.java(Concroller), �s�Jreq��empVO����
%>

<html>
<head>
<title>�峹��� - listOneArt.jsp</title>

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
		<tr>
			<td>
				<h3>�峹��� - listOneArt.jsp</h3>
				<h4>
					<a href="<%= request.getContextPath() %>/article/select_page.jsp"><img src="images/back1.gif"
						width="100" height="32" border="0">�^����</a>
				</h4>
			</td>
		</tr>
	</table>

	<table>
		<tr>
			<th>�峹�s��</th>
			<th>�峹����</th>
			<th>�峹���D</th>
			<th>�|���s��</th>
			<th>�峹���A</th>
			<th>�峹����</th>
			<th>�^��ƶq</th>
			<th>�I���ƶq</th>
			<th>�̫�s��ɶ�</th>
		</tr>
		<tr>
			<td>${artVO.articleNo}</td>
			<td>${artVO.articleCategory}</td>
			<td>${artVO.articleTitle}</td>
			<td>${artVO.memberId}</td>
			<td>${artVO.articleStatus}</td>
			<td>${artVO.articleContent}</td>
			<td>${artVO.articleCount}</td>
			<td>${artVO.viewCount}</td>
			<td><fmt:formatDate value="${artVO.lastEditTimestamp}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
		</tr>
	</table>

</body>
</html>