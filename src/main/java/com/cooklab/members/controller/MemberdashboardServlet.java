package com.cooklab.members.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.cooklab.article.model.ArticleVO;
import com.cooklab.article_collection.model.ArticleCollectionVO;
import com.cooklab.article_reaction.model.ArticleReactionVO;
import com.cooklab.article_report.model.ArticleReportVO;
import com.cooklab.article_sub.model.ArticleSubVO;
import com.cooklab.article_sub_report.model.ArticleSubReportVO;
import com.cooklab.member_collection.model.MemberCollectionVO;
import com.cooklab.member_order.model.MemberOrderVO;
import com.cooklab.members.model.*;
import com.cooklab.notify_center.model.NotifyCenterVO;
import com.cooklab.permission.model.PermissionVO;
import com.cooklab.promo_code_used.model.PromoCodeUsedVO;
import com.cooklab.recipe.model.RecipeVO;
import com.cooklab.recipe_collection.model.RecipeCollectionVO;
import com.cooklab.recipe_comments_report.model.RecipeCommentsReportVO;
import com.cooklab.recipe_reaction.model.RecipeReactionVO;
import com.cooklab.recipe_report.model.RecipeReportVO;
import com.cooklab.shopping_cart.model.ShoppingCartVO;
import com.cooklab.util.HibernateUtil;
import com.google.gson.Gson;
import com.cooklab.admins.*;

@WebServlet("/MemberdashboardServlet")
public class MemberdashboardServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	public MemberdashboardServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		String forwardPath = "";
		switch (action) {
		case"getAll":
			forwardPath =getAll(req, res);
			break;
		case"getone":
			forwardPath =getone(req, res);
			break;
		case"update":
			forwardPath =update(req, res);
			break;
		case"rdnPassword":
			forwardPath =rdnPassword(req, res);
			return;
		default:
			forwardPath = "/dashboard/member/WCC_member.jsp";
		}
		RequestDispatcher dispatcher = req.getRequestDispatcher(forwardPath);
		dispatcher.forward(req, res);

	}

	private String rdnPassword(HttpServletRequest req, HttpServletResponse res) {
		EmailSender EmailSender = new EmailSender();
		Integer memberid = Integer.valueOf(req.getParameter("memberid").trim());
		String account = (req.getParameter("account").trim());
		String email = (req.getParameter("email").trim());
	String	rndpassword = EmailSender.randomPassword(12);
	MemberServletFake MembersService = new MemberServletFake();
	 MembersVO result = MembersService.updateMember(memberid, rndpassword);
	 String response = "fail";
	 if(result != null) {
		 
		 String subtitlte ="廚藝實驗室: 您的密碼已重設";
		 String context =account+"您好: 你的密碼已重設為: "+rndpassword+"請記得更新你的密碼";
				 EmailSender.sendMail(email, subtitlte,context);
				 response="success";
	 }
	 try {
		 res.setContentType("text/plain");
		 PrintWriter writer = res.getWriter();
		 writer.write(response);
		 writer.close();
		
	} catch (IOException e) {
		e.printStackTrace();
	}
	 
		return  "/dashboard/member/WCC_member.jsp";
		
	}

	private String update(HttpServletRequest req, HttpServletResponse res) {
		MemberServletFake MembersService = new MemberServletFake();
		Integer NO = Integer.valueOf(req.getParameter("memberID").trim());
		String passowrd = req.getParameter("password");
		String account = req.getParameter("account");
		String email = req.getParameter("email");
		Byte status = Byte.valueOf(req.getParameter("memberStatus").trim());
		String passowrd1 = MembersService.getOneMember(NO).getMemberPassword();
		if(!passowrd.equals(passowrd1)) {
			EmailSender EmailSender = new EmailSender();
			 String subtitlte ="廚藝實驗室: 您的密碼已重設";
			 String context =account+"您好: 你的密碼已重設為: "+passowrd+"請記得更新你的密碼";
		     EmailSender.sendMail(email, subtitlte,context);						
		}
		System.out.println("AAAA"+status);
		MembersService.updateMember(NO, passowrd, status);
		return  "/dashboard/member/WCC_member.jsp";
	}

	private String getone(HttpServletRequest req, HttpServletResponse res) {
		MembersService MembersService = new MembersService();
		Integer NO = Integer.valueOf(req.getParameter("memberNO").trim());
		MembersVO Members = MembersService.getOneMember(NO);
		MembersVOFake MembersFake = new MembersVOFake(Members);
		System.out.println(MembersFake.getMemberPassword());
		if( MembersFake.getMemberPicture() != null) {
		byte[] imageBytes =  MembersFake.getMemberPicture();
		String base64Image = Base64.getEncoder().encodeToString(imageBytes);
		String picture = new Gson().toJson(base64Image);
		req.setAttribute("picture", picture);
		}
		String json = new Gson().toJson(MembersFake);
		req.setAttribute("json", json);

		return "/dashboard/member/WCC_member_info.jsp";
	}

	private String getAll(HttpServletRequest req, HttpServletResponse res) {
		MembersService MembersService = new MembersService();
		List<MembersVO> Members = MembersService.getAll();
		int lenght =Members.size();
		List<MembersVOFake> MembersFake = new ArrayList<MembersVOFake>();
		for(int i = 0 ; i < lenght;i++) {
			MembersFake.add(new MembersVOFake(Members.get(i)));
		}
		String json = new Gson().toJson(MembersFake);
		req.setAttribute("json", json);
		
		
		return  "/dashboard/member/WCC_member.jsp";
	}
	
	
	private class MemberServletFake extends MembersService{
	private MembersDAO_interface dao;
	Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	private MemberServletFake() {
		dao = new MembersHDAOIm(session.getSessionFactory());
	}
		public MembersVO updateMember(Integer memberId,String memberPassword,Byte memberStatus) {
			MembersService MembersService = new MembersService();
			MembersVO MembersVO = MembersService.getOneMember(memberId);
			MembersVO.setMemberPassword(memberPassword);
			MembersVO.setMemberStatus(memberStatus);
			dao.update(MembersVO);
			return MembersVO;
			}
		public MembersVO updateMember(Integer memberId,String memberPassword) {
			MembersService MembersService = new MembersService();
			MembersVO MembersVO = MembersService.getOneMember(memberId);
			MembersVO.setMemberPassword(memberPassword);
			dao.update(MembersVO);
			return MembersVO;
		}
		
			
		}


	
	
private class MembersVOFake{
	private Integer memberId;
	private String memberAccount;
	private String memberPassword;
	private String memberIntroduce;
	private String memberCellphone;
	private String memberMail;
	private java.sql.Date  memberDate;
	private String memberAddress;
	private String memberCountry;
	private byte memberStatus;
	private byte[] memberPicture;
	private String memberNickname;
	private byte memberGender;
	private Timestamp credcreatedTimestamp;
	private Timestamp lastEditTimestamp;
	
	
	
	
	public MembersVOFake(MembersVO MembersVO){
		super();
		this.memberId = MembersVO.getMemberId();
		this.memberAccount = MembersVO.getMemberAccount();
		this.memberPassword = MembersVO.getMemberPassword();
		this.memberIntroduce = MembersVO.getMemberIntroduce();
		this.memberCellphone = MembersVO.getMemberCellphone();
		this.memberMail = MembersVO.getMemberMail();
		this.memberDate = MembersVO.getMemberDate();
		this.memberAddress = MembersVO.getMemberAddress();
		this.memberCountry = MembersVO.getMemberCountry();
		this.memberStatus = MembersVO.getMemberStatus();
		this.memberPicture = MembersVO.getMemberPicture();
		this.memberNickname = MembersVO.getMemberNickname();
		this.memberGender = MembersVO.getMemberGender();
		this.credcreatedTimestamp = MembersVO.getCredcreatedTimestamp();
		this.lastEditTimestamp = MembersVO.getLastEditTimestamp();
	}
	public Integer getMemberId() {
		return memberId;
	}
	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}
	public String getMemberAccount() {
		return memberAccount;
	}
	public void setMemberAccount(String memberAccount) {
		this.memberAccount = memberAccount;
	}
	public String getMemberPassword() {
		return memberPassword;
	}
	public void setMemberPassword(String memberPassword) {
		this.memberPassword = memberPassword;
	}
	public String getMemberIntroduce() {
		return memberIntroduce;
	}
	public void setMemberIntroduce(String memberIntroduce) {
		this.memberIntroduce = memberIntroduce;
	}
	public String getMemberCellphone() {
		return memberCellphone;
	}
	public void setMemberCellphone(String memberCellphone) {
		this.memberCellphone = memberCellphone;
	}
	public String getMemberMail() {
		return memberMail;
	}
	public void setMemberMail(String memberMail) {
		this.memberMail = memberMail;
	}
	public java.sql.Date getMemberDate() {
		return memberDate;
	}
	public void setMemberDate(java.sql.Date memberDate) {
		this.memberDate = memberDate;
	}
	public String getMemberAddress() {
		return memberAddress;
	}
	public void setMemberAddress(String memberAddress) {
		this.memberAddress = memberAddress;
	}
	public String getMemberCountry() {
		return memberCountry;
	}
	public void setMemberCountry(String memberCountry) {
		this.memberCountry = memberCountry;
	}
	public byte getMemberStatus() {
		return memberStatus;
	}
	public void setMemberStatus(byte memberStatus) {
		this.memberStatus = memberStatus;
	}
	public byte[] getMemberPicture() {
		return memberPicture;
	}
	public void setMemberPicture(byte[] memberPicture) {
		this.memberPicture = memberPicture;
	}
	public String getMemberNickname() {
		return memberNickname;
	}
	public void setMemberNickname(String memberNickname) {
		this.memberNickname = memberNickname;
	}
	public byte getMemberGender() {
		return memberGender;
	}
	public void setMemberGender(byte memberGender) {
		this.memberGender = memberGender;
	}
	public Timestamp getCredcreatedTimestamp() {
		return credcreatedTimestamp;
	}
	public void setCredcreatedTimestamp(Timestamp credcreatedTimestamp) {
		this.credcreatedTimestamp = credcreatedTimestamp;
	}
	public Timestamp getLastEditTimestamp() {
		return lastEditTimestamp;
	}
	public void setLastEditTimestamp(Timestamp lastEditTimestamp) {
		this.lastEditTimestamp = lastEditTimestamp;
	}
	
	
	
	
}	
}
