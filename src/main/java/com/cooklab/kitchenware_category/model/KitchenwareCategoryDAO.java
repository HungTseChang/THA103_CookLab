package com.cooklab.kitchenware_category.model;

import java.util.List;

public interface KitchenwareCategoryDAO {
    public void insert(KitchenwareCategoryVO kitchenwareCategory);
    public void update(KitchenwareCategoryVO kitchenwareCategory);
    public void delete(Integer kitchenwareCategoryNo);
    public KitchenwareCategoryVO findByPrimaryKey(Integer kitchenwareCategoryNo);
    public List<KitchenwareCategoryVO> getAll();
}
