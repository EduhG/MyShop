package com.eduhg.myshop.fragments;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.DropBoxManager;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import com.eduhg.myshop.R;
import com.eduhg.myshop.adapters.SoldItemsAdapter;
import com.eduhg.myshop.models.SoldItem;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class DashboardFragment extends Fragment {
    RecyclerView mRecyclerView;
    ArrayList<SoldItem> soldItem;

    BarChart barChart;

    public DashboardFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_dashboard, container, false);

        // Lookup the recyclerview in activity layout
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView);

        // Initialize contacts
        soldItem = SoldItem.createSoldItemsList(5);
        // Create adapter passing in the sample user data
        SoldItemsAdapter adapter = new SoldItemsAdapter(getContext(), soldItem);
        // Attach the adapter to the recyclerview to populate items
        mRecyclerView.setAdapter(adapter);
        // Set layout manager to position the items
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        final ImageButton imageButton = (ImageButton) rootView.findViewById(R.id.soldItems);

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Clicked", Toast.LENGTH_LONG).show();
                showPopupMenu(view);
            }
        });

        barChart = (BarChart) rootView.findViewById(R.id.profitsChart);

        /*ArrayList<BarEntry> chartEntries = new ArrayList<>();
        chartEntries.add(new BarEntry(44f, 0));
        chartEntries.add(new BarEntry(20f, 1));
        chartEntries.add(new BarEntry(67f, 2));
        chartEntries.add(new BarEntry(43f, 3));
        chartEntries.add(new BarEntry(30f, 4));

        BarDataSet barDataSet = new BarDataSet(chartEntries, "Profits");

        ArrayList<String> weeks = new ArrayList<>();
        weeks.add("Week 1");
        weeks.add("Week 2");
        weeks.add("Week 3");
        weeks.add("Week 4");
        weeks.add("Week 5");

        BarData barData = new BarData(barDataSet);
        barChart.setData(barData);*/

        ArrayList<BarEntry> entries = new ArrayList<>();
        entries.add(new BarEntry(0f, 30f));
        entries.add(new BarEntry(1f, 20f));
        entries.add(new BarEntry(2f, 40f));
        entries.add(new BarEntry(3f, 30f));
        // gap of 2f
        entries.add(new BarEntry(4f, 50f));

        BarDataSet set = new BarDataSet(entries, "BarDataSet");
        set.setColors(ColorTemplate.COLORFUL_COLORS);

        BarData data = new BarData(set);
        data.setBarWidth(0.8f); // set custom bar width
        barChart.setData(data);
        barChart.setDescription("");
        barChart.setDrawGridBackground(false);
        barChart.animateY(2000);
        barChart.setFitBars(true); // make the x-axis fit exactly all bars
        barChart.invalidate(); // refresh

        return rootView;
    }

    private void showPopupMenu(View view) {
        PopupMenu popupmenu = new PopupMenu(getActivity(), view);
        popupmenu.getMenuInflater().inflate(R.menu.sales_actions, popupmenu.getMenu());
        popupmenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuitem) {
                switch (menuitem.getItemId()) {
                    case R.id.action_search:
                        Toast.makeText(getActivity(), "Search Clicked", Toast.LENGTH_LONG).show();
                        break;
                    case R.id.action_settings:
                        Toast.makeText(getActivity(), "Settings Clicked", Toast.LENGTH_LONG).show();
                        break;

                    default:
                        break;

                }

                return true;
            }
        });

        popupmenu.show();
    }
}
