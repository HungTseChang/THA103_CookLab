package com.cooklab.news.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cooklab.news.model.NewsDTO;
import com.cooklab.util.JedisPoolUtil;
import com.google.gson.Gson;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@WebServlet("/newsAjax")
public class newsAjax extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// 將請求物件的編碼設置為UTF-8避免亂碼問題
		req.setCharacterEncoding("UTF-8");

		// 透過前端傳來的action觸發不同的功能
		String action = req.getParameter("action");

		// 創建Gson物件以便將資料打包為json格式回傳給前端
		Gson gson = new Gson();

		// 後台建立系統通知
		if ("insert".equals(action)) {
			// 創建Map物件放入錯誤訊息
			Map<String, String> errorMsgs = new HashMap<String, String>();

			// 連接Redis連線池取得連線資源，使用select切到指定的資料庫
			JedisPool jedisPool = JedisPoolUtil.getJedisPool();
			Jedis jedis = jedisPool.getResource();
			jedis.select(10);

			/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
			String newsTitle = req.getParameter("newsTitle").trim();
			if (newsTitle == null || newsTitle.trim().length() == 0) {
				errorMsgs.put("errTitle", "內容請勿空白");
			}

			String newsContent = req.getParameter("newsContent").trim();
			if (newsContent == null || newsContent.trim().length() == 0) {
				errorMsgs.put("errContent", "內容請勿空白");
			}

			// 錯誤驗證的訊息收集及回傳
			if (!errorMsgs.isEmpty()) {
				String errjson = gson.toJson(errorMsgs);
				res.setContentType("application/json");
				res.setCharacterEncoding("UTF-8");
				res.getWriter().write(errjson);
				return;
			}

			/*************************** 2.開始新增資料 ***************************************/
			Timestamp newsTime = new Timestamp(System.currentTimeMillis());
			System.out.println("新增的資料時間為:" + newsTime);

			NewsDTO news = new NewsDTO(newsTitle, newsContent, newsTime);
			// 因為Redis的list只能存放一個值，故將所需存入的資訊整合為一個json字串
			String newsData = gson.toJson(news);
			System.out.println("gson的轉換格式為:" + newsData);

			// 為了要保證每次新增的消息排序都是最前面，此處使用從左邊插入list的方式
			jedis.lpush("news", newsData);

			/*************************** 3.新增完成,回傳成功訊息回前端 ***********/
			String url = "/THA103_CookLab/dashboard/news/newslist.html";

			// 創建Map物件放入成功訊息
			Map<String, String> successMsg = new HashMap<String, String>();
			successMsg.put("success", "data transfer success");
			successMsg.put("url", url);

			String successjson = gson.toJson(successMsg);

			res.setContentType("application/json");
			res.setCharacterEncoding("UTF-8");
			res.getWriter().write(successjson);

			// 關閉Redis連線釋放資源
			jedis.close();
		}

		// 後台取得全部通知資料
		if ("getAll".equals(action)) {

			// 連接Redis連線池取得連線資源，使用select切到指定的資料庫
			JedisPool jedisPool = JedisPoolUtil.getJedisPool();
			Jedis jedis = jedisPool.getResource();
			jedis.select(10);

			// 宣告集合，將Redis資料取出
			List<String> newsList = jedis.lrange("news", 0, -1);

			// 使用FOR EACH取值並放置於集合中，此處用DTO打包以便直接讓Gson轉換為json物件
			List<NewsDTO> data = new ArrayList<NewsDTO>();
			for (String newsData : newsList) {
				NewsDTO news = gson.fromJson(newsData, NewsDTO.class);
				data.add(news);
			}

			// 使用Gson轉換為Json格式
			String Data = gson.toJson(data);

			// 回傳資料給Ajax進行進一步的處理
			res.setContentType("application/json");
			res.setCharacterEncoding("UTF-8");
			res.getWriter().write(Data);

			// 關閉Redis連線釋放資源
			jedis.close();
		}

		// 取得單一資料
		if ("getOne".equals(action)) {
			Integer newsindex = Integer.valueOf(req.getParameter("newsindex").trim());

			// 連接Redis連線池取得連線資源，使用select切到指定的資料庫
			JedisPool jedisPool = JedisPoolUtil.getJedisPool();
			Jedis jedis = jedisPool.getResource();
			jedis.select(10);

			// 將指定索引值的Redis資料取出
			String newsdata = jedis.lindex("news", newsindex);

			// 用DTO打包以便直接讓Gson轉換為json物件
			NewsDTO news = gson.fromJson(newsdata, NewsDTO.class);

			// 使用Gson轉換為Json格式
			String Data = gson.toJson(news);

			// 回傳資料給Ajax進行進一步的處理
			res.setContentType("application/json");
			res.setCharacterEncoding("UTF-8");
			res.getWriter().write(Data);

			// 關閉Redis連線釋放資源
			jedis.close();
		}

		// 更新資料
		if ("update".equals(action)) {
			// 連接Redis連線池取得連線資源，使用select切到指定的資料庫
			JedisPool jedisPool = JedisPoolUtil.getJedisPool();
			Jedis jedis = jedisPool.getResource();
			jedis.select(10);

			// 創建Map物件放入錯誤訊息
			Map<String, String> errorMsgs = new HashMap<String, String>();

			/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
			String newsTitle = req.getParameter("newsTitle").trim();
			if (newsTitle == null || newsTitle.trim().length() == 0) {
				errorMsgs.put("errTitle", "內容請勿空白");
			}

			String newsContent = req.getParameter("newsContent").trim();
			if (newsContent == null || newsContent.trim().length() == 0) {
				errorMsgs.put("errContent", "內容請勿空白");
			}

			Integer newsindex = Integer.valueOf(req.getParameter("newsindex").trim());

			Timestamp newsTime = null;
			String newsTimeStr = req.getParameter("newsTime").trim();
			if (!newsTimeStr.isEmpty()) {
				SimpleDateFormat inputFormat = new SimpleDateFormat("MMM dd, yyyy, h:mm:ss a", Locale.ENGLISH);
				try {
					Date date = inputFormat.parse(newsTimeStr);
					newsTime = new Timestamp(date.getTime());
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}

			// 錯誤驗證的訊息收集及回傳
			if (!errorMsgs.isEmpty()) {
				String errjson = gson.toJson(errorMsgs);
				res.setContentType("application/json");
				res.setCharacterEncoding("UTF-8");
				res.getWriter().write(errjson);
				return;
			}

			/*************************** 2.開始新增資料 ***************************************/
			NewsDTO news = new NewsDTO(newsTitle, newsContent, newsTime);
			// 因為Redis的list只能存放一個值，故將所需存入的資訊整合為一個json字串
			String newsData = gson.toJson(news);

			// 更新redis相應索引值的資料
			jedis.lset("news", newsindex, newsData);

			/*************************** 3.新增完成,回傳成功訊息回前端 ***********/
			String url = "/THA103_CookLab/dashboard/news/newslist.html";

			// 創建Map物件放入成功訊息
			Map<String, String> successMsg = new HashMap<String, String>();
			successMsg.put("success", "data transfer success");
			successMsg.put("url", url);

			String successjson = gson.toJson(successMsg);

			res.setContentType("application/json");
			res.setCharacterEncoding("UTF-8");
			res.getWriter().write(successjson);

			// 關閉Redis連線釋放資源
			jedis.close();
		}

		// 前端網頁批次取資料(每次10筆)-TODO
		if ("getten".equals(action)) {
			
			// 連接Redis連線池取得連線資源，使用select切到指定的資料庫
			JedisPool jedisPool = JedisPoolUtil.getJedisPool();
			Jedis jedis = jedisPool.getResource();
			jedis.select(10);
			
			int pageSize = 10;
			int currentPage = 1;
			int startIndex = (currentPage - 1) * pageSize;
			int endIndex = startIndex + pageSize - 1;

			// 获取指定范围内的key
			List<String> pageKeys = new ArrayList<>();
			int index = 0;
			for (String key : keys) {
			    if (index >= startIndex && index <= endIndex) {
			        pageKeys.add(key);
			    }
			    index++;
			}
			
			//取得當前頁數後續判斷需要取得第幾筆至第幾筆的資料
			String currentpage = req.getParameter("currentpage").trim();

			// 宣告集合，將Redis資料取出
			List<String> newsList = jedis.lrange("news", 0, -1);

			// 使用FOR EACH取值並放置於集合中，此處用DTO打包以便直接讓Gson轉換為json物件
			List<NewsDTO> data = new ArrayList<NewsDTO>();
			for (String newsData : newsList) {
				NewsDTO news = gson.fromJson(newsData, NewsDTO.class);
				data.add(news);
			}

			// 使用Gson轉換為Json格式
			String Data = gson.toJson(data);

			// 回傳資料給Ajax進行進一步的處理
			res.setContentType("application/json");
			res.setCharacterEncoding("UTF-8");
			res.getWriter().write(Data);

			// 關閉Redis連線釋放資源
			jedis.close();
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

}
