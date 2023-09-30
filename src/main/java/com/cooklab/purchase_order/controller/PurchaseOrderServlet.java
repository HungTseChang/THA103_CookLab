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
		response.setCharacterEncoding("UTF-8");

		if ("delete".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			request.setAttribute("errorMsgs", errorMsgs);

			try {
				Integer purchaseOrderNo = Integer.valueOf(request.getParameter("purchaseOrderNo"));
				PurchaseOrderService purchaseOrderSvc = new PurchaseOrderService();
				purchaseOrderSvc.delete(purchaseOrderNo);

				String url = "";
				RequestDispatcher successView = request.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();

			}
		}

	}

}
