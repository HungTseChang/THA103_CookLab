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

@WebServlet("/NSFront")
public class NewsServletFront extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// 將請求物件的編碼設置為UTF-8避免亂碼問題
		req.setCharacterEncoding("UTF-8");

		// 透過前端傳來的action觸發不同的功能
		String action = req.getParameter("action");

		// 創建Gson物件以便將資料打包為json格式回傳給前端
		Gson gson = new Gson();

		// 前端網頁批次取資料(每次10筆)
		if ("getten".equals(action)) {

			// 連接Redis連線池取得連線資源，使用select切到指定的資料庫
			JedisPool jedisPool = JedisPoolUtil.getJedisPool();
			Jedis jedis = jedisPool.getResource();
			jedis.select(10);

			// 取得當前頁數後續判斷需要取得第幾筆至第幾筆的資料
			Integer currentPage = Integer.valueOf(req.getParameter("currentPage").trim());

			// 設定每10筆一批資料，並計算需要取得的資料索引值區間
			Integer pageSize = 10;
			Integer startIndex = (currentPage - 1) * pageSize;
			Integer endIndex = startIndex + pageSize - 1;

			// 計算每10筆資料共幾頁，傳回前端方便顯示頁數
			Long totalItems = jedis.llen("news");
			Long totalPages = (totalItems + pageSize - 1) / pageSize;

			// 宣告集合，將Redis資料取出
			List<String> newsList = jedis.lrange("news", startIndex, endIndex);

			// 使用FOR EACH取值並放置於集合中，此處用DTO打包以便直接讓Gson轉換為json物件
			List<NewsDTO> redisdata = new ArrayList<NewsDTO>();
			for (String newsData : newsList) {
				NewsDTO news = gson.fromJson(newsData, NewsDTO.class);
				redisdata.add(news);
			}

			// 宣告Map物件，將處理好的資料與頁數資訊進行包裝
			Map<String, Object> data = new HashMap<String, Object>();
			data.put("totalPages", totalPages);
			data.put("redisdata", redisdata);

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
