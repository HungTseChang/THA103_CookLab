<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>CookLab Recipe: Home</title>

<style>
table#table-1 {
	width: 450px;
	background-color: #CCCCFF;
	margin-top: 5px;
	margin-bottom: 10px;
	border: 3px ridge Gray;
	height: 80px;
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

</head>
<body bgcolor='white'>

	<table id="table-1">
		<tr>
			<td><h3>CookLab Recipe: Home</h3>
				<h4>( MVC )</h4></td>
		</tr>
	</table>

	<p>This is the Home page for CookLab Recipe: Home</p>

	<h3>��Ƭd��:</h3>

	<%-- ���~��C --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">�Эץ��H�U���~:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<ul>
		<li><a href='<%=request.getContextPath()%>/recipe/listAllRecipe.jsp'>List</a> all Recipes. <br>
		<br></li>


		<li>
			<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/RecipeServlet">
				<b>��J���нs�� (�p1):</b> <input type="text" name="recipe_no"> <input
					type="hidden" name="action" value="getOne_For_Display"> <input
					type="submit" value="�e�X">
			</FORM>
		</li>

		<jsp:useBean id="recipeSvc" scope="page"
			class="com.cooklab.recipe.model.RecipeService" />

		<li>
			<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/RecipeServlet">
				<b>��ܭ��нs��:</b> <select size="1" name="recipe_no">
					<c:forEach var="recipeVO" items="${recipeSvc.all}">
						<option value="${recipeVO.recipeNo}">${recipeVO.recipeNo}
					</c:forEach>
				</select> <input type="hidden" name="action" value="getOne_For_Display">
				<input type="submit" value="�e�X">
			</FORM>
		</li>

		<li>
			<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/RecipeServlet">
				<b>��ܭ��ЦW��:</b> <select size="1" name="recipe_no">
					<c:forEach var="recipeVO" items="${recipeSvc.all}">
						<option value="${recipeVO.recipeNo}">${recipeVO.recipeName}
					</c:forEach>
				</select> <input type="hidden" name="action" value="getOne_For_Display">
				<input type="submit" value="�e�X">
			</FORM>
		</li>
	</ul>


	<h3>���к޲z</h3>

	<ul>
		<li><a href='addRecipe.jsp'>Add</a> a new Recipe.</li>
	</ul>

</body>
</html>