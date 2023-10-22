<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.cooklab.members.model.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
MembersVO memVO = (MembersVO) request.getAttribute("memVO"); //EmpServlet.java(Concroller), 存入req的empVO物件
%>

<html>
<head>
<title>員工資料 - listOneMembers.jsp</title>

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

	<h4>此頁暫練習採用 Script 的寫法取值:</h4>
	<table id="table-1">
		<tr>
			<td>
				<h3>會員資料 - ListOneMembers.jsp</h3>
				<h4>
					<a href="frontstage/members/select_page.jsp"><img
						src="frontstage/members/images/back1.gif" width="100" height="32" border="0">回首頁</a>
				</h4>
			</td>
		</tr>
	</table>

	<table>
		<tr>
			<th>會員編號</th>
			<th>會員帳號</th>
			<th>會員密碼</th>
			<th>簡介</th>
			<th>手機</th>
			<th>電子信箱</th>
			<th>生日</th>
			<th>通訊地址</th>
			<th>國別</th>
			<th>會員狀態</th>
			<th>暱稱</th>
			<th>性別</th>
			<th>會員頭像</th>
			<th>新增時間</th>
			<th>最後編輯時間</th>
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