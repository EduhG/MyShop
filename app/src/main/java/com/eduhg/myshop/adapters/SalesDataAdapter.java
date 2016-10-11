package com.eduhg.myshop.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.eduhg.myshop.R;
import com.eduhg.myshop.models.SalesData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by EduhG on 10/11/2016.
 */
public class SalesDataAdapter extends RecyclerView.Adapter<SalesDataAdapter.SalesDataViewHolder> {
    Context context;
    List<SalesData> dataList = new ArrayList<>();
    LayoutInflater inflater;
    Listener listener;

    public SalesDataAdapter(Context context, List<SalesData> dataList1) {

        this.context = context;
        this.dataList = dataList1;
        //this.listener = (Listener) context;
        inflater = LayoutInflater.from(context);

    }

    @Override
    public void onBindViewHolder(SalesDataViewHolder viewHolder, int position) {
        // Set item views based on your views and data model
        viewHolder.textViewName.setText(dataList.get(position).item_name);
        viewHolder.textViewSold.setText(dataList.get(position).quantity_sold);
        viewHolder.textViewRem.setText(dataList.get(position).unit_price);

    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    @Override
    public SalesDataViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View convertView = inflater.inflate(R.layout.sold_item, parent, false);
        SalesDataViewHolder viewHolder = new SalesDataViewHolder(convertView);

        return viewHolder;
    }

    class SalesDataViewHolder extends RecyclerView.ViewHolder {
        public TextView textViewName;
        public TextView textViewSold;
        public TextView textViewRem;

        // create a constructor that accepts the entire item row and does the view lookups to find each subview
        public SalesDataViewHolder(View itemView) {
            super(itemView);

            textViewName = (TextView) itemView.findViewById(R.id.item_name);
            textViewSold = (TextView) itemView.findViewById(R.id.qty_sold);
            textViewRem = (TextView) itemView.findViewById(R.id.qty_remaining);

        }
    }

}


