package com.eduhg.myshop.models;

import java.io.Serializable;

/**
 * Created by EduhG on 10/11/2016.
 */
//public class SalesData implements Serializable {
public class SalesData {
    public String item_name, quantity_sold, quantity_remaining, unit_price;

    //public SalesData(String item_name, String quantity_sold, String unit_price) {
    public SalesData() {

    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public String getItem_name() {
        return item_name;
    }

    public void setQuantity_sold(String quantity_sold) {
        this.quantity_sold = quantity_sold;
    }

    public String getQuantity_sold() {
        return quantity_sold;
    }

    public void setUnit_price(String unit_price) {
        this.unit_price = unit_price;
    }

    public String getUnit_price() {
        return unit_price;
    }
}
