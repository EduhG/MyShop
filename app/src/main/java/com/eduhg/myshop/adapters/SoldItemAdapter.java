package com.eduhg.myshop.adapters;

/**
 * Created by EduhG on 10/9/2016.
 */

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.eduhg.myshop.R;
import com.eduhg.myshop.models.SoldItem;

import java.util.ArrayList;
import java.util.List;

public class SoldItemAdapter extends RecyclerView.Adapter<SoldItemAdapter.ViewHolder>{
    List<SoldItem> items;

    public SoldItemAdapter(String[] item_names, String[] qty_sold, String[] qty_remaining){
        super();
        items = new ArrayList<SoldItem>();
        for(int i =0; i<item_names.length; i++){
            SoldItem item = new SoldItem();

            item.setItemName(item_names[i]);
            item.setQtySold(qty_sold[i]);
            item.setQtyRemaining(qty_remaining[i]);

            items.add(item);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.sold_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        SoldItem list =  items.get(position);

        holder.textViewName.setText(list.getItemName());
        holder.textViewSold.setText(list.getQtySold());
        holder.textViewRem.setText(list.getQtyRemaining());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        public TextView textViewName;
        public TextView textViewSold;
        public TextView textViewRem;

        public ViewHolder(View itemView) {
            super(itemView);

            textViewName = (TextView) itemView.findViewById(R.id.item_name);
            textViewSold = (TextView) itemView.findViewById(R.id.qty_sold);
            textViewRem = (TextView) itemView.findViewById(R.id.qty_remaining);

        }
    }
}
