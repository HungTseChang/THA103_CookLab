<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.cooklab.members.model.*"%>
<%-- �����m�߱ĥ� EL ���g�k���� --%>

<%
    MembersService membersSvc = new MembersService();
    List<MembersVO> list = membersSvc.getAll();
    pageContext.setAttribute("list",list);
%>


<html>
<head>
<title>�Ҧ��|����� - listAllMembers.jsp</title>

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
	width: 1250px;
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
		 <h3>�Ҧ��|����� - listAllMembers.jsp</h3>
		 <h4><a href="frontstage/members/select_page.jsp">
		 <img src="frontstage/members/images/back1.gif" width="100" height="32" border="0">�^����</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>�|���s��</th>
		<th>�|���b��</th>
		<th>�|���K�X</th>
		<th>²��</th>
		<th>���</th>
		<th>�q�l�H�c</th>
		<th>�ͤ�</th>
		<th>�q�T�a�}</th>
		<th>��O</th>
		<th>�|�����A</th>
		<th>�ʺ�</th>
		<th>�ʧO</th>
		<th>�|���Y��</th>
		
		<th>�s�W�ɶ�</th>
		<th>�̫�s��ɶ�</th>
		
		<th>�ק�</th>
		<th>�R��</th>
	</tr>
	<%@ include file="page1.file" %> 
	<c:forEach var="membersVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		
		<tr>
			<td>${membersVO.memberId}</td>
			<td>${membersVO.memberAccount}</td>
			<td>${membersVO.memberPassword}</td>
			<td>${membersVO.memberIntroduce}</td>
			<td>${membersVO.memberCellphone}</td>
			<td>${membersVO.memberMail}</td> 
			<td>${membersVO.memberDate}</td>
			<td>${membersVO.memberAddress}</td>
			<td>${membersVO.memberCountry}</td>
			<td>${membersVO.memberStatus}</td>
			<td>${membersVO.memberNickname}</td>
			<td>${membersVO.memberGender}</td>
			 <td><img style="max-width: 200px; max-height: 200px;" 
			 src="/com.tha103.cooklab/MembersImgServlet?memberId=${membersVO.memberId}"></td> 
			<td>${membersVO.credcreatedTimestamp}</td>
			<td>${membersVO.lastEditTimestamp}</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/MembersServlet" style="margin-bottom: 0px;">
			     <input type="submit" value="�ק�">
			     <input type="hidden" name="memberId"  value="${membersVO.memberId}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/MembersServlet" style="margin-bottom: 0px;">
			     <input type="submit" value="�R��">
			     <input type="hidden" name="memberId"  value="${membersVO.memberId}">
			     <input type="hidden" name="action" value="delete"></FORM>
			</td>
		</tr>
	</c:forEach>
</table>
<%@ include file="page2.file" %>

</body>
</html>