<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.cooklab.product.model.*"%>
<%-- �����Ƚm�߱ĥ� Script ���g�k���� --%>

<%
ProductVO productVO = (ProductVO) request.getAttribute("productVO"); //EmpServlet.java(Concroller), �s�Jreq��empVO����
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
					<a href="<%= request.getContextPath() %>/product/select_page.jsp"><img src="<%= request.getContextPath() %>/product/images/back1.gif"
						width="100" height="32" border="0">�^����</a>
				</h4>
			</td>
		</tr>
	</table>

	<table>
		<tr>
			<th>�ӫ~�s��</th>
			<th>�ӫ~�W��</th>
			<th>�ӫ~�Ϥ�</th>
			<th>�W�[�ƶq</th>
			<th>�ӫ~�y�z</th>
			<th>�ӫ~²��</th>
			<th>�ӫ~���</th>
			<th>�U�[�ɶ�</th>
			<th>�W�[�ɶ�</th>
			<th>�w�s�ƶq</th>
			<th>���������s��</th>
			<th>�p������s��</th>
			<th>�j�M����</th>
		</tr>
		<tr>
			<td>${productVO.productNo}</td>
			<td>${productVO.productName}</td>
			<td><img style="max-width: 200px; max-height: 200px;" src="<%= request.getContextPath() %>/ProductImgServlet?productNo=${productVO.productNo}"></td>
			<td>${productVO.saleQty}</td>
			<td>${productVO.productDec}</td>
			<td>${productVO.productIntroduction}</td>
			<td>${productVO.productPrice}</td>
			<td>${productVO.offsaleTime}</td>
			<td>${productVO.shelfTime}</td>
			<td>${productVO.storageQty}</td>
			<td>${productVO.ingredientCategoryNo}</td>
			<td>${productVO.kitchenwareCategoryNo}</td>
			<td>${productVO.searchCount}</td>
		</tr>
	</table>

</body>
</html>