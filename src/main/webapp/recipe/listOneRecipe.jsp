<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.cooklab.recipe.model.*"%>
<%-- �����Ƚm�߱ĥ� Script ���g�k���� --%>

<%
RecipeVO recipeVO = (RecipeVO) request.getAttribute("recipeVO"); //EmpServlet.java(Concroller), �s�Jreq��empVO����
%>
<html>
<head>
<title>���u��� - listOneEmp.jsp</title>

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
				<h3>���u��� - ListOneEmp.jsp</h3>
				<h4>
					<a href="recipe/select_page.jsp"><img src="recipe/images/back1.gif"
						width="100" height="32" border="0">�^����</a>
				</h4>
			</td>
		</tr>
	</table>

	<table>
		<tr>
			<th>���нs��</th>
			<th>�|���s��</th>
			<th>���ЦW��</th>
			<th>�ʭ��Ϥ�</th>
			<th>²��</th>
			<th>�ɥR����</th>
			<th>�a��</th>
			<th>���Ъ��A</th>
			<th>���|����</th>
			<th>�s���H��</th>
			<th>���Х��q</th>
			<th>�̫�s��ɶ�</th>
			<th>�إ߮ɶ�</th>
		</tr>
		<tr>
			<td>${recipeVO.recipeNo}</td>
			<td>${recipeVO.memberId}</td>
			<td>${recipeVO.recipeName}</td>
			<td>${recipeVO.coverImage}</td>
			<td>${recipeVO.introduction}</td>
			<td>${recipeVO.additionalExplanation}</td>
			<td>${recipeVO.region}</td>
			<td>${recipeVO.recipeStatus}</td>
			<td>${recipeVO.reportCount}</td>
			<td>${recipeVO.viewCount}</td>
			<td>${recipeVO.recipeQuantity}</td>
			<td>${recipeVO.lastEditTimestamp}</td>
			<td>${recipeVO.createdTimestamp}</td>
		</tr>
	</table>

</body>
</html>