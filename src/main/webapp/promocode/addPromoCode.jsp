<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.cooklab.promo_code.model.*"%>

<%
PromoCodeVO pcVO = (PromoCodeVO) request.getAttribute("pcVO");
%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>表單資料新增 - addPromoCode.jsp</title>

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
				<h3>表單資料新增 - addPromoCode.jsp</h3>
			</td>
			<td>
				<h4>
					<a href="select_page.jsp">回首頁</a>
				</h4>
			</td>
		</tr>
	</table>

	<h3>資料新增:</h3>

	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<FORM METHOD="post" ACTION="/THA103_CookLab/PromoCodeServlet"
		name="form1">
		<table>




			<tr>
				<td>優惠碼編號:</td>
				<td><input type="TEXT" name="promocodeno"
					value="<%=(pcVO == null) ? "" : pcVO.getPromoCodeNo()%>" size="45" /></td>
			</tr>
			<tr>
				<td>優惠碼序號:</td>
				<td><input type="TEXT" name="promocodeserialnumber"
					value="<%=(pcVO == null) ? "" : pcVO.getPromoCodeSerialNumber()%>"
					size="45" /></td>
			</tr>
			<tr>
				<td>起始時間:</td>
				<td><input type="DATE" name="starttime"
					value="<%=(pcVO == null) ? "" : pcVO.getStartTime()%>"
					size="45" /></td>
			</tr>
			<tr>
				<td>結束時間:</td>
				<td><input type="DATE" name="endtime"
					value="<%=(pcVO == null) ? "" : pcVO.getEndTime()%>" size="45" /></td>
			</tr>
			<tr>
				<td>百分比折價金額:</td>
				<td><input type="TEXT" name="percentagediscountamount"
					value="<%=(pcVO == null) ? "" : pcVO.getPercentageDiscountAmount()%>" size="45" /></td>
			</tr>
			<tr>
				<td>固定折價金額:</td>
				<td><input type="TEXT" name="fixeddiscountamount"
					value="<%=(pcVO == null) ? "" : pcVO.getFixedDiscountAmount()%>"
					size="45" /></td>
			</tr>
			<tr>
				<td>可使用次數:</td>
				<td><input type="TEXT" name="usagesallowed"
					value="<%=(pcVO == null) ? "": pcVO.getUsagesAllowed()%>" size="45" /></td>
			</tr>
			<tr>
				<td>最低消費門檻:</td>
				<td><input type="TEXT" name="minimumconsumption"
					value="<%=(pcVO == null) ? "" : pcVO.getMinimumConsumption()%>" size="45" /></td>
			</tr>


		</table>
		<br> <input type="hidden" name="action" value="insert"> <input
			type="submit" value="送出新增">
	</FORM>

</body>
</html>