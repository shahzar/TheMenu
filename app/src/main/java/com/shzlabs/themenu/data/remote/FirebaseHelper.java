package com.shzlabs.themenu.data.remote;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.shzlabs.themenu.data.model.FoodCategory;
import com.shzlabs.themenu.data.model.FoodItem;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import rx.Emitter;
import rx.Observable;
import rx.functions.Action1;

/**
 * Created by shaz on 20/9/17.
 */

public class FirebaseHelper {

    private static final String TAG = FirebaseHelper.class.getSimpleName();

    FirebaseDatabase firebaseDatabase;

    @Inject
    public FirebaseHelper(FirebaseDatabase firebaseDatabase) {
        this.firebaseDatabase = firebaseDatabase;
    }

    private DatabaseReference getCategoriesDbRef() {
        DatabaseReference dbRef = firebaseDatabase.getReference("data/food-menu/categories");
        dbRef.keepSynced(true);
        return  dbRef;
    }

    private DatabaseReference getFoodItemsDbRef() {
        DatabaseReference dbRef = firebaseDatabase.getReference("data/food-menu/items");
        dbRef.keepSynced(true);
        return  dbRef;
    }

    /**
     * Fetches list of all Food Categories
     * @return
     */
    public Observable<List<FoodCategory>> getCategoriesList() {

        final DatabaseReference dbRef = getCategoriesDbRef();

        return Observable.create(new Action1<Emitter<List<FoodCategory>>>() {
            @Override
            public void call(final Emitter<List<FoodCategory>> emitter) {

                // Firebase data fetch
                dbRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        List<FoodCategory> categoryList = new ArrayList<>();

                        for (DataSnapshot itemSnapshot: dataSnapshot.getChildren()) {
                            FoodCategory category = itemSnapshot.getValue(FoodCategory.class);
                            categoryList.add(category);
                        }
                        emitter.onNext(categoryList);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        emitter.onError(new Throwable(databaseError.getMessage()));
                    }
                });
            }
        }, Emitter.BackpressureMode.BUFFER);

    }

    /**
     * Fetches list of all Items in a specified Category
     * @param categoryId
     * @return
     */
    public Observable<FoodItem> getItemsListByCategoryId(final String categoryId) {

        final DatabaseReference categoriesDbRef = getCategoriesDbRef();
        final DatabaseReference foodItemsDbRef = getFoodItemsDbRef();

        return Observable.create(new Action1<Emitter<FoodItem>>() {
            @Override
            public void call(final Emitter<FoodItem> emitter) {

                // Firebase fetch Food-Item Ids in given Food-Category
                categoriesDbRef.child(categoryId).child("items-ids").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        List<String> itemIds = new ArrayList<>();

                        for (final DataSnapshot data : dataSnapshot.getChildren()) {
                            String id = data.getValue(String.class);
                            itemIds.add(id);

                            // Firebase fetch Food-Item details from Food-Item Id
                            foodItemsDbRef.child(id).addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    FoodItem foodItem = dataSnapshot.getValue(FoodItem.class);
                                    emitter.onNext(foodItem);
                                }

                                @Override
                                public void onCancelled(DatabaseError databaseError) {
                                    emitter.onError(new Throwable(databaseError.getMessage()));
                                }
                            });

                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        emitter.onError(new Throwable(databaseError.getMessage()));
                    }
                });
            }
        }, Emitter.BackpressureMode.BUFFER);
    }

    /**
     * Fetches FoodItem data from FoodItem Id
     * @param itemId
     */
    public Observable<FoodItem> getItemById(final String itemId) {
        final DatabaseReference foodItemsDbRef = getFoodItemsDbRef();

        return Observable.create(new Action1<Emitter<FoodItem>>() {
            @Override
            public void call(final Emitter<FoodItem> emitter) {
                foodItemsDbRef.child(itemId).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        FoodItem foodItem = dataSnapshot.getValue(FoodItem.class);
                        emitter.onNext(foodItem);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        emitter.onError(new Throwable(databaseError.getMessage()));
                    }
                });
            }
        }, Emitter.BackpressureMode.BUFFER);

    }

}
