<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.cooklab.promo_code.model.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
PromoCodeVO pcVO = (PromoCodeVO) request.getAttribute("pcVO"); 
%>

<html>
<head>
<title>表單資料 - listOnePromoCode.jsp</title>

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

<h4>此頁暫練習採用 Script 的寫法取值:</h4>
<table id="table-1">
	<tr><td>
		 <h3>表單資料 - listOnePromoCode.jsp</h3>
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
		<th>表單建立時間</th>
	</tr>
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
	</tr>
</table>

</body>
</html>