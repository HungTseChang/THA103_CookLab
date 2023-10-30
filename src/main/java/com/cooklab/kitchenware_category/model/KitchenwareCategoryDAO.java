package com.cooklab.kitchenware_category.model;

import java.util.List;

public interface KitchenwareCategoryDAO {
    public void insert(KitchenwareCategoryVO kitchenwareCategory);
    public void update(KitchenwareCategoryVO kitchenwareCategory);
    public String delete(KitchenwareCategoryVO kitchenwareCategory);
    public KitchenwareCategoryVO findByPrimaryKey(Integer kitchenwareCategoryNo);
    public List<KitchenwareCategoryVO> getAll();
    public boolean hasAssociatedProducts(Integer kitchenwareCategoryNo);
    public KitchenwareCategoryVO findByName(KitchenwareCategoryVO kitchenwareCategory);
}
