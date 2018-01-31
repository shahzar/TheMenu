package com.shzlabs.themenu.ui.itemdetail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.shzlabs.themenu.R;
import com.shzlabs.themenu.data.model.FoodItem;
import com.shzlabs.themenu.ui.base.BaseFragment;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ItemDetailFragment extends BaseFragment implements ItemDetailView {

    private static final String TAG = ItemDetailFragment.class.getSimpleName();
    private static final String BUNDLE_KEY_ITEM_ID = "item_id";

    View rootView;

    @Inject
    ItemDetailPresenter presenter;

    private String itemId;
    @BindView(R.id.food_item_name)
    TextView foodItemName;
    @BindView(R.id.food_item_desc)
    TextView foodItemDesc;
    @BindView(R.id.food_item_price)
    TextView foodItemPrice;

    public static ItemDetailFragment newInstance(String itemId) {

        Bundle args = new Bundle();
        args.putString(BUNDLE_KEY_ITEM_ID, itemId);

        ItemDetailFragment fragment = new ItemDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_item_detail, container, false);

        // Get Bundle
        Bundle bundle = getArguments();
        if (bundle != null) {
            itemId = bundle.getString(BUNDLE_KEY_ITEM_ID);
        }

        // Inject
        ButterKnife.bind(this, rootView);
        getTheApplication().getAppComponent().inject(this);

        presenter.attachView(this);

        presenter.loadItemDetails(itemId);

        return rootView;
    }

    @Override
    public void displayFoodItemDetails(FoodItem item) {
        foodItemName.setText(item.getItemName());
        foodItemPrice.setText(String.valueOf(item.getUnitPrice()));
    }
}
