 package com.cooklab.product.model;

import java.util.List;

public interface ProductDAO {
    public void insert(ProductVO product);
    public boolean update(ProductVO product);
    public boolean delete(Integer productNo);
    public ProductVO findByPrimaryKey(Integer productNo);
    public List<ProductVO> getAll();
}
