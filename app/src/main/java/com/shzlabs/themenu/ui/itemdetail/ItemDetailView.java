package com.shzlabs.themenu.ui.itemdetail;

import com.shzlabs.themenu.data.model.FoodItem;
import com.shzlabs.themenu.ui.base.BaseView;

/**
 * Created by shaz on 18/9/17.
 */

public interface ItemDetailView extends BaseView{

    void displayFoodItemDetails(FoodItem item);
}
