<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.cooklab.recipe.model.*"%>

<%
//見com.emp.controller.EmpServlet.java第163行存入req的empVO物件 (此為從資料庫取出的empVO, 也可以是輸入格式有錯誤時的empVO物件)
RecipeVO recipeVO = (RecipeVO) request.getAttribute("recipeVO");
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>食譜資料修改 - update_recipe_input.jsp</title>

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
				<h3>食譜資料修改 - update_recipe_input.jsp</h3>
				<h4>
					<a href="/com.tha103.cooklab/recipe/select_page.jsp"><img
						src="/com.tha103.cooklab/recipe/images/back1.gif" width="100"
						height="32" border="0">回首頁</a>
				</h4>
			</td>
		</tr>
	</table>

	<h3>資料修改:</h3>

	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
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
				<td>食譜編號:</td>
				<td><input type="TEXT" name="recipe_no"
					value="${recipeVO.recipeNo}" size="45" /></td>
			</tr>
			<tr>
				<td>會員編號:</td>
				<td><input type="TEXT" name="member_id"
					value="${recipeVO.memberId}" size="45" /></td>
			</tr>
			<tr>
				<td>食譜名稱:</td>
				<td><input type="TEXT" name="recipe_name"
					value="${recipeVO.recipeName}" size="45" /></td>
			</tr>
			<tr>
				<td>封面圖片:</td>
				<td><input name="cover_image" type="file"
					value="${recipeVO.coverImage}" size="45"></td>
			</tr>
			<tr>
				<td>簡介:</td>
				<td><input type="TEXT" name="introduction"
					value="${recipeVO.introduction}" size="45" /></td>
			</tr>
			<tr>
				<td>補充說明:</td>
				<td><input type="TEXT" name="additional_explanation"
					value="${recipeVO.additionalExplanation}" size="45" /></td>
			</tr>
			<tr>
				<td>地區:</td>
				<td><input type="TEXT" name="region" value="${recipeVO.region}"
					size="45" /></td>
			</tr>
			<tr>
				<td>食譜狀態:</td>
				<td><input type="TEXT" name="recipe_status"
					value="${recipeVO.recipeStatus}" size="45" /></td>
			</tr>
			<tr>
				<td>檢舉次數:</td>
				<td><input type="TEXT" name="report_count"
					value="${recipeVO.reportCount}" size="45" /></td>
			</tr>
			<tr>
				<td>瀏覽人次:</td>
				<td><input type="TEXT" name="view_count"
					value="${recipeVO.viewCount}" size="45" /></td>
			</tr>
			<tr>
				<td>食譜份量:</td>
				<td><input type="TEXT" name="recipe_quantity"
					value="${recipeVO.recipeQuantity}" size="45" /></td>
			</tr>

			<%-- 	<jsp:useBean id="deptSvc" scope="page" class="com.dept.model.DeptService" /> --%>
			<!-- 	<tr> -->
			<!-- 		<td>部門:<font color=red><b>*</b></font></td> -->
			<!-- 		<td><select size="1" name="deptno"> -->
			<%-- 			<c:forEach var="deptVO" items="${deptSvc.all}"> --%>
			<%-- 				<option value="${deptVO.deptno}" ${(empVO.deptno==deptVO.deptno)? 'selected':'' } >${deptVO.dname} --%>
			<%-- 			</c:forEach> --%>
			<!-- 		</select></td> -->
			<!-- 	</tr> -->

		</table>
		<br> <input type="hidden" name="action" value="update"> <input
			type="hidden" name="recipe_no" value="<%=recipeVO.getRecipeNo()%>">
		<input type="submit" value="送出修改">
	</FORM>


</body>


</html>