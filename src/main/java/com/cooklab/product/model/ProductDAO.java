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
    
    
    Pair<List<ProductVO>, Long> findByKeywordWithPagination(String keyword, int page, int pageSize);
    Pair<List<ProductVO>, Long> findByCategoryKeywordWithPagination(int type,int page, int pageSize);     
    public Pair<List<ProductVO>, Long> findByKeywordWithCategorywithingredientCategoryPagination(String keyword, int page, int pageSize);
    public Pair<List<ProductVO>, Long> findByKeywordWithCategorywithkitchCategoryPagination(String keyword, int page, int pageSize);
    public List<ProductVO> findTopSearchCountProduct();
    public Pair<List<ProductVO>, Long> findHotTopSearchCountProduct(int page, int pageSize);
    
}
