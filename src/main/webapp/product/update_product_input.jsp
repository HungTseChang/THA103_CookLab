<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.cooklab.product.model.*"%>

<%
//��com.emp.controller.EmpServlet.java��163��s�Jreq��empVO���� (�����q��Ʈw���X��empVO, �]�i�H�O��J�榡�����~�ɪ�empVO����)
ProductVO productVO = (ProductVO) request.getAttribute("productVO"); //EmpServlet.java(Concroller), �s�Jreq��empVO����
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>���u��ƭק� - update_emp_input.jsp</title>

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
	width: 600px;
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
<style>
.wide-row td {
	width: 300px;
}
</style>
</head>
<body bgcolor='white'>

	<table id="table-1">
		<tr>
			<td>
				<h3>���u��ƭק� - update_emp_input.jsp</h3>
				<h4>
					<a href="<%= request.getContextPath() %>/product/select_page.jsp"><img src="<%= request.getContextPath() %>/product/images/back1.gif"
						width="100" height="32" border="0">�^����</a>
				</h4>
			</td>
		</tr>
	</table>

	<h3>��ƭק�:</h3>

	<%-- ���~���C --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">�Эץ��H�U���~:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<FORM METHOD="post"
		ACTION="<%=request.getContextPath()%>/ProductServlet" name="form1"
		enctype="multipart/form-data">
		<table class="table2">
			<tr>
				<td class="wide-row">�ӫ~�s��:<font color=red><b>*</b></font></td>
				<td><%=productVO.getProductNo()%></td>
			</tr>
			<tr>
				<td class="wide-row">�ӫ~�m�W:</td>
				<td><input type="TEXT" name="productName"
					value="<%=productVO.getProductName()%>" size="45" /></td>
			</tr>
			<tr>
				<td class="wide-row">�W�[�ƶq:</td>
				<td><input type="TEXT" name="saleQty"
					value="<%=(productVO == null) ? "" : productVO.getSaleQty()%>"
					size="45" /></td>
			</tr>
			<tr>
				<td class="wide-row">�ӫ~�y�z:</td>
				<td><input type="TEXT" name="productDec"
					value="<%=productVO.getProductDec()%>" size="45" /></td>
			</tr>
			<tr>
				<td class="wide-row">�ӫ~²��:</td>
				<td><input type="TEXT" name="productIntroduction"
					value="<%=productVO.getProductIntroduction()%>" size="45" /></td>
			</tr>
			<tr>
				<td class="wide-row">�ӫ~���:</td>
				<td><input type="TEXT" name="productPrice"
					value="<%=(productVO == null) ? "" : productVO.getProductPrice()%>"
					size="45" /></td>
			</tr>
			<tr>
				<td class="wide-row">�U�[�ɶ�:</td>
				<td><input name="offsaleTime" id="f_date1" type="text"
					value="<%=productVO.getOffsaleTime()%>"></td>
			</tr>
			<tr>
				<td class="wide-row">�W�[�ɶ�:</td>
				<td><input name="shelfTime" id="f_date1" type="text"
					value="<%=productVO.getShelfTime()%>"></td>
			</tr>
			<tr>
				<td class="wide-row">�w�s�ƶq:</td>
				<td><input type="TEXT" name="storageQty"
					value="<%=(productVO == null) ? "" : productVO.getStorageQty()%>"
					size="45" /></td>
			</tr>
			<jsp:useBean id="ingredientSvc" scope="page"
				class="com.cooklab.ingredient_category.model.IngredientService" />
			<tr>
				<td>��������:<font color=red><b>*</b></font></td>
				<td><select size="1" name="ingredientCategoryNo">
						<c:forEach var="ingredientCategoryVO" items="${ingredientSvc.all}">
							<option value="${ingredientCategoryVO.ingredientCategoryNo}"
								${(productVO.ingredientCategoryNo==ingredientCategoryVO.ingredientCategoryNo)? 'selected':'' }>${ingredientCategoryVO.categoryName}
						</c:forEach>
				</select></td>
			</tr>
			<jsp:useBean id="kitchenwareCategorySvc" scope="page"
				class="com.cooklab.kitchenware_category.model.KitchenwareCategoryService" />
			<tr>
				<td>�p�����:<font color=red><b>*</b></font></td>
				<td><select size="1" name="kitchenwareCategoryNo">
						<c:forEach var="KitchenwareCategoryVO"
							items="${kitchenwareCategorySvc.all}">
							<option value="${KitchenwareCategoryVO.kitchenwareCategoryNo}"
								${(productVO.kitchenwareCategoryNo==KitchenwareCategoryVO.kitchenwareCategoryNo)? 'selected':'' }>${KitchenwareCategoryVO.categoryName}
						</c:forEach>
				</select></td>
			</tr>
			<tr>
				<td class="wide-row">�j�M����:</td>
				<td><input type="TEXT" name="searchCount"
					value="<%=(productVO == null) ? "" : productVO.getSearchCount()%>"
					size="45" disabled /></td>
			</tr>

			<tr>
				<td class="wide-row">�ӫ~�Ϥ�:</td>
				<td><img style="max-width: 200px; max-height: 200px;"
					src="/com.tha103.cooklab/ProductImgServlet?productNo=${productVO.productNo}"></td>
				<td><input type="file" name="productPicture"></td>
			</tr>

			<%-- 			<jsp:useBean id="deptSvc" scope="page" --%>
			<%-- 				class="com.dept.model.DeptService" /> --%>
			<!-- 			<tr> -->
			<!-- 				<td>����:<font color=red><b>*</b></font></td> -->
			<!-- 				<td><select size="1" name="deptno"> -->
			<%-- 						<c:forEach var="deptVO" items="${deptSvc.all}"> --%>
			<%-- 							<option value="${deptVO.deptno}" --%>
			<%-- 								${(empVO.deptno==deptVO.deptno)?'selected':'' }>${deptVO.dname} --%>
			<%-- 						</c:forEach> --%>
			<!-- 				</select></td> -->
			<!-- 			</tr> -->

		</table>
		<br> <input type="hidden" name="action" value="update"> <input
			type="hidden" name="productNo" value="<%=productVO.getProductNo()%>">
		<input type="submit" value="�e�X�ק�">
	</FORM>
</body>



<!-- =========================================�H�U�� datetimepicker �������]�w========================================== -->

<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script>
<script
	src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>

<style>
.xdsoft_datetimepicker .xdsoft_datepicker {
	width: 300px; /* width:  300px; */
}

.xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
	height: 151px; /* height:  151px; */
}
</style>

<script>
        $.datetimepicker.setLocale('zh');
        $('#f_date1').datetimepicker({
           theme: '',              //theme: 'dark',
 	       timepicker:false,       //timepicker:true,
 	       step: 1,                //step: 60 (�o�Otimepicker���w�]���j60����)
 	       format:'Y-m-d',         //format:'Y-m-d H:i:s',
 		   value: '<%=productVO.getOffsaleTime()%>
	', // value:   new Date(),
	//disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // �h���S�w���t
	//startDate:	            '2017/07/10',  // �_�l��
	//minDate:               '-1970-01-01', // �h������(���t)���e
	//maxDate:               '+1970-01-01'  // �h������(���t)����
	});

	// ----------------------------------------------------------�H�U�ΨӱƩw�L�k��ܪ����-----------------------------------------------------------

	//      1.�H�U���Y�@�Ѥ��e������L�k���
	//      var somedate1 = new Date('2017-06-15');
	//      $('#f_date1').datetimepicker({
	//          beforeShowDay: function(date) {
	//        	  if (  date.getYear() <  somedate1.getYear() || 
	//		           (date.getYear() == somedate1.getYear() && date.getMonth() <  somedate1.getMonth()) || 
	//		           (date.getYear() == somedate1.getYear() && date.getMonth() == somedate1.getMonth() && date.getDate() < somedate1.getDate())
	//              ) {
	//                   return [false, ""]
	//              }
	//              return [true, ""];
	//      }});

	//      2.�H�U���Y�@�Ѥ��᪺����L�k���
	//      var somedate2 = new Date('2017-06-15');
	//      $('#f_date1').datetimepicker({
	//          beforeShowDay: function(date) {
	//        	  if (  date.getYear() >  somedate2.getYear() || 
	//		           (date.getYear() == somedate2.getYear() && date.getMonth() >  somedate2.getMonth()) || 
	//		           (date.getYear() == somedate2.getYear() && date.getMonth() == somedate2.getMonth() && date.getDate() > somedate2.getDate())
	//              ) {
	//                   return [false, ""]
	//              }
	//              return [true, ""];
	//      }});

	//      3.�H�U����Ӥ�����~������L�k��� (�]�i���ݭn������L���)
	//      var somedate1 = new Date('2017-06-15');
	//      var somedate2 = new Date('2017-06-25');
	//      $('#f_date1').datetimepicker({
	//          beforeShowDay: function(date) {
	//        	  if (  date.getYear() <  somedate1.getYear() || 
	//		           (date.getYear() == somedate1.getYear() && date.getMonth() <  somedate1.getMonth()) || 
	//		           (date.getYear() == somedate1.getYear() && date.getMonth() == somedate1.getMonth() && date.getDate() < somedate1.getDate())
	//		             ||
	//		            date.getYear() >  somedate2.getYear() || 
	//		           (date.getYear() == somedate2.getYear() && date.getMonth() >  somedate2.getMonth()) || 
	//		           (date.getYear() == somedate2.getYear() && date.getMonth() == somedate2.getMonth() && date.getDate() > somedate2.getDate())
	//              ) {
	//                   return [false, ""]
	//              }
	//              return [true, ""];
	//      }});
</script>
</html>