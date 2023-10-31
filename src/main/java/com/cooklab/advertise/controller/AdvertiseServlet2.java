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
import java.util.Base64;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
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
import com.cooklab.product.model.ProductVO;
import com.google.gson.Gson;

/**
 * Servlet implementation class AdvertiseServlet
 */
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
@WebServlet("/AdvertiseServlet2")
public class AdvertiseServlet2 extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		

		if ("getjson".equals(action)) {

			/***************************
			 * 2.開始查詢資料
			 *****************************************/
			AdvertiseService adSvc = new AdvertiseService();
			List<AdvertiseVO> listadvertiseVO = adSvc.upAd();

			List<Map<String, String>> dataMapList = new ArrayList<>();

			for (AdvertiseVO item : listadvertiseVO) {

				Map<String, String> itemMap = new HashMap<>();

				String AdvertiseNo = item.getAdvertiseNo().toString();
				itemMap.put("advertise_no", AdvertiseNo);

				String AdvertiseName = item.getAdvertiseName();
				itemMap.put("advertise_name", AdvertiseName);

				byte[] AdvertiseImg = item.getAdvertiseImg();
				if (AdvertiseImg != null) {
					String AdvertisePicture = Base64.getEncoder().encodeToString(AdvertiseImg);
					itemMap.put("advertise_img", AdvertisePicture);
				} else {
					itemMap.put("advertise_img", "");
				}

				String AdvertiseUrl = item.getAdvertiseUrl();
				itemMap.put("advertise_url", AdvertiseUrl);
	
				

				if (item.getAdvertiseShelfTime() != null) {
					String AdvertiseShelfTime = item.getAdvertiseShelfTime().toString();
					itemMap.put("advertise_shelf_time", AdvertiseShelfTime);
				} else {
					itemMap.put("advertise_shelf_time", "無設定");
				}

				if (item.getAdvertiseOffsaleTime() != null) {
					String AdvertiseOffsaleTime = item.getAdvertiseOffsaleTime().toString();
					itemMap.put("advertise_shelf_time", AdvertiseOffsaleTime);
				} else {
					itemMap.put("advertise_shelf_time", "無設定");
				}

				
				// HashMap 放入列表
				dataMapList.add(itemMap);
			}
			System.out.println(dataMapList);
			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/

			Gson gson = new Gson();
			String jsonData = gson.toJson(dataMapList);
			System.out.println(jsonData);
			res.setContentType("application/json");
			res.setCharacterEncoding("UTF-8");
			res.getWriter().write(jsonData);
		}
	
	}
}