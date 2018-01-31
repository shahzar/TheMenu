package com.shzlabs.themenu.ui.itemdetail;

import com.shzlabs.themenu.data.DataManager;
import com.shzlabs.themenu.data.model.FoodItem;
import com.shzlabs.themenu.ui.base.BasePresenter;

import javax.inject.Inject;

import rx.Subscriber;

/**
 * Created by shaz on 18/9/17.
 */

public class ItemDetailPresenter extends BasePresenter<ItemDetailView> {

    @Inject
    DataManager dataManager;

    @Inject
    public ItemDetailPresenter() {
    }

    public void loadItemDetails(String itemId) {
        dataManager.getItemById(itemId).subscribe(new Subscriber<FoodItem>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(FoodItem item) {
                getView().displayFoodItemDetails(item);
            }
        });
    }
}
