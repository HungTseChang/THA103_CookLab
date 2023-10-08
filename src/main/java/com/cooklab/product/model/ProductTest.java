package com.cooklab.product.model;

import java.sql.Timestamp;
import java.util.List;

public class ProductTest {
	public static void main(String[] args) {
		ProductJDBCDAOIm pDAOIm = new ProductJDBCDAOIm();

//	 新增
//	ProductVO pvo1 = new ProductVO();
//	pvo1.setProductName("大蘋果");
//	pvo1.setSaleQty(30);
//	pvo1.setProductDec("新鮮蘋果");
//	pvo1.setproductIntroduction("蘋果");
//	pvo1.setProductPrice(50);
//	pvo1.setOffsaleTime(null);
////	pvo1.setShelfTime(new Timestamp(System.currentTimeMillis()));
//	pvo1.setStorageQty(100);
//	pvo1.setIngredientCategoryNo(null);//待後續實作透過食材名稱搜尋編號的方法
//	pvo1.setKitchenwareCategoryNo(null);//待後續實作透過廚具名稱搜尋編號的方法
//	pDAOIm.insert(pvo1);

		// 修改
//	ProductVO pvo2 = new ProductVO();
//	pvo2.setProductName("小蘋果");
//	pvo2.setSaleQty(1000);
//	pvo2.setProductDec("小小蘋果");
//	pvo2.setproductIntroduction("蘋果");
//	pvo2.setProductPrice(500);
//	pvo2.setOffsaleTime(null);
//	pvo2.setShelfTime(new Timestamp(System.currentTimeMillis()));
//	pvo2.setStorageQty(1000);
//	pvo2.setIngredientCategoryNo(null);
//	pvo2.setKitchenwareCategoryNo(null);
//	pvo2.setProductNo(3);
//	pDAOIm.update(pvo2);

		// 刪除
//	pDAOIm.delete(1);

		// 查詢單一資料
	ProductVO pvo3 = pDAOIm.findByPrimaryKey(3);
	System.out.println(pvo3);
	System.out.println("---------------------");

		// 查詢全部資料
		List<ProductVO> list = pDAOIm.getAll();
		for (ProductVO apvo3 : list) {
			System.out.println(apvo3);
		}
	}
}
