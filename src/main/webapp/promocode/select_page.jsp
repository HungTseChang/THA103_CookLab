<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>優惠碼頁面</title>

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
   <tr><td><h3>優惠碼頁面</h3><h4>( MVC )</h4></td></tr>
</table>

<p>此為優惠碼頁面首頁</p>

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
  <li><a href='listAllPromoCode.jsp'>清單</a> 所有優惠碼  <br><br></li>
  
  
  <li>
    <FORM METHOD="post" ACTION="/THA103_CookLab/PromoCodeServlet" >
        <b>輸入優惠碼編號 (如1):</b>
        <input type="text" name="promocodeno">
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="送出">
    </FORM>
  </li>

  <jsp:useBean id="pcSvc" scope="page" class="com.cooklab.promo_code.model.PromoCodeService" />
   
  <li>
     <FORM METHOD="post" ACTION="/THA103_CookLab/PromoCodeServlet" >
       <b>選擇優惠碼:</b>
       <select size="1" name="promocodeno">
         <c:forEach var="pcVO" items="${pcSvc.all}" > 
          <option value="${pcVO.promocodeNo}">${pcVO.promocodeNo}
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