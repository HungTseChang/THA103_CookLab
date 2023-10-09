<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.Date" %>
<%@ page import="com.cooklab.article_report.model.*"%>
<%-- �����Ƚm�߱ĥ� Script ���g�k���� --%>

<%
ArticleReportVO ArticleReportVO = (ArticleReportVO) request.getAttribute("ArticleReportVO"); //EmpServlet.java(Concroller), �s�Jreq��empVO����
Integer number =ArticleReportVO.getArticleReportNo();
%>

<html>
<head>
<title>�峹��� - listOneArticleReport</title>

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
				<h3>�峹��� - listOneArticleReport.jsp</h3>
				<h4>
					<a href="/com.tha103.cooklab/articlereport/select_page.jsp"><img src="${pageContext.request.contextPath}/articlereport/images/back1.gif"
						width="100" height="32" border="0">�^����</a>
				</h4>
			</td>
		</tr>
	</table>

	<table id="table1">
	<thead>
		<tr>
			<th>���|�s��</th>
			<th>���|��Id</th>
			<th>���|�̼ʺ�</th>
			<th>�峹�s��</th>
			<th>�峹���D</th>
			<th>���|�z��</th>
			<th>���|���A</th>
			<th>���|�ɶ�</th>
		</tr>
		</thead>
		<tbody>
		</tbody>

	</table>

<script src="${pageContext.request.contextPath}/dashboard/assets\vendors\jquery-3.7.1.min.js"></script>
 <script>
    document.addEventListener("DOMContentLoaded", function () {
    var aa = '${ArticleReportVO}';
    var nickname = "${nickname}";
    var title = "${title}"
  	let status ={
			  0:" <span class='badge bg-success'>�w�B�z</span>",
			  1:" <span class='badge bg-danger'>���B�z</span>",
	  }

  	  let text = "";
	  text += "<tr>";
	  text += "<td class='wcc'>"+ '${ArticleReportVO.getArticleReportNo()}'+"</td>";
	  text +=" <td class='wcc'>"+'${ArticleReportVO.getArticleNo()}'+"</td>";
	  text +=" <td>"+nickname+"</td>";
	  text +=" <td >"+'${ArticleReportVO.getReporterId()}'+"</td>";
	  text +=" <td>"+title+"</td>";
	  text +=" <td>"+'${ArticleReportVO.getReportingReason()}'+"</td>";
	  text +=" <td>"+status['${ArticleReportVO.getReportingStatus()}']+"</td>";
// 	  text +="<td>"+"${ArticleReportVO.createdTimestamp}" +"</td>";
	  text += "<td><fmt:formatDate value='${ArticleReportVO.createdTimestamp}' pattern='yyyy-MM-dd HH:mm:ss'/></td>";
// 	  text += "<td><fmt:formatDate value='${ArticleReportVO.createdTimestamp}' pattern='yyyy-MM-dd HH:mm:ss'/></td>";
	  text +=`
			<td>
		  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/ArticleReportServlet" style="margin-bottom: 0px;">
		     <input type="submit" value="�ק�">
		     <input type="hidden" name="articleReportNo"  value=`;
		    text += <%=number%>;
		    text +=` >
		     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
		</td>`
      text += `<td><FORM METHOD="post" ACTION="<%=request.getContextPath()%>/ArticleReportServlet" style="margin-bottom: 0px;">
		     <input type="submit" value="�R��">
		     <input type="hidden" name="articleReportNo"  value=`;
	  text += <%=number%>;
	  text +=`>
		     <input type="hidden" name="action" value="delete"></FORM>
		</td>
		  `;
	  text += "<tr>";
	  console.log(text);
	  $("table#table1").children("tbody").append(text);
    })
    </script>
</body>
</html>
