package com.eduhg.myshop.models;

import java.util.ArrayList;

/**
 * Created by EduhG on 10/9/2016.
 */
public class SoldItem {
    private String mItemName;
    private String mQtySold;
    private String mQtyRemaining;

    public SoldItem(String name, String sold, String remaining) {
        mItemName = name;
        mQtySold = sold;
        mQtyRemaining = remaining;
    }

    public String getItemName() {
        return mItemName;
    }

    public String getQtySold() {
        return mQtySold;
    }

    public String getQtyRemaining() {
        return mQtyRemaining;
    }

    private static int lastItemId = 0;

    public static ArrayList<SoldItem> createSoldItemsList(int numItems) {
        ArrayList<SoldItem> soldItems = new ArrayList<SoldItem>();

        for (int i = 1; i <= numItems; i++) {
            soldItems.add(new SoldItem("Sold Item " + ++lastItemId, "50", "10"));
        }

        return soldItems;
    }










}
