<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.cooklab.promo_code.model.*"%>

<% 
PromoCodeVO pcVO = (PromoCodeVO) request.getAttribute("pcVO");
%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>�u�f�X�ק� - update_promoCode_input.jsp</title>

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
	<tr><td>
		 <h3>�u�f�X�ק� - update_promocode_input.jsp</h3>
		 <h4><a href="/THA103_CookLab/promocode/select_page.jsp">�^����</a></h4>
	</td></tr>
</table>

<h3>��ƭק�:</h3>

<%-- ���~��C --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">�Эץ��H�U���~:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="/THA103_CookLab/PromoCodeServlet" name="form1">
<table>
	<tr>
		<td>�u�f�X�s��:<font color=red><b>*</b></font></td>
		<td><%=pcVO.getPromoCodeNo()%></td>
	</tr>
	<tr>
		<td>�u�f�X�Ǹ�:</td>
				<td><input type="TEXT" name="promocodeserialnumber"
					value="<%=(pcVO == null) ? "" : pcVO.getPromoCodeSerialNumber()%>"
					size="45" /></td>
	</tr>
	<tr>
		<td>�_�l�ɶ�:</td>
				<td><input type="DATE" name="starttime"
					value="<%=(pcVO == null) ? "" : pcVO.getStartTime()%>"
					size="45" /></td>
	</tr>
	<tr>
		<td>�����ɶ�:</td>
				<td><input type="DATE" name="endtime"
					value="<%=(pcVO == null) ? "" : pcVO.getEndTime()%>" size="45" /></td>
	</tr>
	<tr>
		<td>�ʤ��������B:</td>
				<td><input type="TEXT" name="percentagediscountamount"
					value="<%=(pcVO == null) ? "" : pcVO.getPercentageDiscountAmount()%>" size="45" /></td>
	</tr>
	<tr>
		<td>�T�w������B:</td>
				<td><input type="TEXT" name="fixeddiscountamount"
					value="<%=(pcVO == null) ? "" : pcVO.getFixedDiscountAmount()%>"
					size="45" /></td>
	</tr>
	<tr>
		<td>�i�ϥΦ���:</td>
				<td><input type="TEXT" name="usagesallowed"
					value="<%=(pcVO == null) ? "": pcVO.getUsagesAllowed()%>" size="45" /></td>
	</tr>
	<tr>
		<td>�̧C���O���e:</td>
				<td><input type="TEXT" name="minimumconsumption"
					value="<%=(pcVO == null) ? "" : pcVO.getMinimumConsumption()%>" size="45" /></td>
	</tr>
	<!--  
	<tr>
		<td>���إߪ�:</td>
		<td><input type="TEXT" name="formSubmitter"  value="<%=pcVO.getFormSubmitter()%>" size="45"/></td>
	</tr>
	<tr>
		<td>���B�z��:</td>
		<td><input type="TEXT" name="formResponder"  value="<%=pcVO.getFormResponder()%>" size="45"/></td>
	</tr>
	-->
	<tr>
		<td>���إ߮ɶ�:</td>
				<td><input type="TEXT" name="createdTimestamp"  value="<%=pcVO.getCreatedTimestamp()%>" size="45"
				style="border:none; outline: none;" readonly
				/></td>
	</tr>

</table>
<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="formNo" value="<%=pcVO.getPromoCodeNo()%>">
<input type="submit" value="�e�X�ק�"></FORM>
</body>

</html>