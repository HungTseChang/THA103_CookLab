 package com.cooklab.product.model;

import java.util.List;
import java.util.Map;
import java.util.Set;



public interface ProductDAO {
    public String insert(ProductVO product);
    public String update(ProductVO product);
    public boolean delete(Integer productNo);
    public ProductVO findByPrimaryKey(Integer productNo);
    public List<ProductVO> getAll();
    public List<ProductVO> findByKeyword(String keyword);
    public ProductVO findByProductName(String productName);
    public List<ProductVO> findByProductNames(String productName, String category);
    public List<ProductVO> findByKeywordWithPagination(String keyword, int page, int pageSize);
}
