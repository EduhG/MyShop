package com.eduhg.myshop;

/**
 * Created by EduhG on 10/9/2016.
 */
public class SoldItem {
    private String item_name;
    private String qty_sold;
    private String qty_remaining;

    public String getItemName() {
        return item_name;
    }

    public void setItemName(String name) {
        this.item_name = name;
    }

    public String getQtySold() {
        return qty_sold;
    }

    public void setQtySold(String sold) {
        this.qty_sold = sold;
    }

    public String getQtyRemaining() {
        return qty_remaining;
    }

    public void setQtyRemaining(String remainig) {
        this.qty_remaining = remainig;
    }

}
