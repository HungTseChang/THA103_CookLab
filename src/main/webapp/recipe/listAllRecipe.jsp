<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.cooklab.recipe.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
RecipeService reicpeSvc = new RecipeService();
List<RecipeVO> list = reicpeSvc.getAll();
pageContext.setAttribute("list", list);
%>


<html>
<head>
<title>所有食譜資料 - listAllRecipe.jsp</title>

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

	<h4>此頁練習採用 EL 的寫法取值:</h4>
	<table id="table-1">
		<tr>
			<td>
				<h3>所有食譜資料 - listAllRecipe.jsp</h3>
				<h4>
					<a href="/com.tha103.cooklab/recipe/select_page.jsp"><img src="/com.tha103.cooklab/recipe/images/back1.gif"
						width="100" height="32" border="0">回首頁</a>
				</h4>
			</td>
		</tr>
	</table>

	<table>
		<tr>
			<th>食譜編號</th>
			<th>會員編號</th>
			<th>食譜名稱</th>
			<th>封面圖片</th>
			<th>簡介</th>
			<th>補充說明</th>
			<th>地區</th>
			<th>食譜狀態</th>
			<th>檢舉次數</th>
			<th>瀏覽人次</th>
			<th>食譜份量</th>
			<th>最後編輯時間</th>
			<th>建立時間</th>
		</tr>
		<%@ include file="page1.file"%>
		<c:forEach var="recipeVO" items="${list}" begin="<%=pageIndex%>"
			end="<%=pageIndex+rowsPerPage-1%>">

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
				<td>
					<FORM METHOD="post" ACTION="/com.tha103.cooklab/RecipeServlet"
						style="margin-bottom: 0px;">
						<input type="submit" value="修改"> <input type="hidden"
							name="recipe_no" value="${recipeVO.recipeNo}"> <input
							type="hidden" name="action" value="getOne_For_Update">
					</FORM>
				</td>
				<td>
					<FORM METHOD="post" ACTION="/com.tha103.cooklab/RecipeServlet"
						style="margin-bottom: 0px;">
						<input type="submit" value="刪除"> <input type="hidden"
							name="recipe_no" value="${recipeVO.recipeNo}"> <input
							type="hidden" name="action" value="delete">
					</FORM>
				</td>
			</tr>
		</c:forEach>
	</table>
	<%@ include file="page2.file"%>

</body>
</html>