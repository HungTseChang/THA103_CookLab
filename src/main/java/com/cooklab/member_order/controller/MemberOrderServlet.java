package com.cooklab.member_order.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cooklab.ingredient_category.model.IngredientCategoryVO;
import com.cooklab.ingredient_category.model.IngredientService;
import com.cooklab.kitchenware_category.model.KitchenwareCategoryService;
import com.cooklab.kitchenware_category.model.KitchenwareCategoryVO;
import com.cooklab.member_order.model.MemberOrderService;
import com.cooklab.member_order.model.MemberOrderVO;
import com.cooklab.members.model.MembersService;
import com.cooklab.members.model.MembersVO;
import com.cooklab.order_detail.model.OrderDetailVO;
import com.cooklab.product.model.ProductService;
import com.cooklab.product.model.ProductVO;
import com.cooklab.promo_code.model.PromoCodeService;
import com.cooklab.promo_code.model.PromoCodeVO;
import com.cooklab.util.JedisUtil;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@WebServlet("/MemberOrderServlet")
public class MemberOrderServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("memberMessage".equals(action)) {

			String memberId = "1";

			MembersService memberSvc = new MembersService();
			MembersVO membersInfo = memberSvc.findByPrimaryKey(Integer.valueOf(memberId));

			System.out.println(memberId);
			System.out.println(membersInfo);
			Map<String, String> itemMap = new HashMap<>();

			if (membersInfo.getMemberNickname() == null) {
				itemMap.put("memberNickname", "");
			} else {
				itemMap.put("memberNickname", membersInfo.getMemberNickname());
			}
			itemMap.put("memberAddress", membersInfo.getMemberAddress());
			itemMap.put("memberMail", membersInfo.getMemberMail());
			itemMap.put("memberCellphone", membersInfo.getMemberCellphone());

			Gson gson = new Gson();
			String jsonData = gson.toJson(itemMap);
			System.out.println(jsonData);

			res.setContentType("application/json");
			res.setCharacterEncoding("UTF-8");
			res.getWriter().write(jsonData);

		}

		if ("checkCoupon".equals(action)) {
			String promocodeNumber = req.getParameter("couponCode");
			PromoCodeService promoCodeSvc = new PromoCodeService();
			PromoCodeVO promoCodeInfo = promoCodeSvc.findByPromoCodeSerialNumber(promocodeNumber);

			Map<String, String> itemMap = new HashMap<>();

			if (promoCodeInfo == null) {
				itemMap.put("message", "false");

				Gson gson = new Gson();
				String jsonData = gson.toJson(itemMap);
				System.out.println(jsonData);

				res.setContentType("application/json");
				res.setCharacterEncoding("UTF-8");
				res.getWriter().write(jsonData);
			} else {
				itemMap.put("message", "success");
				itemMap.put("startTime", promoCodeInfo.getStartTime().toString());
				itemMap.put("endTime", promoCodeInfo.getEndTime().toString());
				if (promoCodeInfo.getFixedDiscountAmount() != null
						&& promoCodeInfo.getFixedDiscountAmount().compareTo(new BigDecimal(0)) > 0) {
					itemMap.put("fixedDiscountAmount", promoCodeInfo.getFixedDiscountAmount().toString());
					itemMap.put("percentageDiscountAmount", "0");
				} else {
					itemMap.put("fixedDiscountAmount", "0");
					itemMap.put("percentageDiscountAmount", promoCodeInfo.getPercentageDiscountAmount().toString());
				}
				System.out.println(promoCodeInfo.getPercentageDiscountAmount().toString());
				itemMap.put("promoCodeNo", promoCodeInfo.getPromoCodeNo().toString());
				itemMap.put("minimumConsumption", promoCodeInfo.getMinimumConsumption().toString());

				Gson gson = new Gson();
				String jsonData = gson.toJson(itemMap);
				System.out.println(jsonData);

				res.setContentType("application/json");
				res.setCharacterEncoding("UTF-8");
				res.getWriter().write(jsonData);

			}

		}

		if ("checkout".equals(action)) {
			String memberName = req.getParameter("memberName");
			String memberEmail = req.getParameter("memberEmail");
			String memberPhone = req.getParameter("memberPhone");
			String memberAddress = req.getParameter("memberAddress");
			String orderTotal = req.getParameter("orderTotal");
			String finalPrice = req.getParameter("finalPrice");
			String promoCodeInfo = req.getParameter("promoCodeInfo");
			String cartData = req.getParameter("cartData");

			// 创建订单
			JsonArray cartItems = new JsonParser().parse(cartData).getAsJsonArray();
			MemberOrderVO memberOrder = new MemberOrderVO();
			memberOrder.setMemberId(1);
			memberOrder.setOrderStatus((byte) 0);
			memberOrder.setTotalOrderAmount(Integer.valueOf(orderTotal));
			if (promoCodeInfo == null || promoCodeInfo.isEmpty()) {
				memberOrder.setPromoCodeNo(null);
			} else {
				memberOrder.setPromoCodeNo(Integer.valueOf(promoCodeInfo));
			}

			memberOrder.setCheckoutAmount(Integer.valueOf(finalPrice));
			memberOrder.setShippingAddress(memberAddress);

			Set<OrderDetailVO> details = new LinkedHashSet<>();
			for (int i = 0; i < cartItems.size(); i++) {
				JsonObject cartItem = cartItems.get(i).getAsJsonObject();
				int productNo = cartItem.get("productNo").getAsInt();
				int quantity = cartItem.get("quantity").getAsInt();

				ProductVO productvo = new ProductVO();
//				ProductService productSvc = new ProductService();
//				productvo = productSvc.getOneProduct(productNo);
				productvo.setProductNo(productNo);
				OrderDetailVO orderDetail = new OrderDetailVO();
				orderDetail.setProduct(productvo);
				orderDetail.setOrderQty(quantity);

				// 訂單明細 訂單關聯
				orderDetail.setMemberOrder(memberOrder);

				details.add(orderDetail);
			}
			memberOrder.setOrderDetail(details);
			// 保存订单到数据库（使用 Hibernate 或 JPA）
			MemberOrderService memberOrderSvc = new MemberOrderService();
			String message = memberOrderSvc.insert(memberOrder);

			// 在成功创建订单后，删除购物车中的商品

			if (message.equals("success")) {
				JedisPool jedisPool = JedisUtil.getJedisPool();
				Jedis jedis = jedisPool.getResource();
				jedis.select(1);
				try {
					// 设置会员编号
					String memberNo = "1";
					// 构建购物车键
					String cartKey = "cart:" + memberNo;
					for (int i = 0; i < cartItems.size(); i++) {
						JsonObject cartItem = cartItems.get(i).getAsJsonObject();
						int productNo = cartItem.get("productNo").getAsInt();
						int quantity = cartItem.get("quantity").getAsInt();

						// 在这里使用 productNo 和 quantity 处理购物车中的商品信息
						String productKey = "product:" + productNo;
						// 从 Redis 中删除对应商品
						jedis.hdel(cartKey, productKey);

						// 同时更新数据库中的库存
						ProductService productSvc = new ProductService();
						ProductVO product = productSvc.getOneProduct(productNo);
						if (product != null) {
							// 减少库存
							int currentStock = product.getStorageQty(); // 获取当前库存
							int newStock = currentStock - quantity; // 减少库存后的值
							if (newStock < 0) {
								newStock = 0; // 避免库存为负数
							}

							// 更新库存
							product.setStorageQty(newStock);
							productSvc.update(product);
						}
					}

					Map<String, String> itemMap = new HashMap<>();
					itemMap.put("message", "success");
					// 向前端发送成功消息
					Gson gson = new Gson();
					String jsonData = gson.toJson(itemMap);
					System.out.println(jsonData);

					res.setContentType("application/json");
					res.setCharacterEncoding("UTF-8");
					res.getWriter().write(jsonData);
				} finally {
					jedis.close();
				}
			} else {
				Map<String, String> itemMap = new HashMap<>();
				itemMap.put("message", "false");
				// 失敗
				Gson gson = new Gson();
				String jsonData = gson.toJson(itemMap);
				System.out.println(jsonData);

				res.setContentType("application/json");
				res.setCharacterEncoding("UTF-8");
				res.getWriter().write(jsonData);
			}

		}
		if ("search".equals(action)) {
			MemberOrderService memberOrderSvc = new MemberOrderService();
			List<MemberOrderVO> memberOrderVO = memberOrderSvc.getAll();

			// 创建一个列表来存储 HashMap
			List<Map<String, String>> dataMapList = new ArrayList<>();

			for (MemberOrderVO item : memberOrderVO) {
				// 创建一个 HashMap 来存储当前项的数据
				Map<String, String> itemMap = new HashMap<>();
				// 获取数据并放入 HashMap
				String orderNo = item.getOrderNo().toString();
				itemMap.put("orderNo", orderNo);

				String members = item.getMembers().getMemberId().toString();
				itemMap.put("members", members);

				String orderStatus = item.getOrderStatus().toString();
				itemMap.put("orderStatus", orderStatus);

				String totalOrderAmount = item.getTotalOrderAmount().toString();
				itemMap.put("totalOrderAmount", totalOrderAmount);

				String checkoutAmount = item.getCheckoutAmount().toString();
				itemMap.put("checkoutAmount", checkoutAmount);

				String promoCode;
				if (item.getPromoCode() != null) {
					promoCode = item.getPromoCode().getPromoCodeNo().toString();
				} else {
					promoCode = "無使用";
				}
				itemMap.put("promoCode", promoCode);

				String shippingAddress = item.getShippingAddress();
				itemMap.put("shippingAddress", shippingAddress);

				String createdTimestamp = item.getCreatedTimestamp().toString();
				itemMap.put("createdTimestamp", createdTimestamp);
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
		if ("getDetail".equals(action)) {
			Integer orderNo = Integer.valueOf(req.getParameter("orderNo").trim());
			/***************************
			 * 2.開始查詢資料
			 *****************************************/
			MemberOrderService memberOrderSvc = new MemberOrderService();
			MemberOrderVO memberOrderVO = memberOrderSvc.findByPrimaryKey(orderNo);

			Map<String, Object> memberOrderDetailMap = new HashMap<>();

			memberOrderDetailMap.put("memberId", memberOrderVO.getMembers().getMemberId().toString());
			memberOrderDetailMap.put("shippingAddress", memberOrderVO.getShippingAddress());
			memberOrderDetailMap.put("promoCode", memberOrderVO.getPromoCode().getPromoCodeSerialNumber());
			memberOrderDetailMap.put("memberNickname", memberOrderVO.getMembers().getMemberNickname());
			memberOrderDetailMap.put("orderNo", memberOrderVO.getOrderNo().toString());
			memberOrderDetailMap.put("totalOrderAmount", memberOrderVO.getTotalOrderAmount().toString());
			memberOrderDetailMap.put("checkoutAmount", memberOrderVO.getCheckoutAmount().toString());
			memberOrderDetailMap.put("memberMail", memberOrderVO.getMembers().getMemberMail());
			memberOrderDetailMap.put("createdTimestamp", memberOrderVO.getCreatedTimestamp().toString());
			memberOrderDetailMap.put("orderStatus", memberOrderVO.getOrderStatus().toString());

			// 处理订单明细
			Set<OrderDetailVO> orderDetails = memberOrderVO.getOrderDetail();
			List<Map<String, Object>> orderDetailList = new ArrayList<>();

			for (OrderDetailVO orderDetail : orderDetails) {
				Map<String, Object> orderDetailItem = new HashMap<>();
				orderDetailItem.put("productId", orderDetail.getProduct().getProductName());

				orderDetailItem.put("quantity", orderDetail.getOrderQty().toString());
				int totalcount = orderDetail.getProduct().getProductPrice() * orderDetail.getOrderQty();
				orderDetailItem.put("totalcount", String.valueOf(totalcount));

				System.out.println(orderDetail.getProduct().getProductName());
				System.out.println(orderDetail.getOrderQty().toString());
				System.out.println(String.valueOf(totalcount));

				// 将 orderDetailItem 添加到 orderDetailList
				orderDetailList.add(orderDetailItem);
			}

			memberOrderDetailMap.put("orderDetail", orderDetailList);
			// 2. 使用Gson将Map对象转换为JSON字符串
			Gson gson = new Gson();
			String productDetailJson = gson.toJson(memberOrderDetailMap);

			System.out.println(productDetailJson);
			// 3. 设置响应的内容类型为JSON
			res.setContentType("application/json; charset=UTF-8");

			// 将JSON字符串作为响应写入输出流
			res.getWriter().write(productDetailJson);
		}

		if ("updateOrderStatus".equals(action)) {
			Integer orderNo = Integer.valueOf(req.getParameter("orderNo").trim());
			Integer newStatus = Integer.valueOf(req.getParameter("newStatus").trim());

			MemberOrderService memberOrderSvc = new MemberOrderService();
			MemberOrderVO memberOrderVO = new MemberOrderVO();

			memberOrderVO.setOrderNo(orderNo);
			if (newStatus >= Byte.MIN_VALUE && newStatus <= Byte.MAX_VALUE) {
				byte statusByte = newStatus.byteValue();
				memberOrderVO.setOrderStatus(statusByte);
			}

			memberOrderSvc.update(memberOrderVO);

			Map<String, String> memberOrderMap = new HashMap<>();
			
			if(memberOrderSvc.update(memberOrderVO)==1) {
				memberOrderMap.put("message", "success");
			}else {
				memberOrderMap.put("message", "false");
			}
			// 2. 使用Gson将Map对象转换为JSON字符串
			Gson gson = new Gson();
			String productDetailJson = gson.toJson(memberOrderMap);

			System.out.println(productDetailJson);
			// 3. 设置响应的内容类型为JSON
			res.setContentType("application/json; charset=UTF-8");

			// 将JSON字符串作为响应写入输出流
			res.getWriter().write(productDetailJson);
		}

	}

}
