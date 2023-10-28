package com.cooklab.product.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cooklab.product.model.ProductService;
import com.cooklab.product.model.ProductVO;
import com.cooklab.util.JedisUtil;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import redis.clients.jedis.Jedis;
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

		if ("buttonadd1".equals(action)) { // 来自select_page.jsp的请求
			Integer productNo = Integer.valueOf(req.getParameter("productNo").trim());
			String quantity = req.getParameter("quantity");
			String memberNo = "1"; // 举例，你需要动态设置会员编号

			System.out.println(productNo);

			// 在Redis中检查是否已经存在相同产品
			JedisPool jedisPool = JedisUtil.getJedisPool();
			Jedis jedis = jedisPool.getResource();

			try {
				// 选择数据库
				jedis.select(1);

				String cartKey = "cart:" + memberNo;
				String productKey = "product:" + productNo;
				String existingProduct = jedis.hget(cartKey, productKey);

				if (existingProduct != null) {
					System.out.println("重复商品累加");
					JsonObject existingProductJson = new JsonParser().parse(existingProduct).getAsJsonObject();
					int existingQuantity = existingProductJson.get("quantity").getAsInt();

					// 增加现有数量
					int newQuantity = existingQuantity + 1;
					existingProductJson.addProperty("quantity", newQuantity);

					// 更新 Redis 中的产品
					jedis.hset(cartKey, productKey, existingProductJson.toString());

					// 设置过期时间（如果需要）
					jedis.expire(cartKey, 3600); // 设置为1小时过期

					// 输出信息
					System.out.println("存储到Redis成功!");
				} else {
					// 产品不存在，将其添加到 Redis
					ProductService productSvc = new ProductService();
					ProductVO productinfo = productSvc.getOneProduct(productNo);

					String productName = productinfo.getProductName();
					String productPrice = productinfo.getProductPrice().toString();
					String productImage = "";
					// 读取图像文件并编码为Base64字符串
					byte[] productPicture = productinfo.getProductPicture();
					if (productPicture != null) {
						productImage = Base64.getEncoder().encodeToString(productPicture);
					} else {
						// 处理 productPicture 为 null 的情况，例如给出一个默认值或者其他操作
						productImage = "";
					}
					// 构建一个 JSON 对象
					JsonObject jsonProduct = new JsonObject();
					jsonProduct.addProperty("memberNo", memberNo);
					jsonProduct.addProperty("productNo", productNo);
					jsonProduct.addProperty("productName", productName);
					jsonProduct.addProperty("productPrice", productPrice);
					jsonProduct.addProperty("productImage", productImage);
					jsonProduct.addProperty("quantity", Integer.valueOf(quantity));

					// 将 JSON 对象存储到 Redis
					jedis.hset(cartKey, productKey, jsonProduct.toString());

					// 设置过期时间（如果需要）
					jedis.expire(cartKey, 3600); // 设置为1小时过期

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
		if ("buttonadd2".equals(action)) { // 来自select_page.jsp的请求
			Integer productNo = Integer.valueOf(req.getParameter("productNo").trim());
			Integer quantity = Integer.valueOf(req.getParameter("quantity"));
			String memberNo = "1"; // 举例，你需要动态设置会员编号

			System.out.println(productNo);

			// 在Redis中检查是否已经存在相同产品
			JedisPool jedisPool = JedisUtil.getJedisPool();
			Jedis jedis = jedisPool.getResource();

			try {
				// 选择数据库
				jedis.select(1);

				String cartKey = "cart:" + memberNo;
				String productKey = "product:" + productNo;
				String existingProduct = jedis.hget(cartKey, productKey);

				if (existingProduct != null) {
					System.out.println("重复商品累加");
					JsonObject existingProductJson = new JsonParser().parse(existingProduct).getAsJsonObject();
					int existingQuantity = existingProductJson.get("quantity").getAsInt();

					// 增加现有数量
					int newQuantity = existingQuantity + quantity;
					existingProductJson.addProperty("quantity", newQuantity);

					// 更新 Redis 中的产品
					jedis.hset(cartKey, productKey, existingProductJson.toString());

					// 设置过期时间（如果需要）
					jedis.expire(cartKey, 3600); // 设置为1小时过期

					// 输出信息
					System.out.println("存储到Redis成功!");
				} else {
					// 产品不存在，将其添加到 Redis
					ProductService productSvc = new ProductService();
					ProductVO productinfo = productSvc.getOneProduct(productNo);

					String productName = productinfo.getProductName();
					String productPrice = productinfo.getProductPrice().toString();
					String productImage = "";
					// 读取图像文件并编码为Base64字符串
					byte[] productPicture = productinfo.getProductPicture();
					if (productPicture != null) {
						productImage = Base64.getEncoder().encodeToString(productPicture);
					} else {
						// 处理 productPicture 为 null 的情况，例如给出一个默认值或者其他操作
						productImage = "";
					}

					// 构建一个 JSON 对象
					JsonObject jsonProduct = new JsonObject();
					jsonProduct.addProperty("memberNo", memberNo);
					jsonProduct.addProperty("productNo", productNo);
					jsonProduct.addProperty("productName", productName);
					jsonProduct.addProperty("productPrice", productPrice);
					jsonProduct.addProperty("productImage", productImage);
					jsonProduct.addProperty("quantity", Integer.valueOf(quantity));

					// 将 JSON 对象存储到 Redis
					jedis.hset(cartKey, productKey, jsonProduct.toString());

					// 设置过期时间（如果需要）
					jedis.expire(cartKey, 3600); // 设置为1小时过期

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

		if ("cartsearch".equals(action)) {
			JedisPool jedisPool = JedisUtil.getJedisPool();
			Jedis jedis = jedisPool.getResource();

			try {
				String memberNo = "1"; // 这里设置你的会员编号
				String cartKey = "cart:" + memberNo; // 使用会员编号构建购物车的Redis键

				jedis.select(1);
				Map<String, String> cartData = jedis.hgetAll(cartKey);
				System.out.println(cartKey);
				System.out.println(cartData);
				System.out.println(cartData);
				if (!cartData.isEmpty()) {
					JsonArray cartArray = new JsonArray();

					for (Map.Entry<String, String> entry : cartData.entrySet()) {
						String productKey = entry.getKey();
						String productJson = entry.getValue();

						// 构建 JSON 对象
						JsonObject productObject = new JsonParser().parse(productJson).getAsJsonObject();
						cartArray.add(productObject);
					}

					// 将 JSON 数组写入响应
					res.setContentType("application/json");
					res.setCharacterEncoding("UTF-8");
					res.getWriter().write(cartArray.toString());
				} else {
					// 购物车为空，返回空数组或适当的响应
					res.setContentType("application/json");
					res.setCharacterEncoding("UTF-8");
					res.getWriter().write("[]"); // 返回空数组或适当的响应
				}
			} finally {
				jedis.close(); // 记得关闭Jedis连接，归还到连接池
			}
		}

		if ("remove".equals(action)) {

			int productNo = Integer.parseInt(req.getParameter("productId"));
			String memberNo = "1"; // 假设需要设置会员编号

			JedisPool jedisPool = JedisUtil.getJedisPool();
			Jedis jedis = jedisPool.getResource();

			Map<String, String> itemMap = new HashMap<>();
			try {
				jedis.select(1);

				String cartKey = "cart:" + memberNo;
				String productKey = "product:" + productNo;

				Long deletedCount = jedis.hdel(cartKey, productKey);

				if (deletedCount > 0) {
					itemMap.put("message", "success");
					System.out.println("刪除成功");
				} else {
					itemMap.put("message", "false");
					System.out.println("刪除失敗");
				}
			} finally {
				jedis.close();
			}

			Gson gson = new Gson();
			String jsonData = gson.toJson(itemMap);
			System.out.println(jsonData);

			res.setContentType("application/json");
			res.setCharacterEncoding("UTF-8");
			res.getWriter().write(jsonData);
		}
		if ("cartsearch2".equals(action)) {
			JedisPool jedisPool = JedisUtil.getJedisPool();
			Jedis jedis = jedisPool.getResource();

			try {
				String memberNo = "1"; // 在这里设置你的会员编号
				String cartKey = "cart:" + memberNo; // 使用会员编号构建购物车的 Redis 键

				jedis.select(1);
				String productKeysStr = req.getParameter("productNo"); // 获取传递的产品编号字符串

				// 将逗号分隔的字符串拆分为整数数组
				String[] productKeysStrArray = productKeysStr.split(",");
				int[] productKeys = new int[productKeysStrArray.length];
				for (int i = 0; i < productKeysStrArray.length; i++) {
					productKeys[i] = Integer.parseInt(productKeysStrArray[i]);
				}

				JsonArray cartArray = new JsonArray();
				for (int productKey : productKeys) {
					String productJson = jedis.hget(cartKey, "product:" + productKey);

					if (productJson != null) {
						JsonObject productObject = new JsonParser().parse(productJson).getAsJsonObject();
						cartArray.add(productObject);
					}
				}
				System.out.println(cartArray);
				// 将 JSON 数组写入响应
				res.setContentType("application/json");
				res.setCharacterEncoding("UTF-8");
				res.getWriter().write(cartArray.toString());
			} finally {
				jedis.close(); // 记得关闭 Jedis 连接，归还到连接池
			}
		}

	}

}
