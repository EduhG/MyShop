package com.eduhg.myshop.dialogFragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.eduhg.myshop.R;
import com.eduhg.myshop.database.DBHelper;
import com.eduhg.myshop.database.SqliteHandler;
import com.eduhg.myshop.models.SalesData;

/**
 * Created by EduhG on 10/11/2016.
 */
public class NewSaleDialogFragment  extends DialogFragment {

    private Spinner spCountries;
    private EditText input_units;
    private EditText input_price;

    SqliteHandler myDb;
    DBHelper dbHelper;

    public NewSaleDialogFragment() {
        // Empty constructor is required for DialogFragment
        // Make sure not to add arguments to the constructor
        // Use `newInstance` instead as shown below
    }

    public static NewSaleDialogFragment newInstance(String title) {
        NewSaleDialogFragment frag = new NewSaleDialogFragment();
        Bundle args = new Bundle();
        args.putString("title", title);
        frag.setArguments(args);
        return frag;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.new_sales_entry, container);

        spCountries = (Spinner) view.findViewById(R.id.spCountries);
        input_units = (EditText) view.findViewById(R.id.input_units);
        input_price = (EditText) view.findViewById(R.id.input_price);

        ((Button) view.findViewById(R.id.dismisDialogButton)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // When button is clicked, call up to owning activity.
                NewSaleDialogFragment.this.dismiss();
            }
        });

        ((Button) view.findViewById(R.id.confirmationDialogButton)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // When button is clicked, call up to owning activity.
                getDataForInserting();
                Toast.makeText(getActivity(), "Settings Clicked", Toast.LENGTH_LONG).show();

                NewSaleDialogFragment.this.dismiss();
            }
        });

        myDb = new SqliteHandler(getContext());

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // Get field from view

        // Fetch arguments from bundle and set title
        String title = getArguments().getString("title", "Add Stock");
        getDialog().setTitle(title);
        // Show soft keyboard automatically and request focus to field
        // mEditText.requestFocus();
        getDialog().getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
    }

    private void getDataForInserting() {
        /*String item_name = spCountries.getSelectedItem().toString();
        String quantity = input_units.getText().toString();
        String price = input_price.getText().toString();

        myDb.addSale(item_name, quantity, price);

        input_units.setText("");
        input_price.setText("");

        Toast.makeText(getActivity(), "Data Added Successfully", Toast.LENGTH_LONG).show();
        */

        SalesData salesData = new SalesData();

        if (!spCountries.getSelectedItem().toString().isEmpty()) {
            salesData.item_name = spCountries.getSelectedItem().toString();
        } else {
            salesData.item_name = "";
        }
        if (!input_units.getText().toString().isEmpty()) {
            salesData.quantity_sold = input_units.getText().toString();
        } else {
            salesData.quantity_sold = "";
        }
        if (!input_price.getText().toString().isEmpty()) {
            salesData.unit_price = input_price.getText().toString();
        } else {
            salesData.unit_price = "";
        }

        try {
            dbHelper.insertUserDetail(salesData);

            input_units.setText("");
            input_price.setText("");

            Toast.makeText(getActivity(), "Data Added Successfully", Toast.LENGTH_LONG).show();

        } catch (Exception e) {
            Toast.makeText(getActivity(), "Data Not Added", Toast.LENGTH_LONG).show();
        }


    }
}