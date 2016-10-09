package com.eduhg.myshop.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.PopupMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import com.eduhg.myshop.R;

public class SalesFragment extends Fragment {
    public SalesFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_sales, container, false);

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

    public void showPopup(View v) {
        PopupMenu popup = new PopupMenu(getActivity(), v);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.sales_actions, popup.getMenu());
        popup.show();
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
