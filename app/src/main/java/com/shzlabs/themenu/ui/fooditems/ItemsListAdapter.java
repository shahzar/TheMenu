package com.shzlabs.themenu.ui.fooditems;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.shzlabs.themenu.R;
import com.shzlabs.themenu.data.model.FoodCategory;
import com.shzlabs.themenu.data.model.FoodItem;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by shaz on 20/9/17.
 */

public class ItemsListAdapter extends RecyclerView.Adapter<ItemsListAdapter.CustomViewHolder> {

    private List<FoodItem> items;
    private int lastItemPositionOnScreen = -1;
    private Context context;
    RecyclerClickListener clickListener;

    @Inject
    public ItemsListAdapter(Context context) {
        this.context = context;
        items = new ArrayList<>();
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_food_item, parent, false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {
        holder.itemName.setText(items.get(position).getItemName());
        holder.itemPrice.setText(String.valueOf(items.get(position).getUnitPrice()));
        setAnimation(holder.itemView, position);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public void onViewDetachedFromWindow(CustomViewHolder holder) {
        super.onViewDetachedFromWindow(holder);
        holder.itemView.clearAnimation();
    }

    public String getItemIdAtPosition(int position) {
        return items.get(position).getItemId();
    }


    public void setItems(List<FoodItem> items) {
        this.items.clear();
        this.items.addAll(items);
        notifyDataSetChanged();
    }

    public void addItem(FoodItem item) {
        items.add(item);
        notifyDataSetChanged();
    }

    private void setAnimation(View viewToAnimate, int position) {
        if (position > lastItemPositionOnScreen) {
            Animation animation = AnimationUtils.loadAnimation(context, android.R.anim.fade_in);
            viewToAnimate.startAnimation(animation);
            lastItemPositionOnScreen = position;
        }
    }

    void setItemClickListener(RecyclerClickListener clickListener) {
        this.clickListener = clickListener;
    }


    class CustomViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.food_item_name)
        TextView itemName;
        @BindView(R.id.food_item_desc)
        TextView itemDesc;
        @BindView(R.id.food_item_price)
        TextView itemPrice;

        public CustomViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (clickListener != null) {
                        clickListener.listItemClicked(view, CustomViewHolder.this.getLayoutPosition());
                    }
                }
            });
        }
    }

    public interface RecyclerClickListener{
        void listItemClicked(View view, int position);
    }
}
