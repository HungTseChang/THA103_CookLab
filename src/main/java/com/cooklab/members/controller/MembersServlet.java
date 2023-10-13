package com.cooklab.members.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import com.cooklab.members.model.*;
@WebServlet("/MembersServlet")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
maxFileSize = 1024 * 1024 * 10,      // 10MB
maxRequestSize = 1024 * 1024 * 50)   // 50MB

public class MembersServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			String str = req.getParameter("memberAccount");
			if (str == null || (str.trim()).length() == 0) {
				errorMsgs.add("請輸入正確的會員帳號");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/frontstage/members/select_page.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}
			

			String memberAccount = null;
			try {
				memberAccount = str;
			} catch (Exception e) {
				errorMsgs.add("會員編號格式不正確");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/frontstage/members/select_page.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			/*************************** 2.開始查詢資料 *****************************************/
			MembersService memSvc = new MembersService();
			
			MembersVO memVO = memSvc.getOneMemberAccount(memberAccount);
			if (memVO == null) {
				errorMsgs.add("查無資料，請再次確認");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/frontstage/members/select_page.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
			req.setAttribute("memVO", memVO); // 資料庫取出的empVO物件,存入req
/**/		String url = "/frontstage/members/listOneMembers.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
			successView.forward(req, res);
		}

		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 ****************************************/
				Integer memberId = null;
			    try {
			    	memberId = Integer.valueOf(req.getParameter("memberId"));
			        // 接下來的程式碼處理 memberId
			    } catch (NumberFormatException e) {
			        //System.out.println("錯誤嚕");
			    }

			/*************************** 2.開始查詢資料 ****************************************/
			MembersService memSvc = new MembersService();
			MembersVO memVO = memSvc.getOneMember(memberId);
		

			/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
			req.setAttribute("memVO", memVO); // 資料庫取出的empVO物件,存入req
			String url = "/frontstage/members/update_mem_input.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
			successView.forward(req, res);
		}
//		
		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
//			Integer memberId = Integer.valueOf(req.getParameter("memberId").trim());
//			String ename = req.getParameter("ename");
//			String enameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
//			if (ename == null || ename.trim().length() == 0) {
//				errorMsgs.add("員工姓名: 請勿空白");
//			} else if (!ename.trim().matches(enameReg)) { // 以下練習正則(規)表示式(regular-expression)
//				errorMsgs.add("員工姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
//			}
//
//			String job = req.getParameter("job").trim();
//			if (job == null || job.trim().length() == 0) {
//				errorMsgs.add("職位請勿空白");
//			}
//
//			java.sql.Date hiredate = null;
//			try {
//				hiredate = java.sql.Date.valueOf(req.getParameter("hiredate").trim());
//			} catch (IllegalArgumentException e) {
//				hiredate = new java.sql.Date(System.currentTimeMillis());
//				errorMsgs.add("請輸入日期!");
//			}
//
//			Double sal = null;
//			try {
//				sal = Double.valueOf(req.getParameter("sal").trim());
//			} catch (NumberFormatException e) {
//				sal = 0.0;
//				errorMsgs.add("薪水請填數字.");
//			}
//
//			Double comm = null;
//			try {
//				comm = Double.valueOf(req.getParameter("comm").trim());
//			} catch (NumberFormatException e) {
//				comm = 0.0;
//				errorMsgs.add("獎金請填數字.");
//			}
//
//			String memberAccount =req.getParameter("memberAccount").trim());
//
			MembersVO memVO = new MembersVO();
			
			Integer memberId = Integer.valueOf(req.getParameter("memberId").trim());;
		    memVO.setMemberId(memberId);


			String memberAccount = req.getParameter("member_account").trim();
			if (memberAccount != null && !memberAccount.isEmpty()) {
			    // 在這裡進行對 memberAccount 的資料驗證
			    memVO.setMemberAccount(memberAccount);
			}
			else
				errorMsgs.add("帳號: 請勿空白");

			String memberPassword = req.getParameter("member_password").trim();
			if (memberPassword != null && !memberPassword.isEmpty()) {
			    // 在這裡進行對 memberPassword 的資料驗證
			    memVO.setMemberPassword(memberPassword);
			}
			else
				errorMsgs.add("密碼: 請勿空白");

			String memberIntroduce = req.getParameter("member_introduce").trim();
			if (memberIntroduce != null && !memberIntroduce.isEmpty()) {
			    // 在這裡進行對 memberIntroduce 的資料驗證
			    memVO.setMemberIntroduce(memberIntroduce);
			}

			String memberCellphone = req.getParameter("member_cellphone").trim();
			if (memberCellphone != null && !memberCellphone.isEmpty()) {
			    // 在這裡進行對 memberCellphone 的資料驗證
			    memVO.setMemberCellphone(memberCellphone);
			}
			else
				errorMsgs.add("手機: 請勿空白");

			String memberMail = req.getParameter("member_mail").trim();
			if (memberMail != null && !memberMail.isEmpty()) {
			    // 在這裡進行對 memberMail 的資料驗證
			    memVO.setMemberMail(memberMail);
			}
			else
				errorMsgs.add("電子信箱: 請勿空白");
			
			java.sql.Date memberDate = null;
			String memberDateParam = req.getParameter("member_date").trim();
			if (memberDateParam != null && !memberDateParam.isEmpty()) {
			    try {
			        memberDate = java.sql.Date.valueOf(memberDateParam);
			        memVO.setMemberDate(memberDate);
			    } catch (IllegalArgumentException e) {
			        // 處理無效的 memberDate 資料
					errorMsgs.add("日期無效");
			    }
			}

			String memberAddress = req.getParameter("member_address").trim();
			if (memberAddress != null && !memberAddress.isEmpty()) {
			    // 在這裡進行對 memberAddress 的資料驗證
			    memVO.setMemberAddress(memberAddress);
			}
			else
				errorMsgs.add("通訊地址: 請勿空白");

			String memberCountry = req.getParameter("member_country").trim();
			if (memberCountry != null && !memberCountry.isEmpty()) {
			    // 在這裡進行對 memberCountry 的資料驗證
			    memVO.setMemberCountry(memberCountry);
			}
			else
				errorMsgs.add("國別: 請勿空白");
			Byte memberStatus = null;
			String memberStatusParam = req.getParameter("member_status").trim();
			if (memberStatusParam != null && !memberStatusParam.isEmpty()) {
			    try {
			        memberStatus = Byte.valueOf(memberStatusParam);
			        memVO.setMemberStatus(memberStatus);
			    } catch (NumberFormatException e) {
			        // 處理無效的 memberStatus 資料
			    }
			}
			else
				errorMsgs.add("會員狀態: 請勿空白");

			String memberNickname = req.getParameter("member_nickname").trim();
			if (memberNickname != null && !memberNickname.isEmpty()) {
			    // 在這裡進行對 memberNickname 的資料驗證
			    memVO.setMemberNickname(memberNickname);
			}
			else
				errorMsgs.add("會員暱稱: 請勿空白");
			Byte memberGender = null;
			String memberGenderParam = req.getParameter("member_gender").trim();
			if (memberGenderParam != null && !memberGenderParam.isEmpty()) {
			    try {
			        memberGender = Byte.valueOf(memberGenderParam);
			        memVO.setMemberGender(memberGender);
			    } catch (NumberFormatException e) {
			        // 處理無效的 memberGender 資料
					errorMsgs.add("會員性別: 請勿空白");
			    }
			}

			//圖片
			byte[] buf = null;
            Part filePart = req.getPart("mem_img");
            
	        InputStream in = filePart.getInputStream();
	        if(in.available() != 0)
	        {
		        buf = new byte[in.available()];   // byte[] buf = in.readAllBytes();  // Java 9 的新方法
		        //System.out.println();
		        in.read(buf);
		        in.close(); 
		        memVO.setMemberPicture(buf);
	        }
	        else
	        {

				
				MembersService memSvc = new MembersService();
				MembersVO memVO2 = memSvc.getOneMember(memberId);
				
				buf = memVO2.getMemberPicture();
//				MembersVO memVO = memSvc.getOneMemberAccount(memberAccount);
//	        	String memPreviewString = req.getParameter("mem_preview");
//	        	//System.out.println(memPreviewString);
//	        	byte[] memPreviewBytes = memPreviewString.getBytes();

//	        	memVO.setMemberPicture(memPreviewBytes);
	        }
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("memVO", memVO); // 含有輸入格式錯誤的empVO物件,也存入req
				RequestDispatcher failureView = req.getRequestDispatcher("/frontstage/members/update_mem_input.jsp");
				failureView.forward(req, res);
				return; // 程式中斷
			}
//
//			/*************************** 2.開始修改資料 *****************************************//
			MembersService memSvc = new MembersService();
			memVO = memSvc.updateMember(memberId,memberAccount,memberPassword,memberIntroduce,
					memberCellphone,memberMail, memberDate,memberAddress,memberCountry
					,memberStatus,buf,memberNickname,memberGender);

//			/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
			memVO = memSvc.getOneMember(memberId);
			req.setAttribute("memVO", memVO); // 資料庫update成功後,正確的的empVO物件,存入req
			String url = "/frontstage/members/listOneMembers.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
			successView.forward(req, res);
		}

		if ("insert".equals(action)) { // 來自addMembers.jsp的請求

			MembersVO memVO = new MembersVO();
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
//			String ename = req.getParameter("ename");
//			String enameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
//			if (ename == null || ename.trim().length() == 0) {
//				errorMsgs.add("員工姓名: 請勿空白");
//			} else if (!ename.trim().matches(enameReg)) { // 以下練習正則(規)表示式(regular-expression)
//				errorMsgs.add("員工姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
//			}
//
//			String job = req.getParameter("job").trim();
//			if (job == null || job.trim().length() == 0) {
//				errorMsgs.add("職位請勿空白");
//			}
//
//			java.sql.Date hiredate = null;
//			try {
//				hiredate = java.sql.Date.valueOf(req.getParameter("hiredate").trim());
//			} catch (IllegalArgumentException e) {
//				hiredate = new java.sql.Date(System.currentTimeMillis());
//				errorMsgs.add("請輸入日期!");
//			}
//
//			Double sal = null;
//			try {
//				sal = Double.valueOf(req.getParameter("sal").trim());
//			} catch (NumberFormatException e) {
//				sal = 0.0;
//				errorMsgs.add("薪水請填數字.");
//			}
//
//			Double comm = null;
//			try {
//				comm = Double.valueOf(req.getParameter("comm").trim());
//			} catch (NumberFormatException e) {
//				comm = 0.0;
//				errorMsgs.add("獎金請填數字.");
//			}
//
//			Integer deptno = Integer.valueOf(req.getParameter("deptno").trim());
//
//			EmpVO empVO = new EmpVO();
//			empVO.setEname(ename);
//			empVO.setJob(job);
//			empVO.setHiredate(hiredate);
//			empVO.setSal(sal);
//			empVO.setComm(comm);
//			empVO.setDeptno(deptno);

//			MembersVO memVO = new MembersVO();
//			String memberAccount = req.getParameter("member_account").trim();
//			memVO.setMemberAccount(memberAccount);
			
			String memberAccount = req.getParameter("member_account").trim();
	

//			if (memberAccount != null && !memberAccount.isEmpty()) {
//			    // 在這裡進行對 memberAccount 的資料驗證
			    memVO.setMemberAccount(memberAccount);
//			}
//			else
//			{
//				errorMsgs.add("帳號: 請勿空白");
//			}
			
			String memberPassword = (req.getParameter("member_password").trim());
			memVO.setMemberPassword(memberPassword);
			String memberIntroduce = (req.getParameter("member_introduce").trim());
			memVO.setMemberPassword(memberIntroduce);
			String memberCellphone = (req.getParameter("member_cellphone").trim());
			memVO.setMemberCellphone(memberCellphone);
			String memberMail= (req.getParameter("member_mail").trim());
			memVO.setMemberMail(memberMail);
			java.sql.Date memberDate = (java.sql.Date.valueOf(req.getParameter("member_date").trim()));
			
			memVO.setMemberDate(memberDate);
			String memberAddress = (req.getParameter("member_address").trim());
			memVO.setMemberAddress(memberAddress);
			String memberCountry = (req.getParameter("member_country").trim());
			memVO.setMemberCountry(memberCountry);
			Byte memberStatus = (Byte.valueOf(req.getParameter("member_status").trim()));
			memVO.setMemberStatus(memberStatus);
			String memberNickname = (req.getParameter("member_nickname").trim());
			memVO.setMemberNickname(memberNickname);
			Byte memberGender = (Byte.valueOf(req.getParameter("member_gender").trim()));
			memVO.setMemberGender(memberGender);
			
			byte[] buf = null;
            Part filePart = req.getPart("mem_img");
            
	            InputStream in = filePart.getInputStream();
	            if(in.available() != 0)
	            {
		            buf = new byte[in.available()];   // byte[] buf = in.readAllBytes();  // Java 9 的新方法
		            //System.out.println();
		            in.read(buf);
		            in.close(); 
		            
					memVO.setMemberPicture(buf);
	            }
			memVO.setMemberGender(memberGender);
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("memVO", memVO); // 含有輸入格式錯誤的empVO物件,也存入req
				RequestDispatcher failureView = req.getRequestDispatcher("/frontstage/members/addMembers.jsp");
				failureView.forward(req, res);
				return;
			}

			/*************************** 2.開始查詢資料 ****************************************/
			MembersService memSvc = new MembersService();
//			/*************************** 2.開始新增資料 ***************************************/
			memVO = memSvc.addMembers(memberAccount,memberPassword,memberIntroduce,
					memberCellphone,memberMail, memberDate,memberAddress,memberCountry
					,memberStatus,buf,memberNickname,memberGender);

			/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
//			req.setAttribute("memVO", memVO); // 資料庫update成功後,正確的的empVO物件,存入req
			String url = "/frontstage/members/listAllMembers.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
			successView.forward(req, res);
		}
//
		if ("delete".equals(action)) { // 來自listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
				/***************************1.接收請求參數***************************************/
				Integer memberId = Integer.valueOf(req.getParameter("memberId"));
				
				/***************************2.開始刪除資料***************************************/
				MembersService memSvc = new MembersService();
				memSvc.deleteMember(memberId);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/frontstage/members/listAllMembers.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
		}
	}
}
