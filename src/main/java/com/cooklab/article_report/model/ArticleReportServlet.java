package com.cooklab.article_report.model;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;

import com.cooklab.members.model.*;
import com.cooklab.article.model.*;
import com.google.gson.Gson;

import javax.servlet.http.*;

@WebServlet("/ArticleReportServlet")
public class ArticleReportServlet extends HttpServlet { 
	private static final long serialVersionUID = 1L;
       

    public ArticleReportServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		String forwardPath = "";
		switch (action) {
		case "changeData":
			forwardPath = changeData(req, res);
			break;
		case "getArticleReport":
			forwardPath = getArticleReport(req, res);
			break;
		case "confirmArticleReport":
		   forwardPath =confirmArticleReport(req, res);
		   break;
		case "getone":
		   forwardPath =getOneArticleReport(req, res);
		   break;
		case"getOne_For_Update":
			   forwardPath =getOne_For_Update(req, res);
			   break;
		case"update":
			   forwardPath =update(req, res);
			   break;
		case"getOne_For_CreatNew":
			   forwardPath =getOne_For_CreatNew(req, res);
			   break;
		case   "insert":
			   forwardPath =insert(req, res);
			   break;
		case "delete":
			   forwardPath =delete(req, res);
			   break;
		default:
			forwardPath = "/dashboard/article_report/WCC_article_report.jsp";
	}
//		res.setContentType("text/html; charset=UTF-8");
		RequestDispatcher dispatcher = req.getRequestDispatcher(forwardPath);
		dispatcher.forward(req, res);

		}




	private String changeData(HttpServletRequest req, HttpServletResponse res) {
		int articleReportNo = Integer.valueOf(req.getParameter("articleReportNo"));
		ArticleReportService aehbdao = new ArticleReportService();
		 ArticleReportVO ArticleReportVO = aehbdao.getOne(articleReportNo);
			req.setAttribute("ArticleReportVO", ArticleReportVO);
			MembersVO MembersVO = ArticleReportVO.getArticleVO().getMembersVO();
			req.setAttribute("MembersVO", MembersVO);
			System.out.println(MembersVO);
		return "/dashboard/article_report/WCC_article_report_info.jsp";
	}


	private String getArticleReport(HttpServletRequest req, HttpServletResponse res) {
		ArticleReportService ahbdao = new ArticleReportService();
		 List<ArticleReportVO> list1=   ahbdao.getAll();
		 List<String> listtitle =new ArrayList<String>();
		 List<String> listnickname = new ArrayList<String>();
		 for(int i = 0 ; i < list1.size();i++) {
			 listtitle.add(list1.get(i).getArticleVO().getArticleTitle());
			 listnickname.add( list1.get(i).getMembersVO().getMemberNickname()  );
		 }
		 String json = new Gson().toJson(list1);
		 String title = new Gson().toJson(listtitle);
		 String nickname =new Gson().toJson(listnickname);
		 System.out.print(title);
		 System.out.print(nickname);

		 req.setAttribute("json",json);
		 req.setAttribute("title",title);
		 req.setAttribute("nickname",nickname);

		
		return "/dashboard/article_report/WCC_article_report.jsp";
	}
	


private String confirmArticleReport(HttpServletRequest req, HttpServletResponse res) {
	ArticleReportJDBCDAOIm ahbdao = new ArticleReportJDBCDAOIm();
	Integer articleReportNo = Integer.valueOf(req.getParameter("articleReportNo"));
	Byte reportingStatus = Byte.valueOf(req.getParameter("status"));
	String reportingAnswer = req.getParameter("reportingAnswer");
//	ArticleReportVO ArticleReportVO1 = new ArticleReportVO();
	 ArticleReportVO ArticleReportVO = ahbdao.findByPrimaryKey(articleReportNo);
//	 	 ArticleReportVO1.setArticleReportNo(articleReportNo);
//		ArticleReportVO1.setArticleNo(ArticleReportVO.getArticleNo());
//		ArticleReportVO1.setReporterId(ArticleReportVO.getReporterId());
//		ArticleReportVO1.setReportingReason(ArticleReportVO.getReportingReason());
		ArticleReportVO.setReportingStatus(reportingStatus);
		 ArticleReportVO.setReportingAnswer(reportingAnswer);
	 ahbdao.update(ArticleReportVO);

	
	return  "/dashboard/article_report/WCC_article_report_info.jsp";
}

private String getOneArticleReport(HttpServletRequest req, HttpServletResponse res) {
	ArticleReportService aehbdao = new ArticleReportService();
	Integer articleReportNo = null;
	 String error =null;
//	String articleReportNost =req.getParameter("articleReportNo");
	try {
	 articleReportNo = Integer.valueOf(req.getParameter("articleReportNo"));
	 }catch(NumberFormatException a){
		 if(  req.getParameter("articleReportNo")==null || req.getParameter("articleReportNo").trim()=="") {
			  error="搜尋欄位不可為空值";
		 }else {
			  error = "格式錯誤，請輸入數字";
		 }
		 req.setAttribute("error",error);
		 return "/articlereport/select_page.jsp";
	 }
	ArticleReportVO ArticleReportVO =aehbdao.getOne(articleReportNo);
	
	 String title = ArticleReportVO.getArticleVO().getArticleTitle();
	 String nickname = ArticleReportVO.getMembersVO().getMemberNickname();
	 req.setAttribute("ArticleReportVO",ArticleReportVO);	 
	 req.setAttribute("title",title);
	 req.setAttribute("nickname",nickname);
	System.out.println("title"+title);
	System.out.println("nickname"+nickname);
	return "/articlereport/listOneArticleReport.jsp";
	
}


private String getOne_For_Update(HttpServletRequest req, HttpServletResponse res) {
	int articleReportNo = Integer.valueOf(req.getParameter("articleReportNo"));
	ArticleReportService aehbdao = new ArticleReportService();
	 ArticleReportVO ArticleReportVO = aehbdao.getOne(articleReportNo);
	 ArticleService ArtService = new ArticleService();
	 List<ArticleVO> list =  ArtService.getAll();
	 List<Integer> ArtNolist = new ArrayList<Integer>();
	 List<String> ArtTitlelist = new ArrayList<String>();

	 for(int i =0; i< list.size();i++) {
		 ArtNolist.add(list.get(i).getArticleNo());
		 ArtTitlelist.add(list.get(i).getArticleTitle());
	 }
	 MembersService MembersService = new MembersService();
	 List<MembersVO> list2 =  MembersService.getAll();
	 List<Integer> MemIdlist = new ArrayList<Integer>();
	 List<String> MemNiKlist = new ArrayList<String>();
	 for(int j =0; j< list2.size();j++) {
		 MemIdlist.add(list2.get(j).getMemberId());
		 MemNiKlist.add(list2.get(j).getMemberNickname());
	 } 
	 
		req.setAttribute("ArticleReportVO", ArticleReportVO);
		req.setAttribute("ArtNolist", ArtNolist);
		req.setAttribute("ArtTitlelist", ArtTitlelist);
		req.setAttribute("MemIdlist", MemIdlist);
		req.setAttribute("MemNiKlist", MemNiKlist);


	return "/articlereport/update_ArticleReport_input.jsp";
}
private String update(HttpServletRequest req, HttpServletResponse res) {
	ArticleReportService aehbdao = new ArticleReportService();
	Integer articleNo = null;
	Integer reporterId=null;
	Integer 	 articleReportNo = Integer.valueOf( req.getParameter("articleReportNo"));
	List<String> error =new ArrayList<String>();
	Timestamp createdTimestamp=null;
	String error1;
	String error2;
	String error3;

	try {
	 articleNo = Integer.valueOf(req.getParameter("articleNo"));

	}catch(NumberFormatException e){
		 if(  req.getParameter("articleNo")==null || req.getParameter("articleNo").trim()=="") {
			  error1="文章編號欄位不可為空值";
			  error.add(error1);
		 }else {
			  error1 = "文章編號格式錯誤，請輸入數字";
			  error.add(error1);
		 }
	}
try {
		reporterId = Integer.valueOf(req.getParameter("reporterId"));
		}catch(NumberFormatException a) {
	 if(  req.getParameter("reporterId")==null || req.getParameter("reporterId").trim()=="") {
		  error2="檢舉者會員編號欄位不可為空值";
		  error.add(error2);
	 }else {
		  error2 = "檢舉者會員編號格式錯誤，請輸入數字";
		  error.add(error2);
	 }
		}
try {
	 createdTimestamp =Timestamp.valueOf(req.getParameter("createdTimestamp"));

}catch(Exception w){
	 if(  req.getParameter("createdTimestamp")==null || req.getParameter("createdTimestamp").trim()=="") {
		  error3="創建時間欄位不可為空值";
		  error.add(error3);
	 }else {
		  error3 = "創建時間欄位格式錯誤，請按照格式輸入";
		  error.add(error3);
	 }
	
}
if(!error.isEmpty()) {
req.setAttribute("error",error);
req.setAttribute("ArticleReportVO", aehbdao.getOne(articleReportNo));
getOne_For_Update(req,res);
return "/articlereport/update_ArticleReport_input.jsp";
}
	String reportingReason = req.getParameter("reportingReason");
    Byte reportingStatus = Byte.valueOf(req.getParameter("reportingStatus"));

	aehbdao.update(articleNo, reporterId, reportingReason, reportingStatus,null, createdTimestamp, articleReportNo);
	
	return "/articlereport/listAllArticleReport.jsp";
}


private String getOne_For_CreatNew(HttpServletRequest req, HttpServletResponse res) {
	 ArticleService ArtService = new ArticleService();
	 List<ArticleVO> list =  ArtService.getAll();
	 List<Integer> ArtNolist = new ArrayList<Integer>();
	 List<String> ArtTitlelist = new ArrayList<String>();

	 for(int i =0; i< list.size();i++) {
		 ArtNolist.add(list.get(i).getArticleNo());
		 ArtTitlelist.add(list.get(i).getArticleTitle());
	 }
	 MembersService MembersService = new MembersService();
	 List<MembersVO> list2 =  MembersService.getAll();
	 List<Integer> MemIdlist = new ArrayList<Integer>();
	 List<String> MemNiKlist = new ArrayList<String>();
	 for(int j =0; j< list2.size();j++) {
		 MemIdlist.add(list2.get(j).getMemberId());
		 MemNiKlist.add(list2.get(j).getMemberNickname());
	 } 
	 Timestamp createTime = new Timestamp(new Date().getTime());
		req.setAttribute("ArtNolist", ArtNolist);
		req.setAttribute("ArtTitlelist", ArtTitlelist);
		req.setAttribute("MemIdlist", MemIdlist);
		req.setAttribute("MemNiKlist", MemNiKlist);
		req.setAttribute("createTime", createTime);

	
	
	
	return "/articlereport/addArticleRpeort.jsp";
	
}



private String insert(HttpServletRequest req, HttpServletResponse res) {
		ArticleReportService aehbdao = new ArticleReportService();
	
		Integer articleNo = Integer.valueOf(req.getParameter("articleNo"));
		Integer reporterId = Integer.valueOf(req.getParameter("reporterId"));
		String reportingReason = req.getParameter("reportingReason");
	    Byte reportingStatus = Byte.valueOf(req.getParameter("reportingStatus"));
		List<String> error =new ArrayList<String>();
		Timestamp createdTimestamp=null;
		String error1;
		String error3;
		if(reportingReason==null ||reportingReason.trim().isEmpty()) {
			error1 ="檢舉理由不能為空";
			error.add(error1);
			System.out.print(error);
		}
		try {
			 createdTimestamp =Timestamp.valueOf(req.getParameter("createdTimestamp"));

		}catch(Exception w){
			 if(  req.getParameter("createdTimestamp")==null || req.getParameter("createdTimestamp").trim()=="") {
				  error3="創建時間欄位不可為空值";
				  error.add(error3);
			 }else {
				  error3 = "創建時間欄位格式錯誤，請按照格式輸入";
				  error.add(error3);
			 }
			}
if(!error.isEmpty()) {
			req.setAttribute("error",error);
			getOne_For_CreatNew(req,res);	
			return"/articlereport/addArticleRpeort.jsp";
			}
		
		
		
		aehbdao.add(articleNo, reporterId, reportingReason, reportingStatus, null,createdTimestamp);
		return "/articlereport/listAllArticleReport.jsp";
	
	
}



private String delete(HttpServletRequest req, HttpServletResponse res) {
	ArticleReportService aehbdao = new ArticleReportService();
	Integer articleReportNo = Integer.valueOf(req.getParameter("articleReportNo"));
	aehbdao.delete(articleReportNo);
	return  "/articlereport/listAllArticleReport.jsp";
}










}
