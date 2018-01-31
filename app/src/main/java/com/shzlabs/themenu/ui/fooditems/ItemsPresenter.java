package com.shzlabs.themenu.ui.fooditems;

import android.util.Log;

import com.shzlabs.themenu.data.DataManager;
import com.shzlabs.themenu.data.model.FoodCategory;
import com.shzlabs.themenu.data.model.FoodItem;
import com.shzlabs.themenu.ui.base.BasePresenter;

import java.util.List;

import javax.inject.Inject;

import rx.Subscriber;

/**
 * Created by shaz on 18/9/17.
 */

public class ItemsPresenter extends BasePresenter<ItemsView> {

    private static final String TAG = ItemsPresenter.class.getSimpleName();

    @Inject
    DataManager dataManager;

    @Inject
    public ItemsPresenter() {
    }

    public void loadItemsList(String categoryId) {

        dataManager.getItemsByCategoryId(categoryId).subscribe(new Subscriber<FoodItem>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(FoodItem foodItem) {
                getView().displayNewItem(foodItem);
            }
        });

    }
}
