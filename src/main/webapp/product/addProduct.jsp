<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.cooklab.product.model.*"%>

<%
//見com.emp.controller.EmpServlet.java第238行存入req的empVO物件 (此為輸入格式有錯誤時的empVO物件)
ProductVO productVO = (ProductVO) request.getAttribute("productVO");
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>員工資料新增 - addEmp.jsp</title>

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
	border: 1px solid #CCCCFF;
}

th, td {
	padding: 1px;
}
</style>

</head>
<body bgcolor='white'>

	<table id="table-1">
		<tr>
			<td>
				<h3>員工資料新增 - addEmp.jsp</h3>
			</td>
			<td>
				<h4>
					<a href="<%= request.getContextPath() %>/product/select_page.jsp"><img src="<%= request.getContextPath() %>/product/images/tomcat.png"
						width="100" height="100" border="0">回首頁</a>
				</h4>
			</td>
		</tr>
	</table>

	<h3>資料新增:</h3>

	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<FORM METHOD="post"
		ACTION="<%=request.getContextPath()%>/ProductServlet" name="form1"
		enctype="multipart/form-data">
		<table>
			<tr>
				<td>商品名稱:</td>
				<td><input type="TEXT" name="productName"
					value="<%=(productVO == null) ? "請寫名稱" : productVO.getProductName()%>"
					size="45" /></td>
			</tr>
			<tr>
				<td>商品描述:</td>
				<td><input type="TEXT" name="productDec"
					value="<%=(productVO == null) ? "請寫描述" : productVO.getProductName()%>"
					size="45" /></td>
			</tr>
			<tr>
				<td>商品簡介:</td>
				<td><input type="TEXT" name="productIntroduction"
					value="<%=(productVO == null) ? "請寫簡介" : productVO.getProductName()%>"
					size="45" /></td>
			</tr>
			<tr>
				<td>商品數量:</td>
				<td><input type="TEXT" name="saleQty"
					value="<%=(productVO == null) ? "1" : productVO.getSaleQty()%>"
					size="45" /></td>
			</tr>
			<tr>
				<td>商品價格:</td>
				<td><input type="TEXT" name="productPrice"
					value="<%=(productVO == null) ? "100" : productVO.getProductPrice()%>"
					size="45" /></td>
			</tr>
			<tr>
				<td>上架時間:</td>
				<td><input type="TEXT" name="shelfTime" id="f_date1" /></td>
			</tr>
			<tr>
				<td>下架時間:</td>
				<td><input name="offsaleTime" id="f_date1" type="text"></td>
			</tr>
			<tr>
				<td>庫存數量:</td>
				<td><input type="TEXT" name="storageQty"
					value="<%=(productVO == null) ? "10000" : productVO.getStorageQty()%>"
					size="45" /></td>
			</tr>
			<jsp:useBean id="ingredientSvc" scope="page"
				class="com.cooklab.ingredient_category.model.IngredientService" />
			<tr>
				<td>食材種類:<font color=red><b>*</b></font></td>
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
				<td>廚具種類:<font color=red><b>*</b></font></td>
				<td><select size="1" name="kitchenwareCategoryNo">
						<c:forEach var="KitchenwareCategoryVO" items="${kitchenwareCategorySvc.all}">
							<option value="${KitchenwareCategoryVO.kitchenwareCategoryNo}"
								${(productVO.kitchenwareCategoryNo==KitchenwareCategoryVO.kitchenwareCategoryNo)? 'selected':'' }>${KitchenwareCategoryVO.categoryName}
						</c:forEach>
				</select></td>
			</tr>
			<tr>
				<td>商品圖片:</td>
				<td><input type="file" name="productPicture"></td>
			</tr>
		</table>
		<br> <input type="hidden" name="action" value="insert"> <input
			type="submit" value="送出新增">
	</FORM>

</body>



<!-- =========================================以下為 datetimepicker 之相關設定========================================== -->

<%
java.sql.Timestamp hiredate = null;
try {
	hiredate = productVO.getShelfTime();
} catch (Exception e) {
	hiredate = new java.sql.Timestamp(System.currentTimeMillis());
}
%>
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
	       step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
	       format:'Y-m-d',         //format:'Y-m-d H:i:s',
		   value: '<%=hiredate%>
	', // value:   new Date(),
	//disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
	//startDate:	            '2017/07/10',  // 起始日
	//minDate:               '-1970-01-01', // 去除今日(不含)之前
	//maxDate:               '+1970-01-01'  // 去除今日(不含)之後
	});

	// ----------------------------------------------------------以下用來排定無法選擇的日期-----------------------------------------------------------

	//      1.以下為某一天之前的日期無法選擇
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

	//      2.以下為某一天之後的日期無法選擇
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

	//      3.以下為兩個日期之外的日期無法選擇 (也可按需要換成其他日期)
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