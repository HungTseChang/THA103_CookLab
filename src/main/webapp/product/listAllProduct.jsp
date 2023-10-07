<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.cooklab.product.model.*"%>
<%-- �����m�߱ĥ� EL ���g�k���� --%>

<%
	ProductService productSvc = new ProductService();
    List<ProductVO> list = productSvc.getAll();
    pageContext.setAttribute("list",list);
%>


<html>
<head>
<title>�Ҧ����u��� - listAllEmp.jsp</title>

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

<h4>�����m�߱ĥ� EL ���g�k����:</h4>
<table id="table-1">
	<tr><td>
		 <h3>�Ҧ����u��� - listAllEmp.jsp</h3>
		 <h4><a href="<%= request.getContextPath() %>/product/select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">�^����</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>�ӫ~�s��</th>
		<th>�ӫ~�Ϥ�</th>
		<th>�ӫ~�W��</th>
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
		<th>�ק�</th>
		<th>�R��</th>
	</tr>
	<%@ include file="page1.file" %> 
	<c:forEach var="productVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		
		<tr>
			<td>${productVO.productNo}</td>
			<td><img style="max-width: 200px; max-height: 200px;" src="<%= request.getContextPath() %>/ProductImgServlet?productNo=${productVO.productNo}"></td>
			<td>${productVO.productName}</td>
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
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/ProductServlet" style="margin-bottom: 0px;">
			     <input type="submit" value="�ק�">
			     <input type="hidden" name="productNo"  value="${productVO.productNo}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/ProductServlet" style="margin-bottom: 0px;">
			     <input type="submit" value="�R��">
			     <input type="hidden" name="productNo"  value="${productVO.productNo}">
			     <input type="hidden" name="action" value="delete"></FORM>
			</td>
		</tr>
	</c:forEach>
</table>
<%@ include file="page2.file" %>

</body>
</html>