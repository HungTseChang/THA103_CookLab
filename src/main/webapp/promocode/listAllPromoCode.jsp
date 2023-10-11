<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page import="java.util.*"%>
<%@ page import="com.cooklab.promo_code.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
	PromoCodeService pcSvc = new PromoCodeService();
    List<PromoCodeVO> list = pcSvc.getAll();
    pageContext.setAttribute("list",list);
%>


<html>
<head>
<title>所有表單資料 - listAllPromoCode.jsp</title>

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
		 <h3>所有表單資料 - listAllPromoCode.jsp</h3>
		 <h4><a href="/THA103_CookLab/promocode/select_page.jsp">回首頁</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>優惠碼編號</th>
		<th>優惠碼序號</th>
		<th>起始時間</th>
		<th>結束時間</th>
		<th>百分比折價金額</th>
		<th>固定折價金額</th>
		<th>可使用次數</th>
		<th>最低消費門檻</th>
		
	</tr>
	<%@ include file="page1.file" %> 
	<c:forEach var="sfVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		
		<tr>
			<td>${pcVO.promoCodeNo}</td>
			<td>${pcVO.promoCodeSerialNumber}</td>
			<td>${pcVO.startTime}</td>
			<td>${pcVO.endTime}</td>
			<td>${pcVO.percentageDiscountAmount}</td>
			<td>${pcVO.fixedDiscountAmount}</td> 
			<td>${pcVO.usagesAllowed}</td>
			<td>${pcVO.minimumConsumption}</td>
			
		<fmt:formatDate value="${pcVO.createdTimestamp}" pattern="yyyy-MM-dd HH:mm:ss" var="formattedTimestamp" />
		<td>${formattedTimestamp}</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/PromoCodeServlet" style="margin-bottom: 0px;">
			     <input type="submit" value="修改">
			     <input type="hidden" name="promocodeno"  value="${pcVO.promoCodeNo}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/PromoCodeServlet" style="margin-bottom: 0px;">
			     <input type="submit" value="刪除">
			     <input type="hidden" name="promocodeno"  value="${pcVO.promoCodemNo}">
			     <input type="hidden" name="action" value="delete"></FORM>
			</td>
		</tr>
	</c:forEach>
</table>
<%@ include file="page2.file" %>

</body>
</html>