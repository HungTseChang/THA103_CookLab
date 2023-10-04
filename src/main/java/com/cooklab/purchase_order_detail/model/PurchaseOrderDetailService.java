package com.cooklab.purchase_order_detail.model;

public class PurchaseOrderDetailService {

	private PurchaseOrderDetailDAO dao;
	
	public PurchaseOrderDetailService() {
		dao = new PurchaseOrderDetailJDBCDAOIm();
	}

	 public void deleteByPurchaseOrderNo(Integer purchaseOrderDetailNo) {
		 dao.delete(purchaseOrderDetailNo);
	 }
		

}
