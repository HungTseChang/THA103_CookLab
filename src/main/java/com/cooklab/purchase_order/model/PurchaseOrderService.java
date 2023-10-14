package com.cooklab.purchase_order.model;

import java.util.List;

public class PurchaseOrderService {

	private PurchaseOrderDAO dao;

	public PurchaseOrderService() {
		dao = new PurchaseOrderHDAOIm();
	}

	public List<PurchaseOrderVO> getAll(){
		return dao.getAll();
	}
	
	public void delete(Integer PurchaseOrderNo) {
		System.out.println("a");
		dao.delete(PurchaseOrderNo);
	}
	
}
