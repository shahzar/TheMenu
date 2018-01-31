package com.shzlabs.themenu.ui.categories;

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
import com.shzlabs.themenu.ui.base.BaseFragment;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CategoriesFragment extends BaseFragment implements CategoriesView {

    private static final String TAG = CategoriesFragment.class.getSimpleName();

    View rootView;

    @Inject
    CategoriesPresenter presenter;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @Inject
    CategoriesListAdapter adapter;

    OnCategoryItemSelectedListener listener;

    public static CategoriesFragment newInstance() {

        Bundle args = new Bundle();

        CategoriesFragment fragment = new CategoriesFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnCategoryItemSelectedListener) {
            listener = (OnCategoryItemSelectedListener) context;
        }else{
            throw new ClassCastException(context.toString()
                    + " must implement " + OnCategoryItemSelectedListener.class.getCanonicalName());
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_categories, container, false);

        // Inject
        ButterKnife.bind(this, rootView);
        getTheApplication().getAppComponent().inject(this);

        // Set title
        getActivity().setTitle(getActivity().getResources().getString(R.string.app_name));

        presenter.attachView(this);

        // Setup recycler view
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        presenter.loadCategoriesList();

        adapter.setItemClickListener(new CategoriesListAdapter.RecyclerClickListener() {
            @Override
            public void listItemClicked(View view, int position) {
                FoodCategory category = adapter.getCategoryAtPosition(position);
                listener.displayFoodItemFragment(category.getCategoryId(),
                        category.getLabel());
            }
        });

        return rootView;
    }

    @Override
    public void displayCategoriesList(List<FoodCategory> items) {
        adapter.setItems(items);
    }

    public interface OnCategoryItemSelectedListener{
        void displayFoodItemFragment(String categoryId, String categoryLabel);
    }
}
