package com.cooklab.advertise.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.cooklab.advertise.model.AdvertiseService;
import com.cooklab.advertise.model.AdvertiseVO;
import com.cooklab.product.model.ProductService;
import com.google.gson.Gson;

/**
 * Servlet implementation class AdvertiseServlet
 */
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
@WebServlet("/AdvertiseServlet")
public class AdvertiseServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			String str = req.getParameter("advertise_no");
			if (str == null || (str.trim()).length() == 0) {
				errorMsgs.add("請輸入廣告編號");
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
				errorMsgs.add("廣告編號格式不正確");
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
				errorMsgs.add("查無資料");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/advertise/select_page.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
			req.setAttribute("advertiseVO", advertiseVO); // 資料庫取出的advertiseVO物件,存入req
			String url = "/mazer-main/dist/advertise/advertise_getone.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
			successView.forward(req, res);
		}

		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 ****************************************/
			Integer advertiseno = Integer.valueOf(req.getParameter("advertise_no"));

			/*************************** 2.開始查詢資料 ****************************************/
			AdvertiseService adSvc = new AdvertiseService();
			adSvc.getOneAd(advertiseno);

			/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/

			String url = "/mazer-main/dist/advertise/advertise_getone.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_advertise_input.jsp
			successView.forward(req, res);
		}

		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			Integer advertiseno = Integer.valueOf(req.getParameter("advertise_no").trim());

			String advertisename = req.getParameter("advertise_name");
			String advertisenameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
			if (advertisename == null || advertisename.trim().length() == 0) {
				errorMsgs.add("廣告名稱: 請勿空白");
			} else if (!advertisename.trim().matches(advertisenameReg)) { // 以下練習正則(規)表示式(regular-expression)
				errorMsgs.add("廣告名稱: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
			}
			java.sql.Timestamp advertiseShelfTime = null;
			String advertiseShelfTimeStr = req.getParameter("advertise_shelf_time");

			if (advertiseShelfTimeStr != null) {
				try {
					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
					LocalDateTime localDateTime = LocalDateTime.parse(advertiseShelfTimeStr, formatter);
					advertiseShelfTime = Timestamp.valueOf(localDateTime);
				} catch (DateTimeParseException e) {
					e.printStackTrace();
					advertiseShelfTime = new java.sql.Timestamp(System.currentTimeMillis());
				}
			} else {
				// 处理参数为 null 的情况，可以给出错误提示或执行适当的操作
			}

			java.sql.Timestamp advertiseOffsaleTime = null;
			String advertiseOffsaleTimeStr = req.getParameter("advertise_offsale_time");

			if (advertiseOffsaleTimeStr != null) {
				try {
					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
					LocalDateTime localDateTime = LocalDateTime.parse(advertiseOffsaleTimeStr, formatter);
					advertiseOffsaleTime = Timestamp.valueOf(localDateTime);
				} catch (DateTimeParseException e) {
					e.printStackTrace();
					advertiseOffsaleTime = new java.sql.Timestamp(System.currentTimeMillis());
				}
			} else {
				// 处理参数为 null 的情况，可以给出错误提示或执行适当的操作
			}
			Part filePart = req.getPart("advertise_img");
			String fileName = filePart.getSubmittedFileName();
			System.out.println(fileName);
			System.out.println(filePart);
			byte[] imageBytes = null;

			if (filePart != null) {
				fileName = filePart.getSubmittedFileName();
				if (fileName != null && !fileName.isEmpty()) {
					System.out.println("New file uploaded");
					// 处理新文件上传逻辑
					InputStream fileContent = filePart.getInputStream();
					ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
					byte[] buffer = new byte[4096];
					int bytesRead;

					while ((bytesRead = fileContent.read(buffer)) != -1) {
						outputStream.write(buffer, 0, bytesRead);
					}

					imageBytes = outputStream.toByteArray();
				} else {
					System.out.println("No new file uploaded");
					AdvertiseService advertiseSvc = new AdvertiseService();
					imageBytes = advertiseSvc.getOneAd(advertiseno).getAdvertiseImg();
					System.out.println("old picture");
					// 处理未上传新文件的逻辑
				}
			}
			String advertiseurl = req.getParameter("advertise_url");

			if (advertiseurl == null || advertiseurl.trim().length() == 0) {

				errorMsgs.add("請輸入圖片鏈結");
			}

			AdvertiseVO advertiseVO = new AdvertiseVO();

			advertiseVO.setAdvertiseNo(advertiseno);
			advertiseVO.setAdvertiseName(advertisename);
			advertiseVO.setAdvertiseShelfTime(advertiseShelfTime);
			advertiseVO.setAdvertiseOffsaleTime(advertiseOffsaleTime);
			advertiseVO.setAdvertiseImg(imageBytes);
			advertiseVO.setAdvertiseUrl(advertiseurl);

			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("advertiseVO", advertiseVO); // 含有輸入格式錯誤的advertiseVO物件,也存入req
				RequestDispatcher failureView = req.getRequestDispatcher("/advertise/update_advertise_input.jsp");
				failureView.forward(req, res);
				return; // 程式中斷
			}

			/*************************** 2.開始修改資料 *****************************************/
			AdvertiseService adSvc = new AdvertiseService();
			adSvc.updateAd(advertiseVO);

			/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
			req.setAttribute("advertiseVO", advertiseVO); // 資料庫update成功後,正確的的advertiseVO物件,存入req
			String url = "/mazer-main/dist/advertise/advertise_allview.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneAdvertise.jsp
			successView.forward(req, res);
		}

		if ("insert".equals(action)) { // 來自addAdvertise.jsp的請求
			System.out.println("sssss");
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/

			String advertisename = req.getParameter("advertise_name");
			String advertisenameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
			if (advertisename == null || advertisename.trim().length() == 0) {
				errorMsgs.add("廣告名稱: 請勿空白");
			} else if (!advertisename.trim().matches(advertisenameReg)) { // 以下練習正則(規)表示式(regular-expression)
				errorMsgs.add("廣告名稱: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
			}
			java.sql.Timestamp advertiseShelfTime = null;
			String advertiseShelfTimeStr = req.getParameter("advertise_shelf_time");

			if (advertiseShelfTimeStr != null) {
				try {
					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
					LocalDateTime localDateTime = LocalDateTime.parse(advertiseShelfTimeStr, formatter);
					advertiseShelfTime = Timestamp.valueOf(localDateTime);
				} catch (DateTimeParseException e) {
					e.printStackTrace();
					advertiseShelfTime = new java.sql.Timestamp(System.currentTimeMillis());
				}
			} else {
				// 处理参数为 null 的情况，可以给出错误提示或执行适当的操作
			}
			java.sql.Timestamp advertiseOffsaleTime = null;
			String advertiseOffsaleTimeStr = req.getParameter("advertise_offsale_time");

			if (advertiseOffsaleTimeStr != null) {
				try {
					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
					LocalDateTime localDateTime = LocalDateTime.parse(advertiseOffsaleTimeStr, formatter);
					advertiseOffsaleTime = Timestamp.valueOf(localDateTime);
				} catch (DateTimeParseException e) {
					e.printStackTrace();
					advertiseOffsaleTime = new java.sql.Timestamp(System.currentTimeMillis());
				}
			} else {
				// 处理参数为 null 的情况，可以给出错误提示或执行适当的操作
			}
			String advertiseimg = null;
			if (advertiseimg == null || advertiseimg.trim().length() == 0) {

				errorMsgs.add("請輸入圖片");
			}
			String advertiseurl = req.getParameter("advertise_url");

			if (advertiseurl == null || advertiseurl.trim().length() == 0) {

				errorMsgs.add("請輸入圖片鏈結");
			}
			// 處理上傳圖片
			System.out.println("ccccc");
			Part filePart = req.getPart("advertise_img");
			InputStream in = filePart.getInputStream();
			byte[] buf = new byte[in.available()]; // byte[] buf = in.readAllBytes(); // Java 9 的新方法
			in.read(buf);
			in.close();

			AdvertiseVO advertiseVO = new AdvertiseVO();
			advertiseVO.setAdvertiseName(advertisename);
			advertiseVO.setAdvertiseShelfTime(advertiseShelfTime);
			advertiseVO.setAdvertiseOffsaleTime(advertiseOffsaleTime);
			advertiseVO.setAdvertiseImg(buf);
			advertiseVO.setAdvertiseUrl(advertiseurl);

			// Send the use back to the form, if there were errors
//			if (!errorMsgs.isEmpty()) {
//				System.out.println("bbbbb");
//				req.setAttribute("advertiseVO", advertiseVO); // 含有輸入格式錯誤的empVO物件,也存入req
//				RequestDispatcher failureView = req.getRequestDispatcher("/mazer-main/dist/advertise/advertise_set.jsp");
//				failureView.forward(req, res);
//				return; // 程式中斷
//			}

			/*************************** 2.開始新增資料 ***************************************/
			AdvertiseService adSvc = new AdvertiseService();
			adSvc.addAd(advertiseVO);

			/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
			String url = "/mazer-main/dist/advertise/advertise_allview.jsp";
			System.out.println("aaaaaaa");
			RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
			successView.forward(req, res);
		}

		if ("delete".equals(action)) { // 來自listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 ***************************************/
			Integer advertiseno = Integer.valueOf(req.getParameter("advertise_no").trim());
			AdvertiseVO advertiseVO = new AdvertiseVO();
			advertiseVO.setAdvertiseNo(advertiseno);

			/*************************** 2.開始刪除資料 ***************************************/
			AdvertiseService adSvc = new AdvertiseService();
			adSvc.deleteAd(advertiseVO);

			/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
			String url = "/mazer-main/dist/advertise/advertise_allview.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
			successView.forward(req, res);
		}
	}
}