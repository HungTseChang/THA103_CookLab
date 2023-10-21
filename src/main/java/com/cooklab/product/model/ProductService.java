package com.cooklab.product.model;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import com.cooklab.util.HibernateUtil;

public class ProductService {

	private ProductDAO dao, dao2;

	public ProductService() {
//		dao = new ProductHDAOIm(HibernateUtil.getSessionFactory());
		dao2 = new ProductHDAOrRecipeTest(HibernateUtil.getSessionFactory());// 測試
	}

	public ProductVO addProduct(String productName, Integer saleQty, String productDec, String productIntroduction,
			Integer productPrice, Timestamp offsaleTime, Timestamp shelfTime, Integer storageQty,
			Integer ingredientCategoryNo, Integer kitchenwareCategoryNo, byte[] productPicture) {

		ProductVO productVO = new ProductVO();
		productVO.setProductName(productName);
		productVO.setProductDec(productDec);
		productVO.setProductIntroduction(productIntroduction);
		productVO.setProductPrice(productPrice);
		productVO.setSaleQty(saleQty);
		productVO.setOffsaleTime(offsaleTime);
		productVO.setShelfTime(shelfTime);
		productVO.setStorageQty(storageQty);
		productVO.setIngredientCategoryNo(ingredientCategoryNo);
		productVO.setKitchenwareCategoryNo(kitchenwareCategoryNo);
		productVO.setProductPicture(productPicture);

		dao.insert(productVO);

		return productVO;
	}

	public ProductVO updateProduct(Integer productNo, String productName, Integer saleQty, String productDec,
			String productIntroduction, Integer productPrice, Timestamp offsaleTime, Timestamp shelfTime,
			Integer storageQty, Integer ingredientCategoryNo, Integer kitchenwareCategoryNo, Integer searchCount,
			byte[] productPicture) {

		ProductVO productVO = new ProductVO();
		productVO.setProductNo(productNo);
		productVO.setProductName(productName);
		productVO.setProductDec(productDec);
		productVO.setProductIntroduction(productIntroduction);
		productVO.setProductPrice(productPrice);
		productVO.setSaleQty(saleQty);
		productVO.setOffsaleTime(offsaleTime);
		productVO.setShelfTime(shelfTime);
		productVO.setStorageQty(storageQty);
		productVO.setIngredientCategoryNo(ingredientCategoryNo);
		productVO.setKitchenwareCategoryNo(kitchenwareCategoryNo);
		productVO.setSearchCount(searchCount);
		if (productPicture != null) {
			productVO.setProductPicture(productPicture);
		} else {
			productPicture = dao.findByPrimaryKey(productNo).getProductPicture();
			productVO.setProductPicture(productPicture);
		}
		System.out.println("Received productNo: " + productNo);
		dao.update(productVO);
		return productVO;
	}

	public void deleteProduct(Integer productNo) {
		dao.delete(productNo);
	}

	public ProductVO getOneProduct(Integer productNo) {
		return dao.findByPrimaryKey(productNo);
	}

	public List<ProductVO> getAll() {
		return dao.getAll();
	}

	public List<Map<String, Object>> findByKeyword(String keyword) {
		return dao.findByKeyword(keyword);
	}

	public ProductVO findByProductName(String productName) {
		return dao2.findByProductName(productName);
	}

	public List<ProductVO> findByProductNames(String productName, String category) {
		return dao2.findByProductNames(productName, category);
	}

}
