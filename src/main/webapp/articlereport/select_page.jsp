<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>IBM Art: Home</title>

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
   <tr><td><h3>IBM Art: Home</h3><h4>( MVC )</h4></td></tr>
</table>

<p>This is the Home page for IBM Art: Home</p>

<h3>��Ƭd��:</h3>
	
<%-- ���~��C --%>
<c:if test="${not empty error}">
	<font style="color:red">�Эץ��H�U���~:</font>
	<ul>
	    <c:forEach var="message" items="${error}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<ul>
  <li><a href='${pageContext.request.contextPath}/articlereport/listAllArticleReport.jsp'>List</a> all ArticleReport.  <br><br></li>
  
  
  <li>
    <FORM METHOD="post" ACTION="${pageContext.request.contextPath}/ArticleReportServlet" >
        <b>��J�峹�s�� (�p1):</b>
        <input type="text" name="articleReportNo">  <!-- �e�X���ѼƬOname="xxx" -->
        <input type="hidden" name="action" value="getone">
        <input type="submit" value="�e�X">
    </FORM>
  </li>

  <jsp:useBean id="articleReport" scope="page" class="com.cooklab.article_report.model.ArticleReportService" />
   
  <li>
     <FORM METHOD="post" ACTION="${pageContext.request.contextPath}/ArticleReportServlet" >
       <b>������|�s��:</b>
       <select size="1" name="articleReportNo">
         <c:forEach var="articlereport" items="${articleReport.getAll()}" > 
          <option value="${articlereport.getArticleReportNo()}">${articlereport.getArticleReportNo()}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getone">
       <input type="submit" value="�e�X">
    </FORM>
  </li>
  
  <li>
     <FORM METHOD="post" ACTION="${pageContext.request.contextPath}/ArticleReportServlet" >
       <b>������|�̼ʺ�:</b>
       <select size="1" name="articleReportNo">
         <c:forEach var="articlereport" items="${articleReport.getAll()}" > 
          <option value="${articlereport.getArticleReportNo()}">${articlereport.getMembersVO().getMemberNickname()}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getone">
       <input type="submit" value="�e�X">
     </FORM>
  </li>
  
    <li>
     <FORM METHOD="post" ACTION="${pageContext.request.contextPath}/ArticleReportServlet" >
       <b>��ܤ峹�W��:</b>
       <select size="1" name="articlereportNo">
         <c:forEach var="articlereport" items="${articleReport.getAll()}" > 
          <option value="${articlereport.getArticleReportNo()}">${articlereport.getArticleVO().getArticleTitle()}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getone">
       <input type="submit" value="�e�X">
     </FORM>
  </li>
</ul>


<h3>�峹�޲z</h3>

<ul>
     <FORM METHOD="post" ACTION="${pageContext.request.contextPath}/ArticleReportServlet" >
       <input type="hidden" name="action" value="getOne_For_CreatNew">
  <li><input type="submit" value="�إ߷s���峹���|">Add</a> </li>
       </FORM>
  
</ul>

</body>
</html>