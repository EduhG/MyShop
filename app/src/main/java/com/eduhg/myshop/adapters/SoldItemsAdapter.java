package com.eduhg.myshop.adapters;

/**
 * Created by EduhG on 10/9/2016.
 */

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.eduhg.myshop.R;
import com.eduhg.myshop.models.SoldItem;

import java.util.ArrayList;
import java.util.List;

//the custom ViewHolder which gives us access to our views
public class SoldItemsAdapter extends
        RecyclerView.Adapter<SoldItemsAdapter.ViewHolder>{

    // Store a member variable for sold items
    private List<SoldItem> mSoldItems;
    // Store the context for easy access
    private Context mContext;

    // Pass in the contact array into the constructor
    public SoldItemsAdapter(Context context, List<SoldItem> soldItems) {
        mSoldItems = soldItems;
        mContext = context;
    }

    // Easy access to the context object in the recyclerview
    private Context getContext() {
        return mContext;
    }

    // Usually involves inflating a layout from XML and returning the holder
    @Override
    public SoldItemsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.sold_item, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    // Involves populating data into the item through holder
    @Override
    public void onBindViewHolder(SoldItemsAdapter.ViewHolder viewHolder, int position) {
        // Get the data model based on position
        SoldItem soldItem = mSoldItems.get(position);

        // Set item views based on your views and data model
        viewHolder.textViewName.setText(soldItem.getItemName());
        viewHolder.textViewSold.setText(soldItem.getQtySold());
        viewHolder.textViewRem.setText(soldItem.getQtyRemaining());

    }

    // Returns the total count of items in the list
    @Override
    public int getItemCount() {
        return mSoldItems.size();
    }

    // Provide a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // member variables for any view that will be set as you render a row
        public TextView textViewName;
        public TextView textViewSold;
        public TextView textViewRem;

        // create a constructor that accepts the entire item row and does the view lookups to find each subview
        public ViewHolder(View itemView) {
            super(itemView);

            textViewName = (TextView) itemView.findViewById(R.id.item_name);
            textViewSold = (TextView) itemView.findViewById(R.id.qty_sold);
            textViewRem = (TextView) itemView.findViewById(R.id.qty_remaining);

        }
    }

}
