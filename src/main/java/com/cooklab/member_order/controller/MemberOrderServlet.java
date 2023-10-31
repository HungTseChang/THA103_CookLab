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
import javax.servlet.http.HttpSession;

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
import com.cooklab.promo_code_used.model.PromoCodeUsedVO;
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

		HttpSession session = req.getSession();
		
		String userId = session.getAttribute("userId").toString();
		Integer userId2 =  (Integer) session.getAttribute("userId");
		
		//訂購詳情資料渲染
		if ("memberMessage".equals(action)) {
			//未來動態會員編號
			String memberId = userId;
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

		//優惠碼相關
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

		
		//結帳(生成訂單 訂單明細 memberVO.setOrderDetail{set集合}(OrderDetailVO) (迴圈跑set明細VO))
		if ("checkout".equals(action)) {
			
			String memberAddress = req.getParameter("memberAddress");
			String orderTotal = req.getParameter("orderTotal");
			String finalPrice = req.getParameter("finalPrice");
			String promoCodeInfo = req.getParameter("promoCodeInfo");
			String cartData = req.getParameter("cartData");
			
			System.out.println(memberAddress);
			System.out.println(orderTotal);
			System.out.println(finalPrice);
			System.out.println(promoCodeInfo);
			System.out.println(cartData);
			
			JsonArray cartItems = new JsonParser().parse(cartData).getAsJsonArray();
			
			//會員訂單
			MemberOrderVO memberOrder = new MemberOrderVO();
			MembersService membersService = new MembersService();
			
			
//			MembersVO membersVO = membersService.findByPrimaryKey(userId2);
//			membersVO.setMemberId(userId2);

			memberOrder.setMemberId(userId2);
//			memberOrder.setMembers(membersVO);
			
			memberOrder.setOrderStatus((byte) 0);
			memberOrder.setTotalOrderAmount(Integer.valueOf(orderTotal));
			
			//優惠券使用狀況			
			if (promoCodeInfo == null || promoCodeInfo.isEmpty()) {
				memberOrder.setPromoCodeNo(null);
			} else {
				memberOrder.setPromoCodeNo(Integer.valueOf(promoCodeInfo));

			}

			memberOrder.setCheckoutAmount(Integer.valueOf(finalPrice));
			memberOrder.setShippingAddress(memberAddress);

			//訂單明細
			Set<OrderDetailVO> details = new LinkedHashSet<>();
			
			for (int i = 0; i < cartItems.size(); i++) {
				JsonObject cartItem = cartItems.get(i).getAsJsonObject();
				int productNo = cartItem.get("productNo").getAsInt();
				int quantity = cartItem.get("quantity").getAsInt();

				ProductVO productvo = new ProductVO();
				productvo.setProductNo(productNo);
				
				OrderDetailVO orderDetail = new OrderDetailVO();
				orderDetail.setProduct(productvo);
				orderDetail.setOrderQty(quantity);
				
				orderDetail.setMemberOrder(memberOrder);

				details.add(orderDetail);
			}
			memberOrder.setOrderDetail(details);

			
			MemberOrderService memberOrderSvc = new MemberOrderService();
			String message = memberOrderSvc.insert(memberOrder);

			
			//成功 刪除購物車內容
			if (message.equals("success")) {
				JedisPool jedisPool = JedisUtil.getJedisPool();
				Jedis jedis = jedisPool.getResource();
				jedis.select(1);
				try {
					//未來動態會員編號
					String memberNo = userId;
					
					String cartKey = "cart:" + memberNo;
					for (int i = 0; i < cartItems.size(); i++) {
						JsonObject cartItem = cartItems.get(i).getAsJsonObject();
						int productNo = cartItem.get("productNo").getAsInt();
						int quantity = cartItem.get("quantity").getAsInt();
						String productKey = "product:" + productNo;
						
						jedis.hdel(cartKey, productKey);

						//MySql庫存
						ProductService productSvc = new ProductService();
						ProductVO product = productSvc.getOneProduct(productNo);
						if (product != null) {
							int currentStock = product.getStorageQty(); 
							int newStock = currentStock - quantity; 
							if (newStock < 0) {
								newStock = 0; 
							}

							product.setStorageQty(newStock);
							productSvc.update(product);
						}
					}

					Map<String, String> itemMap = new HashMap<>();
					itemMap.put("message", "success");

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

				Gson gson = new Gson();
				String jsonData = gson.toJson(itemMap);
				System.out.println(jsonData);

				res.setContentType("application/json");
				res.setCharacterEncoding("UTF-8");
				res.getWriter().write(jsonData);
			}

		}
		//訂單全部
		if ("search".equals(action)) {
			MemberOrderService memberOrderSvc = new MemberOrderService();
			List<MemberOrderVO> memberOrderVO = memberOrderSvc.getAll();

			List<Map<String, String>> dataMapList = new ArrayList<>();

			for (MemberOrderVO item : memberOrderVO) {

				Map<String, String> itemMap = new HashMap<>();

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
		
		//訂單詳情(單筆)
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
			
			String promoCode = "";
			PromoCodeVO promoCodeVO = memberOrderVO.getPromoCode();
			if (promoCodeVO != null) {
			    promoCode = promoCodeVO.getPromoCodeSerialNumber();
			}

			memberOrderDetailMap.put("promoCode", promoCode);
			memberOrderDetailMap.put("memberNickname", memberOrderVO.getMembers().getMemberNickname());
			memberOrderDetailMap.put("orderNo", memberOrderVO.getOrderNo().toString());
			memberOrderDetailMap.put("totalOrderAmount", memberOrderVO.getTotalOrderAmount().toString());
			memberOrderDetailMap.put("checkoutAmount", memberOrderVO.getCheckoutAmount().toString());
			memberOrderDetailMap.put("memberMail", memberOrderVO.getMembers().getMemberMail());
			memberOrderDetailMap.put("createdTimestamp", memberOrderVO.getCreatedTimestamp().toString());
			memberOrderDetailMap.put("orderStatus", memberOrderVO.getOrderStatus().toString());

			
			//訂單明細
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

				orderDetailList.add(orderDetailItem);
			}

			memberOrderDetailMap.put("orderDetail", orderDetailList);

			Gson gson = new Gson();
			String productDetailJson = gson.toJson(memberOrderDetailMap);


			res.setContentType("application/json; charset=UTF-8");
			res.getWriter().write(productDetailJson);
		}

		//訂單狀態更新
		if ("updateOrderStatus".equals(action)) {
			Integer orderNo = Integer.valueOf(req.getParameter("orderNo").trim());
			Integer newStatus = Integer.valueOf(req.getParameter("newStatus").trim());

			MemberOrderService memberOrderSvc = new MemberOrderService();
			MemberOrderVO memberOrderVO = new MemberOrderVO();

			memberOrderVO =memberOrderSvc.findByPrimaryKey(orderNo);
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

			Gson gson = new Gson();
			String productDetailJson = gson.toJson(memberOrderMap);

			System.out.println(productDetailJson);

			res.setContentType("application/json; charset=UTF-8");
			res.getWriter().write(productDetailJson);
		}

	}

}
