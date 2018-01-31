package com.shzlabs.themenu.ui.fooditems;

import com.shzlabs.themenu.data.model.FoodCategory;
import com.shzlabs.themenu.data.model.FoodItem;
import com.shzlabs.themenu.ui.base.BaseView;

import java.util.List;

/**
 * Created by shaz on 18/9/17.
 */

public interface ItemsView extends BaseView{
    void displayItemsList(List<FoodItem> items);
    void displayNewItem(FoodItem item);
}
