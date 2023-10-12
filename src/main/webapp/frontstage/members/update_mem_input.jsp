<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.cooklab.members.model.*"%>

<% //見com.emp.controller.EmpServlet.java第163行存入req的empVO物件 (此為從資料庫取出的empVO, 也可以是輸入格式有錯誤時的empVO物件)
   MembersVO memVO = (MembersVO) request.getAttribute("memVO");
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>員工資料修改 - update_emp_input.jsp</title>

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
    border: 0px solid #CCCCFF;
  }
  th, td {
    padding: 1px;
  }
</style>

</head>
<body bgcolor='white'>

<table id="table-1">
	<tr><td>
		 <h3>員工資料修改 - update_emp_input.jsp</h3>
		 <h4><a href="frontstage/members/select_page.jsp"><img src="frontstage/members/images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>

<h3>資料修改:</h3>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/MembersServlet" name="form1" enctype="multipart/form-data">
<table>
	<tr>		

		<td>會員編號:<font color=red><b>*</b></font></td>
		<td><%=memVO.getMemberId()%></td>
	</tr>
	<tr>
		<td>會員帳號:</td>
		<td><input type="TEXT" name="member_account" value="<%=memVO.getMemberAccount()%>" size="45"/></td>
	</tr>
	<tr>
		<td>會員密碼:</td>
		<td><input type="TEXT" name="member_password" value="<%=memVO.getMemberPassword()%>" size="45"/></td>
	</tr>
	<tr>
		<td>簡介:</td>
		<td><input type="TEXT" name="member_introduce"   value="<%=memVO.getMemberIntroduce()%>" size="45"/></td>
	</tr>
	<tr>
		<td>手機:</td>
		<td><input type="TEXT" name="member_cellphone"   value="<%=memVO.getMemberCellphone()%>" size="45"/></td>
	</tr>
	<tr>
		<td>電子信箱:</td>
		<td><input type="TEXT" name="member_mail"   value="<%=memVO.getMemberMail()%>" size="45"/></td>
	</tr>
	<tr>
		<td>生日:</td>
		<td><input name="member_date" id="f_date1" type="text" value="<%=memVO.getMemberDate()%>"></td> 
	</tr>
	<tr>
		<td>通訊地址:</td>
		<td><input type="TEXT" name="member_address"  value="<%=memVO.getMemberAddress()%>" size="45"/></td>
	</tr>
	<tr>
		<td>國別:</td>
		<td><input type="TEXT" name="member_country"  value="<%=memVO.getMemberCountry()%>" size="45"/></td>
	</tr>
	<tr>
		<td>會員狀態:</td>
		<td><input type="TEXT" name="member_status"  value="<%=memVO.getMemberStatus()%>" size="45"/></td>
	</tr>
	<tr>
		<td>暱稱:</td>
		<td><input type="TEXT" name="member_nickname"  value="<%=memVO.getMemberNickname()%>" size="45"/></td>
	</tr>
	<tr>
		<td>性別:</td>
		<td><input type="TEXT" name="member_gender"  value="<%=memVO.getMemberGender()%>" size="45"/></td>
	</tr>
	<tr>
		<td>新增時間:</td>
		<td><input type="TEXT" name="created_timestamp" value="<%=memVO.getCredcreatedTimestamp()%>" readonly></td>
	</tr>
	<tr>
		<td>最後編輯時間:</td>
		<td><input type="TEXT" name="last_edit_timestamp" value="<%=memVO.getLastEditTimestamp()%>" readonly></td>
	</tr>



</table>
		<input type="file" id="image-upload" accept="image/*" style="display: none;" name="mem_img">
		<button type="button" onclick="document.getElementById('image-upload').click();">選擇圖片</button>

   		<img id="image-preview" src="" alt="圖片預覽" style="display:none;">
   		<br>
		<br> 
<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="memberId" value="<%=memVO.getMemberId()%>">
<input type="submit" value="送出修改"></FORM>
</body>



<!-- =========================================以下為 datetimepicker 之相關設定========================================== -->

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script>
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>

<style>
  .xdsoft_datetimepicker .xdsoft_datepicker {
           width:  300px;   /* width:  300px; */
  }
  .xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
           height: 151px;   /* height:  151px; */
  }
</style>

<script>
        $.datetimepicker.setLocale('zh');
        $('#f_date1').datetimepicker({
           theme: '',              //theme: 'dark',
 	       timepicker:false,       //timepicker:true,
 	       step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
 	       format:'Y-m-d',         //format:'Y-m-d H:i:s',
 		   value: '<%=memVO.getMemberDate()%>', // value:   new Date(),
           //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
           //startDate:	            '2017/07/10',  // 起始日
           //minDate:               '-1970-01-01', // 去除今日(不含)之前
           //maxDate:               '+1970-01-01'  // 去除今日(不含)之後
        });
        document.getElementById('image-upload').addEventListener('change', function() {
            var fileInput = this;
            var imagePreview = document.getElementById('image-preview');
            if (fileInput.files && fileInput.files[0]) {
                var reader = new FileReader();

                reader.onload = function(e) {
                    imagePreview.src = e.target.result;
                };

                reader.readAsDataURL(fileInput.files[0]);
                imagePreview.removeAttribute('style');
            }
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