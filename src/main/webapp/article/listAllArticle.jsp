<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.cooklab.article.model.ArticleVO" %>
<%@ page import="com.cooklab.article.model.ArticleService" %>
<%-- �����m�߱ĥ� EL ���g�k���� --%>

<%
	ArticleService artSvc = new ArticleService();
	List<ArticleVO> list = artSvc.getAll();
    pageContext.setAttribute("list",list);
%>


<html>
<head>
<title>�Ҧ��峹��� - listarticleArt.jsp</title>

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
		 <h3>�Ҧ��峹��� - listarticleArt.jsp</h3>
		 <h4><a href="<%= request.getContextPath() %>/article/select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">�^����</a></h4>
	</td></tr>
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
		<th>�إ߮ɶ�</th>
		<th>�̫�s��ɶ�</th>
		<th>�ާ@  </th>
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
                    <!-- �o�OBase64�Ϥ��A�ϥ�<img>������� -->
                    <img src="data:image/jpeg;base64,${artVO.articleContent}" alt="�Ϥ��y�z">
                </c:when>
                <c:otherwise>
                    <!-- �o�O�奻�A������� -->
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
			     <input type="submit" value="�ק�">
			     <input type="hidden" name="articleNo"  value="${artVO.articleNo}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/ArticleServlet" style="margin-bottom: 0px;">
			     <input type="submit" value="�R��">
			     <input type="hidden" name="articleNo"  value="${artVO.articleNo}">
			     <input type="hidden" name="action" value="delete"></FORM>
			</td>
		</tr>
	</c:forEach>
</table>
<%@ include file="page2.file" %>

</body>
</html>