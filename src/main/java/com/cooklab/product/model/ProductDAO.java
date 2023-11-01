package com.cooklab.product.model;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.cooklab.product.model.Pair;

public interface ProductDAO {
	public String insert(ProductVO product);

	public String update(ProductVO product);

	public boolean delete(Integer productNo);

	public ProductVO findByPrimaryKey(Integer productNo);

	public List<ProductVO> getAll();

	public List<ProductVO> findByKeyword(String keyword);

	public ProductVO findByProductName(String productName, String category);

	public List<ProductVO> findByProductNames(String productName, String category);


	public List<ProductVO> findTopSearchCountProduct();

	// 熱門商品
	public Pair<List<ProductVO>, Long> findHotTopSearchCountProduct(int page, int pageSize, int type, int sorts);

	// 食材
	public Pair<List<ProductVO>, Long> findIngrdinetProduct(int page, int pageSize, int type, int sorts);

	// 廚具
	public Pair<List<ProductVO>, Long> findKitchwareProduct(int page, int pageSize, int type, int sorts);

	// 總覽
	public Pair<List<ProductVO>, Long> findAllProduct(int page, int pageSize, int type, int sorts);
	
	//關鍵字
	public Pair<List<ProductVO>, Long> findByKeyword(String keyword, int page, int pageSize, int type, int sorts);

}
