<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.cooklab.article.model.ArticleVO" %>
<%@ page import="com.cooklab.article.model.ArticleService" %>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
	ArticleService artSvc = new ArticleService();
	List<ArticleVO> list = artSvc.getAll();
    pageContext.setAttribute("list",list);
%>


<html>
<head>
<title>所有文章資料 - listarticleArt.jsp</title>

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
	<tr><td>
		 <h3>所有文章資料 - listarticleArt.jsp</h3>
		 <h4><a href="<%= request.getContextPath() %>/article/select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>文章編號</th>
		<th>文章分類</th>
		<th>文章標題</th>
		<th>會員編號</th>
		<th>文章狀態</th>
		<th>文章內文</th>
		<th>回文數量</th>
		<th>點擊數量</th>
		<th>建立時間</th>
		<th>最後編輯時間</th>
		<th>操作  </th>
	</tr>
	<%@ include file="page1.file" %> 
	<c:forEach var="artVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		
		<tr>
			<td>${artVO.articleNo}</td>
			<td>${artVO.articleCategory}</td>
			<td>${artVO.articleTitle}</td>
			<td>${artVO.memberId}</td>
			<td>${artVO.articleStatus}</td>
			
			<td>
            <c:choose>
                <c:when test="${artVO.articleContent.startsWith('/9j/4AAQSkZJRgABAQEAZABkAAD/2wBDAAMCAgMCA')}">
                    <!-- 這是Base64圖片，使用<img>元素顯示 -->
                    <img src="data:image/jpeg;base64,${artVO.articleContent}" alt="圖片描述">
                </c:when>
                <c:otherwise>
                    <!-- 這是文本，直接顯示 -->
                    ${artVO.articleContent}
                </c:otherwise>
            </c:choose>
            </td>
			<td>${artVO.articleCount}</td>
			<td>${artVO.viewCount}</td>
			<td>${artVO.createdTimestamp}</td>
			<td><fmt:formatDate value="${artVO.lastEditTimestamp}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
			
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/ArticleServlet" style="margin-bottom: 0px;">
			     <input type="submit" value="修改">
			     <input type="hidden" name="articleNo"  value="${artVO.articleNo}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/ArticleServlet" style="margin-bottom: 0px;">
			     <input type="submit" value="刪除">
			     <input type="hidden" name="articleNo"  value="${artVO.articleNo}">
			     <input type="hidden" name="action" value="delete"></FORM>
			</td>
		</tr>
	</c:forEach>
</table>
<%@ include file="page2.file" %>

</body>
</html>