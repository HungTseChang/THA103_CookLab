<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.cooklab.members.model.*"%>

<% //見com.emp.controller.EmpServlet.java第163行存入req的empVO物件 (此為從資料庫取出的empVO, 也可以是輸入格式有錯誤時的empVO物件)
   MembersVO memVO = (MembersVO) request.getAttribute("memVO");
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>會員資料修改 - update_mem_input.jsp</title>

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
		<td><input type="TEXT" name="member_account" value="${memVO.memberAccount}" size="45"/></td>
	</tr>
	<tr>
		<td>會員密碼:</td>
		<td><input type="TEXT" name="member_password" value="${memVO.memberPassword}" size="45"/></td>
	</tr>
	<tr>
		<td>簡介:</td>
		<td><input type="TEXT" name="member_introduce"   value="${memVO.memberIntroduce}" size="45"/></td>
	</tr>
	<tr>
		<td>手機:</td>
		<td><input type="TEXT" name="member_cellphone"   value="${memVO.memberCellphone}" size="45"/></td>
	</tr>
	<tr>
		<td>電子信箱:</td>
		<td><input type="TEXT" name="member_mail"   value="${memVO.memberMail}" size="45"/></td>
	</tr>
	<tr>
		<td>生日:</td>
		<td><input name="member_date" id="f_date1" type="text" value="${memVO.memberDate}"></td> 
	</tr>
	<tr>
		<td>通訊地址:</td>
		<td><input type="TEXT" name="member_address"  value="${memVO.memberAddress}" size="45"/></td>
	</tr>
	<tr>
		<td>國別:</td>
		<td><input type="TEXT" name="member_country"  value="${memVO.memberCountry}" size="45"/></td>
	</tr>
	<tr>
		<td>會員狀態:</td>
		
		  <td><label for="MemberStatusOption1">正常</label>
		  <input type="radio" id="MemberStatusOption1" name="member_status" value="0">
		  
		  <label for="MemberStatusOption2">鎖定</label>
		  <input type="radio" id="MemberStatusOption2" name="member_status" value="1"></td>
	</tr>
	<tr>
		<td>暱稱:</td>
		<td><input type="TEXT" name="member_nickname"  value="${memVO.memberNickname}" size="45"/></td>
	</tr>
	<tr>
		<td>性別:</td>

		  <td><label for="option1">男</label>
		  <input type="radio" id="option1" name="member_gender" value="0">
		  
		  <label for="option2">女</label>
		  <input type="radio" id="option2" name="member_gender" value="1"></td>
		  
	</tr>




</table>
		<input type="file" id="image-upload" accept="image/*" style="display: none;" name="mem_img">
		<button type="button" onclick="document.getElementById('image-upload').click();">選擇圖片</button>
		
		<input type="hidden" name="mem_preview" value="/com.tha103.cooklab/MembersImgServlet?memberId=<%=memVO.getMemberId()%>">
   		<img id="image-preview" src="/com.tha103.cooklab/MembersImgServlet?memberId=<%=memVO.getMemberId()%>" alt="圖片預覽" style="display:none; max-width: 200px; max-height: 200px;">

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
        
        var memberGenderValue = ${memVO.memberGender} ; // 你可以根据实际需要获取值
        // 根据${memVO.memberGender}的值设置默认选中的单选按钮
        if (memberGenderValue == 0) {
          document.getElementById("option1").checked = true;
        } else if (memberGenderValue == 1) {
          document.getElementById("option2").checked = true;
        }
        var memberStatusValue = ${memVO.memberStatus} ; // 你可以根据实际需要获取值
        
        // 根据${memVO.memberGender}的值设置默认选中的单选按钮
        if (memberStatusValue == 0) {
          document.getElementById("MemberStatusOption1").checked = true;
        } else if (memberStatusValue == 1) {
          document.getElementById("MemberStatusOption2").checked = true;
        }
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