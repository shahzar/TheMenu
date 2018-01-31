package com.shzlabs.themenu.ui.categories;

import com.shzlabs.themenu.data.model.FoodCategory;
import com.shzlabs.themenu.ui.base.BaseView;

import java.util.List;

/**
 * Created by shaz on 18/9/17.
 */

public interface CategoriesView extends BaseView{
    void displayCategoriesList(List<FoodCategory> items);
}
