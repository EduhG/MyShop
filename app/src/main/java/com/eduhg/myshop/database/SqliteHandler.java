package com.eduhg.myshop.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by EduhG on 10/11/2016.
 */
public class SqliteHandler  extends SQLiteOpenHelper {

    public static final String TABLE_NAME = "sales";

    public static final String ID = "id";
    public static final String ITEM_NAME = "item_name";
    public static final String QUANTITY = "quantity";
    public static final String PRICE = "price";

    private static final String TAG = SqliteHandler.class.getCanonicalName();

    public SqliteHandler(Context context) {
        super(context, "myshop", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String table_sql = "CREATE TABLE sales (id INTEGER PRIMARY KEY " +
                "item_name TEXT " +
                "quantity INTEGER " +
                "price DOUBLE)";

        db.execSQL(table_sql);

        Log.d(TAG, "Database Table created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);

        onCreate(db);
    }

    void addSale(String item_name, String quantity, String price) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(ITEM_NAME, item_name);
        contentValues.put(QUANTITY, quantity);
        contentValues.put(PRICE, price);

        long id = db.insert(TABLE_NAME, null, contentValues);
        db.close();

        Log.d(TAG, "New sales record inserted successfully");
    }
}
