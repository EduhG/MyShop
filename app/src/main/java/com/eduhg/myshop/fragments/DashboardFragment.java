package com.eduhg.myshop.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
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

import java.util.ArrayList;

public class DashboardFragment extends Fragment {
    RecyclerView mRecyclerView;
    ArrayList<SoldItem> soldItem;

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
