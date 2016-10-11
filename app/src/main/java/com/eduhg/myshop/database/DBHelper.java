package com.eduhg.myshop.database;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.eduhg.myshop.models.SalesData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by EduhG on 10/11/2016.
 */
public class DBHelper extends SQLiteOpenHelper {

    private static final String TAG = "DbHelper";
    // Database Info
    private static final String DATABASE_NAME = "my_shop";
    private static final int DATABASE_VERSION = 1;

    //Table Names
    private static final String TABLE_SALES = "sales";

    //userdetail Table Columns
    private static final String _ID = "_id";
    private static final String ITEM_NAME = "item_name";
    private static final String QUANTITY_SOLD = "quantity_sold";
    private static final String UNIT_PRICE = "unit_price";
    private static final String QUANTITY_REMAINING = "quantity_remaining";

    private static DBHelper mDbHelper;

    /*public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }*/

    public static synchronized DBHelper getInstance(Context context) {
        // Use the application context, which will ensure that you
        // don't accidentally leak an Activity's context.

        if (mDbHelper == null) {
            mDbHelper = new DBHelper(context.getApplicationContext());
        }
        return mDbHelper;
    }


    /**
     * Constructor should be private to prevent direct instantiation.
     * Make a call to the static method "getInstance()" instead.
     */
    private DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_SALES_TABLE = "CREATE TABLE " + TABLE_SALES +
                "(" +
                _ID + " INTEGER PRIMARY KEY ," +
                ITEM_NAME + " TEXT," +
                QUANTITY_SOLD + " TEXT," +
                UNIT_PRICE + " TEXT," +
                QUANTITY_REMAINING + " TEXT" +
                ")";

        db.execSQL(CREATE_SALES_TABLE);
    }

    /*
    Called when the database needs to be upgraded.
    This method will only be called if a database already exists on disk with the same DATABASE_NAME,
    but the DATABASE_VERSION is different than the version of the database that exists on disk.
    */

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion != newVersion) {
            // Simplest implementation is to drop all old tables and recreate them
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_SALES);

            onCreate(db);
        }
    }

    /*
   Insert a  user detail into database
   */

    public void insertUserDetail(SalesData salesData) {

        SQLiteDatabase db = getWritableDatabase();

        db.beginTransaction();

        try {
            ContentValues values = new ContentValues();
            values.put(ITEM_NAME, salesData.item_name);
            values.put(QUANTITY_SOLD, salesData.quantity_sold);
            values.put(UNIT_PRICE, salesData.unit_price);
            values.put(QUANTITY_REMAINING, salesData.quantity_remaining);

            db.insertOrThrow(TABLE_SALES, null, values);
            db.setTransactionSuccessful();
        } catch (SQLException e) {
            e.printStackTrace();
            Log.d(TAG, "Error while trying to add post to database");
        } finally {

            db.endTransaction();
        }
    }

    /*
    fetch all data from UserTable
    */

    public List<SalesData> getAllSalesData() {

        List<SalesData> salesDetails = new ArrayList<>();

        String USER_DETAIL_SELECT_QUERY = "SELECT * FROM " + TABLE_SALES;

        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(USER_DETAIL_SELECT_QUERY, null);

        try {
            if (cursor.moveToFirst()) {
                do {
                    SalesData salesData = new SalesData();
                    salesData.item_name = cursor.getString(cursor.getColumnIndex(ITEM_NAME));
                    salesData.quantity_sold = cursor.getString(cursor.getColumnIndex(QUANTITY_SOLD));
                    salesData.unit_price = cursor.getString(cursor.getColumnIndex(UNIT_PRICE));
                    salesData.quantity_remaining = cursor.getString(cursor.getColumnIndex(QUANTITY_REMAINING));

                    salesDetails.add(salesData);

                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            Log.d(TAG, "Error while trying to get posts from database");
        } finally {
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
        }

        return salesDetails;

    }
}
