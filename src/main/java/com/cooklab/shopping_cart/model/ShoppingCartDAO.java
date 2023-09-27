package com.cooklab.shopping_cart.model;

import java.util.List;



public interface ShoppingCartDAO {
    public void insert(ShoppingCartVO shoppingCartVO);
    public void update(ShoppingCartVO shoppingCartVO);
    public void delete(Integer shoppingCartNo);
    public ShoppingCartVO findByPrimaryKey(Integer shoppingCartNo);
    public List<ShoppingCartVO> getAll();
}
