package com.cooklab.product.controller;

import java.io.IOException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
		
		HttpSession session = req.getSession();
		String userId = session.getAttribute("userId").toString();
		
		
		if ("buttonadd1".equals(action)) {
			Integer productNo = Integer.valueOf(req.getParameter("productNo").trim());
			String quantity = req.getParameter("quantity");

			//動態會員編號
			String memberNo = userId; 

			System.out.println(productNo);

			JedisPool jedisPool = JedisUtil.getJedisPool();
			Jedis jedis = jedisPool.getResource();

			try {
				jedis.select(1);

				String cartKey = "cart:" + memberNo;
				String productKey = "product:" + productNo;
				String existingProduct = jedis.hget(cartKey, productKey);

				if (existingProduct != null) {
					System.out.println("重复商品累加");
					
					//使用 Gson 函式庫來解析 Redis 從資料庫擷取的 JSON 字串，將其轉換為 Gson 可以理解的 JSON 物件
					JsonObject existingProductJson = new JsonParser().parse(existingProduct).getAsJsonObject();
					int existingQuantity = existingProductJson.get("quantity").getAsInt();

					int newQuantity = existingQuantity + 1;
					existingProductJson.addProperty("quantity", newQuantity);

					// 更新
					jedis.hset(cartKey, productKey, existingProductJson.toString());

					// 過期時間
					jedis.expire(cartKey, 3600);

					System.out.println("儲存成功Redis!");
				} else {
					// 不存在
					ProductService productSvc = new ProductService();
					ProductVO productinfo = productSvc.getOneProduct(productNo);

					String productName = productinfo.getProductName();
					String productPrice = productinfo.getProductPrice().toString();
					String productImage = "";

					byte[] productPicture = productinfo.getProductPicture();
					if (productPicture != null) {
						productImage = Base64.getEncoder().encodeToString(productPicture);
					} else {
						productImage = "";
					}
					// JSON
					JsonObject jsonProduct = new JsonObject();
					jsonProduct.addProperty("memberNo", memberNo);
					jsonProduct.addProperty("productNo", productNo);
					jsonProduct.addProperty("productName", productName);
					jsonProduct.addProperty("productPrice", productPrice);
					jsonProduct.addProperty("productImage", productImage);
					jsonProduct.addProperty("quantity", Integer.valueOf(quantity));

					// JSON to Redis   (jsonProduct.toString()  轉換為 JSON 字符串)
					jedis.hset(cartKey, productKey, jsonProduct.toString());

					// 過期時間
					jedis.expire(cartKey, 3600);

					System.out.println("儲存成功Redis!");
				}

				res.setContentType("application/json");
				res.setCharacterEncoding("UTF-8");
				String successMessage = "{\"message\": \"成功加入購物車\"}";
				res.getWriter().write(successMessage);
			} finally {
				jedis.close();
			}
		}
		if ("buttonadd2".equals(action)) {
			Integer productNo = Integer.valueOf(req.getParameter("productNo").trim());
			Integer quantity = Integer.valueOf(req.getParameter("quantity"));

			//動態會員
			String memberNo = userId;

			System.out.println(productNo);

			JedisPool jedisPool = JedisUtil.getJedisPool();
			Jedis jedis = jedisPool.getResource();

			try {
				jedis.select(1);

				String cartKey = "cart:" + memberNo;
				String productKey = "product:" + productNo;
				String existingProduct = jedis.hget(cartKey, productKey);

				if (existingProduct != null) {
					System.out.println("重複商品累加");
					JsonObject existingProductJson = new JsonParser().parse(existingProduct).getAsJsonObject();
					int existingQuantity = existingProductJson.get("quantity").getAsInt();

					int newQuantity = existingQuantity + quantity;
					existingProductJson.addProperty("quantity", newQuantity);

					// 更新
					jedis.hset(cartKey, productKey, existingProductJson.toString());

					jedis.expire(cartKey, 3600);

					System.out.println("儲存成功Redis!");
				} else {

					ProductService productSvc = new ProductService();
					ProductVO productinfo = productSvc.getOneProduct(productNo);

					String productName = productinfo.getProductName();
					String productPrice = productinfo.getProductPrice().toString();
					String productImage = "";

					byte[] productPicture = productinfo.getProductPicture();
					if (productPicture != null) {
						productImage = Base64.getEncoder().encodeToString(productPicture);
					} else {
						productImage = "";
					}

					JsonObject jsonProduct = new JsonObject();
					jsonProduct.addProperty("memberNo", memberNo);
					jsonProduct.addProperty("productNo", productNo);
					jsonProduct.addProperty("productName", productName);
					jsonProduct.addProperty("productPrice", productPrice);
					jsonProduct.addProperty("productImage", productImage);
					jsonProduct.addProperty("quantity", Integer.valueOf(quantity));

					jedis.hset(cartKey, productKey, jsonProduct.toString());

					jedis.expire(cartKey, 3600);

					System.out.println("儲存成功Redis!!");
				}
				res.setContentType("application/json");
				res.setCharacterEncoding("UTF-8");
				String successMessage = "{\"message\": \"成功加入購物車\"}";
				res.getWriter().write(successMessage);
			} finally {
				jedis.close();
			}
		}

		if ("cartsearch".equals(action)) {
			JedisPool jedisPool = JedisUtil.getJedisPool();
			Jedis jedis = jedisPool.getResource();

			try {
				//動態會員
				String memberNo = userId; // 
				//Key
				String cartKey = "cart:" + memberNo; 

				jedis.select(1);
				Map<String, String> cartData = jedis.hgetAll(cartKey);
				System.out.println(cartKey);
				System.out.println(cartData);
				System.out.println(cartData);
				if (!cartData.isEmpty()) {
					
					//JSON 陣列
					JsonArray cartArray = new JsonArray();

					for (Map.Entry<String, String> entry : cartData.entrySet()) {
						String productKey = entry.getKey();
						String productJson = entry.getValue();

						
						JsonObject productObject = new JsonParser().parse(productJson).getAsJsonObject();
						cartArray.add(productObject);
					}

					res.setContentType("application/json");
					res.setCharacterEncoding("UTF-8");
					res.getWriter().write(cartArray.toString());
				} else {
					// 空陣列(前端陣列儲存)
					res.setContentType("application/json");
					res.setCharacterEncoding("UTF-8");
					res.getWriter().write("[]"); 
				}
			} finally {
				jedis.close(); 
			}
		}

		if ("remove".equals(action)) {

			int productNo = Integer.parseInt(req.getParameter("productId"));
			//動態會員
			String memberNo = userId; 

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
				//動態會員
				String memberNo = userId; 
				String cartKey = "cart:" + memberNo; 

				jedis.select(1);
				String productKeysStr = req.getParameter("productNo"); 

				//商品編號陣列
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

				res.setContentType("application/json");
				res.setCharacterEncoding("UTF-8");
				//轉換為 JSON 字符串
				res.getWriter().write(cartArray.toString());
			} finally {
				jedis.close(); 
			}
		}

		if ("updateQuantity".equals(action)) {
			Integer productNo = Integer.valueOf(req.getParameter("productId").trim());
			Integer newQuantity = Integer.valueOf(req.getParameter("newQuantity").trim());

			String memberNo = userId; 

			JedisPool jedisPool = JedisUtil.getJedisPool();
			Jedis jedis = jedisPool.getResource();
			try {
				jedis.select(1);
				String cartKey = "cart:" + memberNo;
				String productKey = "product:" + productNo;
				String existingProduct = jedis.hget(cartKey, productKey);
				if (existingProduct != null) {
					JsonObject existingProductJson = new JsonParser().parse(existingProduct).getAsJsonObject();
					existingProductJson.addProperty("quantity", newQuantity);

					jedis.hset(cartKey, productKey, existingProductJson.toString());

					jedis.expire(cartKey, 3600); // 设置为1小时过期

					Map<String, String> cartData = jedis.hgetAll(cartKey);
					System.out.println(cartKey);
					System.out.println(cartData);
					System.out.println(cartData);
					if (!cartData.isEmpty()) {
						JsonArray cartArray = new JsonArray();

						for (Map.Entry<String, String> entry : cartData.entrySet()) {
							String productKeys = entry.getKey();
							String productJsons = entry.getValue();
							// 构建 JSON 对象
							JsonObject productObject = new JsonParser().parse(productJsons).getAsJsonObject();
							cartArray.add(productObject);
						}

						// 将 JSON 数组写入响应
						res.setContentType("application/json");
						res.setCharacterEncoding("UTF-8");
						res.getWriter().write(cartArray.toString());
					} else {
						res.setContentType("application/json");
						res.setCharacterEncoding("UTF-8");
						res.getWriter().write("[]"); 
					}
				}
			} finally {
				jedis.close();
			}
		}

	}

}
