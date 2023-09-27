package com.cooklab.kitchenware_category.model;

import java.util.List;

public class KitchenwareCategoryTest {
	public static void main(String[] args) {

		KitchenwareCategoryJDBCDAOIm kCDAOIm = new KitchenwareCategoryJDBCDAOIm();

		// 新增
		KitchenwareCategoryVO kCVO1 = new KitchenwareCategoryVO();
		kCVO1.setCategoryName("銅鍋");
		kCDAOIm.insert(kCVO1);

		// 修改
//		KitchenwareCategoryVO kCVO2 = new KitchenwareCategoryVO();
//		kCVO2.setKitchenwareCategoryNo(2);
//		kCVO2.setCategoryName("玻璃鍋");
//		kCDAOIm.update(kCVO2);

		// 刪除
//		kCDAOIm.delete(3);

		// 查詢單一資料
//		KitchenwareCategoryVO kCVO3 = kCDAOIm.findByPrimaryKey(2);
//		System.out.print(kCVO3.getKitchenwareCategoryNo() + ",");
//		System.out.println(kCVO3.getCategoryName() );
//		System.out.println("---------------------");

		// 查詢全部資料
		List<KitchenwareCategoryVO> list = kCDAOIm.getAll();
		for (KitchenwareCategoryVO akCVO3 : list) {
			System.out.print(akCVO3.getKitchenwareCategoryNo() + ",");
			System.out.print(akCVO3.getCategoryName());
			System.out.println();
		}
	}
}
