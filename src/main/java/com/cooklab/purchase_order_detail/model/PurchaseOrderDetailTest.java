package com.cooklab.purchase_order_detail.model;

import java.sql.Date;
import java.util.List;

public class PurchaseOrderDetailTest {
	public static void main(String[] args) {

		PurchaseOrderDetailJDBCDAOIm pODDAOIm = new PurchaseOrderDetailJDBCDAOIm();

//		 新增
		PurchaseOrderDetailVO podvo1 = new PurchaseOrderDetailVO();
		podvo1.setProductName("鱈魚");
		podvo1.setProductQty(10);
//		podvo1.setExpiredDate(new Date(System.currentTimeMillis()));
		podvo1.setPurchaseOrderNo(Integer.valueOf(2));//待實作獲取進貨單號的方法
		podvo1.setProductNo(Integer.valueOf(3));//待實作獲取產品編號的方法
		podvo1.setPurchaseOrderPrice(Integer.valueOf(1000));
		pODDAOIm.insert(podvo1);

		// 修改
//		PurchaseOrderDetailVO podvo2 = new PurchaseOrderDetailVO();
//		podvo2.setPurchaseOrderDetailNo(1);
//		podvo2.setProductName("鱈魚");
//		podvo2.setProductQty(10);
//		podvo2.setExpiredDate(new Date(System.currentTimeMillis()));
//		podvo2.setPurchaseOrderNo(Integer.valueOf(2));//待實作獲取進貨單號的方法
//		podvo2.setProductNo(Integer.valueOf(3));//待實作獲取產品編號的方法
//		podvo2.setPurchaseOrderPrice(Integer.valueOf(1000));
//		pODDAOIm.update(podvo2);

		// 刪除
//		pODDAOIm.delete(3);

		// 查詢單一資料
//		PurchaseOrderDetailVO povo3 = pODDAOIm.findByPrimaryKey(2);
//		System.out.print(povo3.getPurchaseOrderDetailNo() + ",");
//		System.out.print(povo3.getProductName() + ",");
//		System.out.print(povo3.getProductQty() + ",");
//		System.out.print(povo3.getExpiredDate() + ",");
//		System.out.print(povo3.getPurchaseOrderNo() + ",");
//		System.out.print(povo3.getProductNo() + ",");
//		System.out.println(povo3.getPurchaseOrderPrice() );
//		System.out.println("---------------------");

		// 查詢全部資料
		List<PurchaseOrderDetailVO> list = pODDAOIm.getAll();
		for (PurchaseOrderDetailVO apovo3 : list) {
			System.out.print(apovo3.getPurchaseOrderDetailNo() + ",");
			System.out.print(apovo3.getProductName() + ",");
			System.out.print(apovo3.getProductQty() + ",");
			System.out.print(apovo3.getExpiredDate() + ",");
			System.out.print(apovo3.getPurchaseOrderNo() + ",");
			System.out.print(apovo3.getProductNo() + ",");
			System.out.print(apovo3.getPurchaseOrderPrice() );
			System.out.println();
	}
}
}
