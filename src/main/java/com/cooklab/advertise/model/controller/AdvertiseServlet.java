package com.cooklab.advertise.model.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cooklab.advertise.model.AdvertiseService;
import com.cooklab.advertise.model.AdvertiseVO;

import com.google.gson.Gson;

/**
 * Servlet implementation class AdvertiseServlet
 */
@WebServlet("/AdvertiseServlet")
public class AdvertiseServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			String str = req.getParameter("advertiseno");
			if (str == null || (str.trim()).length() == 0) {
				errorMsgs.put("advertiseno", "請輸入廣告編號");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/advertise/select_page.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			Integer advertiseno = null;
			try {
				advertiseno = Integer.valueOf(str);
			} catch (Exception e) {
				errorMsgs.put("advertise", "廣告編號格式不正確");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/advertise/select_page.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			/*************************** 2.開始查詢資料 *****************************************/
			AdvertiseService adSvc = new AdvertiseService();
			AdvertiseVO advertiseVO = adSvc.getOneAd(advertiseno);
			if (advertiseVO == null) {
				errorMsgs.put("advertiseno", "查無資料");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/advertise/select_page.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
			req.setAttribute("advertiseVO", advertiseVO); // 資料庫取出的advertiseVO物件,存入req
			String url = "/advertise/listOneAdvertise.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
			successView.forward(req, res);
		}

		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求

			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 ****************************************/
			Integer advertiseno = Integer.valueOf(req.getParameter("advertiseno"));

			/*************************** 2.開始查詢資料 ****************************************/
			AdvertiseService adSvc = new AdvertiseService();
			AdvertiseVO advertiseVO = adSvc.getOneAd(advertiseno);

			/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/

			String url = "/advertise/update_advertise_input.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_advertise_input.jsp
			successView.forward(req, res);
		}

		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求

			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			Integer advertiseno = Integer.valueOf(req.getParameter("advertiseno").trim());

			String advertisename = req.getParameter("advertisename");
			String advertisenameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
			if (advertisename == null || advertisename.trim().length() == 0) {
				errorMsgs.put("advertisename", "廣告名稱: 請勿空白");
			} else if (!advertisename.trim().matches(advertisenameReg)) { // 以下練習正則(規)表示式(regular-expression)
				errorMsgs.put("advertisename", "廣告名稱: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
			}

			java.sql.Timestamp advertiseshelftime = null;
			try {
				advertiseshelftime = java.sql.Timestamp.valueOf(req.getParameter("advertiseshelftime").trim());
			} catch (IllegalArgumentException e) {
				errorMsgs.put("advertiseshelftime", "請輸入廣告上架日期");
			}

			java.sql.Timestamp advertiseoffsaletime = null;
			try {
				advertiseoffsaletime = java.sql.Timestamp.valueOf(req.getParameter("advertiseoffsaletime").trim());
			} catch (IllegalArgumentException e) {
				errorMsgs.put("advertiseoffsaletime", "請輸入廣告下架日期");
			}
			String advertiseimg = null;
			if (advertiseimg == null || advertiseimg.trim().length() == 0) {

				errorMsgs.put("advertiseimg", "請輸入圖片");
			}

			String advertiseurl = null;
			if (advertiseurl == null || advertiseurl.trim().length() == 0) {

				errorMsgs.put("advertiseurl", "請輸入圖片鏈結");
			}
			
			AdvertiseVO advertiseVO = new AdvertiseVO();
			
			advertiseVO.setAdvertiseNo(advertiseno);
			advertiseVO.setAdvertiseName(advertisename);
			advertiseVO.setAdvertiseShelfTime(advertiseshelftime);
			advertiseVO.setAdvertiseOffsaleTime(advertiseoffsaletime);
			advertiseVO.setAdvertiseImg(advertiseimg);
			advertiseVO.setAdvertiseUrl(advertiseurl);
		

			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("advertiseVO", advertiseVO); // 含有輸入格式錯誤的empVO物件,也存入req
				RequestDispatcher failureView = req.getRequestDispatcher("/advertise/update_advertise_input.jsp");
				failureView.forward(req, res);
				return; // 程式中斷
			}


			/*************************** 2.開始修改資料 *****************************************/
			AdvertiseService adSvc = new AdvertiseService();
			advertiseVO = adSvc.updateAd( advertisename, advertiseshelftime, advertiseoffsaletime, advertiseimg,advertiseurl);
			
			

			/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
			req.setAttribute("advertiseVO", advertiseVO); // 資料庫update成功後,正確的的advertiseVO物件,存入req
			String url = "/advertise/listOneAdvertise.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneAdvertise.jsp
			successView.forward(req, res);
		}

		if ("insert".equals(action)) { // 來自addAdvertise.jsp的請求

			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);

			/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
			Integer advertiseno = Integer.valueOf(req.getParameter("advertiseno").trim());

			String advertisename = req.getParameter("advertisename");
			String advertisenameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
			if (advertisename == null || advertisename.trim().length() == 0) {
				errorMsgs.put("advertisename", "廣告名稱: 請勿空白");
			} else if (!advertisename.trim().matches(advertisenameReg)) { // 以下練習正則(規)表示式(regular-expression)
				errorMsgs.put("advertisename", "廣告名稱: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
			}

			java.sql.Timestamp advertiseshelftime = null;
			try {
				advertiseshelftime = java.sql.Timestamp.valueOf(req.getParameter("advertiseshelftime").trim());
			} catch (IllegalArgumentException e) {
				errorMsgs.put("advertiseshelftime", "請輸入廣告上架日期");
			}

			java.sql.Timestamp advertiseoffsaletime = null;
			try {
				advertiseoffsaletime = java.sql.Timestamp.valueOf(req.getParameter("advertiseoffsaletime").trim());
			} catch (IllegalArgumentException e) {
				errorMsgs.put("advertiseoffsaletime", "請輸入廣告下架日期");
			}
			String advertiseimg = null;
			if (advertiseimg == null || advertiseimg.trim().length() == 0) {

				errorMsgs.put("advertiseimg", "請輸入圖片");
			}

			String advertiseurl = null;
			if (advertiseurl == null || advertiseurl.trim().length() == 0) {

				errorMsgs.put("advertiseurl", "請輸入圖片鏈結");
			}

			AdvertiseVO advertiseVO = new AdvertiseVO();
			advertiseVO.setAdvertiseNo(advertiseno);
			advertiseVO.setAdvertiseName(advertisename);
			advertiseVO.setAdvertiseShelfTime(advertiseshelftime);
			advertiseVO.setAdvertiseOffsaleTime(advertiseoffsaletime);
			advertiseVO.setAdvertiseImg(advertiseimg);
			advertiseVO.setAdvertiseUrl(advertiseurl);
		

			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("advertiseVO", advertiseVO); // 含有輸入格式錯誤的empVO物件,也存入req
				RequestDispatcher failureView = req.getRequestDispatcher("/advertise/update_advertise_input.jsp");
				failureView.forward(req, res);
				return; // 程式中斷
			}

			/*************************** 2.開始新增資料 ***************************************/
			AdvertiseService advertiseSvc = new AdvertiseService();
			advertiseSvc.addAd(advertisename, advertiseshelftime, advertiseoffsaletime, advertiseimg, advertiseurl);

			/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
			String url = "/emp/listAllEmp.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
			successView.forward(req, res);
		}

		if ("delete".equals(action)) { // 來自listAllEmp.jsp

			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 ***************************************/
			Integer advertiseno = Integer.valueOf(req.getParameter("advertiseno"));

			/*************************** 2.開始刪除資料 ***************************************/
			AdvertiseService advertiseSvc = new AdvertiseService();
			advertiseSvc.deleteAd(advertiseno);

			/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
			String url = "/advertise/listAllAdvertise.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
			successView.forward(req, res);
		}
	}
}