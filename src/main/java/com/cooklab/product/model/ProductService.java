package com.cooklab.product.model;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cooklab.recipe.model.RecipeVO;
import com.cooklab.util.HibernateUtil;
import com.cooklab.util.JedisUtil;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class ProductService {

	private ProductDAO dao, dao2;

	public ProductService() {
		dao = new ProductHDAOIm(HibernateUtil.getSessionFactory());
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

	public List<ProductVO> findByKeyword(String keyword) {
		return dao.findByKeyword(keyword);
	}

//	public ProductVO findByProductName(String productName) {
//		return dao2.findByProductName(productName);
//	}

	public List<ProductVO> findByProductNames(String productName, String category) {
		return dao2.findByProductNames(productName, category);
	}

	public String insert(ProductVO product) {
		return dao.insert(product);
	}

	public String update(ProductVO productVO) {
		return dao.update(productVO);
	}


	public List<ProductVO> findTopSearchCountProduct() {
		return dao.findTopSearchCountProduct();
	}

	// 熱門商品
	public List<Map<String, String>> findHotTopSearchCountProduct(int page, int pageSize, int type, int sorts) {

		Pair<List<ProductVO>, Long> pair = dao.findHotTopSearchCountProduct(page, pageSize, type, sorts);

		List<ProductVO> listproduct = (List<ProductVO>) pair.getFirst();
		Long totalProductCount = (Long) pair.getSecond();

		System.out.println(totalProductCount);

		if (pair.getSecond() == 0) {
			List<Map<String, String>> error = new ArrayList<>();
			Map<String, String> noproduct = new HashMap<>();
			noproduct.put("Noproduct", "Noproduct");

			error.add(noproduct);
			return error;
		}

		List<Map<String, String>> dataMapList = new ArrayList<>();

		for (ProductVO item : listproduct) {
			Map<String, String> itemMap = new HashMap<>();

			itemMap.put("totalProductCount", String.valueOf(totalProductCount));
			itemMap.put("currentPage", String.valueOf(page));
			itemMap.put("pageSize", String.valueOf(pageSize));
			itemMap.put("products", String.valueOf(listproduct));
			String productNo = item.getProductNo().toString();
			itemMap.put("productNo", productNo);
			String productName = item.getProductName();
			itemMap.put("productName", productName);
			byte[] productPicture = item.getProductPicture();
			if (productPicture != null) {
				String productImage = Base64.getEncoder().encodeToString(productPicture);
				itemMap.put("productImage", productImage);
			} else {
				itemMap.put("productImage", "");
			}
			String productDec = item.getProductDec();
			itemMap.put("productDec", productDec);
			String productIntroduction = item.getProductIntroduction();
			itemMap.put("productIntroduction", productIntroduction);
			String productPrice = item.getProductPrice().toString();
			itemMap.put("productPrice", productPrice);
			if (item.getOffsaleTime() != null) {
				String offsaleTime = item.getOffsaleTime().toString();
				itemMap.put("offsaleTime", offsaleTime);
			} else {
				itemMap.put("offsaleTime", "無設定");
			}
			if (item.getShelfTime() != null) {
				String shelfTime = item.getShelfTime().toString();
				itemMap.put("shelfTime", shelfTime);
			} else {
				itemMap.put("shelfTime", "無設定");
			}
			if (item.getIngredientCategory() != null) {
				String ingredientCategory = item.getIngredientCategory().getCategoryName();
				itemMap.put("Category", ingredientCategory);
				itemMap.put("Categorytype", "ingredientCategory");
			}
			if (item.getKitchenwareCategory() != null) {
				String kitchenwareCategory = item.getKitchenwareCategory().getCategoryName();
				itemMap.put("Category", kitchenwareCategory);
				itemMap.put("Categorytype", "kitchenwareCategory");
			}
			dataMapList.add(itemMap);
		}

		System.out.println(dataMapList);
		System.out.println(totalProductCount);

		return dataMapList;
	}

	// 食材
	public List<Map<String, String>> findIngrdinetProduct(int page, int pageSize, int type, int sorts) {
		Pair<List<ProductVO>, Long> pair = dao.findIngrdinetProduct(page, pageSize, type, sorts);

		List<ProductVO> listproduct = (List<ProductVO>) pair.getFirst();
		Long totalProductCount = (Long) pair.getSecond();

		if (pair.getSecond() == 0) {
			List<Map<String, String>> error = new ArrayList<>();
			Map<String, String> noproduct = new HashMap<>();
			noproduct.put("Noproduct", "Noproduct");

			error.add(noproduct);
			return error;
		}			
		System.out.println(totalProductCount);

		List<Map<String, String>> dataMapList = new ArrayList<>();

		for (ProductVO item : listproduct) {
			Map<String, String> itemMap = new HashMap<>();

			itemMap.put("totalProductCount", String.valueOf(totalProductCount));
			itemMap.put("currentPage", String.valueOf(page));
			itemMap.put("pageSize", String.valueOf(pageSize));
			itemMap.put("products", String.valueOf(listproduct));
			String productNo = item.getProductNo().toString();
			itemMap.put("productNo", productNo);
			String productName = item.getProductName();
			itemMap.put("productName", productName);
			byte[] productPicture = item.getProductPicture();
			if (productPicture != null) {
				String productImage = Base64.getEncoder().encodeToString(productPicture);
				itemMap.put("productImage", productImage);
			} else {
				itemMap.put("productImage", "");
			}
			String productDec = item.getProductDec();
			itemMap.put("productDec", productDec);
			String productIntroduction = item.getProductIntroduction();
			itemMap.put("productIntroduction", productIntroduction);
			String productPrice = item.getProductPrice().toString();
			itemMap.put("productPrice", productPrice);
			if (item.getOffsaleTime() != null) {
				String offsaleTime = item.getOffsaleTime().toString();
				itemMap.put("offsaleTime", offsaleTime);
			} else {
				itemMap.put("offsaleTime", "無設定");
			}
			if (item.getShelfTime() != null) {
				String shelfTime = item.getShelfTime().toString();
				itemMap.put("shelfTime", shelfTime);
			} else {
				itemMap.put("shelfTime", "無設定");
			}
			if (item.getIngredientCategory() != null) {
				String ingredientCategory = item.getIngredientCategory().getCategoryName();
				itemMap.put("Category", ingredientCategory);
				itemMap.put("Categorytype", "ingredientCategory");
			}
			if (item.getKitchenwareCategory() != null) {
				String kitchenwareCategory = item.getKitchenwareCategory().getCategoryName();
				itemMap.put("Category", kitchenwareCategory);
				itemMap.put("Categorytype", "kitchenwareCategory");
			}
			dataMapList.add(itemMap);
		}

		System.out.println(dataMapList);
		System.out.println(totalProductCount);

		return dataMapList;
	}

	// 廚具
	public List<Map<String, String>> findKitchwareProduct(int page, int pageSize, int type, int sorts) {
		Pair<List<ProductVO>, Long> pair = dao.findKitchwareProduct(page, pageSize, type, sorts);

		List<ProductVO> listproduct = (List<ProductVO>) pair.getFirst();
		Long totalProductCount = (Long) pair.getSecond();

		
		if (pair.getSecond() == 0) {
			List<Map<String, String>> error = new ArrayList<>();
			Map<String, String> noproduct = new HashMap<>();
			noproduct.put("Noproduct", "Noproduct");

			error.add(noproduct);
			return error;
		}
		System.out.println(totalProductCount);

		List<Map<String, String>> dataMapList = new ArrayList<>();
		

		for (ProductVO item : listproduct) {
			Map<String, String> itemMap = new HashMap<>();

			itemMap.put("totalProductCount", String.valueOf(totalProductCount));
			itemMap.put("currentPage", String.valueOf(page));
			itemMap.put("pageSize", String.valueOf(pageSize));
			itemMap.put("products", String.valueOf(listproduct));
			String productNo = item.getProductNo().toString();
			itemMap.put("productNo", productNo);
			String productName = item.getProductName();
			itemMap.put("productName", productName);
			byte[] productPicture = item.getProductPicture();
			if (productPicture != null) {
				String productImage = Base64.getEncoder().encodeToString(productPicture);
				itemMap.put("productImage", productImage);
			} else {
				itemMap.put("productImage", "");
			}
			String productDec = item.getProductDec();
			itemMap.put("productDec", productDec);
			String productIntroduction = item.getProductIntroduction();
			itemMap.put("productIntroduction", productIntroduction);
			String productPrice = item.getProductPrice().toString();
			itemMap.put("productPrice", productPrice);
			if (item.getOffsaleTime() != null) {
				String offsaleTime = item.getOffsaleTime().toString();
				itemMap.put("offsaleTime", offsaleTime);
			} else {
				itemMap.put("offsaleTime", "無設定");
			}
			if (item.getShelfTime() != null) {
				String shelfTime = item.getShelfTime().toString();
				itemMap.put("shelfTime", shelfTime);
			} else {
				itemMap.put("shelfTime", "無設定");
			}
			if (item.getIngredientCategory() != null) {
				String ingredientCategory = item.getIngredientCategory().getCategoryName();
				itemMap.put("Category", ingredientCategory);
				itemMap.put("Categorytype", "ingredientCategory");
			}
			if (item.getKitchenwareCategory() != null) {
				String kitchenwareCategory = item.getKitchenwareCategory().getCategoryName();
				itemMap.put("Category", kitchenwareCategory);
				itemMap.put("Categorytype", "kitchenwareCategory");
			}
			dataMapList.add(itemMap);
		}

		System.out.println(dataMapList);
		System.out.println(totalProductCount);

		return dataMapList;
	}

	// 總覽
	public List<Map<String, String>> findAllProduct(int page, int pageSize, int type, int sorts) {
		Pair<List<ProductVO>, Long> pair = dao.findAllProduct(page, pageSize, type, sorts);

		List<ProductVO> listproduct = (List<ProductVO>) pair.getFirst();
		Long totalProductCount = (Long) pair.getSecond();

		System.out.println(totalProductCount);

		List<Map<String, String>> dataMapList = new ArrayList<>();

		for (ProductVO item : listproduct) {
			Map<String, String> itemMap = new HashMap<>();

			itemMap.put("totalProductCount", String.valueOf(totalProductCount));
			itemMap.put("currentPage", String.valueOf(page));
			itemMap.put("pageSize", String.valueOf(pageSize));
			itemMap.put("products", String.valueOf(listproduct));
			String productNo = item.getProductNo().toString();
			itemMap.put("productNo", productNo);
			String productName = item.getProductName();
			itemMap.put("productName", productName);
			byte[] productPicture = item.getProductPicture();
			if (productPicture != null) {
				String productImage = Base64.getEncoder().encodeToString(productPicture);
				itemMap.put("productImage", productImage);
			} else {
				itemMap.put("productImage", "");
			}
			String productDec = item.getProductDec();
			itemMap.put("productDec", productDec);
			String productIntroduction = item.getProductIntroduction();
			itemMap.put("productIntroduction", productIntroduction);
			String productPrice = item.getProductPrice().toString();
			itemMap.put("productPrice", productPrice);
			if (item.getOffsaleTime() != null) {
				String offsaleTime = item.getOffsaleTime().toString();
				itemMap.put("offsaleTime", offsaleTime);
			} else {
				itemMap.put("offsaleTime", "無設定");
			}
			if (item.getShelfTime() != null) {
				String shelfTime = item.getShelfTime().toString();
				itemMap.put("shelfTime", shelfTime);
			} else {
				itemMap.put("shelfTime", "無設定");
			}
			if (item.getIngredientCategory() != null) {
				String ingredientCategory = item.getIngredientCategory().getCategoryName();
				itemMap.put("Category", ingredientCategory);
				itemMap.put("Categorytype", "ingredientCategory");
			}
			if (item.getKitchenwareCategory() != null) {
				String kitchenwareCategory = item.getKitchenwareCategory().getCategoryName();
				itemMap.put("Category", kitchenwareCategory);
				itemMap.put("Categorytype", "kitchenwareCategory");
			}
			dataMapList.add(itemMap);
		}

		System.out.println(dataMapList);
		System.out.println(totalProductCount);

		return dataMapList;
	}

	// 關鍵字
	public List<Map<String, String>> findByKeyword(String keyword, int page, int pageSize, int type, int sorts) {
		Pair<List<ProductVO>, Long> pair = dao.findByKeyword(keyword, page, pageSize, type, sorts);

		List<ProductVO> listproduct = (List<ProductVO>) pair.getFirst();
		Long totalProductCount = (Long) pair.getSecond();

		System.out.println(totalProductCount);

		if (pair.getSecond() == 0) {
			List<Map<String, String>> error = new ArrayList<>();
			Map<String, String> noproduct = new HashMap<>();
			noproduct.put("Noproduct", "Noproduct");

			error.add(noproduct);
			return error;
		}

		List<Map<String, String>> dataMapList = new ArrayList<>();
		for (ProductVO item : listproduct) {
			Map<String, String> itemMap = new HashMap<>();

			try {
				incrementProductSearchCount(item.getProductNo());
			} catch (Exception e) {
				e.printStackTrace();
			}

			itemMap.put("totalProductCount", String.valueOf(totalProductCount));
			itemMap.put("currentPage", String.valueOf(page));
			itemMap.put("pageSize", String.valueOf(pageSize));
			itemMap.put("products", String.valueOf(listproduct));
			String productNo = item.getProductNo().toString();
			itemMap.put("productNo", productNo);
			String productName = item.getProductName();
			itemMap.put("productName", productName);
			byte[] productPicture = item.getProductPicture();
			if (productPicture != null) {
				String productImage = Base64.getEncoder().encodeToString(productPicture);
				itemMap.put("productImage", productImage);
			} else {
				itemMap.put("productImage", "");
			}
			String productDec = item.getProductDec();
			itemMap.put("productDec", productDec);
			String productIntroduction = item.getProductIntroduction();
			itemMap.put("productIntroduction", productIntroduction);
			String productPrice = item.getProductPrice().toString();
			itemMap.put("productPrice", productPrice);
			if (item.getOffsaleTime() != null) {
				String offsaleTime = item.getOffsaleTime().toString();
				itemMap.put("offsaleTime", offsaleTime);
			} else {
				itemMap.put("offsaleTime", "無設定");
			}
			if (item.getShelfTime() != null) {
				String shelfTime = item.getShelfTime().toString();
				itemMap.put("shelfTime", shelfTime);
			} else {
				itemMap.put("shelfTime", "無設定");
			}
			if (item.getIngredientCategory() != null) {
				String ingredientCategory = item.getIngredientCategory().getCategoryName();
				itemMap.put("Category", ingredientCategory);
				itemMap.put("Categorytype", "ingredientCategory");
			}
			if (item.getKitchenwareCategory() != null) {
				String kitchenwareCategory = item.getKitchenwareCategory().getCategoryName();
				itemMap.put("Category", kitchenwareCategory);
				itemMap.put("Categorytype", "kitchenwareCategory");
			}
			dataMapList.add(itemMap);
		}

		System.out.println(dataMapList);
		System.out.println(totalProductCount);

		return dataMapList;
	}

	// 搜尋次數
	private void incrementProductSearchCount(int productNo) {
		JedisPool jedisPool = JedisUtil.getJedisPool();
		Jedis jedis = jedisPool.getResource();
		try {
			jedis.select(2);
			String productKey = "product_searchCount" ;
			jedis.hincrBy(productKey, String.valueOf(productNo), 1);
			System.out.println("success");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			jedis.close();
		}
	}
	
	public String updateSearchCount(String productNo, String searchCount) {
		ProductVO productVO = dao.findByPrimaryKey(Integer.valueOf(productNo));

		int oldSearch = 0;
		if(productVO.getSearchCount()!= null) {
			oldSearch = productVO.getSearchCount();
		}else {
			oldSearch = 0;
		}
		productVO.setSearchCount(oldSearch + Integer.valueOf(searchCount));
		return dao.update(productVO);
	}
}
