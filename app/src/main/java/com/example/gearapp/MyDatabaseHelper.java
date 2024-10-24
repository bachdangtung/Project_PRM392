package com.example.gearapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MyDatabaseHelper extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME = "GearApp.db";
    private static final int DATABASE_VERSION = 1;

    // Table category
    private static final String TABLE_CATEGORY = "category";
    private static final String COLUMN_CATEGORY_ID = "id";
    private static final String COLUMN_CATEGORY_NAME = "name";
    private static final String COLUMN_CATEGORY_IMAGE = "image";

    // Table product
    private static final String TABLE_PRODUCT = "product";
    private static final String COLUMN_PRODUCT_ID = "id";
    private static final String COLUMN_PRODUCT_NAME = "name";
    private static final String COLUMN_PRODUCT_IMAGE = "image";
    private static final String COLUMN_PRODUCT_PRICE = "price";
    private static final String COLUMN_PRODUCT_CATEGORY_ID = "category_id";

    //table cart
    private static final String TABLE_CART = "cart";
    private static final String COLUMN_CART_ID = "id";
    private static final String COLUMN_CART_NAME = "name";
    private static final String COLUMN_CART_IMAGE = "image";
    private static final String COLUMN_CART_PRICE = "price";
    private static final String COLUMN_CART_QUANTITY = "quantity";

    //table user
    private static final String TABLE_USER = "user";
    private static final String COLUMN_USER_ID = "id";
    private static final String COLUMN_USER_EMAIL = "email";
    private static final String COLUMN_USER_NAME = "name";
    private static final String COLUMN_USER_PASSWORD = "password";
    private static final String COLUMN_USER_MOBILE = "mobile";
    private static final String COLUMN_USER_STATUS = "status";

    //table order
    private static final String TABLE_ORDER = "order";
    private static final String COLUMN_ORDER_ID = "id";
    private static final String COLUMN_ORDER_ADDRESS = "address";
    private static final String COLUMN_ORDER_PHONE = "phone";
    private static final String COLUMN_ORDER_EMAIL = "email";
    private static final String COLUMN_ORDER_QUANTITY = "quantity";
    private static final String COLUMN_ORDER_TOTALMONEY = "totalmoney";
    private static final String COLUMN_ORDER_STATUS = "status";
    private static final String COLUMN_ORDER_DATEORDER = "dateorder";
    private static final String COLUMN_ORDER_USER_ID = "user_id";

    //table orderdetail
    private static final String TABLE_ORDERDETAIL = "orderdetail";
    private static final String COLUMN_ORDERDETAIL_ID = "id";
    private static final String COLUMN_ORDERDETAIL_QUANTITY = "quantity";
    private static final String COLUMN_ORDERDETAIL_PRICE = "price";
    private static final String COLUMN_ORDERDETAIL_ORDER_ID = "order_id";
    private static final String COLUMN_ORDERDETAIL_PRODUCT_ID = "product_id";

    //table statistic
    private static final String TABLE_STATISTIC = "statistic";
    private static final String COLUMN_STATISTIC_DATE = "date";
    private static final String COLUMN_STATISTIC_TOTALMONEY = "totalmoney";
    private static final String COLUMN_STATISTIC_TOTALORDER = "totalorder";
    private static final String COLUMN_STATISTIC_TOTALPRODUCT = "totalproduct";

    //table ChatMessage
    private static final String TABLE_CHATMESSAGE = "chatmessage";
    private static final String COLUMN_CHATMESSAGE_SENDID = "sendid";
    private static final String COLUMN_CHATMESSAGE_RECEIVEDID = "receivedid";
    private static final String COLUMN_CHATMESSAGE_MESSAGE = "message";
    private static final String COLUMN_CHATMESSAGE_DATE = "date";

    //table Message
    private static final String TABLE_MESSAGE = "message";
    private static final String COLUMN_MESSAGE_SUCCESS = "success";
    private static final String COLUMN_MESSAGE_MESSAGE = "message";
    private static final String COLUMN_MESSAGE_NAME = "name";



    public MyDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Tạo bảng category
        String createCategoryTable = "CREATE TABLE " + TABLE_CATEGORY + " (" +
                COLUMN_CATEGORY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_CATEGORY_NAME + " TEXT, " +
                COLUMN_CATEGORY_IMAGE + " TEXT);";
        db.execSQL(createCategoryTable);

        // Tạo bảng product
        String createProductTable = "CREATE TABLE " + TABLE_PRODUCT + " (" +
                COLUMN_PRODUCT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_PRODUCT_NAME + " TEXT, " +
                COLUMN_PRODUCT_IMAGE + " TEXT, " +
                COLUMN_PRODUCT_PRICE + " TEXT, " +
                COLUMN_PRODUCT_CATEGORY_ID + " INTEGER, " +
                "FOREIGN KEY (" + COLUMN_PRODUCT_CATEGORY_ID + ") REFERENCES " + TABLE_CATEGORY + "(" + COLUMN_CATEGORY_ID + "));";
        db.execSQL(createProductTable);

        // Tạo bảng cart
        String createCartTable = "CREATE TABLE " + TABLE_CART + " (" +
                COLUMN_CART_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_CART_NAME + " TEXT, " +
                COLUMN_CART_IMAGE + " TEXT, " +
                COLUMN_CART_PRICE + " TEXT, " +
                COLUMN_CART_QUANTITY + " INTEGER);";
        db.execSQL(createCartTable);

        // Tạo bảng user
        String createUserTable = "CREATE TABLE " + TABLE_USER + " (" +
                COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_USER_EMAIL + " TEXT, " +
                COLUMN_USER_NAME + " TEXT, " +
                COLUMN_USER_PASSWORD + " TEXT, " +
                COLUMN_USER_MOBILE + " TEXT, " +
                COLUMN_USER_STATUS + " TEXT);";
        db.execSQL(createUserTable);

        //tao bang order
        String createOrderTable = "CREATE TABLE [" + TABLE_ORDER + "] (" +
                COLUMN_ORDER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_ORDER_ADDRESS + " TEXT, " +
                COLUMN_ORDER_PHONE + " TEXT, " +
                COLUMN_ORDER_EMAIL + " TEXT, " +
                COLUMN_ORDER_QUANTITY + " INTEGER, " +
                COLUMN_ORDER_TOTALMONEY + " TEXT, " +
                COLUMN_ORDER_STATUS + " TEXT, " +
                COLUMN_ORDER_DATEORDER + " TEXT, " +
                COLUMN_ORDER_USER_ID + " INTEGER, " +
                "FOREIGN KEY (" + COLUMN_ORDER_USER_ID + ") REFERENCES " + TABLE_USER + "(" + COLUMN_USER_ID + "));";
        db.execSQL(createOrderTable);

        // tạo bảng orderdetail
        String createOrderDetailTable = "CREATE TABLE [" + TABLE_ORDERDETAIL + "] (" +
                COLUMN_ORDERDETAIL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_ORDERDETAIL_QUANTITY + " INTEGER, " +
                COLUMN_ORDERDETAIL_PRICE + " TEXT, " +
                COLUMN_ORDERDETAIL_ORDER_ID + " INTEGER, " +
                COLUMN_ORDERDETAIL_PRODUCT_ID + " INTEGER, " +
                "FOREIGN KEY (" + COLUMN_ORDERDETAIL_ORDER_ID + ") REFERENCES [" + TABLE_ORDER + "](" + COLUMN_ORDER_ID + "), " +
                "FOREIGN KEY (" + COLUMN_ORDERDETAIL_PRODUCT_ID + ") REFERENCES [" + TABLE_PRODUCT + "](" + COLUMN_PRODUCT_ID + "));";
        db.execSQL(createOrderDetailTable);

        //tạo bảng statistic
        String createStatisticTable = "CREATE TABLE " + TABLE_STATISTIC + " (" +
                COLUMN_STATISTIC_DATE + " TEXT PRIMARY KEY, " +
                COLUMN_STATISTIC_TOTALMONEY + " TEXT, " +
                COLUMN_STATISTIC_TOTALORDER + " INTEGER, " +
                COLUMN_STATISTIC_TOTALPRODUCT + " INTEGER);";
        db.execSQL(createStatisticTable);

        //tạo bảng chatmessage
        String createChatMessageTable = "CREATE TABLE " + TABLE_CHATMESSAGE + " (" +
                COLUMN_CHATMESSAGE_SENDID + " INTEGER, " +
                COLUMN_CHATMESSAGE_RECEIVEDID + " INTEGER, " +
                COLUMN_CHATMESSAGE_MESSAGE + " TEXT, " +
                COLUMN_CHATMESSAGE_DATE + " TEXT);";
        db.execSQL(createChatMessageTable);

        //tao bang message
        String createMessageTable = "CREATE TABLE " + TABLE_MESSAGE + " (" +
                COLUMN_MESSAGE_SUCCESS + " INTEGER, " +
                COLUMN_MESSAGE_MESSAGE + " TEXT, " +
                COLUMN_MESSAGE_NAME + " TEXT);";
        db.execSQL(createMessageTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CATEGORY);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUCT);
        onCreate(db);
    }

    // Thêm danh mục vào bảng category
    void addCategory(String name, String image) {
                SQLiteDatabase db = this.getWritableDatabase();
                ContentValues cv = new ContentValues();

                cv.put(COLUMN_CATEGORY_NAME, name);
                cv.put(COLUMN_CATEGORY_IMAGE, image);
                long result = db.insert(TABLE_CATEGORY, null, cv);
                if (result == -1) {
                    Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "Added successfully!", Toast.LENGTH_SHORT).show();
        }
    }

    // Thêm sản phẩm vào bảng product
    void addProduct(String name, String image, String price, int category_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_PRODUCT_NAME, name);
        cv.put(COLUMN_PRODUCT_IMAGE, image);
        cv.put(COLUMN_PRODUCT_PRICE, price);
        cv.put(COLUMN_PRODUCT_CATEGORY_ID, category_id);
        long result = db.insert(TABLE_PRODUCT, null, cv);
        if (result == -1) {
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Added successfully!", Toast.LENGTH_SHORT).show();
        }
    }

    // Thêm sản phẩm vào bảng cart
    void addCart(String name, String image, String price, int quantity) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_CART_NAME, name);
        cv.put(COLUMN_CART_IMAGE, image);
        cv.put(COLUMN_CART_PRICE, price);
        cv.put(COLUMN_CART_QUANTITY, quantity);
        long result = db.insert(TABLE_CART, null, cv);
        if (result == -1) {
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Added successfully!", Toast.LENGTH_SHORT).show();
        }
    }

    // Thêm người dùng vào bảng user
    void addUser(String email, String name, String password, String mobile, String status) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_USER_EMAIL, email);
        cv.put(COLUMN_USER_NAME, name);
        cv.put(COLUMN_USER_PASSWORD, password);
        cv.put(COLUMN_USER_MOBILE, mobile);
        cv.put(COLUMN_USER_STATUS, status);
        long result = db.insert(TABLE_USER, null, cv);
        if (result == -1) {
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Added successfully!", Toast.LENGTH_SHORT).show();
        }
    }

    // Thêm đơn hàng vào bảng order
    void addOrder(String address, String phone, String email, int quantity, String totalmoney, String status, String dateorder, int user_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_ORDER_ADDRESS, address);
        cv.put(COLUMN_ORDER_PHONE, phone);
        cv.put(COLUMN_ORDER_EMAIL, email);
        cv.put(COLUMN_ORDER_QUANTITY, quantity);
        cv.put(COLUMN_ORDER_TOTALMONEY, totalmoney);
        cv.put(COLUMN_ORDER_STATUS, status);
        cv.put(COLUMN_ORDER_DATEORDER, dateorder);
        cv.put(COLUMN_ORDER_USER_ID, user_id);
        long result = db.insert(TABLE_ORDER, null, cv);
        if (result == -1) {
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Added successfully!", Toast.LENGTH_SHORT).show();
        }
    }

    // Thêm chi tiết đơn hàng vào bảng orderdetail
    void addOrderDetail(int quantity, String price, int order_id, int product_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_ORDERDETAIL_QUANTITY, quantity);
        cv.put(COLUMN_ORDERDETAIL_PRICE, price);
        cv.put(COLUMN_ORDERDETAIL_ORDER_ID, order_id);
        cv.put(COLUMN_ORDERDETAIL_PRODUCT_ID, product_id);
        long result = db.insert(TABLE_ORDERDETAIL, null, cv);
        if (result == -1) {
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Added successfully!", Toast.LENGTH_SHORT).show();
        }
    }

    // Thêm thống kê vào bảng statistic
    void addStatistic(String date, String totalmoney, int totalorder, int totalproduct) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_STATISTIC_DATE, date);
        cv.put(COLUMN_STATISTIC_TOTALMONEY, totalmoney);
        cv.put(COLUMN_STATISTIC_TOTALORDER, totalorder);
        cv.put(COLUMN_STATISTIC_TOTALPRODUCT, totalproduct);
        long result = db.insert(TABLE_STATISTIC, null, cv);
        if (result == -1) {
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Added successfully!", Toast.LENGTH_SHORT).show();
        }
    }

    // Thêm tin nhắn vào bảng chatmessage
    void addChatMessage(int sendid, int receivedid, String message, String date) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_CHATMESSAGE_SENDID, sendid);
        cv.put(COLUMN_CHATMESSAGE_RECEIVEDID, receivedid);
        cv.put(COLUMN_CHATMESSAGE_MESSAGE, message);
        cv.put(COLUMN_CHATMESSAGE_DATE, date);
        long result = db.insert(TABLE_CHATMESSAGE, null, cv);
        if (result == -1) {
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Added successfully!", Toast.LENGTH_SHORT).show();
        }
    }

    // Thêm tin nhắn vào bảng message
    void addMessage(String message, String name, boolean success) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_MESSAGE_SUCCESS, success ? 1 : 0);
        cv.put(COLUMN_MESSAGE_MESSAGE, message);
        cv.put(COLUMN_MESSAGE_NAME, name);
        long result = db.insert(TABLE_MESSAGE, null, cv);
        if (result == -1) {
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Added successfully!", Toast.LENGTH_SHORT).show();
        }
    }
}