package com.shzlabs.themenu.data;

import com.shzlabs.themenu.data.model.FoodCategory;
import com.shzlabs.themenu.data.model.FoodItem;
import com.shzlabs.themenu.data.remote.FirebaseHelper;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;

/**
 * Created by shaz on 20/9/17.
 */

public class DataManager {

    @Inject
    FirebaseHelper firebaseHelper;

    @Inject
    public DataManager() {
    }

    public Observable<List<FoodCategory>> getCategories() {
        return firebaseHelper.getCategoriesList();
    }

    public Observable<FoodItem> getItemsByCategoryId(String categoryId) {
        return firebaseHelper.getItemsListByCategoryId(categoryId);
    }

    public Observable<FoodItem> getItemById(String itemId) {
        return firebaseHelper.getItemById(itemId);
    }
}
