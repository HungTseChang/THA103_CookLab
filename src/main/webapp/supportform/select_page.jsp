<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>�ȪA�����խ���</title>

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
   <tr><td><h3>�ȪA�����խ���</h3><h4>( MVC )</h4></td></tr>
</table>

<p>�����ȪA�����խ���</p>

<h3>��Ƭd��:</h3>
	
<%-- ���~��C --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">�Эץ��H�U���~:</font>
	<ul>
	    <c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<ul>
  <li><a href='listAllSupportForm.jsp'>�M��</a> �Ҧ��ȪA���  <br><br></li>
  
  
  <li>
    <FORM METHOD="post" ACTION="/THA103_CookLab/SupportFormServlet" >
        <b>��J���s�� (�p1):</b>
        <input type="text" name="formNo">
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="�e�X">
    </FORM>
  </li>

  <jsp:useBean id="sfSvc" scope="page" class="com.cooklab.support_form.model.SupportFormJDBCService" />
   
  <li>
     <FORM METHOD="post" ACTION="/THA103_CookLab/SupportFormServlet" >
       <b>��ܪ��s��:</b>
       <select size="1" name="formNo">
         <c:forEach var="sfVO" items="${sfSvc.all}" > 
          <option value="${sfVO.formNo}">${sfVO.formNo}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="�e�X">
    </FORM>
  </li>
  
</ul>


<h3>���޲z</h3>

<ul>
  <li><a href='addSupportForm.jsp'>�s�W</a> �s�ȪA���</li>
</ul>

</body>
</html>