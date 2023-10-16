<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.cooklab.product.model.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
ProductVO productVO = (ProductVO) request.getAttribute("productVO"); //EmpServlet.java(Concroller), 存入req的empVO物件
%>

<html>
<head>
<title>員工資料 - listOneEmp.jsp</title>

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
		<tr>
			<td>
				<h3>員工資料 - ListOneEmp.jsp</h3>
				<h4>
					<a href="<%= request.getContextPath() %>/product/select_page.jsp"><img src="<%= request.getContextPath() %>/product/images/back1.gif"
						width="100" height="32" border="0">回首頁</a>
				</h4>
			</td>
		</tr>
	</table>

	<table>
		<tr>
			<th>商品編號</th>
			<th>商品名稱</th>
			<th>商品圖片</th>
			<th>上架數量</th>
			<th>商品描述</th>
			<th>商品簡介</th>
			<th>商品售價</th>
			<th>下架時間</th>
			<th>上架時間</th>
			<th>庫存數量</th>
			<th>食材種類編號</th>
			<th>廚具種類編號</th>
			<th>搜尋次數</th>
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