package com.cooklab.ingredient_category.model;

import java.util.List;

public class IngredientCategoryTest {
	public static void main(String[] args) {

		IngredientCategoryJDBCDAOIm iCDAOIm = new IngredientCategoryJDBCDAOIm();

		// 新增
		IngredientCategoryVO iCVO1 = new IngredientCategoryVO();
		iCVO1.setCategoryName("牛腹肉");
		iCDAOIm.insert(iCVO1);

		// 修改
//		IngredientCategoryVO iCVO2 = new IngredientCategoryVO();
//		iCVO2.setIngredientCategoryNo(2);
//		iCVO2.setCategoryName("松阪豬");
//		iCDAOIm.update(iCVO2);

		// 刪除
//		iCDAOIm.delete(3);

		// 查詢單一資料
//		IngredientCategoryVO iCVO3 = iCDAOIm.findByPrimaryKey(2);
//		System.out.print(iCVO3.getIngredientCategoryNo() + ",");
//		System.out.println(iCVO3.getCategoryName() );
//		System.out.println("---------------------");

//		// 查詢全部資料
		List<IngredientCategoryVO> list = iCDAOIm.getAll();
		for (IngredientCategoryVO aiCVO3 : list) {
			System.out.print(aiCVO3.getIngredientCategoryNo() + ",");
			System.out.print(aiCVO3.getCategoryName());
			System.out.println();
		}
	}
}
