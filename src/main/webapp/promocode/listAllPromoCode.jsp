<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page import="java.util.*"%>
<%@ page import="com.cooklab.promo_code.model.*"%>
<%-- �����m�߱ĥ� EL ���g�k���� --%>

<%
	PromoCodeService pcSvc = new PromoCodeService();
    List<PromoCodeVO> list = pcSvc.getAll();
    pageContext.setAttribute("list",list);
%>


<html>
<head>
<title>�Ҧ������ - listAllPromoCode.jsp</title>

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
		 <h3>�Ҧ������ - listAllPromoCode.jsp</h3>
		 <h4><a href="/THA103_CookLab/promocode/select_page.jsp">�^����</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>�u�f�X�s��</th>
		<th>�u�f�X�Ǹ�</th>
		<th>�_�l�ɶ�</th>
		<th>�����ɶ�</th>
		<th>�ʤ��������B</th>
		<th>�T�w������B</th>
		<th>�i�ϥΦ���</th>
		<th>�̧C���O���e</th>
		
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
			     <input type="submit" value="�ק�">
			     <input type="hidden" name="promocodeno"  value="${pcVO.promoCodeNo}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/PromoCodeServlet" style="margin-bottom: 0px;">
			     <input type="submit" value="�R��">
			     <input type="hidden" name="promocodeno"  value="${pcVO.promoCodemNo}">
			     <input type="hidden" name="action" value="delete"></FORM>
			</td>
		</tr>
	</c:forEach>
</table>
<%@ include file="page2.file" %>

</body>
</html>