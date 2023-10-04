package com.cooklab.purchase_order.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cooklab.purchase_order.model.PurchaseOrderService;
import com.cooklab.purchase_order_detail.model.PurchaseOrderDetailService;



/**
 * Servlet implementation class PurchaseOrderServlet
 */
@WebServlet("/PurchaseOrderServlet")
public class PurchaseOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public PurchaseOrderServlet() {
		super();

	}


	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");

		if ("delete".equals(action)) { // 來自listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			request.setAttribute("errorMsgs", errorMsgs);
			


			String purchaseOrderNoStr = request.getParameter("orderNo");

			if (purchaseOrderNoStr != null && !purchaseOrderNoStr.isEmpty()) {
				
				/***************************1.接收請求參數***************************************/
			    Integer purchaseOrderNo = Integer.valueOf(purchaseOrderNoStr);
				/***************************2.開始刪除資料***************************************/
				
			    PurchaseOrderDetailService purchaseOrderDetailSvc = new PurchaseOrderDetailService();
			    purchaseOrderDetailSvc.deleteByPurchaseOrderNo(purchaseOrderNo);
			    
			    PurchaseOrderService purchaseOrderSvc = new PurchaseOrderService();
				purchaseOrderSvc.delete(purchaseOrderNo);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/mazer-main/dist/purchase_order/TYT_purchase_order_allView.jsp";
				RequestDispatcher successView = request.getRequestDispatcher(url);
				successView.forward(request, response);

			} else {
			    // 处理参数为空的情况，例如给出错误提示或执行其他逻辑
			}
				

		}

	}

}
