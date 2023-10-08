package com.cooklab.purchase_order.model;

import java.util.List;

public class PurchaseOrderTest {
	public static void main(String[] args) {

		PurchaseOrderJDBCDAOIm pODAOIm = new PurchaseOrderJDBCDAOIm();

//		 新增
		PurchaseOrderVO povo1 = new PurchaseOrderVO();
		povo1.setPurchaseOrderDate(java.sql.Date.valueOf("2023-09-11"));
		povo1.setPurchaseOrderSupplier("美味食材天堂");
		povo1.setPurchaseOrderTotal(Integer.valueOf(200));
		pODAOIm.insert(povo1);

		// 修改
//		PurchaseOrderVO povo2 = new PurchaseOrderVO();
//		povo2.setPurchaseOrderNo(2);
//		povo2.setPurchaseOrderDate(java.sql.Date.valueOf("2023-09-10"));
//		povo2.setPurchaseOrderSupplier("烹飪奇蹟廚具店");
//		povo2.setPurchaseOrderTotal(Integer.valueOf(100));
//		pODAOIm.update(povo2);

		// 刪除
//		poDAOIm.delete(4);

		// 查詢單一資料
		PurchaseOrderVO povo3 = pODAOIm.findByPrimaryKey(2);
		System.out.println(povo3);
		System.out.println("---------------------");

		// 查詢全部資料
		List<PurchaseOrderVO> list = pODAOIm.getAll();
		for (PurchaseOrderVO apovo3 : list) {
			System.out.println(apovo3);
		}
	}
}
