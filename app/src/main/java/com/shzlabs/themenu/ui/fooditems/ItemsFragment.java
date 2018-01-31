package com.shzlabs.themenu.ui.fooditems;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shzlabs.themenu.R;
import com.shzlabs.themenu.data.model.FoodCategory;
import com.shzlabs.themenu.data.model.FoodItem;
import com.shzlabs.themenu.ui.base.BaseFragment;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ItemsFragment extends BaseFragment implements ItemsView {

    private static final String TAG = ItemsFragment.class.getSimpleName();
    private static final String BUNDLE_KEY_CATEGORY_ID = "category_id";
    private static final String BUNDLE_KEY_CATEGORY_LABEL = "category_label";

    View rootView;

    @Inject
    ItemsPresenter presenter;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @Inject
    ItemsListAdapter adapter;
    private String categoryId;
    private String categoryLabel;

    OnFoodItemSelectedListener foodItemSelectedListener;

    public static ItemsFragment newInstance(String categoryId, String categoryLabel) {

        Bundle args = new Bundle();
        args.putString(BUNDLE_KEY_CATEGORY_ID, categoryId);
        args.putString(BUNDLE_KEY_CATEGORY_LABEL, categoryLabel);

        ItemsFragment fragment = new ItemsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFoodItemSelectedListener) {
            foodItemSelectedListener = (OnFoodItemSelectedListener) context;
        }else{
            throw new ClassCastException(context.toString()
                    + " must implement " + OnFoodItemSelectedListener.class.getCanonicalName());
        }
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_categories, container, false);

        // Get Bundle
        Bundle bundle = getArguments();
        if (bundle != null) {
            categoryId = bundle.getString(BUNDLE_KEY_CATEGORY_ID);
            categoryLabel = bundle.getString(BUNDLE_KEY_CATEGORY_LABEL);
        }

        // Set title
        if (categoryLabel != null) {
            getActivity().setTitle(categoryLabel);
        }

        // Inject
        ButterKnife.bind(this, rootView);
        getTheApplication().getAppComponent().inject(this);

        presenter.attachView(this);

        // Setup recycler view
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        presenter.loadItemsList(categoryId);

        adapter.setItemClickListener(new ItemsListAdapter.RecyclerClickListener() {
            @Override
            public void listItemClicked(View view, int position) {
                foodItemSelectedListener.displayFoodItemDetailsFragment(adapter.getItemIdAtPosition(position));
            }
        });

        return rootView;
    }

    @Override
    public void displayItemsList(List<FoodItem> items) {
        adapter.setItems(items);
    }

    @Override
    public void displayNewItem(FoodItem item) {
        adapter.addItem(item);
    }

    public interface OnFoodItemSelectedListener{
        void displayFoodItemDetailsFragment(String itemId);
    }
}
