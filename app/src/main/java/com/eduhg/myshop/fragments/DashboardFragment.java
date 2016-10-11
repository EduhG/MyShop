package com.eduhg.myshop.fragments;

import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.DropBoxManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.eduhg.myshop.R;
import com.eduhg.myshop.adapters.SalesDataAdapter;
import com.eduhg.myshop.adapters.SoldItemsAdapter;
import com.eduhg.myshop.database.DBHelper;
import com.eduhg.myshop.dialogFragments.NewSaleDialogFragment;
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

    FloatingActionButton fab_plus, fab_sales, fab_expense;
    Animation fab_open, fab_close, fab_clockwise, fab_anticlockwise;
    boolean isOpen = false;

    DBHelper dbHelper;
    SalesDataAdapter salesDataAdapter;

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

        /*// Lookup the recyclerview in activity layout
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView);

        // Initialize contacts
        soldItem = SoldItem.createSoldItemsList(5);
        // Create adapter passing in the sample user data
        SoldItemsAdapter adapter = new SoldItemsAdapter(getContext(), soldItem);
        // Attach the adapter to the recyclerview to populate items
        mRecyclerView.setAdapter(adapter);
        // Set layout manager to position the items
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));*/


        /*        Querrying data from sqlite db and adding it to recycler view
        */

        dbHelper = DBHelper.getInstance(getContext());

        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView);
        salesDataAdapter = new SalesDataAdapter(getActivity(), dbHelper.getAllSalesData());
        mRecyclerView.setAdapter(salesDataAdapter);
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

        fab_plus = (FloatingActionButton) rootView.findViewById(R.id.fab_plus);
        fab_expense = (FloatingActionButton) rootView.findViewById(R.id.fab_expenses);
        fab_sales = (FloatingActionButton) rootView.findViewById(R.id.fab_sales);

        fab_open = AnimationUtils.loadAnimation(getContext(), R.anim.fab_open);
        fab_close = AnimationUtils.loadAnimation(getContext(), R.anim.fab_close);
        fab_clockwise = AnimationUtils.loadAnimation(getContext(), R.anim.fab_rotate_clockwise);
        fab_anticlockwise = AnimationUtils.loadAnimation(getContext(), R.anim.fab_rotate_anticlockwise);

        fab_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isOpen){
                    fab_expense.startAnimation(fab_close);
                    fab_sales.startAnimation(fab_close);
                    fab_plus.startAnimation(fab_anticlockwise);

                    fab_expense.setClickable(false);
                    fab_sales.setClickable(false);

                    isOpen = false;
                } else {
                    fab_expense.startAnimation(fab_open);
                    fab_sales.startAnimation(fab_open);
                    fab_plus.startAnimation(fab_clockwise);

                    fab_expense.setClickable(true);
                    fab_sales.setClickable(true);

                    isOpen = true;
                }
            }
        });

        FloatingActionButton fab = (FloatingActionButton) rootView.findViewById(R.id.fab_sales);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showEditDialog();
            }
        });

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

    private void showEditDialog() {
        FragmentManager fm = getActivity().getSupportFragmentManager();
        NewSaleDialogFragment newTopicFragment = NewSaleDialogFragment.newInstance("Add Stock");
        //NewSaleDialogFragment newTopicFragment = new NewSaleDialogFragment();
        newTopicFragment.show(fm, "new_sales_entry");

        /* AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setTitle("Add Stock");
        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();

        //View transactionLayout = View.inflate(getActivity(), R.layout.new_sales_entry, null);
        //builder.setView(transactionLayout);

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        builder.setView(inflater.inflate(R.layout.new_sales_entry, null))
                // Add action buttons
                .setPositiveButton("Save", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        // sign in the user ...
                        Toast.makeText(getActivity(), "Settings Clicked", Toast.LENGTH_LONG).show();
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //NewTopicFragment.this.getDialog().cancel();
                    }
                });
        builder.show();*/

    }

}
