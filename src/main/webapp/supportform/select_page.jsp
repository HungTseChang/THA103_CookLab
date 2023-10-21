<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>客服表單測試頁面</title>

<style>
  table#table-1 {
	width: 450px;
	background-color: #CCCCFF;
	margin-top: 5px;
	margin-bottom: 10px;
    border: 3px ridge Gray;
    height: 80px;
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

</head>
<body bgcolor='white'>

<table id="table-1">
   <tr><td><h3>客服表單測試頁面</h3><h4>( MVC )</h4></td></tr>
</table>

<p>此為客服表單測試首頁</p>

<h3>資料查詢:</h3>
	
<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
	    <c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<ul>
  <li><a href='listAllSupportForm.jsp'>清單</a> 所有客服表單  <br><br></li>
  
  
  <li>
    <FORM METHOD="post" ACTION="/THA103_CookLab/SupportFormServlet" >
        <b>輸入表單編號 (如1):</b>
        <input type="text" name="formNo">
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="送出">
    </FORM>
  </li>

  <jsp:useBean id="sfSvc" scope="page" class="com.cooklab.support_form.model.SupportFormJDBCService" />
   
  <li>
     <FORM METHOD="post" ACTION="/THA103_CookLab/SupportFormServlet" >
       <b>選擇表單編號:</b>
       <select size="1" name="formNo">
         <c:forEach var="sfVO" items="${sfSvc.all}" > 
          <option value="${sfVO.formNo}">${sfVO.formNo}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
    </FORM>
  </li>
  
</ul>


<h3>表單管理</h3>

<ul>
  <li><a href='addSupportForm.jsp'>新增</a> 新客服表單</li>
</ul>

</body>
</html>