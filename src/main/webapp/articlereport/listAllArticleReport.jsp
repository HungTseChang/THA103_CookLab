<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.cooklab.article.model.ArticleVO" %>
<%@ page import="com.cooklab.article_report.model.*" %>
<%@ page import="com.google.gson.Gson" %>
<%@ page import="java.sql.Timestamp" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="com.cooklab.members.model.MembersVO" %>

<%-- 此頁練習採用 EL 的寫法取值 --%>

<%

ArticleReportService ArticleReportService = new ArticleReportService();
	List<ArticleReportVO> list = ArticleReportService.getAll();
	 List<String> listtitle =new ArrayList<String>();
	 List<String> listnickname = new ArrayList<String>();
	 List<String> time= new ArrayList<String>();
	 for(int i = 0 ; i < list.size();i++) {
		 listtitle.add(list.get(i).getArticleVO().getArticleTitle());
		 listnickname.add( list.get(i).getMembersVO().getMemberNickname()  );
		 Timestamp timestamp = list.get(i).getCreatedTimestamp();
		 Date originalDate = new Date(timestamp.getTime());
	     SimpleDateFormat targetFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	     String formattedDateString = '"'+targetFormat.format(originalDate)+'"';
	     time.add(formattedDateString);

	 }
	 String json = new Gson().toJson(list);
	 String title = new Gson().toJson(listtitle);
	 String nickname =new Gson().toJson(listnickname);
	 pageContext.setAttribute("json",json);
	 pageContext.setAttribute("title",title);
	 pageContext.setAttribute("nickname",nickname);
	 pageContext.setAttribute("time",time);
%>


<html>
<head>
<title>所有文章資料 - ArticleReport.jsp</title>

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
	width: 800px;
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
		 <h3>所有文章資料 - listarticleArt.jsp</h3>
		 <h4><a href="${pageContext.request.contextPath}/articlereport/select_page.jsp"><img src="${pageContext.request.contextPath}/articlereport/images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>
                       <div class="datable dropdown">
                        <select class="wcc" id="select1">
                        <option value="5">5</option>
                        <option value="10">10</option>
                        <option value="15">15</option>
                        <option value="20">20</option>
                        </select>
                        <label>每頁展示筆數</label>
                        </div>
<table id="table1">
	<thead>
		<tr>
			<th>檢舉編號</th>
			<th>檢舉者Id</th>
			<th>檢舉者暱稱</th>
			<th>文章編號</th>
			<th>文章標題</th>
			<th>檢舉理由</th>
			<th>檢舉狀態</th>
			<th>檢舉時間</th>
		</tr>
		</thead>
		<tbody>
		</tbody>
</table>
   <div class="pagination">
        <span class="page-item wcc" id="prev-page">上一頁</span>
        <span class="page-item wcc" id="next-page">下一頁</span>
        <span id="current-page">1</span>
        <span id="total-pages">of 1</span>
    </div>
<script src="${pageContext.request.contextPath}/dashboard/assets\vendors\jquery-3.7.1.min.js"></script>

<script>
document.addEventListener("DOMContentLoaded", function () {
 var rowsPerPage = 5;
 var currentPage = 1;
 
var myList=JSON.parse('${json}');
var title = JSON.parse('${title}');
var nikname = JSON.parse('${nickname}');
var time = JSON.parse(`${time}`);
 var number =myList.length;
    function updateTable() {    
    	var startIndex = (currentPage - 1) * rowsPerPage;
    	var endIndex = startIndex + rowsPerPage;
    	var tableBody = $("table#table1").children("tbody");
    	tableBody.empty();
	for(let i = startIndex ; i<endIndex ;i++){
    	        	  
  if (i <number){
	  let aa = myList[i];

         	  let status ={
         			  0:" <span class='badge bg-success'>已處理</span>",
         			  1:" <span class='badge bg-danger'>未處理</span>",
         	  }
         var aNO = aa.articleReportNo;
         	  console.log(aNO);
  	  let text = "";
	  text += "<tr>";
	  text += "<td class='wcc'>"+aa.articleReportNo+"</td>";
	  text +=" <td class='wcc'>"+aa.reporterId+"</td>";
	  text +=" <td>"+nikname[i]+"</td>";
	  text +=" <td class='wcc'>"+aa.articleNo+"</td>";
	  text +=" <td>"+title[i]+"</td>";
	  text +=" <td>"+aa.reportingReason+"</td>";
	  text +=" <td>"+status[aa.reportingStatus]+"</td>";
	  text +=" <td>"+time[i]+"</td>";
	  text +=`
			<td>
		  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/ArticleReportServlet" style="margin-bottom: 0px;">
		     <input type="submit" value="修改">
		     <input type="hidden" name="articleReportNo"  value=`;
		    text +=aa.articleReportNo;
		    text +=` >
		     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
		</td>
		<td>
		  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/ArticleReportServlet" style="margin-bottom: 0px;">
		     <input type="submit" value="刪除">
		     <input type="hidden" name="articleReportNo"  value=`;
	  text +=aa.articleReportNo;
	  text +=`>
		     <input type="hidden" name="action" value="delete"></FORM>
		</td>
		  `;
	  text += "<tr>";
	  $("table#table1").children("tbody").append(text);
     	   }
	}
$("#current-page").text(currentPage);
var totalPages = Math.ceil(number/ rowsPerPage);
$("#total-pages").text("of " + totalPages);
	}
    $("#select1").change(function() {
 	   rowsPerPage = $(this).val();
 	   currentPage = 1;
 	   updateTable();
  });
    $("#prev-page").click(function() {
        if (currentPage > 1) {
            currentPage--;
            updateTable();
        }
        
        
    });
    
    $("#next-page").click(function() {
        var totalPages = Math.ceil(number / rowsPerPage);
        if (currentPage < totalPages) {
            currentPage++;
            updateTable();
        }
    });
    
    
    updateTable();
    
	
    })

</script>



</body>
</html>