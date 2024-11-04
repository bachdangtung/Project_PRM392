package com.example.gearapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class OrderDatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "GearApp.db";
    private static final int DATABASE_VERSION = 1;

    // Order table
    private static final String TABLE_ORDER = "OrderTable";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_ADDRESS = "address";
    private static final String COLUMN_PHONE = "phone";
    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_QUANTITY = "quantity";
    private static final String COLUMN_TOTAL_MONEY = "totalMoney";
    private static final String COLUMN_STATUS = "status";
    private static final String COLUMN_DATE_ORDER = "dateOrder";

    // Create table SQL
    private static final String CREATE_ORDER_TABLE = "CREATE TABLE " + TABLE_ORDER + "("
            + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_ADDRESS + " TEXT, "
            + COLUMN_PHONE + " TEXT, "
            + COLUMN_EMAIL + " TEXT, "
            + COLUMN_QUANTITY + " INTEGER, "
            + COLUMN_TOTAL_MONEY + " TEXT, "
            + COLUMN_STATUS + " TEXT, "
            + COLUMN_DATE_ORDER + " TEXT)";

    public OrderDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_ORDER_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ORDER);
        onCreate(db);
    }

    // Method to insert an order into the database
    public boolean insertOrder(String address, String phone, String email, int quantity, String totalMoney, String status, String dateOrder) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ADDRESS, address);
        values.put(COLUMN_PHONE, phone);
        values.put(COLUMN_EMAIL, email);
        values.put(COLUMN_QUANTITY, quantity);
        values.put(COLUMN_TOTAL_MONEY, totalMoney);
        values.put(COLUMN_STATUS, status);
        values.put(COLUMN_DATE_ORDER, dateOrder);

        long result = -1;
        try {
            result = db.insert(TABLE_ORDER, null, values);
        } catch (Exception e) {
            Log.e("DatabaseError", "Exception: " + e.getMessage());
        } finally {
            db.close();
        }

        return result != -1; // Returns true if the insert was successful
    }
}
