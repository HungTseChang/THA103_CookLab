<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.cooklab.members.model.*"%>
<%-- �����Ƚm�߱ĥ� Script ���g�k���� --%>

<%
MembersVO memVO = (MembersVO) request.getAttribute("memVO"); //EmpServlet.java(Concroller), �s�Jreq��empVO����
%>

<html>
<head>
<title>���u��� - listOneMembers.jsp</title>

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

	<h4>�����Ƚm�߱ĥ� Script ���g�k����:</h4>
	<table id="table-1">
		<tr>
			<td>
				<h3>�|����� - ListOneMembers.jsp</h3>
				<h4>
					<a href="frontstage/members/select_page.jsp"><img
						src="frontstage/members/images/back1.gif" width="100" height="32" border="0">�^����</a>
				</h4>
			</td>
		</tr>
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
		</tr>
		<tr>
			<td><%=memVO.getMemberId()%></td>
			<td><%=memVO.getMemberAccount()%></td>
			<td><%=memVO.getMemberPassword()%></td>
			<td><%=memVO.getMemberIntroduce()%></td>
			<td><%=memVO.getMemberCellphone()%></td>
			<td><%=memVO.getMemberMail()%></td>

			<td><%=memVO.getMemberDate()%></td>
			<td><%=memVO.getMemberAddress()%></td>
			<td><%=memVO.getMemberCountry()%></td>
			<td><%=memVO.getMemberStatus()%></td>
			<td><%=memVO.getMemberNickname()%></td>
			<td><%=memVO.getMemberGender()%></td>
			 <td><img style="max-width: 200px; max-height: 200px;" 
			 src="/CookLab/MembersImgServlet?memberId=${memVO.memberId}"></td> 
			<td><%=memVO.getCredcreatedTimestamp()%></td>
			<td><%=memVO.getLastEditTimestamp()%></td>

		</tr>
	</table>

</body>
</html>