<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.cooklab.members.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
    MembersService membersSvc = new MembersService();
    List<MembersVO> list = membersSvc.getAll();
    pageContext.setAttribute("list",list);
%>


<html>
<head>
<title>所有會員資料 - listAllMembers.jsp</title>

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

<h4>此頁練習採用 EL 的寫法取值:</h4>
<table id="table-1">
	<tr><td>
		 <h3>所有會員資料 - listAllMembers.jsp</h3>
		 <h4><a href="frontstage/members/select_page.jsp">
		 <img src="frontstage/members/images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
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
		
		<th>修改</th>
		<th>刪除</th>
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
			     <input type="submit" value="修改">
			     <input type="hidden" name="memberId"  value="${membersVO.memberId}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/MembersServlet" style="margin-bottom: 0px;">
			     <input type="submit" value="刪除">
			     <input type="hidden" name="memberId"  value="${membersVO.memberId}">
			     <input type="hidden" name="action" value="delete"></FORM>
			</td>
		</tr>
	</c:forEach>
</table>
<%@ include file="page2.file" %>

</body>
</html>