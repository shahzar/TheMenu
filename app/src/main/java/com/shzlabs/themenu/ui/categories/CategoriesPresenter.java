package com.shzlabs.themenu.ui.categories;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.shzlabs.themenu.data.DataManager;
import com.shzlabs.themenu.data.model.FoodCategory;
import com.shzlabs.themenu.ui.base.BasePresenter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import rx.Subscriber;

/**
 * Created by shaz on 18/9/17.
 */

public class CategoriesPresenter extends BasePresenter<CategoriesView> {

    private static final String TAG = CategoriesPresenter.class.getSimpleName();

    @Inject
    DataManager dataManager;

    @Inject
    public CategoriesPresenter() {
    }

    public void loadCategoriesList() {

        dataManager.getCategories().subscribe(new Subscriber<List<FoodCategory>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "onError: ", e);
            }

            @Override
            public void onNext(List<FoodCategory> foodCategoryList) {
                getView().displayCategoriesList(foodCategoryList);
            }
        });

    }
}
