package com.cooklab.member_order.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cooklab.member_order.model.MemberOrderService;
import com.cooklab.member_order.model.MemberOrderVO;
import com.cooklab.members.model.MembersService;
import com.cooklab.members.model.MembersVO;
import com.cooklab.order_detail.model.OrderDetailVO;
import com.cooklab.promo_code.model.PromoCodeService;
import com.cooklab.promo_code.model.PromoCodeVO;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

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

			System.out.println(memberName);
			System.out.println(memberEmail);
			System.out.println(memberPhone);
			System.out.println(memberAddress);
			System.out.println(orderTotal);
			System.out.println(finalPrice);
			System.out.println(promoCodeInfo);
			System.out.println(cartData);

			// 创建订单
			MemberOrderVO memberOrder = new MemberOrderVO();
			memberOrder.setMemberId(1); 
			memberOrder.setOrderStatus((byte) 0); 
			memberOrder.setTotalOrderAmount(Integer.valueOf(orderTotal));
			memberOrder.setPromoCodeNo(Integer.valueOf(promoCodeInfo)); 
			memberOrder.setCheckoutAmount(Integer.valueOf(finalPrice)); 
			memberOrder.setShippingAddress(memberAddress); 

			
			JsonArray cartItems = new JsonParser().parse(cartData).getAsJsonArray();

			
			for (int i = 0; i < cartItems.size(); i++) {
				JsonObject cartItem = cartItems.get(i).getAsJsonObject();
				int productNo = cartItem.get("productNo").getAsInt();
				int quantity = cartItem.get("quantity").getAsInt();

				
				OrderDetailVO orderDetail = new OrderDetailVO();
				orderDetail.setProductNo(productNo); 
				orderDetail.setOrderQty(quantity); 

				// 訂單明細 訂單關聯
				orderDetail.setMemberOrder(memberOrder);

				// 将订单明细添加到订单的订单明细集合
				memberOrder.getOrderDetail().add(orderDetail);
			}

			// 保存订单到数据库（使用 Hibernate 或 JPA）
			MemberOrderService memberOrderSvc = new MemberOrderService();
			memberOrderSvc.insert(memberOrder);

		}

	}

}
