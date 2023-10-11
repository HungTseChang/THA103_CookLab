<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.cooklab.recipe.model.*"%>

<%
//��com.emp.controller.EmpServlet.java��238��s�Jreq��empVO���� (������J�榡�����~�ɪ�empVO����)
RecipeVO recipeVO = (RecipeVO) request.getAttribute("recipeVO");
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>���u��Ʒs�W - addRecipe.jsp</title>

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
		<tr>
			<td>
				<h3>���u��Ʒs�W - addRecipe.jsp</h3>
			</td>
			<td>
				<h4>
					<a href="select_page.jsp"><img src="images/tomcat.png"
						width="100" height="100" border="0">�^����</a>
				</h4>
			</td>
		</tr>
	</table>

	<h3>��Ʒs�W:</h3>

	<%-- ���~��C --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">�Эץ��H�U���~:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<FORM METHOD="post" ACTION="/com.tha103.cooklab/RecipeServlet"
		name="form1" enctype="multipart/form-data">
		<table>
			<tr>
				<td>�|���s��:</td>
				<td><input type="TEXT" name="member_id"
					value="${recipeVO.memberId}" size="45" /></td>
			</tr>
			<tr>
				<td>���ЦW��:</td>
				<td><input type="TEXT" name="recipe_name"
					value="${recipeVO.recipeName}" size="45" /></td>
			</tr>
			<tr>
				<td>�ʭ��Ϥ�:</td>
				<td><input name="cover_image" type="file"
					value="${recipeVO.coverImage}" size="45"></td>
			</tr>
			<tr>
				<td>²��:</td>
				<td><input type="TEXT" name="introduction"
					value="${recipeVO.introduction}" size="45" /></td>
			</tr>
			<tr>
				<td>�ɥR����:</td>
				<td><input type="TEXT" name="additional_explanation"
					value="${recipeVO.additionalExplanation}" size="45" /></td>
			</tr>
			<tr>
				<td>�a��:</td>
				<td><input type="TEXT" name="region" value="${recipeVO.region}"
					size="45" /></td>
			</tr>
			<tr>
				<td>���Ъ��A:</td>
				<td><input type="TEXT" name="recipe_status"
					value="${recipeVO.recipeStatus}" size="45" /></td>
			</tr>
			<tr>
				<td>���|����:</td>
				<td><input type="TEXT" name="report_count"
					value="${recipeVO.reportCount}" size="45" /></td>
			</tr>
			<tr>
				<td>�s���H��:</td>
				<td><input type="TEXT" name="view_count"
					value="${recipeVO.viewCount}" size="45" /></td>
			</tr>
			<tr>
				<td>���Х��q:</td>
				<td><input type="TEXT" name="recipe_quantity"
					value="${recipeVO.recipeQuantity}" size="45" /></td>
			</tr>

			<%-- 	<jsp:useBean id="deptSvc" scope="page" class="com.dept.model.DeptService" /> --%>
			<!-- 	<tr> -->
			<!-- 		<td>����:<font color=red><b>*</b></font></td> -->
			<!-- 		<td><select size="1" name="deptno"> -->
			<%-- 			<c:forEach var="deptVO" items="${deptSvc.all}"> --%>
			<%-- 				<option value="${deptVO.deptno}" ${(empVO.deptno==deptVO.deptno)? 'selected':'' } >${deptVO.dname} --%>
			<%-- 			</c:forEach> --%>
			<!-- 		</select></td> -->
			<!-- 	</tr> -->

		</table>
		<br> <input type="hidden" name="action" value="insert"> <input
			type="submit" value="�e�X�s�W">
	</FORM>

</body>




</html>