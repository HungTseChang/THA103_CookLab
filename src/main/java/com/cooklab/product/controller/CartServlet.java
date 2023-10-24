package com.cooklab.product.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import com.cooklab.util.JedisUtil;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import redis.clients.jedis.JedisPool;

@WebServlet("/CartServlet")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
//當數據量大於fileSizeThreshold值時，內容將被寫入磁碟
//上傳過程中無論是單個文件超過maxFileSize值，或者上傳的總量大於maxRequestSize 值都會拋出IllegalStateException 異常
public class CartServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		//主頁 或 搜尋頁 購物車按鈕直接新增 (1筆商品)
		if ("buttonadd1".equals(action)) { // 來自select_page.jsp的請求

			String productNo = req.getParameter("productNo");

			// 在Redis中检查是否已经存在相同产品
			JedisPool jedisPool = JedisUtil.getJedisPool();
			Jedis jedis = jedisPool.getResource();

			try {

				String existingProduct = jedis.get("product:" + productNo);

				if (existingProduct != null) {
					// 如果产品已经存在，将其解析为 JSON 对象
					JsonObject existingProductJson = new JsonParser().parse(existingProduct).getAsJsonObject();
					int existingQuantity = existingProductJson.get("quantity").getAsInt();

					// 增加现有数量
					int newQuantity = existingQuantity + 1;
					existingProductJson.addProperty("quantity", newQuantity);

					// 更新 Redis 中的产品
					jedis.set("product:" + productNo, existingProductJson.toString());
					
					// 设置过期时间（如果需要）
					jedis.expire("product:" + productNo, 3600); // 设置为1小时过期
					
					// 输出信息
					System.out.println("存储到Redis成功!");
				} else {
					// 产品不存在，将其添加到 Redis
					String productName = req.getParameter("productName");
					String productPrice = req.getParameter("productPrice");
					String productImage = req.getParameter("productImage");
					String quantity = req.getParameter("quantity");

					// 构建一个 JSON 对象
					JsonObject jsonProduct = new JsonObject();
					jsonProduct.addProperty("productNo", productNo);
					jsonProduct.addProperty("productName", productName);
					jsonProduct.addProperty("productPrice", productPrice);
					jsonProduct.addProperty("productImage", productImage);
					jsonProduct.addProperty("quantity", quantity);

					// 将 JSON 对象存储到 Redis
					jedis.set("product:" + productNo, jsonProduct.toString());

					// 设置过期时间（如果需要）
					jedis.expire("product:" + productNo, 3600); // 设置为1小时过期

					// 输出信息
					System.out.println("存储到Redis成功!");
				}
				// 向前端发送成功消息
				res.setContentType("application/json");
				res.setCharacterEncoding("UTF-8");
				String successMessage = "{\"message\": \"成功加入购物车\"}";
				res.getWriter().write(successMessage);
			} finally {
				jedis.close(); // 记得关闭Jedis连接，归还到连接池
			}

		}

		//特定商品數量 新增至購物車
		if ("buttonadd2".equals(action)) { // 从商品详情页面的请求

			String productNo = req.getParameter("productNo");
			String quantity = req.getParameter("quantity"); // 从AJAX请求中获取数量
			// 在Redis中检查是否已经存在相同产品
			JedisPool jedisPool = JedisUtil.getJedisPool();
			Jedis jedis = jedisPool.getResource();
		    try {   	
		    	String existingProduct = jedis.get("product:" + productNo);  	
		    	if (existingProduct != null) {
					// 如果产品已经存在，将其解析为 JSON 对象
					JsonObject existingProductJson = new JsonParser().parse(existingProduct).getAsJsonObject();
					int existingQuantity = existingProductJson.get("quantity").getAsInt();

					// 增加现有数量
					int newQuantity = existingQuantity + Integer.parseInt(quantity);
					existingProductJson.addProperty("quantity", newQuantity);

					// 更新 Redis 中的产品
					jedis.set("product:" + productNo, existingProductJson.toString());
					
					// 设置过期时间（如果需要）
					jedis.expire("product:" + productNo, 3600); // 设置为1小时过期
					
					// 输出信息
					System.out.println("存储到Redis成功!");
				} else {
					// 产品不存在，将其添加到 Redis
					String productName = req.getParameter("productName");
					String productPrice = req.getParameter("productPrice");
					String productImage = req.getParameter("productImage");
					String quantitys = req.getParameter("quantity");

					// 构建一个 JSON 对象
					JsonObject jsonProduct = new JsonObject();
					jsonProduct.addProperty("productNo", productNo);
					jsonProduct.addProperty("productName", productName);
					jsonProduct.addProperty("productPrice", productPrice);
					jsonProduct.addProperty("productImage", productImage);
					jsonProduct.addProperty("quantity", quantitys);

					// 将 JSON 对象存储到 Redis
					jedis.set("product:" + productNo, jsonProduct.toString());

					// 设置过期时间（如果需要）
					jedis.expire("product:" + productNo, 3600); // 设置为1小时过期

					// 输出信息
					System.out.println("存储到Redis成功!");
				}
		    	      
		     // 向前端发送成功消息
				res.setContentType("application/json");
				res.setCharacterEncoding("UTF-8");
				String successMessage = "{\"message\": \"成功加入购物车\"}";
				res.getWriter().write(successMessage);
		    } catch (NumberFormatException e) {
		        // 处理数量无效的情况
		        res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		        res.getWriter().write("无效的数量");
		    }
		}

		
	}

}
